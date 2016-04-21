import java.awt.*;

/**
 * Created by bgenc on 19/04/16.
 */
public class HTTVoronoiTriangle
{
	private HTTVoronoiSite owner;
	private boolean isLeaf;
	private int level;
	private HTTVoronoiTriangle children[];
	private HTTVoronoiPoint points[];

	public HTTVoronoiTriangle(HTTVoronoiPoint p1, HTTVoronoiPoint p2, HTTVoronoiPoint p3,
	                          int level)
	{
		this.owner = null;
		this.isLeaf = true;
		this.points = new HTTVoronoiPoint[3];
		this.points[0] = p1;
		this.points[1] = p2;
		this.points[2] = p3;
		this.level = level;
	}

	/**
	 * Returns true if this triangle is not divided.
	 *
	 * @return True if this triangle is not divided.
	 */
	public boolean isLeaf()
	{
		return this.isLeaf;
	}

	/**
	 * Returns true if this triangle has been divided into four smaller triangles.
	 *
	 * @return True if this triangle has been divided.
	 */
	public boolean hasChildren()
	{
		return !this.isLeaf;
	}

	public void setOwner(HTTVoronoiSite site)
	{
		this.owner = site;
	}

	/**
	 * Check whether this triangle allows further division.
	 * @return True if this triangle can be further divided.
	 */
	public boolean isDivisible()
	{
		return this.level != HTTVoronoiTree.DEPTH_THRESHOLD;
	}

	/**
	 * Divide this triangle into four smaller triangles.
	 */
	public void divide()
	{
		this.children = new HTTVoronoiTriangle[4];
		HTTVoronoiPoint p0 = this.points[0];
		HTTVoronoiPoint p1 = this.points[1];
		HTTVoronoiPoint p2 = this.points[2];
		HTTVoronoiPoint p0_1 = new HTTVoronoiPoint(
				(this.points[0].getX() + this.points[1].getX()) / 2,
				(this.points[0].getY() + this.points[1].getY()) / 2);
		HTTVoronoiPoint p1_2 = new HTTVoronoiPoint(
				(this.points[1].getX() + this.points[2].getX()) / 2,
				(this.points[1].getY() + this.points[2].getY()) / 2);
		HTTVoronoiPoint p2_0 = new HTTVoronoiPoint(
				(this.points[2].getX() + this.points[0].getX()) / 2,
				(this.points[2].getY() + this.points[0].getY()) / 2);
		this.children[0] = new HTTVoronoiTriangle(p0, p0_1, p2_0, level + 1);
		this.children[1] = new HTTVoronoiTriangle(p1, p1_2, p0_1, level + 1);
		this.children[2] = new HTTVoronoiTriangle(p2, p2_0, p1_2, level + 1);
		this.children[3] = new HTTVoronoiTriangle(p0_1, p1_2, p2_0, level + 1);
		this.isLeaf = false;
	}

	/**
	 * Draw this triangle on the given Graphics buffer. Also draw the children
	 * of this triangle if there exists any.
	 *
	 * @param g The Graphics buffer to draw on.
	 */
	void draw(Graphics2D g)
	{
		int height = (int) g.getClipBounds().getHeight();

		for (int i = 0; i < points.length; i++)
		{
			points[i].draw(g);
			g.drawLine(points[i].getIntX(), height - points[i].getIntY(),
			           points[(i + 1) % points.length].getIntX(),
			           height - points[(i + 1) % points.length].getIntY());
			//g.drawString(""+points[i], points[i].getIntX(), height - points[i].getIntY
			// ());
		}
		if (this.hasChildren())
		{
			for (HTTVoronoiTriangle aChildren : this.children)
			{
				aChildren.draw(g);
			}
		}
	}

	private boolean left(float a0, float a1, float b0, float b1, float c0, float c1)
	{
		float r = (0.5f * ((b0 - a0) * (c1 - a1) - (b1 - a1) * (c0 - a0)));
		//System.out.println(r);
		return r >= 0;
	}

	private boolean contains(float x, float y)
	{
		return left(points[0].getX(), points[0].getY(), points[1].getX(),
		            points[1].getY(), x, y) &&
				left(points[1].getX(), points[1].getY(), points[2].getX(), points[2].getY(),
				     x, y) &&
				left(points[2].getX(), points[2].getY(), points[0].getX(), points[0].getY(),
				     x, y);
	}

	HTTVoronoiTriangle getTriangle(float x, float y)
	{
		if (this.contains(x, y))
		{
			if (this.isLeaf)
			{
				return this;
			}
			else
			{
				int i = 0;
				while (i < this.children.length)
				{
					HTTVoronoiTriangle tri = this.children[i].getTriangle(x, y);
					if (tri != null)
					{
						return tri;
					}
					else
					{
						i++;
					}
				}
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	public String toString()
	{
		return "[T:" + this.points[0] + "," + this.points[1] + "," + this.points[2] + "]";
	}
}
