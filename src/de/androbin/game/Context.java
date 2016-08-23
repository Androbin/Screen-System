package de.androbin.game;

public interface Context
{
	default void onPaused()
	{
	}
	
	default void onResumed()
	{
	}
	
	default void onStarted()
	{
	}
	
	default void onStopped()
	{
	}
}