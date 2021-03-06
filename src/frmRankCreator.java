
import file.com.rc.FileManager;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import maker.com.rc.RankCreator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MarkXXIV
 */
public class frmRankCreator extends javax.swing.JFrame 
{
    String filePath;
    String directory;
    /**
     * Creates new form frmRankCreator
     */
    public frmRankCreator() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jfcSource = new javax.swing.JFileChooser();
        btnSource = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblSource = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResult = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rank Creator");
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        btnSource.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnSource.setForeground(new java.awt.Color(0, 102, 102));
        btnSource.setText("Selecionar");
        btnSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSourceActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Seleciona el archivo");

        btnCreate.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(0, 102, 102));
        btnCreate.setText("Crear intervalos");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        txtResult.setEditable(false);
        txtResult.setBackground(new java.awt.Color(0, 0, 0));
        txtResult.setColumns(20);
        txtResult.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        txtResult.setForeground(new java.awt.Color(255, 255, 255));
        txtResult.setLineWrap(true);
        txtResult.setRows(5);
        jScrollPane1.setViewportView(txtResult);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSource, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)))
                    .addComponent(lblSource, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSource)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSource, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreate)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSourceActionPerformed
        // TODO add your handling code here:
        jfcSource = new JFileChooser();
        FileFilter ff = jfcSource.getAcceptAllFileFilter();
        jfcSource.removeChoosableFileFilter(ff);
        jfcSource.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents", "txt"));
        
        jfcSource.showOpenDialog(this);
        if(jfcSource.getSelectedFile() != null)
        {
            System.out.println(jfcSource.getSelectedFile());
            System.out.println(jfcSource.getCurrentDirectory());
            filePath = jfcSource.getSelectedFile().toString();
            directory = jfcSource.getCurrentDirectory().toString();
            lblSource.setText("Source: " + filePath);
        }
    }//GEN-LAST:event_btnSourceActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        FileManager fm = new FileManager(filePath);
        RankCreator rc = new RankCreator();
        try
        {
            int[] nums = fm.getNumbers();
            List<String> interval = rc.createIntervals(nums);
            if(fm.saveResult(interval, directory + "\\Rangos.txt"))
            {
                txtResult.setText("Se ha procesado el archivo, y se ha creado el archivo de resultado con el nombre: \r\n" 
                        + directory + "/Intervalos.txt");
            }
            else
            {
                txtResult.setText("No se ha podido crear el archivo de resultados");
            }
        }catch(IOException | NumberFormatException e)
        {
            System.out.println(e.getClass().toString());
            if(e.getClass().toString().equals("class java.lang.NumberFormatException"))
            {
                txtResult.setText("Ocurrió un error. \r\nVerifique que el archivo tenga únicamente números. \r\n" + e.getMessage());
            }
            else
            {
                txtResult.setText("Ocurrió un error. \r\n" + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmRankCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRankCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRankCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRankCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRankCreator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnSource;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFileChooser jfcSource;
    private javax.swing.JLabel lblSource;
    private javax.swing.JTextArea txtResult;
    // End of variables declaration//GEN-END:variables
}
