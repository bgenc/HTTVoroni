import java.awt.*;

/**
 * Created by bgenc on 19/04/16.
 */
public class HTTVoronoiPoint
{
	private float x;
	private float y;
	private String label;
	private HTTVoronoiSite owner;

	public HTTVoronoiPoint(String _label, float _x, float _y)
	{
		this.label = _label;
		this.x = _x;
		this.y = _y;
		this.owner = null;
	}

	String getLabel()
	{
		return this.label;
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

	HTTVoronoiSite getOwner()
	{
		return this.owner;
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

	boolean canBeOwnedBy(HTTVoronoiSite s)
	{
		if (this.owner == null)
			return true;
		else if (s == this.owner)
			return true;
		else if (effectOf(s) > effectOf(owner))
			return true;
		else
			return false;
	}

	public void draw(Graphics2D g2)
	{
		int size = 6;
		if (this.owner != null)
			g2.setColor(this.owner.getColor());
		else
			g2.setColor(Color.black);
		g2.fillOval(this.getIntX() - size/2,
		            (int) (g2.getClipBounds().getHeight() - this.getIntY() - size/2),
		            size, size);

		if (this.owner == null)
			g2.drawString(this.label + "(-)", this.getIntX() - size/2,
			              (int) (g2.getClipBounds().getHeight() - this.getIntY() - size/2));
		else
			g2.drawString(this.label + "(" + this.owner.getLabel() + ")", this.getIntX() - size/2,
		              (int) (g2.getClipBounds().getHeight() - this.getIntY() - size/2));
	}


	public String toString()
	{
		return "Point " + this.label;
	}
}
