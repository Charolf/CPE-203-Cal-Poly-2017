import java.util.List;
import java.util.Optional;

public interface Position extends Entity
{
	Optional<Entity> findnearest(WorldModel world, Point point, Entity entity);
    Optional<Entity> nearestEntity(List<Entity> entities, Point pos);
    Point nextPosition(WorldModel world, Point destPos);
    boolean moveToFull(WorldModel world, Entity target, EventScheduler scheduler);
}