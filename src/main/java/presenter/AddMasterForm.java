package presenter;

import com.toedter.calendar.JDateChooser;
import dto.MasterEditDto;
import service.MasterService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

public class AddMasterForm extends JFrame {
    private JPanel rootAddPanel;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField genderField;
    private JTextField themeOfDiplomaField;
    private JPanel startDatePanel;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel secondPanel;
    private JPanel endDatePanel;
    private JTextField endReasonField;
    private JComboBox cathedraBox;
    private JComboBox chiefsBox;
    private JLabel en;
    private JLabel alertText;
    private Calendar calendar = Calendar.getInstance();
    private JDateChooser startDate = new JDateChooser(calendar.getTime());
    private JDateChooser endDate = new JDateChooser();
    private MasterService masterService;

    public AddMasterForm(MasterService masterService) {
        this.masterService = masterService;
        setContentOfCathedras();
        setContentOfChiefs();
        setContentPane(rootAddPanel);
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        alertText.setForeground(Color.red);

        //Date fields
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
                MasterEditDto masterEditDto1 = combineMasterData();
                if (validateInput()) {
                    masterService.createMaster(masterEditDto1);
                }
            }
        });
    }

    private MasterEditDto combineMasterData() {
        MasterEditDto masterEditDto = new MasterEditDto();
        masterEditDto.setName(nameField.getText());
        masterEditDto.setGender(genderField.getText());
        masterEditDto.setPhone(phoneField.getText());
        masterEditDto.setThemeOfDiploma(themeOfDiplomaField.getText());
        masterEditDto.setStartDate(new Date(startDate.getDate().getTime()));
        if(endDate.getDate() != null) {
            masterEditDto.setEndDate(new Date(endDate.getDate().getTime()));
        }
        masterEditDto.setEndReason(endReasonField.getText());
        masterEditDto.setCathedraName(cathedraBox.getSelectedItem().toString());
        masterEditDto.setChief(chiefsBox.getSelectedItem().toString());
        return masterEditDto;
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
        if(!validator)alertText.setVisible(true);
        return validator;
    }

    private void setContentOfCathedras() {
        masterService.getCathedrasListForBox().forEach(cathedra -> cathedraBox.addItem(cathedra));
    }

    private void setContentOfChiefs() {
        masterService.getChiefsListForBox().forEach(chief -> chiefsBox.addItem(chief));
    }
}
