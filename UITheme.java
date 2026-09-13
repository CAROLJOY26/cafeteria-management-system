import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public final class UITheme {
    public static final Color PURPLE = new Color(116, 91, 255);
    public static final Color PURPLE_DARK = new Color(83, 62, 190);
    public static final Color BLUE = new Color(74, 144, 226);
    public static final Color PINK = new Color(245, 126, 182);
    public static final Color MINT = new Color(93, 202, 169);
    public static final Color YELLOW = new Color(247, 190, 82);
    public static final Color RED = new Color(235, 91, 91);
    public static final Color BG = new Color(247, 245, 252);
    public static final Color TEXT = new Color(43, 43, 62);
    public static final Color MUTED = new Color(112, 111, 130);
    public static final Color WHITE = Color.WHITE;

    private UITheme() {}

    public static JLabel title(String text, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, size));
        label.setForeground(TEXT);
        return label;
    }

    public static JLabel subtitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(MUTED);
        return label;
    }

    public static RoundedButton button(String text, Color color) {
        return new RoundedButton(text, color);
    }

    public static RoundedTextField textField(String placeholder) {
        return new RoundedTextField(placeholder);
    }

    public static RoundedPasswordField passwordField(String placeholder) {
        return new RoundedPasswordField(placeholder);
    }

    public static RoundedPanel card() {
        RoundedPanel panel = new RoundedPanel(24, new Color(255, 255, 255, 235));
        panel.setBorder(new EmptyBorder(22, 24, 22, 24));
        return panel;
    }

    public static class RoundedButton extends JButton {
        private final Color baseColor;
        private boolean hover;

        public RoundedButton(String text, Color color) {
            super(text);
            this.baseColor = color;
            setFont(new Font("SansSerif", Font.BOLD, 14));
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(new EmptyBorder(13, 22, 13, 22));
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseEntered(java.awt.event.MouseEvent e) { hover = true; repaint(); }
                @Override public void mouseExited(java.awt.event.MouseEvent e) { hover = false; repaint(); }
            });
        }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color c = hover ? baseColor.brighter() : baseColor;
            g2.setColor(new Color(0, 0, 0, 22));
            g2.fillRoundRect(1, 4, getWidth() - 2, getHeight() - 3, getHeight(), getHeight());
            g2.setColor(c);
            g2.fillRoundRect(0, 0, getWidth(), getHeight() - 4, getHeight(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color fill;
        public RoundedPanel(int radius, Color fill) {
            this.radius = radius;
            this.fill = fill;
            setOpaque(false);
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0, 0, 0, 18));
            g2.fillRoundRect(2, 4, getWidth() - 4, getHeight() - 5, radius, radius);
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, getWidth() - 2, getHeight() - 4, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static class RoundedTextField extends JTextField {
        private final String placeholder;
        public RoundedTextField(String placeholder) {
            this.placeholder = placeholder;
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            setForeground(TEXT);
            setBorder(new EmptyBorder(12, 16, 12, 16));
            setOpaque(false);
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(247, 247, 252));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            g2.setColor(new Color(220, 219, 232));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 18, 18);
            g2.dispose();
            super.paintComponent(g);
            if (getText().isEmpty() && !hasFocus()) {
                Graphics2D p = (Graphics2D) g.create();
                p.setColor(new Color(155, 154, 172));
                p.setFont(getFont());
                p.drawString(placeholder, 16, getHeight()/2 + 5);
                p.dispose();
            }
        }
    }

    public static class RoundedPasswordField extends JPasswordField {
        private final String placeholder;
        public RoundedPasswordField(String placeholder) {
            this.placeholder = placeholder;
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            setForeground(TEXT);
            setBorder(new EmptyBorder(12, 16, 12, 16));
            setOpaque(false);
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(247, 247, 252));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            g2.setColor(new Color(220, 219, 232));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 18, 18);
            g2.dispose();
            super.paintComponent(g);
            if (getPassword().length == 0 && !hasFocus()) {
                Graphics2D p = (Graphics2D) g.create();
                p.setColor(new Color(155, 154, 172));
                p.setFont(getFont());
                p.drawString(placeholder, 16, getHeight()/2 + 5);
                p.dispose();
            }
        }
    }

    public static class GradientPanel extends JPanel {
        private final Color c1, c2;
        public GradientPanel(Color c1, Color c2) { this.c1 = c1; this.c2 = c2; setOpaque(false); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setPaint(new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
