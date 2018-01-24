import java.util.Optional;
import java.util.LinkedList;
import processing.core.PImage;
import java.util.List;

public abstract class AbstractKinetic extends AbstractAnimation implements Position
{
	public AbstractKinetic(String id, Point position,
							List<PImage> images, int resourceLimit, 
							int resourceCount, int actionPeriod, 
              int animationPeriod, int repeatCount) 
	{ 
		super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod, repeatCount);
	}

   	// public Point nextPosition(WorldModel world, Point destPos)
   	// {
    //   	int horiz = Integer.signum(destPos.getX() - this.getposition().getX());
    //   	Point newPos = new Point(this.getposition().getX() + horiz,
    //      	this.getposition().getY());

    //   	if (horiz == 0 || world.isOccupied(newPos))
    //   	{
    //      	int vert = Integer.signum(destPos.getY() - this.getposition().getY());
    //      	newPos = new Point(this.getposition().getX(),
    //         	this.getposition().getY() + vert);

    //      	if (vert == 0 || world.isOccupied(newPos))
    //      	{
    //         	newPos = this.getposition();
    //      	}
    //   	}

    //   	return newPos;
   	// }

    public Point nextPosition(WorldModel world, Point destPos)
    {
        int horiz = Integer.signum(destPos.getX() - this.getposition().getX());
        Point newPos = new Point(this.getposition().getX() + horiz,
          this.getposition().getY());

        // Optional<Entity> occupant = world.getOccupant(newPos);

        if (horiz == 0 || OccupyChecker(world, newPos))
        {
          int vert = Integer.signum(destPos.getY() - this.getposition().getY());
          newPos = new Point(this.getposition().getX(),
              this.getposition().getY() + vert);

          if (vert == 0 || OccupyChecker(world, newPos))
          {
              newPos = this.getposition();
          }
        }

        return newPos;
    }

   	public Optional<Entity> findnearest(WorldModel world, Point pos, Entity e)
   	{
      	List<Entity> ofType = new LinkedList<>();
      	for (Entity entity : world.getentities())
      	{
         	//if (entity instanceof Blacksmith)
         	if (instanceChecker(entity))
         	{
            	ofType.add(entity);
         	}
      	}

      	return nearestEntity(ofType, pos);
   	}

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
    
    public boolean moveToFull(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (this.getposition().adjacent(target.getposition()))
        {
        	posFunc(world, target, scheduler);
          return true;
        }
        else
        {
            Point nextPos = nextPosition(world, target.getposition());

            if (!getposition().equals(nextPos))
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

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> Target = this.findnearest(world, this.getposition(), this);
        long nextPeriod = this.getactionPeriod();

        if (ConditionActivity(world,imageStore,scheduler,Target))
        {
          Block1(world, imageStore,scheduler,Target, nextPeriod);
        }
        else
        {
          Block2(world,imageStore,scheduler);
        }
        Block3(world,imageStore,scheduler);
    }


    protected abstract boolean instanceChecker(Entity entity);
    protected abstract void posFunc(WorldModel world, Entity target, EventScheduler scheduler);
    protected abstract boolean OccupyChecker(WorldModel world, Point destPos);

    protected abstract boolean ConditionActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler, Optional<Entity> target);
    protected abstract void Block1(WorldModel world, ImageStore imageStore, EventScheduler scheduler, Optional<Entity> target, long nextPeriod);
    protected abstract void Block2(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
    protected abstract void Block3(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
}