package vue.components;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class RoundedBorder extends AbstractBorder {
    private static final long serialVersionUID = 1L;
    private final Color color;
    private final int arc;
    private final int thickness;

    public RoundedBorder(Color color, int arc) {
        this(color, arc, 1);
    }

    public RoundedBorder(Color color, int arc, int thickness) {
        this.color = color;
        this.arc = arc;
        this.thickness = thickness;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        for (int i = 0; i < thickness; i++) {
            g2.drawRoundRect(x + i, y + i, width - i - i - 1, height - i - i - 1, arc, arc);
        }
        g2.dispose();
    }
}
