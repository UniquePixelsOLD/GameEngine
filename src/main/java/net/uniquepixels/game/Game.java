package net.uniquepixels.game;

import org.bukkit.NamespacedKey;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {

  private final List<World> requiredWorlds = new ArrayList<>();
  private final int maxPlayers;
  private final int minPlayers;
  private final int requiredPlayers;
  private final NamespacedKey gameKey;
  private int currentPlayers = 0;
  private GameState currentState = GameState.EMPTY;
  private GameState nextStep = GameState.AWAITING_PLAYERS;

  public Game(int maxPlayers, int minPlayers, int requiredPlayers, NamespacedKey gameKey) {
    this.maxPlayers = maxPlayers;
    this.minPlayers = minPlayers;
    this.requiredPlayers = requiredPlayers;
    this.gameKey = gameKey;
  }

  public GameState getCurrentState() {
    return currentState;
  }

  public void setCurrentState(GameState currentState) {
    this.currentState = currentState;
  }

  public GameState getNextStep() {
    return nextStep;
  }

  public void setNextStep(GameState nextStep) {
    this.nextStep = nextStep;
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
