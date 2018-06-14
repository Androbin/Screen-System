package de.androbin.screen;

import de.androbin.screen.transit.*;
import de.androbin.shell.*;

public class SmoothScreenManager<T extends Transition> extends SimpleScreenManager {
  protected TransitionState<T> transit;
  
  @ Override
  public Shell call( final Shell screen ) {
    if ( screen != null ) {
      updateSize( screen );
    }
    
    return super.call( screen );
  }
  
  @ Override
  public Shell close() {
    final Shell screen0 = previous();
    
    if ( screen0 != null ) {
      updateSize( screen0 );
    }
    
    final Shell screen = super.close();
    
    if ( current() == null ) {
      destroy();
    }
    
    return screen;
  }
  
  private boolean fade( final Transition.Type type, final T crossfade,
      final Shell screen ) {
    if ( transit != null ) {
      return false;
    }
    
    final TransitionState<T> transit = new TransitionState<>();
    
    transit.type = type;
    transit.transition = crossfade;
    
    if ( screen != null ) {
      updateSize( screen );
      screen.onStarted();
    }
    
    transit.screen0 = current();
    transit.screen1 = screen;
    
    this.transit = transit;
    return true;
  }
  
  public boolean fadeCall( final Shell screen, final T crossfade ) {
    if ( screen == null ) {
      return true;
    }
    
    return fade( Transition.Type.CALL, crossfade, screen );
  }
  
  public boolean fadeClose( final T crossfade ) {
    return fade( Transition.Type.CLOSE, crossfade, previous() );
  }
  
  public boolean fadeSwitchTo( final Shell screen, final T crossfade ) {
    return fade( Transition.Type.SWITCH, crossfade, screen );
  }
  
  public boolean isFading() {
    return transit != null;
  }
  
  @ Override
  protected void onResized( final int width, final int height ) {
    if ( transit == null ) {
      final Shell screen = current();
      
      if ( screen != null ) {
        updateSize( screen );
      }
    } else {
      final Shell screen0 = transit.screen0;
      
      if ( screen0 != null ) {
        updateSize( screen0 );
      }
      
      final Shell screen1 = transit.screen1;
      
      if ( screen1 != null ) {
        updateSize( screen1 );
      }
    }
  }
  
  public final void updateSize( final Shell screen ) {
    screen.setSize( getWidth(), getHeight() );
  }
  
  @ Override
  public Shell switchTo( final Shell screen ) {
    if ( screen != null ) {
      updateSize( screen );
    }
    
    return super.switchTo( screen );
  }
  
  @ Override
  public void update( final float delta ) {
    super.update( delta );
    
    if ( transit != null ) {
      updateTransition( delta );
    }
  }
  
  private void updateTransition( final float delta ) {
    transit.transition.update( delta );
    
    final Shell screen0 = transit.screen0;
    final Shell screen1 = transit.screen1;
    
    if ( transit.checkCrossed() ) {
      if ( screen0 != null ) {
        screen0.onPaused();
        
        switch ( transit.type ) {
          case CLOSE:
          case SWITCH:
            unset();
          case CALL:
        }
      }
      
      if ( screen1 != null ) {
        screen1.onResumed();
        
        switch ( transit.type ) {
          case CALL:
          case SWITCH:
            set( screen1 );
          case CLOSE:
        }
      }
    }
    
    if ( transit.hasFinished() ) {
      if ( screen0 == null ) {
        return;
      }
      
      switch ( transit.type ) {
        case CLOSE:
        case SWITCH:
          screen0.onStopped();
        case CALL:
      }
      
      transit = null;
    }
  }
}