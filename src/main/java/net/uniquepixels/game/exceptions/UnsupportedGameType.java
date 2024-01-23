package net.uniquepixels.game.exceptions;

import net.uniquepixels.game.config.GameType;

public class UnsupportedGameType extends Exception{
  public UnsupportedGameType(GameType gameType, GameType origin) {
    super("Unsupported game type! Game is " + gameType.name() + " but server accepts only " + origin.name());
  }
}
