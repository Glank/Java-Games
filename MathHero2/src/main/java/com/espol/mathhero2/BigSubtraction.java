package com.espol.mathhero2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class BigSubtraction extends Enemy {
    protected String problem;
	protected int solution;

	public BigSubtraction()
	{
		super(.8);
		int n1 = (int)(Math.random()*9)+1;
		int n2 = (int)(Math.random()*9)+1;
		solution = n1;
		problem = ""+(n1+n2)+"-"+n2;
		color = java.awt.Color.RED;
	}

	public String getProblem()
	{
		return problem;
	}

	public int getSolution()
	{
		return solution;
	}
}
