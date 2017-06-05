package de.androbin.game;

public interface ScreenManager {
  Screen callScreen( Screen screen );
  
  Screen closeScreen();
  
  Screen currentScreen();
  
  Screen previousScreen();
  
  Screen switchScreen( Screen screen );
}