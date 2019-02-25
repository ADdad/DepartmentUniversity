package com.univer.presenter;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.toedter.calendar.JDateChooser;
import com.univer.dto.ScientistJobDto;
import com.univer.model.ScienceTheme;
import com.univer.presenter.table.models.ScienceJobTableModel;
import com.univer.service.MasterService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

public class AddScientistJobForm extends JFrame {
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
    private Calendar calendar = Calendar.getInstance();
    private JDateChooser startDate = new JDateChooser(calendar.getTime());
    private JDateChooser endDate = new JDateChooser();
    private MasterService masterService;
    private JTable rootTable;
    private String masterId;

    public AddScientistJobForm(MasterService masterService, JTable rootTable, String masterId) {
        this.rootTable = rootTable;
        this.masterService = masterService;
        this.masterId = masterId;
        setContentOfThemes();
        setContentPane(rootAddPanel);
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        alertText.setForeground(Color.red);

        // Date fields
        startDate.setDateFormatString("dd/MM/yyyy");
        endDate.setDateFormatString("dd/MM/yyyy");
        startDatePanel.add(startDate);
        endDatePanel.add(endDate);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScientistJobDto jobEditDto = combineJobData();
                if (validateInput()) {
                    masterService.addJobToMaster(jobEditDto);
                    rootTable.setModel(new ScienceJobTableModel(masterService.getMasterJobs(masterId)));
                    dispose();
                }
            }
        });
    }

    private ScientistJobDto combineJobData() {
        ScientistJobDto scientistJobDto = new ScientistJobDto();
        scientistJobDto.setName(nameField.getText());
        scientistJobDto.setStartDate(new Date(startDate.getDate().getTime()));
        if (endDate.getDate() != null) {
            scientistJobDto.setEndDate(new Date(endDate.getDate().getTime()));
        }
        scientistJobDto.setWorkerId(masterId);
        scientistJobDto.setScienceTheme(new ScienceTheme(scienceThemeBox.getSelectedItem().toString()));
        return scientistJobDto;
    }

    private boolean validateInput() {
        boolean validator = true;
        if (this.nameField.getText().isEmpty()) {
            validator = false;
            nameField.setBorder(new LineBorder(Color.red, 1));
        }
        if (this.endDate.getDate() != null && startDate.getDate().after(endDate.getDate())) {
            validator = false;
            nameField.setBorder(new LineBorder(Color.red, 1));
        }
        if (!validator)
            alertText.setVisible(true);
        return validator;
    }

    private void setContentOfThemes() {
        // masterService.getScienceThemesValues().forEach(theme -> scienceThemeBox.addItem(theme));
        for (String s :
                masterService.getScienceThemesValues()) {
            scienceThemeBox.addItem(s);
        }
    }

}
