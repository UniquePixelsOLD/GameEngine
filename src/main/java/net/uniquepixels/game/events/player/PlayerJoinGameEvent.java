package net.uniquepixels.game.events.player;

import net.uniquepixels.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerJoinGameEvent extends PlayerGameEvent {

  private static final HandlerList handlers = new HandlerList();

  public PlayerJoinGameEvent(Game game, Player player) {
    super(game, player);
  }

  public static @NotNull HandlerList getHandlerList() {
    return handlers;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return handlers;
  }
}
