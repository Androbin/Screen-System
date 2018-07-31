package de.androbin.screen.transit;

import de.androbin.shell.*;

public final class TransitionState<T extends Transition> {
  public Transition.Type type;
  public T transition;
  public Object value;
  
  public Shell screen0;
  public Shell screen1;
  
  private boolean paused;
  private boolean resumed;
  
  public boolean checkPaused() {
    if ( !paused && transition.getTime() >= transition.getPauseTime() ) {
      paused = true;
      return true;
    } else {
      return false;
    }
  }
  
  public boolean checkResumed() {
    if ( !resumed && transition.getTime() >= transition.getResumeTime() ) {
      resumed = true;
      return true;
    } else {
      return false;
    }
  }
  
  public boolean hasFinished() {
    return transition.getTime() >= transition.duration;
  }
}