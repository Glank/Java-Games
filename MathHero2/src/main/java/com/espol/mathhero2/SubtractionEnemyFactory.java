package com.espol.mathhero2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class SubtractionEnemyFactory implements EnemyFactory {

    public SubtractionEnemyFactory() {
    }

    @Override
    public Enemy createSmallEnemy() {
        return new Subtraction();
    }

    @Override
    public Enemy createBigEnemy() {
        return new BigSubtraction();
    }
    
}
