package de.androbin.game.transition;

import de.androbin.gfx.*;
import java.awt.*;

public final class ColorCrossfade extends Transition {
  private final float red;
  private final float green;
  private final float blue;
  
  public ColorCrossfade( final Color color, final float crossing, final float duration ) {
    this( color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f,
        crossing, duration );
  }
  
  public ColorCrossfade( final float red, final float green, final float blue,
      final float crossing, final float duration ) {
    super( crossing, duration );
    
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
  
  @ Override
  public void render( final Graphics2D g, final Dimension size,
      final Renderable state0, final Renderable state1 ) {
    final float time = getTime();
    final float alpha;
    
    if ( time < crossing ) {
      render( g, state0 );
      alpha = time / crossing;
    } else {
      render( g, state1 );
      alpha = ( duration - time ) / ( duration - crossing );
    }
    
    g.setColor( new Color( red, green, blue, alpha ) );
    g.fillRect( 0, 0, size.width, size.height );
  }
  
  private void render( final Graphics2D g, final Renderable state ) {
    if ( state == null ) {
      return;
    }
    
    final Graphics2D g2 = (Graphics2D) g.create();
    state.render( g2 );
    g2.dispose();
  }
}