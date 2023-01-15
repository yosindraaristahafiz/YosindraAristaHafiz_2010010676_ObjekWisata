/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasiobjekwisata;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HP
 */
public class Transaksi extends javax.swing.JInternalFrame {

    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        initComponents();
        clear();
        tampil();
        setMobil();
        setObjek();
    }
    public final Connection conn = new Koneksi().getConnetion();
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    DefaultTableModel tabMode;
    
    public void setMobil(){
        try {
            String sql = "SELECT * FROM mobil";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                cMOBIL.addItem(rs.getString("ID_Mobil"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void setObjek(){
        try {
            String sql = "SELECT * FROM objek";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                cOBJEKWISATA.addItem(rs.getString("ID_Objek"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void tampil(){
        Object [] baris = {"No", "ID Transaksi", "ID Mobil", "Jenis Mobil", "Plat Nomor", "Harga Sewa", "ID Objek Wisata", "Nama Objek Wisata", "Lokasi", "Harga Tiket", "Nama Pelanggan","Tanggal Sewa", "Durasi Sewa", "Total Keseluruhan"};
        tabMode = new DefaultTableModel(null, baris);
        tabelTransaksi.setModel(tabMode);
        try {
                String sql = "SELECT transaksi.ID_Transaksi, mobil.ID_Mobil, mobil.Jenis_Mobil, mobil.Plat_Nomor, mobil.Harga_Sewa, objek.ID_Objek, objek.Nama_Objek, objek.Lokasi, objek.Harga_Tiket, transaksi.Nama_Pelanggan, transaksi.Tanggal_Sewa, transaksi.Durasi_Sewa, transaksi.Total_Harga FROM transaksi,mobil,objek WHERE transaksi.ID_Mobil = mobil.ID_Mobil AND transaksi.ID_Objek = objek.ID_Objek";
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                int no = 0;
                while (rs.next()){
                        no++;
                        String idtransaksi = rs.getString("ID_Transaksi");
                        String mobil = rs.getString("ID_Mobil");
                        String jenis = rs.getString("Jenis_Mobil");
                        String plat = rs.getString("Plat_Nomor");
                        String hargasewa = rs.getString("Harga_Sewa");
                        String idobjekwisata = rs.getString("ID_Objek");
                        String namaobjekwisata = rs.getString("Nama_Objek");
                        String lokasi = rs.getString("Lokasi");
                        String hargatiket  = rs.getString("Harga_Tiket");
                        String namapelanggan  = rs.getString("Nama_Pelanggan");
                        String tanggalsewa = rs.getString("Tanggal_Sewa");
                        String durasisewa = rs.getString("Durasi_Sewa");
                        String totalkeseluruhan = rs.getString("Total_Harga");

                        Object [] data = {no,idtransaksi,mobil,jenis,plat,hargasewa,idobjekwisata,namaobjekwisata,lokasi,hargatiket,namapelanggan,tanggalsewa,durasisewa,totalkeseluruhan};
                        tabMode.addRow(data);
                }
        } catch (Exception e){
                System.out.println(e.toString());
        }
    }
    
    public String kodeOtomatis(){
        String kode = "";
        try {
            int kodeLama;
            pst = conn.prepareStatement("SELECT ID_Transaksi FROM transaksi ORDER BY ID_Transaksi DESC");
            rs = pst.executeQuery();
            if (!rs.next()){
                kode = "PSN-0001";
            } else {
                kodeLama=Integer.parseInt(rs.getString(1).substring(4))+1;
                if(kodeLama<10){
                    kode = "PSN-000"+kodeLama;
                }
                else if(kodeLama >= 10 && kodeLama<100){
                    kode = "PSN-00"+kodeLama;
                }
                else if(kodeLama >= 100 && kodeLama<1000){
                    kode = "PSN-0"+kodeLama;
                }
                else{
                    kode = "PSN-"+kodeLama;
                }
            }
        } catch (Exception e) {
            System.out.println("error kode otomasis : " +e.toString());
        }
        return kode;
    }
    
    public void clear(){
        tIDTRANSAKSI.setText(kodeOtomatis());
        
        cMOBIL.setSelectedItem(null);
        tJENISMOBIL.setText(null);
        tPLATNOMOR.setText(null);
        tHARGASEWA.setText(null);
        
        cOBJEKWISATA.setSelectedItem(null);
        tNAMAOBJEKWISATA.setText(null);
        tLOKASIOBJEKWISATA.setText(null);
        tHARGATIKET.setText(null);
        
        tNAMAPELANGGAN.setText(null);
        tTANGGAL.setDate(null);
        tDURASISEWA.setText(null);
        
        tTOTAL.setText(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tIDTRANSAKSI = new javax.swing.JTextField();
        cMOBIL = new javax.swing.JComboBox<>();
        tJENISMOBIL = new javax.swing.JTextField();
        tPLATNOMOR = new javax.swing.JTextField();
        tHARGASEWA = new javax.swing.JTextField();
        cOBJEKWISATA = new javax.swing.JComboBox<>();
        tNAMAOBJEKWISATA = new javax.swing.JTextField();
        tLOKASIOBJEKWISATA = new javax.swing.JTextField();
        tHARGATIKET = new javax.swing.JTextField();
        tNAMAPELANGGAN = new javax.swing.JTextField();
        tTANGGAL = new com.toedter.calendar.JDateChooser();
        tDURASISEWA = new javax.swing.JTextField();
        tTOTAL = new javax.swing.JTextField();
        bSave = new javax.swing.JButton();
        bEdit = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        bPrint = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTransaksi = new javax.swing.JTable();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Transaksi"));

        jLabel1.setText("ID Transkasi");

        jLabel2.setText("ID Mobil");

        jLabel3.setText("Jenis Mobil");

        jLabel4.setText("Plat Nomor");

        jLabel5.setText("Harga Sewa");

        jLabel6.setText("ID Objek Wisata");

        jLabel7.setText("Nama Objek Wisata");

        jLabel8.setText("Lokasi Objek Wisata");

        jLabel9.setText("Harga Tiket");

        jLabel10.setText("Nama Pelanggan");

        jLabel11.setText("Tanggal Sewa");

        jLabel12.setText("Durasi Sewa");

        jLabel13.setText("Total Keseluruhan");

        tIDTRANSAKSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tIDTRANSAKSIActionPerformed(evt);
            }
        });

        cMOBIL.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cMOBILPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        tJENISMOBIL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tJENISMOBILActionPerformed(evt);
            }
        });

        cOBJEKWISATA.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cOBJEKWISATAPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cOBJEKWISATA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cOBJEKWISATAActionPerformed(evt);
            }
        });

        tDURASISEWA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tDURASISEWAActionPerformed(evt);
            }
        });

        tTOTAL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tTOTALMouseClicked(evt);
            }
        });
        tTOTAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tTOTALActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tIDTRANSAKSI)
                    .addComponent(cMOBIL, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tJENISMOBIL)
                    .addComponent(tPLATNOMOR)
                    .addComponent(tHARGASEWA)
                    .addComponent(cOBJEKWISATA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tNAMAOBJEKWISATA)
                    .addComponent(tLOKASIOBJEKWISATA)
                    .addComponent(tHARGATIKET)
                    .addComponent(tNAMAPELANGGAN)
                    .addComponent(tTANGGAL, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(tDURASISEWA)
                    .addComponent(tTOTAL))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tIDTRANSAKSI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cMOBIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(tJENISMOBIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tPLATNOMOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tHARGASEWA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cOBJEKWISATA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tNAMAOBJEKWISATA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tLOKASIOBJEKWISATA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tHARGATIKET, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(tNAMAPELANGGAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11))
                    .addComponent(tTANGGAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tDURASISEWA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(354, Short.MAX_VALUE))
        );

        bSave.setText("SAVE");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        bEdit.setText("EDIT");
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });

        bDelete.setText("DELETE");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        bClear.setText("CLEAR");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        bPrint.setText("PRINT");
        bPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrintActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Transaksi"));

        tabelTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelTransaksi);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bClear, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(bDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bClear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tIDTRANSAKSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tIDTRANSAKSIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tIDTRANSAKSIActionPerformed

    private void tDURASISEWAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tDURASISEWAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tDURASISEWAActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        // TODO add your handling code here:
        String ID_Mobil = cMOBIL.getSelectedItem().toString();
        String ID_Objek = cOBJEKWISATA.getSelectedItem().toString();
        
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(dateformat.format(tTANGGAL.getDate()));
        
        if (tIDTRANSAKSI.getText().equals("")){
            JOptionPane.showMessageDialog(null, "ID Booking Tidak Boleh Kosong");
            tIDTRANSAKSI.requestFocus();
        } else if (tTANGGAL.getDate() == null){
            JOptionPane.showMessageDialog(null, "Tanggal Sewa Tidak Boleh Kosong");
        } else if (cMOBIL.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Mobil");
        } else if (cOBJEKWISATA.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Objek Wisata");
        } else {
            try {
               int s;
               String sql = "INSERT INTO transaksi VALUES ('"+tIDTRANSAKSI.getText()+"', '"+ID_Mobil+"', '"+ID_Objek+"'  , '"+tNAMAPELANGGAN.getText()+"', '"+tanggal+"', '"+tDURASISEWA.getText()+"', '"+tTOTAL.getText()+"' )";
               st = conn.createStatement();
               s = st.executeUpdate(sql);
               if (s == 1){
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                clear();
                tampil();
               }
           } catch (HeadlessException | SQLException e){
                System.out.println(e.toString());
           }    
        }
        
    }//GEN-LAST:event_bSaveActionPerformed

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        // TODO add your handling code here:
         String ID_Mobil = cMOBIL.getSelectedItem().toString();
        String ID_Objek = cOBJEKWISATA.getSelectedItem().toString();
        
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(dateformat.format(tTANGGAL.getDate()));
        
        if (tIDTRANSAKSI.getText().equals("")){
            JOptionPane.showMessageDialog(null, "ID Booking Tidak Boleh Kosong");
            tIDTRANSAKSI.requestFocus();
        } else if (tTANGGAL.getDate().equals(null)){
            JOptionPane.showMessageDialog(null, "Tanggal Sewa Tidak Boleh Kosong");
        } else if (cMOBIL.getSelectedItem().equals(null)){
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Mobil");
        } else if (cOBJEKWISATA.getSelectedItem().equals(null)){
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Objek Wisata");
        } else {
            try {
               int s;
               String sql = "UPDATE transaksi SET ID_Mobil = '"+ID_Mobil+"', ID_Objek = '"+ID_Objek+"'  , Nama_Pelanggan = '"+tNAMAPELANGGAN.getText()+"', Tanggal_Sewa = '"+tanggal+"', Durasi_Sewa = '"+tDURASISEWA.getText()+"', Total_Harga = '"+tTOTAL.getText()+"' WHERE ID_Transaksi = '"+tIDTRANSAKSI.getText()+"' ";
               st = conn.createStatement();
               s = st.executeUpdate(sql);
               if (s == 1){
                JOptionPane.showMessageDialog(null, "Data Berhasil Diedit");
                clear();
                tampil();
               }
           } catch (Exception e){
                System.out.println(e.toString());
           }    
        }
    }//GEN-LAST:event_bEditActionPerformed

    private void tJENISMOBILActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tJENISMOBILActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tJENISMOBILActionPerformed

    private void cMOBILPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cMOBILPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM mobil WHERE ID_Mobil = '"+cMOBIL.getSelectedItem()+"'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()){
                tJENISMOBIL.setText(rs.getString("Jenis_Mobil"));
                tPLATNOMOR.setText(rs.getString("Plat_Nomor"));
                tHARGASEWA.setText(rs.getString("Harga_Sewa"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_cMOBILPopupMenuWillBecomeInvisible

    private void cOBJEKWISATAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cOBJEKWISATAActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cOBJEKWISATAActionPerformed

    private void cOBJEKWISATAPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cOBJEKWISATAPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
                try {
            String sql = "SELECT * FROM objek WHERE ID_Objek = '"+cOBJEKWISATA.getSelectedItem()+"'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()){
                tNAMAOBJEKWISATA.setText(rs.getString("Nama_Objek"));
                tLOKASIOBJEKWISATA.setText(rs.getString("Lokasi"));
                tHARGATIKET.setText(rs.getString("Harga_Tiket"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_cOBJEKWISATAPopupMenuWillBecomeInvisible

    private void tTOTALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTOTALActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTOTALActionPerformed

    private void tTOTALMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tTOTALMouseClicked
        // TODO add your handling code here:
        int harga_sewa = Integer.parseInt(tHARGASEWA.getText());
        int harga_tiket = Integer.parseInt(tHARGATIKET.getText());
        int durasi = Integer.parseInt(tDURASISEWA.getText());
        
        int total = harga_sewa * durasi + harga_tiket;
        tTOTAL.setText(String.valueOf(total));
    }//GEN-LAST:event_tTOTALMouseClicked

    private void tabelTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTransaksiMouseClicked
        // TODO add your handling code here:
            try {
            int baris = tabelTransaksi.getSelectedRow();
            tIDTRANSAKSI.setText(tabelTransaksi.getValueAt(baris, 1).toString());
            cMOBIL.setSelectedItem(tabelTransaksi.getValueAt(baris, 2).toString());
            tJENISMOBIL.setText(tabelTransaksi.getValueAt(baris,3).toString());
            tPLATNOMOR.setText(tabelTransaksi.getValueAt(baris,4).toString());
            tHARGASEWA.setText(tabelTransaksi.getValueAt(baris,5).toString());
            
            cOBJEKWISATA.setSelectedItem(tabelTransaksi.getValueAt(baris, 6).toString());
            tNAMAOBJEKWISATA.setText(tabelTransaksi.getValueAt(baris,7).toString());
            tLOKASIOBJEKWISATA.setText(tabelTransaksi.getValueAt(baris,8).toString());
            tHARGATIKET.setText(tabelTransaksi.getValueAt(baris,9).toString());
            
            tNAMAPELANGGAN.setText(tabelTransaksi.getValueAt(baris,10).toString());
            
            String tanggal = tabelTransaksi.getValueAt(baris, 11).toString();
            Date stanggal = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal);
            tTANGGAL.setDate(stanggal);
            
            tDURASISEWA.setText(tabelTransaksi.getValueAt(baris,12).toString());
            tTOTAL.setText(tabelTransaksi.getValueAt(baris,13).toString());
            
        } catch (ParseException e) {
            System.out.println(e.toString());
        }
        
    }//GEN-LAST:event_tabelTransaksiMouseClicked

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        try {
               int s;
               String sql = "DELETE FROM transaksi WHERE ID_Transaksi = '"+tIDTRANSAKSI.getText()+"' ";
               st = conn.createStatement();
               s = st.executeUpdate(sql);
               if (s == 1){
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapous");
                clear();
                tampil();
               }
           } catch (Exception e){
                System.out.println(e.toString());
           }
        
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_bClearActionPerformed

    private void bPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrintActionPerformed
        // TODO add your handling code here:
        
               JasperReport reports;

        String path=".\\src\\aplikasiobjekwisata\\laporanTransaksi.jasper";
        try {
            reports = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
        } catch (JRException e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_bPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClear;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bEdit;
    private javax.swing.JButton bPrint;
    private javax.swing.JButton bSave;
    private javax.swing.JComboBox<String> cMOBIL;
    private javax.swing.JComboBox<String> cOBJEKWISATA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tDURASISEWA;
    private javax.swing.JTextField tHARGASEWA;
    private javax.swing.JTextField tHARGATIKET;
    private javax.swing.JTextField tIDTRANSAKSI;
    private javax.swing.JTextField tJENISMOBIL;
    private javax.swing.JTextField tLOKASIOBJEKWISATA;
    private javax.swing.JTextField tNAMAOBJEKWISATA;
    private javax.swing.JTextField tNAMAPELANGGAN;
    private javax.swing.JTextField tPLATNOMOR;
    private com.toedter.calendar.JDateChooser tTANGGAL;
    private javax.swing.JTextField tTOTAL;
    private javax.swing.JTable tabelTransaksi;
    // End of variables declaration//GEN-END:variables

}
