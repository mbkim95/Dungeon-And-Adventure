package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Controller.Controller;

public abstract class Player extends Character implements useSkill, usePortion{

	public boolean movingShot;
	public float speed;
	public int power;
	boolean shooted;
	public boolean run;
	public boolean attack1;
	public boolean attack2;
	public boolean skill1;
	public boolean skill2;
	public boolean skill3;

	public int hp;
	public int mp;
	
	public Portion hp_portion = new HP_Portion();
	public Portion mp_portion = new MP_Portion();

	public Player(Controller gameScreen) {
		super(gameScreen);
	}

	public void draw(Graphics2D g, BufferedImage img) {

	}

	public void process(int cnt) {

		super.process(cnt);
		if (status == 2 && nowFrame == 7 && shooted) {
			// gameScreen.shootPlayer();
			shooted = false;
		}
	}

	public void setIdle() {

	}

	public void setWalk() {

	}

	public void setAttack() {

	}

	public void setSkill() {

	}

	public void setDead() {

	}

	
	public void useMP_Portion() {

	}

	public void useHP_Portion() {
		// TODO Auto-generated method stub
		
	}
}
