   interface EntityVisitor<R>
   {
      R visit(BlackSmith BlackSmith);
      R visit(Ore Ore);
      R visit(Vein Vein);
      R visit(MinerFull MinerFull);
      R visit(MinerNotFull MinerNotFull);
      R visit(Obstacle Obstacle);
      R visit(Quake Quake);
      R visit(OreBlob OreBlob);
   }