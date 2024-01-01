package hu.modeldriven.validator.ui.combobox;

import hu.modeldriven.validator.core.ValidationSuite;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.Component;

public class ValidationSuiteComboBoxRenderer extends BasicComboBoxRenderer {

    @Override
    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        ValidationSuite item = (ValidationSuite) value;
        if (item != null) {
            setText(item.name());
        } else {
            setText("");
        }

        return this;
    }

}
