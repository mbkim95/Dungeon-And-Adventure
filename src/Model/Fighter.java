package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Controller.Controller;

public class Fighter extends Player {

	public Fighter(Controller gameScreen) {

		super(gameScreen);
		movingShot = false;
		hp = 200;
		mp = 200;
		speed = 4.0f;
		power = 20;
		shooted = true;
	}

	public void draw(Graphics2D g, BufferedImage img) {
		if (status == 0) {
			int sx = (frameList.get(nowFrame) % 1) * 51;
			int sy = (frameList.get(nowFrame) / 1) * 131;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 51, 131, 7);
		} else if (status == 1 && run == false) {
			int sx = (frameList.get(nowFrame) % 8) * 77;
			int sy = (frameList.get(nowFrame) / 8) * 131;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 77, 131, 7);
		} else if (status == 1 && run == true) {
			int sx = (frameList.get(nowFrame) % 6) * 119;
			int sy = (frameList.get(nowFrame) / 6);
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 119, 106, 7);
		} else if (status == 2 && attack1 == true) {
			int sx = (frameList.get(nowFrame) % 7) * 170;
			int sy = (frameList.get(nowFrame) / 7) * 129;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 170, 129, 7);
		} else if (status == 2 && attack2 == true) {
			int sx = (frameList.get(nowFrame) % 5) * 107;
			int sy = (frameList.get(nowFrame) / 5) * 148;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 107, 148, 7);
		} else if (status == 4 && skill1 == true) {
			int sx = (frameList.get(nowFrame) % 4) * 120;
			int sy = (frameList.get(nowFrame) / 4) * 125;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 120, 125, 7);
		} else if (status == 4 && skill2 == true) {
			int sx = (frameList.get(nowFrame) % 5) * 210;
			int sy = (frameList.get(nowFrame) / 5) * 210;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, dir == 1 ? (int) x + 65 : (int) x - 65, (int) y, sx, sy, 210, 210, 7);
		} else if (status == 4 && skill3 == true) {
			int sx = (frameList.get(nowFrame) % 7) * 69;
			int sy = (frameList.get(nowFrame) / 7) * 138;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 69, 138, 7);
		} else if (status == 3 && hp > 0) {
			int sx = (frameList.get(nowFrame) % 2) * 66;
			int sy = (frameList.get(nowFrame) / 2) * 120;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, dir == 1 ? (int) x + 6 : (int) x - 6, (int) y + 4, sx, sy, 66, 120, 7);
		}
	}

	public void process(int cnt) {

		super.process(cnt);
		if (status == 2 && nowFrame == 7 && shooted) {
			// gameScreen.shootPlayer();
			shooted = false;
		}
	}

	public void setIdle() {

		frameList.clear();
		frameDelay.clear();
		frameList.add(0);
		frameDelay.add(5l);

		nowFrame = 0;
		nowDelay = frameDelay.get(nowFrame);
		isRoof = true;

		status = 0;

		shooted = true;
		skill1 = false;
		skill2 = false;
		skill3 = false;
	}

	public void setWalk() {
		if (run == false) {

			long[] delay = { 4l, 4l, 4l, 5l, 4l, 5l, 4l, 5l };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 8; i++) {
				frameList.add(dir == 1 ? i : 7 - i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = true;

			status = 1;

			shooted = true;
		} else if (run == true) {
			long[] delay = { 4l, 4l, 4l, 5l, 4l, 5l };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 6; i++) {
				frameList.add(dir == 1 ? i : 5 - i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = true;

			status = 1;

			shooted = true;
		}
	}

	public void setAttack() {
		if (attack1 == true) {
			long[] delay = { 4, 4, 5, 3, 3, 3, 3 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 7; i++) {
				frameList.add(dir == 1 ? i : 6 - i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = 0;

			status = 2;
			attack2 = false;
		} else if (attack2 == true) {
			long[] delay = { 2, 4, 10, 11, 3 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 5; i++) {
				frameList.add(dir == 1 ? i : 4 - i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = 0;

			status = 2;

		}
	}

	public void setSkill() {
		if (skill1 && mp - 25 >= 0) {
			long[] delay = { 4, 13, 11, 8 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 4; i++) {
				frameList.add(dir == 1 ? i : 3 - i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = 0;

			status = 4;
			power = 50;
			mp -= 25;
		} else if (skill2 && mp - 60 >= 0) {
			long[] delay = { 4, 12, 10, 5, 2 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 5; i++) {
				frameList.add(dir == 1 ? i : 4 - i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = 0;

			status = 4;
			power = 100;

			mp -= 60;

		} else if (skill3 && hp - 50 > 0) {
			long[] delay = { 12, 12, 12, 12, 12, 12, 12 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 7; i++) {
				frameList.add(dir == 1 ? i : 6 - i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = 0;

			status = 4;
			if (mp < 200 && mp + 25 < 200)
				mp += 25;
			else if (mp < 200 && mp + 25 > 200)
				mp = 200;			
			
			hp -= 50;
		}

	}

	public void setDead() {

		if (hp > 0) {
			long[] delay = { 21, 22 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 2; i++) {
				frameList.add(dir == 1 ? i : 1 - i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = 0;

			status = 3;
		} else {
			frameList.clear();
			frameDelay.clear();
			for (int i = 90; i < 100; i++) {
				frameList.add(dir == 1 ? i : 99 - i + 90);
				frameDelay.add(10l);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = -1;

			status = 3;
		}
	}

	public void useHP_Portion() {
		if (hp_portion.cnt > 0) {
			hp_portion.cnt--;
			if (hp + hp_portion.recovery > 200)
				this.hp = 200;
			else
				this.hp += hp_portion.recovery;
		}

	}

	public void useMP_Portion() {
		if (mp_portion.cnt > 0) {
			mp_portion.cnt--;
			if (mp + mp_portion.recovery > 200)
				this.mp = 200;
			else
				this.mp += mp_portion.recovery;
		}

	}
}
