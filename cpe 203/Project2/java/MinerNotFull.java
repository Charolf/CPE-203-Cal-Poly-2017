import java.util.LinkedList;
import java.util.List;
import processing.core.PImage;
import java.util.Optional;

public class MinerNotFull implements AnimationEntity, KineticEntity, Position
{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int resourceLimit;
    private int resourceCount;
    private int actionPeriod;
    private int animationPeriod;

    public MinerNotFull(String id, Point position, List<PImage> images, int resourceLimit, int actionPeriod, int animationPeriod) 
    {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.resourceLimit = 4;
        this.resourceCount = resourceLimit;
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent((Entity)this,
           Activity.createActivityAction((Entity)this, world, imageStore),
           this.actionPeriod);
        scheduler.scheduleEvent((Entity)this, Animation.createAnimationAction(this, 0), this.animationPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
   	{
      	Optional<Entity> notFullTarget = this.findnearest(world, this.position, this);

      	if (!notFullTarget.isPresent() ||
         	!this.moveToFull(world, notFullTarget.get(), scheduler) ||
         	!this.transformNotFull(world, scheduler, imageStore))
      	{
         	scheduler.scheduleEvent(this,
            	Activity.createActivityAction(this, world, imageStore),
            	this.actionPeriod);
      	}
   	}

   //5
   	public Optional<Entity> findnearest(WorldModel world, Point pos, Entity e)
   	{
      	List<Entity> ofType = new LinkedList<>();
      	for (Entity entity : world.getentities())
      	{
         	if (entity instanceof Ore)
         	{
            	ofType.add(entity);
         	}
      	}

      	return nearestEntity(ofType, pos);
   	}

   	//15
   	public Optional<Entity> nearestEntity(List<Entity> entities, Point pos)
   	{
      	if (entities.isEmpty())
      	{
         	return Optional.empty();
      	}
      	else
      	{
         	Entity nearest = entities.get(0);
         	int nearestDistance = nearest.getposition().distanceSquared(pos);

         	for (Entity other : entities)
         	{
            	int otherDistance = other.getposition().distanceSquared(pos);

            	if (otherDistance < nearestDistance)
            	{
               	nearest = other;
               	nearestDistance = otherDistance;
            	}
         	}

         	return Optional.of(nearest);
      	}
   	}

    //THIS IS MOVETONOTFULL
    public boolean moveToFull(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (this.position.adjacent(target.getposition()))
        {
            resourceCount += 1;
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);

            return true;
        }
        else
        {
            Point nextPos = nextPosition(world, target.getposition());

            if (!position.equals(nextPos))
            {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity((Entity) this, nextPos);
            }
            return false;
        }
      }


    //13
    public Point nextPosition(WorldModel world, Point destPos)
    {
        int horiz = Integer.signum(destPos.getX() - this.position.getX());
        Point newPos = new Point(this.position.getX() + horiz,
          this.position.getY());

        if (horiz == 0 || world.isOccupied(newPos))
        {
          int vert = Integer.signum(destPos.getY() - this.position.getY());
          newPos = new Point(this.position.getX(),
              this.position.getY() + vert);

          if (vert == 0 || world.isOccupied(newPos))
          {
              newPos = this.position;
          }
        }

        return newPos;
    }


   //8
   public boolean transformNotFull(WorldModel world,
      EventScheduler scheduler, ImageStore imageStore)
   {
      if (this.resourceCount >= this.resourceLimit)
      {
         MinerFull miner = this.position.createMinerFull(this.id, this.resourceLimit,
            this.actionPeriod, this.animationPeriod,
            this.images);


         world.removeEntity((Entity)this);
         scheduler.unscheduleAllEvents((Entity)this);

         world.addEntity((Entity)miner);
         miner.scheduleActions(scheduler, world, imageStore);

         return true;
      }

      return false;
   }

    public int getactionPeriod() { return actionPeriod; }
    public int getanimationPeriod() { return animationPeriod; }
    public int getResourceLimit() { return resourceLimit; }
    public int getResourceCount() { return resourceCount; }
    public void nextImage() { imageIndex = (imageIndex + 1) % images.size(); }

	public Point getposition() { return position; }
	public void setposition(Point point) { this.position = point; }
	public List<PImage> getimages() { return images; }
	public String getid() { return id; }
	public int getImageIndex() { return imageIndex; }
}

