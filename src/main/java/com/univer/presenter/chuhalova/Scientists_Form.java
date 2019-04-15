//package com.univer.presenter.chuhalova;
//
//import com.mysql.jdbc.Connection;
//import com.univer.model.Cathedra;
//import com.univer.model.Master;
//import com.univer.model.Postgraduate;
//import com.univer.model.Scientist;
//import com.univer.model.dto.MasterMainDto;
//import com.univer.service.ChuhalovaService;
//import com.univer.service.MasterService;
//
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.text.ParseException;
//import javax.swing.JOptionPane;
//
//import javax.swing.table.DefaultTableModel;
//public class Scientists_Form extends javax.swing.JFrame {
//
//    private ChuhalovaService chuhalovaService;
//    private MasterService masterService;
//    public Scientists_Form(ChuhalovaService chuhalovaService, MasterService masterService) {
//        this.chuhalovaService = chuhalovaService;
//        this.masterService = masterService;
//        initComponents();
//        Show_Departments();
//        Show_Masters_In_JTable();
//        Show_Asp_In_JTable();
//        Show_Prof_In_JTable();
//    }
//    public boolean checkInputs()
//    {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                Calendar cal = start_date_mas.getSelectedDate();
//                String stDate = dateFormat.format(cal.getTime());
//                Calendar cal2 = finish_date_mas.getSelectedDate();
//                String stDate2 = dateFormat.format(cal2.getTime());
//        if(
//              id_mas.getText().equals("")||
//              surname_mas.getText().equals("")||
//              tel_mas.getText().equals("")||
//              sex_mas.getText().equals("")||
//              topic_mas.getText().equals("")||
//              reason_mas.getText().equals("")||
//              stDate.equals("")||
//              stDate2.equals("")
//          ){
//            return false;
//            }
//        else{
//                return true;
//        }
//    }
//
//        public boolean checkInputsAsp()
//    {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                Calendar cal = start_date_asp.getSelectedDate();
//                String stDate = dateFormat.format(cal.getTime());
//                Calendar cal2 = finish_date_asp.getSelectedDate();
//                String stDate2 = dateFormat.format(cal2.getTime());
//                Calendar cal3 = start_work_prof.getSelectedDate();
//                String stDate3 = dateFormat.format(cal3.getTime());
//        if(
//              id_asp.getText().equals("")||
//              surname_asp.getText().equals("")||
//              tel_asp.getText().equals("")||
//              sex_asp.getText().equals("")||
//              topic_asp.getText().equals("")||
//              stDate.equals("")||
//              stDate2.equals("")||
//              stDate3.equals("")
//          ){
//            return false;
//            }
//        else{
//                return true;
//        }
//    }
//
//    public boolean checkInputsProf()
//    {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                Calendar cal = start_work_prof.getSelectedDate();
//                String stDate = dateFormat.format(cal.getTime());
//        if(
//              id_prof.getText().equals("")||
//              surname_prof.getText().equals("")||
//              tel_prof.getText().equals("")||
//              sex_prof.getText().equals("")||
//              rank_prof.getText().equals("")||
//              position_prof.getText().equals("")||
//              stDate.equals("")
//          ){
//            return false;
//            }
//        else{
//                return true;
//        }
//    }
//
//
//
//
//        public void Show_Departments() {
//            for (Cathedra s : chuhalovaService.getCathedras()) {
//                selectdepforscientists.addItem(s);
//            }
//        }
//
//        public void Show_Masters_In_JTable()
//    {
//        List<Master> mastersForMainTable = chuhalovaService.getMasters();
//        DefaultTableModel model = (DefaultTableModel) table_mas.getModel();
//        // clear jtable content
//        model.setRowCount(0);
//        Object[] row = new Object[8];
//        for(Master master : mastersForMainTable)
//        {
//            row[0] = master.getScientistId();
//            row[1] = master.getSecondName();
//            row[2] = master.getPhoneNumber();
//            row[3] = master.getGender();
//            row[4] = master.getDiplomaTheme();
//            row[5] = master.getStartDate();
//            row[6] = master.getEndDate();
//            row[7] = master.getEndReason();
//            model.addRow(row);
//        }
//
//    }
//
//    public void Show_Asp_In_JTable()
//    {
//        List<Postgraduate> allPostgraduates = chuhalovaService.getAllPostgraduates();
//        DefaultTableModel model = (DefaultTableModel) table_asp.getModel();
//        // clear jtable content
//        model.setRowCount(0);
//        Object[] row = new Object[8];
//        for(Postgraduate postgraduate : allPostgraduates)
//        {
//            row[0] = postgraduate.getScientistId();
//            row[1] = postgraduate.getSecondName();
//            row[2] = postgraduate.getPhoneNumber();
//            row[3] = postgraduate.getGender();
//            row[4] = postgraduate.getThesisTheme();
//            row[5] = postgraduate.getStartDate();
//            row[6] = postgraduate.getEndDate();
//            row[7] = postgraduate.getThesisProtectionDate();
//            model.addRow(row);
//        }
//
//    }
//
//        public void Show_Prof_In_JTable()
//    {
//
//        DefaultTableModel model = (DefaultTableModel) table_prof.getModel();
//        // clear jtable content
//        model.setRowCount(0);
//        Object[] row = new Object[7];
//        System.out.println();
//        for(int i = 0; i <= list.size() - 1; i++)
//        {
//            row[0] = list.get(i).getScientistId();
//            row[1] = list.get(i).getSecondName();
//            row[2] = list.get(i).getPhoneNumber();
//            row[3] = list.get(i).getGender();
//            row[4] = list.get(i).getPositionProf();
//            row[5] = list.get(i).getRankProf();
//            row[6] = list.get(i).getStartWorkProf();
//            model.addRow(row);
//        }
//
//    }
//
//         public void ShowItem(int index) {
//            id_mas.setText(getMasterList().get(index).getScientistId());
//            surname_mas.setText(getMasterList().get(index).getSecondName());
//            sex_mas.setText(getMasterList().get(index).getGender());
//            tel_mas.setText(getMasterList().get(index).getPhoneNumber());
//            reason_mas.setText(getMasterList().get(index).getFinishingReasonMas());
//
//        try {
//            Date startDateMas = new SimpleDateFormat("yyyy-MM-dd").parse(getMasterList().get(index).getStartDateMas());
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(startDateMas);
//            start_date_mas.setSelectedDate(cal);
//            Date finishDateMas = new SimpleDateFormat("yyyy-MM-dd").parse(getMasterList().get(index).getEndDateMas());
//            Calendar cal2 = Calendar.getInstance();
//            cal2.setTime(finishDateMas);
//            finish_date_mas.setSelectedDate(cal2);
//        } catch (ParseException ex) {
//            // Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void ShowItemAsp(int index)
//    {
//            id_asp.setText(getAspList().get(index).getScientistId());
//            surname_asp.setText(getAspList().get(index).getSecondName());
//            sex_asp.setText(getAspList().get(index).getGender());
//            tel_asp.setText(getAspList().get(index).getPhoneNumber());
//            try {
//            Date startDateAsp = new SimpleDateFormat("yyyy-MM-dd").parse(getAspList().get(index).getStartDateAsp());
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(startDateAsp);
//            start_date_asp.setSelectedDate(cal);
//            Date finishDateAsp = new SimpleDateFormat("yyyy-MM-dd").parse(getAspList().get(index).getEndDateAsp());
//            Calendar cal2 = Calendar.getInstance();
//            cal2.setTime(finishDateAsp);
//            finish_date_asp.setSelectedDate(cal2);
//        } catch (ParseException ex) {
//            // Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//        public void ShowItemProf(int index)
//    {
//            id_prof.setText(getProfList().get(index).getScientistId());
//            surname_prof.setText(getProfList().get(index).getSecondName());
//            sex_prof.setText(getProfList().get(index).getGender());
//            tel_prof.setText(getProfList().get(index).getPhoneNumber());
//            position_prof.setText(getProfList().get(index).getPositionProf());
//            rank_prof.setText(getProfList().get(index).getRankProf());
//            try {
//            Date startDateProf = new SimpleDateFormat("yyyy-MM-dd").parse(getProfList().get(index).getStartWorkProf());
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(startDateProf);
//            start_work_prof.setSelectedDate(cal);
//        } catch (ParseException ex) {
//            // Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//
//    @SuppressWarnings("unchecked")
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
//    private void initComponents() {
//
//        jScrollPane4 = new javax.swing.JScrollPane();
//        jPanel1 = new javax.swing.JPanel();
//        reason_mas = new javax.swing.JTextField();
//        insert_prof = new java.awt.Button();
//        delete_mas = new java.awt.Button();
//        edit_prof = new java.awt.Button();
//        jLabel12 = new javax.swing.JLabel();
//        start_date_mas = new datechooser.beans.DateChooserCombo();
//        delete_prof = new java.awt.Button();
//        jLabel16 = new javax.swing.JLabel();
//        topic_mas = new javax.swing.JTextField();
//        id_prof = new javax.swing.JTextField();
//        sex_prof = new javax.swing.JTextField();
//        rank_prof = new javax.swing.JTextField();
//        id_mas = new javax.swing.JTextField();
//        insert_asp = new java.awt.Button();
//        jLabel21 = new javax.swing.JLabel();
//        surname_prof = new javax.swing.JTextField();
//        tel_asp = new javax.swing.JTextField();
//        jScrollPane1 = new javax.swing.JScrollPane();
//        table_prof = new javax.swing.JTable();
//        jLabel3 = new javax.swing.JLabel();
//        jLabel11 = new javax.swing.JLabel();
//        jLabel13 = new javax.swing.JLabel();
//        jScrollPane2 = new javax.swing.JScrollPane();
//        table_mas = new javax.swing.JTable();
//        jLabel14 = new javax.swing.JLabel();
//        jLabel20 = new javax.swing.JLabel();
//        sex_mas = new javax.swing.JTextField();
//        jLabel7 = new javax.swing.JLabel();
//        jLabel10 = new javax.swing.JLabel();
//        finish_date_mas = new datechooser.beans.DateChooserCombo();
//        jLabel6 = new javax.swing.JLabel();
//        jLabel15 = new javax.swing.JLabel();
//        tel_prof = new javax.swing.JTextField();
//        jLabel23 = new javax.swing.JLabel();
//        surname_mas = new javax.swing.JTextField();
//        delete_asp = new java.awt.Button();
//        tel_mas = new javax.swing.JTextField();
//        jScrollPane3 = new javax.swing.JScrollPane();
//        table_asp = new javax.swing.JTable();
//        jLabel8 = new javax.swing.JLabel();
//        sex_asp = new javax.swing.JTextField();
//        jLabel2 = new javax.swing.JLabel();
//        insert_mas = new java.awt.Button();
//        edit_asp = new java.awt.Button();
//        jLabel17 = new javax.swing.JLabel();
//        topic_asp = new javax.swing.JTextField();
//        jLabel9 = new javax.swing.JLabel();
//        jLabel5 = new javax.swing.JLabel();
//        position_prof = new javax.swing.JTextField();
//        edit_mas = new java.awt.Button();
//        jLabel18 = new javax.swing.JLabel();
//        jLabel22 = new javax.swing.JLabel();
//        id_asp = new javax.swing.JTextField();
//        jLabel4 = new javax.swing.JLabel();
//        jLabel1 = new javax.swing.JLabel();
//        jLabel19 = new javax.swing.JLabel();
//        surname_asp = new javax.swing.JTextField();
//        start_date_asp = new datechooser.beans.DateChooserCombo();
//        finish_date_asp = new datechooser.beans.DateChooserCombo();
//        start_work_prof = new datechooser.beans.DateChooserCombo();
//        present_date_asp1 = new datechooser.beans.DateChooserCombo();
//        selectdepforscientists = new javax.swing.JComboBox();
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        setTitle("idScientist");
//
//        insert_prof.setLabel("Insert");
//        insert_prof.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                insert_profActionPerformed(evt);
//            }
//        });
//
//        delete_mas.setLabel("Delete");
//        delete_mas.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                delete_masActionPerformed(evt);
//            }
//        });
//
//        edit_prof.setLabel("Edit");
//        edit_prof.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                edit_profActionPerformed(evt);
//            }
//        });
//
//        jLabel12.setText("Sex");
//
//        delete_prof.setLabel("Delete");
//        delete_prof.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                delete_profActionPerformed(evt);
//            }
//        });
//
//        jLabel16.setText("Present date");
//
//        insert_asp.setLabel("Insert");
//        insert_asp.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                insert_aspActionPerformed(evt);
//            }
//        });
//
//        jLabel21.setText("Position");
//
//        table_prof.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {
//                {null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null}
//            },
//            new String [] {
//                "id", "surname", "tel", "sex", "position", "rank", "start"
//            }
//        ));
//        table_prof.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                table_profMouseClicked(evt);
//            }
//        });
//        jScrollPane1.setViewportView(table_prof);
//
//        jLabel3.setText("Tel");
//
//        jLabel11.setText("Tel");
//
//        jLabel13.setText("Topic Asp");
//
//        table_mas.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {
//                {null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null}
//            },
//            new String [] {
//                "id", "surname", "tel", "sex", "topic", "start", "finish", "reason"
//            }
//        ));
//        table_mas.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                table_masMouseClicked(evt);
//            }
//        });
//        jScrollPane2.setViewportView(table_mas);
//
//        jLabel14.setText("Start date ");
//
//        jLabel20.setText("Sex");
//
//        jLabel7.setText("Finish date");
//
//        jLabel10.setText("Surname");
//
//        jLabel6.setText("Start date ");
//
//        jLabel15.setText("Finish date");
//
//        jLabel23.setText("Start work");
//
//        delete_asp.setLabel("Delete");
//        delete_asp.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                delete_aspActionPerformed(evt);
//            }
//        });
//
//        table_asp.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {
//                {null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null}
//            },
//            new String [] {
//                "id", "surname", "tel", "sex", "topic", "start", "finish", "present"
//            }
//        ));
//        table_asp.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                table_aspMouseClicked(evt);
//            }
//        });
//        jScrollPane3.setViewportView(table_asp);
//        if (table_asp.getColumnModel().getColumnCount() > 0) {
//            table_asp.getColumnModel().getColumn(7).setHeaderValue("start");
//        }
//
//        jLabel8.setText("Reason of ending");
//
//        jLabel2.setText("Surname");
//
//        insert_mas.setLabel("Insert");
//        insert_mas.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                insert_masActionPerformed(evt);
//            }
//        });
//
//        edit_asp.setLabel("Edit");
//        edit_asp.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                edit_aspActionPerformed(evt);
//            }
//        });
//
//        jLabel17.setText("ID");
//
//        jLabel9.setText("ID");
//
//        jLabel5.setText("Topic Master");
//
//        edit_mas.setLabel("Edit");
//        edit_mas.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                edit_masActionPerformed(evt);
//            }
//        });
//
//        jLabel18.setText("Surname");
//
//        jLabel22.setText("Rank");
//
//        jLabel4.setText("Sex");
//
//        jLabel1.setText("ID");
//
//        jLabel19.setText("Tel");
//
//        selectdepforscientists.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                selectdepforscientistsActionPerformed(evt);
//            }
//        });
//
//        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
//        jPanel1.setLayout(jPanel1Layout);
//        jPanel1Layout.setHorizontalGroup(
//            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(jPanel1Layout.createSequentialGroup()
//                .addContainerGap()
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(jPanel1Layout.createSequentialGroup()
//                        .addGap(1, 1, 1)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addComponent(jLabel13)
//                            .addComponent(jLabel12)
//                            .addComponent(jLabel11)
//                            .addComponent(jLabel10)
//                            .addComponent(jLabel9)
//                            .addComponent(jLabel8)
//                            .addComponent(jLabel7)
//                            .addComponent(jLabel6)
//                            .addComponent(jLabel5)
//                            .addComponent(jLabel4)
//                            .addComponent(jLabel3)
//                            .addComponent(jLabel2)
//                            .addComponent(jLabel1)))
//                    .addComponent(jLabel15)
//                    .addComponent(jLabel16)
//                    .addComponent(jLabel17)
//                    .addComponent(jLabel18)
//                    .addComponent(jLabel19)
//                    .addComponent(jLabel20)
//                    .addComponent(jLabel21)
//                    .addComponent(jLabel22)
//                    .addComponent(jLabel23)
//                    .addComponent(jLabel14))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                        .addComponent(id_mas, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(surname_mas, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(sex_mas, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(topic_mas, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(position_prof, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(tel_prof, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(id_prof, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(id_asp, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(tel_asp, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(topic_asp, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(tel_mas, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(surname_asp, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(surname_prof, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(sex_asp, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(sex_prof, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(rank_prof, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addGroup(jPanel1Layout.createSequentialGroup()
//                            .addGap(6, 6, 6)
//                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                .addComponent(finish_date_mas, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addComponent(start_date_mas, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                        .addComponent(reason_mas, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
//                        .addComponent(start_date_asp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addComponent(finish_date_asp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addComponent(start_work_prof, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                    .addComponent(present_date_asp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addGap(24, 24, 24)
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(insert_asp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(edit_asp, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(delete_asp, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addGroup(jPanel1Layout.createSequentialGroup()
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addComponent(edit_prof, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
//                            .addComponent(delete_prof, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
//                            .addComponent(delete_mas, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
//                            .addComponent(edit_mas, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
//                            .addComponent(insert_mas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                            .addComponent(insert_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                            .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addGap(52, 52, 52)
//                                .addComponent(selectdepforscientists, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(0, 0, Short.MAX_VALUE)))))
//                .addContainerGap())
//        );
//        jPanel1Layout.setVerticalGroup(
//            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(jPanel1Layout.createSequentialGroup()
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
//                        .addGap(12, 12, 12)
//                        .addComponent(selectdepforscientists, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
//                    .addGroup(jPanel1Layout.createSequentialGroup()
//                        .addContainerGap()
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                            .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                    .addComponent(insert_mas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                        .addComponent(id_mas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                        .addComponent(jLabel1)))
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                    .addComponent(surname_mas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                    .addComponent(jLabel2)))
//                            .addComponent(edit_mas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                .addComponent(tel_mas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addComponent(jLabel3))
//                            .addComponent(delete_mas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                            .addComponent(jLabel4)
//                            .addComponent(sex_mas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                            .addComponent(jLabel5)
//                            .addComponent(topic_mas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addComponent(jLabel6)
//                            .addComponent(start_date_mas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addComponent(jLabel7)
//                            .addComponent(finish_date_mas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                            .addComponent(jLabel8)
//                            .addComponent(reason_mas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
//                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                            .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                                    .addGroup(jPanel1Layout.createSequentialGroup()
//                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                                            .addComponent(jLabel9)
//                                            .addComponent(id_asp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                            .addComponent(insert_asp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                            .addComponent(jLabel10)
//                                            .addComponent(surname_asp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                                    .addComponent(edit_asp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                    .addComponent(jLabel11)
//                                    .addComponent(tel_asp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                            .addComponent(delete_asp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                            .addComponent(jLabel12)
//                            .addComponent(sex_asp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                            .addComponent(jLabel13)
//                            .addComponent(topic_asp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                            .addComponent(jLabel14)
//                            .addComponent(start_date_asp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addGap(18, 18, 18)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                            .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addComponent(jLabel15)
//                                .addGap(18, 18, 18)
//                                .addComponent(jLabel16)
//                                .addGap(0, 0, Short.MAX_VALUE))
//                            .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addComponent(finish_date_asp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                                .addComponent(present_date_asp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(26, 26, 26))))
//                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addGap(15, 15, 15)
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(jPanel1Layout.createSequentialGroup()
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                .addComponent(jLabel17)
//                                .addComponent(id_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                            .addComponent(insert_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                            .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                    .addComponent(jLabel18)
//                                    .addComponent(surname_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                    .addComponent(jLabel19)
//                                    .addComponent(tel_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                            .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addComponent(edit_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addComponent(delete_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                            .addComponent(jLabel20)
//                            .addComponent(sex_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                            .addComponent(jLabel21)
//                            .addComponent(position_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                            .addComponent(jLabel22)
//                            .addComponent(rank_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addComponent(jLabel23)
//                            .addComponent(start_work_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addGap(0, 0, 0))
//        );
//
//        jScrollPane4.setViewportView(jPanel1);
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1094, Short.MAX_VALUE)
//                .addContainerGap())
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(262, Short.MAX_VALUE))
//        );
//
//        pack();
//    }// </editor-fold>//GEN-END:initComponents
//
//    private void insert_aspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insert_aspActionPerformed
//
//        if(checkInputsAsp()&&!id_asp.getText().equals("")){
//            try {
//                Connection con = getConnection();
//                Department dep = (Department) selectdepforscientists.getSelectedItem();
//                PreparedStatement ps = con.prepareStatement("INSERT INTO Scientist(idScientist, surname, tel, sex,topicAsp, startDateAsp, finishDateAsp, presentDateAsp, roleScientist, idDep)"
//                        + "values(?, ?, ?, ?, ?, ?, ?, ?, 'aspirant','" + dep.getId() + "')");
//                ps.setString(1, id_asp.getText());
//                ps.setString(2, surname_asp.getText());
//                ps.setString(3, tel_asp.getText());
//                ps.setString(4, sex_asp.getText());
//                ps.setString(5, topic_asp.getText());
//
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                Calendar cal = start_date_asp.getSelectedDate();
//                String addDate = dateFormat.format(cal.getTime());
//                ps.setString(6, addDate);
//
//                Calendar cal2 = finish_date_asp.getSelectedDate();
//                String addDate2 = dateFormat.format(cal2.getTime());
//                ps.setString(7, addDate2);
//
//                Calendar cal3 = start_work_prof.getSelectedDate();
//                String addDate3 = dateFormat.format(cal3.getTime());
//                ps.setString(8, addDate3);
//                ps.executeUpdate();
//                Show_Asp_In_JTable();
//
//                JOptionPane.showMessageDialog(null, "Data Inserted");
//                 } catch (Exception ex) {
//                 JOptionPane.showMessageDialog(null, ex.getMessage());
//            }
//         }else{
//            JOptionPane.showMessageDialog(null, "One Or More Field Are Empty");
//        }
//    }//GEN-LAST:event_insert_aspActionPerformed
//
//    private void insert_profActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insert_profActionPerformed
//                if(checkInputsProf()&&!id_prof.getText().equals("")){
//            try {
//                Connection con = getConnection();
//                Department dep = (Department) selectdepforscientists.getSelectedItem();
//                PreparedStatement ps = con.prepareStatement("INSERT INTO Scientist(idScientist, surname, tel, sex,positionProf, rankProf, startWorkProf, roleScientist, idDep)"
//                        + "values(?, ?, ?, ?, ?, ?, ?, 'professor','" + dep.getId() + "')");
//                ps.setString(1, id_prof.getText());
//                ps.setString(2, surname_prof.getText());
//                ps.setString(3, tel_prof.getText());
//                ps.setString(4, sex_prof.getText());
//                ps.setString(5, position_prof.getText());
//                ps.setString(6, rank_prof.getText());
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                Calendar cal = start_work_prof.getSelectedDate();
//                String addDate = dateFormat.format(cal.getTime());
//                ps.setString(7, addDate);
//                ps.executeUpdate();
//                Show_Prof_In_JTable();
//
//                JOptionPane.showMessageDialog(null, "Data Inserted");
//                 } catch (Exception ex) {
//                 JOptionPane.showMessageDialog(null, ex.getMessage());
//            }
//         }else{
//            JOptionPane.showMessageDialog(null, "One Or More Field Are Empty");
//        }
//    }//GEN-LAST:event_insert_profActionPerformed
//
//    private void edit_aspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_aspActionPerformed
//        if(checkInputsAsp()&&!id_asp.getText().equals(""))
//        {
//            String UpdateQuery = null;
//            PreparedStatement ps = null;
//            Connection con = getConnection();
//
//                try {
//                    UpdateQuery = "UPDATE Scientist SET surname = ?,tel = ? ,sex = ?,topicAsp = ?,startDateAsp = ?, finishDateAsp =?, presentDateAsp = ? WHERE idScientist = ?";
//                    ps = con.prepareStatement(UpdateQuery);
//
//                    ps.setString(1, surname_asp.getText());
//                    ps.setString(2, tel_asp.getText());
//                    ps.setString(3, sex_asp.getText());
//                    ps.setString(4, topic_asp.getText());
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                    Calendar cal = start_date_asp.getSelectedDate();
//                    String addDate = dateFormat.format(cal.getTime());
//                    ps.setString(5, addDate);
//
//                    Calendar cal2 = finish_date_asp.getSelectedDate();
//                    String addDate2 = dateFormat.format(cal2.getTime());
//                    ps.setString(6, addDate2);
//
//                    Calendar cal3 = start_work_prof.getSelectedDate();
//                    String addDate3 = dateFormat.format(cal3.getTime());
//                    ps.setString(7, addDate3);
//
//                    ps.setString(8, id_asp.getText());
//                    ps.executeUpdate();
//                    Show_Asp_In_JTable();
//                    JOptionPane.showMessageDialog(null, "Scientist Updated");
//
//                } catch (SQLException ex) {
//                    Logger.getLogger(Scientists_Form.class.getName()).log(Level.SEVERE, null, ex);
//                }
//        }else{
//            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
//        }
//    }//GEN-LAST:event_edit_aspActionPerformed
//
//
//    private void insert_masActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insert_masActionPerformed
//
//        if(checkInputs()&&!id_mas.getText().equals("")){
//            try {
//                 Connection con = getConnection();
//                 Department dep = (Department) selectdepforscientists.getSelectedItem();
//                PreparedStatement ps = con.prepareStatement("INSERT INTO Scientist(idScientist, surname, tel, sex, topicMas, startDateMas, finishDateMas, finishingReasonMas, roleScientist, idDep)"
//                        + "values(?, ?, ?, ?, ?, ?, ?, ?, 'master','" + dep.getId() +"')");
//                ps.setString(1, id_mas.getText());
//                ps.setString(2, surname_mas.getText());
//                ps.setString(3, tel_mas.getText());
//                ps.setString(4, sex_mas.getText());
//                ps.setString(5, topic_mas.getText());
//
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                Calendar cal = start_date_mas.getSelectedDate();
//                String addDate = dateFormat.format(cal.getTime());
//                ps.setString(6, addDate);
//
//                Calendar cal2 = finish_date_mas.getSelectedDate();
//                String addDate2 = dateFormat.format(cal2.getTime());
//                ps.setString(7, addDate2);
//
//                ps.setString(8, reason_mas.getText());
//                ps.executeUpdate();
//                Show_Masters_In_JTable();
//
//                JOptionPane.showMessageDialog(null, "Data Inserted");
//                 } catch (Exception ex) {
//                 JOptionPane.showMessageDialog(null, ex.getMessage());
//            }
//         }else{
//            JOptionPane.showMessageDialog(null, "One Or More Field Are Empty");
//        }
//    }//GEN-LAST:event_insert_masActionPerformed
//
//    private void edit_masActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_masActionPerformed
//
//        if(checkInputs()&&!id_mas.getText().equals(""))
//        {
//            String UpdateQuery = null;
//            PreparedStatement ps = null;
//            Connection con = getConnection();
//
//                try {
//                    UpdateQuery = "UPDATE Scientist SET surname = ?,tel = ? ,sex = ?,topicMas = ?,startDateMas = ?, finishDateMas =?, finishingReasonMas = ? WHERE idScientist = ?";
//                    ps = con.prepareStatement(UpdateQuery);
//
//                    ps.setString(1, surname_mas.getText());
//                    ps.setString(2, tel_mas.getText());
//                    ps.setString(3, sex_mas.getText());
//                    ps.setString(4, topic_mas.getText());
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                    Calendar cal = start_date_mas.getSelectedDate();
//                    String addDate = dateFormat.format(cal.getTime());
//                    ps.setString(5, addDate);
//
//                    Calendar cal2 = finish_date_mas.getSelectedDate();
//                    String addDate2 = dateFormat.format(cal2.getTime());
//                    ps.setString(6, addDate2);
//                    ps.setString(7, reason_mas.getText());
//                    ps.setString(8, id_mas.getText());
//                    ps.executeUpdate();
//                    Show_Masters_In_JTable();
//                    JOptionPane.showMessageDialog(null, "Scientist Updated");
//
//                } catch (SQLException ex) {
//                    Logger.getLogger(Scientists_Form.class.getName()).log(Level.SEVERE, null, ex);
//                }
//        }else{
//            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
//        }
//    }//GEN-LAST:event_edit_masActionPerformed
//
//    private void edit_profActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_profActionPerformed
//        if(checkInputsProf()&&!id_prof.getText().equals(""))
//        {
//            String UpdateQuery = null;
//            PreparedStatement ps = null;
//            Connection con = getConnection();
//
//                try {
//                    UpdateQuery = "UPDATE Scientist SET surname = ?,tel = ? ,sex = ?,positionProf = ?,rankProf = ?, startWorkProf =?  WHERE idScientist = ?";
//                    ps = con.prepareStatement(UpdateQuery);
//
//                    ps.setString(1, surname_prof.getText());
//                    ps.setString(2, tel_prof.getText());
//                    ps.setString(3, sex_prof.getText());
//                    ps.setString(4, position_prof.getText());
//                    ps.setString(5, rank_prof.getText());
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                    Calendar cal = start_work_prof.getSelectedDate();
//                    String addDate = dateFormat.format(cal.getTime());
//                    ps.setString(6, addDate);
//
//                    ps.setString(7, id_prof.getText());
//                    ps.executeUpdate();
//                    Show_Prof_In_JTable();
//                    JOptionPane.showMessageDialog(null, "Scientist Updated");
//
//                } catch (SQLException ex) {
//                    Logger.getLogger(Scientists_Form.class.getName()).log(Level.SEVERE, null, ex);
//                }
//        }else{
//            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
//        }
//    }//GEN-LAST:event_edit_profActionPerformed
//
//    private void delete_aspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_aspActionPerformed
//        if(!id_asp.getText().equals(""))
//        {
//            try {
//                Connection con = getConnection();
//                PreparedStatement ps = con.prepareStatement("DELETE FROM Scientist WHERE idScientist = ?");
//                ps.setString(1, id_asp.getText());
//                ps.executeUpdate();
//                Show_Asp_In_JTable();
//                JOptionPane.showMessageDialog(null, "Scientist Deleted");
//            } catch (SQLException ex) {
//                Logger.getLogger(Scientists_Form.class.getName()).log(Level.SEVERE, null, ex);
//                JOptionPane.showMessageDialog(null, "Scientist Not Deleted");
//            }
//
//        }else{
//            JOptionPane.showMessageDialog(null, "Scientist Not Deleted : No Id To Delete");
//        }
//    }//GEN-LAST:event_delete_aspActionPerformed
//
//    private void delete_masActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_masActionPerformed
//         if(!id_mas.getText().equals(""))
//        {
//            try {
//                Connection con = getConnection();
//                PreparedStatement ps = con.prepareStatement("DELETE FROM Scientist WHERE idScientist = ?");
//                ps.setString(1, id_mas.getText());
//                ps.executeUpdate();
//                Show_Masters_In_JTable();
//                JOptionPane.showMessageDialog(null, "Scientist Deleted");
//            } catch (SQLException ex) {
//                Logger.getLogger(Scientists_Form.class.getName()).log(Level.SEVERE, null, ex);
//                JOptionPane.showMessageDialog(null, "Scientist Not Deleted");
//            }
//
//        }else{
//            JOptionPane.showMessageDialog(null, "Scientist Not Deleted : No Id To Delete");
//        }
//
//    }//GEN-LAST:event_delete_masActionPerformed
//
//    private void delete_profActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_profActionPerformed
//        if(!id_prof.getText().equals(""))
//        {
//            try {
//                Connection con = getConnection();
//                PreparedStatement ps = con.prepareStatement("DELETE FROM Scientist WHERE idScientist = ?");
//                ps.setString(1, id_prof.getText());
//                ps.executeUpdate();
//                Show_Prof_In_JTable();
//                JOptionPane.showMessageDialog(null, "Scientist Deleted");
//            } catch (SQLException ex) {
//                Logger.getLogger(Scientists_Form.class.getName()).log(Level.SEVERE, null, ex);
//                JOptionPane.showMessageDialog(null, "Scientist Not Deleted");
//            }
//
//        }else{
//            JOptionPane.showMessageDialog(null, "Scientist Not Deleted : No Id To Delete");
//        }
//    }//GEN-LAST:event_delete_profActionPerformed
//
//    private void table_masMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_masMouseClicked
//        int index = table_mas.getSelectedRow();
//        ShowItem(index);
//
//    }//GEN-LAST:event_table_masMouseClicked
//
//    private void table_aspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_aspMouseClicked
//        int index = table_asp.getSelectedRow();
//        ShowItemAsp(index);
//    }//GEN-LAST:event_table_aspMouseClicked
//
//    private void table_profMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_profMouseClicked
//        int index = table_prof.getSelectedRow();
//        ShowItemProf(index);
//    }//GEN-LAST:event_table_profMouseClicked
//
//    private void selectdepforscientistsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectdepforscientistsActionPerformed
//        Show_Masters_In_JTable();
//        Show_Asp_In_JTable();
//        Show_Prof_In_JTable();
//    }//GEN-LAST:event_selectdepforscientistsActionPerformed
//
//    /**
//     * @param args the command line arguments
//     */
//
//
//    // Variables declaration - do not modify//GEN-BEGIN:variables
//    private java.awt.Button delete_asp;
//    private java.awt.Button delete_mas;
//    private java.awt.Button delete_prof;
//    private java.awt.Button edit_asp;
//    private java.awt.Button edit_mas;
//    private java.awt.Button edit_prof;
//    private datechooser.beans.DateChooserCombo finish_date_asp;
//    private datechooser.beans.DateChooserCombo finish_date_mas;
//    private javax.swing.JTextField id_asp;
//    private javax.swing.JTextField id_mas;
//    private javax.swing.JTextField id_prof;
//    private java.awt.Button insert_asp;
//    private java.awt.Button insert_mas;
//    private java.awt.Button insert_prof;
//    private javax.swing.JLabel jLabel1;
//    private javax.swing.JLabel jLabel10;
//    private javax.swing.JLabel jLabel11;
//    private javax.swing.JLabel jLabel12;
//    private javax.swing.JLabel jLabel13;
//    private javax.swing.JLabel jLabel14;
//    private javax.swing.JLabel jLabel15;
//    private javax.swing.JLabel jLabel16;
//    private javax.swing.JLabel jLabel17;
//    private javax.swing.JLabel jLabel18;
//    private javax.swing.JLabel jLabel19;
//    private javax.swing.JLabel jLabel2;
//    private javax.swing.JLabel jLabel20;
//    private javax.swing.JLabel jLabel21;
//    private javax.swing.JLabel jLabel22;
//    private javax.swing.JLabel jLabel23;
//    private javax.swing.JLabel jLabel3;
//    private javax.swing.JLabel jLabel4;
//    private javax.swing.JLabel jLabel5;
//    private javax.swing.JLabel jLabel6;
//    private javax.swing.JLabel jLabel7;
//    private javax.swing.JLabel jLabel8;
//    private javax.swing.JLabel jLabel9;
//    private javax.swing.JPanel jPanel1;
//    private javax.swing.JScrollPane jScrollPane1;
//    private javax.swing.JScrollPane jScrollPane2;
//    private javax.swing.JScrollPane jScrollPane3;
//    private javax.swing.JScrollPane jScrollPane4;
//    private javax.swing.JTextField position_prof;
//    private datechooser.beans.DateChooserCombo present_date_asp1;
//    private javax.swing.JTextField rank_prof;
//    private javax.swing.JTextField reason_mas;
//    private javax.swing.JComboBox selectdepforscientists;
//    private javax.swing.JTextField sex_asp;
//    private javax.swing.JTextField sex_mas;
//    private javax.swing.JTextField sex_prof;
//    private datechooser.beans.DateChooserCombo start_date_asp;
//    private datechooser.beans.DateChooserCombo start_date_mas;
//    private datechooser.beans.DateChooserCombo start_work_prof;
//    private javax.swing.JTextField surname_asp;
//    private javax.swing.JTextField surname_mas;
//    private javax.swing.JTextField surname_prof;
//    private javax.swing.JTable table_asp;
//    private javax.swing.JTable table_mas;
//    private javax.swing.JTable table_prof;
//    private javax.swing.JTextField tel_asp;
//    private javax.swing.JTextField tel_mas;
//    private javax.swing.JTextField tel_prof;
//    private javax.swing.JTextField topic_asp;
//    private javax.swing.JTextField topic_mas;
//    // End of variables declaration//GEN-END:variables
//}
