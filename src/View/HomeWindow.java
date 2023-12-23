/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.SoundManager;
import Model.TableScore;
import ViewModel.Game;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author raisy
 * 
 * 
 * @Created by SyncZer
 * 
 *       ///\\\
 * 
 * Saya Raisyad Julfikar mengerjakan evaluasi Tugas Masa Depan dalam mata
 * kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya
 * tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.
 * 
 */
public class HomeWindow extends javax.swing.JFrame {

    /**
     * Creates new form HomeWindow
     */
    
    // properti
    public Game game; // objek game
    private TableScore tscore; // data tabel
    public Clip audio; // backsound
    private String username; // username
    
    
    public HomeWindow() {
        initComponents();
        this.username = ""; // init default value
        setTitle("Keep Standing Uhuy | Menu");
        try {
            // create the new table
            this.tscore = new TableScore();
            // assign value
            TableScore.setModel(tscore.setTable());
        } catch (Exception er) {
            System.out.println(er.getMessage());
        }
        // Init sound and play menus sound
        SoundManager bgm = new SoundManager();
        audio = bgm.playSound(this.audio, "sound/menus.wav");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        usernameInput = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableScore = new javax.swing.JTable();
        Play = new javax.swing.JButton();
        Quit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Username : ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 95, -1, -1));

        usernameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameInputActionPerformed(evt);
            }
        });
        getContentPane().add(usernameInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 90, 187, -1));

        TableScore.setBackground(new java.awt.Color(0, 0, 0));
        TableScore.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TableScore.setForeground(new java.awt.Color(255, 255, 255));
        TableScore.setModel(new javax.swing.table.DefaultTableModel(
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
        TableScore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableScoreMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableScore);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 134, 375, 275));

        Play.setBackground(new java.awt.Color(51, 153, 0));
        Play.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        Play.setForeground(new java.awt.Color(255, 255, 255));
        Play.setText("Play");
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayActionPerformed(evt);
            }
        });
        getContentPane().add(Play, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 421, -1, -1));

        Quit.setBackground(new java.awt.Color(153, 0, 0));
        Quit.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        Quit.setForeground(new java.awt.Color(255, 255, 255));
        Quit.setText("Quit");
        Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitActionPerformed(evt);
            }
        });
        getContentPane().add(Quit, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 421, -1, -1));

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 51));
        jLabel2.setText("Keep Standing Uhuy");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AssetMenu/bg_menu.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameInputActionPerformed

    private void PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayActionPerformed
        // TODO add your handling code here:   
        if (usernameInput.getText().length() == 0) { // If user not input the username
            JOptionPane.showMessageDialog(this, "Username tidak boleh kosong!");
        } else {
            this.username = usernameInput.getText();
            try {
                // sound new game
                SoundManager bgm = new SoundManager();
                bgm.stopSound(this.audio);

                // create new game
                game = new Game();
            } catch (IOException ex) {
                Logger.getLogger(HomeWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            GameWindow gw = new GameWindow(game); // Create game window
            game.setUsername(this.username); // set username
         
            TableScore temp = null;
            
            try {
                temp = new TableScore();
            } catch (Exception ex) {
                Logger.getLogger(HomeWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            temp.getTScore(this.username);
            
            int scoreSkor = 0, scoreStanding = 0;
            boolean mark = false;
            try {
                // Jika username sudah ada didalam db
                while(temp.getRes().next()){
                    scoreSkor = Integer.parseInt(temp.getRes().getString(2));
                    scoreStanding = Integer.parseInt(temp.getRes().getString(3));
                    mark = true;
                }
            } catch (Exception ex) {
                Logger.getLogger(HomeWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Jika user belum ada di db, set skor dan standing 0
            if(!mark) game.setScore(0,0);
            else // Jika user sudah ada, set skor dan standing yang sudah ada
                game.setScore(scoreSkor, scoreStanding);
            
            
            // Game starting
            game.StartGame(gw);
            this.setVisible(false); // dispose or set false for this frame
            this.dispose();
        }
    }//GEN-LAST:event_PlayActionPerformed

    private void QuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitActionPerformed
        // TODO add your handling code here:
        dispose();
        showCredits(); // Panggil method untuk menampilkan frame Credits
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram(); // Panggil method untuk mengakhiri program
            }
        });

        timer.setRepeats(false); // Hanya menjalankan timer sekali
        timer.start();

        dispose(); // Menutup halaman saat ini
    }//GEN-LAST:event_QuitActionPerformed

    private void TableScoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableScoreMouseClicked
        // TODO add your handling code here:
        TableScore.setSelectionBackground(Color.GREEN); // Mengganti warna bg untuk row yang dipilih
        TableScore.setSelectionForeground(Color.BLACK);
        TableScore.setDefaultEditor(Object.class, null); // Biar user tidak bisa mengedit dirow dan column yang terselected 
    }//GEN-LAST:event_TableScoreMouseClicked
    
    private void showCredits() {
        // Kode untuk menampilkan frame Credits
        CreditsWindow creditsFrame = new CreditsWindow();
        creditsFrame.setVisible(true);
    }

    private void exitProgram() {
        // Kode untuk mengakhiri program
        dispose();
        System.exit(0);
    }
    
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
            java.util.logging.Logger.getLogger(HomeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Play;
    private javax.swing.JButton Quit;
    private javax.swing.JTable TableScore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField usernameInput;
    // End of variables declaration//GEN-END:variables
}
