package Controller;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

import Model.Boss;
import Model.DIFF;
import Model.Fighter;
import Model.Goblin;
import Model.Goblin2;
import Model.Goblin3;
import Model.Home;
import Model.NPC;
import Model.Ox;
import Model.Player;
import Model.PuzzleMap;
import Model.Quiz;
import Model.RandomBox;
import Model.RescueMap;
import Model.Thief;
import Model.Warrior;
import Model.Wave;
import View.MainFrame;

@SuppressWarnings("serial")
public class Controller extends Canvas implements Runnable {

	/*
	 * public final static int UP_PRESSED = 0x001; public final static int
	 * DOWN_PRESSED = 0x002; public final static int LEFT_PRESSED = 0x004;
	 * public final static int RIGHT_PRESSED = 0x008; public final static int
	 * FIRE_PRESSED = 0x010;
	 */

	MainFrame mainFrame;
	BufferedImage buff;// 더블버퍼링용 백버퍼
	Graphics2D gc2;// 더블버퍼링용 그래픽 컨텍스트
	SoundController sound = new SoundController();
	int delay;// 루프 딜레이. 1/950초 단위.
	long pretime;// 루프 간격을 조절하기 위한 시간 체크값

	public Random rand;
	// kh1
	public BufferedImage title = makeBufferedImage("rsc/Title.png");

	BufferedImage homeImg;
	BufferedImage bgImg;
	BufferedImage bgImg2;
	BufferedImage bgImg3;
	BufferedImage bgImg4;
	BufferedImage bgImg5;
	BufferedImage bgImg6;
	BufferedImage bgImg7;
	BufferedImage bgImg8;
	BufferedImage bgImg9;
	BufferedImage bgImg10;
	BufferedImage bgImg11;
	BufferedImage bgImg12;
	BufferedImage bgImg13;
	BufferedImage bgImg14;
	BufferedImage bgImg15;
	BufferedImage bgImg16;

	BufferedImage pzImg;
	BufferedImage pzImg2;
	BufferedImage pzImg3;
	BufferedImage pzImg4;
	BufferedImage pzImg5;
	BufferedImage pzImg6;
	BufferedImage pzImg7;

	BufferedImage hud;
	BufferedImage character_text;
	BufferedImage hp_portion;
	BufferedImage mp_portion;
	BufferedImage key_1;
	BufferedImage key_2;
	BufferedImage key_a;
	BufferedImage key_s;
	BufferedImage key_d;
	BufferedImage key_f;
	BufferedImage num0;
	BufferedImage num1;
	BufferedImage num2;
	BufferedImage num3;
	BufferedImage num4;
	BufferedImage num5;
	BufferedImage num6;
	BufferedImage num7;
	BufferedImage num8;
	BufferedImage num9;

	BufferedImage icon1;
	BufferedImage icon1_delay;
	BufferedImage icon2;
	BufferedImage icon2_delay;
	BufferedImage icon3;
	BufferedImage icon3_delay;

	BufferedImage text1;
	BufferedImage text2;
	BufferedImage text3;
	BufferedImage ox;
	BufferedImage diff;
	BufferedImage randombox;

	// 플레이어이미지
	BufferedImage pstand;// 애니메이션용 이미지
	BufferedImage pstand_flipx;// 애니메이션용 이미지

	BufferedImage pwalk;// 애니메이션용 이미지
	BufferedImage pwalk_flipx;// 애니메이션용 이미지(왼쪽보기)

	BufferedImage prun;// 애니메이션용 이미지
	BufferedImage prun_flipx;// 애니메이션용 이미지(왼쪽보기)

	BufferedImage pdamage;// 애니메이션용 이미지
	BufferedImage pdamage_flipx;// 애니메이션용 이미지(왼쪽보기)

	BufferedImage pattack1;// 애니메이션용 이미지
	BufferedImage pattack1_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage pattack2;// 애니메이션용 이미지
	BufferedImage pattack2_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage pskill1;// 애니메이션용 이미지
	BufferedImage pskill1_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage pskill2;// 애니메이션용 이미지
	BufferedImage pskill2_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage pskill3;// 애니메이션용 이미지
	BufferedImage pskill3_flipx;// 애니메이션용 이미지(왼쪽보기)

	// 화살 이미지
	BufferedImage waveSprite;
	BufferedImage waveSprite_flipx;

	// 고블린1 이미지
	BufferedImage goblinstand;// 애니메이션용 이미지
	BufferedImage goblinstand_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage goblinwalk;// 애니메이션용 이미지
	BufferedImage goblinwalk_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage goblinattack;// 애니메이션용 이미지
	BufferedImage goblinattack_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage goblindead;// 애니메이션용 이미지
	BufferedImage goblindead_flipx;// 애니메이션용 이미지(왼쪽보기)

	// 고블린2 이미지
	BufferedImage goblin2stand;// 애니메이션용 이미지
	BufferedImage goblin2stand_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage goblin2walk;// 애니메이션용 이미지
	BufferedImage goblin2walk_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage goblin2attack;// 애니메이션용 이미지
	BufferedImage goblin2attack_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage goblin2dead;// 애니메이션용 이미지
	BufferedImage goblin2dead_flipx;// 애니메이션용 이미지(왼쪽보기)

	// 고블린3 이미지
	BufferedImage goblin3stand;// 애니메이션용 이미지
	BufferedImage goblin3stand_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage goblin3walk;// 애니메이션용 이미지
	BufferedImage goblin3walk_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage goblin3attack;// 애니메이션용 이미지
	BufferedImage goblin3attack_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage goblin3dead;// 애니메이션용 이미지
	BufferedImage goblin3dead_flipx;// 애니메이션용 이미지(왼쪽보기)

	// 보스 이미지
	BufferedImage bossstand;// 애니메이션용 이미지
	BufferedImage bossstand_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage bosswalk;// 애니메이션용 이미지
	BufferedImage bosswalk_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage bossattack;// 애니메이션용 이미지
	BufferedImage bossattack_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage bossdead;// 애니메이션용 이미지
	BufferedImage bossdead_flipx;// 애니메이션용 이미지(왼쪽보기)

	BufferedImage thiefstand;// 애니메이션용 이미지
	BufferedImage thiefstand_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage thiefwalk;// 애니메이션용 이미지
	BufferedImage thiefwalk_flipx;// 애니메이션용 이미지(왼쪽보기)
	BufferedImage thiefdead;// 애니메이션용 이미지
	BufferedImage thiefdead_flipx;// 애니메이션용 이미지(왼쪽보기)

	BufferedImage npc1;
	BufferedImage npc2;
	BufferedImage doctor;

	BufferedImage gameover;
	BufferedImage gameending;

	// 캐릭터
	Player player;

	ArrayList<Wave> waves;

	RescueMap stage;
	PuzzleMap puzzle;
	Home home;

	ArrayList<Goblin> goblins;
	ArrayList<Goblin2> goblin2s;
	ArrayList<Goblin3> goblin3s;
	ArrayList<Boss> bosses;
	ArrayList<Wave> boss_waves;

	ArrayList<Thief> thieves;
	NPC town_npc1;
	NPC town_npc2;
	NPC npc_doctor;

	static long keytimer;

	int cnt;
	boolean seria = false;
	boolean rescued = false;	

	public static int characterchoice = 0; // 0 - 검사 1 - 격투가
	public static int key = 0;

	int textcnt;

	boolean keyBuff_UP;
	boolean keyBuff_DOWN;
	boolean keyBuff_LEFT;
	boolean keyBuff_RIGHT;
	boolean keyBuff_FIRE;
	boolean keyBuff_SKILL;

	int y_limit;

