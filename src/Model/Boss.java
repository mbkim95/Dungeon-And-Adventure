package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Controller.Controller;

public class Boss extends Monster {
	public Boss(Controller gameScreen) {

		super(gameScreen);
		speed = 3.0f;
		power = 25;
		hp = 800;
		life = true;
		vDir = gameScreen.rand.nextInt(10) % 3;
	}

	public void draw(Graphics2D g, BufferedImage img) {
		if (status == 0) {
			int sx = (frameList.get(nowFrame)) * 518;
			int sy = (frameList.get(nowFrame));
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth()/2, img.getHeight()/2);
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 259, 196, 7);
			
		} else if (status == 1) {
			int sx = (frameList.get(nowFrame) % 8) * 340;
			int sy = (frameList.get(nowFrame) / 8);
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth()/2, img.getHeight()/2);
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 340, 210, 7);
		} else if (status == 2) {
			int sx = (frameList.get(nowFrame) % 8) * 390;
			int sy = (frameList.get(nowFrame) / 8);
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth()/2, img.getHeight()/2);
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 390, 325, 7);
		} else if (status == 3) {
			int sx = (frameList.get(nowFrame) % 3) * 300;
			int sy = (frameList.get(nowFrame) / 3);
			BufferedImage imgZoom = gameScreen.getScaledImage(img, img.getWidth()/2, img.getHeight()/2);
			gameScreen.drawImageClip(g, imgZoom, (int) x, (int) y, sx, sy, 300, 206, 7);
		}
	}

	public boolean Process(int cnt) {

		super.process(cnt);

		if (status != 2 && actCnt == 0 && life == true) {
			act = gameScreen.rand.nextInt(10) % 2;
			switch (act) {
			case 0:// 정지
				setIdle();
				break;
			case 1:// 걷는다
				dir = gameScreen.rand.nextInt(10) % 2;
				vDir = gameScreen.rand.nextInt(10) % 3;
				setWalk();
				break;
			case 2:// 휘두른다
				setAttack();
				break;
			case 3:
				setDead();
				break;
			}
			actCnt = 40;
		} else {

			if (actCnt > 0)
				actCnt--;

			if (status == 1) {
				// 이동
				if (dir == 0 && x > 200)
					x -= speed;
				if (dir == 1 && x < 824)
					x += speed;
				if (vDir == 0 && y > 400)
					y -= speed;
				if (vDir == 1 && y < 630)
					y += speed;
			}
		}

		return true;
	}

	public void setIdle() {

		frameList.clear();
		frameDelay.clear();
		frameList.add(0);
		frameDelay.add(8l);

		nowFrame = 0;
		nowDelay = frameDelay.get(nowFrame);
		isRoof = true;

		status = 0;
	}

	public void setWalk() {

		long[] delay = { 7l, 6l, 5l, 6l, 7l, 6l };

		frameList.clear();
		frameDelay.clear();
		for (int i = 0; i < 6; i++) {
			frameList.add(dir == 0 ? 5 - i : i);
			frameDelay.add(delay[i]);
		}

		nowFrame = 0;
		nowDelay = frameDelay.get(nowFrame);
		isRoof = true;

		status = 1;
	}

	public void setAttack() {

		long[] delay = { 8, 7, 18, 7, 4, 3, 15, 10 };

		frameList.clear();
		frameDelay.clear();
		for (int i = 0; i < 8; i++) {
			frameList.add(dir == 0 ? 7 - i : i);
			frameDelay.add(delay[i]);
		}

		nowFrame = 0;
		nowDelay = frameDelay.get(nowFrame);
		isRoof = false;
		nextAnimation = 0;

		status = 2;
	}

	public void setDead() {

		if (hp > 0) {
			long[] delay = {5, 12 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 2; i++) {
				frameList.add(dir == 0 ? 2 - i  : i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = 0;

			status = 3;
			life = true;
		} else {
			long[] delay = { 13, 12, 10 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 3; i++) {
				frameList.add(dir == 0 ? 2 - i : i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = -1;

			status = 3;
			life = false;
		}
	}
}
