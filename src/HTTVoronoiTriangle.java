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
		HTTVoronoiPoint p0_1 = new HTTVoronoiPoint(p0.getLabel() + "|" + p1.getLabel(),
				(this.points[0].getX() + this.points[1].getX()) / 2,
				(this.points[0].getY() + this.points[1].getY()) / 2);
		HTTVoronoiPoint p1_2 = new HTTVoronoiPoint(p1.getLabel() + "|" + p2.getLabel(),
				(this.points[1].getX() + this.points[2].getX()) / 2,
				(this.points[1].getY() + this.points[2].getY()) / 2);
		HTTVoronoiPoint p2_0 = new HTTVoronoiPoint(p2.getLabel() + "|" + p0.getLabel(),
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
			if (points[i].getOwner() == points[(i + 1) % points.length].getOwner() &&
					points[i].getOwner() != null)
				g.setColor(points[i].getOwner().getColor());
			else
				g.setColor(Color.black);
			g.drawLine(points[i].getIntX(), height - points[i].getIntY(),
			           points[(i + 1) % points.length].getIntX(),
			           height - points[(i + 1) % points.length].getIntY());
			//g.drawString(""+points[i], points[i].getIntX(), height - points[i].getIntY
			// ());
			points[i].draw(g);
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
		return "Triangle " + this.points[0].getLabel() + "-" + this.points[1].getLabel()
				+ "-" + this.points[2].getLabel();
	}

	public boolean insert(HTTVoronoiSite s)
	{
		System.out.println("Trying to insert " + s + " into " + this);
		// How many points will change ownership?
		int change = 0;

		for (HTTVoronoiPoint p : points)
		{
			if (p.canBeOwnedBy(s))
			{
				System.out.println("  " + s + " now owns " + p);
				p.setOwner(s);
				change++;
			}
		}

		System.out.println("  A total of " + change + " changes occurred.");

		if (change == 0) //No change of ownership
		{
			return false;
		}
		else if (change == 3) // All points changed ownership
		{
			System.out.println("  Setting " + this + " as a leaf!");
			this.children = null;
			this.isLeaf = true;
			return true;
		}
		else if (this.isDivisible())
		{
			System.out.println("  Dividing " + this);
			if (this.isLeaf) // If this is already a leaf, then we divide it
				this.divide();

			// Insert all owner sites into all children
			// TODO this may insert the same owner a few times which is bad
			for (HTTVoronoiTriangle t : children)
				for (HTTVoronoiPoint p : points)
				{
					t.insert(p.getOwner());
				}

			return true;
		}
		else
		{
			System.out.println("Whta the!");
			return false;
		}
	}
}
