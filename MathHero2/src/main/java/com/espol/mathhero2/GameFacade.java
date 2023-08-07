package com.espol.mathhero2;
public class GameFacade {

    private MathHero mathHero;

    public GameFacade() {
        this.mathHero = new MathHero();
    }

    public void startGame() {
        this.mathHero.makeTestWindow();
    }
}