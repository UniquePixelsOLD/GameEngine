package net.uniquepixels.game.listeners.player;

import net.kyori.adventure.text.Component;
import net.uniquepixels.game.GameEngine;
import net.uniquepixels.game.events.player.PlayerJoinGameEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

  private final GameEngine gameEngine;

  public PlayerJoinListener(GameEngine gameEngine) {
    this.gameEngine = gameEngine;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onJoin(PlayerJoinEvent event) {

    Player player = event.getPlayer();
    event.joinMessage(Component.empty());

    UUID uid = player.getWorld().getUID();

    Bukkit.getPluginManager().callEvent(new PlayerJoinGameEvent(null, player));

  }
}
