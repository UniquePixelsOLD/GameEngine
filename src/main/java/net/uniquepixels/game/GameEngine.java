package net.uniquepixels.game;

import net.uniquepixels.game.communication.Communicator;
import net.uniquepixels.game.config.GameType;
import net.uniquepixels.game.playersync.PlayerInGame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class GameEngine extends JavaPlugin {

  private final List<Game<? extends JavaPlugin>> activeGames = new ArrayList<>();

  public List<Game<? extends JavaPlugin>> getActiveGames() {
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

    Communicator communicator = new Communicator();

    pm.registerEvents(new PlayerInGame(communicator), this);

  }

  @Override
  public void onDisable() {

  }

  public void hidePlayerFromOtherGames(Player player) {

    for (Game<? extends JavaPlugin> activeGame : activeGames) {

      if (!activeGame.getPlayers().contains(player))
        activeGame.getPlayers().forEach(player1 -> {
          player1.hidePlayer(this, player);
          player.hidePlayer(this, player1);
        });

    }
  }
}
