import javax.swing.*;

/**
 * Created by bgenc on 19/04/16.
 */
public class HTTVoroniMain extends JFrame
{
    public HTTVoroniMain()
    {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new HTTVoronoiPanel());
    }

    public static void main(String args[])
    {
        HTTVoroniMain app = new HTTVoroniMain();
        app.setSize(600, 600);
        app.setVisible(true);
    }
}
