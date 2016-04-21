import java.awt.*;

/**
 * Created by bgenc on 19/04/16.
 */
public class HTTVoronoiPoint
{
	private float x;
	private float y;
	private HTTVoronoiSite owner;

	public HTTVoronoiPoint(float _x, float _y)
	{
		this.x = _x;
		this.y = _y;
		this.owner = null;
	}

	float getX()
	{
		return this.x;
	}

	float getY()
	{
		return this.y;
	}

	int getIntX()
	{
		return (int) (this.x);
	}

	int getIntY()
	{
		return (int) (this.y);
	}

	void setOwner(HTTVoronoiSite site)
	{
		this.owner = site;
	}

	float distance(HTTVoronoiPoint p)
	{
		return (float) Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
	}

	float effectOf(HTTVoronoiSite s)
	{
		return s.getWeight() / distance(s);
	}

	boolean testOwner(HTTVoronoiSite s)
	{
		if (this.owner == null)
			return true;
		else if (effectOf(s) > effectOf(owner))
			return true;
		else
			return false;
	}

	public void draw(Graphics2D g2)
	{
		int size = 4;
		g2.setColor(Color.black);
		g2.fillOval(this.getIntX() - size/2,
		            (int) (g2.getClipBounds().getHeight() - this.getIntY() - size/2),
		            size, size);
	}

	public String toString()
	{
		return "(P:" + this.x + "," + this.y + ")";
	}
}
