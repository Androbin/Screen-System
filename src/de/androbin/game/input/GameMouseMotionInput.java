package de.androbin.game.input;

import de.androbin.game.*;
import java.awt.event.*;
import java.util.function.*;

public final class GameMouseMotionInput implements MouseMotionListener {
  private final Supplier<Screen> screen;
  
  public GameMouseMotionInput( final Supplier<Screen> screen ) {
    this.screen = screen;
  }
  
  @ Override
  public void mouseDragged( final MouseEvent event ) {
    final Screen screen = this.screen.get();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseMotionListener listener = screen.inputs.mouseMotion;
    
    if ( listener != null ) {
      listener.mouseDragged( event );
    }
  }
  
  @ Override
  public void mouseMoved( final MouseEvent event ) {
    final Screen screen = this.screen.get();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseMotionListener listener = screen.inputs.mouseMotion;
    
    if ( listener != null ) {
      listener.mouseMoved( event );
    }
  }
}