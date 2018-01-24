import processing.core.PImage;
import java.util.List;

public abstract class AbstractActivity extends AbstractEntity implements KineticEntity
{
	private int actionPeriod;

	public AbstractActivity(String id, Point position,
							List<PImage> images, int resourceLimit, 
							int resourceCount, int actionPeriod) 
	{ 
		super(id, position, images, resourceLimit, resourceCount);
		this.actionPeriod = actionPeriod;
	}

	public int getactionPeriod() { return actionPeriod; }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
         scheduler.scheduleEvent(this,
            Activity.createActivityAction(this, world, imageStore),
            this.getactionPeriod());	
	}
}