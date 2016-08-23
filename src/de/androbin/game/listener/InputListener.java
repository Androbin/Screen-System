package de.androbin.game.listener;

import java.awt.event.*;

public interface InputListener
{
	default KeyListener getKeyListener()
	{
		return null;
	}
	
	default MouseListener getMouseListener()
	{
		return null;
	}
	
	default MouseMotionListener getMouseMotionListener()
	{
		return null;
	}
	
	default MouseWheelListener getMouseWheelListener()
	{
		return null;
	}
}