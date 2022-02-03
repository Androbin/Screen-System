package de.androbin.screen;

import de.androbin.gfx.util.*;
import de.androbin.util.*;
import java.awt.*;
import java.util.*;
import javax.sound.sampled.*;

public final class SimpleResourceManager<K> implements ResourceManager<K> {
  private final Map<K, Image> images = new HashMap<>();
  private final Map<K, Clip> sounds = new HashMap<>();
  
  private final Set<Clip> activeSounds = new HashSet<>();
  
  @ Override
  public Image getImage( final K key ) {
    return images.get( key );
  }
  
  @ Override
  public Clip getSound( final K key ) {
    return sounds.get( key );
  }
  
  @ Override
  public void loadImage( final K key, final String path ) {
    images.put( key, ImageUtil.loadImage( path ) );
  }
  
  @ Override
  public void loadSound( final K key, final String path ) {
    sounds.put( key, SoundUtil.loadClip( path ) );
  }
  
  @ Override
  public void loopSound( final K key, final int loop ) {
    @ SuppressWarnings( "resource" )
    final Clip sound = getSound( key );
    
    if ( sound == null ) {
      return;
    }
    
    activeSounds.add( sound );
    sound.setFramePosition( 0 );
    sound.loop( loop );
  }
  
  @ Override
  public void playSound( final K key ) {
    @ SuppressWarnings( "resource" )
    final Clip sound = getSound( key );
    
    if ( sound == null ) {
      return;
    }
    
    activeSounds.add( sound );
    sound.setFramePosition( 0 );
    sound.start();
  }
  
  @ Override
  public void release() {
    sounds.values().forEach( Clip::close );
  }
  
  @ Override
  public void stopSound( final K key ) {
    @ SuppressWarnings( "resource" )
    final Clip sound = getSound( key );
    
    if ( sound == null ) {
      return;
    }
    
    sound.stop();
    activeSounds.remove( sound );
  }
  
  @ Override
  public void stopSounds() {
    activeSounds.forEach( Clip::stop );
    activeSounds.clear();
  }
}