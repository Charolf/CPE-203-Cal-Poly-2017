public interface KineticEntity extends Entity
{
	void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);
	int getactionPeriod();
	void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
}