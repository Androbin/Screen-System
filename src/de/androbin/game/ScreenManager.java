package de.androbin.game;

public interface ScreenManager {
  Screen callScreen( final Screen screen );
  
  Screen closeScreen();
  
  Screen currentScreen();
  
  Screen previousScreen();
  
  Screen switchScreen( final Screen screen );
}