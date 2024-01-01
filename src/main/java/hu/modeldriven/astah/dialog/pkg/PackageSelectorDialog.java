package hu.modeldriven.astah.dialog.pkg;

import com.change_vision.jude.api.inf.model.IPackage;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.function.Consumer;

public class PackageSelectorDialog {

    private final Component parentComponent;

    private final IPackage rootPackage;

    private final Consumer<IPackage> callback;

    public PackageSelectorDialog(Component parentComponent, IPackage rootPackage, Consumer<IPackage> callback) {
        this.parentComponent = parentComponent;
        this.rootPackage = rootPackage;
        this.callback = callback;
    }

    public void show() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().setLayout(new BorderLayout());

        PackageSelectorPanel panel = new PackageSelectorPanel(dialog, rootPackage, callback);
        dialog.getContentPane().add(panel);

        dialog.pack();
        dialog.setLocationRelativeTo(parentComponent);
        dialog.setVisible(true);
    }

}
