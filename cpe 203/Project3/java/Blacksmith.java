import processing.core.PImage;
import java.util.List;

public class Blacksmith extends AbstractEntity
{
	// private String id;
	// private Point position;
	// private List<PImage> images;
	// private int imageIndex;
	// //Unsure on below variables:
	// private int resourceLimit;
	// private int resourceCount;
	// private int actionPeriod;
	// private int animationPeriod;

	public Blacksmith(String id, Point position, List<PImage> images)
	{
       //  this.id = id;
       //  this.position = position;
       //  this.images = images;
       //  this.imageIndex = 0;
       // 	//All zero for BlackSmith below:
       //  this.resourceLimit = 0;
      	// this.resourceCount = 0;
      	// this.actionPeriod = 0;
      	// this.animationPeriod = 0;
    	super(id, position, images, 0, 0);
	}

	// public Point getposition() { return position; }
	// public void setposition(Point point) { this.position = point; }
	// public List<PImage> getimages() { return images; }
	// public String getid() { return id; }
	// public int getImageIndex() { return imageIndex; }
}