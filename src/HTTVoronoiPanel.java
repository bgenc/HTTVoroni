import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

/**
 * Created by bgenc on 19/04/16.
 */
public class HTTVoronoiPanel extends JPanel implements MouseListener
{
	private HTTVoronoiTree tree;
	private Vector<HTTVoronoiSite> sites;

	public HTTVoronoiPanel()
	{
		HTTVoronoiPoint p1 = new HTTVoronoiPoint(20, 20);
		HTTVoronoiPoint p2 = new HTTVoronoiPoint(580, 20);
		HTTVoronoiPoint p3 = new HTTVoronoiPoint(300, 550);

		HTTVoronoiTriangle tri = new HTTVoronoiTriangle(p1, p2, p3, 0);
		this.tree = new HTTVoronoiTree(tri);

		this.sites = new Vector<>();

		this.addMouseListener(this);
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		g2.setColor(Color.black);
		this.tree.draw(g2);

		// Draw the sites
		g2.setColor(Color.blue);
		for (HTTVoronoiSite s : sites)
			s.draw(g2);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		int clickX = e.getX();
		int clickY = this.getHeight() - e.getY();
		HTTVoronoiSite site = new HTTVoronoiSite(clickX, clickY, 1.0f);
		sites.add(site);
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent)
	{

	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}
}
