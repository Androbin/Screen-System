package de.androbin.game.listener;

import de.androbin.game.*;
import java.awt.event.*;

public final class GameMouseListener implements MouseListener {
  private final GameScreenManager sm;
  
  public GameMouseListener( final GameScreenManager sm ) {
    this.sm = sm;
  }
  
  @ Override
  public void mouseClicked( final MouseEvent event ) {
    final Screen screen = sm.active();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseListener listener = screen.getMouseListener();
    
    if ( listener == null ) {
      return;
    }
    
    listener.mouseClicked( event );
  }
  
  @ Override
  public void mouseEntered( final MouseEvent event ) {
    final Screen screen = sm.active();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseListener listener = screen.getMouseListener();
    
    if ( listener == null ) {
      return;
    }
    
    listener.mouseEntered( event );
  }
  
  @ Override
  public void mouseExited( final MouseEvent event ) {
    final Screen screen = sm.active();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseListener listener = screen.getMouseListener();
    
    if ( listener == null ) {
      return;
    }
    
    listener.mouseExited( event );
  }
  
  @ Override
  public void mousePressed( final MouseEvent event ) {
    final Screen screen = sm.active();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseListener listener = screen.getMouseListener();
    
    if ( listener == null ) {
      return;
    }
    
    listener.mousePressed( event );
  }
  
  @ Override
  public void mouseReleased( final MouseEvent event ) {
    final Screen screen = sm.active();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseListener listener = screen.getMouseListener();
    
    if ( listener == null ) {
      return;
    }
    
    listener.mouseReleased( event );
  }
}