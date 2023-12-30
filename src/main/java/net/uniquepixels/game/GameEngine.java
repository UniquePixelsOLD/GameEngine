package net.uniquepixels.game;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.uniquepixels.core.paper.gui.backend.UIHolder;
import net.uniquepixels.game.listeners.linking.DataLinker;
import net.uniquepixels.game.maintenance.GameCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

import java.util.ArrayList;
import java.util.List;

public class GameEngine extends JavaPlugin {

  private final List<Game> activeGames = new ArrayList<>();

  public List<Game> getActiveGames() {
    return activeGames;
  }

  @Override
  public void onEnable() {


    PluginManager pm = Bukkit.getPluginManager();

    // DataSync load

    DataLinker dataLinker = new DataLinker();
    pm.registerEvents(dataLinker, this);

    // Messaging Channels
    Messenger messenger = Bukkit.getServer().getMessenger();
    dataLinker.getMessageChannel().forEach((channel, pluginMessageListener) -> messenger.registerIncomingPluginChannel(this, channel, dataLinker));
    messenger.registerOutgoingPluginChannel(this, "uniquepixels:up-send-player-to-game");

    RegisteredServiceProvider<UIHolder> registration = this.getServer().getServicesManager().getRegistration(UIHolder.class);

    if (registration == null) {

      this.getServer().sendMessage(Component.text("Core Plugin hasn't been enabled before GameEngine start!").color(NamedTextColor.RED));

      Bukkit.getPluginManager().disablePlugin(this);

      return;
    }

    UIHolder uiHolder = registration.getProvider();


    getCommand("games").setExecutor(new GameCommand(uiHolder, this));


  }

  @Override
  public void onDisable() {


  }
}
