package de.androbin.screen;

import de.androbin.screen.transit.*;
import de.androbin.shell.*;

public class SimpleSmoothScreenManager<T extends Transition> extends SimpleScreenManager
    implements SmoothScreenManager<T> {
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
    
    return super.close();
  }
  
  private boolean fade( final Transition.Type type, final T transition,
      final Shell screen, final Object value ) {
    if ( transit != null ) {
      return false;
    }
    
    final TransitionState<T> transit = new TransitionState<>();
    
    transit.type = type;
    transit.transition = transition;
    transit.value = value;
    
    transit.screen0 = current();
    transit.screen1 = screen;
    
    if ( transit.screen0 != null ) {
      transit.screen0.onBeforePaused();
    }
    
    if ( type.push ) {
      updateSize( screen );
      screen.onStarted();
    }
    
    this.transit = transit;
    return true;
  }
  
  @ Override
  public boolean fadeCall( final Shell screen, final T transition ) {
    if ( screen == null ) {
      return true;
    }
    
    return fade( Transition.Type.CALL, transition, screen, null );
  }
  
  @ Override
  public boolean fadeClose( final T transition ) {
    return fade( Transition.Type.CLOSE, transition, previous(), null );
  }
  
  @ Override
  public boolean fadeConfig( final Object value, final T transition ) {
    return fade( Transition.Type.CONFIG, transition, current(), value );
  }
  
  @ Override
  public boolean fadeSwitchTo( final Shell screen, final T transition ) {
    return fade( Transition.Type.SWITCH, transition, screen, null );
  }
  
  @ Override
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
    
    if ( transit.checkPaused() && screen0 != null ) {
      screen0.onPaused();
      
      if ( transit.type.pop ) {
        unset();
      }
    }
    
    if ( transit.checkResumed() && screen1 != null ) {
      if ( transit.value != null ) {
        screen1.onConfig( transit.value );
      }
      
      screen1.onResumed();
      
      if ( transit.type.push ) {
        set( screen1 );
      }
    }
    
    if ( transit.hasFinished() ) {
      if ( transit.type.pop && screen0 != null ) {
        screen0.onStopped();
      }
      
      if ( screen1 != null ) {
        screen1.onAfterResumed();
      }
      
      transit = null;
    }
  }
}