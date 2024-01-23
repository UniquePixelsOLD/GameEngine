package net.uniquepixels.game.playersync.event.impl;

import net.uniquepixels.game.Game;
import net.uniquepixels.game.playersync.event.PlayerEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class GamePlayerInteractEvent extends PlayerEvent {
  private static final HandlerList handlers = new HandlerList();
  protected ItemStack item;
  protected Action action;
  protected Block blockClicked;
  protected BlockFace blockFace;

  public GamePlayerInteractEvent(Game<? extends JavaPlugin> game, Player player, ItemStack item, Action action, Block blockClicked, BlockFace blockFace) {
    super(game, player);
    this.item = item;
    this.action = action;
    this.blockClicked = blockClicked;
    this.blockFace = blockFace;
  }

  public static @NotNull HandlerList getHandlerList() {
    return handlers;
  }

  public ItemStack getItem() {
    return item;
  }

  public Action getAction() {
    return action;
  }

  public Block getBlockClicked() {
    return blockClicked;
  }

  public BlockFace getBlockFace() {
    return blockFace;
  }

  public @NotNull HandlerList getHandlers() {
    return handlers;
  }
}
