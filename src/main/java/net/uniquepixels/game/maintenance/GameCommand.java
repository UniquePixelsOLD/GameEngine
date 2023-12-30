package net.uniquepixels.game.maintenance;

import net.uniquepixels.core.paper.gui.backend.UIHolder;
import net.uniquepixels.game.GameEngine;
import net.uniquepixels.game.maintenance.ui.GamesUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GameCommand implements CommandExecutor {

  private final UIHolder holder;
  private final GameEngine engine;

  public GameCommand(UIHolder holder, GameEngine engine) {
    this.holder = holder;
    this.engine = engine;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

    if (!(sender instanceof Player player))
      return true;


    if (!player.hasPermission("up.game-engine.game")) {

      player.sendMessage("keine Rechte");

      return true;
    }

    this.holder.open(new GamesUI(player, this.engine), player);

    return true;
  }
}
