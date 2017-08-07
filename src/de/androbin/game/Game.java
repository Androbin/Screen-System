package de.androbin.game;

import de.androbin.game.input.*;
import de.androbin.gfx.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.*;

public final class Game extends CustomPane {
  public final GameScreenManager screens;
  public final Inputs inputs;
  
  public Game() {
    screens = new GameScreenManager( this::getSize, this::stop );
    inputs = new Inputs();
    
    final Supplier<Screen> screen = screens::current;
    
    addKeyListener( inputs.keyboard = new GameKeyInput( screen ) );
    addMouseListener( inputs.mouse = new GameMouseInput( screen ) );
    addMouseMotionListener( inputs.mouseMotion = new GameMouseMotionInput( screen ) );
    addMouseWheelListener( inputs.mouseWheel = new GameMouseWheelInput( screen ) );
    addComponentListener( new ComponentAdapter() {
      @ Override
      public void componentResized( final ComponentEvent event ) {
        screens.resize();
      }
    } );
  }
  
  public Game( final int fps ) {
    this();
    setFPS( fps );
  }
  
  @ Override
  protected void destroy() {
    while ( screens.current() != null ) {
      screens.close();
    }
  }
  
  @ Override
  public void render( final Graphics2D g ) {
    screens.render( g );
  }
  
  public void stop() {
    running = false;
  }
  
  @ Override
  protected void update( final float delta ) {
    screens.update( delta );
  }
}