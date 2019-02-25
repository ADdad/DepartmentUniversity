package presenter;

import com.toedter.calendar.JDateChooser;
import presenter.table.models.MultiLineTableCellRenderer;
import presenter.table.models.ScienceJobTableModel;
import presenter.table.models.WorkTableModel;
import service.MasterService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.List;

public class ScientificWorksWindow extends JFrame {
    private MasterService masterService;
    private JPanel rootPanel;
    private JTable table1;
    private JComboBox cathedrasBox;
    private JComboBox chiefsBox;
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;
    private JPanel startDatePanel;
    private JPanel endDatePanel;
    private JComboBox scienceThemeBox;
    private JButton backButton;
    private JComboBox yearsBox;
    private String masterId;

    public ScientificWorksWindow(MasterService masterService, String masterId) {
        this.masterService = masterService;
        this.masterId = masterId;
        configTable();
        setContentOfScienceThemes();
        setContentOfYearsBox();
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
                            "Would you like to delete selected work?",
                            "Delete job",
                            JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        deleteJob();
                    }
                }
            }
        });
        scienceThemeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scienceThemeName = String.valueOf(scienceThemeBox.getSelectedItem());
                String year = String.valueOf(yearsBox.getSelectedItem());
                table1.setModel(new WorkTableModel(masterService.getFilteredWorks(scienceThemeName, year, masterId)));
            }
        });
        yearsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scienceThemeName = String.valueOf(scienceThemeBox.getSelectedItem());
                String year = String.valueOf(yearsBox.getSelectedItem());
                table1.setModel(new WorkTableModel(masterService.getFilteredWorks(scienceThemeName, year, masterId)));
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddScientistJobForm(masterService, table1, masterId);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRowSelected()) {
                    //  new EditScienceJobForm(masterService, table1, masterService.getWorkToEdit(getSelectedWorkId()));
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private boolean isRowSelected() {
        return table1.getSelectedRow() != -1;
    }

    private void configTable() {
        WorkTableModel model = new WorkTableModel(masterService.getMastersWorks(masterId));
        table1.setModel(model);
        MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
        table1.setDefaultRenderer(String[].class, renderer);
        table1.setRowHeight(100);
        if(table1.getModel().getRowCount() > 0) table1.setRowSelectionInterval(0, 0);
    }

    private void setContentOfScienceThemes() {
        List<String> scienceThemesBox = masterService.getScienceThemesValues();
        scienceThemesBox.add(0, "");
        scienceThemesBox.forEach(chief -> scienceThemeBox.addItem(chief));
    }

    private void setContentOfYearsBox() {
        List<String> yearsValues = masterService.getYearsValues(masterId);
        yearsValues.add(0, "");
        yearsValues.forEach(year -> yearsBox.addItem(year));
    }

    private String getSelectedWorkId() {
        int row = table1.getSelectedRow();
        return table1.getModel().getValueAt(row, -1).toString();
    }

    private void deleteJob() {
        masterService.deleteMasterJob(getSelectedWorkId());
        table1.setModel(new WorkTableModel(masterService.getMastersWorks(masterId)));
    }
}
