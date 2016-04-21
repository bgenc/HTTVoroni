import java.awt.*;

/**
 * Created by bgenc on 19/04/16.
 */
public class HTTVoronoiTree
{
	private HTTVoronoiTriangle root;
	public static final int DEPTH_THRESHOLD = 2;

	public HTTVoronoiTree(HTTVoronoiTriangle tri)
	{
		this.root = tri;
	}

	public HTTVoronoiTriangle getRoot()
	{
		return this.root;
	}

	public HTTVoronoiTriangle getTriangle(float x, float y)
	{
		return this.root.getTriangle(x, y);
	}

	public boolean insert(HTTVoronoiSite s)
	{
		return this.root.insert(s);
	}

	public void draw(Graphics2D g)
	{
		this.root.draw(g);
	}
}
