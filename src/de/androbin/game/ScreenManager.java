package de.androbin.game;

public interface ScreenManager {
  Screen call( Screen screen );
  
  Screen close();
  
  Screen current();
  
  Screen previous();
  
  Screen switchTo( Screen screen );
}