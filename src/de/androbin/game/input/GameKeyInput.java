package de.androbin.game.input;

import de.androbin.game.*;
import java.awt.event.*;
import java.util.function.*;

public final class GameKeyInput implements KeyListener {
  private final Supplier<Screen> screen;
  
  public GameKeyInput( final Supplier<Screen> screen ) {
    this.screen = screen;
  }
  
  @ Override
  public void keyPressed( final KeyEvent event ) {
    final Screen screen = this.screen.get();
    
    if ( screen == null ) {
      return;
    }
    
    final KeyListener listener = screen.inputs.keyboard;
    
    if ( listener != null ) {
      listener.keyPressed( event );
    }
  }
  
  @ Override
  public void keyReleased( final KeyEvent event ) {
    final Screen screen = this.screen.get();
    
    if ( screen == null ) {
      return;
    }
    
    final KeyListener listener = screen.inputs.keyboard;
    
    if ( listener != null ) {
      listener.keyReleased( event );
    }
  }
  
  @ Override
  public void keyTyped( final KeyEvent event ) {
    final Screen screen = this.screen.get();
    
    if ( screen == null ) {
      return;
    }
    
    final KeyListener listener = screen.inputs.keyboard;
    
    if ( listener != null ) {
      listener.keyTyped( event );
    }
  }
}