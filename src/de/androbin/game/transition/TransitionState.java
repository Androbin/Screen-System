package de.androbin.game.transition;

import de.androbin.game.*;

public final class TransitionState {
  public Transition.Type type;
  public Transition transition;
  
  public Screen screen0;
  public Screen screen1;
  
  private boolean crossed;
  
  public boolean checkCrossed() {
    if ( !crossed && transition.getTime() >= transition.crossing ) {
      crossed = true;
      return true;
    } else {
      return false;
    }
  }
  
  public boolean hasFinished() {
    return transition.getTime() >= transition.duration;
  }
}