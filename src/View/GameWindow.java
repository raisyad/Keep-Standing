/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import static Model.Resolution.Settings.GAME_HEIGHT;
import static Model.Resolution.Settings.GAME_WIDTH;
import ViewModel.Game;
import ViewModel.KeyInputs;
import java.awt.Canvas;
import javax.swing.JFrame;

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
public class GameWindow extends Canvas {
    
    // properti JFrame
    JFrame frame;
    
    // Constructor with value
    public GameWindow(Game game){
        String title = "Keep Standing Uhuy | InGame"; // Set Title
        frame = new JFrame(title); // Create frame with assign the apk title
        
        // Add key listener for the movement of player inGame
        frame.addKeyListener(new KeyInputs(game));
        
        // Add the game 
        frame.add(game); 
        
        // Set the resolution of the game
        frame.setSize(GAME_WIDTH, GAME_HEIGHT);
        
        // Set location frame
        frame.setLocationRelativeTo(null);
        
        // Set so that the user cannot maximize the frame
        frame.setResizable(false); // set resizeable frame
        
        // Set visible frame to true / set the frame to show
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void CloseWindow() {
        // menutup window/frame
        frame.setVisible(false); // membuat frame invisible
        frame.dispose(); // membersihkan frame
    }
}
