/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.mathhero2;

public class AdditionEnemyFactory implements EnemyFactory {
    @Override
    public Enemy createSmallEnemy() {
        return new Addition();
    }

    @Override
    public Enemy createBigEnemy() {
        return new BigAddition();
    }
}
