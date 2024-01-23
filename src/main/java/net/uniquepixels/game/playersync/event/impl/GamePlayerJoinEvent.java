package net.uniquepixels.game.playersync.event.impl;

import net.uniquepixels.game.Game;
import net.uniquepixels.game.playersync.event.PlayerEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class GamePlayerJoinEvent extends PlayerEvent implements Cancellable {

  private static final HandlerList handlers = new HandlerList();
  private boolean canceled = false;

  public GamePlayerJoinEvent(Game<? extends JavaPlugin> game, Player player) {
    super(game, player);
  }

  public static @NotNull HandlerList getHandlerList() {
    return handlers;
  }

  public @NotNull HandlerList getHandlers() {
    return handlers;
  }

  @Override
  public boolean isCancelled() {
    return this.canceled;
  }

  @Override
  public void setCancelled(boolean b) {
    this.canceled = b;
  }
}
