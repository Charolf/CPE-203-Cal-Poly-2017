public class Activity extends AbstractAction
{
	// private Entity entity;
	private WorldModel world;
	private ImageStore imageStore;

	public Activity(Entity entity, WorldModel world, ImageStore imageStore)
	{
		super(entity);
		this.world = world;
		this.imageStore = imageStore;
	}

   public static Activity createActivityAction(Entity entity, WorldModel world,
      ImageStore imageStore)
   {
      return new Activity(entity, world, imageStore);
   }

	public void executeAction(EventScheduler scheduler)
	{
		//RENAMED ALL EXECUTE FUNCTIONS TO THIS SO EASIER.
		((KineticEntity)this.getEntity()).executeActivity(world, imageStore, scheduler);
	}

	//public Entity getEntity() { return entity; }
	public WorldModel getWorld() { return world; }
	public ImageStore getImageStore() { return imageStore; }

}