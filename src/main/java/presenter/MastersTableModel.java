package presenter;

import dto.MasterMainDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MastersTableModel extends AbstractTableModel {


    private static final int MAIN_TABLE_COLUMN_COUNT = 7;
    private List<MasterMainDto> masters;

    public MastersTableModel(List<MasterMainDto> masters) {
        this.masters = new ArrayList<>(masters);
    }

    @Override
    public int getRowCount() {
        return masters.size();
    }

    @Override
    public int getColumnCount() {
        return MAIN_TABLE_COLUMN_COUNT;
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
        MasterMainDto master = masters.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = master.getId();
                break;
            case 1:
                value = master.getName();
                break;
            case 2:
                value = master.getGender();
                break;
            case 3:
                value = master.getDiplomaTheme();
                break;
            case 4:
                value = master.getStartOfMaster();
                break;
            case 5:
                value = master.getCathedra().getName();
                break;
            case 6:
                value = master.getChief().getSecondName();
                break;
        }
        return value;
    }
}