package net.uniquepixels.game;

import net.uniquepixels.game.config.GameType;
import net.uniquepixels.game.exceptions.UnsupportedGameType;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Game<P extends JavaPlugin> {

  private final List<World> requiredWorlds = new ArrayList<>();
  private final int maxPlayers;
  private final int minPlayers;
  private final int requiredPlayers;
  private final Material uiItem;
  private final GameType type;
  private final NamespacedKey gameKey;
  private final UUID gameId;
  private int currentPlayers = 0;
  private GameState currentState = GameState.WAITING;

  public Game(Class<P> pluginClazz, int maxPlayers, int minPlayers, int requiredPlayers, Material uiItem, GameType type, NamespacedKey gameKey) throws UnsupportedGameType {
    this.maxPlayers = maxPlayers;
    this.minPlayers = minPlayers;
    this.requiredPlayers = requiredPlayers;
    this.uiItem = uiItem;
    this.type = type;
    this.gameKey = gameKey;
    this.gameId = UUID.randomUUID();

    this.checkForGameType(pluginClazz);
  }

  private void checkForGameType(Class<P> pluginClazz) throws UnsupportedGameType {
    P gamePlugin = JavaPlugin.getPlugin(pluginClazz);
    GameEngine gameEngine = JavaPlugin.getPlugin(GameEngine.class);

    if (this.type != GameType.EMPTY) {

      gamePlugin.onDisable();
      throw new UnsupportedGameType(type, gameEngine.getServerType());

    }

    gameEngine.getActiveGames().add(this);
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

  public int getCurrentPlayers() {
    return currentPlayers;
  }

  void setCurrentPlayers(int newValue) {
    this.currentPlayers = newValue;
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

  public List<World> getRequiredWorlds() {
    return requiredWorlds;
  }
}
