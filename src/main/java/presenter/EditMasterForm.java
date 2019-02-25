package presenter;

import com.toedter.calendar.JDateChooser;
import dto.MasterEditDto;
import presenter.table.models.MastersTableModel;
import presenter.table.models.ScienceJobTableModel;
import service.MasterService;

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
        masterService.getCathedrasListForBox().forEach(cathedra -> cathedraBox.addItem(cathedra));
    }

    private void setContentOfChiefs() {
        masterService.getChiefsListForBox().forEach(chief -> chiefsBox.addItem(chief));
    }
}
