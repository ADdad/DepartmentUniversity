package com.univer.presenter.starostiuk;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.univer.presenter.table.models.MultiLineTableCellRenderer;
import com.univer.presenter.table.models.WorkTableModel;
import com.univer.service.MasterService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScientificWorksWindow extends JFrame {
    private MasterService masterService;
    private JPanel rootPanel;
    private JTable table1;
    private JComboBox cathedrasBox;
    private JComboBox chiefsBox;
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;
    private JPanel startDatePanel;
    private JPanel endDatePanel;
    private JComboBox scienceThemeBox;
    private JButton backButton;
    private JComboBox yearsBox;
    private String masterId;

    public ScientificWorksWindow(MasterService masterService, String masterId) {
        this.masterService = masterService;
        this.masterId = masterId;
        configTable();
        setContentOfScienceThemes();
        setContentOfYearsBox();
        setContentPane(rootPanel);
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRowSelected()) {
                    int n = JOptionPane.showConfirmDialog(
                            rootPanel,
                            "Would you like to delete selected work?",
                            "Delete job",
                            JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        deleteWork();
                    }
                }
            }
        });
        scienceThemeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scienceThemeName = String.valueOf(scienceThemeBox.getSelectedItem());
                String year = String.valueOf(yearsBox.getSelectedItem());
                table1.setModel(new WorkTableModel(masterService.getFilteredWorks(scienceThemeName, year, masterId)));
            }
        });
        yearsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scienceThemeName = String.valueOf(scienceThemeBox.getSelectedItem());
                String year = String.valueOf(yearsBox.getSelectedItem());
                table1.setModel(new WorkTableModel(masterService.getFilteredWorks(scienceThemeName, year, masterId)));
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddWorkForm(masterService, table1, masterId);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRowSelected()) {
                    new EditWorkForm(masterService, table1, masterService.getWorkToEdit(getSelectedWorkId()), masterId);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private boolean isRowSelected() {
        return table1.getSelectedRow() != -1;
    }

    private void configTable() {
        WorkTableModel model = new WorkTableModel(masterService.getMastersWorks(masterId));
        table1.setModel(model);
        MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
        table1.setDefaultRenderer(String[].class, renderer);
        table1.setRowHeight(100);
        if (table1.getModel().getRowCount() > 0) table1.setRowSelectionInterval(0, 0);
    }

    private void setContentOfScienceThemes() {
        List<String> scienceThemesBox = masterService.getScienceThemesValues();
        scienceThemesBox.add(0, "");
        for (String s :
                scienceThemesBox) {
            scienceThemeBox.addItem(s);
        }
        //scienceThemesBox.forEach(chief -> this.scienceThemeBox.addItem(chief));
    }

    private void setContentOfYearsBox() {
        List<String> yearsValues = masterService.getYearsValues(masterId);
        yearsValues.add(0, "");
        for (String s :
                yearsValues) {
            yearsBox.addItem(s);
        }
        //yearsValues.forEach(year -> yearsBox.addItem(year));
    }

    private String getSelectedWorkId() {
        int row = table1.getSelectedRow();
        return table1.getModel().getValueAt(row, -1).toString();
    }

    private void deleteWork() {
        masterService.deleteMasterWork(getSelectedWorkId(), masterId);
        table1.setModel(new WorkTableModel(masterService.getMastersWorks(masterId)));
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
        rootPanel.setLayout(new GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        rootPanel.add(scrollPane1, new GridConstraints(1, 0, 5, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
        scrollPane1.setViewportView(table1);
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        rootPanel.add(deleteButton, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editButton = new JButton();
        editButton.setText("Edit");
        rootPanel.add(editButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setText("Add");
        rootPanel.add(addButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setEnabled(false);
        rootPanel.add(panel1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        startDatePanel = new JPanel();
        startDatePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(startDatePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Science theme");
        startDatePanel.add(label1);
        scienceThemeBox = new JComboBox();
        startDatePanel.add(scienceThemeBox);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Year");
        panel2.add(label2);
        yearsBox = new JComboBox();
        panel2.add(yearsBox);
        backButton = new JButton();
        backButton.setText("Back");
        rootPanel.add(backButton, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
