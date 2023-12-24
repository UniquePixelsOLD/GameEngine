package net.uniquepixels.game;

public enum GameState {

  EMPTY(0), // Game is not yet set
  PREPARING(1), // The Server is preparing the Game
  AWAITING_PLAYERS(2), // Game waits for enough players
  REQUIRED_PLAYERS_REACHED(3), // Game has required players
  MAX_PLAYERS_REACHED(4), // Game has max players
  STARTING(5), // Game starts
  RUNNING(6), // Game is running
  END(7), // Game is ending
  SEND_PLAYERS_BACK(8), // Game sends players back to previous server
  STOPPING(9); // Game will be deleted

  private final int state;

  GameState(int state) {
    this.state = state;
  }

  public int getState() {
    return state;
  }
}
