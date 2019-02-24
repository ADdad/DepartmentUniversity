package presenter;

import com.toedter.calendar.JDateChooser;
import dto.MasterMainDto;
import service.MasterService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
    private Calendar calendar = Calendar.getInstance();
    private JDateChooser startDate = new JDateChooser(calendar.getTime());
    private JDateChooser endDate = new JDateChooser(calendar.getTime());
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
                MasterMainDto masterMainDto = new MasterMainDto();
                masterMainDto.setName(nameField.getText());
                masterMainDto.setGender(genderField.getText());
                masterMainDto.set
                if(validateInput()){

                }
            }
        });
    }

    private boolean validateInput() {

        return false;
    }

    private void setContentOfCathedras() {
        masterService.getCathedrasListForBox().forEach(cathedra -> cathedraBox.addItem(cathedra));
    }

    private void setContentOfChiefs() {
        masterService.getChiefsListForBox().forEach(chief -> chiefsBox.addItem(chief));
    }
}
