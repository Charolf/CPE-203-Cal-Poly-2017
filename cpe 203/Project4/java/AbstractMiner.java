import processing.core.PImage;
import java.util.List;

public abstract class AbstractMiner extends AbstractKinetic
{
	public AbstractMiner(String id, Point position,
							List<PImage> images, int resourceLimit, 
							int resourceCount, int actionPeriod, 
							int animationPeriod, int repeatCount) 
	{
		super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod,0);
	}

   	public void transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
   	{
      	Entity miner = this.getposition().createMinerNotFull(this.getid(), this.getresourceLimit(),
         	this.getactionPeriod(), this.getanimationPeriod(),
         	this.getimages());

      	world.removeEntity(this);
      	scheduler.unscheduleAllEvents(this);

      	world.addEntity(miner);
      	((KineticEntity)miner).scheduleActions(scheduler, world, imageStore);
   	}
}