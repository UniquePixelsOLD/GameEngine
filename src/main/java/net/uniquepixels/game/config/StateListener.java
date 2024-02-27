package net.uniquepixels.game.config;

import net.uniquepixels.game.Game;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class StateListener implements Listener {

  private final Game game;

  public StateListener(Game game) {
    this.game = game;
  }

  private void handle(Cancellable cancellable, BlockState state) {
    switch (this.game.getCurrentState()) {
      case WAITING -> cancellable.setCancelled(this.game.onWaitingState().isBlocked(state));
      case ENDING -> cancellable.setCancelled(this.game.onEndingState().isBlocked(state));
      case RUNNING -> cancellable.setCancelled(this.game.onRunningState().isBlocked(state));
    }
  }
  @EventHandler
  public void onFoodLevelChange(FoodLevelChangeEvent event) {
    this.handle(event, BlockState.FOOD);
  }

  @EventHandler
  public void onEntityDamage(EntityDamageEvent event) {
    this.handle(event, BlockState.DAMAGE);
  }

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    this.handle(event, BlockState.BLOCK_BREAK);
  }

  @EventHandler
  public void onBlockPlace(BlockPlaceEvent event) {
    this.handle(event, BlockState.BLOCK_PLACE);
  }

  @EventHandler
  public void onBlockPlace(PlayerInteractEvent event) {
    this.handle(event, BlockState.INTERACT);
  }

}
