/**
 * Created by bgenc on 4/21/16.
 */
public class HTTVoronoiSite extends HTTVoronoiPoint
{
	/**
	 * The weight of the site.
	 */
	private int w;

	public HTTVoronoiSite(float _x, float _y, int _w)
	{
		super(_x, _y);
		this.w = _w;
	}

	public int getWeight()
	{
		return this.w;
	}

	public String toString()
	{
		return "(S:" + this.getX() + "," + this.getY() + "," + this.getWeight() + ")";
	}
}
