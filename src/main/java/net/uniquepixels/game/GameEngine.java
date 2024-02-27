package net.uniquepixels.game;

import net.uniquepixels.game.commands.StartCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class GameEngine extends JavaPlugin {

  private Game game = null;

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  @Override
  public void onEnable() {

    getCommand("start").setExecutor(new StartCommand());

  }

  @Override
  public void onDisable() {

  }
}
