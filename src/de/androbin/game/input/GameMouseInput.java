package de.androbin.game.input;

import de.androbin.game.*;
import java.awt.event.*;
import java.util.function.*;

public final class GameMouseInput implements MouseListener {
  private final Supplier<Screen> screen;
  
  public GameMouseInput( final Supplier<Screen> screen ) {
    this.screen = screen;
  }
  
  @ Override
  public void mouseClicked( final MouseEvent event ) {
    final Screen screen = this.screen.get();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseListener listener = screen.inputs.mouse;
    
    if ( listener != null ) {
      listener.mouseClicked( event );
    }
  }
  
  @ Override
  public void mouseEntered( final MouseEvent event ) {
    final Screen screen = this.screen.get();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseListener listener = screen.inputs.mouse;
    
    if ( listener != null ) {
      listener.mouseEntered( event );
    }
  }
  
  @ Override
  public void mouseExited( final MouseEvent event ) {
    final Screen screen = this.screen.get();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseListener listener = screen.inputs.mouse;
    
    if ( listener != null ) {
      listener.mouseExited( event );
    }
  }
  
  @ Override
  public void mousePressed( final MouseEvent event ) {
    final Screen screen = this.screen.get();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseListener listener = screen.inputs.mouse;
    
    if ( listener != null ) {
      listener.mousePressed( event );
    }
  }
  
  @ Override
  public void mouseReleased( final MouseEvent event ) {
    final Screen screen = this.screen.get();
    
    if ( screen == null ) {
      return;
    }
    
    final MouseListener listener = screen.inputs.mouse;
    
    if ( listener != null ) {
      listener.mouseReleased( event );
    }
  }
}