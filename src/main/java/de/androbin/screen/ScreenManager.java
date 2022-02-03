package de.androbin.screen;

import de.androbin.shell.*;

public interface ScreenManager extends Shell {
  Shell call( Shell screen );
  
  Shell close();
  
  Shell current();
  
  Shell previous();
  
  Shell switchTo( Shell screen );
}