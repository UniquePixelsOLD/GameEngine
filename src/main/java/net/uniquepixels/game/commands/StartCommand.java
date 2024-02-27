package net.uniquepixels.game.commands;

import net.uniquepixels.game.GameCountdown;
import net.uniquepixels.game.GameEngine;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StartCommand implements CommandExecutor {


  @Override
  public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

    GameEngine plugin = GameEngine.getPlugin(GameEngine.class);

    if (!(commandSender instanceof Player player))
      return true;

    GameCountdown gameCountdown = plugin.getGame().startCountdown();
    gameCountdown.setStartCountdown(10); // TODO - change back to 30 seconds

    return true;
  }
}
