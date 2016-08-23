package de.androbin.game.listener;

import de.androbin.game.*;
import java.awt.event.*;

public final class GameKeyListener implements KeyListener
{
	private final GameScreenManager sm;
	
	public GameKeyListener( final GameScreenManager sm )
	{
		this.sm = sm;
	}
	
	@ Override
	public void keyPressed( final KeyEvent event )
	{
		final Screen screen = sm.active();
		
		if ( screen == null )
		{
			return;
		}
		
		final KeyListener listener = screen.getKeyListener();
		
		if ( listener == null )
		{
			return;
		}
		
		listener.keyPressed( event );
	}
	
	@ Override
	public void keyReleased( final KeyEvent event )
	{
		final Screen screen = sm.active();
		
		if ( screen == null )
		{
			return;
		}
		
		final KeyListener listener = screen.getKeyListener();
		
		if ( listener == null )
		{
			return;
		}
		
		listener.keyReleased( event );
	}
	
	@ Override
	public void keyTyped( final KeyEvent event )
	{
		final Screen screen = sm.active();
		
		if ( screen == null )
		{
			return;
		}
		
		final KeyListener listener = screen.getKeyListener();
		
		if ( listener == null )
		{
			return;
		}
		
		listener.keyTyped( event );
	}
}