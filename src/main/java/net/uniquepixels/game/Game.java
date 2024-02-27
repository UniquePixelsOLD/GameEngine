package net.uniquepixels.game;

import net.kyori.adventure.text.Component;
import net.uniquepixels.game.config.GameType;
import net.uniquepixels.game.config.StateConfig;
import net.uniquepixels.game.config.StateListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public abstract class Game implements Listener {
  private final int maxPlayers;
  private final int minPlayers;
  private final int requiredPlayers;
  private final Material uiItem;
  private final GameType type;
  private final UUID gameId;
  private GameState currentState = GameState.WAITING;

  public Game(int maxPlayers, int minPlayers, int requiredPlayers, Material uiItem, GameType type, Plugin plugin) {
    this.maxPlayers = maxPlayers;
    this.minPlayers = minPlayers;
    this.requiredPlayers = requiredPlayers;
    this.uiItem = uiItem;
    this.type = type;
    this.gameId = UUID.randomUUID();

    registerStateListener(plugin);
  }

  public abstract GameCountdown startCountdown();

  public abstract StateConfig onWaitingState();

  public abstract StateConfig onRunningState();

  public abstract StateConfig onEndingState();

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {

    if (this.getCurrentState() != GameState.WAITING) {
      event.joinMessage(Component.empty());
      event.getPlayer().kick();
      return;
    }

    event.joinMessage(Component.text("Player joined"));

    this.onJoin(event);
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {

    event.quitMessage(Component.text("Player left"));
    this.onQuit(event);
  }

  public abstract void onJoin(PlayerJoinEvent event);

  public abstract void onQuit(PlayerQuitEvent event);

  private void registerStateListener(Plugin plugin) {
    PluginManager pluginManager = Bukkit.getPluginManager();

    pluginManager.registerEvents(new StateListener(this), plugin);
  }


  public UUID getGameId() {
    return gameId;
  }

  public Material getUiItem() {
    return uiItem;
  }

  public GameState getCurrentState() {
    return currentState;
  }

  public void setCurrentState(GameState currentState) {
    this.currentState = currentState;
  }

  public GameType getType() {
    return type;
  }

  public int getMaxPlayers() {
    return maxPlayers;
  }

  public int getMinPlayers() {
    return minPlayers;
  }

  public int getRequiredPlayers() {
    return requiredPlayers;
  }
}
