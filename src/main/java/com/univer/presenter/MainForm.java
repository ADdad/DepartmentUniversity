package com.univer.presenter;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.univer.presenter.table.models.MastersTableModel;
import com.univer.service.MasterService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private MasterService masterService;
    private JPanel rootPanel;
    private JTable table1;
    private JComboBox cathedrasBox;
    private JComboBox chiefsBox;
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;
    private JLabel cathedraLabel;
    private JLabel chiefLabel;

    public MainForm(MasterService masterService) {
        this.masterService = masterService;
        configTable();
        setContentOfCathedras();
        setContentOfChiefs();
        setContentPane(rootPanel);
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRowSelected()) {
                    int n = JOptionPane.showConfirmDialog(
                            rootPanel,
                            "Would you like to delete selected master?",
                            "Delete master",
                            JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        deleteMaster();
                    }
                }
            }
        });
        cathedrasBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cathedra = String.valueOf(cathedrasBox.getSelectedItem());
                String chiefName = String.valueOf(chiefsBox.getSelectedItem());
                table1.setModel(new MastersTableModel(masterService.getFilteredMasters(cathedra, chiefName)));
            }
        });
        chiefsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cathedra = String.valueOf(cathedrasBox.getSelectedItem());
                String chiefName = String.valueOf(chiefsBox.getSelectedItem());
                table1.setModel(new MastersTableModel(masterService.getFilteredMasters(cathedra, chiefName)));
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMasterForm(masterService, table1);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRowSelected()) {
                    new EditMasterForm(masterService, table1, masterService.getMasterToEdit(getSelectedMasterId()));
                }
            }
        });
    }

    private boolean isRowSelected() {
        return table1.getSelectedRow() != -1;
    }

    private void configTable() {
        MastersTableModel model = new MastersTableModel(masterService.getMastersForMainTable());
        table1.setModel(model);
        if (table1.getModel().getRowCount() > 0) table1.setRowSelectionInterval(0, 0);
    }

    private void setContentOfCathedras() {
        List<String> cathedrasListForBox = masterService.getCathedrasListForBox();
        cathedrasListForBox.add(0, "");
        for (String s :
                cathedrasListForBox) {
            cathedrasBox.addItem(s);
        }
        // cathedrasListForBox.forEach(cathedra -> cathedrasBox.addItem(cathedra));
    }

    private void setContentOfChiefs() {
        List<String> chiefsListForBox = masterService.getChiefsListForBox();
        chiefsListForBox.add(0, "");
        for (String s :
                chiefsListForBox) {
            chiefsBox.addItem(s);
        }
        //chiefsListForBox.forEach(chief -> chiefsBox.addItem(chief));
    }

    private String getSelectedMasterId() {
        int row = table1.getSelectedRow();
        return table1.getModel().getValueAt(row, -1).toString();
    }

    private void deleteMaster() {
        masterService.deleteMaster(getSelectedMasterId());
        table1.setModel(new MastersTableModel(masterService.getMastersForMainTable()));
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        rootPanel.add(scrollPane1, new GridConstraints(1, 0, 3, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
        scrollPane1.setViewportView(table1);
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        rootPanel.add(deleteButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editButton = new JButton();
        editButton.setText("Edit");
        rootPanel.add(editButton, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setText("Add");
        rootPanel.add(addButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setEnabled(false);
        rootPanel.add(panel1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cathedraLabel = new JLabel();
        cathedraLabel.setText("Cathedra");
        panel2.add(cathedraLabel);
        cathedrasBox = new JComboBox();
        panel2.add(cathedrasBox);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        chiefLabel = new JLabel();
        chiefLabel.setText("Chief");
        panel3.add(chiefLabel);
        chiefsBox = new JComboBox();
        panel3.add(chiefsBox);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
