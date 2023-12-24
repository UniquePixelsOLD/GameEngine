package net.uniquepixels.game.events.player;

import net.uniquepixels.game.Game;
import net.uniquepixels.game.events.GameEvent;
import org.bukkit.entity.Player;

public abstract class PlayerGameEvent extends GameEvent {

  private final Player player;

  public PlayerGameEvent(Game game, Player player) {
    super(game);
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }
}
