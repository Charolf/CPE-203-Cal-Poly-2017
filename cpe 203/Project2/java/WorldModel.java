import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import processing.core.PImage;
import processing.core.PApplet;

final class WorldModel
{
   private int numRows;
   private int numCols;
   private Background background[][];
   private Entity occupancy[][];
   private Set<Entity> entities;

   private static final int ORE_REACH = 1;

   private static final int PROPERTY_KEY = 0;

   private static final String BGND_KEY = "background";
   private static final int BGND_NUM_PROPERTIES = 4;
   private static final int BGND_ID = 1;
   private static final int BGND_COL = 2;
   private static final int BGND_ROW = 3;

   private static final String MINER_KEY = "miner";
   private static final int MINER_NUM_PROPERTIES = 7;
   private static final int MINER_ID = 1;
   private static final int MINER_COL = 2;
   private static final int MINER_ROW = 3;
   private static final int MINER_LIMIT = 4;
   private static final int MINER_ACTION_PERIOD = 5;
   private static final int MINER_ANIMATION_PERIOD = 6;

   private static final String OBSTACLE_KEY = "obstacle";
   private static final int OBSTACLE_NUM_PROPERTIES = 4;
   private static final int OBSTACLE_ID = 1;
   private static final int OBSTACLE_COL = 2;
   private static final int OBSTACLE_ROW = 3;

   private static final String ORE_KEY = "ore";
   private static final int ORE_NUM_PROPERTIES = 5;
   private static final int ORE_ID = 1;
   private static final int ORE_COL = 2;
   private static final int ORE_ROW = 3;
   private static final int ORE_ACTION_PERIOD = 4;

   private static final String VEIN_KEY = "vein";
   private static final int VEIN_NUM_PROPERTIES = 5;
   private static final int VEIN_ID = 1;
   private static final int VEIN_COL = 2;
   private static final int VEIN_ROW = 3;
   private static final int VEIN_ACTION_PERIOD = 4;

   private static final String SMITH_KEY = "blacksmith";
   private static final int SMITH_NUM_PROPERTIES = 4;
   private static final int SMITH_ID = 1;
   private static final int SMITH_COL = 2;
   private static final int SMITH_ROW = 3;


   public WorldModel(int numRows, int numCols, Background defaultBackground)
   {
      this.numRows = numRows;
      this.numCols = numCols;
      this.background = new Background[numRows][numCols];
      this.occupancy = new Entity[numRows][numCols];
      this.entities = new HashSet<>();

      for (int row = 0; row < numRows; row++)
      {
         Arrays.fill(this.background[row], defaultBackground);
      }
   }

   //1
   public Optional<Point> findOpenAround(Point pos)
   {
      for (int dy = -ORE_REACH; dy <= ORE_REACH; dy++)
      {
         for (int dx = -ORE_REACH; dx <= ORE_REACH; dx++)
         {
            Point newPt = new Point(pos.getX() + dx, pos.getY() + dy);
            if (this.withinBounds(newPt) &&
               !this.isOccupied(newPt))
            {
               return Optional.of(newPt);
            }
         }
      }

      return Optional.empty();
   }

   //2
   public void tryAddEntity(Entity entity)
   {
      if (this.isOccupied(entity.getposition()))
      {
         // arguably the wrong type of exception, but we are not
         // defining our own exceptions yet
         throw new IllegalArgumentException("position occupied");
      }

      this.addEntity(entity);
   }

   //3
   public boolean withinBounds(Point pos)
   {
      return pos.getY() >= 0 && pos.getY() < this.numRows &&
         pos.getX() >= 0 && pos.getX() < this.numCols;
   }

   //4
   public boolean isOccupied(Point pos)
   {
      return this.withinBounds(pos) &&
         this.getOccupancyCell(pos) != null;
   }

   // //5
   // public Optional<Entity> findNearest(Point pos,
   //    Entity entity)
   // {
   //    List<Entity> ofType = new LinkedList<>();
   //    for (Entity entity : this.entities)
   //    {
   //       if (entity instanceof BlackSmith)
   //       {
   //          ofType.add(entity);
   //       }
   //    }

