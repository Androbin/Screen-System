package de.androbin.game;

import java.awt.*;
import javax.sound.sampled.*;

public interface ResourceManager<K> {
  Image getImage( K key );
  
  Clip getSound( K key );
  
  void loadImage( K key, String path );
  
  void loadSound( K key, String path );
  
  void loopSound( K key, int loop );
  
  void playSound( K key );
  
  void stopSound( K key );
  
  void stopSounds();
}