	public Controller(MainFrame mainFrame) {

		this.mainFrame = mainFrame;
		// kh2
		// 배경 그림 로딩
		homeImg = makeBufferedImage("rsc/village.png");
		bgImg = makeBufferedImage("rsc/rescue_r.png");
		bgImg2 = makeBufferedImage("rsc/rescue_dlr.png");
		bgImg3 = makeBufferedImage("rsc/rescue_l.png");
		bgImg4 = makeBufferedImage("rsc/rescue_ud.png");
		bgImg5 = makeBufferedImage("rsc/rescue_ulr.png");
		bgImg6 = makeBufferedImage("rsc/rescue_dr.png");
		bgImg7 = makeBufferedImage("rsc/rescue_u.png");
		bgImg8 = makeBufferedImage("rsc/rescue_dlr.png");
		bgImg9 = makeBufferedImage("rsc/rescue_u.png");
		bgImg10 = makeBufferedImage("rsc/rescue_ulr.png");
		bgImg11 = makeBufferedImage("rsc/rescue_dl.png");
		bgImg12 = makeBufferedImage("rsc/rescue_u.png");
		bgImg13 = makeBufferedImage("rsc/rescue_ud.png");
		bgImg14 = makeBufferedImage("rsc/rescue_dr.png");
		bgImg15 = makeBufferedImage("rsc/rescue_lr.png");
		bgImg16 = makeBufferedImage("rsc/rescue_lr.png");

		// ===================================================

		pzImg = makeBufferedImage("rsc/puzzlemap_start.png");
		pzImg2 = makeBufferedImage("rsc/puzzlemap_long.png");
		pzImg3 = makeBufferedImage("rsc/puzzlemap_long.png");
		pzImg4 = makeBufferedImage("rsc/puzzlemap_long.png");
		pzImg5 = makeBufferedImage("rsc/puzzlemap_long.png");
		pzImg6 = makeBufferedImage("rsc/puzzlemap_goblin.png");
		pzImg7 = makeBufferedImage("rsc/puzzlemap_end.png");

		hud = makeBufferedImage("rsc/hud.png");
		hp_portion = makeBufferedImage("rsc/hpportion.png");
		mp_portion = makeBufferedImage("rsc/mpportion.png");
		key_a = makeBufferedImage("rsc/a.png");
		key_s = makeBufferedImage("rsc/s.png");
		key_d = makeBufferedImage("rsc/d.png");
		key_f = makeBufferedImage("rsc/f.png");
		key_1 = makeBufferedImage("rsc/1.png");
		key_2 = makeBufferedImage("rsc/2.png");
		num0 = makeBufferedImage("rsc/num_0.png");
		num1 = makeBufferedImage("rsc/num_1.png");
		num2 = makeBufferedImage("rsc/num_2.png");
		num3 = makeBufferedImage("rsc/num_3.png");
		num4 = makeBufferedImage("rsc/num_4.png");
		num5 = makeBufferedImage("rsc/num_5.png");
		num6 = makeBufferedImage("rsc/num_6.png");
		num7 = makeBufferedImage("rsc/num_7.png");
		num8 = makeBufferedImage("rsc/num_8.png");
		num9 = makeBufferedImage("rsc/num_9.png");

		text1 = makeBufferedImage("rsc/text1.png");
		text2 = makeBufferedImage("rsc/text2.png");
		text3 = makeBufferedImage("rsc/text3.png");

		ox = makeBufferedImage("rsc/ox.png");
		diff = makeBufferedImage("rsc/diff.png");
		randombox = makeBufferedImage("rsc/randombox.png");

		icon3 = makeBufferedImage("rsc/icon3.png");
		icon3_delay = makeBufferedImage("rsc/icon3_delay.png");

		// 플레이어 그림 로딩
		if (characterchoice == 0) {
			icon1 = makeBufferedImage("rsc/warrior_icon1.png");
			icon1_delay = makeBufferedImage("rsc/warrior_icon1_delay.png");
			icon2 = makeBufferedImage("rsc/warrior_icon2.png");
			icon2_delay = makeBufferedImage("rsc/warrior_icon2_delay.png");
			character_text = makeBufferedImage("rsc/warrior_text.png");

			pstand = makeBufferedImage("rsc/warrior_stand.png");
			pstand_flipx = getFlippedImage(pstand, true, false);
			pwalk = makeBufferedImage("rsc/warrior_walk.png");
			pwalk_flipx = getFlippedImage(pwalk, true, false);
			prun = makeBufferedImage("rsc/warrior_run.png");
			prun_flipx = getFlippedImage(prun, true, false);
			pdamage = makeBufferedImage("rsc/warrior_damage.png");
			pdamage_flipx = getFlippedImage(pdamage, true, false);
			pattack1 = makeBufferedImage("rsc/warrior_attack1.png");
			pattack1_flipx = getFlippedImage(pattack1, true, false);
			pattack2 = makeBufferedImage("rsc/warrior_attack2.png");
			pattack2_flipx = getFlippedImage(pattack2, true, false);
			pskill1 = makeBufferedImage("rsc/warrior_skill1.png");
			pskill1_flipx = getFlippedImage(pskill1, true, false);
			pskill2 = makeBufferedImage("rsc/warrior_skill21.png");
			pskill2_flipx = getFlippedImage(pskill2, true, false);
			pskill3 = makeBufferedImage("rsc/warrior_skill3.png");
			pskill3_flipx = getFlippedImage(pskill3, true, false);

			// 화살 그림 로딩
			waveSprite = makeBufferedImage("rsc/wave.png");
			waveSprite_flipx = getFlippedImage(waveSprite, true, false);
		} else {
			icon1 = makeBufferedImage("rsc/fighter_icon1.png");
			icon1_delay = makeBufferedImage("rsc/fighter_icon1_delay.png");
			icon2 = makeBufferedImage("rsc/fighter_icon2.png");
			icon2_delay = makeBufferedImage("rsc/fighter_icon2_delay.png");
			character_text = makeBufferedImage("rsc/fighter_text.png");

			pstand = makeBufferedImage("rsc/fighter_stand.png");
			pstand_flipx = getFlippedImage(pstand, true, false);
			pwalk = makeBufferedImage("rsc/fighter_walk.png");
			pwalk_flipx = getFlippedImage(pwalk, true, false);
			prun = makeBufferedImage("rsc/fighter_run.png");
			prun_flipx = getFlippedImage(prun, true, false);
			pdamage = makeBufferedImage("rsc/fighter_damage.png");
			pdamage_flipx = getFlippedImage(pdamage, true, false);
			pattack1 = makeBufferedImage("rsc/fighter_attack1.png");
			pattack1_flipx = getFlippedImage(pattack1, true, false);
			pattack2 = makeBufferedImage("rsc/fighter_attack2.png");
			pattack2_flipx = getFlippedImage(pattack2, true, false);
			pskill1 = makeBufferedImage("rsc/fighter_skill1.png");
			pskill1_flipx = getFlippedImage(pskill1, true, false);
			pskill2 = makeBufferedImage("rsc/fighter_effect.png");
			pskill2_flipx = getFlippedImage(pskill2, true, false);
			pskill3 = makeBufferedImage("rsc/fighter_skill3.png");
			pskill3_flipx = getFlippedImage(pskill3, true, false);
		}

		// 고블린 그림 로딩
		goblinstand = makeBufferedImage("rsc/goblin.png");
		goblinstand_flipx = getFlippedImage(goblinstand, true, false);
		goblinwalk = makeBufferedImage("rsc/goblin_walk.png");
		goblinwalk_flipx = getFlippedImage(goblinwalk, true, false);
		goblinattack = makeBufferedImage("rsc/goblin_attack.png");
		goblinattack_flipx = getFlippedImage(goblinattack, true, false);
		goblindead = makeBufferedImage("rsc/goblin_dead.png");
		goblindead_flipx = getFlippedImage(goblindead, true, false);

		goblin2stand = makeBufferedImage("rsc/goblin2.png");
		goblin2stand_flipx = getFlippedImage(goblin2stand, true, false);
		goblin2walk = makeBufferedImage("rsc/goblin2_walk.png");
		goblin2walk_flipx = getFlippedImage(goblin2walk, true, false);
		goblin2attack = makeBufferedImage("rsc/goblin2_attack.png");
		goblin2attack_flipx = getFlippedImage(goblin2attack, true, false);
		goblin2dead = makeBufferedImage("rsc/goblin2_dead.png");
		goblin2dead_flipx = getFlippedImage(goblin2dead, true, false);

		goblin3stand = makeBufferedImage("rsc/goblin3.png");
		goblin3stand_flipx = getFlippedImage(goblin3stand, true, false);
		goblin3walk = makeBufferedImage("rsc/goblin3_walk.png");
		goblin3walk_flipx = getFlippedImage(goblin3walk, true, false);
		goblin3attack = makeBufferedImage("rsc/goblin3_attack.png");
		goblin3attack_flipx = getFlippedImage(goblin3attack, true, false);
		goblin3dead = makeBufferedImage("rsc/goblin3_dead.png");
		goblin3dead_flipx = getFlippedImage(goblin3dead, true, false);

		bossstand = makeBufferedImage("rsc/boss.png");
		bossstand_flipx = getFlippedImage(bossstand, true, false);
		bosswalk = makeBufferedImage("rsc/boss_walk.png");
		bosswalk_flipx = getFlippedImage(bosswalk, true, false);
		bossattack = makeBufferedImage("rsc/boss_attack.png");
		bossattack_flipx = getFlippedImage(bossattack, true, false);
		bossdead = makeBufferedImage("rsc/boss_dead.png");
		bossdead_flipx = getFlippedImage(bossdead, true, false);

		thiefstand = makeBufferedImage("rsc/thief.png");
		thiefstand_flipx = getFlippedImage(thiefstand, true, false);
		thiefwalk = makeBufferedImage("rsc/thief_walk.png");
		thiefwalk_flipx = getFlippedImage(thiefwalk, true, false);
		thiefdead = makeBufferedImage("rsc/thief_dead.png");
		thiefdead_flipx = getFlippedImage(thiefdead, true, false);

		npc1 = makeBufferedImage("rsc/npc1.png");
		npc2 = makeBufferedImage("rsc/npc2.png");
		doctor = makeBufferedImage("rsc/doctor.png");

		gameover = makeBufferedImage("rsc/gameover.png");
		gameending = makeBufferedImage("rsc/ending.png");

		if (characterchoice == 0) {
			player = new Warrior(this);
		} else {
			player = new Fighter(this);
		}

		player.setIdle();
		player.x = 200;
		player.y = 500;

		home = new Home();
		stage = new RescueMap();
		puzzle = new PuzzleMap();

		cnt = 0;
		rand = new Random();

		waves = new ArrayList<Wave>();

		goblins = new ArrayList<Goblin>();
		goblin2s = new ArrayList<Goblin2>();
		goblin3s = new ArrayList<Goblin3>();
		bosses = new ArrayList<Boss>();
		boss_waves = new ArrayList<Wave>();
		thieves = new ArrayList<Thief>();

		town_npc1 = new NPC(this, 300, 340);
		town_npc2 = new NPC(this, 650, 360);
		npc_doctor = new NPC(this, 800, 400);

		home.home = true;

		this.setFocusable(false);

		delay = 15;

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			try {
				pretime = System.currentTimeMillis();
				process();
				repaint();

				long nowTime = System.currentTimeMillis();
				if (nowTime - pretime < delay)
					Thread.sleep(delay - (nowTime - pretime) < 0 ? 1 : delay - (nowTime - pretime));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if (gc2 == null) {

			buff = new BufferedImage(mainFrame.gGameWidth, mainFrame.gGameHeight, BufferedImage.TYPE_INT_ARGB);
			// 오프스크린 버퍼
			if (buff != null)
				gc2 = buff.createGraphics();
			return;
		}
		update(g);
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		if (gc2 == null)
			return;

		dblpaint();// 오프스크린 버퍼에 그리기
		// g.drawImage((Image)getScaledImage(buff, mainFrame.gGameWidth,
		// mainFrame.gGameHeight), 0,0, mainFrame.gGameWidth,
		// mainFrame.gGameHeight, this);//오프스크린 버퍼를 메인화면에 그린다.
		g.drawImage(buff, 0, 0, mainFrame.gGameWidth, mainFrame.gGameHeight, this);
		// 오프스크린 버퍼를 메인화면에 그린다.
	}

	void dblpaint() {
		drawBG();
		drawNPC();
		drawEnemy();
		drawPlayer();
		drawwave();
		drawUI();
		ending();

	}

	void ending() {
		if (player.hp <= 0) {
			drawImageF(gc2, gameover, 0, 0, this);
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			bosses.clear();
			boss_waves.clear();
			waves.clear();

		}
	}

	// kh3
	void drawBG() {
		if (home.home)
			drawImageF(gc2, homeImg, 0, 0, this);
		else if (stage.stage1)
			drawImageF(gc2, bgImg, 0, 0, this);
		else if (stage.stage2)
			drawImageF(gc2, bgImg2, 0, 0, this);
		else if (stage.stage3)
			drawImageF(gc2, bgImg3, 0, 0, this);
		else if (stage.stage4)
			drawImageF(gc2, bgImg4, 0, 0, this);
		else if (stage.stage5)
			drawImageF(gc2, bgImg5, 0, 0, this);
		else if (stage.stage6)
			drawImageF(gc2, bgImg6, 0, 0, this);
		else if (stage.stage7)
			drawImageF(gc2, bgImg7, 0, 0, this);
		else if (stage.stage8)
			drawImageF(gc2, bgImg8, 0, 0, this);
		else if (stage.stage9)
			drawImageF(gc2, bgImg9, 0, 0, this);
		else if (stage.stage10)
			drawImageF(gc2, bgImg10, 0, 0, this);
		else if (stage.stage11)
			drawImageF(gc2, bgImg11, 0, 0, this);
		else if (stage.stage12)
			drawImageF(gc2, bgImg12, 0, 0, this);
		else if (stage.stage13)
			drawImageF(gc2, bgImg13, 0, 0, this);
		else if (stage.stage14)
			drawImageF(gc2, bgImg14, 0, 0, this);
		else if (stage.stage15)
			drawImageF(gc2, bgImg15, 0, 0, this);
		else if (stage.stage16)
			drawImageF(gc2, bgImg16, 0, 0, this);

		else if (puzzle.stage1)
			drawImageF(gc2, pzImg, 0, 0, this);
		else if (puzzle.stage2)
			drawImageF(gc2, pzImg2, 0, 0, this);
		else if (puzzle.stage3)
			drawImageF(gc2, pzImg3, 0, 0, this);
		else if (puzzle.stage4)
			drawImageF(gc2, pzImg4, 0, 0, this);
		else if (puzzle.stage5)
			drawImageF(gc2, pzImg5, 0, 0, this);
		else if (puzzle.stage6)
			drawImageF(gc2, pzImg6, 0, 0, this);
		else if (puzzle.stage7)
			drawImageF(gc2, pzImg7, 0, 0, this);
	}

	void drawPlayer() {
		if (player.status == 0) {
			player.draw(gc2, player.dir == 0 ? pstand_flipx : pstand);
		} else if (player.status == 1 && player.run == false) {
			player.draw(gc2, player.dir == 0 ? pwalk_flipx : pwalk);
		} else if (player.status == 1 && player.run == true) {
			player.draw(gc2, player.dir == 0 ? prun_flipx : prun);
		} else if (player.status == 2 && player.attack1 == true) {
			player.draw(gc2, player.dir == 0 ? pattack1_flipx : pattack1);
		} else if (player.status == 2 && player.attack2 == true) {
			player.draw(gc2, player.dir == 0 ? pattack2_flipx : pattack2);
		} else if (player.status == 3 && player.hp > 0) {
			player.draw(gc2, player.dir == 0 ? pdamage_flipx : pdamage);
		} else if (player.status == 4 && player.skill1 == true) {
			player.draw(gc2, player.dir == 0 ? pskill1_flipx : pskill1);
		} else if (player.status == 4 && player.skill2 == true) {
			player.draw(gc2, player.dir == 0 ? pskill2_flipx : pskill2);
		} else if (player.status == 4 && player.skill3 == true) {
			player.draw(gc2, player.dir == 0 ? pskill3_flipx : pskill3);
		}
	}

	void drawwave() {

		Wave buff;

		for (int i = 0; i < waves.size(); i++) {
			buff = waves.get(i);
			if (buff != null) {
				if (buff.dir == 1)
					gc2.drawImage(waveSprite, (int) buff.x, (int) buff.y, this);
				else
					gc2.drawImage(waveSprite_flipx, (int) buff.x, (int) buff.y, this);
			}
		}
	}

	void drawNPC() {
		if (home.home) {
			town_npc1.draw(gc2, npc1);
			town_npc2.draw(gc2, npc2);
		} else if (stage.stage16) {
			npc_doctor.draw(gc2, doctor);
		}

	}

	void drawEnemy() {

		Goblin buff;
		Goblin2 buff2;
		Goblin3 buff3;
		Boss bbuff;
		Thief tbuff;

		for (int i = 0; i < goblins.size(); i++) {
			buff = goblins.get(i);
			gc2.drawRoundRect((int) buff.x - 25, (int) buff.y + 10, 50, 5, 2, 2);
			gc2.setColor(Color.RED);
			gc2.fillRoundRect((int) buff.x - 25, (int) buff.y + 10, buff.hp / 4, 5, 2, 2);
			gc2.setColor(Color.WHITE);
			if (buff != null) {
				if (goblins.get(i).status == 0)
					buff.draw(gc2, buff.dir == 0 ? goblinstand_flipx : goblinstand);
				else if (goblins.get(i).status == 1)
					buff.draw(gc2, buff.dir == 0 ? goblinwalk_flipx : goblinwalk);
				else if (goblins.get(i).status == 2)
					buff.draw(gc2, buff.dir == 0 ? goblinattack_flipx : goblinattack);
				else if (goblins.get(i).status == 3)
					buff.draw(gc2, buff.dir == 0 ? goblindead_flipx : goblindead);
			}
		}

		for (int i = 0; i < goblin2s.size(); i++) {
			buff2 = goblin2s.get(i);
			gc2.drawRoundRect((int) buff2.x - 25, (int) buff2.y + 10, 50, 5, 2, 2);
			gc2.setColor(Color.RED);
			gc2.fillRoundRect((int) buff2.x - 25, (int) buff2.y + 10, buff2.hp / 5, 5, 2, 2);
			gc2.setColor(Color.WHITE);
			if (buff2 != null) {
				if (goblin2s.get(i).status == 0)
					buff2.draw(gc2, buff2.dir == 0 ? goblin2stand_flipx : goblin2stand);
				else if (goblin2s.get(i).status == 1)
					buff2.draw(gc2, buff2.dir == 0 ? goblin2walk_flipx : goblin2walk);
				else if (goblin2s.get(i).status == 2)
					buff2.draw(gc2, buff2.dir == 0 ? goblin2attack_flipx : goblin2attack);
				else if (goblin2s.get(i).status == 3)
					buff2.draw(gc2, buff2.dir == 0 ? goblin2dead_flipx : goblin2dead);
			}
		}

		for (int i = 0; i < goblin3s.size(); i++) {
			buff3 = goblin3s.get(i);
			gc2.drawRoundRect((int) buff3.x - 25, (int) buff3.y + 10, 50, 5, 2, 2);
			gc2.setColor(Color.RED);
			gc2.fillRoundRect((int) buff3.x - 25, (int) buff3.y + 10, buff3.hp / 8, 5, 2, 2);
			gc2.setColor(Color.WHITE);
			if (buff3 != null) {
				if (goblin3s.get(i).status == 0)
					buff3.draw(gc2, buff3.dir == 0 ? goblin3stand_flipx : goblin3stand);
				else if (goblin3s.get(i).status == 1)
					buff3.draw(gc2, buff3.dir == 0 ? goblin3walk_flipx : goblin3walk);
				else if (goblin3s.get(i).status == 2)
					buff3.draw(gc2, buff3.dir == 0 ? goblin3attack_flipx : goblin3attack);
				else if (goblin3s.get(i).status == 3)
					buff3.draw(gc2, buff3.dir == 0 ? goblin3dead_flipx : goblin3dead);
			}
		}

		for (int i = 0; i < bosses.size(); i++) {
			bbuff = bosses.get(i);
			gc2.drawRoundRect((int) bbuff.x - 50, (int) bbuff.y + 10, 100, 5, 2, 2);
			gc2.setColor(Color.RED);
			gc2.fillRoundRect((int) bbuff.x - 50, (int) bbuff.y + 10, bbuff.hp / 8, 5, 2, 2);
			gc2.setColor(Color.WHITE);
			if (bbuff != null) {
				if (bosses.get(i).status == 0)
					bbuff.draw(gc2, bbuff.dir == 0 ? bossstand_flipx : bossstand);
				else if (bosses.get(i).status == 1)
					bbuff.draw(gc2, bbuff.dir == 0 ? bosswalk_flipx : bosswalk);
				else if (bosses.get(i).status == 2)
					bbuff.draw(gc2, bbuff.dir == 0 ? bossattack_flipx : bossattack);
				else if (bosses.get(i).status == 3)
					bbuff.draw(gc2, bbuff.dir == 0 ? bossdead_flipx : bossdead);
			}
		}

		for (int i = 0; i < thieves.size(); i++) {
			tbuff = thieves.get(i);
			gc2.drawRoundRect((int) tbuff.x - 50, (int) tbuff.y + 10, 50, 5, 2, 2);
			gc2.setColor(Color.RED);
			gc2.fillRoundRect((int) tbuff.x - 50, (int) tbuff.y + 10, tbuff.hp / 2, 5, 2, 2);
			gc2.setColor(Color.WHITE);
			if (tbuff != null) {
				if (thieves.get(i).status == 0)
					tbuff.draw(gc2, tbuff.dir == 0 ? thiefstand_flipx : thiefstand);
				else if (thieves.get(i).status == 1)
					tbuff.draw(gc2, tbuff.dir == 0 ? thiefwalk_flipx : thiefwalk);
				else if (thieves.get(i).status == 2)
					tbuff.draw(gc2, tbuff.dir == 0 ? thiefdead_flipx : thiefdead);
			}
		}
	}

	void drawEffect() {

	}

	void drawUI() {
		drawImageF(gc2, hud, 0, 640, this);		
		drawImageF(gc2, character_text, 30, 650, this);

		gc2.setColor(Color.BLACK);
		gc2.drawRoundRect(200, 665, 200, 20, 5, 5);
		gc2.setColor(Color.RED);
		gc2.fillRoundRect(200, 665, player.hp, 20, 5, 5);
		gc2.setColor(Color.BLACK);
		gc2.drawRoundRect(200, 695, 200, 20, 5, 5);
		gc2.setColor(Color.BLUE);
		gc2.fillRoundRect(200, 695, player.mp, 20, 5, 5);
		drawImageF(gc2, icon1, 800, 660, this);
		drawImageF(gc2, icon2, 850, 660, this);
		drawImageF(gc2, icon3, 900, 660, this);
		drawImageF(gc2, key_s, 803, 700, this);
		drawImageF(gc2, key_d, 852, 700, this);
		drawImageF(gc2, key_f, 903, 700, this);
		drawImageF(gc2, hp_portion, 580, 660, this);
		drawImageF(gc2, mp_portion, 630, 660, this);
		drawImageF(gc2, key_1, 583, 700, this);
		drawImageF(gc2, key_2, 633, 700, this);
		
		switch (player.hp_portion.cnt) {
		case 0:
			drawImageF(gc2, num0, 602, 678, this);
			break;
		case 1:
			drawImageF(gc2, num1, 602, 678, this);
			break;
		case 2:
			drawImageF(gc2, num2, 602, 678, this);
			break;
		case 3:
			drawImageF(gc2, num3, 602, 678, this);
			break;
		case 4:
			drawImageF(gc2, num4, 602, 678, this);
			break;
		case 5:
			drawImageF(gc2, num5, 602, 678, this);
			break;
		case 6:
			drawImageF(gc2, num6, 602, 678, this);
			break;
		case 7:
			drawImageF(gc2, num7, 602, 678, this);
			break;
		case 8:
			drawImageF(gc2, num8, 602, 678, this);
			break;
		case 9:
			drawImageF(gc2, num9, 602, 678, this);
			break;
		}
		switch (player.mp_portion.cnt) {
		case 0:
			drawImageF(gc2, num0, 652, 678, this);
			break;
		case 1:
			drawImageF(gc2, num1, 652, 678, this);
			break;
		case 2:
			drawImageF(gc2, num2, 652, 678, this);
			break;
		case 3:
			drawImageF(gc2, num3, 652, 678, this);
			break;
		case 4:
			drawImageF(gc2, num4, 652, 678, this);
			break;
		case 5:
			drawImageF(gc2, num5, 652, 678, this);
			break;
		case 6:
			drawImageF(gc2, num6, 652, 678, this);
			break;
		case 7:
			drawImageF(gc2, num7, 652, 678, this);
			break;
		case 8:
			drawImageF(gc2, num8, 652, 678, this);
			break;
		case 9:
			drawImageF(gc2, num9, 652, 678, this);
			break;
		}

		if (home.home) {
			if (200 < player.x && player.x < 400 && 300 < player.y && player.y < 450) {
				drawImageF(gc2, key_a, player.x - 10, player.y - 170, this);
				if (textcnt == 1) {
					drawImageF(gc2, text1, 0, 405, this);
				}
			} else if (550 < player.x && player.x < 750 && 300 < player.y && player.y < 450) {
				drawImageF(gc2, key_a, player.x - 10, player.y - 170, this);
				if (textcnt == 1) {
					drawImageF(gc2, text2, 0, 405, this);
				}
			}
		} else if (stage.stage16) {
			if (700 < player.x && player.x < 850 && 300 < player.y && player.y < 450) {
				drawImageF(gc2, key_a, player.x - 10, player.y - 170, this);
				if (textcnt == 1) {
					drawImageF(gc2, text3, 0, 405, this);
				}
			}
		} else if (puzzle.stage1 || puzzle.stage2 || puzzle.stage3 || puzzle.stage5) {
			if (120 < player.x && player.x < 200 && 350 < player.y && player.y < 550 && key == 0) {
				drawImageF(gc2, key_a, player.x - 10, player.y - 170, this);
				if (textcnt == 1) {
					drawImageF(gc2, ox, 0, 405, this);
				}
			}
		} else if (puzzle.stage4) {
			if (120 < player.x && player.x < 200 && 350 < player.y && player.y < 550 && key == 0) {
				drawImageF(gc2, key_a, player.x - 10, player.y - 170, this);
				if (textcnt == 1) {
					drawImageF(gc2, diff, 0, 405, this);
				}
			}
		} else if (puzzle.stage7) {
			if (250 < player.x && player.x < 330 && 350 < player.y && player.y < 550 && key == 0) {
				drawImageF(gc2, key_a, player.x - 10, player.y - 170, this);
				if (textcnt == 1) {
					drawImageF(gc2, randombox, 0, 405, this);
				}
			}
			
			if(key==1){
				drawImageF(gc2, gameending, 0, 0, this);
			}
		}
		
	}

	void process() {

		keyProcess();
		processPlayer();
		processWave();
		processSkill1();
		processAttack();
		processEnemy();
		processBoss();
		processStage();
		processQuiz();
		cnt++;
	}

	void processPlayer() {
		player.process(cnt);
	}

	void processAttack() { // 공격 판정 함수

		Goblin sbuff;
		Goblin2 sbuff2;
		Goblin3 sbuff3;
		Boss bbuff;
		Thief tbuff;

		for (int j = goblins.size() - 1; j >= 0; j--) {
			sbuff = goblins.get(j);
			if (sbuff != null) {
				if (player.status == 2 && player.dir == 1) {
					if (player.x < sbuff.x && sbuff.x < player.x + 100 && sbuff.life == true) {
						if (player.y - 30 < sbuff.y && sbuff.y < player.y + 20) {
							sbuff.hp -= player.power;
							sbuff.x += 20;
							System.out.println("R_normal Damage : " + player.power);

							sbuff.setDead();
							if (sbuff.hp > 0)
								sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
							else {
								sound.Sound("rsc/sound/gbn_die.wav", false, 5);
								if (rand.nextInt(5) == 0) {
									player.hp_portion.get_Portion();
								}
								if (rand.nextInt(5) == 0) {
									player.mp_portion.get_Portion();
								}
								break;
							}
						}
					}
				} else if (player.status == 2 && player.dir == 0) {
					if (player.x - 100 < sbuff.x && sbuff.x < player.x && sbuff.life == true) {
						if (player.y - 30 < sbuff.y && sbuff.y < player.y + 20) {
							sbuff.hp -= player.power;
							sbuff.x -= 20;
							System.out.println("L+normal Damage : " + player.power);
							sbuff.setDead();
							if (sbuff.hp > 0)
								sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
							else {
								sound.Sound("rsc/sound/gbn_die.wav", false, 5);
								if (rand.nextInt(5) == 0) {
									player.hp_portion.get_Portion();
								}
								if (rand.nextInt(5) == 0) {
									player.mp_portion.get_Portion();
								}
								break;
							}
						}
					}
				}
			}
		}
		for (int j = goblin2s.size() - 1; j >= 0; j--) {
			sbuff2 = goblin2s.get(j);
			if (sbuff2 != null) {
				if (player.status == 2 && player.dir == 1) {
					if (player.x < sbuff2.x && sbuff2.x < player.x + 100 && sbuff2.life == true) {
						if (player.y - 30 < sbuff2.y && sbuff2.y < player.y + 20) {
							sbuff2.hp -= player.power;
							sbuff2.x += 20;
							System.out.println("R_normal Damage : " + player.power);
							sbuff2.setDead();
							if (sbuff2.hp > 0)
								sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
							else {
								sound.Sound("rsc/sound/gbn_die.wav", false, 5);
								if (rand.nextInt(5) == 0) {
									player.hp_portion.get_Portion();
								}
								if (rand.nextInt(5) == 0) {
									player.mp_portion.get_Portion();
								}
								break;
							}
						}
					}
				} else if (player.status == 2 && player.dir == 0) {
					if (player.x - 100 < sbuff2.x && sbuff2.x < player.x && sbuff2.life == true) {
						if (player.y - 30 < sbuff2.y && sbuff2.y < player.y + 20) {
							sbuff2.hp -= player.power;
							sbuff2.x -= 20;
							System.out.println("L+normal Damage : " + player.power);
							sbuff2.setDead();
							if (sbuff2.hp > 0)
								sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
							else {
								sound.Sound("rsc/sound/gbn_die.wav", false, 5);
								if (rand.nextInt(5) == 0) {
									player.hp_portion.get_Portion();
								}
								if (rand.nextInt(5) == 0) {
									player.mp_portion.get_Portion();
								}
							}
							break;
						}
					}
				}
			}
		}
		for (int j = goblin3s.size() - 1; j >= 0; j--) {
			sbuff3 = goblin3s.get(j);
			if (sbuff3 != null) {
				if (player.status == 2 && player.dir == 1) {
					if (player.x < sbuff3.x && sbuff3.x < player.x + 100 && sbuff3.life == true) {
						if (player.y - 30 < sbuff3.y && sbuff3.y < player.y + 20) {
							sbuff3.hp -= player.power;
							sbuff3.x += 20;
							System.out.println("R_normal Damage : " + player.power);
							sbuff3.setDead();
							if (sbuff3.hp > 0)
								sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
							else {
								sound.Sound("rsc/sound/gbn_die.wav", false, 5);
								if (rand.nextInt(5) == 0) {
									player.hp_portion.get_Portion();
								}
								if (rand.nextInt(5) == 0) {
									player.mp_portion.get_Portion();
								}
							}
							break;
						}
					}
				} else if (player.status == 2 && player.dir == 0) {
					if (player.x - 100 < sbuff3.x && sbuff3.x < player.x && sbuff3.life == true) {
						if (player.y - 30 < sbuff3.y && sbuff3.y < player.y + 20) {
							sbuff3.hp -= player.power;
							sbuff3.x -= 20;
							System.out.println("L+normal Damage : " + player.power);
							sbuff3.setDead();
							if (sbuff3.hp > 0)
								sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
							else {
								sound.Sound("rsc/sound/gbn_die.wav", false, 5);
								if (rand.nextInt(5) == 0) {
									player.hp_portion.get_Portion();
								}
								if (rand.nextInt(5) == 0) {
									player.mp_portion.get_Portion();
								}
							}
							break;
						}
					}
				}
			}
		}

		for (int j = bosses.size() - 1; j >= 0; j--) {
			bbuff = bosses.get(j);
			if (bbuff != null) {
				if (player.status == 2 && player.dir == 1) {
					if (player.x < bbuff.x && bbuff.x < player.x + 100 && bbuff.life == true) {
						if (player.y - 30 < bbuff.y && bbuff.y < player.y + 20) {
							bbuff.hp -= player.power;
							bbuff.x += 20;
							System.out.println("R_normal Damage : " + player.power);
							bbuff.setDead();
							if (bbuff.hp > 0)
								sound.Sound("rsc/sound/boss_dmg.wav", false, 5);
							else {
								sound.Sound("rsc/sound/boss_die.wav", false, 5);
								if (rand.nextInt(5) == 0) {
									player.hp_portion.get_Portion();
								}
								if (rand.nextInt(5) == 0) {
									player.mp_portion.get_Portion();
								}
							}
							break;
						}
					}
				} else if (player.status == 2 && player.dir == 0) {
					if (player.x - 100 < bbuff.x && bbuff.x < player.x && bbuff.life == true) {
						if (player.y - 30 < bbuff.y && bbuff.y < player.y + 20) {
							bbuff.hp -= player.power;
							bbuff.x -= 20;
							System.out.println("L+normal Damage : " + player.power);
							bbuff.setDead();
							if (bbuff.hp > 0)
								sound.Sound("rsc/sound/boss_dmg.wav", false, 5);
							else {
								sound.Sound("rsc/sound/boss_die.wav", false, 5);
								if (rand.nextInt(5) == 0) {
									player.hp_portion.get_Portion();
								}
								if (rand.nextInt(5) == 0) {
									player.mp_portion.get_Portion();
								}
							}
							break;
						}
					}
				}
			}
		}

		for (int j = thieves.size() - 1; j >= 0; j--) {
			tbuff = thieves.get(j);
			if (tbuff != null) {
				if (player.status == 2 && player.dir == 1) {
					if (player.x < tbuff.x && tbuff.x < player.x + 100 && tbuff.life == true) {
						if (player.y - 30 < tbuff.y && tbuff.y < player.y + 20) {
							tbuff.hp -= player.power;
							tbuff.x += 20;
							System.out.println("R_normal Damage : " + player.power);
							tbuff.setDead();
							if (tbuff.hp > 0)
								sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
							else {
								sound.Sound("rsc/sound/gbn_die.wav", false, 5);
								if (rand.nextInt(5) == 0) {
									player.hp_portion.get_Portion();
								}
								if (rand.nextInt(5) == 0) {
									player.mp_portion.get_Portion();
								}
							}
							break;
						}
					}
				} else if (player.status == 2 && player.dir == 0) {
					if (player.x - 100 < tbuff.x && tbuff.x < player.x && tbuff.life == true) {
						if (player.y - 30 < tbuff.y && tbuff.y < player.y + 20) {
							tbuff.hp -= player.power;
							tbuff.x -= 20;
							System.out.println("L+normal Damage : " + player.power);
							tbuff.setDead();
							if (tbuff.hp > 0)
								sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
							else {
								sound.Sound("rsc/sound/gbn_die.wav", false, 5);
								if (rand.nextInt(5) == 0) {
									player.hp_portion.get_Portion();
								}
								if (rand.nextInt(5) == 0) {
									player.mp_portion.get_Portion();
								}
							}
							break;
						}
					}
				}
			}
		}
	}

	void processSkill1() { // 공격 판정 함수

		Goblin sbuff;
		Goblin2 sbuff2;
		Goblin3 sbuff3;
		Boss bbuff;
		Thief tbuff;

		for (int j = goblins.size() - 1; j >= 0; j--) {
			sbuff = goblins.get(j);
			if (sbuff != null) {
				if (characterchoice == 1) {
					if (player.status == 4 && player.dir == 1 && player.skill3 != true) {
						if (player.x < sbuff.x && sbuff.x < player.x + 100 && sbuff.life == true) {
							if (player.y - 30 < sbuff.y && sbuff.y < player.y + 20) {
								sbuff.hp -= player.power;
								sbuff.x += 50;
								System.out.println("R Damage : " + player.power);
								sbuff.setDead();
								if (sbuff.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					} else if (player.status == 4 && player.dir == 0 && player.skill3 != true) {
						if (player.x - 100 < sbuff.x && sbuff.x < player.x && sbuff.life == true) {
							if (player.y - 30 < sbuff.y && sbuff.y < player.y + 20) {
								sbuff.hp -= player.power;
								sbuff.x -= 50;
								System.out.println("L Damage : " + player.power);
								sbuff.setDead();
								if (sbuff.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					}
				} else {
					if (player.status == 4 && player.dir == 1 && player.skill3 != true) {
						if (player.x < sbuff.x && sbuff.x < player.x + 150 && sbuff.life == true) {
							if (player.y - 30 < sbuff.y && sbuff.y < player.y + 20) {
								sbuff.hp -= player.power;
								sbuff.x += 50;
								System.out.println("R Damage : " + player.power);
								sbuff.setDead();
								if (sbuff.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					} else if (player.status == 4 && player.dir == 0 && player.skill3 != true) {
						if (player.x - 150 < sbuff.x && sbuff.x < player.x && sbuff.life == true) {
							if (player.y - 30 < sbuff.y && sbuff.y < player.y + 20) {
								sbuff.hp -= player.power;
								sbuff.x -= 50;
								System.out.println("L Damage : " + player.power);
								sbuff.setDead();
								if (sbuff.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		for (int j = goblin2s.size() - 1; j >= 0; j--) {
			sbuff2 = goblin2s.get(j);
			if (sbuff2 != null) {
				if (characterchoice == 1) {
					if (player.status == 4 && player.dir == 1 && player.skill3 != true) {
						if (player.x < sbuff2.x && sbuff2.x < player.x + 100 && sbuff2.life == true) {
							if (player.y - 30 < sbuff2.y && sbuff2.y < player.y + 20) {
								sbuff2.hp -= player.power;
								sbuff2.x += 50;
								System.out.println("R Damage : " + player.power);
								sbuff2.setDead();
								if (sbuff2.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					} else if (player.status == 4 && player.dir == 0 && player.skill3 != true) {
						if (player.x - 100 < sbuff2.x && sbuff2.x < player.x && sbuff2.life == true) {
							if (player.y - 30 < sbuff2.y && sbuff2.y < player.y + 20) {
								sbuff2.hp -= player.power;
								sbuff2.x -= 50;
								System.out.println("L Damage : " + player.power);
								sbuff2.setDead();
								if (sbuff2.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					}

				} else {
					if (player.status == 4 && player.dir == 1 && player.skill3 != true) {
						if (player.x < sbuff2.x && sbuff2.x < player.x + 150 && sbuff2.life == true) {
							if (player.y - 30 < sbuff2.y && sbuff2.y < player.y + 20) {
								sbuff2.hp -= player.power;
								sbuff2.x += 50;
								System.out.println("R Damage : " + player.power);
								sbuff2.setDead();
								if (sbuff2.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					} else if (player.status == 4 && player.dir == 0 && player.skill3 != true) {
						if (player.x - 150 < sbuff2.x && sbuff2.x < player.x && sbuff2.life == true) {
							if (player.y - 30 < sbuff2.y && sbuff2.y < player.y + 20) {
								sbuff2.hp -= player.power;
								sbuff2.x -= 50;
								System.out.println("L Damage : " + player.power);
								sbuff2.setDead();
								if (sbuff2.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					}
				}
			}
		}

		for (int j = goblin3s.size() - 1; j >= 0; j--) {
			sbuff3 = goblin3s.get(j);
			if (sbuff3 != null) {
				if (characterchoice == 1) {
					if (player.status == 4 && player.dir == 1 && player.skill3 != true) {
						if (player.x < sbuff3.x && sbuff3.x < player.x + 100 && sbuff3.life == true) {
							if (player.y - 30 < sbuff3.y && sbuff3.y < player.y + 20) {
								sbuff3.hp -= player.power;
								sbuff3.x += 50;
								System.out.println("R Damage : " + player.power);
								sbuff3.setDead();
								if (sbuff3.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					} else if (player.status == 4 && player.dir == 0 && player.skill3 != true) {
						if (player.x - 100 < sbuff3.x && sbuff3.x < player.x && sbuff3.life == true) {
							if (player.y - 30 < sbuff3.y && sbuff3.y < player.y + 20) {
								sbuff3.hp -= player.power;
								sbuff3.x -= 50;
								System.out.println("L Damage : " + player.power);
								sbuff3.setDead();
								if (sbuff3.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					}

				} else {
					if (player.status == 4 && player.dir == 1 && player.skill3 != true) {
						if (player.x < sbuff3.x && sbuff3.x < player.x + 150 && sbuff3.life == true) {
							if (player.y - 30 < sbuff3.y && sbuff3.y < player.y + 20) {
								sbuff3.hp -= player.power;
								sbuff3.x += 50;
								System.out.println("R Damage : " + player.power);
								sbuff3.setDead();
								if (sbuff3.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					} else if (player.status == 4 && player.dir == 0 && player.skill3 != true) {
						if (player.x - 150 < sbuff3.x && sbuff3.x < player.x && sbuff3.life == true) {
							if (player.y - 30 < sbuff3.y && sbuff3.y < player.y + 20) {
								sbuff3.hp -= player.power;
								sbuff3.x -= 50;
								System.out.println("L Damage : " + player.power);
								sbuff3.setDead();
								if (sbuff3.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		for (int j = bosses.size() - 1; j >= 0; j--) {
			bbuff = bosses.get(j);
			if (bbuff != null) {
				if (characterchoice == 1) {
					if (player.status == 4 && player.dir == 1 && player.skill3 != true) {
						if (player.x < bbuff.x && bbuff.x < player.x + 100 && bbuff.life == true) {
							if (player.y - 30 < bbuff.y && bbuff.y < player.y + 20) {
								bbuff.hp -= player.power;
								bbuff.x += 50;
								System.out.println("R Damage : " + player.power);
								bbuff.setDead();
								if (bbuff.hp > 0)
									sound.Sound("rsc/sound/boss_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/boss_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					} else if (player.status == 4 && player.dir == 0 && player.skill3 != true) {
						if (player.x - 100 < bbuff.x && bbuff.x < player.x && bbuff.life == true) {
							if (player.y - 30 < bbuff.y && bbuff.y < player.y + 20) {
								bbuff.hp -= player.power;
								bbuff.x -= 50;
								System.out.println("L Damage : " + player.power);
								bbuff.setDead();
								if (bbuff.hp > 0)
									sound.Sound("rsc/sound/boss_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/boss_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					}

				} else {
					if (player.status == 4 && player.dir == 1 && player.skill3 != true) {
						if (player.x < bbuff.x && bbuff.x < player.x + 150 && bbuff.life == true) {
							if (player.y - 30 < bbuff.y && bbuff.y < player.y + 20) {
								bbuff.hp -= player.power;
								bbuff.x += 50;
								System.out.println("R Damage : " + player.power);
								bbuff.setDead();
								if (bbuff.hp > 0)
									sound.Sound("rsc/sound/boss_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/boss_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					} else if (player.status == 4 && player.dir == 0 && player.skill3 != true) {
						if (player.x - 150 < bbuff.x && bbuff.x < player.x && bbuff.life == true) {
							if (player.y - 30 < bbuff.y && bbuff.y < player.y + 20) {
								bbuff.hp -= player.power;
								bbuff.x -= 50;
								System.out.println("L Damage : " + player.power);
								bbuff.setDead();
								if (bbuff.hp > 0)
									sound.Sound("rsc/sound/boss_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/boss_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		for (int j = thieves.size() - 1; j >= 0; j--) {
			tbuff = thieves.get(j);
			if (tbuff != null) {
				if (characterchoice == 1) {
					if (player.status == 4 && player.dir == 1 && player.skill3 != true) {
						if (player.x < tbuff.x && tbuff.x < player.x + 100 && tbuff.life == true) {
							if (player.y - 30 < tbuff.y && tbuff.y < player.y + 20) {
								tbuff.hp -= player.power;
								tbuff.x += 50;
								System.out.println("R Damage : " + player.power);
								tbuff.setDead();
								if (tbuff.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					} else if (player.status == 4 && player.dir == 0 && player.skill3 != true) {
						if (player.x - 100 < tbuff.x && tbuff.x < player.x && tbuff.life == true) {
							if (player.y - 30 < tbuff.y && tbuff.y < player.y + 20) {
								tbuff.hp -= player.power;
								tbuff.x -= 50;
								System.out.println("L Damage : " + player.power);
								tbuff.setDead();
								if (tbuff.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					}

				} else {
					if (player.status == 4 && player.dir == 1 && player.skill3 != true) {
						if (player.x < tbuff.x && tbuff.x < player.x + 150 && tbuff.life == true) {
							if (player.y - 30 < tbuff.y && tbuff.y < player.y + 20) {
								tbuff.hp -= player.power;
								tbuff.x += 50;
								System.out.println("R Damage : " + player.power);
								tbuff.setDead();
								if (tbuff.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					} else if (player.status == 4 && player.dir == 0 && player.skill3 != true) {
						if (player.x - 150 < tbuff.x && tbuff.x < player.x && tbuff.life == true) {
							if (player.y - 30 < tbuff.y && tbuff.y < player.y + 20) {
								tbuff.hp -= player.power;
								tbuff.x -= 50;
								System.out.println("L Damage : " + player.power);
								tbuff.setDead();
								if (tbuff.hp > 0)
									sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
								else {
									sound.Sound("rsc/sound/gbn_die.wav", false, 5);
									if (rand.nextInt(5) == 0) {
										player.hp_portion.get_Portion();
									}
									if (rand.nextInt(5) == 0) {
										player.mp_portion.get_Portion();
									}
								}
								break;
							}
						}
					}
				}
			}
		}

	}

	void processWave() { // 스킬 충돌처리 함수

		Wave buff;

		for (int i = waves.size() - 1; i >= 0; i--) {
			buff = waves.get(i);
			if (buff != null) {
				if (!buff.process())
					waves.remove(i);
				else {
					Goblin sbuff;
					Goblin2 sbuff2;
					Goblin3 sbuff3;
					Boss bbuff;
					Thief tbuff;

					for (int j = goblins.size() - 1; j >= 0; j--) {
						sbuff = goblins.get(j);
						if (sbuff != null) {
							if (buff.dir == 1 && buff.x < sbuff.x && sbuff.x < buff.x + 50 && sbuff.life == true) {
								if (buff.y - 20 < sbuff.y && sbuff.y < buff.y + 200) {

									System.out.println(waves.get(i).power);

									sbuff.hp -= waves.get(i).power;
									/*
									 * if (sbuff.dir == 0) { sbuff.x += 15; }
									 * else { sbuff.x -= 15; }
									 */
									// waves.remove(i);
									sbuff.setDead();
									if (sbuff.hp > 0)
										sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
									else {
										sound.Sound("rsc/sound/gbn_die.wav", false, 5);
										if (rand.nextInt(5) == 0) {
											player.hp_portion.get_Portion();
										}
										if (rand.nextInt(5) == 0) {
											player.mp_portion.get_Portion();
										}
									}
									break;
								}

							} else if (buff.dir == 0 && buff.x - 50 < sbuff.x && sbuff.x < buff.x
									&& sbuff.life == true) {
								if (buff.y - 20 < sbuff.y && sbuff.y < buff.y + 200) {
									System.out.println(waves.get(i).power);
									sbuff.hp -= waves.get(i).power;
									/*
									 * if (sbuff.dir == 0) { sbuff.x += 15; }
									 * else { sbuff.x -= 15; }
									 */
									// waves.remove(i);
									sbuff.setDead();
									if (sbuff.hp > 0)
										sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
									else {
										sound.Sound("rsc/sound/gbn_die.wav", false, 5);
										if (rand.nextInt(5) == 0) {
											player.hp_portion.get_Portion();
										}
										if (rand.nextInt(5) == 0) {
											player.mp_portion.get_Portion();
										}
									}
									break;
								}
							}
						}
					}

					for (int j = goblin2s.size() - 1; j >= 0; j--) {
						sbuff2 = goblin2s.get(j);
						if (sbuff2 != null) {
							if (buff.dir == 1 && buff.x < sbuff2.x && sbuff2.x < buff.x + 50 && sbuff2.life == true) {
								if (buff.y - 20 < sbuff2.y && sbuff2.y < buff.y + 200) {

									System.out.println(waves.get(i).power);
									sbuff2.hp -= waves.get(i).power;
									/*
									 * if (sbuff.dir == 0) { sbuff.x += 15; }
									 * else { sbuff.x -= 15; }
									 */
									// waves.remove(i);
									sbuff2.setDead();
									if (sbuff2.hp > 0)
										sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
									else {
										sound.Sound("rsc/sound/gbn_die.wav", false, 5);
										if (rand.nextInt(5) == 0) {
											player.hp_portion.get_Portion();
										}
										if (rand.nextInt(5) == 0) {
											player.mp_portion.get_Portion();
										}
									}
									break;
								}

							} else if (buff.dir == 0 && buff.x - 50 < sbuff2.x && sbuff2.x < buff.x
									&& sbuff2.life == true) {
								if (buff.y - 20 < sbuff2.y && sbuff2.y < buff.y + 200) {
									System.out.println(waves.get(i).power);
									sbuff2.hp -= waves.get(i).power;
									/*
									 * if (sbuff.dir == 0) { sbuff.x += 15; }
									 * else { sbuff.x -= 15; }
									 */
									// waves.remove(i);
									sbuff2.setDead();
									if (sbuff2.hp > 0)
										sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
									else {
										sound.Sound("rsc/sound/gbn_die.wav", false, 5);
										if (rand.nextInt(5) == 0) {
											player.hp_portion.get_Portion();
										}
										if (rand.nextInt(5) == 0) {
											player.mp_portion.get_Portion();
										}
									}
									break;
								}
							}
						}
					}

					for (int j = goblin3s.size() - 1; j >= 0; j--) {
						sbuff3 = goblin3s.get(j);
						if (sbuff3 != null) {
							if (buff.dir == 1 && buff.x < sbuff3.x && sbuff3.x < buff.x + 50 && sbuff3.life == true) {
								if (buff.y - 20 < sbuff3.y && sbuff3.y < buff.y + 200) {

									System.out.println(waves.get(i).power);
									sbuff3.hp -= waves.get(i).power;
									/*
									 * if (sbuff.dir == 0) { sbuff.x += 15; }
									 * else { sbuff.x -= 15; }
									 */
									// waves.remove(i);
									sbuff3.setDead();
									if (sbuff3.hp > 0)
										sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
									else {
										sound.Sound("rsc/sound/gbn_die.wav", false, 5);
										if (rand.nextInt(5) == 0) {
											player.hp_portion.get_Portion();
										}
										if (rand.nextInt(5) == 0) {
											player.mp_portion.get_Portion();
										}
									}
									break;
								}

							} else if (buff.dir == 0 && buff.x - 50 < sbuff3.x && sbuff3.x < buff.x
									&& sbuff3.life == true) {
								if (buff.y - 20 < sbuff3.y && sbuff3.y < buff.y + 200) {
									System.out.println(waves.get(i).power);
									sbuff3.hp -= waves.get(i).power;
									/*
									 * if (sbuff.dir == 0) { sbuff.x += 15; }
									 * else { sbuff.x -= 15; }
									 */
									// waves.remove(i);
									sbuff3.setDead();
									if (sbuff3.hp > 0)
										sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
									else {
										sound.Sound("rsc/sound/gbn_die.wav", false, 5);
										if (rand.nextInt(5) == 0) {
											player.hp_portion.get_Portion();
										}
										if (rand.nextInt(5) == 0) {
											player.mp_portion.get_Portion();
										}
									}
									break;
								}
							}
						}
					}
					for (int j = bosses.size() - 1; j >= 0; j--) {
						bbuff = bosses.get(j);
						if (bbuff != null) {
							if (buff.dir == 1 && buff.x < bbuff.x && bbuff.x < buff.x + 50 && bbuff.life == true) {
								if (buff.y - 20 < bbuff.y && bbuff.y < buff.y + 200) {

									System.out.println(waves.get(i).power);
									bbuff.hp -= waves.get(i).power;
									/*
									 * if (sbuff.dir == 0) { sbuff.x += 15; }
									 * else { sbuff.x -= 15; }
									 */
									// waves.remove(i);
									bbuff.setDead();
									if (bbuff.hp > 0)
										sound.Sound("rsc/sound/boss_dmg.wav", false, 5);
									else {
										sound.Sound("rsc/sound/boss_die.wav", false, 5);
										if (rand.nextInt(5) == 0) {
											player.hp_portion.get_Portion();
										}
										if (rand.nextInt(5) == 0) {
											player.mp_portion.get_Portion();
										}
									}
									break;
								}

							} else if (buff.dir == 0 && buff.x - 50 < bbuff.x && bbuff.x < buff.x
									&& bbuff.life == true) {
								if (buff.y - 20 < bbuff.y && bbuff.y < buff.y + 200) {
									System.out.println(waves.get(i).power);
									bbuff.hp -= waves.get(i).power;
									/*
									 * if (sbuff.dir == 0) { sbuff.x += 15; }
									 * else { sbuff.x -= 15; }
									 */
									// waves.remove(i);
									bbuff.setDead();
									if (bbuff.hp > 0)
										sound.Sound("rsc/sound/boss_dmg.wav", false, 5);
									else {
										sound.Sound("rsc/sound/boss_die.wav", false, 5);
										if (rand.nextInt(5) == 0) {
											player.hp_portion.get_Portion();
										}
										if (rand.nextInt(5) == 0) {
											player.mp_portion.get_Portion();
										}
									}
									break;
								}
							}
						}
					}

					for (int j = thieves.size() - 1; j >= 0; j--) {
						tbuff = thieves.get(j);
						if (tbuff != null) {
							if (buff.dir == 1 && buff.x < tbuff.x && tbuff.x < buff.x + 50 && tbuff.life == true) {
								if (buff.y - 20 < tbuff.y && tbuff.y < buff.y + 200) {

									System.out.println(waves.get(i).power);
									tbuff.hp -= waves.get(i).power;
									/*
									 * if (sbuff.dir == 0) { sbuff.x += 15; }
									 * else { sbuff.x -= 15; }
									 */
									// waves.remove(i);
									tbuff.setDead();
									if (tbuff.hp > 0)
										sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
									else {
										sound.Sound("rsc/sound/gbn_die.wav", false, 5);
										if (rand.nextInt(5) == 0) {
											player.hp_portion.get_Portion();
										}
										if (rand.nextInt(5) == 0) {
											player.mp_portion.get_Portion();
										}
									}
									break;
								}

							} else if (buff.dir == 0 && buff.x - 50 < tbuff.x && tbuff.x < buff.x
									&& tbuff.life == true) {
								if (buff.y - 20 < tbuff.y && tbuff.y < buff.y + 200) {
									System.out.println(waves.get(i).power);
									tbuff.hp -= waves.get(i).power;
									/*
									 * if (sbuff.dir == 0) { sbuff.x += 15; }
									 * else { sbuff.x -= 15; }
									 */
									// waves.remove(i);
									tbuff.setDead();
									if (tbuff.hp > 0)
										sound.Sound("rsc/sound/gbn_dmg.wav", false, 5);
									else {
										sound.Sound("rsc/sound/gbn_die.wav", false, 5);
										if (rand.nextInt(5) == 0) {
											player.hp_portion.get_Portion();
										}
										if (rand.nextInt(5) == 0) {
											player.mp_portion.get_Portion();
										}
									}
									break;
								}
							}
						}
					}
				}
			}
		}
	}

	void processEnemy() {

		Goblin buff;
		Goblin2 buff2;
		Goblin3 buff3;
		Boss bbuff;
		Thief tbuff;

		for (int i = goblins.size() - 1; i >= 0; i--) {
			buff = goblins.get(i);
			if (buff.status == 0 || buff.status == 1) {
				if (buff.dir == 0 && player.x > buff.x - 150 && player.x < buff.x) {
					if (player.y > buff.y - 10 && player.y < buff.y + 30) {

						buff.setAttack();
						if (player.x > buff.x - 50 && player.x < buff.x) {
							sound.Sound("rsc/sound/damage.wav", false, 5);
							player.hp -= buff.power;
							player.x -= 30;
							player.setDead();
						}

						System.out.println(player.hp);

					}
				} else if (buff.dir == 1 && player.x > buff.x && player.x < buff.x + 150) {
					if (player.y > buff.y - 10 && player.y < buff.y + 30) {
						buff.setAttack();
						if (player.x > buff.x && player.x < buff.x + 50) {
							sound.Sound("rsc/sound/damage.wav", false, 5);
							player.hp -= buff.power;
							player.x += 30;
							player.setDead();
						}

						System.out.println(player.hp);
					}
				}
			}
			if (buff != null) {
				if (!buff.Process(cnt))
					goblins.remove(i);
			}
		}
		for (int i = goblin2s.size() - 1; i >= 0; i--) {
			buff2 = goblin2s.get(i);
			if (buff2.status == 0 || buff2.status == 1) {
				if (buff2.dir == 0 && player.x > buff2.x - 150 && player.x < buff2.x) {
					if (player.y > buff2.y - 10 && player.y < buff2.y + 30) {

						buff2.setAttack();
						if (player.x > buff2.x - 50 && player.x < buff2.x) {
							sound.Sound("rsc/sound/damage.wav", false, 5);
							player.hp -= buff2.power;
							player.x -= 30;
							player.setDead();
						}

						System.out.println(player.hp);
					}
				} else if (buff2.dir == 1 && player.x > buff2.x && player.x < buff2.x + 150) {
					if (player.y > buff2.y - 10 && player.y < buff2.y + 30) {
						buff2.setAttack();
						if (player.x > buff2.x && player.x < buff2.x + 50) {
							sound.Sound("rsc/sound/damage.wav", false, 5);
							player.hp -= buff2.power;
							player.x += 30;
							player.setDead();
						}
						System.out.println(player.hp);
					}
				}
			}
			if (buff2 != null) {
				if (!buff2.Process(cnt))
					goblin2s.remove(i);
			}
		}
		for (int i = goblin3s.size() - 1; i >= 0; i--) {
			buff3 = goblin3s.get(i);
			if (buff3.status == 0 || buff3.status == 1) {
				if (buff3.dir == 0 && player.x > buff3.x - 150 && player.x < buff3.x) {
					if (player.y > buff3.y - 10 && player.y < buff3.y + 30) {

						buff3.setAttack();
						if (player.x > buff3.x - 50 && player.x < buff3.x) {
							sound.Sound("rsc/sound/damage.wav", false, 5);
							player.hp -= buff3.power;
							player.x -= 30;
							player.setDead();
						}

						System.out.println(player.hp);
					}
				} else if (buff3.dir == 1 && player.x > buff3.x && player.x < buff3.x + 150) {
					if (player.y > buff3.y - 10 && player.y < buff3.y + 30) {
						buff3.setAttack();
						if (player.x > buff3.x && player.x < buff3.x + 50) {
							sound.Sound("rsc/sound/damage.wav", false, 5);
							player.hp -= buff3.power;
							player.x += 30;
							player.setDead();
						}

						System.out.println(player.hp);
					}
				}
			}
			if (buff3 != null) {
				if (!buff3.Process(cnt))
					goblin3s.remove(i);
			}
		}
		for (int i = bosses.size() - 1; i >= 0; i--) {
			bbuff = bosses.get(i);
			if (bbuff.status == 0 || bbuff.status == 1) {
				if (bbuff.dir == 0 && player.x > bbuff.x - 200 && player.x < bbuff.x) {
					if (player.y > bbuff.y - 10 && player.y < bbuff.y + 30) {
						bbuff.setAttack();
						if (player.x > bbuff.x - 250 && player.x < bbuff.x) {
							attackBoss();
						}
						System.out.println(player.hp);
					}
				} else if (bbuff.dir == 1 && player.x > bbuff.x && player.x < bbuff.x + 200) {
					if (player.y > bbuff.y - 10 && player.y < bbuff.y + 30) {
						bbuff.setAttack();
						if (player.x > bbuff.x && player.x < bbuff.x + 200) {
							attackBoss();
						}
						System.out.println(player.hp);
					}
				}
			}
			if (bbuff != null) {
				if (!bbuff.Process(cnt))
					bosses.remove(i);
			}
		}

		for (int i = thieves.size() - 1; i >= 0; i--) {
			tbuff = thieves.get(i);
			if (tbuff.status == 0 || tbuff.status == 1) {
				if (tbuff.dir == 0 && player.x > tbuff.x - 200 && player.x < tbuff.x) {
					if (player.y > tbuff.y - 10 && player.y < tbuff.y + 30) {
						tbuff.setWalk();
					}
				} else if (tbuff.dir == 1 && player.x > tbuff.x && player.x < tbuff.x + 200) {
					if (player.y > tbuff.y - 10 && player.y < tbuff.y + 30) {
						tbuff.setWalk();
					}
				}
			}
			if (tbuff != null) {
				if (!tbuff.Process(cnt))
					thieves.remove(i);
			}
		}
	}

	void processQuiz() {
		if (key == -1) {
			player.hp -= 10;
			key = 0;
		}
	}

	void processBoss() {
		Wave buff;
		for (int i = boss_waves.size() - 1; i >= 0; i--) {
			buff = boss_waves.get(i);
			if (buff != null) {
				if (!buff.process()) {
					if (buff.x < bosses.get(0).x - 200 || buff.x > bosses.get(0).x + 200)
						boss_waves.remove(i);
				} else {
					if (player != null) {

						if (buff.dir == 1 && buff.x + 10 < player.x && player.x < buff.x + 50) {
							if (buff.y < player.y && player.y < buff.y + 150) {
								System.out.println(boss_waves.get(i).power);
								player.hp -= boss_waves.get(i).power;
								player.setDead();
								sound.Sound("rsc/sound/damage.wav", false, 5);
								System.out.println(player.hp);
								break;
							}

						} else if (buff.dir == 0 && buff.x - 20 < player.x && player.x < buff.x + 20) {
							if (buff.y < player.y && player.y < buff.y + 150) {
								System.out.println(boss_waves.get(i).power);
								player.hp -= boss_waves.get(i).power;
								player.setDead();
								sound.Sound("rsc/sound/damage.wav", false, 5);
								System.out.println(player.hp);
								break;
							}
						}
					}
				}
			}
		}
	}

	// kh4
	void processStage() {

		if (rescued == false && home.home && player.x > 950) {
			System.out.println("hello");
			stage.stage1 = true;
			home.home = false;
			stage.stage1(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			bosses.clear();
			for (int i = 0; i < 3; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}

		if (rescued == false && player.x > 950 && player.y > 350 && stage.stage1 && goblins.get(0).hp <= 0) {
			stage.stage2 = true;
			stage.stage1 = false;
			stage.stage2(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			bosses.clear();
			for (int i = 0; i < 4; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}

		// ====================================================================
		if (rescued == false && player.x < 100 && player.y > 300 && player.y < 450 && stage.stage2
				&& goblins.get(0).hp <= 0) {
			stage.stage1 = true;
			stage.stage2 = false;
			stage.stage1(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 3; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		if (rescued == false && player.x > 350 && player.x < 600 && player.y > 600 && stage.stage2
				&& goblins.get(0).hp <= 0) {
			stage.stage4 = true;
			stage.stage2 = false;
			stage.stage4(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 4; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		if (rescued == false && player.x > 950 && player.y > 300 && player.y < 450 && stage.stage2
				&& goblins.get(0).hp <= 0) {
			stage.stage3 = true;
			stage.stage2 = false;
			stage.stage3(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 4; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		// ====================================================================
		if (rescued == false && player.x < 100 && player.y > 300 && player.y < 450 && stage.stage3
				&& goblins.get(0).hp <= 0) {
			stage.stage2 = true;
			stage.stage3 = false;
			stage.stage2(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 4; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		// ====================================================================
		if (rescued == false && player.x > 350 && player.x < 600 && player.y < 130 && stage.stage4
				&& goblins.get(0).hp <= 0) {
			stage.stage2 = true;
			stage.stage4 = false;
			stage.stage2(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 4; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}

		if (rescued == false && player.x > 350 && player.x < 600 && player.y > 600 && stage.stage4
				&& goblins.get(0).hp <= 0) {
			stage.stage5 = true;
			stage.stage4 = false;
			stage.stage5(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 6; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		// ====================================================================

		if (rescued == false && player.x > 350 && player.x < 600 && player.y < 130 && stage.stage5
				&& goblins.get(0).hp <= 0) {
			stage.stage4 = true;
			stage.stage5 = false;
			stage.stage4(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 4; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		if (rescued == false && player.x < 100 && player.y > 300 && player.y < 450 && stage.stage5
				&& goblins.get(0).hp <= 0) {
			stage.stage6 = true;
			stage.stage5 = false;
			stage.stage6(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 5; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		if (rescued == false && player.x > 950 && player.y > 300 && player.y < 450 && stage.stage5
				&& goblins.get(0).hp <= 0) {
			stage.stage8 = true;
			stage.stage5 = false;
			stage.stage8(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 2; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);

				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
			for (int i = 0; i < 3; i++) {
				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		// ====================================================================
		if (rescued == false && player.x > 950 && player.y > 300 && player.y < 450 && stage.stage6
				&& goblins.get(0).hp <= 0) {
			stage.stage5 = true;
			stage.stage6 = false;
			stage.stage5(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 6; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}

		if (rescued == false && player.x > 350 && player.x < 600 && player.y > 600 && stage.stage6
				&& goblins.get(0).hp <= 0) {
			stage.stage7 = true;
			stage.stage6 = false;
			stage.stage7(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 1; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
			for (int i = 0; i < 3; i++) {

				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);

			}
		}
		// ====================================================================
		if (rescued == false && player.x > 350 && player.x < 600 && player.y < 130 && stage.stage7
				&& goblins.get(0).hp <= 0) {
			stage.stage6 = true;
			stage.stage7 = false;
			stage.stage6(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 5; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		// ====================================================================
		if (rescued == false && player.x < 100 && player.y > 300 && player.y < 450 && stage.stage8
				&& goblins.get(0).hp <= 0) {
			stage.stage5 = true;
			stage.stage8 = false;
			stage.stage5(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 6; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		if (rescued == false && player.x > 350 && player.x < 600 && player.y > 600 && stage.stage8
				&& goblins.get(0).hp <= 0) {
			stage.stage9 = true;
			stage.stage8 = false;
			stage.stage9(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			for (int i = 0; i < 2; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		if (rescued == false && player.x > 950 && player.y > 300 && player.y < 450 && stage.stage8
				&& goblins.get(0).hp <= 0) {
			stage.stage10 = true;
			stage.stage8 = false;
			stage.stage10(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 3; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		// ====================================================================
		if (rescued == false && player.x > 350 && player.x < 600 && player.y < 130 && stage.stage9
				&& goblins.get(0).hp <= 0) {
			stage.stage8 = true;
			stage.stage9 = false;
			stage.stage8(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 2; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);

				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
			for (int i = 0; i < 3; i++) {
				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		// ====================================================================
		if (rescued == false && player.x > 350 && player.x < 600 && player.y < 130 && stage.stage10
				&& goblins.get(0).hp <= 0) {
			stage.stage13 = true;
			stage.stage10 = false;
			stage.stage13(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 2; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		if (rescued == false && player.x < 100 && player.y > 300 && player.y < 450 && stage.stage10
				&& goblins.get(0).hp <= 0) {
			stage.stage8 = true;
			stage.stage10 = false;
			stage.stage8(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 2; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);

				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
			for (int i = 0; i < 3; i++) {
				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		if (rescued == false && player.x > 950 && player.y > 300 && player.y < 450 && stage.stage10
				&& goblins.get(0).hp <= 0) {
			stage.stage11 = true;
			stage.stage10 = false;
			stage.stage11(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 2; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);

			}

			for (int i = 0; i < 1; i++) {
				genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		// ====================================================================
		if (rescued == false && player.x < 100 && player.y > 300 && player.y < 450 && stage.stage11
				&& goblins.get(0).hp <= 0) {
			stage.stage10 = true;
			stage.stage11 = false;
			stage.stage10(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 3; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		if (rescued == false && player.x > 350 && player.x < 600 && player.y > 600 && stage.stage11
				&& goblins.get(0).hp <= 0) {
			stage.stage12 = true;
			stage.stage11 = false;
			stage.stage12(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 2; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}

		// ====================================================================
		if (rescued == false && player.x > 350 && player.x < 600 && player.y < 130 && stage.stage12
				&& goblins.get(0).hp <= 0) {
			stage.stage11 = true;
			stage.stage12 = false;
			stage.stage11(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 2; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);

			}

			for (int i = 0; i < 1; i++) {
				genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}
		// ====================================================================

		if (rescued == false && player.x > 350 && player.x < 600 && player.y < 130 && stage.stage13
				&& goblins.get(0).hp <= 0) {
			stage.stage14 = true;
			stage.stage13 = false;
			stage.stage14(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 3; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}

		if (rescued == false && player.x > 350 && player.x < 600 && player.y > 600 && stage.stage13
				&& goblins.get(0).hp <= 0) {
			stage.stage10 = true;
			stage.stage13 = false;
			stage.stage10(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 3; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}

		// ====================================================================
		if (rescued == false && player.x > 350 && player.x < 600 && player.y > 600 && stage.stage14
				&& goblins.get(0).hp <= 0) {
			stage.stage13 = true;
			stage.stage14 = false;
			stage.stage10(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			for (int i = 0; i < 2; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				// genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}
		}

		if (rescued == false && player.x > 950 && player.y > 300 && player.y < 450 && stage.stage14
				&& goblins.get(0).hp <= 0) {
			stage.stage15 = true;
			stage.stage14 = false;
			stage.stage15(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();

			genBoss(700, 450);

			for (int i = 0; i < 2; i++) {
				genGoblin(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin2(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
				genGoblin3(rand.nextInt(700) + 100, rand.nextInt(450) + 150);
			}

		}

		// ======================================================================
		if (rescued == false && player.x > 950 && player.y > 300 && player.y < 600 && stage.stage15
				&& bosses.get(0).hp <= 0) {
			stage.stage16 = true;
			stage.stage15 = false;
			stage.stage16(player);
			waves.clear();
			goblins.clear();
			goblin2s.clear();
			goblin3s.clear();
			bosses.clear();

		}
		// ====================================================================

		if (rescued == false && seria == true && stage.stage16 && player.x > 900 && player.y > 300 && player.y < 600) {
			home.home = true;
			stage.stage16 = false;
			home.home(player);
			waves.clear();
			rescued = true;

		}

		if (rescued == true && home.home && player.x < 100 && player.y > 300 && player.y < 600) {
			puzzle.stage1 = true;
			home.home = false;
			puzzle.stage1(player);

		}

		if (rescued == true && puzzle.stage1 && player.x < 100 && player.y > 300 && player.y < 600 && key == 1) {
			puzzle.stage2 = true;
			puzzle.stage1 = false;
			key = 0;
			puzzle.stage2(player);

		}
		if (rescued == true && puzzle.stage2 && player.x < 100 && player.y > 300 && player.y < 600 && key == 1) {
			puzzle.stage3 = true;
			puzzle.stage2 = false;
			key = 0;
			puzzle.stage3(player);

		}
		if (rescued == true && puzzle.stage3 && player.x < 100 && player.y > 300 && player.y < 600 && key == 1) {
			puzzle.stage4 = true;
			puzzle.stage3 = false;
			key = 0;
			puzzle.stage4(player);
		}
		if (rescued == true && puzzle.stage4 && player.x < 100 && player.y > 300 && player.y < 600 && key == 1) {
			puzzle.stage5 = true;
			puzzle.stage4 = false;
			key = 0;
			puzzle.stage5(player);

		}
		if (rescued == true && puzzle.stage5 && player.x < 100 && player.y > 300 && player.y < 600 && key == 1) {
			puzzle.stage6 = true;
			puzzle.stage5 = false;
			puzzle.stage6(player);
			genThief(400, 400);
		}
		if (rescued == true && puzzle.stage6 && player.x < 100 && player.y > 300 && player.y < 600
				&& thieves.get(0).hp <= 0) {
			puzzle.stage7 = true;
			puzzle.stage6 = false;
			thieves.clear();
			key = 0;
			puzzle.stage7(player);
		}
	}

	void processEffect() {

	}

	void keyProcess() {

		if (keyBuff_FIRE) {

			if (player.status == 0 || player.status == 1) {
				player.status = 2;
				return;
			} else
				keyBuff_FIRE = false;
		}
		if (keyBuff_SKILL) {
			if (player.status == 0 || player.status == 1) {
				player.status = 4;
				return;
			} else
				keyBuff_SKILL = false;
		}
		if (keyBuff_LEFT && player.status != 3) {

			if (player.status != 2 || player.status != 4) {
				player.dir = 0;
				player.status = 1;
			}
			if (player.status == 0 || player.status == 1)
				if (player.x >= 100) {
					player.x -= 1 * player.speed;
				} else if (player.status == 2 && player.movingShot)
					player.x -= 0.5f * player.speed;
		}
		if (keyBuff_RIGHT && player.status != 3) {

			if (player.status != 2 || player.status != 2) {
				player.dir = 1;
				player.status = 1;
			}
			if (player.status == 0 || player.status == 1)
				if (player.x <= 951) {
					player.x += 1 * player.speed;
				} else if (player.status == 2 && player.movingShot)
					player.x += 0.5f * player.speed;
		}
		if (keyBuff_UP && player.status != 3) {

			if (player.status != 2 || player.status != 2)
				player.status = 1;
			if (player.status == 0 || player.status == 1)
				if (home.home) {
					y_limit = 300;
				} else {
					y_limit = 130;
				}
			if (player.y >= y_limit)
				player.y -= 0.5f * player.speed;
			else if (player.status == 2 && player.movingShot)
				player.y -= 0.25f * player.speed;
		}
		if (keyBuff_DOWN && player.status != 3) {

			if (player.status != 2 || player.status != 2)
				player.status = 1;
			if (player.status == 0 || player.status == 1)
				if (player.y <= 630)
					player.y += 0.5f * player.speed;

				else if (player.status == 2 && player.movingShot)

					player.y += 0.25f * player.speed;
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		long timer;

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (textcnt != 1)
				keyBuff_LEFT = true;
			break;
		case KeyEvent.VK_UP:
			if (textcnt != 1)
				keyBuff_UP = true;
			break;
		case KeyEvent.VK_RIGHT:
			if (textcnt != 1)
				keyBuff_RIGHT = true;
			break;
		case KeyEvent.VK_DOWN:
			if (textcnt != 1)
				keyBuff_DOWN = true;
			break;
		case KeyEvent.VK_SHIFT:
			player.run = true;
			System.out.println(player.speed);
			if (characterchoice == 0) {
				player.speed = 6.0f;
			} else {
				player.speed = 7.0f;
			}
			break;
		case KeyEvent.VK_A:
			if (home.home) {
				if (200 < player.x && player.x < 400 && 300 < player.y && player.y < 450) {
					System.out.println("NPC_talk");
					textcnt++;
					if (textcnt > 1)
						textcnt = 0;
				} else if (550 < player.x && player.x < 750 && 300 < player.y && player.y < 450) {
					System.out.println("NPC_talk2");
					textcnt++;
					if (textcnt > 1)
						textcnt = 0;
				}
			} else if (stage.stage16) {
				if (700 < player.x && player.x < 850 && 300 < player.y && player.y < 450) {
					System.out.println("doctor_talk");
					textcnt++;
					if (textcnt > 1)
						textcnt = 0;
					seria = true;
				}
			} else if (rescued && key == 0) {
				if (puzzle.stage1 && 120 < player.x && player.x < 200 && 350 < player.y && player.y < 550) {
					textcnt++;
					if (textcnt > 1) {
						textcnt = 0;
						new Ox(1);
					}
				} else if (puzzle.stage2 && 120 < player.x && player.x < 200 && 350 < player.y && player.y < 550) {
					textcnt++;
					if (textcnt > 1) {
						textcnt = 0;
						new Quiz();
					}
				} else if (puzzle.stage3 && 120 < player.x && player.x < 200 && 350 < player.y && player.y < 550) {
					textcnt++;
					if (textcnt > 1) {
						textcnt = 0;
						new Ox(2);
					}
				} else if (puzzle.stage4 && 120 < player.x && player.x < 200 && 350 < player.y && player.y < 550) {
					textcnt++;
					if (textcnt > 1) {
						textcnt = 0;
						new DIFF();
					}
				} else if (puzzle.stage5 && 120 < player.x && player.x < 200 && 350 < player.y && player.y < 550) {
					textcnt++;
					if (textcnt > 1) {
						textcnt = 0;
						new Ox(3);
					}
				} else if (puzzle.stage7 && 250 < player.x && player.x < 330 && 350 < player.y && player.y < 550) {
					textcnt++;
					if (textcnt > 1) {
						textcnt = 0;
						new RandomBox();
					}
				}
			} else {
				if (characterchoice == 0) {
					sound.Sound("rsc/sound/slash.wav", false, 5);
				} else {
					sound.Sound("rsc/sound/punch.wav", false, 5);
				}
				timer = System.currentTimeMillis();
				keyBuff_FIRE = true;
				player.attack1 = true;
				player.attack2 = false;
				long t = timer - keytimer;
				if (150 < t && t < 350) {
					player.attack1 = false;
					player.attack2 = true;
					player.setAttack();
				}
			}
			break;
		case KeyEvent.VK_S:
			if (characterchoice == 0) {
				if (player.mp > 20) {
					sound.Sound("rsc/sound/w_skill1.wav", false, 5);
					keyBuff_SKILL = true;
					player.skill1 = true;
					player.setSkill();
					shootPlayer();
				}
			} else {
				if (player.mp > 25) {
					sound.Sound("rsc/sound/f_skill1.wav", false, 5);
					keyBuff_SKILL = true;
					player.skill1 = true;
					player.setSkill();
				}
			}
			break;
		case KeyEvent.VK_D:
			if (characterchoice == 0) {
				if (player.mp > 40) {
					sound.Sound("rsc/sound/w_skill2.wav", false, 2);
					keyBuff_SKILL = true;
					player.skill2 = true;
					player.setSkill();
				}
			} else {
				if (player.mp > 60) {
					sound.Sound("rsc/sound/f_skill2.wav", false, 5);
					keyBuff_SKILL = true;
					player.skill2 = true;
					player.setSkill();
				}
			}
			break;
		case KeyEvent.VK_F:
			if (player.hp > 50) {
				sound.Sound("rsc/sound/skill3.wav", false, 5);
				keyBuff_SKILL = true;
				player.skill3 = true;
				player.setSkill();
			}
			break;
		case KeyEvent.VK_1:
			player.useHP_Portion();
			if (player.hp_portion.cnt > 0)
				sound.Sound("rsc/sound/hp_portion.wav", false, 5);
			break;
		case KeyEvent.VK_2:
			player.useMP_Portion();
			if (player.mp_portion.cnt > 0)
				sound.Sound("rsc/sound/mp_portion.wav", false, 5);
			break;
		case KeyEvent.VK_9:
			player.hp_portion.get_Portion();
			break;
		case KeyEvent.VK_0:
			player.mp_portion.get_Portion();
			break;
		case KeyEvent.VK_O:
			new DIFF();
		default:
			break;
		}

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			keyBuff_LEFT = false;
			player.run = false;
			break;
		case KeyEvent.VK_UP:
			keyBuff_UP = false;
			break;
		case KeyEvent.VK_RIGHT:
			keyBuff_RIGHT = false;
			player.run = false;
			break;
		case KeyEvent.VK_DOWN:
			keyBuff_DOWN = false;
			break;
		case KeyEvent.VK_SHIFT:
			player.run = false;
			if (characterchoice == 0) {
				player.speed = 3.2f;
			} else {
				player.speed = 4.0f;
			}
			break;
		case KeyEvent.VK_A:
			keyBuff_FIRE = false;
			keytimer = System.currentTimeMillis();
			break;
		case KeyEvent.VK_S:
			keyBuff_SKILL = false;
			if (characterchoice == 1) {
				player.power = 20;
			}
			break;
		case KeyEvent.VK_D:
			keyBuff_SKILL = false;
			if (characterchoice == 0) {
				player.power = 25;
			} else {
				player.power = 20;
			}
			break;
		case KeyEvent.VK_F:
			keyBuff_SKILL = false;
			break;
		}

		if (player.status == 1)
			player.status = 0;
	}

	public void shootPlayer() {
		Wave wave = new Wave(player.dir, player.x + (player.dir == 0 ? -55 : 20), player.y - 130, 8.5f, 5);
		waves.add(wave);
	}

	public void attackBoss() {

		Boss boss;
		if (bosses.get(0) != null) {
			boss = bosses.get(0);
			Wave boss_wave = new Wave(boss.dir, boss.x + (boss.dir == 0 ? +150 : -200), boss.y - 130, 5.5f, 3);
			boss_waves.add(boss_wave);
		}
	}

	public void genGoblin(float x, float y) {

		Goblin goblin = new Goblin(this);
		goblin.x = x;
		goblin.y = y;
		goblin.dir = rand.nextInt(10) % 2;
		if (rand.nextInt(5) < 2)
			goblin.setWalk();
		goblins.add(goblin);
	}

	public void genGoblin2(float x, float y) {

		Goblin2 goblin2 = new Goblin2(this);
		goblin2.x = x;
		goblin2.y = y;
		goblin2.dir = rand.nextInt(10) % 2;
		if (rand.nextInt(5) < 2)
			goblin2.setWalk();
		goblin2s.add(goblin2);
	}

	public void genGoblin3(float x, float y) {

		Goblin3 goblin3 = new Goblin3(this);
		goblin3.x = x;
		goblin3.y = y;
		goblin3.dir = rand.nextInt(10) % 2;
		if (rand.nextInt(5) < 2)
			goblin3.setWalk();
		goblin3s.add(goblin3);
	}

	public void genBoss(float x, float y) {

		Boss boss = new Boss(this);
		boss.x = x;
		boss.y = y;
		boss.dir = rand.nextInt(10) % 2;
		if (rand.nextInt(5) < 2)
			boss.setWalk();
		bosses.add(boss);
	}

	public void genThief(float x, float y) {

		Thief thief = new Thief(this);
		thief.x = x;
		thief.y = y;
		thief.dir = rand.nextInt(10) % 2;
		if (rand.nextInt(5) < 2)
			thief.setWalk();
		thieves.add(thief);
	}

	/*
	 * 이 아래로는 범용으로 사용 가능한 커스텀 api
	 */

	// 확대 축소된 BufferedImage 얻기
	public BufferedImage getScaledImage(BufferedImage image, int width, int height) {

		if (width <= 0)
			width = 1;
		if (height <= 0)
			height = 1;

		GraphicsConfiguration gc = mainFrame.getGraphicsConfiguration();
		BufferedImage result = gc.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
		Graphics2D g = result.createGraphics();

		double w = image.getWidth();
		double h = image.getHeight();
		g.scale((double) width / w, (double) height / h);
		g.drawRenderedImage(image, null);
		g.dispose();

		return result;
	}

	// BufferedImage 리소스 읽어들이기 - Image를 BufferedImage로 바꾸는 방식
	public BufferedImage makeBufferedImage(String furl) {

		Image img = null;
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage(furl);
		try {
			// 여기부터
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(img, 0);
			mt.waitForID(0);
			// 여기까지, getImage로 읽어들인 이미지가 로딩이 완료됐는지 확인하는 부분
		} catch (Exception ee) {
			ee.printStackTrace();
			return null;
		}

		BufferedImage bImg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D bGr = bImg.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		return bImg;
	}

	// 이미지 클리핑
	public void drawImageClip(Graphics2D g, BufferedImage img, int x, int y, int sx, int sy, int wd, int ht, int anc) {
		// sx,sy부터 wd,ht만큼 클리핑해서 그린다.
		if (x < 0) {
			wd += x;
			sx -= x;
			x = 0;
		}
		if (y < 0) {
			ht += y;
			sy -= y;
			y = 0;
		}
		if (wd < 0 || ht < 0)
			return;
		x = x - (anc % 3) * (wd / 2);
		y = y - (anc / 3) * (ht / 2);
		g.setClip(x, y, wd, ht);
		drawImageF(g, img, x - sx, y - sy, this);
		g.setClip(0, 0, mainFrame.gGameWidth, mainFrame.gGameHeight);
	}

	// float를 좌표로 받는 그리기
	void drawImageF(Graphics2D gc, BufferedImage image, float x, float y, ImageObserver obs) {

		gc.drawImage(image, (int) x, (int) y, obs);
	}

	// 이미지 플립
	public BufferedImage getFlippedImage(BufferedImage img, boolean flipX, boolean flipY) {

		AffineTransform at = new AffineTransform();
		at.concatenate(AffineTransform.getScaleInstance(flipX ? -1 : 1, flipY ? -1 : 1));
		at.concatenate(AffineTransform.getTranslateInstance(flipX ? -img.getWidth() : 0, flipY ? -img.getHeight() : 0));

		BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.transform(at);
		g.drawImage(img, 0, 0, null);
		g.dispose();

		return newImage;
	}

	// 중심점으로부터의 거리
	public int GetDistance(int x1, int y1, int x2, int y2) {
		return Math.abs((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
}
