package de.androbin.game;

import java.util.*;

public final class DefaultScreenManager implements ScreenManager {
  private final List<Screen> screens = new ArrayList<Screen>();
  
  @ Override
  public Screen callScreen( final Screen screen ) {
    final Screen screen_ = currentScreen();
    
    if ( screen == screen_ ) {
      return screen;
    }
    
    if ( screen_ != null ) {
      screen_.pause();
    }
    
    doCall( screen );
    return screen_;
  }
  
  @ Override
  public Screen closeScreen() {
    final Screen screen = currentScreen();
    
    if ( screen == null ) {
      return screen;
    }
    
    doClose( screen );
    
    if ( currentScreen() != null ) {
      currentScreen().resume();
    }
    
    return screen;
  }
  
  private Screen doCall( final Screen screen ) {
    screens.add( 0, screen );
    screen.start();
    screen.resume();
    return screen;
  }
  
  private Screen doClose( final Screen screen ) {
    screen.pause();
    screen.stop();
    screens.remove( 0 );
    return screen;
  }
  
  @ Override
  public Screen currentScreen() {
    return screens.isEmpty() ? null : screens.get( 0 );
  }
  
  @ Override
  public Screen previousScreen() {
    return screens.size() > 1 ? screens.get( 1 ) : null;
  }
  
  @ Override
  public Screen switchScreen( final Screen screen ) {
    if ( screen == null ) {
      return closeScreen();
    }
    
    final Screen screen_ = currentScreen();
    
    if ( screen == screen_ ) {
      return screen;
    }
    
    if ( screen_ != null ) {
      doClose( screen_ );
    }
    
    doCall( screen );
    return screen_;
  }
}