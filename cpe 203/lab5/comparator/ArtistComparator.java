import java.util.Comparator;

public class ArtistComparator implements Comparator<Song>
{
	public int compare(Song s1, Song s2)
	{
		int result = s1.getArtist().compareTo(s2.getArtist());
		if (result != 0)
			return result; 
		return 0;
	}
}