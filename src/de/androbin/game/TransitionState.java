package de.androbin.game;

import de.androbin.gfx.transition.*;

public final class TransitionState {
  public Transition.Type type;
  public Screen screen;
  public Transition transition;
  
  public float speed;
  public float progress;
  
  public boolean crossed;
  public boolean crossfading;
}