   //    return Entity.nearestEntity(ofType, pos);
   // }

   /*
      Assumes that there is no entity currently occupying the
      intended destination cell.
   */

   //6
   public void addEntity(Entity entity)
   {
      if (this.withinBounds(entity.getposition()))
      {
         this.setOccupancyCell(entity.getposition(), entity);
         this.entities.add(entity);
      }
   }

   //7
   public void moveEntity(Entity entity, Point pos)
   {
      Point oldPos = entity.getposition();
      if (this.withinBounds(pos) && !pos.equals(oldPos))
      {
         this.setOccupancyCell(oldPos, null);
         this.removeEntityAt(pos);
         this.setOccupancyCell(pos, entity);
         //entity.getposition() = pos;
         entity.setposition(pos);
      }
   }

   //8
   public void removeEntity(Entity entity)
   {
      this.removeEntityAt(entity.getposition());
   }

   //9
   public void removeEntityAt(Point pos)
   {
      if (this.withinBounds(pos)
         && this.getOccupancyCell(pos) != null)
      {
         Entity entity = this.getOccupancyCell(pos);

         /* this moves the entity just outside of the grid for
            debugging purposes */
         //entity.getposition() = new Point(-1, -1);
         entity.setposition(new Point(-1, -1));
         this.entities.remove(entity);
         this.setOccupancyCell(pos, null);
      }
   }

   //10
   public Optional<PImage> getBackgroundImage(Point pos)
   {
      if (this.withinBounds(pos))
      {
         return Optional.of(Functions.getCurrentImage(this.getBackgroundCell(pos)));
      }
      else
      {
         return Optional.empty();
      }
   }

   //11
   public void setBackground(Point pos, Background background)
   {
      if (this.withinBounds(pos))
      {
         this.setBackgroundCell(pos, background);
      }
   }

   //12
   public Optional<Entity> getOccupant(Point pos)
   {
      if (this.isOccupied(pos))
      {
         return Optional.of(this.getOccupancyCell(pos));
      }
      else
      {
         return Optional.empty();
      }
   }

   //13
   public Entity getOccupancyCell(Point pos)
   {
      return this.occupancy[pos.getY()][pos.getX()];
   }

   //14
   public void setOccupancyCell(Point pos,
      Entity entity)
   {
      this.occupancy[pos.getY()][pos.getX()] = entity;
   }

   //15
   public Background getBackgroundCell(Point pos)
   {
      return this.background[pos.getY()][pos.getX()];
   }

   //16
   public void setBackgroundCell(Point pos, Background background)
   {
      this.background[pos.getY()][pos.getX()] = background;
   }

   //17
   public void load(Scanner in, ImageStore imageStore)
   {
      int lineNumber = 0;
      while (in.hasNextLine())
      {
         try
         {
            if (!processLine(in.nextLine(), imageStore))
            {
               System.err.println(String.format("invalid entry on line %d",
                  lineNumber));
            }
         }
         catch (NumberFormatException e)
         {
            System.err.println(String.format("invalid entry on line %d",
               lineNumber));
         }
         catch (IllegalArgumentException e)
         {
            System.err.println(String.format("issue on line %d: %s",
               lineNumber, e.getMessage()));
         }
         lineNumber++;
      }
   }

   //18
   public boolean processLine(String line, ImageStore imageStore)
   {
      String[] properties = line.split("\\s");
      if (properties.length > 0)
      {
         switch (properties[PROPERTY_KEY])
         {
         case BGND_KEY:
            return this.parseBackground(properties, imageStore);
         case MINER_KEY:
            return this.parseMiner(properties, imageStore);
         case OBSTACLE_KEY:
            return this. parseObstacle(properties, imageStore);
         case ORE_KEY:
            return this.parseOre(properties, imageStore);
         case SMITH_KEY:
            return this.parseSmith(properties, imageStore);
         case VEIN_KEY:
            return this.parseVein(properties, imageStore);
         }
      }

      return false;
   }

