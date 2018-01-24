public abstract class AbstractAction implements Action
{
	private Entity entity;

	public AbstractAction(Entity entity)
	{
		this.entity = entity;
	}

	public Entity getEntity() { return entity; }

}