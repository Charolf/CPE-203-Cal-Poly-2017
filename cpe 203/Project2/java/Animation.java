public class Animation implements Action
{
	private Entity entity;
	private int repeatCount;

	public Animation(Entity entity, int repeatCount)
	{
		this.entity = entity;
		this.repeatCount = repeatCount;
	}

   	public static Animation createAnimationAction(Entity entity, int repeatCount)
   	{
      	return new Animation(entity, repeatCount);
   	}

	public void executeAction(EventScheduler scheduler)
	{
    	((AnimationEntity)this.entity).nextImage();

      	if (this.repeatCount != 1)
      	{
         	scheduler.scheduleEvent(this.entity,
            	createAnimationAction(this.entity,
               	Math.max(this.repeatCount - 1, 0)),
            	((AnimationEntity)this.entity).getanimationPeriod());
      	}
	}

	public Entity getEntity() { return entity; }
	public int getRepeatCount() { return repeatCount; }


}