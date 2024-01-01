package hu.modeldriven.astah.validator.ui.table;

import hu.modeldriven.astah.validator.core.ValidationRule;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class SeverityRenderer extends DefaultTableCellRenderer {

    private final int WIDTH = 16;
    private final int HEIGHT = 16;

    private final Icon infoIcon;
    private final Icon warningIcon;
    private final Icon errorIcon;

    public SeverityRenderer() {
        super();
        this.infoIcon = scaleIcon(UIManager.getIcon("OptionPane.informationIcon"), WIDTH, HEIGHT);
        this.warningIcon = scaleIcon(UIManager.getIcon("OptionPane.warningIcon"), WIDTH, HEIGHT);
        this.errorIcon = scaleIcon(UIManager.getIcon("OptionPane.errorIcon"), WIDTH, HEIGHT);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        ValidationRule.Severity severity = (ValidationRule.Severity) value;

        switch (severity) {
            case ERROR:
                setIcon(errorIcon);
                break;
            case WARNING:
                setIcon(warningIcon);
                break;
            case INFO:
                setIcon(infoIcon);
                break;
        }

        return this;
    }

    private ImageIcon scaleIcon(Icon icon, int width, int height) {
        ImageIcon originalIcon = convertToImageIcon(icon);
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(scaledImage);
    }

    private ImageIcon convertToImageIcon(Icon icon) {
        // Create an empty ImageIcon with no image
        ImageIcon imageIcon = new ImageIcon();

        // Set the icon to the ImageIcon
        imageIcon.setImage(iconToImage(icon));

        return imageIcon;
    }

    // Helper method to convert Icon to Image
    private Image iconToImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        } else {
            // Create an empty image and draw the icon on it
            int width = icon.getIconWidth();
            int height = icon.getIconHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }

}
