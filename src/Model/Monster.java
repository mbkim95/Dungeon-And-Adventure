package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Controller.Controller;

public abstract class Monster extends Character {
	
	public float speed;	
	public boolean life;
	int actCnt;
	int vDir;
	public int hp;
	public int power;
	int act; // 행동변수

	public Monster(Controller gameScreen) {

		super(gameScreen);		
	}
	
	public Monster() {

			
	}

	public void draw(Graphics2D g, BufferedImage img) {
		
	}

	public boolean Process(int cnt) {

		super.process(cnt);
				
		return true;
	}

	public void setIdle() {
		
	}

	public void setWalk() {
	
	}

	public void setAttack() {
		
	}

	public void setDead() {
		
	}

}
