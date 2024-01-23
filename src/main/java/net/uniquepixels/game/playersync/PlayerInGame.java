package net.uniquepixels.game.playersync;

import net.kyori.adventure.text.Component;
import net.uniquepixels.game.Game;
import net.uniquepixels.game.GameEngine;
import net.uniquepixels.game.communication.Communicator;
import net.uniquepixels.game.playersync.event.impl.GameEntityDamageEntityEvent;
import net.uniquepixels.game.playersync.event.impl.GamePlayerInteractEvent;
import net.uniquepixels.game.playersync.event.impl.GamePlayerJoinEvent;
import net.uniquepixels.game.playersync.event.impl.GamePlayerMoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;
import java.util.UUID;

public class PlayerInGame implements Listener {

  private final GameEngine gameEngine = JavaPlugin.getPlugin(GameEngine.class);
  private final Communicator communicator;

  public PlayerInGame(Communicator communicator) {
    this.communicator = communicator;
  }

  private Optional<Game<? extends JavaPlugin>> getGameFromEntity(Entity entity) {
    return this.gameEngine.getActiveGames().stream().filter(game -> game.getCreatedByGame().contains(entity)).findFirst();
  }

  private Optional<Game<? extends JavaPlugin>> getGameFromPlayer(Player player) {
    return this.gameEngine.getActiveGames().stream().filter(game -> game.getPlayers().contains(player)).findFirst();
  }

  private Optional<UUID> getRedirect(UUID uid) {

    if (!this.communicator.getPool().exists(uid.toString()))
      return Optional.empty();

    String rawGameId = this.communicator.getPool().get(uid.toString());

    return Optional.of(UUID.fromString(rawGameId));
  }

  @EventHandler(priority = EventPriority.HIGH)
  public void onMove(PlayerMoveEvent event) {

    Player player = event.getPlayer();

    Optional<Game<? extends JavaPlugin>> gameFromPlayer = this.getGameFromPlayer(player);

    if (gameFromPlayer.isEmpty()) {
      player.kick(Component.text("move"));// TODO translate
      return;
    }

    GamePlayerMoveEvent gameEvent = new GamePlayerMoveEvent(gameFromPlayer.get(), player, event.getFrom(), event.getTo());
    Bukkit.getPluginManager().callEvent(gameEvent);

    event.setCancelled(gameEvent.isCancelled());
  }

  @EventHandler(priority = EventPriority.HIGH)
  public void onJoin(PlayerJoinEvent event) {

    Player player = event.getPlayer();
    event.joinMessage(Component.empty());

    Optional<UUID> optional = this.getRedirect(player.getUniqueId());
    this.removeData(player.getUniqueId());

    if (optional.isEmpty()) {
      player.kick(Component.text("Es konnte kein kompatibler Game Server gefunden werden!")); // TODO translate
      return;
    }

    Optional<Game<? extends JavaPlugin>> first = this.gameEngine.getActiveGames().stream().filter(game -> game.getGameId().equals(optional.get())).findFirst();

    if (first.isEmpty()) {
      player.kick(Component.text("Es konnte kein kompatibler Game Server gefunden werden!"));// TODO translate
      return;
    }

    Game<? extends JavaPlugin> game = first.get();

    gameEngine.hidePlayerFromOtherGames(player);

    GamePlayerJoinEvent gameEvent = new GamePlayerJoinEvent(game, player);
    Bukkit.getPluginManager().callEvent(gameEvent);

    if (gameEvent.isCancelled()) {
      player.kick();
    }
  }

  private void removeData(UUID uid) {
    this.communicator.getPool().del(uid.toString());
  }

  @EventHandler(priority = EventPriority.HIGH)
  public void onInteract(PlayerInteractEvent event) {

    Player player = event.getPlayer();


    Optional<Game<? extends JavaPlugin>> gameFromPlayer = this.getGameFromPlayer(player);

    if (gameFromPlayer.isEmpty()) {
      player.kick(Component.text("move"));
      return;
    }

    Bukkit.getPluginManager().callEvent(new GamePlayerInteractEvent(gameFromPlayer.get(), player, event.getItem(), event.getAction(), event.getClickedBlock(), event.getBlockFace()));
  }

  @EventHandler(priority = EventPriority.HIGH)
  public void onEntityDamage(EntityDamageByEntityEvent event) {

    Optional<Game<? extends JavaPlugin>> game = null;

    if (event.getDamager() instanceof Player player) {
      game = this.getGameFromPlayer(player);

      if (game.isEmpty()) {
        player.kick(Component.text("move"));
        return;
      }

    } else {


      game = this.getGameFromEntity(event.getDamager());

    }

    Bukkit.getPluginManager().callEvent(new GameEntityDamageEntityEvent(game.get(), event.getDamager(), event.getEntity(), event.getDamage()));

  }


}
