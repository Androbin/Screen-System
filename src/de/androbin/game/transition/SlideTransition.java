package de.androbin.game.transition;

import de.androbin.gfx.*;
import java.awt.*;

public final class SlideTransition extends Transition {
  private final int dx;
  private final int dy;
  
  public SlideTransition( final int dx, final int dy,
      final float crossing, final float duration ) {
    super( crossing, duration );
    
    this.dx = dx;
    this.dy = dy;
  }
  
  @ Override
  public void render( final Graphics2D g, final Dimension size,
      final Renderable state0, final Renderable state1 ) {
    final float time = getTime();
    final float progress;
    
    if ( time < crossing ) {
      progress = 0.5f * time / crossing;
    } else {
      progress = 0.5f + 0.5f * ( time - crossing ) / ( duration - crossing );
    }
    
    render( g, size, state0, progress );
    render( g, size, state1, progress - 1f );
  }
  
  private void render( final Graphics2D g, final Dimension size,
      final Renderable state, final float progress ) {
    if ( state == null ) {
      return;
    }
    
    final float x = dx * progress * size.width;
    final float y = dy * progress * size.height;
    
    final Graphics2D g2 = (Graphics2D) g.create();
    g2.translate( x, y );
    state.render( g2 );
    g2.dispose();
  }
}