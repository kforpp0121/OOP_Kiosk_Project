import javax.swing.border.Border;
import java.awt.*;

class RoundedBorder implements Border {

    private int radius;
    private int thickness;

    public RoundedBorder(int radius, int thickness) {
        this.radius = radius;
        this.thickness = thickness;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + this.thickness, this.radius + this.thickness,
                this.radius + this.thickness + 1, this.radius + this.thickness);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(this.thickness)); // 두께 설정
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}