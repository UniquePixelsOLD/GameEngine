package net.uniquepixels.game.config;

import net.uniquepixels.game.GameState;

import java.util.Arrays;
import java.util.List;

public class StateConfig {

  private final GameState mainState;
  private final List<BlockState> blocked;

  public static StateConfig all(GameState state) {
    return new StateConfig(state, BlockState.values());
  }

  public StateConfig(GameState mainState, BlockState... blocked) {
    this.mainState = mainState;
    this.blocked = Arrays.stream(blocked).toList();
  }

  public List<BlockState> getBlocked() {
    return blocked;
  }

  public GameState getMainState() {
    return mainState;
  }

  public boolean isBlocked(BlockState state) {
    return this.blocked.contains(state);
  }
}
