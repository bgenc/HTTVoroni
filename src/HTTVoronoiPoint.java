/**
 * Created by bgenc on 19/04/16.
 */
public class HTTVoronoiPoint
{
    private float x;
    private float y;

    public HTTVoronoiPoint(float _x, float _y)
    {
        this.x = _x;
        this.y = _y;
    }

    public float getX()
    {
        return this.x;
    }

    public float getY()
    {
        return this.y;
    }

    public int getIntX()
    {
        return (int)(this.x);
    }

    public int getIntY()
    {
        return (int)(this.y);
    }
}
