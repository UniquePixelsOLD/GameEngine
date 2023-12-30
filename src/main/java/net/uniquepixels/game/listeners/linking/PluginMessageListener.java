package net.uniquepixels.game.listeners.linking;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface PluginMessageListener {

  void onPluginMessage(String channel, @NotNull Player player, ByteArrayDataInput data);

}
