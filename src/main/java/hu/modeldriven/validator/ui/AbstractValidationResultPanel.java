/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hu.modeldriven.validator.ui;

import hu.modeldriven.validator.core.ValidationSuite;

/**
 * @author zsolt
 */
public class AbstractValidationResultPanel extends javax.swing.JPanel {

    /**
     * Creates new form AbstractValidationResultPanel
     */
    public AbstractValidationResultPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        actionPanel = new javax.swing.JPanel();
        selectRepositoryButton = new javax.swing.JButton();
        validateButton = new javax.swing.JButton();
        suiteComboBox = new javax.swing.JComboBox<>();
        clearValidationButton = new javax.swing.JButton();
        selectPackageButton = new javax.swing.JButton();
        cardPanel = new javax.swing.JPanel();
        infoPanel = new javax.swing.JPanel();
        infoLabel = new javax.swing.JLabel();
        tablePanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        validationResultInfoPanel = new javax.swing.JPanel();
        validationResultLabel = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        actionPanel.setBackground(new java.awt.Color(255, 255, 255));

        selectRepositoryButton.setText("Select Repository...");

        validateButton.setText("Validate");
        validateButton.setEnabled(false);

        clearValidationButton.setText("Clear");

        selectPackageButton.setText("Select package...");

        javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(actionPanel);
        actionPanel.setLayout(actionPanelLayout);
        actionPanelLayout.setHorizontalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectRepositoryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(suiteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectPackageButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(validateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearValidationButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        actionPanelLayout.setVerticalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectRepositoryButton)
                    .addComponent(suiteComboBox)
                    .addComponent(validateButton)
                    .addComponent(clearValidationButton)
                    .addComponent(selectPackageButton))
                .addGap(11, 11, 11))
        );

        add(actionPanel, java.awt.BorderLayout.NORTH);

        cardPanel.setLayout(new java.awt.CardLayout());

        infoLabel.setText("Execute validation in order to see result!");

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                .addContainerGap(395, Short.MAX_VALUE)
                .addComponent(infoLabel)
                .addGap(296, 296, 296))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(infoLabel)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        cardPanel.add(infoPanel, "infoPanel");

        tablePanel.setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollPane.setViewportView(table);

        tablePanel.add(scrollPane, java.awt.BorderLayout.CENTER);

        cardPanel.add(tablePanel, "tablePanel");

        add(cardPanel, java.awt.BorderLayout.CENTER);

        validationResultInfoPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        validationResultInfoPanel.setPreferredSize(new java.awt.Dimension(993, 40));

        validationResultLabel.setText("No result....");

        javax.swing.GroupLayout validationResultInfoPanelLayout = new javax.swing.GroupLayout(validationResultInfoPanel);
        validationResultInfoPanel.setLayout(validationResultInfoPanelLayout);
        validationResultInfoPanelLayout.setHorizontalGroup(
            validationResultInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(validationResultInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(validationResultLabel)
                .addContainerGap(898, Short.MAX_VALUE))
        );
        validationResultInfoPanelLayout.setVerticalGroup(
            validationResultInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(validationResultInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(validationResultLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(validationResultInfoPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    protected javax.swing.JPanel cardPanel;
    protected javax.swing.JButton clearValidationButton;
    private javax.swing.JLabel infoLabel;
    protected javax.swing.JPanel infoPanel;
    private javax.swing.JScrollPane scrollPane;
    protected javax.swing.JButton selectPackageButton;
    protected javax.swing.JButton selectRepositoryButton;
    protected javax.swing.JComboBox<ValidationSuite> suiteComboBox;
    protected javax.swing.JTable table;
    protected javax.swing.JPanel tablePanel;
    protected javax.swing.JButton validateButton;
    private javax.swing.JPanel validationResultInfoPanel;
    protected javax.swing.JLabel validationResultLabel;
    // End of variables declaration//GEN-END:variables
}
