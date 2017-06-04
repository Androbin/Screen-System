package de.androbin.game.listener;

import java.awt.event.*;

public final class TeeKeyListener implements KeyListener {
  private final KeyListener[] listeners;
  
  public TeeKeyListener( final KeyListener ... listeners ) {
    this.listeners = listeners;
  }
  
  @ Override
  public void keyPressed( final KeyEvent event ) {
    for ( final KeyListener listener : listeners ) {
      listener.keyPressed( event );
    }
  }
  
  @ Override
  public void keyReleased( final KeyEvent event ) {
    for ( final KeyListener listener : listeners ) {
      listener.keyReleased( event );
    }
  }
  
  @ Override
  public void keyTyped( final KeyEvent event ) {
    for ( final KeyListener listener : listeners ) {
      listener.keyTyped( event );
    }
  }
}