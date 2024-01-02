package net.uniquepixels.game;

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

  @Override
  public void onEnable() {


    PluginManager pm = Bukkit.getPluginManager();
  }

  @Override
  public void onDisable() {

  }
}
