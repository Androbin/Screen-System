package de.androbin.game;

import de.androbin.game.listener.*;
import de.androbin.gfx.*;

public abstract class Screen implements Context, Renderable {
  protected final Game game;
  public final Inputs inputs;
  
  private int width;
  private int height;
  
  private boolean started;
  private boolean active;
  
  public Screen( final Game game ) {
    this.game = game;
    this.inputs = new Inputs();
  }
  
  public final int getHeight() {
    return height;
  }
  
  public final int getWidth() {
    return width;
  }
  
  protected abstract void onResized( int width, int height );
  
  protected final void pause() {
    if ( started && active ) {
      active = false;
      onPaused();
    }
  }
  
  protected final void resume() {
    if ( started && !active ) {
      active = true;
      onResumed();
    }
  }
  
  protected final void start() {
    if ( !started ) {
      started = true;
      onStarted();
    }
  }
  
  protected final void stop() {
    if ( started ) {
      started = false;
      onStopped();
    }
  }
  
  protected abstract void update( float delta );
  
  protected final void updateSize() {
    final int width = game.getWidth();
    final int height = game.getHeight();
    
    if ( width != this.width || height != this.height ) {
      this.width = width;
      this.height = height;
      onResized( width, height );
    }
  }
}