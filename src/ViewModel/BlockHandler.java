/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import Model.Block;
import static Model.Resolution.Settings.GAME_HEIGHT;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
public class BlockHandler {
    // =======      VARIABLE
    
    private final Random rand = new Random(); // Init Func of random
    
    private ArrayList<Block> blocks = new ArrayList<>();     // Var for list of block
    
    private int countBlock = 0,                              // To count the number of blocks
                BlockMax = 10,                               // Max Of Block in frame
                BlockWidth = rand.nextInt(351) + 100,   // Width of every block created
                tempWidth = 0,                               // Var to save the previous width
                tempCalc = rand.nextInt(20) + 1,
                Calc = rand.nextInt(20) + 1;
    
    // =======
    
    // Update position of block
    public void updatePosBlock(){
        Iterator<Block> itr = blocks.iterator(); // iterator untuk setiap obstacle
        
        // When data block is not null
        while(itr.hasNext()) {
            Block ob = itr.next();
            if(ob.getY() < 0){ // Checking, if pos of Y beyond the top screen of the game
                itr.remove();  // remove block and ...
                countBlock--;  // decrement the block
            } else ob.updatePosColl(); // If pos of Y no beyond the top screen of the game
            
        }
    }
    
    // Rendering block...
    public void renderBlock(Graphics g){
        for (Block block : blocks) {
            block.render(g);
        }
    }
    
    // Adding the block
    public void addObstacle() throws IOException {
        // If the count of block less than max of block, then ...
        if(countBlock < BlockMax){
            if(countBlock > 1){
                // jika jumlah obstacle lebih dari 1
                
                // Assign value tempwidth from blockwidth
                tempWidth = BlockWidth;
                
                // This statement if prev score is 1, ...
                if (this.tempCalc == 1) {
                    // Random Number width now,
                    // and accompanied by the condition that the current block to be made is not the same 
                    // as the previous width or the current width is not longer than the previous width
                    do {
                        BlockWidth = rand.nextInt(401) + 100;
                    }while (BlockWidth == tempWidth || BlockWidth > tempWidth);
                } else { // This statement if prev score is not 1, ...
                    do {
                        BlockWidth = rand.nextInt(401) + 100;
                    }while (BlockWidth == tempWidth);
                }
                // If the width now less than prev width, random score, with the following conditions
                if (BlockWidth < tempWidth){
                    this.tempCalc = rand.nextInt(this.tempCalc + this.tempCalc + 10) + 10;
                } else if (BlockWidth > tempWidth) { // If the width now greater than prev width, random score, with the following conditions
                    do {
                        Calc = rand.nextInt(((this.tempCalc + 1) - (this.tempCalc / 2))) + 1;
                    } while (Calc == this.tempCalc || Calc > this.tempCalc);
                    this.tempCalc = Calc;
                }
            } else { // jika jumlah obstacle tidak lebih dari 1
                tempWidth = BlockWidth;
                if (this.tempCalc == 1) {
                    do {
                        BlockWidth = rand.nextInt(401) + 100;
                    }while (BlockWidth == tempWidth || BlockWidth > tempWidth);
                } else {
                    do {
                        BlockWidth = rand.nextInt(401) + 100;
                    }while (BlockWidth == tempWidth);
                }
                
                if (BlockWidth < tempWidth){
                    this.tempCalc = rand.nextInt(this.tempCalc + this.tempCalc + 10) + 10;
                } else if (BlockWidth > tempWidth) {
                    do {
                        Calc = rand.nextInt(((this.tempCalc + 1) - (this.tempCalc / 2))) + 1; // Menghasilkan angka acak antara 10 dan 19
                    } while (Calc == this.tempCalc || Calc > this.tempCalc);
                    this.tempCalc = Calc;
                }
            }
            // Init/create block
            Block block = new Block(0, GAME_HEIGHT, BlockWidth, 50);
            block.setScore(this.tempCalc); // set score
            blocks.add(block);    // add block to list
            countBlock++;           // increments the number of blocks
        }
    }

    // Get the block
    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
