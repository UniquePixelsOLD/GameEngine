package net.uniquepixels.game;

import net.uniquepixels.game.config.GameType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class GameEngine extends JavaPlugin {

  private final List<Game> activeGames = new ArrayList<>();

  public List<Game> getActiveGames() {
    return activeGames;
  }

  public GameType getServerType() {
    if (this.activeGames.isEmpty())
      return GameType.EMPTY;

    return this.activeGames.get(0).getType();
  }

  @Override
  public void onEnable() {
    PluginManager pm = Bukkit.getPluginManager();
  }

  @Override
  public void onDisable() {

  }
}
