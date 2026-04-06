package vue.components;

import javax.swing.border.CompoundBorder;
import java.awt.*;

public class CompoundRoundedBorder extends CompoundBorder {
    public CompoundRoundedBorder(Color outerColor, int arc, int outerThickness) {
        super(new RoundedBorder(outerColor, arc, outerThickness), new RoundedBorder(new Color(40, 50, 80), arc, 1));
    }
}
