/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import static Model.GameSetting.Settings.GAME_SPEED;
import static Model.GameSetting.Settings.gravity;
import static Model.GameSetting.Settings.jumpStrength;
import static Model.GameSetting.Settings.playerSpeed;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

public class Player extends GameObject {
    // =======      VARIABLE
    
    // Key or mark when user ...
    private boolean MoveUp,     // Move to up
                    MoveRight,  // Move to Right
                    MoveLeft,   // Move to Left
                    Crouch,     // Crouching
                    Air = true; // In Air (terbang)
    
    
    private BufferedImage backgroundImage,          // Var assign value for bg image
                          idleCharacterImg,         // Var assign value for character when idle
                          crouchCharacterImg,       // Var assign value for character when crouch or pressed the arrow down
                          jumpCharacterImg,         // Var assign value for character when jump
                          fallCharacterImg;         // Var assign value for character when fall
    private BufferedImage[] walkingCharacterImgs;   // Var assign value for character when walking or run (same)
    
    
    private boolean isCrouching = false,    // Var for checking when user crouching or not
                    isJumping = false,      // Var for checking when user jumping or not
                    isFalling = false,      // Var for checking when user falling or not
                    start = false,          // Var for the starting game
                    jumped = false;         // Mark JJump
    
    private int walkingFrameIndex = 0,      // Var to select index of image when user walking or running
                skor = 0,                   // Var to assign skor
                standing = 0,               // Var to assign standing
                tempY = 0;                  // Var for tempY
    
    private float xSpeed = 0,       // Var speed of X
                  jump = 0;         // Var jump
    
    
    
    
    // =======
    
    // Constructor with value
    public Player(int x, int y) {
        super(x, y, 50, 50);
        
        // Load image
        try {
            // Load image and assign to var
            
            // Load image and assign for background on game
            backgroundImage = ImageIO.read(new File("resources/img/bg_game.jpg"));
            
            // Load Image and assign for the character idle
            idleCharacterImg = ImageIO.read(new File("resources/img/idle.png"));
            
            // Load image and assign for character walking
            walkingCharacterImgs = new BufferedImage[2];
            walkingCharacterImgs[0] = ImageIO.read(new File("resources/img/run1.png"));
            walkingCharacterImgs[1] = ImageIO.read(new File("resources/img/run2.png"));
            
            // Load image and assign for character crouch
            crouchCharacterImg = ImageIO.read(new File("resources/img/crouch.png"));
            
            // Load image and assign for character jumping
            jumpCharacterImg = ImageIO.read(new File("resources/img/jump.png"));
            
            // Load image and assign for character falling
            fallCharacterImg = ImageIO.read(new File("resources/img/fall.png"));
        } catch (IOException er) {
            System.out.println(er.getMessage());
        }
    }
    
    // Override method from parent
    @Override
    public void render(Graphics g){
        // load Image bg
        g.drawImage(backgroundImage, 0, 0, null);
        
        // Load Image, when...
        if (isCrouching) {          // Crouching
            g.drawImage(crouchCharacterImg, (int)x, (int)y, width, height, null);
        } else if (isJumping) {     // Jumping
            g.drawImage(jumpCharacterImg, (int)x, (int)y, width, height, null);
        } else if (isFalling) {     // Falling
            g.drawImage(fallCharacterImg, (int)x, (int)y, width, height, null);
        } else if (MoveLeft || MoveRight) { // Walking or Running (same)
            BufferedImage walkingCharacterImg = walkingCharacterImgs[walkingFrameIndex];
            g.drawImage(walkingCharacterImg, (int)x, (int)y, width, height, null);
        } else {                    // Idling
            g.drawImage(idleCharacterImg, (int)x, (int)y, width, height, null);
        }
        
        // Show Skor and standing with settings Color and font
        g.setColor(Color.BLACK);
        Font font = new Font("8-Bit Madness", Font.PLAIN, 16);
        g.setFont(font);
        g.drawString("Skor : " + Integer.toString(this.skor), 840, 30);
        g.drawString("Standing : " + Integer.toString(this.standing), 840, 60);
    }
    
    // Updating positioning of block
    public void update(ArrayList<Block> block){
        updatePosPlayer(block);
        RePosCollision();
    }
    
