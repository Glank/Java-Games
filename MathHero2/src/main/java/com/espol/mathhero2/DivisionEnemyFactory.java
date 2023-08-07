package com.espol.mathhero2;

public class DivisionEnemyFactory implements EnemyFactory {

    public DivisionEnemyFactory() {
    }

    @Override
    public Enemy createSmallEnemy() {
        return new Division();
    }

    @Override
    public Enemy createBigEnemy() {
        
        return new BigDivision();
            
    }
    
}
