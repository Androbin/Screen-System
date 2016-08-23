package de.androbin.game;

import java.awt.*;
import javax.sound.sampled.*;

public interface ResourceManager<K>
{
	Image getImage( final K key );
	
	Clip getSound( final K key );
	
	void loadImage( final K key, final String path );
	
	void loadSound( final K key, final String path );
	
	void loopSound( final K key, final int loop );
	
	void playSound( final K key );
	
	void stopSound( final K key );
	
	void stopSounds();
}