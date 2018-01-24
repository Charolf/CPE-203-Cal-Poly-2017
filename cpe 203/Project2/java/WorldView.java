import processing.core.PApplet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import processing.core.PImage;

final class WorldView
{
   private PApplet screen;
   private WorldModel world;
   private int tileWidth;
   private int tileHeight;
   private Viewport viewport;

   public WorldView(int numRows, int numCols, PApplet screen, WorldModel world,
      int tileWidth, int tileHeight)
   {
      this.screen = screen;
      this.world = world;
      this.tileWidth = tileWidth;
      this.tileHeight = tileHeight;
      this.viewport = new Viewport(numRows, numCols);
   }

   //1
   public void shiftView(int colDelta, int rowDelta)
   {
      int newCol = clamp(this.viewport.getcol() + colDelta, 0,
         this.world.getnumCols() - this.viewport.getnumCols());
      int newRow = clamp(this.viewport.getrow() + rowDelta, 0,
         this.world.getnumRows() - this.viewport.getnumRows());

      this.viewport.shift(newCol, newRow);
   }

   //2
   public void drawBackground()
   {
      for (int row = 0; row < this.viewport.getnumRows(); row++)
      {
         for (int col = 0; col < this.viewport.getnumCols(); col++)
         {
            Point worldPoint = this.viewport.viewportToWorld(col, row);
            Optional<PImage> image = this.world.getBackgroundImage(worldPoint);
            if (image.isPresent())
            {
               this.screen.image(image.get(), col * this.tileWidth,
                  row * this.tileHeight);
            }
         }
      }
   }

   //3
   public void drawEntities()
   {
      for (Entity entity : this.world.getentities())
      {
         Point pos = entity.getposition();

         if (this.viewport.contains(pos))
         {
            Point viewPoint = this.viewport.worldToViewport(pos.getX(), pos.getY());
            this.screen.image(Functions.getCurrentImage(entity),
               viewPoint.getX() * this.tileWidth, viewPoint.getY() * this.tileHeight);
         }
      }
   }

   //4
   public void drawViewport()
   {
      this.drawBackground();
      this.drawEntities();
   }

   //5
   public int clamp(int value, int low, int high)
   {
      return Math.min(high, Math.max(value, low));
   }


}
