package presenter;

import presenter.table.models.ScienceJobTableModel;
import service.MasterService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScientificJobsWindow extends JFrame {
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
    private JPanel startDatePanel;
    private JPanel endDatePanel;
    private JComboBox scienceThemeBox;
    private JButton backButton;
    private String masterId;

    public ScientificJobsWindow(MasterService masterService, String masterId) {
        this.masterService = masterService;
        this.masterId = masterId;
        configTable();
        setContentOfCathedras();
        setContentOfChiefs();
        setContentPane(rootPanel);
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRowSelected()) {
                    int n = JOptionPane.showConfirmDialog(
                            rootPanel,
                            "Would you like to delete selected job?",
                            "Delete job",
                            JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        deleteJob();
                    }
                }
            }
        });
        cathedrasBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cathedra = String.valueOf(cathedrasBox.getSelectedItem());
                String chiefName = String.valueOf(chiefsBox.getSelectedItem());
                table1.setModel(new ScienceJobTableModel(masterService.getFilteredMasters(cathedra, chiefName)));
            }
        });
        chiefsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cathedra = String.valueOf(cathedrasBox.getSelectedItem());
                String chiefName = String.valueOf(chiefsBox.getSelectedItem());
                table1.setModel(new ScienceJobTableModel(masterService.getFilteredMasters(cathedra, chiefName)));
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
                if (isRowSelected()) {
                    new EditMasterForm(masterService, table1, masterService.getMasterToEdit(getSelectedMasterId()));
                }
            }
        });
    }

    private boolean isRowSelected() {
        return table1.getSelectedRow() != -1;
    }

    private void configTable() {
        ScienceJobTableModel model = new ScienceJobTableModel(masterService.getMasterJobs(masterId));
        table1.setModel(model);
        table1.setRowSelectionInterval(0, 0);
    }

    private void setContentOfCathedras() {
        List<String> cathedrasListForBox = masterService.getCathedrasListForBox();
        cathedrasListForBox.add(0, "");
        cathedrasListForBox.forEach(cathedra -> cathedrasBox.addItem(cathedra));
    }

    private void setContentOfChiefs() {
        List<String> chiefsListForBox = masterService.getChiefsListForBox();
        chiefsListForBox.add(0, "");
        chiefsListForBox.forEach(chief -> chiefsBox.addItem(chief));
    }

    private String getSelectedJobThemeId() {
        int row = table1.getSelectedRow();
        return table1.getModel().getValueAt(row, -2).toString();
    }

    private void deleteJob() {
        masterService.deleteMasterJob(masterId, getSelectedJobThemeId());
        table1.setModel(new ScienceJobTableModel(masterService.getMasterJobs(masterId)));
    }
}
