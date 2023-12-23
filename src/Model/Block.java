/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import static Model.GameSetting.Settings.ObsSpeed;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

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
public class Block extends GameObject {
    // =======      VARIABLE
    
    // Var assign value of image for block
    private BufferedImage color;
    
    // Init rand for the random value
    Random rand = new Random();
    
    private int skor = rand.nextInt(10) + 1,    // Var skor and assign value from rand between 10 - 1
                standing = 1;                        // Var standing and default value 1 (bcs, when user get on block, standing user will be increased by 1)
    private boolean stepped = false;                 // Var stepped, Checks whether the user stepped on or not
    
    // =======
    
    public Block(float x, float y, int width, int height) throws IOException {
        // konstruktor
        super(x, y, width, height);
        renderBg();
    }
    
    private void renderBg() throws IOException {
        color = ImageIO.read(new File("resources/img/pijakan_bg.jpg"));
    }
    
    // Procedure for updating positioning and collision
    public void updatePosColl() {
        BlockMovement();
        RePosCollision();
    }
    
    @Override
    public void render(Graphics g) {
        // Render Image for Block BG
        g.drawImage(color, (int)x, (int)y, width, height, null);
        
        Font orbitronFont = new Font("Minecraft", Font.PLAIN, 18);
        g.setFont(orbitronFont);
        g.setColor(Color.RED);
        g.drawString(String.valueOf(this.skor), (int)x + width + 25, (int)y + (height/2));
    }
    
    // For direct the block from the bottom to up
    private void BlockMovement(){
        y -= ObsSpeed;
    }
    
    // Return value score when user stepped on block
    public int getScore(){
        return skor;
    }
    
    // Set value score when user stepped on block
    public void setScore(int skor){
        this.skor = skor;
    }
    
    public int getStanding(){
        // mengambil nilai standing si obstacle
        return standing;
    }
    
    // Return value standing when user stepped on block
    public boolean getStepped(){
        return stepped;
    }
    
    // Set value standing when user stepped on block
    public void setStepped (boolean stepped) {
        this.stepped = stepped;
    }
    
    // Get x value
    public float getX () {
        return x;
    }
    
    // Get y value
    public float getY () {
        return y;
    }
}
