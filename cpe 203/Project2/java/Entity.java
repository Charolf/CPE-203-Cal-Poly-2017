import java.util.List;
import processing.core.PImage;

public interface Entity
{
	Point getposition();
	void setposition(Point point);
	List<PImage> getimages();
	String getid();
	int getImageIndex();
}