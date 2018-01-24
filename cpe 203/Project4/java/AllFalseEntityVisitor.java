public class AllFalseEntityVisitor implements EntityVisitor<Boolean>
{
	public Boolean visit(BlackSmith BlackSmith) { return false; }
	public Boolean visit(Ore Ore) { return false; }
	public Boolean visit(Vein Vein) { return false; }
	public Boolean visit(MinerFull MinerFull) { return false; }
	public Boolean visit(Obstacle Obstacle) { return false; }
	public Boolean visit(Quake Quake) { return false; }
	public Boolean visit(OreBlob OreBlob) { return false; }
}