import java.awt.*;

/**
 * Created by bgenc on 4/21/16.
 */
public class HTTVoronoiSite extends HTTVoronoiPoint
{
	/**
	 * The weight of the site.
	 */
	private float w;

	private Color c;

	public HTTVoronoiSite(String _label, float _x, float _y, float _w)
	{
		super(_label, _x, _y);
		this.w = _w;
		this.c = new Color((float)Math.random() * 0.8f,
		                   (float)Math.random() * 0.8f,
		                   (float)Math.random() * 0.8f);
	}

	public float getWeight()
	{
		return this.w;
	}

	public Color getColor()
	{
		return this.c;
	}

	public String toString()
	{
		return "Site "  + this.getLabel();
	}

	public void draw(Graphics2D g2)
	{
		int size = (int) (w * 10);
		g2.setColor(c);
		g2.fillOval(this.getIntX() - size/2,
		            (int) (g2.getClipBounds().getHeight() - this.getIntY() - size/2),
		            size, size);
		g2.drawString(this.getLabel(), this.getIntX() - size/2,
		            (int) (g2.getClipBounds().getHeight() - this.getIntY() - size/2));
	}
}
