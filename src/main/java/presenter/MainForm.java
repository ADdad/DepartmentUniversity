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
                if (isRowSelected()) {
                    int n = JOptionPane.showConfirmDialog(
                            rootPanel,
                            "Would you like to delete selected master?",
                            "Delete master",
                            JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        deleteMaster();
                    }
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMasterForm(masterService, table1);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isRowSelected()) {
                    new EditMasterForm(masterService, table1, masterService.getMasterToEdit(getSelectedMasterId()));
                }
            }
        });
    }

    private boolean isRowSelected(){
        return table1.getSelectedRow() != -1;
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

    private String getSelectedMasterId(){
        int row = table1.getSelectedRow();
        return table1.getModel().getValueAt(row, -1).toString();
    }

    private void deleteMaster(){
        masterService.deleteMaster(getSelectedMasterId());
        table1.setModel(new MastersTableModel(masterService.getMastersForMainTable()));
    }
}
