package net.uniquepixels.game;

import net.uniquepixels.game.config.GameType;
import net.uniquepixels.game.config.StateConfig;
import net.uniquepixels.game.config.StateListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public abstract class Game {
  private final int maxPlayers;
  private final int minPlayers;
  private final int requiredPlayers;
  private final Material uiItem;
  private final GameType type;
  private final NamespacedKey gameKey;
  private final UUID gameId;
  private GameState currentState = GameState.WAITING;

  public Game(int maxPlayers, int minPlayers, int requiredPlayers, Material uiItem, GameType type, NamespacedKey gameKey) {
    this.maxPlayers = maxPlayers;
    this.minPlayers = minPlayers;
    this.requiredPlayers = requiredPlayers;
    this.uiItem = uiItem;
    this.type = type;
    this.gameKey = gameKey;
    this.gameId = UUID.randomUUID();

    registerStateListener();
  }

  public abstract StateConfig onWaitingState();

  public abstract StateConfig onRunningState();

  public abstract StateConfig onEndingState();

  private void registerStateListener() {
    PluginManager pluginManager = Bukkit.getPluginManager();

    pluginManager.registerEvents(new StateListener(this), JavaPlugin.getPlugin(GameEngine.class));
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

  public NamespacedKey getGameKey() {
    return gameKey;
  }
}
