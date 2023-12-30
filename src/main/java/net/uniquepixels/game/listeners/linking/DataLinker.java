package net.uniquepixels.game.listeners.linking;

import com.google.common.io.ByteStreams;
import net.uniquepixels.core.paper.games.GameTypes;
import net.uniquepixels.game.listeners.linking.impl.SendPlayerToGame;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class DataLinker implements org.bukkit.plugin.messaging.PluginMessageListener, Listener {

  private final HashMap<UUID, GameTypes> awaitingPlayerLinks = new HashMap<>();
  private final HashMap<String, PluginMessageListener> messageChannel = new HashMap<>();

  public DataLinker() {
    this.messageChannel.put("uniquepixels:up-send-player-to-game", new SendPlayerToGame(this));
  }

  public HashMap<String, PluginMessageListener> getMessageChannel() {
    return messageChannel;
  }

  public HashMap<UUID, GameTypes> getAwaitingPlayerLinks() {
    return awaitingPlayerLinks;
  }

  @Override
  public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, byte @NotNull [] bytes) {

    if (this.messageChannel.containsKey(channel))
      return;

    PluginMessageListener pluginMessageListener = this.messageChannel.get(channel);

    pluginMessageListener.onPluginMessage(channel, player, ByteStreams.newDataInput(bytes));

  }
}
