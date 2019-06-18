package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Controller.Controller;

public class Warrior extends Player {

	public Warrior(Controller gameScreen) {

		super(gameScreen);
		movingShot = false;
		hp = 200;
		mp = 200;
		speed = 3.2f;
		power = 25;
		shooted = true;

	}

	public void draw(Graphics2D g, BufferedImage img) {
		if (status == 0) {
			int sx = (frameList.get(nowFrame) % 1) * 62;
			int sy = (frameList.get(nowFrame) / 1) * 124;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 62, 124, 7);
		}else if (status == 1 && run == false) {
			int sx = (frameList.get(nowFrame) % 8) * 68;
			int sy = (frameList.get(nowFrame) / 8) * 124;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 68, 124, 7);
		} else if (status == 1 && run == true) {
			int sx = (frameList.get(nowFrame) % 8) * 126;
			int sy = (frameList.get(nowFrame) / 8) * 111;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 126, 111, 7);
		} else if (status == 2 && attack1 == true) {
			int sx = (frameList.get(nowFrame) % 9) * 200;
			int sy = (frameList.get(nowFrame) / 9) * 117;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 200, 117, 7);
		} else if (status == 2 && attack2 == true) {
			int sx = (frameList.get(nowFrame) % 10) * 200;
			int sy = (frameList.get(nowFrame) / 10) * 104;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 200, 104, 7);
		} else if (status == 4 && skill1 == true) {
			int sx = (frameList.get(nowFrame) % 9) * 200;
			int sy = (frameList.get(nowFrame) / 9) * 176;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 200, 176, 7);
		} else if (status == 4 && skill2 == true) {
			int sx = (frameList.get(nowFrame) % 9) * 300;
			int sy = (frameList.get(nowFrame) / 9) * 200;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, dir == 1 ? (int) x + 30 : (int) x - 30, (int) y, sx, sy, 300, 200, 7);
		} else if (status == 4 && skill3 == true) {
			int sx = (frameList.get(nowFrame) % 16) * 86;
			int sy = (frameList.get(nowFrame) / 16) * 128;
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth(), img.getHeight());
			gameScreen.drawImageClip(g, imgZoom, dir == 1 ? (int) x + 6 : (int) x - 6, (int) y + 4, sx, sy, 86, 128, 7);
		} else if (status == 3 && hp > 0) {
			int sx = (frameList.get(nowFrame) % 4) * 66;
			int sy = (frameList.get(nowFrame) / 4) * 120;
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

			long[] delay = { 4l, 4l, 4l, 5l, 4l, 5l, 4l, 5l, 4l, 5l };

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
			long[] delay = { 4l, 4l, 4l, 5l, 4l, 5l, 4l, 5l, 4l, 5l };

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
		}
	}

	public void setAttack() {
		if (attack1 == true) {
			long[] delay = { 2, 4, 3, 3, 3, 3, 3, 3, 3 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 9; i++) {
				frameList.add(dir == 1 ? i : 8 - i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = 0;

			status = 2;
			attack2 = false;
		} else if (attack2 == true) {
			long[] delay = { 2, 4, 3, 3, 3, 3, 3, 3, 1, 2 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 10; i++) {
				frameList.add(dir == 1 ? i : 9 - i);
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
		if (skill1 && mp - 20 >= 0) {
			long[] delay = { 2, 4, 3, 3, 3, 3, 3, 3, 3 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 9; i++) {
				frameList.add(dir == 1 ? i : 8 - i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = 0;

			status = 4;
			mp -= 20;
		} else if (skill2 && mp - 40 >= 0) {
			long[] delay = { 3, 6, 6, 8, 6, 4, 2, 4, 15 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 9; i++) {
				frameList.add(dir == 1 ? i : 8 - i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = 0;

			status = 4;
			power = 70;
			mp -= 40;
		} else if (skill3 && hp - 50 > 0) {
			long[] delay = { 2, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 16; i++) {
				frameList.add(dir == 1 ? i : 15 - i);
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
			long[] delay = { 11, 10, 10, 12 };

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
