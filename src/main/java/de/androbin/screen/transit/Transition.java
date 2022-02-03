package de.androbin.screen.transit;

public abstract class Transition {
  private float time;
  public final float duration;
  
  public Transition( final float duration ) {
    this.duration = duration;
  }
  
  public abstract float getPauseTime();
  
  public abstract float getResumeTime();
  
  public final float getTime() {
    return time;
  }
  
  public final void update( final float delta ) {
    time = Math.min( time + delta, duration );
  }
  
  public enum Type {
    CALL( false, true ),
    CLOSE( true, false ),
    SWITCH( true, true ),
    CONFIG( false, false );
    
    public final boolean pop;
    public final boolean push;
    
    private Type( final boolean pop, final boolean push ) {
      this.pop = pop;
      this.push = push;
    }
  }
}