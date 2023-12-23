package Model;

import java.awt.Graphics;
import java.awt.Rectangle;
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
public abstract class GameObject {

    // Attr positioning
    protected float x,
                    y;
    
    // Attr Ukuran
    protected int width,
                    height;
    
    // Collsion
    protected Rectangle collision;
    
    // Constructor with value
    public GameObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        // Created the Collision
        this.collision = new Rectangle((int)x, (int)y, width, height);
    }
    
    // Mereposisi / Updated the position of collision
    protected void RePosCollision(){
        this.collision.x = (int) x;
        this.collision.y = (int) y;
    }
    
    // Get box-shaped collision
    public Rectangle getCollision() {
        return this.collision;
    }
    
    public abstract void render(Graphics g);
    
}
