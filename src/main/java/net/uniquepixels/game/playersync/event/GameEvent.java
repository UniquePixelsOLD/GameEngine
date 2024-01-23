package net.uniquepixels.game.playersync.event;

import net.uniquepixels.game.Game;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class GameEvent extends Event {

  private final Game<? extends JavaPlugin> game;

  public Game<? extends JavaPlugin> getGame() {
    return game;
  }

  public GameEvent(Game<? extends JavaPlugin> game) {
    this.game = game;
  }
}