    // Updating player and block position
    public void updatePosPlayer(ArrayList<Block> ArrOfBlock){
        // When user idle, speed of X set to zero (0)
        if (MoveLeft && MoveRight || !MoveLeft && !MoveRight) xSpeed = 0;
        else if (MoveLeft || MoveRight) { // When user move left or right ...
            if (MoveLeft) xSpeed -= playerSpeed;            // When user pressed or move to left (reduce playerSpeed)
            else if (MoveRight) xSpeed += playerSpeed + 1;  // When user pressed or move to right (increase playerSpeed)
            
            // Code below is transition of walking character load image
            walkingFrameIndex++;
            if (walkingFrameIndex >= walkingCharacterImgs.length) {
                walkingFrameIndex = 0;
            }
        } else walkingFrameIndex = 0; // This is a statement if user not pressed left or right
        
        // Code below is a settings for running or walking speed of character
        if (xSpeed > 6) xSpeed = 6;          // Max 6
        else if (xSpeed < -6) xSpeed = -6;   // And Min is -6
        
        if (!MoveUp && Air) {
            isJumping = false; // Set nilai isJumping kembali ke false saat karakter berhenti melompat
        }
        
        // Jumping
        if (MoveUp && !Air){
            Air = true;
            jump -= jumpStrength;
        }
        
        if(!Air && !CheckIntersect(ArrOfBlock)){
            Air = true;
        }
        
        if (Air) {          // When user is on the air
            jump += gravity;       // Increae gravity to speed of jump
            if (jump > jumpStrength) {
                jump = jumpStrength; // Limit the maximum fall speed according to the strength of the jump
            }
            
            if (jump < 0) {         // If jump value less than 0
                isJumping = true;
                isFalling = false;
            } else {                // If jump value more than 0
                isJumping = false;
                isFalling = true;
            }
        } else {                // When user not jumping or falling
            isJumping = false;
            isFalling = false;
        }
        
        checkCollision(ArrOfBlock); // Checking the collision
        
        x += xSpeed;    // Increase xSpeed to X
        y += jump;      // Incres jump to Y
    }
    
    // Checking, whether the user is in contact with the collision ?
    public boolean CheckIntersect(ArrayList<Block> ArrOfBlock){
        for(Block block : ArrOfBlock) if(getBoundBottom().intersects(block.getCollision())) return true;
        return false;
    }
    
    private void checkCollision(ArrayList<Block> ArrOfBlock) {
        // Loop for every block
        for(Block block : ArrOfBlock){
            // If the player is standing on the block
            if(getBoundBottom().intersects(block.getCollision())){
                Air = false;    // Mark user not on the air
                y = block.getCollision().y - height; // y tempat collision
                
                if(!start) {
                    start = true;
                    tempY = (int) y; // When a new game is start
                }
                
                if(tempY != y) {
                    // jika y player bertabrakan sebelumnya
                    // tidak sama dengan y sekarang
                    if (y < 500) {
                        if (block.getStepped() == false) {
                            skor += block.getScore();
                            standing += block.getStanding();
                            block.setStepped(true);
                        }
                    }
                    tempY = (int) y;
                }
            }
            
            // If the player touches the right beam or the left if the pov of the character
            if (getBoundLeft().intersects(block.getCollision())) {
                xSpeed = GAME_SPEED;
            }
            
            // If the player bumps or will come out of the left frame
            if (getBoundLeft().x < 0) x = 0;
            
            // If the player bumps or will come out of the right frame
            if (getBoundRight().x > 1000) x = 950;
            
            // If the player touches the lower beam or touches the lower beam (upper if from the user) with the character's head
            if (getBoundTop().intersects(block.getCollision())) y = block.getCollision().y + block.getCollision().height;
            
        }
    }
    
    // Create a lower bound
    public Rectangle getBoundBottom(){
        return new Rectangle((int) (x + (width / 2)-(width / 4)), (int) (y + (height / 2)), width / 2, height / 2);
    }
    
    // Create a upper bound
    public Rectangle getBoundTop(){
        return new Rectangle((int) (x + (width / 2)-(width / 4)), (int) (y), width / 2, height / 2);
    }
    
    // Create a Right bound
    public Rectangle getBoundRight(){
        return new Rectangle((int) x + width - 5, (int)y + 5, 5, height - 10);
    }
    
    // Create a Left bound
    public Rectangle getBoundLeft(){
        return new Rectangle((int) x, (int)y + 5, 5, height - 10);
    }
    
    // Set player to left
    public void setMoveLeft(boolean MoveLeft) {
        this.MoveLeft = MoveLeft;
    }
    
    // Set player to up
    public void setJumpUp(boolean MoveUp) {
        this.MoveUp = MoveUp;
    }
    
    // Set player to right
    public void setMoveRight(boolean MoveRight) {
        this.MoveRight = MoveRight;
    }
    
    // Set Player to crouch
    public void setCrouch(boolean Crouch) {
        this.Crouch = Crouch;
        
        if (Crouch) isCrouching = true;
        else isCrouching = false;
    }
    
    // Set player score
    public void setScore(int skor) {
        this.skor = skor;
    }
    
    // Set player standing
    public void setStanding(int standing) {
        this.standing = standing;
    }
    
    // Get player score
    public int getScore() {
        return this.skor;
    }
    
    // Get Player standing
    public int getStanding() {
        return this.standing;
    }
}
