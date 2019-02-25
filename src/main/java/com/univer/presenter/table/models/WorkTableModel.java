package com.univer.presenter.table.models;

import com.univer.dto.ScientificWorkDto;
import com.univer.model.ScienceTheme;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class WorkTableModel extends AbstractTableModel {


    private static final int MAIN_TABLE_COLUMN_COUNT = 4;
    private List<ScientificWorkDto> works;

    public WorkTableModel(List<ScientificWorkDto> works) {
        this.works = new ArrayList<>(works);
    }

    @Override
    public int getRowCount() {
        return works.size();
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
                name = "Type";
                break;
            case 2:
                name = "Year";
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
                type = String[].class;
                break;
        }
        return type;
    }

    private String getValueOrEmpty(String value) {
        if (value != null && !value.isEmpty()) return value;
        return "Empty";
    }

    private String getValueOrEmpty(int value) {
        if (value != 0) return "" + value;
        return "Empty";
    }

    private String[] getValues(List<ScienceTheme> scienceThemes) {
        String[] result = new String[scienceThemes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = scienceThemes.get(i).getName();
        }
        return result;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ScientificWorkDto work = works.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case -1:
                value = work.getId();
                break;
            case 0:
                value = getValueOrEmpty(work.getName());
                break;
            case 1:
                value = getValueOrEmpty(work.getJobType());
                break;
            case 2:
                value = getValueOrEmpty(work.getYearOfJob());
                break;
            case 3:
                value = getValues(work.getScienceThemes());
                break;
        }
        return value;
    }
}