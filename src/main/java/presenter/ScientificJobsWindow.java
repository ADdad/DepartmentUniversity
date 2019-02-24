package presenter;

import com.toedter.calendar.JDateChooser;
import presenter.table.models.ScienceJobTableModel;
import service.MasterService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
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
    private Calendar calendar = Calendar.getInstance();
    private JDateChooser startDate = new JDateChooser();
    private JDateChooser endDate = new JDateChooser();

    public ScientificJobsWindow(MasterService masterService, String masterId) {
        this.masterService = masterService;
        this.masterId = masterId;
        configTable();
        setContentOfScienceThemes();
        setContentPane(rootPanel);
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Date fields
        startDate.setDateFormatString("dd/MM/yyyy");
        endDate.setDateFormatString("dd/MM/yyyy");
        startDatePanel.add(startDate);
        endDatePanel.add(endDate);

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
        endDate.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        String scienceThemeName = String.valueOf(scienceThemeBox.getSelectedItem());
                        table1.setModel(new ScienceJobTableModel(masterService.getFilteredJobs(scienceThemeName,
                                startDate.getDate(),
                                endDate.getDate(),
                                masterId)));
                    }
                });
        startDate.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        String scienceThemeName = String.valueOf(scienceThemeBox.getSelectedItem());
                        table1.setModel(new ScienceJobTableModel(masterService.getFilteredJobs(scienceThemeName,
                                startDate.getDate(),
                                endDate.getDate(),
                                masterId)));
                    }
                });
        scienceThemeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scienceThemeName = String.valueOf(scienceThemeBox.getSelectedItem());
                table1.setModel(new ScienceJobTableModel(masterService.getFilteredJobs(scienceThemeName,
                        startDate.getDate(),
                        endDate.getDate(),
                        masterId)));
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
                    //  new EditMasterForm(masterService, table1, masterService.getMasterToEdit(getSelectedMasterId()));
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
        ScienceJobTableModel model = new ScienceJobTableModel(masterService.getMasterJobs(masterId));
        table1.setModel(model);
        table1.setRowSelectionInterval(0, 0);
    }

    private void setContentOfScienceThemes() {
        List<String> scienceThemesBox = masterService.getScienceThemesValues();
        scienceThemesBox.add(0, "");
        scienceThemesBox.forEach(chief -> chiefsBox.addItem(chief));
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
