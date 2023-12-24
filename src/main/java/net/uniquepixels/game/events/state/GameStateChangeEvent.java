package net.uniquepixels.game.events.state;

import net.uniquepixels.game.Game;
import net.uniquepixels.game.GameState;
import net.uniquepixels.game.events.GameEvent;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class GameStateChangeEvent extends GameEvent {

  private static final HandlerList handlers = new HandlerList();
  private final GameState currentGameState;
  private final GameState previousGameState;

  public GameStateChangeEvent(Game game, GameState currentGameState, GameState previousGameState) {
    super(game);
    this.currentGameState = currentGameState;
    this.previousGameState = previousGameState;
  }

  public static @NotNull HandlerList getHandlerList() {
    return handlers;
  }

  public GameState getCurrentGameState() {
    return currentGameState;
  }

  public GameState getPreviousGameState() {
    return previousGameState;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return handlers;
  }
}