   //19
   public boolean parseBackground(String [] properties,
      ImageStore imageStore)
   {
      if (properties.length == BGND_NUM_PROPERTIES)
      {
         Point pt = new Point(Integer.parseInt(properties[BGND_COL]),
            Integer.parseInt(properties[BGND_ROW]));
         String id = properties[BGND_ID];
         setBackground(pt,
            new Background(id, imageStore.getImageList(id)));
      }

      return properties.length == BGND_NUM_PROPERTIES;
   }

   //20
   public boolean parseMiner(String [] properties, ImageStore imageStore)
   {
      if (properties.length == MINER_NUM_PROPERTIES)
      {
         Point pt = new Point(Integer.parseInt(properties[MINER_COL]),
            Integer.parseInt(properties[MINER_ROW]));
         Entity entity = pt.createMinerNotFull(properties[MINER_ID],
            Integer.parseInt(properties[MINER_LIMIT]),
            Integer.parseInt(properties[MINER_ACTION_PERIOD]),
            Integer.parseInt(properties[MINER_ANIMATION_PERIOD]),
            imageStore.getImageList(MINER_KEY));
         this.tryAddEntity(entity);
      }

      return properties.length == MINER_NUM_PROPERTIES;
   }

   //21
   public boolean parseObstacle(String [] properties, ImageStore imageStore)
   {
      if (properties.length == OBSTACLE_NUM_PROPERTIES)
      {
         Point pt = new Point(
            Integer.parseInt(properties[OBSTACLE_COL]),
            Integer.parseInt(properties[OBSTACLE_ROW]));
         Entity entity = pt.createObstacle(properties[OBSTACLE_ID],
         imageStore.getImageList(OBSTACLE_KEY));
         this.tryAddEntity(entity);
      }

      return properties.length == OBSTACLE_NUM_PROPERTIES;
   }

   //22
   public boolean parseOre(String [] properties, ImageStore imageStore)
   {
      if (properties.length == ORE_NUM_PROPERTIES)
      {
         Point pt = new Point(Integer.parseInt(properties[ORE_COL]),
            Integer.parseInt(properties[ORE_ROW]));
         Entity entity = pt.createOre(properties[ORE_ID],
            Integer.parseInt(properties[ORE_ACTION_PERIOD]),
            imageStore.getImageList(ORE_KEY));
         this.tryAddEntity(entity);
      }

      return properties.length == ORE_NUM_PROPERTIES;
   }

   //23
   public boolean parseSmith(String [] properties, ImageStore imageStore)
   {
      if (properties.length == SMITH_NUM_PROPERTIES)
      {
         Point pt = new Point(Integer.parseInt(properties[SMITH_COL]),
            Integer.parseInt(properties[SMITH_ROW]));
         Entity entity = pt.createBlacksmith(properties[SMITH_ID],
         imageStore.getImageList(SMITH_KEY));
         this.tryAddEntity(entity);
      }

      return properties.length == SMITH_NUM_PROPERTIES;
   }

   //24
   public boolean parseVein(String [] properties, ImageStore imageStore)
   {
      if (properties.length == VEIN_NUM_PROPERTIES)
      {
         Point pt = new Point(Integer.parseInt(properties[VEIN_COL]),
            Integer.parseInt(properties[VEIN_ROW]));
         Entity entity = pt.createVein(properties[VEIN_ID],
            Integer.parseInt(properties[VEIN_ACTION_PERIOD]),
            imageStore.getImageList(VEIN_KEY));
         this.tryAddEntity(entity);
      }

      return properties.length == VEIN_NUM_PROPERTIES;
   }

   //Getters
   public int getnumCols() { return numCols; }
   public int getnumRows() { return numRows; }
   public Background[][] getBackground() { return background; }
   public Entity[][] getoccupancy() { return occupancy; }
   public Set<Entity> getentities() { return entities;}

}
