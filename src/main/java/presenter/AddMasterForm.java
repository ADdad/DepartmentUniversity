package presenter;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
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

    public AddMasterForm(){

        setContentPane(rootAddPanel);
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        startDate.setDateFormatString("dd/MM/yyyy");
        endDate.setDateFormatString("dd/MM/yyyy");
        startDatePanel.add(startDate);
        endDatePanel.add(endDate);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
