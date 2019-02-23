package presenter;

import service.MasterService;

import javax.swing.*;

public class MainForm extends JFrame {
    private MasterService masterService;
    private JPanel rootPanel;
    private JTable table1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;
    private JLabel cathedraLabel;
    private JLabel chiefLabel;

    public MainForm(MasterService masterService){
        this.masterService = masterService;
        configTable();
        setContentPane(rootPanel);
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void configTable(){
        MastersTableModel model = new MastersTableModel(masterService.getMastersForMainTable());
        table1.setModel(model);
    }

    private void setContentOfCathedras(){

    }
}
