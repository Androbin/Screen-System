package de.androbin.game.listener;

import de.androbin.game.*;
import java.awt.event.*;

public final class GameMouseWheelListener implements MouseWheelListener {
  private final GameScreenManager sm;
  
  public GameMouseWheelListener( final GameScreenManager sm ) {
    this.sm = sm;
  }
  
  @ Override
  public void mouseWheelMoved( final MouseWheelEvent event ) {
    final Screen screen = sm.active();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseWheelListener listener = screen.inputs.mouseWheel;
    
    if ( listener == null ) {
      return;
    }
    
    listener.mouseWheelMoved( event );
  }
}