//package javaapplication1;
//
//import com.mysql.jdbc.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.text.ParseException;
//import javax.swing.JOptionPane;
//import javaapplication1.ScienceTopic;
//import javax.swing.table.DefaultTableModel;
//public class WorkersOnST_Form extends javax.swing.JFrame {
//    public WorkersOnST_Form() {
//        initComponents();
//        getConnection();
//        Show_Departments();
//    }
//    public Connection getConnection()
//    {
//        Connection con = null;
//
//        try {
//            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ScienceTopicPart?autoReconnect=true&useSSL=false","root","");
//            return con;
//        } catch (SQLException ex) {
//            Logger.getLogger(WorkersOnST_Form.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//     public ArrayList<Department> getDepartmentsList() {
//        ArrayList<Department> departmentsList  = new ArrayList<Department>();
//        Connection con = getConnection();
//        String query = "SELECT * FROM Department";
//        Statement st;
//        ResultSet rs;
//        try {
//            st = con.createStatement();
//            rs = st.executeQuery(query);
//            Department dep;
//            while(rs.next())
//            {
//                dep = new Department(rs.getString("idDep"),rs.getString("nameDep"),rs.getString("telDep"));
//                departmentsList.add(dep);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Scientists_Form.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return departmentsList;
//    }
//
//    public void Show_Departments() {
//        ArrayList<Department> list = getDepartmentsList();
//        for(int i = 0; i <= list.size() - 1; i++) {
//            select_dep.addItem(list.get(i));
//            select_dep_pr.addItem(list.get(i));
//        }
//    }
//
//    public ArrayList<ScienceTopic> getTopicsList() {
//        ArrayList<ScienceTopic> topicsList  = new ArrayList<ScienceTopic>();
//        Connection con = getConnection();
//        Department dep = (Department) select_dep.getSelectedItem();
//        String query = "SELECT * FROM ScienceTopic WHERE idDep = '" + dep.getId() + "'";
//        Statement st;
//        ResultSet rs;
//        try {
//            st = con.createStatement();
//            rs = st.executeQuery(query);
//            ScienceTopic sst;
//            while(rs.next())
//            {
//                sst = new ScienceTopic(rs.getString("idST"),rs.getString("idDep"),rs.getString("nameST"),rs.getString("nameCust"),rs.getString("startDate"),rs.getString("finishDate"));
//                topicsList.add(sst);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Scientists_Form.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return topicsList;
//    }
//
//    public void Show_Topics() {
//        ArrayList<ScienceTopic> list = getTopicsList();
//        select_st.removeAllItems();
//        for(int i = 0; i <= list.size() - 1; i++) {
//            select_st.addItem(list.get(i));
//        }
//    }
//
//    public ArrayList<Scientist> getProfsList() {
//        ArrayList<Scientist> profsList  = new ArrayList<Scientist>();
//        Connection con = getConnection();
//        Department dep = (Department) select_dep_pr.getSelectedItem();
//        String query = "SELECT * FROM Scientist WHERE roleScientist LIKE 'professor' AND idDep = '" + dep.getId() + "'";
//        Statement st;
//        ResultSet rs;
//        try {
//            st = con.createStatement();
//            rs = st.executeQuery(query);
//            Scientist prof;
//            while(rs.next())
//            {
//                prof = new Scientist(rs.getString("idScientist"), rs.getString("surname"), rs.getString("tel"), rs.getString("sex"), null, null, null, null, null, null, null, null, rs.getString("positionProf"), rs.getString("rankProf"),rs.getString("startWorkProf"), "professor", rs.getString("idDep"));
//                profsList.add(prof);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Scientists_Form.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return profsList;
//    }
//
//    public void Show_Profs() {
//        ArrayList<Scientist> list = getProfsList();
//        select_surn.removeAllItems();
//        for(int i = 0; i <= list.size() - 1; i++) {
//            select_surn.addItem(list.get(i));
//        }
//    }
//
//    public boolean checkInputs()
//    {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                Calendar cal = start_sc_st.getSelectedDate();
//                String stDate = dateFormat.format(cal.getTime());
//                Calendar cal2 = finish_sc_st.getSelectedDate();
//                String stDate2 = dateFormat.format(cal2.getTime());
//        if(
//              textField2.getText().equals("")||
//              stDate.equals("")||
//              stDate2.equals("")
//          ){
//            return false;
//            }
//        else{
//            return true;
//        }
//    }
//
//        public ArrayList<ScientistScienceTopic> getScientistScienceTopicList()
//    {
//            ArrayList<ScientistScienceTopic> scientistScienceTopicList  = new ArrayList<ScientistScienceTopic>();
//            Connection con = getConnection();
//            if (select_st.getSelectedItem() != null) {
//                ScienceTopic sst = (ScienceTopic) select_st.getSelectedItem();
//                String query = "SELECT * FROM ScientistScienceTopic INNER JOIN Scientist ON Scientist.idScientist = ScientistScienceTopic.idScientist WHERE idST = '" + sst.getId() + "'";
//
//                Statement st;
//                ResultSet rs;
//
//                try {
//
//                    st = con.createStatement();
//                    rs = st.executeQuery(query);
//                    ScientistScienceTopic scientistScienceTopicItem;
//
//                    while(rs.next())
//                    {
//                        scientistScienceTopicItem = new ScientistScienceTopic(rs.getString("id"),rs.getString("nameWork"), rs.getString("startDate"), rs.getString("finishDate"), rs.getString("surname"));
//                        scientistScienceTopicList.add(scientistScienceTopicItem);
//                    }
//
//                } catch (SQLException ex) {
//                    Logger.getLogger(WorkersOnST_Form.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//        return scientistScienceTopicList;
//
//    }
//
//        public void Show_In_ScientistScienceTopic_JTable()
//    {
//        ArrayList<ScientistScienceTopic> list = getScientistScienceTopicList();
//        DefaultTableModel model = (DefaultTableModel) sc_st_table.getModel();
//        model.setRowCount(0);
//        Object[] row = new Object[4];
//        for(int i = 0; i <= list.size() - 1; i++)
//        {
//            row[0] = list.get(i).getScientistSurname();
//            row[1] = list.get(i).getNameWork();
//            row[2] = list.get(i).getStartDate();
//            row[3] = list.get(i).getEndDate();
//            model.addRow(row);
//        }
//
//    }
//
//    public ArrayList<ProfessorScienceTopic> getProfessorScienceTopicList()
//    {
//            ArrayList<ProfessorScienceTopic> profScienceTopicList  = new ArrayList<ProfessorScienceTopic>();
//            Connection con = getConnection();
//            if (select_st.getSelectedItem() != null) {
//                ScienceTopic sst = (ScienceTopic) select_st.getSelectedItem();
//                String query = "SELECT * FROM ProfessorScienceTopic INNER JOIN Scientist ON Scientist.idScientist = ProfessorScienceTopic.idScientist WHERE idST = '" + sst.getId() + "'";
//
//                Statement st;
//                ResultSet rs;
//
//                try {
//
//                    st = con.createStatement();
//                    rs = st.executeQuery(query);
//                    ProfessorScienceTopic profScienceTopicItem;
//
//                    while(rs.next())
//                    {
//                        profScienceTopicItem = new ProfessorScienceTopic(rs.getString("id"), rs.getString("startDate"), rs.getString("finishDate"), rs.getString("surname"), rs.getString("idST"));
//                        profScienceTopicList.add(profScienceTopicItem);
//                    }
//
//                } catch (SQLException ex) {
//                    Logger.getLogger(WorkersOnST_Form.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//        return profScienceTopicList;
//
//    }
//
//        public void Show_In_ProfScienceTopic_JTable()
//    {
//        ArrayList<ProfessorScienceTopic> list = getProfessorScienceTopicList();
//        DefaultTableModel model = (DefaultTableModel) pr_st_table.getModel();
//        model.setRowCount(0);
//        Object[] row = new Object[3];
//        for(int i = 0; i <= list.size() - 1; i++)
//        {
//            row[0] = list.get(i).getProfSurname();
//            row[1] = list.get(i).getStartDate();
//            row[2] = list.get(i).getEndDate();
//            model.addRow(row);
//        }
//
//    }
//
//    @SuppressWarnings("unchecked")
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
//    private void initComponents() {
//
//        jScrollPane4 = new javax.swing.JScrollPane();
//        jPanel1 = new javax.swing.JPanel();
//        jScrollPane1 = new javax.swing.JScrollPane();
//        pr_st_table = new javax.swing.JTable();
//        jScrollPane3 = new javax.swing.JScrollPane();
//        sc_st_table = new javax.swing.JTable();
//        select_dep = new javax.swing.JComboBox();
//        select_surn = new javax.swing.JComboBox();
//        textField2 = new java.awt.TextField();
//        start_sc_st = new datechooser.beans.DateChooserCombo();
//        finish_sc_st = new datechooser.beans.DateChooserCombo();
//        jLabel1 = new javax.swing.JLabel();
//        jLabel2 = new javax.swing.JLabel();
//        jLabel3 = new javax.swing.JLabel();
//        jLabel4 = new javax.swing.JLabel();
//        select_dep_pr = new javax.swing.JComboBox();
//        jLabel5 = new javax.swing.JLabel();
//        select_st = new javax.swing.JComboBox();
//        insert_prof = new java.awt.Button();
//        jLabel6 = new javax.swing.JLabel();
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        setTitle("idScientist");
//
//        pr_st_table.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {
//                {null, null, null},
//                {null, null, null},
//                {null, null, null},
//                {null, null, null}
//            },
//            new String [] {
//                "professor", "startDate", "finishDate"
//            }
//        ));
//        jScrollPane1.setViewportView(pr_st_table);
//
//        sc_st_table.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null}
//            },
//            new String [] {
//                "sciensist", "nameWork", "startDate", "finishDate"
//            }
//        ));
//        jScrollPane3.setViewportView(sc_st_table);
//
//        select_dep.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                select_depActionPerformed(evt);
//            }
//        });
//
//        select_surn.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                select_surnActionPerformed(evt);
//            }
//        });
//
//        textField2.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                textField2ActionPerformed(evt);
//            }
//        });
//
//        jLabel1.setText("Кафедра");
//
//        jLabel2.setText("Тема");
//
//        jLabel3.setText("Додати керівника");
//
//        jLabel4.setText("Кафедра");
//
//        select_dep_pr.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                select_dep_prActionPerformed(evt);
//            }
//        });
//
//        jLabel5.setText("ID");
//
//        select_st.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                select_stActionPerformed(evt);
//            }
//        });
//
//        insert_prof.setLabel("Insert");
//        insert_prof.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                insert_profActionPerformed(evt);
//            }
//        });
//
//        jLabel6.setText("Прізвище");
//
//        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
//        jPanel1.setLayout(jPanel1Layout);
//        jPanel1Layout.setHorizontalGroup(
//            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(jPanel1Layout.createSequentialGroup()
//                .addGap(21, 21, 21)
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                    .addGroup(jPanel1Layout.createSequentialGroup()
//                        .addComponent(start_sc_st, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
//                        .addComponent(finish_sc_st, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
//                    .addComponent(jLabel1)
//                    .addComponent(jLabel2)
//                    .addComponent(jLabel3)
//                    .addComponent(jLabel4)
//                    .addComponent(select_dep_pr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
//                        .addComponent(select_dep, javax.swing.GroupLayout.Alignment.LEADING, 0, 100, Short.MAX_VALUE)
//                        .addComponent(select_st, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                    .addComponent(insert_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(select_surn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jLabel6)
//                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jLabel5))
//                .addGap(30, 30, 30)
//                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(27, 27, 27)
//                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(152, Short.MAX_VALUE))
//        );
//        jPanel1Layout.setVerticalGroup(
//            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(jPanel1Layout.createSequentialGroup()
//                .addGap(22, 22, 22)
//                .addComponent(jLabel1)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(jPanel1Layout.createSequentialGroup()
//                        .addComponent(select_dep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addGap(18, 18, 18)
//                        .addComponent(jLabel2)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                        .addComponent(select_st, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                        .addComponent(jLabel3)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                        .addComponent(jLabel4)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(select_dep_pr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(jLabel6)
//                        .addGap(5, 5, 5)
//                        .addComponent(select_surn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(jLabel5)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addComponent(start_sc_st, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
//                            .addComponent(finish_sc_st, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(insert_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
//                        .addGap(0, 55, Short.MAX_VALUE)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addGap(15, 15, 15))))
//        );
//
//        jScrollPane4.setViewportView(jPanel1);
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                .addContainerGap(22, Short.MAX_VALUE)
//                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap())
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(535, Short.MAX_VALUE))
//        );
//
//        pack();
//    }// </editor-fold>//GEN-END:initComponents
//
//    private void select_surnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_surnActionPerformed
//        // TODO add your handling code here:
//    }//GEN-LAST:event_select_surnActionPerformed
//
//    private void select_stActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_stActionPerformed
//        Show_In_ScientistScienceTopic_JTable();
//        Show_In_ProfScienceTopic_JTable();
//    }//GEN-LAST:event_select_stActionPerformed
//
//    private void textField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField2ActionPerformed
//        // TODO add your handling code here:
//    }//GEN-LAST:event_textField2ActionPerformed
//
//    private void select_depActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_depActionPerformed
//        Show_Topics();
//        Show_In_ScientistScienceTopic_JTable();
//        Show_In_ProfScienceTopic_JTable();
//    }//GEN-LAST:event_select_depActionPerformed
//
//    private void select_dep_prActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_dep_prActionPerformed
//        Show_Profs();
//    }//GEN-LAST:event_select_dep_prActionPerformed
//
//    private void insert_profActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insert_profActionPerformed
//        if(checkInputs()&&!textField2.getText().equals("")){
//            try {
//                Connection con = getConnection();
//                ScienceTopic sct = (ScienceTopic) select_st.getSelectedItem();
//                Scientist scc = (Scientist) select_surn.getSelectedItem();
//                PreparedStatement ps = con.prepareStatement("INSERT INTO ProfessorScienceTopic(id, startDate, finishDate, idScientist, idST)"
//                        + "values(?, ?, ?, '" + scc.getScientistId() + "','" + sct.getId() + "')");
//
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                ps.setString(1, textField2.getText());
//                Calendar cal = start_sc_st.getSelectedDate();
//                String addDate = dateFormat.format(cal.getTime());
//                ps.setString(2, addDate);
//
//                Calendar cal2 = finish_sc_st.getSelectedDate();
//                String addDate2 = dateFormat.format(cal2.getTime());
//                ps.setString(3, addDate2);
//                ps.executeUpdate();
//                Show_In_ProfScienceTopic_JTable();
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
//
//    /**
//     * @param args the command line arguments
//     */
//
//
//    // Variables declaration - do not modify//GEN-BEGIN:variables
//    private datechooser.beans.DateChooserCombo finish_sc_st;
//    private java.awt.Button insert_prof;
//    private javax.swing.JLabel jLabel1;
//    private javax.swing.JLabel jLabel2;
//    private javax.swing.JLabel jLabel3;
//    private javax.swing.JLabel jLabel4;
//    private javax.swing.JLabel jLabel5;
//    private javax.swing.JLabel jLabel6;
//    private javax.swing.JPanel jPanel1;
//    private javax.swing.JScrollPane jScrollPane1;
//    private javax.swing.JScrollPane jScrollPane3;
//    private javax.swing.JScrollPane jScrollPane4;
//    private javax.swing.JTable pr_st_table;
//    private javax.swing.JTable sc_st_table;
//    private javax.swing.JComboBox select_dep;
//    private javax.swing.JComboBox select_dep_pr;
//    private javax.swing.JComboBox select_st;
//    private javax.swing.JComboBox select_surn;
//    private datechooser.beans.DateChooserCombo start_sc_st;
//    private java.awt.TextField textField2;
//    // End of variables declaration//GEN-END:variables
//}
