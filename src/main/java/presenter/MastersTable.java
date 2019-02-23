package presenter;

import model.Master;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MastersTable {

    public MastersTable(List<Master> masters) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                ClickTableModel model = new ClickTableModel(masters);
                JTable table = new JTable(model);

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new JScrollPane(table));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class ClickTableModel extends AbstractTableModel {

        private static final int MAIN_TABLE_COLUMN_COUNT = 7;
        private List<Master> masters;

        public ClickTableModel(List<Master> masters) {
            this.masters = new ArrayList<>(masters);
        }

        @Override
        public int getRowCount() {
            return masters.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public String getColumnName(int column) {
            String name = "??";
            switch (column) {
                case 0:
                    name = "Id";
                    break;
                case 1:
                    name = "Name";
                    break;
                case 2:
                    name = "Gender";
                    break;
                case 3:
                    name = "Theme";
                    break;
                case 4:
                    name = "Start of master";
                    break;
                case 5:
                    name = "Cathedra";
                    break;
                case 6:
                    name = "Chief";
                    break;
            }
            return name;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Class type = String.class;
            switch (columnIndex) {
                case 0:
                    type = String.class;
                    break;
                case 1:
                    type = String.class;
                    break;
                case 2:
                    type = String.class;
                    break;
                case 3:
                    type = String.class;
                    break;
                case 4:
                    type = String.class;
                    break;
                case 5:
                    type = String.class;
                    break;
                case 6:
                    type = String.class;
                    break;
            }
            return type;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Master master = masters.get(rowIndex);
            Object value = null;
            switch (columnIndex) {
                case 0:
                    value = master.getScientistId();
                    break;
                case 1:
                    value = master.getSecondName();
                    break;
                case 2:
                    value = master.getGender();
                    break;
                case 3:
                    value = master.getDiplomaTheme();
                    break;
                case 4:
                    value = master.getStartDate();
                    break;
                case 5:
                    value = master.getCathedraId();
                    break;
                case 6:
                    value = master.getChiefId();
                    break;
            }
            return value;
        }
    }
}