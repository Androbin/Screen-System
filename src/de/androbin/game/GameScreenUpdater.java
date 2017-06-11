package de.androbin.game;

import static de.androbin.math.util.floats.FloatMathUtil.*;

public final class GameScreenUpdater {
  private final GameScreenManager gsm;
  private final TransitionState ts;
  
  public GameScreenUpdater( final GameScreenManager gsm, final TransitionState ts ) {
    this.gsm = gsm;
    this.ts = ts;
  }
  
  public void update( final float delta ) {
    if ( gsm.sm.currentScreen() != null ) {
      gsm.sm.currentScreen().update( delta );
    }
    
    if ( ts.crossfading ) {
      updateCrossfading( delta );
    }
  }
  
  private void updateCrossfading( final float delta ) {
    if ( ts.screen != null ) {
      ts.screen.update( delta );
    }
    
    ts.progress += delta * ts.speed;
    
    if ( !ts.crossed && ts.progress >= min( ts.transition.getCrossingTime(), 1f ) ) {
      updateCrossing();
    }
    
    updateTransition();
  }
  
  private void updateCrossing() {
    if ( gsm.sm.currentScreen() != null ) {
      gsm.sm.currentScreen().pause();
    }
    
    if ( ts.screen != null ) {
      switch ( ts.type ) {
        case CALL:
        case SWITCH:
          ts.screen.start();
        case CLOSE:
          ts.screen.resume();
          break;
      }
    }
    
    ts.crossed = true;
  }
  
  private void updateTransition() {
    if ( ts.progress >= 1f ) {
      ts.progress = 1f;
      
      switch ( ts.type ) {
        case CALL:
          gsm.call( ts.screen );
          break;
        case CLOSE:
          gsm.close();
          break;
        case SWITCH:
          gsm.switchTo( ts.screen );
          break;
      }
      
      ts.crossfading = false;
    }
  }
}