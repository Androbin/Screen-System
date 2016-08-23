package de.androbin.game.listener;

import de.androbin.game.*;
import java.awt.*;
import java.awt.event.*;

public final class GameMouseMotionListener implements MouseMotionListener
{
	private final GameScreenManager	sm;
	private final Point				mouse	= new Point();
	
	public GameMouseMotionListener( final GameScreenManager sm )
	{
		this.sm = sm;
	}
	
	public Point getMousePosition()
	{
		return mouse;
	}
	
	@ Override
	public void mouseDragged( final MouseEvent event )
	{
		final Screen screen = sm.active();
		
		if ( screen == null )
		{
			return;
		}
		
		final MouseMotionListener listener = screen.getMouseMotionListener();
		
		if ( listener == null )
		{
			return;
		}
		
		listener.mouseDragged( event );
	}
	
	@ Override
	public void mouseMoved( final MouseEvent event )
	{
		final Screen screen = sm.active();
		
		if ( screen == null )
		{
			return;
		}
		
		final MouseMotionListener listener = screen.getMouseMotionListener();
		
		if ( listener == null )
		{
			return;
		}
		
		listener.mouseMoved( event );
	}
}