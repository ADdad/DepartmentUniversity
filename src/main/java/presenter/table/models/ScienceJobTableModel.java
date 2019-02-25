package presenter.table.models;

import dto.ScientistJobDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ScienceJobTableModel extends AbstractTableModel {


    private static final int MAIN_TABLE_COLUMN_COUNT = 4;
    private List<ScientistJobDto> jobs;

    public ScienceJobTableModel(List<ScientistJobDto> jobs) {
        this.jobs = new ArrayList<>(jobs);
    }

    @Override
    public int getRowCount() {
        return jobs.size();
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
                name = "Name";
                break;
            case 1:
                name = "Start date";
                break;
            case 2:
                name = "End date";
                break;
            case 3:
                name = "Science theme";
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
        }
        return type;
    }

    private String getValueOrEmpty(String value) {
        if (value != null && !value.isEmpty()) return value;
        return "Empty";
    }

    private String getValueOrEmpty(java.sql.Date value) {
        if (value != null) return value.toString();
        return "Empty";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ScientistJobDto job = jobs.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case -3:
                value = job.getId();
                break;
            case -2:
                value = job.getScienceTheme().getId();
                break;
            case -1:
                value = job.getWorkerId();
                break;
            case 0:
                value = getValueOrEmpty(job.getName());
                break;
            case 1:
                value = getValueOrEmpty(job.getStartDate());
                break;
            case 2:
                value = getValueOrEmpty(job.getEndDate());
                break;
            case 3:
                value = job.getScienceTheme().getName();
                break;
        }
        return value;
    }
}