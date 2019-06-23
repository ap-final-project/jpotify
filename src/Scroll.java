
import java.awt.*;
//from  w w w. j  a  va  2 s  .c  o m
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
public class Scroll extends JScrollPane {
    public Scroll(Panel panel){
        super(panel);
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setComponentZOrder(this.getVerticalScrollBar(), 0);
        this.setComponentZOrder(this.getViewport(), 1);
        this.getVerticalScrollBar().setOpaque(false);

        this.setLayout(new ScrollPaneLayout() {
            @Override
            public void layoutContainer(Container parent) {
                JScrollPane scrollPane = (JScrollPane) parent;
                Rectangle availR = scrollPane.getBounds();
                availR.x = availR.y = 0;

                Insets parentInsets = parent.getInsets();
                availR.x = parentInsets.left;
                availR.y = parentInsets.top;
                availR.width -= parentInsets.left + parentInsets.right;
                availR.height -= parentInsets.top + parentInsets.bottom;

                Rectangle vsbR = new Rectangle();
                vsbR.width = 12;
                vsbR.height = availR.height;
                vsbR.x = availR.x + availR.width - vsbR.width;
                vsbR.y = availR.y;

                if (viewport != null) {
                    viewport.setBounds(availR);
                }
                if (vsb != null) {
                    vsb.setVisible(true);
                    vsb.setBounds(vsbR);
                }
            }
        });
        this.getVerticalScrollBar().setUI(new MyScrollBarUI());
    }
    public void setPanel(Panel p){
        this.viewport.remove(0);
        this.viewport.add(p);
        this.getViewport().revalidate();
    }
}

class MyScrollBarUI extends BasicScrollBarUI {


    private final Dimension d = new Dimension(20,20);
    @Override
    protected JButton createDecreaseButton(int orientation) {
        Button btn =new Button(2) {
            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
        btn.setIcon(new ImageIcon("img\\up-arrow.png"));
        return btn;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        Button btn =new Button(2) {
            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
        btn.setIcon(new ImageIcon("img\\down-arrow.png"));
        return btn;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = null;
        JScrollBar sb = (JScrollBar) c;
        if (!sb.isEnabled() || r.width > r.height) {
            return;
        } else if (isDragging) {
            color = Color.DARK_GRAY;
        } else if (isThumbRollover()) {
            color = Color.LIGHT_GRAY;
        } else {
            color = Color.GRAY;
        }
        g2.setPaint(color);
        g2.fillRoundRect(r.x, r.y, r.width, r.height, 0, 0);
        g2.setPaint(Color.WHITE);
//        g2.drawRoundRect(r.x, r.y, r.width, r.height, 0, 0);
        g2.dispose();
    }

    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
        super.setThumbBounds(x, y, width, height);
        scrollbar.repaint();
    }
}
