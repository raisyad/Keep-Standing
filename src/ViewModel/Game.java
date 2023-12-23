/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import Model.Player;
import static Model.Resolution.Settings.GAME_WIDTH;
import Model.SoundManager;
import Model.TableScore;
import View.GameWindow;
import View.HomeWindow;
import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
public class Game extends JPanel implements Runnable {
    // mengimplementasikan Runnable java untuk membuat thread
    
    // properti thread
    private Thread gameThread;
    
    private GameWindow window; // window game
    private Clip audio; // backsound
    
    // Attr game
    private final Player player; // player
    private final BlockHandler blockHandler; // obstacle
    private String username; // username
    
    private int skor, // skor
                standing, // standing
                counterJeda = 0; // Gap addObstacle
    
    private boolean markFirst = true, // Var mark for the game starting (early)
                    running = false; // deteksi game berjalan
    
    public enum STATE{
        Game,
        GameOver
    }
    
    public Game() throws IOException{
        
        // Player random pos
        Random rand = new Random();
        int playerPos = rand.nextInt(1200 - 800) + 800;
        this.player = new Player(GAME_WIDTH - playerPos, 200);
        
        // Init block
        this.blockHandler = new BlockHandler();
        
        // Init sound
        SoundManager bgm = new SoundManager();
        audio = bgm.playSound(this.audio, "sound/starting.wav");
    }
    
    // Set STATE game
    public STATE gameState = STATE.Game;
    
    // Set untuk starting the game
    public synchronized void StartGame(GameWindow gameWindow){
        gameThread = new Thread(this); // Init thread
        gameThread.start(); // Thread dijalankan
        this.window = gameWindow; // Init window
        running = true; // Set running (game berjalan)
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g); // method parent
        player.render(g); // rendered objek player
        blockHandler.renderBlock(g); // rendered objek block
    }
    
    @Override
    public void run() {
        // Selama game masi berjalan
        while(true){
            try {
                updateGame(); // update objek game
                repaint(); // membuat ulang Component
                Thread.sleep(700L/60L); // pause thread
                
                this.skor = player.getScore(); // mengambil value skor
                this.standing = player.getStanding(); // mengambil value standing
                
                // Jika game sudah bukan early atau game tidak baru dimulai
                if (!this.markFirst) {
                    this.counterJeda++;
                }
                
                if(CheckingGameOver() == STATE.GameOver) {
                    // jika state saat ini GameOver
                    isOnGameOver();
                }
                
            } catch (IOException er) {
                System.out.println(er.getMessage());
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, er);
            } catch (InterruptedException er) {
                System.out.println(er.getMessage());
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, er);
            } catch (Exception ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Checking, Does the player touch the top or bottom of the screen ?...
    private STATE CheckingGameOver() {
        if (this.player.getBoundTop().y < -2 || this.player.getBoundBottom().y > 550) {
            return STATE.GameOver;
        }
        return this.gameState;
    }
    
    // State when user is game over
    public void isOnGameOver() throws InterruptedException, Exception {
        SoundManager bgm = new SoundManager(); 
        bgm.stopSound(this.audio); // stop bgm
        EndOfGame(); // simpan skor adapt dan fall
        closeWindow(); // tutup window
        new HomeWindow().setVisible(true); // menampilkan menu
        stopGame(); // stop game
    }
    
    // update objek dalam game
    public void updateGame() throws IOException{
        if (this.markFirst) { // Jika game baru dimulai
            this.markFirst = false;
            blockHandler.addObstacle(); // menambah obstacle
        } else if (this.counterJeda == 100) { // Jika statement game baru dimulainya sudah masuk ke fase false
            this.counterJeda = 0;
            blockHandler.addObstacle(); // menambah obstacle
        }
        
        blockHandler.updatePosBlock(); // mengupdate kondisi obstacle
        player.update(blockHandler.getBlocks()); // mengupdate kondisi player
    }
    
    // Menghentikan permainan
    public synchronized void stopGame() throws InterruptedException {
        gameThread.join(); // menghentikan thread
        running = false; // set tidak berjalan
    }
    
    public void EndOfGame() throws Exception {
        // Init and play game over sound
        SoundManager gobgm = new SoundManager();
        audio = gobgm.playSound(this.audio, "sound/game_over.wav");
        // Save data/score
        saveData();
        
        // menampilan panel game over
        JOptionPane.showMessageDialog(null, "Username : " + this.username + "\nSkor : " + this.skor + "\nStanding : " + this.standing, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
        // berhentikan sound saat panel game over hilang
        gobgm.stopSound(this.audio);
    }
    
    // Save data
    public void saveData() throws Exception {
        TableScore tscore = new TableScore();
        tscore.insertData(this.username, this.skor, this.standing);
    }
    
    // Menutup window/frame
    public void closeWindow() {
        // menutup window
        window.CloseWindow();
    }
    
    // Set value player
    public Player getPlayer(){
        return this.player;
    }
    
    // Set value username
    public void setUsername(String username) {
        // mengeset username game
        this.username = username;
    }
    
    // Set value score
    public void setScore(int skor, int standing) {
        // mengeset skor player di awal game
        this.player.setScore(skor);
        this.player.setStanding(standing);
    }
    
    
    
}
