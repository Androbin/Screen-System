package de.androbin.screen.transit;

import de.androbin.shell.*;

public final class TransitionState<T extends Transition> {
  public Transition.Type type;
  public T transition;
  
  public Shell screen0;
  public Shell screen1;
  
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