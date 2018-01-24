import java.util.*;
import processing.core.PImage;

final class Point
{
   private final int x;
   private final int y;

   private static final String QUAKE_ID = "quake";
   private static final int QUAKE_ACTION_PERIOD = 1100;
   private static final int QUAKE_ANIMATION_PERIOD = 100;
   private static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;

   public Point(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   public String toString()
   {
      return "(" + x + "," + y + ")";
   }

   public boolean equals(Object other)
   {
      return other instanceof Point &&
         ((Point)other).x == this.x &&
         ((Point)other).y == this.y;
   }

   public int hashCode()
   {
      int result = 17;
      result = result * 31 + x;
      result = result * 31 + y;
      return result;
   }

   //1
   public boolean adjacent(Point p2)
   {
      return (this.x == p2.x && Math.abs(this.y - p2.y) == 1) ||
         (this.y == p2.y && Math.abs(this.x - p2.x) == 1);
   }

   //2
   public int distanceSquared(Point p2)
   {
      int deltaX = this.x - p2.x;
      int deltaY = this.y - p2.y;

      return deltaX * deltaX + deltaY * deltaY;
   }

   //3
   public Quake createQuake(List<PImage> images)
   {
      return new Quake(QUAKE_ID, this, images, QUAKE_ACTION_PERIOD, QUAKE_ANIMATION_PERIOD);
   }

   //4
   public Vein createVein(String id, int actionPeriod,
      List<PImage> images)
   {
      return new Vein(id, this, images, actionPeriod);
   }

   //5
   public Blacksmith createBlacksmith(String id, List<PImage> images)
   {
      return new Blacksmith(id, this, images);
   }

   //6
   public MinerFull createMinerFull(String id, int resourceLimit, int actionPeriod, int animationPeriod,
      List<PImage> images)
   {
      return new MinerFull(id, this, images,
         resourceLimit, actionPeriod, animationPeriod);
   }

   //7
   public MinerNotFull createMinerNotFull(String id, int resourceLimit, int actionPeriod, int animationPeriod,
      List<PImage> images)
   {
      return new MinerNotFull(id, this, images, resourceLimit, actionPeriod, animationPeriod);
   }

   //8
   public Obstacle createObstacle(String id, List<PImage> images)
   {
      return new Obstacle(id, this, images);
   }

   //9
   public Ore createOre(String id, int actionPeriod,
      List<PImage> images)
   {
      return new Ore(id, this, images, actionPeriod);
   }

   //10
   public OreBlob createOreBlob(String id, int actionPeriod, int animationPeriod, List<PImage> images)
   {
      return new OreBlob(id, this, images, actionPeriod, animationPeriod);
   }


   //Getters
   public int getX() { return x; }
   public int getY() { return y; }


}
