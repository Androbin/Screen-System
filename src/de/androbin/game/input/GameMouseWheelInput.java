package de.androbin.game.input;

import de.androbin.game.*;
import java.awt.event.*;
import java.util.function.*;

public final class GameMouseWheelInput implements MouseWheelListener {
  private final Supplier<Screen> screen;
  
  public GameMouseWheelInput( final Supplier<Screen> screen ) {
    this.screen = screen;
  }
  
  @ Override
  public void mouseWheelMoved( final MouseWheelEvent event ) {
    final Screen screen = this.screen.get();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseWheelListener listener = screen.inputs.mouseWheel;
    
    if ( listener != null ) {
      listener.mouseWheelMoved( event );
    }
  }
}