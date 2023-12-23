/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import ViewModel.Game.STATE;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
public class KeyInputs implements KeyListener {
    // mewarisi interface KeyListener agar bisa menerima input keyboard
    
    // properti game
    private final Game game;

    public KeyInputs(Game game) {
        // konstruktor
        this.game = game; // set game
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            game.getPlayer().setJumpUp(true); // atas
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            game.getPlayer().setMoveLeft(true); // kiri
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            game.getPlayer().setCrouch(true); // bawah
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            game.getPlayer().setMoveRight(true); // kanan
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            game.gameState = STATE.GameOver;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            game.getPlayer().setJumpUp(false); // atas
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            game.getPlayer().setMoveLeft(false); // kiri
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            game.getPlayer().setCrouch(false); // bawah
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            game.getPlayer().setMoveRight(false); // kanan
        }
    }
}
