package de.androbin.game;

import de.androbin.game.listener.*;
import de.androbin.gfx.*;
import java.awt.*;
import java.awt.event.*;

public final class Game extends CustomPane
{
	public final GameScreenManager			gsm;
	public final GameMouseMotionListener	mml;
	
	public Game()
	{
		this( 20L );
	}
	
	public Game( final long delay )
	{
		this.delayMilli = delay;
		
		gsm = new GameScreenManager( this );
		
		addKeyListener( new GameKeyListener( gsm ) );
		addMouseListener( new GameMouseListener( gsm ) );
		addMouseMotionListener( mml = new GameMouseMotionListener( gsm ) );
		addMouseWheelListener( new GameMouseWheelListener( gsm ) );
		addComponentListener( new ComponentAdapter()
		{
			@ Override
			public void componentResized( final ComponentEvent event )
			{
				gsm.resize( gsm.active() );
			}
		} );
	}
	
	@ Override
	protected void destroy()
	{
		while ( gsm.sm.currentScreen() != null )
		{
			gsm.sm.closeScreen();
		}
	}
	
	@ Override
	public Point getMousePosition()
	{
		return mml.getMousePosition();
	}
	
	@ Override
	public void render( final Graphics2D g )
	{
		gsm.render( g );
	}
	
	public void start()
	{
		if ( gsm.active() != null )
		{
			gsm.active().start();
		}
		
		start( "2D Game Engine" );
	}
	
	public void stop()
	{
		running = false;
	}
	
	@ Override
	protected void update( final float delta )
	{
		gsm.gsu.update( delta );
	}
}
