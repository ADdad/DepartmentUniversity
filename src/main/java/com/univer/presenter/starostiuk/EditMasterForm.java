package com.univer.presenter.starostiuk;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.toedter.calendar.JDateChooser;
import com.univer.model.dto.MasterEditDto;
import com.univer.presenter.table.models.MastersTableModel;
import com.univer.service.MasterService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

public class EditMasterForm extends JFrame {
    private JPanel rootAddPanel;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField genderField;
    private JTextField themeOfDiplomaField;
    private JPanel startDatePanel;
    private JButton saveButton;
    private JButton closeButton;
    private JPanel secondPanel;
    private JPanel endDatePanel;
    private JTextField endReasonField;
    private JComboBox cathedraBox;
    private JComboBox chiefsBox;
    private JLabel en;
    private JLabel alertText;
    private JButton workOnThemasButton;
    private JButton scientificWorksButton;
    private JTextArea themeOfDiplomaArea;
    private Calendar calendar = Calendar.getInstance();
    private JDateChooser startDate = new JDateChooser(calendar.getTime());
    private JDateChooser endDate = new JDateChooser();
    private MasterService masterService;
    private JTable mainTable;

    public EditMasterForm(MasterService masterService, JTable mainTable, MasterEditDto masterEditDto) {
        this.mainTable = mainTable;
        this.masterService = masterService;
        extractMasterData(masterEditDto);
        setContentOfCathedras();
        setContentOfChiefs();
        setContentPane(rootAddPanel);
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        alertText.setForeground(Color.red);

        //Date fields
        startDate.setDateFormatString("dd/MM/yyyy");
        endDate.setDateFormatString("dd/MM/yyyy");
        startDatePanel.add(startDate);
        endDatePanel.add(endDate);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MasterEditDto masterEditDto1 = combineMasterData();
                if (validateInput()) {
                    masterService.updateMaster(masterEditDto1);
                    mainTable.setModel(new MastersTableModel(masterService.getMastersForMainTable()));
                    dispose();
                }
            }
        });
        workOnThemasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScientificJobsWindow(masterService, masterEditDto.getId());
            }
        });
        scientificWorksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScientificWorksWindow(masterService, masterEditDto.getId());
            }
        });
    }

    private MasterEditDto combineMasterData() {
        MasterEditDto masterEditDto = new MasterEditDto();
        masterEditDto.setName(nameField.getText());
        masterEditDto.setGender(genderField.getText());
        masterEditDto.setPhone(phoneField.getText());
        masterEditDto.setThemeOfDiploma(themeOfDiplomaArea.getText());
        masterEditDto.setStartDate(new Date(startDate.getDate().getTime()));
        if (endDate.getDate() != null) {
            masterEditDto.setEndDate(new Date(endDate.getDate().getTime()));
        }
        masterEditDto.setEndReason(endReasonField.getText());
        masterEditDto.setCathedraName(cathedraBox.getSelectedItem().toString());
        masterEditDto.setChief(chiefsBox.getSelectedItem().toString());
        return masterEditDto;
    }

    private String getValueOrEmpty(String value) {
        if (value != null && !value.isEmpty()) return value;
        return "Empty";
    }

    private void extractMasterData(MasterEditDto masterEditDto) {
        nameField.setText(masterEditDto.getName());
        genderField.setText(masterEditDto.getGender());
        phoneField.setText(masterEditDto.getPhone());
        themeOfDiplomaArea.setText(getValueOrEmpty(masterEditDto.getThemeOfDiploma()));
        startDate.setDate(new java.util.Date(masterEditDto.getStartDate().getTime()));
        if (masterEditDto.getEndDate() != null) {
            endDate.setDate(new java.util.Date(masterEditDto.getEndDate().getTime()));
        }
        endReasonField.setText(getValueOrEmpty(masterEditDto.getEndReason()));
        cathedraBox.setSelectedItem(masterEditDto.getCathedraName());
        chiefsBox.setSelectedItem(masterEditDto.getChief());
    }

    private boolean validateInput() {
        boolean validator = true;
        if (nameField.getText().isEmpty()) {
            validator = false;
            nameField.setBorder(new LineBorder(Color.red, 1));
        }
        if (genderField.getText().isEmpty()) {
            validator = false;
            genderField.setBorder(new LineBorder(Color.red, 1));
        }
        if (phoneField.getText().isEmpty()) {
            validator = false;
            phoneField.setBorder(new LineBorder(Color.red, 1));
        }
        if (!validator) alertText.setVisible(true);
        return validator;
    }

    private void setContentOfCathedras() {
        for (String s :
                masterService.getCathedrasListForBox()) {
            cathedraBox.addItem(s);
        }
//        masterService.getCathedrasListForBox().forEach(cathedra -> cathedraBox.addItem(cathedra));
    }

    private void setContentOfChiefs() {
        for (String s :
                masterService.getChiefsListForBox()) {
            chiefsBox.addItem(s);
        }
//        masterService.getChiefsListForBox().forEach(chief -> chiefsBox.addItem(chief));
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
        rootAddPanel = new JPanel();
        rootAddPanel.setLayout(new GridLayoutManager(8, 2, new Insets(0, 0, 0, 0), -1, -1));
        startDatePanel = new JPanel();
        startDatePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rootAddPanel.add(startDatePanel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Start  masters date*");
        startDatePanel.add(label1);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootAddPanel.add(panel1, new GridConstraints(0, 1, 6, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        endDatePanel = new JPanel();
        endDatePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(endDatePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("End masters date");
        endDatePanel.add(label2);
        secondPanel = new JPanel();
        secondPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(secondPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, 40), null, null, 0, false));
        en = new JLabel();
        en.setText("End reason");
        secondPanel.add(en);
        endReasonField = new JTextField();
        endReasonField.setColumns(10);
        secondPanel.add(endReasonField);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Cathedra*");
        panel2.add(label3);
        cathedraBox = new JComboBox();
        panel2.add(cathedraBox);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Chief*");
        panel3.add(label4);
        chiefsBox = new JComboBox();
        panel3.add(chiefsBox);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rootAddPanel.add(panel4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(40, -1), null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Name*");
        panel4.add(label5);
        nameField = new JTextField();
        nameField.setColumns(10);
        panel4.add(nameField);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rootAddPanel.add(panel5, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Phone*");
        panel5.add(label6);
        phoneField = new JTextField();
        phoneField.setColumns(10);
        panel5.add(phoneField);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rootAddPanel.add(panel6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Gender*");
        panel6.add(label7);
        genderField = new JTextField();
        genderField.setColumns(10);
        panel6.add(genderField);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rootAddPanel.add(panel7, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Diploma theme");
        panel7.add(label8);
        themeOfDiplomaArea = new JTextArea();
        themeOfDiplomaArea.setColumns(10);
        themeOfDiplomaArea.setRows(3);
        panel7.add(themeOfDiplomaArea);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rootAddPanel.add(panel8, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        scientificWorksButton = new JButton();
        scientificWorksButton.setText("Scientific works");
        panel8.add(scientificWorksButton);
        workOnThemasButton = new JButton();
        workOnThemasButton.setText("Work on themas");
        panel8.add(workOnThemasButton);
        saveButton = new JButton();
        saveButton.setText("Save");
        panel8.add(saveButton);
        closeButton = new JButton();
        closeButton.setText("Close");
        panel8.add(closeButton);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rootAddPanel.add(panel9, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        alertText = new JLabel();
        alertText.setBackground(new Color(-6094845));
        alertText.setText("Please fill all mandatory attributes");
        alertText.setVisible(false);
        panel9.add(alertText);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootAddPanel;
    }
}
