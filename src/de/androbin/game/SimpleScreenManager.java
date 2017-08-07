package de.androbin.game;

import java.util.*;

public class SimpleScreenManager implements ScreenManager {
  private final List<Screen> screens = new ArrayList<>();
  
  @ Override
  public Screen call( final Screen screen ) {
    final Screen screen0 = current();
    
    if ( screen == null ) {
      return screen0;
    }
    
    if ( screen == screen0 ) {
      return screen;
    }
    
    if ( screen0 != null ) {
      screen0.pause();
    }
    
    doCall( screen );
    return screen0;
  }
  
  @ Override
  public Screen close() {
    final Screen screen0 = current();
    
    if ( screen0 == null ) {
      return screen0;
    }
    
    doClose();
    
    final Screen screen = current();
    
    if ( screen != null ) {
      screen.resume();
    }
    
    return screen0;
  }
  
  @ Override
  public final Screen current() {
    return screens.isEmpty() ? null : screens.get( screens.size() - 1 );
  }
  
  private void doCall( final Screen screen ) {
    screen.start();
    screen.resume();
    set( screen );
  }
  
  private void doClose() {
    final Screen screen = current();
    unset();
    screen.pause();
    screen.stop();
  }
  
  @ Override
  public final Screen previous() {
    return screens.size() < 2 ? null : screens.get( screens.size() - 2 );
  }
  
  protected final void set( final Screen screen ) {
    screens.add( screen );
  }
  
  @ Override
  public Screen switchTo( final Screen screen ) {
    if ( screen == null ) {
      return close();
    }
    
    final Screen screen0 = current();
    
    if ( screen == screen0 ) {
      return screen;
    }
    
    if ( screen0 != null ) {
      doClose();
    }
    
    doCall( screen );
    return screen0;
  }
  
  protected final void unset() {
    screens.remove( screens.size() - 1 );
  }
}