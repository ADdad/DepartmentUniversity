package presenter;

import service.MasterService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private MasterService masterService;
    private JPanel rootPanel;
    private JTable table1;
    private JComboBox cathedrasBox;
    private JComboBox chiefsBox;
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;
    private JLabel cathedraLabel;
    private JLabel chiefLabel;

    public MainForm(MasterService masterService){
        this.masterService = masterService;
        configTable();
        setContentOfCathedras();
        setContentOfChiefs();
        setContentPane(rootPanel);
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int n = JOptionPane.showConfirmDialog(
                        rootPanel,
                        "Would you like to delete selected master?",
                        "Delete master",
                        JOptionPane.YES_NO_OPTION);
                if(n == 0){
                    deleteMaster();
                }
            }
        });
        cathedrasBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cathedra = String.valueOf(cathedrasBox.getSelectedItem());
                String chiefName = String.valueOf(chiefsBox.getSelectedItem());
                table1.setModel(new MastersTableModel(masterService.getFilteredMasters(cathedra, chiefName)));
            }
        });
        chiefsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cathedra = String.valueOf(cathedrasBox.getSelectedItem());
                String chiefName = String.valueOf(chiefsBox.getSelectedItem());
                table1.setModel(new MastersTableModel(masterService.getFilteredMasters(cathedra, chiefName)));
            }
        });
    }

    private void configTable(){
        MastersTableModel model = new MastersTableModel(masterService.getMastersForMainTable());
        table1.setModel(model);
        table1.setRowSelectionInterval(0, 0);
    }

    private void setContentOfCathedras(){
        List<String> cathedrasListForBox = masterService.getCathedrasListForBox();
        cathedrasListForBox.add(0, "");
        cathedrasListForBox.forEach(cathedra -> cathedrasBox.addItem(cathedra));
    }

    private void setContentOfChiefs(){
        List<String> chiefsListForBox = masterService.getChiefsListForBox();
        chiefsListForBox.add(0, "");
        chiefsListForBox.forEach(chief -> chiefsBox.addItem(chief));
    }

    private void deleteMaster(){
        int row = table1.getSelectedRow();
        String value = table1.getModel().getValueAt(row, -1).toString();
        System.out.println(value);
        //masterService.deleteMaster(value);
    }
}
