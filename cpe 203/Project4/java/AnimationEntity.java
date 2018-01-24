import processing.core.PImage;

public interface AnimationEntity extends Entity
{
	void nextImage();
	int getanimationPeriod();
}