package net.uniquepixels.game.maintenance.ui;

import net.kyori.adventure.text.Component;
import net.uniquepixels.core.paper.gui.UIRow;
import net.uniquepixels.core.paper.gui.UISlot;
import net.uniquepixels.core.paper.gui.exception.OutOfInventoryException;
import net.uniquepixels.core.paper.gui.item.UIItem;
import net.uniquepixels.core.paper.gui.types.chest.ChestUI;
import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import net.uniquepixels.game.Game;
import net.uniquepixels.game.GameEngine;
import org.bukkit.entity.Player;

public class GamesUI extends ChestUI {

  private final GameEngine engine;

  public GamesUI(Player opener, GameEngine engine) {
    super(Component.text("Active Games"), UIRow.CHEST_ROW_1, opener);
    this.engine = engine;
  }

  @Override
  protected void initItems(Player player) throws OutOfInventoryException {

    for (int i = 0; i < 7; i++) {

      try {

        Game game = this.engine.getActiveGames().get(i);

        UIItem item = new UIItem(
          new DefaultItemStackBuilder<>(game.getUiItem())
            .displayName(Component.text(game.getGameKey().getKey()))
            .buildItem(),
          UISlot.fromSlotId(i).orElse(UISlot.SLOT_0));

        item(item, (clicker, uiItem, clickType, inventoryClickEvent) -> {

          clicker.sendMessage(Component.text("Clicked"));

          return true;
        });

      } catch (IndexOutOfBoundsException e) {
        e.fillInStackTrace();
        break;
      }

    }

  }

  @Override
  public boolean allowItemMovementInOtherInventories() {
    return false;
  }
}
