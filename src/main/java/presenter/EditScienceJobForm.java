package presenter;

import com.toedter.calendar.JDateChooser;
import dto.ScientistJobDto;
import model.ScienceTheme;
import model.ScientistJob;
import presenter.table.models.ScienceJobTableModel;
import service.MasterService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

public class EditScienceJobForm extends JFrame {
    private JPanel rootAddPanel;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField genderField;
    private JTextArea themeOfDiplomaArea;
    private JPanel startDatePanel;
    private JButton saveButton;
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
    private ScientistJobDto scientistJobDto;

    public EditScienceJobForm(MasterService masterService, JTable rootTable, ScientistJobDto scientistJobDto) {
        this.rootTable = rootTable;
        this.masterService = masterService;
        this.scientistJobDto = scientistJobDto;
        setContentOfThemes();
        extractJobData();
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
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScientistJobDto jobEditDto = combineJobData();
                if (validateInput()) {
                    masterService.updateJobOfMaster(jobEditDto);
                    rootTable.setModel(new ScienceJobTableModel(masterService.getMasterJobs(scientistJobDto.getWorkerId())));
                    dispose();
                }
            }
        });
    }

    private ScientistJobDto combineJobData() {
        ScientistJobDto scientistJobDtoResponse = new ScientistJobDto();
        scientistJobDtoResponse.setName(nameField.getText());
        scientistJobDtoResponse.setStartDate(new Date(startDate.getDate().getTime()));
        if(endDate.getDate() != null){
            scientistJobDtoResponse.setEndDate(new Date(endDate.getDate().getTime()));
        }
        scientistJobDtoResponse.setScienceTheme(new ScienceTheme(scienceThemeBox.getSelectedItem().toString()));
        scientistJobDtoResponse.setId(scientistJobDto.getId());
        scientistJobDtoResponse.setWorkerId(scientistJobDto.getWorkerId());
        return scientistJobDtoResponse;
    }

    private void extractJobData(){
        this.nameField.setText(scientistJobDto.getName());
        endDate.setDate(scientistJobDto.getEndDate());
        startDate.setDate(scientistJobDto.getStartDate());
        scienceThemeBox.setSelectedItem(scientistJobDto.getScienceTheme().getName());
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
        masterService.getScienceThemesValues().forEach(theme -> scienceThemeBox.addItem(theme));
    }
}
