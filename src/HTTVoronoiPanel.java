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
        HTTVoronoiPoint p1 = new HTTVoronoiPoint(200, 0);
        HTTVoronoiPoint p2 = new HTTVoronoiPoint(0, 400);
        HTTVoronoiPoint p3 = new HTTVoronoiPoint(400, 400);

        HTTVoronoiTriangle tri = new HTTVoronoiTriangle(p1, p2, p3);
        this.tree = new HTTVoronoiTree(tri);

        this.addMouseListener(this);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.black);
        this.tree.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.tree.getRoot().divide();
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
