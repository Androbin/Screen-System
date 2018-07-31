package de.androbin.screen;

import de.androbin.screen.transit.*;
import de.androbin.shell.*;

public interface SmoothScreenManager<T extends Transition> extends ScreenManager {
  boolean fadeCall( Shell screen, T transition );
  
  boolean fadeClose( T transition );
  
  boolean fadeConfig( Object value, T transition );
  
  boolean fadeSwitchTo( Shell screen, T transition );
  
  boolean isFading();
}