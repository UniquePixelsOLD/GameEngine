package net.uniquepixels.game.playersync.event;

import net.uniquepixels.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PlayerEvent extends GameEvent {

  private final Player player;

  public Player getPlayer() {
    return player;
  }

  public PlayerEvent(Game<? extends JavaPlugin> game, Player player) {
    super(game);
    this.player = player;
  }
}
