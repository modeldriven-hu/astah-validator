package hu.modeldriven.astah.core.dialog.pkg;

import com.change_vision.jude.api.inf.model.IPackage;

import javax.swing.JDialog;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.util.function.Consumer;

public class PackageSelectorPanel extends AbstractPackageSelectorPanel {

    private final JDialog parentDialog;
    private final IPackage rootPackage;

    private final Consumer<IPackage> callback;

    public PackageSelectorPanel(JDialog parentDialog, IPackage rootPackage, Consumer<IPackage> callback) {
        super();
        this.parentDialog = parentDialog;
        this.rootPackage = rootPackage;
        this.callback = callback;
        initComponents();
    }

    private void initComponents() {

        DefaultTreeModel treeModel = new DefaultTreeModel(
                new PackageTreeNode(rootPackage, null));

        packageTree.setModel(treeModel);
        packageTree.setCellRenderer(new PackageTreeNodeRenderer());
        packageTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        this.okButton.addActionListener(actionEvent -> {

            PackageTreeNode node = (PackageTreeNode)
                    packageTree.getLastSelectedPathComponent();

            if (node != null) {
                this.callback.accept(node.model());
            }

            this.parentDialog.setVisible(false);
            this.parentDialog.dispose();
        });

        this.cancelButton.addActionListener(actionEvent -> {
            this.parentDialog.setVisible(false);
            this.parentDialog.dispose();
        });
    }

}
