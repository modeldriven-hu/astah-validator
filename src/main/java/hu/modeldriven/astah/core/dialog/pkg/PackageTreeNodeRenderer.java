package hu.modeldriven.astah.core.dialog.pkg;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Component;

public class PackageTreeNodeRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        Component component = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        PackageTreeNode node = (PackageTreeNode) value;

        if (component instanceof JLabel) {
            ((JLabel) component).setText(node.name());
            ((JLabel) component).setIcon(this.getDefaultOpenIcon());
        }

        return component;
    }
}
