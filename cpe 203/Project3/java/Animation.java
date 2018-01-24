public class Animation extends AbstractAction
{
	// private Entity entity;
	private int repeatCount;

	public Animation(Entity entity, int repeatCount)
	{
		super(entity);
		this.repeatCount = repeatCount;
	}

   	public static Animation createAnimationAction(Entity entity, int repeatCount)
   	{
      	return new Animation(entity, repeatCount);
   	}

	public void executeAction(EventScheduler scheduler)
	{
    	((AnimationEntity)this.getEntity()).nextImage();

      	if (this.repeatCount != 1)
      	{
         	scheduler.scheduleEvent(this.getEntity(),
            	createAnimationAction(this.getEntity(),
               	Math.max(this.repeatCount - 1, 0)),
            	((AnimationEntity)this.getEntity()).getanimationPeriod());
      	}
	}

	// public Entity getEntity() { return entity; }
	public int getRepeatCount() { return repeatCount; }


}