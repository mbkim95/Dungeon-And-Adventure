package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Controller.Controller;

public class Goblin2 extends Monster {
	
	public Goblin2(Controller gameScreen) {

		super(gameScreen);
		speed = 2.5f;
		power = 8;
		hp = 250;
		life = true;
		vDir = gameScreen.rand.nextInt(10) % 3;
	}

	public void draw(Graphics2D g, BufferedImage img) {
		if (status == 0) {
			int sx = (frameList.get(nowFrame)) * 50;
			int sy = (frameList.get(nowFrame));
			gameScreen.drawImageClip(g, img, (int) x, (int) y, sx, sy, 50, 76, 7);
		} else if (status == 1) {
			int sx = (frameList.get(nowFrame) % 6) * 61;
			int sy = (frameList.get(nowFrame) / 6);
			gameScreen.drawImageClip(g, img, (int) x, (int) y, sx, sy, 61, 73, 7);
		} else if (status == 2) {
			int sx = (frameList.get(nowFrame) % 4) * 110;
			int sy = (frameList.get(nowFrame) / 4);
			gameScreen.drawImageClip(g, img, (int) x, (int) y, sx, sy, 110, 120, 7);
		} else if (status == 3) {
			int sx = (frameList.get(nowFrame) % 5) * 93;
			int sy = (frameList.get(nowFrame) / 5) * 72;
			gameScreen.drawImageClip(g, img, (int) x, (int) y, sx, sy, 93, 72, 7);
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

		long[] delay = { 10l, 5l, 20l, 5l };

		frameList.clear();
		frameDelay.clear();
		for (int i = 0; i < 4; i++) {
			frameList.add(dir == 0 ? 3 - i : i);
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
			long[] delay = { 13, 12 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 2; i++) {
				frameList.add(dir == 0 ? 1 - i+3 : i);
				frameDelay.add(delay[i]);
			}

			nowFrame = 0;
			nowDelay = frameDelay.get(nowFrame);
			isRoof = false;
			nextAnimation = 0;

			status = 3;
			life = true;
		} else {
			long[] delay = { 13, 12, 10, 13, 11 };

			frameList.clear();
			frameDelay.clear();
			for (int i = 0; i < 5; i++) {
				frameList.add(dir == 0 ? 4 - i : i);
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
