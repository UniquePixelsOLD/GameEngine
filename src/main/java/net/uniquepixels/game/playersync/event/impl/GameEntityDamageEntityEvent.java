package net.uniquepixels.game.playersync.event.impl;

import net.uniquepixels.game.Game;
import net.uniquepixels.game.playersync.event.GameEvent;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class GameEntityDamageEntityEvent extends GameEvent {

  private static final HandlerList handlers = new HandlerList();
  private final Entity damager;
  private final Entity entity;
  private final double damage;

  public GameEntityDamageEntityEvent(Game<? extends JavaPlugin> game, Entity damager, Entity entity, double damage) {
    super(game);
    this.damager = damager;
    this.entity = entity;
    this.damage = damage;
  }

  public static @NotNull HandlerList getHandlerList() {
    return handlers;
  }

  public Entity getDamager() {
    return damager;
  }

  public Entity getEntity() {
    return entity;
  }

  public double getDamage() {
    return damage;
  }

  public @NotNull HandlerList getHandlers() {
    return handlers;
  }
}
