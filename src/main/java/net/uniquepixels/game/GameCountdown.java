package net.uniquepixels.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class GameCountdown {

  private int startCountdown = 60;
  private boolean running = false;

  public boolean isRunning() {
    return running;
  }

  public void setRunning(boolean running) {
    this.running = running;
  }

  public int getStartCountdown() {
    return startCountdown;
  }

  public void setStartCountdown(int startCountdown) {
    this.startCountdown = startCountdown;
  }

  public void runCountdown(Consumer<Player> onCountdown) {

    if (this.running)
      return;

    this.running = true;
    Bukkit.getScheduler().runTaskTimer(GameEngine.getProvidingPlugin(GameEngine.class), task -> {
      Bukkit.getOnlinePlayers().forEach(onCountdown::accept);
      this.setStartCountdown(this.getStartCountdown() - 1);
      if (this.getStartCountdown() == -1)
        task.cancel();
    }, 0, 20);
  }
}
