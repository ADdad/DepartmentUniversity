package com.univer.presenter;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.univer.dto.ScientificWorkDto;
import com.univer.presenter.table.models.WorkTableModel;
import com.univer.service.MasterService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWorkForm extends JFrame {
    private JPanel rootAddPanel;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField genderField;
    private JTextArea themeOfDiplomaArea;
    private JPanel startDatePanel;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel endDatePanel;
    private JTextField endReasonField;
    private JComboBox scienceThemeBox;
    private JComboBox chiefsBox;
    private JLabel alertText;
    private JTextField typeTextField;
    private JSpinner yearSpinner;
    private JList list1;
    private JList list2;
    private MasterService masterService;
    private JTable rootTable;
    private String masterId;

    public AddWorkForm(MasterService masterService, JTable rootTable, String masterId) {
        this.rootTable = rootTable;
        this.masterService = masterService;
        this.masterId = masterId;
        yearSpinner.setSize(40, 20);
        setContentOfThemes();
        setContentOfAuthors();
        list1.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list2.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        setContentPane(rootAddPanel);
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        alertText.setForeground(Color.red);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScientificWorkDto workDto = combineWorkData();
                if (validateInput()) {
                    masterService.addWorkToMaster(workDto, masterId);
                    rootTable.setModel(new WorkTableModel(masterService.getMastersWorks(masterId)));
                    dispose();
                }
            }
        });
    }

    private ScientificWorkDto combineWorkData() {
        ScientificWorkDto scientificWorkDto = new ScientificWorkDto();
        scientificWorkDto.setName(nameField.getText());
        scientificWorkDto.setJobType(typeTextField.getText());
        scientificWorkDto.setYearOfJob(Integer.parseInt(yearSpinner.getValue().toString()));
        scientificWorkDto.setThemeNames(list1.getSelectedValuesList());
        scientificWorkDto.setAuthorsNames(list2.getSelectedValuesList());
        return scientificWorkDto;
    }

    private boolean validateInput() {
        boolean validator = true;
        if (nameField.getText().isEmpty()) {
            validator = false;
            nameField.setBorder(new LineBorder(Color.red, 1));
        }
        if (typeTextField.getText().isEmpty()) {
            validator = false;
            typeTextField.setBorder(new LineBorder(Color.red, 1));
        }
        if (list1.getSelectedValuesList().size() < 1) {
            validator = false;
            list1.setBorder(new LineBorder(Color.red, 1));
        }
        if (!validator)
            alertText.setVisible(true);
        return validator;
    }

    private void setContentOfThemes() {
        list1.setListData(masterService.getScienceThemesValues().toArray());
    }

    private void setContentOfAuthors() {
        list2.setListData(masterService.getAuthors(masterId).toArray());
    }

}
