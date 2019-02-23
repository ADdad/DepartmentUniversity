package presenter;

import dto.MasterMainDto;
import service.MasterService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame {
    private MasterService masterService;

    public MainFrame(MasterService masterService) {
        this.masterService = masterService;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                MastersTableModel model = new MastersTableModel(masterService.getMastersForMainTable());
                JTable table = new JTable(model);

                JFrame frame = new JFrame("Masters");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new JScrollPane(table));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }


}