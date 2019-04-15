package com.univer.presenter.chuhalova;

import com.univer.model.Cathedra;
import com.univer.model.ScienceTheme;
import com.univer.service.ChuhalovaService;
import com.univer.service.MasterService;
import datechooser.beans.DateChooserCombo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScienceTopic_Form extends javax.swing.JFrame {
    ChuhalovaService chuhalovaService;
    MasterService masterService;
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button delete_st;
    private java.awt.Button edit_st;
    private javax.swing.JLabel finishDate;
    private datechooser.beans.DateChooserCombo finishDate_input;
    private javax.swing.JLabel idST;
    private javax.swing.JTextField idST_input;
    private java.awt.Button insert_st;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel nameCust;
    private javax.swing.JTextField nameCust_input;
    private javax.swing.JLabel nameST;
    private javax.swing.JTextField nameST_input;
    private javax.swing.JComboBox selectdepinst;
    private javax.swing.JLabel startDate;
    private DateChooserCombo startDate_input;
    private javax.swing.JTable table_st;

    public ScienceTopic_Form(MasterService masterService, ChuhalovaService chuhalovaService) {
        this.chuhalovaService = chuhalovaService;
        this.masterService = masterService;
        initComponents();
        Show_Departments();
        Show_In_JTable();
    }

    public void Show_Departments() {
        for (Cathedra s : chuhalovaService.getCathedras()) {
            selectdepinst.addItem(s);
        }
    }

    public boolean checkInputs() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = startDate_input.getSelectedDate();
        String stDate = dateFormat.format(cal.getTime());
        Calendar cal2 = finishDate_input.getSelectedDate();
        String stDate2 = dateFormat.format(cal2.getTime());
        if (
                idST_input.getText().equals("") ||
                        nameST_input.getText().equals("") ||
                        nameCust_input.getText().equals("") |
                                stDate.equals("") ||
                        stDate2.equals("")
        ) {
            return false;
        } else {
            return true;
        }
    }

    public void Show_In_JTable() {
        List<ScienceTheme> list = chuhalovaService.getScienceThemes();
        DefaultTableModel model = (DefaultTableModel) table_st.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[5];
        System.out.println();
        for (int i = 0; i <= list.size() - 1; i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getCustomer();
            row[3] = list.get(i).getStartDate();
            row[4] = list.get(i).getEndDate();
            model.addRow(row);
        }

    }

    public void ShowItem(int index) {
        ScienceTheme scienceTheme = chuhalovaService.getScienceThemes().get(index);
        idST_input.setText(scienceTheme.getId());
        nameST_input.setText(scienceTheme.getName());
        nameCust_input.setText(scienceTheme.getCustomer());
        Date startDate = scienceTheme.getStartDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        startDate_input.setSelectedDate(cal);
        Date finishDate = scienceTheme.getEndDate();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(finishDate);
        finishDate_input.setSelectedDate(cal2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        delete_st = new java.awt.Button();
        startDate_input = new datechooser.beans.DateChooserCombo();
        idST_input = new javax.swing.JTextField();
        nameCust = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_st = new javax.swing.JTable();
        finishDate = new javax.swing.JLabel();
        finishDate_input = new datechooser.beans.DateChooserCombo();
        startDate = new javax.swing.JLabel();
        nameST_input = new javax.swing.JTextField();
        nameCust_input = new javax.swing.JTextField();
        nameST = new javax.swing.JLabel();
        insert_st = new java.awt.Button();
        edit_st = new java.awt.Button();
        idST = new javax.swing.JLabel();
        selectdepinst = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("idScientist");

        delete_st.setLabel("Delete");
        delete_st.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_stActionPerformed(evt);
            }
        });

        nameCust.setText("nameCust");

        table_st.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String[]{
                        "idST", "nameST", "nameCust", "startDate", "finishDate"
                }
        ));
        table_st.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_stMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_st);

        finishDate.setText("finishDate");

        startDate.setText("startDate");

        nameST.setText("nameST");

        insert_st.setLabel("Insert");
        insert_st.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insert_stActionPerformed(evt);
            }
        });

        edit_st.setLabel("Edit");
        edit_st.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_stActionPerformed(evt);
            }
        });

        idST.setText("idST");

        selectdepinst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectdepinstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(finishDate)
                                        .addComponent(startDate)
                                        .addComponent(idST, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nameCust, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nameST, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(idST_input, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                                        .addComponent(nameST_input, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                                        .addComponent(nameCust_input, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                                                .addGap(24, 24, 24)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(delete_st, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(edit_st, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(insert_st, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(finishDate_input, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addComponent(startDate_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                                        .addComponent(selectdepinst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(167, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(43, 43, 43)
                                                        .addComponent(edit_st, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addGap(5, 5, 5)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(insert_st, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(idST_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(idST)))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(nameST_input)
                                                                .addComponent(nameST, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addComponent(selectdepinst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(nameCust_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(nameCust))
                                                        .addComponent(delete_st, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(startDate_input, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                                        .addComponent(startDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(10, 10, 10)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(finishDate_input, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                                        .addComponent(finishDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 101, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1059, Short.MAX_VALUE)
                                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(535, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edit_stActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_stActionPerformed
        if (checkInputs() && !idST.getText().equals("")) {
            ScienceTheme scienceTheme = new ScienceTheme(idST.getText(), nameST_input.getText(),
                    nameCust_input.getText(), new java.sql.Date(startDate_input.getSelectedDate().getTimeInMillis()),
                    new java.sql.Date(finishDate_input.getSelectedDate().getTimeInMillis()));
            chuhalovaService.updateScienceTheme(scienceTheme);
            Show_In_JTable();
            JOptionPane.showMessageDialog(null, "ST Updated");

        } else {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
    }//GEN-LAST:event_edit_stActionPerformed

    private void insert_stActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insert_stActionPerformed
        if (checkInputs() && !idST.getText().equals("")) {
            try {


                Cathedra cathedra = (Cathedra) selectdepinst.getSelectedItem();
                ScienceTheme scienceTheme = new ScienceTheme();
                scienceTheme.setCathedraId(cathedra != null ? cathedra.getId() : null);
                scienceTheme.setName(nameST_input.getText());
                scienceTheme.setCustomer(nameCust_input.getText());
                scienceTheme.setStartDate(new java.sql.Date(startDate_input.getSelectedDate().getTimeInMillis()));
                scienceTheme.setStartDate(new java.sql.Date(finishDate_input.getSelectedDate().getTimeInMillis()));
                chuhalovaService.createScienceTheme(scienceTheme);
                Show_In_JTable();

                JOptionPane.showMessageDialog(null, "Data Inserted");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "One Or More Field Are Empty");
        }
    }//GEN-LAST:event_insert_stActionPerformed

    private void table_stMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_stMouseClicked
        int index = table_st.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_table_stMouseClicked

    private void delete_stActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_stActionPerformed
        if (!idST.getText().equals("")) {
            chuhalovaService.deleteScienceTheme(idST_input.getText());
            Show_In_JTable();
            JOptionPane.showMessageDialog(null, "ST Deleted");

        } else {
            JOptionPane.showMessageDialog(null, "ST Not Deleted : No Id To Delete");
        }
    }//GEN-LAST:event_delete_stActionPerformed

    private void selectdepinstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectdepinstActionPerformed
        Show_In_JTable();
    }//GEN-LAST:event_selectdepinstActionPerformed
    // End of variables declaration//GEN-END:variables
}
