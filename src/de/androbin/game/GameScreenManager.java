package de.androbin.game;

import de.androbin.gfx.*;
import de.androbin.gfx.transition.*;
import java.awt.*;
import java.awt.image.*;

public final class GameScreenManager implements Renderable {
  private final Game game;
  public final ScreenManager sm = new DefaultScreenManager();
  private final TransitionState ts = new TransitionState();
  public final GameScreenUpdater gsu = new GameScreenUpdater( this, ts );
  
  public GameScreenManager( final Game game ) {
    this.game = game;
  }
  
  public Screen call( final Screen screen ) {
    if ( ts.crossfading && ts.progress < 1f || screen == null ) {
      return active();
    }
    
    resize( screen );
    return sm.callScreen( screen );
  }
  
  public Screen close() {
    resize( sm.previousScreen() );
    final Screen screen = sm.closeScreen();
    
    if ( active() == null ) {
      game.stop();
    }
    
    return screen;
  }
  
  private void crossfade( final Transition.Type type, final Screen screen,
      final Transition crossfade, final float duration ) {
    if ( !ts.crossfading || ts.progress >= 1f ) {
      ts.crossfading = false;
      resize( screen );
      ts.type = type;
      ts.screen = screen;
      ts.transition = crossfade;
      ts.speed = 1f / duration;
      ts.progress = 0f;
      ts.crossed = false;
      ts.crossfading = true;
    }
  }
  
  public void crossfadeCall( final Screen screen, final Transition crossfade,
      final float duration ) {
    if ( screen != null ) {
      crossfade( Transition.Type.CALL, screen, crossfade, duration );
    }
  }
  
  public void crossfadeClose( final Transition crossfade, final float duration ) {
    crossfade( Transition.Type.CLOSE, sm.previousScreen(), crossfade, duration );
  }
  
  public void crossfadeSwitch( final Screen screen, final Transition crossfade,
      final float duration ) {
    if ( screen == null ) {
      crossfade( Transition.Type.CLOSE, null, crossfade, duration );
    } else {
      crossfade( Transition.Type.SWITCH, screen, crossfade, duration );
    }
  }
  
  public Screen active() {
    return ts.crossfading && ts.crossed ? ts.screen : sm.currentScreen();
  }
  
  @ Override
  public void render( final Graphics2D g ) {
    if ( ts.crossfading ) {
      final Screen screen1 = sm.currentScreen();
      final Screen screen2 = ts.screen;
      
      final BufferedImage image1 = new BufferedImage( game.getWidth(), game.getHeight(),
          BufferedImage.TYPE_INT_RGB );
      final BufferedImage image2 = new BufferedImage( game.getWidth(), game.getHeight(),
          BufferedImage.TYPE_INT_RGB );
      
      if ( screen1 != null ) {
        screen1.render( image1.createGraphics() );
      }
      
      if ( screen2 != null ) {
        screen2.render( image2.createGraphics() );
      }
      
      ts.transition.render( g, image1, image2, ts.progress );
    } else {
      if ( active() != null ) {
        active().render( g );
      }
    }
  }
  
  public void resize( final Screen screen ) {
    if ( screen != null ) {
      screen.updateSize();
    }
  }
  
  public Screen switchTo( final Screen screen ) {
    if ( ts.crossfading && ts.progress < 1f ) {
      return active();
    }
    
    if ( screen == null ) {
      return close();
    }
    
    resize( screen );
    return sm.switchScreen( screen );
  }
}