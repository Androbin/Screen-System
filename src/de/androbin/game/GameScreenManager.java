package de.androbin.game;

import de.androbin.game.transition.*;
import de.androbin.gfx.*;
import java.awt.*;
import java.util.function.*;

public final class GameScreenManager extends SimpleScreenManager implements Renderable {
  private TransitionState transit;
  
  private final Supplier<Dimension> sizing;
  private final Runnable exit;
  
  public GameScreenManager( final Supplier<Dimension> sizing, final Runnable exit ) {
    this.sizing = sizing;
    this.exit = exit;
  }
  
  @ Override
  public Screen call( final Screen screen ) {
    if ( screen != null ) {
      screen.updateSize();
    }
    
    return super.call( screen );
  }
  
  @ Override
  public Screen close() {
    final Screen screen0 = previous();
    
    if ( screen0 != null ) {
      screen0.updateSize();
    }
    
    final Screen screen = super.close();
    
    if ( current() == null ) {
      exit.run();
    }
    
    return screen;
  }
  
  private boolean crossfade( final Transition.Type type, final Transition crossfade,
      final Screen screen ) {
    if ( transit != null ) {
      return false;
    }
    
    final TransitionState transit = new TransitionState();
    
    transit.type = type;
    transit.transition = crossfade;
    
    if ( screen != null ) {
      screen.updateSize();
      screen.start();
    }
    
    transit.screen0 = current();
    transit.screen1 = screen;
    
    this.transit = transit;
    return true;
  }
  
  public boolean crossfadeCall( final Screen screen, final Transition crossfade ) {
    if ( screen == null ) {
      return true;
    } else {
      return crossfade( Transition.Type.CALL, crossfade, screen );
    }
  }
  
  public boolean crossfadeClose( final Transition crossfade ) {
    return crossfade( Transition.Type.CLOSE, crossfade, previous() );
  }
  
  public boolean crossfadeSwitch( final Screen screen, final Transition crossfade ) {
    if ( screen == null ) {
      return crossfade( Transition.Type.CLOSE, crossfade, null );
    } else {
      return crossfade( Transition.Type.SWITCH, crossfade, screen );
    }
  }
  
  private void doTransition() {
    final Screen screen0 = transit.screen0;
    
    if ( screen0 != null ) {
      screen0.pause();
      
      switch ( transit.type ) {
        case CLOSE:
        case SWITCH:
          unset();
        case CALL:
      }
    }
    
    final Screen screen1 = transit.screen1;
    
    if ( screen1 != null ) {
      screen1.resume();
      
      switch ( transit.type ) {
        case CALL:
        case SWITCH:
          set( screen1 );
        case CLOSE:
      }
    }
  }
  
  private void finishTransition() {
    final Screen screen0 = transit.screen0;
    
    if ( screen0 == null ) {
      return;
    }
    
    switch ( transit.type ) {
      case CLOSE:
      case SWITCH:
        screen0.stop();
      case CALL:
    }
  }
  
  @ Override
  public void render( final Graphics2D g ) {
    if ( transit == null ) {
      final Screen screen = current();
      
      if ( screen != null ) {
        screen.render( g );
      }
    } else {
      final Screen screen0 = transit.screen0;
      final Screen screen1 = transit.screen1;
      
      final Dimension size = sizing.get();
      
      transit.transition.render( g, size, screen0, screen1 );
    }
  }
  
  public void resize() {
    if ( transit == null ) {
      final Screen screen = current();
      
      if ( screen != null ) {
        screen.updateSize();
      }
    } else {
      final Screen screen0 = transit.screen0;
      
      if ( screen0 != null ) {
        screen0.updateSize();
      }
      
      final Screen screen1 = transit.screen1;
      
      if ( screen1 != null ) {
        screen1.updateSize();
      }
    }
  }
  
  @ Override
  public Screen switchTo( final Screen screen ) {
    if ( screen != null ) {
      screen.updateSize();
    }
    
    return super.switchTo( screen );
  }
  
  public void update( final float delta ) {
    if ( transit == null ) {
      final Screen screen = current();
      
      if ( screen != null ) {
        screen.update( delta );
      }
    } else {
      final Screen screen0 = transit.screen0;
      
      if ( screen0 != null ) {
        screen0.update( delta );
      }
      
      final Screen screen1 = transit.screen1;
      
      if ( screen1 != null ) {
        screen1.update( delta );
      }
      
      transit.transition.update( delta );
      
      if ( transit.checkCrossed() ) {
        doTransition();
      }
      
      if ( transit.hasFinished() ) {
        finishTransition();
        transit = null;
      }
    }
  }
}