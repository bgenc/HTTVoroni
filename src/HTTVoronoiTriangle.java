import java.awt.*;

/**
 * Created by bgenc on 19/04/16.
 */
public class HTTVoronoiTriangle
{
    private boolean isLeaf;
    private HTTVoronoiTriangle children[];
    private HTTVoronoiPoint points[];

    public HTTVoronoiTriangle(HTTVoronoiPoint p1, HTTVoronoiPoint p2, HTTVoronoiPoint p3)
    {
        this.isLeaf = true;
        this.points = new HTTVoronoiPoint[3];
        this.points[0] = p1;
        this.points[1] = p2;
        this.points[2] = p3;
    }

    public boolean isLeaf()
    {
        return this.isLeaf;
    }

    public boolean hasChildren()
    {
        return !this.isLeaf;
    }

    public void divide()
    {
        assert this.isLeaf;

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
        this.children[0] = new HTTVoronoiTriangle(p0, p0_1, p2_0);
        this.children[1] = new HTTVoronoiTriangle(p1, p1_2, p0_1);
        this.children[2] = new HTTVoronoiTriangle(p2, p2_0, p1_2);
        this.children[3] = new HTTVoronoiTriangle(p0_1, p1_2, p2_0);
        this.isLeaf = false;
    }

    public void draw(Graphics g)
    {
        for (int i = 0; i < points.length; i++)
        g.drawLine(points[i].getIntX(), points[i].getIntY(),
                points[(i + 1) % points.length].getIntX(),
                points[(i+1)%points.length].getIntY());
        if (this.hasChildren())
        {
            for (int i = 0; i < this.children.length; i++)
                this.children[i].draw(g);
        }
    }
}
