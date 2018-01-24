import java.util.LinkedList;
import java.util.List;
import processing.core.PImage;
import java.util.Optional;

public class OreBlob extends AbstractKinetic
{	
    // private String id;
    // private Point position;
    // private List<PImage> images;
    // private int imageIndex;

    // //NOT USED:
    // private int resourceLimit;
    // private int resourceCount;

    // private int actionPeriod;
    // private int animationPeriod;	

    public OreBlob(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) 
    {
        // this.id = id;
        // this.position = position;
        // this.images = images;
        // this.imageIndex = 0;

        // //NOT USED:
        // this.resourceLimit = 0;
        // this.resourceCount = 0;

        // this.actionPeriod = actionPeriod;
        // this.animationPeriod = animationPeriod;
      super(id, position, images, 0, 0, actionPeriod, animationPeriod, 0);
    }

    // public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    // {
    //     scheduler.scheduleEvent(this,
    //        Activity.createActivityAction(this, world, imageStore),
    //        this.actionPeriod);
    //     scheduler.scheduleEvent(this,
    //        Animation.createAnimationAction(this, 0), this.animationPeriod);
    // }


    // public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    // {
    //   	Optional<Entity> blobTarget = this.findnearest(world, this.getposition(), this);
    //   	long nextPeriod = this.getactionPeriod();

    //   	if (ConditionActivity(world,imageStore,scheduler,Target))
    //   	{
    //       Block1(world,imageStore,scheduler,Target,nextPeriod);
    //   	}

    //   	scheduler.scheduleEvent(this,
    //      	Activity.createActivityAction(this, world, imageStore),
    //      	nextPeriod);
    // }

    public boolean ConditionActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler, Optional<Entity> target)
    {
      return (target.isPresent());
    }

    public void Block1(WorldModel world, ImageStore imageStore, EventScheduler scheduler, Optional<Entity> target, long nextPeriod)
    {
      Point tgtPos = target.get().getposition();

      if(this.moveToFull(world, target.get(), scheduler))
      {
        Entity quake = tgtPos.createQuake(imageStore.getImageList(Quake.QUAKE_KEY));

        world.addEntity(quake);
        nextPeriod += this.getactionPeriod();
        ((KineticEntity)quake).scheduleActions(scheduler, world, imageStore);
      }
    }

    public void Block2(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {}
    
    public void Block3(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
      scheduler.scheduleEvent(this,
        Activity.createActivityAction(this, world, imageStore),
        this.getactionPeriod());
    }

   // //5
   // 	public Optional<Entity> findnearest(WorldModel world, Point pos, Entity e)
   // 	{
   //    	List<Entity> ofType = new LinkedList<>();
   //    	for (Entity entity : world.getentities())
   //    	{
   //       	if (entity instanceof Vein)
   //       	{
   //          	ofType.add(entity);
   //       	}
   //    	}

   //    	return nearestEntity(ofType, pos);
   // 	}

    public Boolean instanceChecker(Entity entity) { entity.accept(new OreBlobVisitor()); }

   	//15
   	// public Optional<Entity> nearestEntity(List<Entity> entities, Point pos)
   	// {
    //   	if (entities.isEmpty())
    //   	{
    //      	return Optional.empty();
    //   	}
    //   	else
    //   	{
    //      	Entity nearest = entities.get(0);
    //      	int nearestDistance = nearest.getposition().distanceSquared(pos);

    //      	for (Entity other : entities)
    //      	{
    //         	int otherDistance = other.getposition().distanceSquared(pos);

    //         	if (otherDistance < nearestDistance)
    //         	{
    //            	nearest = other;
    //            	nearestDistance = otherDistance;
    //         	}
    //      	}

    //      	return Optional.of(nearest);
    //   	}
   	// }

   //12 moveToOreBlob Originally.
   // public boolean moveToFull(WorldModel world,
   //    Entity target, EventScheduler scheduler)
   // {
   //    if (this.position.adjacent(target.getposition()))
   //    {
   //       world.removeEntity(target);
   //       scheduler.unscheduleAllEvents(target);
   //       return true;
   //    }
   //    else
   //    {
   //       Point nextPos = this.nextPosition(world, target.getposition());

   //       if (!this.position.equals(nextPos))
   //       {
   //          Optional<Entity> occupant = world.getOccupant(nextPos);
   //          if (occupant.isPresent())
   //          {
   //             scheduler.unscheduleAllEvents(occupant.get());
   //          }

   //          world.moveEntity(this, nextPos);
   //       }
   //       return false;
   //    }
   // }

    //FOR MOVETOFULL ABSTRACT
    public void posFunc(WorldModel world, Entity target, EventScheduler scheduler) 
    { 
      world.removeEntity(target);
      scheduler.unscheduleAllEvents(target);
    }

   	//14
   	// public Point nextPosition(WorldModel world, Point destPos)
   	// {
    //   	int horiz = Integer.signum(destPos.getX() - this.getposition().getX());
    //   	Point newPos = new Point(this.getposition().getX() + horiz,
    //      	this.getposition().getY());

    //   	Optional<Entity> occupant = world.getOccupant(newPos);

    //   	if (horiz == 0 ||
    //      	(occupant.isPresent() && !(occupant.get() instanceof Ore)))
    //   	{
    //      	int vert = Integer.signum(destPos.getY() - this.getposition().getY());
    //      	newPos = new Point(this.getposition().getX(), this.getposition().getY() + vert);
    //      	occupant = world.getOccupant(newPos);

    //      	if (vert == 0 ||
    //         	(occupant.isPresent() && !(occupant.get() instanceof Ore)))
    //      	{
    //         	newPos = this.getposition();
    //      	}
    //   	}

    //   	return newPos;
   	// }

    public boolean OccupyChecker(WorldModel world, Point destPos)
    {
      return (world.isOccupied(destPos) && !(world.getOccupant(destPos).get() instanceof Ore));
    }

    // public int getactionPeriod() { return actionPeriod; }
    // public int getanimationPeriod() { return animationPeriod; }
    // public void nextImage() { imageIndex = (imageIndex + 1) % images.size(); }

	// public Point getposition() { return position; }
	// public void setposition(Point point) { this.position = point; }
	// public List<PImage> getimages() { return images; }
	// public String getid() { return id; }
	// public int getImageIndex() { return imageIndex; }

  public <R> R accept(EntityVisitor<R> visitor)
    {
      return visitor.visit(this);
    }

}

