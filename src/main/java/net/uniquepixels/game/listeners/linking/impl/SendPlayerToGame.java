package net.uniquepixels.game.listeners.linking.impl;

import com.google.common.io.ByteArrayDataInput;
import net.uniquepixels.core.paper.games.GameTypes;
import net.uniquepixels.game.Game;
import net.uniquepixels.game.GameEngine;
import net.uniquepixels.game.listeners.linking.DataLinker;
import net.uniquepixels.game.listeners.linking.PluginMessageListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class SendPlayerToGame implements PluginMessageListener {

  private final DataLinker dataLinker;
  private final GameEngine gameEngine = JavaPlugin.getPlugin(GameEngine.class);

  public SendPlayerToGame(DataLinker dataLinker) {
    this.dataLinker = dataLinker;
  }

  @Override
  public void onPluginMessage(String channel, @NotNull Player player, ByteArrayDataInput data) {

    String uid = data.readUTF();
    String game = data.readUTF();

    GameTypes type = GameTypes.valueOf(game);

    Optional<Game> optional = this.gameEngine.getActiveGames().stream().filter(game1 -> game1.getType() == type).filter(game1 -> game1.getCurrentPlayers() < game1.getMaxPlayers()).findFirst();

    if (optional.isEmpty())
      return;

    this.dataLinker.getAwaitingPlayerLinks().put(UUID.fromString(uid), GameTypes.valueOf(game.toUpperCase()));


  }
}
