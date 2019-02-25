package presenter;

import com.toedter.calendar.JDateChooser;
import dto.ScientificWorkDto;
import dto.ScientistJobDto;
import model.ScienceTheme;
import presenter.table.models.ScienceJobTableModel;
import presenter.table.models.WorkTableModel;
import service.MasterService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;

public class EditWorkForm extends JFrame {
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
    private ScientificWorkDto scientificWorkDto;

    public EditWorkForm(MasterService masterService, JTable rootTable, ScientificWorkDto scientificWorkDto, String masterId) {
        this.rootTable = rootTable;
        this.masterService = masterService;
        this.masterId = masterId;
        this.scientificWorkDto = scientificWorkDto;
        yearSpinner.setSize(40, 20);
        extractWorkData();
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
                    masterService.updateWork(workDto, EditWorkForm.this.masterId);
                    rootTable.setModel(new WorkTableModel(masterService.getMastersWorks(EditWorkForm.this.masterId)));
                    dispose();
                }
            }
        });
    }

    private ScientificWorkDto combineWorkData() {
        ScientificWorkDto scientificWorkDto = new ScientificWorkDto();
        scientificWorkDto.setId(this.scientificWorkDto.getId());
        scientificWorkDto.setName(nameField.getText());
        scientificWorkDto.setJobType(typeTextField.getText());
        scientificWorkDto.setYearOfJob(Integer.parseInt(yearSpinner.getValue().toString()));
        scientificWorkDto.setThemeNames(list1.getSelectedValuesList());
        scientificWorkDto.setAuthorsNames(list2.getSelectedValuesList());
        return scientificWorkDto;
    }

    private void extractWorkData(){
        nameField.setText(scientificWorkDto.getName());
        typeTextField.setText(scientificWorkDto.getJobType());
        yearSpinner.setValue(scientificWorkDto.getYearOfJob());
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
