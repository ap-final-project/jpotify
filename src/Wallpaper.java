import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.LayerUI;

class WallpaperLayerUI extends LayerUI<JComponent> {
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);

        Graphics2D g2 = (Graphics2D) g.create();

        int w = c.getWidth();
        int h = c.getHeight();
        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, .5f));
        g2.setPaint(new GradientPaint(0, 0, Color.yellow, 0, h, Color.red));
        g2.fillRect(0, 0, w, h);

        g2.dispose();
    }
}

public class Wallpaper {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createUI();
            }
        });
    }

    public static void createUI() {
        JFrame f = new JFrame("Wallpaper");

        Panel panel = createPanel();
        LayerUI<JComponent> layerUI = new WallpaperLayerUI();
        JLayer<JComponent> jlayer = new JLayer<JComponent>(panel, layerUI);

        f.add(jlayer);

        f.setSize(300, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private static Panel createPanel() {
        Panel p = new Panel(1);

        ButtonGroup entreeGroup = new ButtonGroup();
        JRadioButton radioButton;
        p.add(radioButton = new JRadioButton("Beef", true));
        entreeGroup.add(radioButton);
        p.add(radioButton = new JRadioButton("Chicken"));
        entreeGroup.add(radioButton);
        p.add(radioButton = new JRadioButton("Vegetable"));
        entreeGroup.add(radioButton);

        p.add(new JCheckBox("Ketchup"));
        p.add(new JCheckBox("Mustard"));
        p.add(new JCheckBox("Pickles"));

        p.add(new JLabel("Special requests:"));
        p.add(new JTextField(20));

        JButton orderButton = new JButton("Place Order");
        p.add(orderButton);

        return p;
    }
}