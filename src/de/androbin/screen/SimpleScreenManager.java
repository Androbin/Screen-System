package de.androbin.screen;

import de.androbin.shell.*;
import de.androbin.shell.input.supply.*;
import java.util.*;
import java.util.function.*;

public abstract class SimpleScreenManager extends BasicShell implements ScreenManager {
  private final List<Shell> screens = new ArrayList<>();
  
  public SimpleScreenManager() {
    final Supplier<Shell> screen = this::current;
    
    keyInputs.add( KeyInputSupply.fromShell( screen ) );
    mouseInputs.add( MouseInputSupply.fromShell( screen ) );
    mouseMotionInputs.add( MouseMotionInputSupply.fromShell( screen ) );
    mouseWheelInputs.add( MouseWheelInputSupply.fromShell( screen ) );
  }
  
  @ Override
  public Shell call( final Shell screen ) {
    final Shell screen0 = current();
    
    if ( screen == null ) {
      return screen0;
    }
    
    if ( screen == screen0 ) {
      return screen;
    }
    
    if ( screen0 != null ) {
      screen0.onPaused();
    }
    
    doCall( screen );
    return screen0;
  }
  
  @ Override
  public Shell close() {
    final Shell screen0 = current();
    
    if ( screen0 == null ) {
      return screen0;
    }
    
    doClose();
    
    final Shell screen = current();
    
    if ( screen != null ) {
      screen.onResumed();
    }
    
    return screen0;
  }
  
  @ Override
  public final Shell current() {
    return screens.isEmpty() ? null : screens.get( screens.size() - 1 );
  }
  
  @ Override
  public void destroy() {
    while ( current() != null ) {
      close();
    }
  }
  
  private void doCall( final Shell screen ) {
    screen.onStarted();
    screen.onResumed();
    set( screen );
  }
  
  private void doClose() {
    final Shell screen = current();
    unset();
    screen.onPaused();
    screen.onStopped();
  }
  
  @ Override
  public final Shell previous() {
    return screens.size() < 2 ? null : screens.get( screens.size() - 2 );
  }
  
  protected final void set( final Shell screen ) {
    screens.add( screen );
  }
  
  @ Override
  public Shell switchTo( final Shell screen ) {
    if ( screen == null ) {
      return close();
    }
    
    final Shell screen0 = current();
    
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
  
  @ Override
  public void update( final float delta ) {
    final Shell screen = current();
    
    if ( screen != null ) {
      screen.update( delta );
    }
  }
}