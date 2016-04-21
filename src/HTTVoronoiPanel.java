import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by bgenc on 19/04/16.
 */
public class HTTVoronoiPanel extends JPanel implements MouseListener {
    private HTTVoronoiTree tree;

    public HTTVoronoiPanel()
    {
        HTTVoronoiPoint p1 = new HTTVoronoiPoint(0, 0);
        HTTVoronoiPoint p2 = new HTTVoronoiPoint(600, 0);
        HTTVoronoiPoint p3 = new HTTVoronoiPoint(300, 600);

        HTTVoronoiTriangle tri = new HTTVoronoiTriangle(p1, p2, p3, 0);
        this.tree = new HTTVoronoiTree(tri);

        this.addMouseListener(this);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
	    Graphics2D g2 = (Graphics2D)g;
	    RenderingHints rh = new RenderingHints(
			    RenderingHints.KEY_TEXT_ANTIALIASING,
			    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);
        g2.setColor(Color.black);
        this.tree.draw(g2);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        HTTVoronoiTriangle tri = this.tree.getTriangle(e.getX(), this.getHeight() - e
              .getY());
	    if (tri == null)
		    return;
        tri.divide();
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
