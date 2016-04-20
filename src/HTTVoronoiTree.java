import java.awt.*;

/**
 * Created by bgenc on 19/04/16.
 */
public class HTTVoronoiTree
{
    private HTTVoronoiTriangle root;

    public HTTVoronoiTree(HTTVoronoiTriangle tri)
    {
        this.root = tri;
    }

    public HTTVoronoiTriangle getRoot()
    {
        return this.root;
    }

    public void draw(Graphics g)
    {
        this.root.draw(g);

    }
}
