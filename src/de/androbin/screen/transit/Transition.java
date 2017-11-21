package de.androbin.screen.transit;

public class Transition {
  private float time;
  public final float crossing;
  public final float duration;
  
  public Transition( final float crossing, final float duration ) {
    this.crossing = crossing;
    this.duration = duration;
  }
  
  public final float getTime() {
    return time;
  }
  
  public final void update( final float delta ) {
    time = Math.min( time + delta, duration );
  }
  
  public enum Type {
    CALL,
    CLOSE,
    SWITCH;
  }
}