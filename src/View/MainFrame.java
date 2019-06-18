package View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import Controller.Controller;
import Controller.SoundController;
//import Controller.*;
//import Model.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements KeyListener{

	
	final public int gGameWidth = 1024;
	final public int gGameHeight = 768;
	public static boolean song = true;
	
	Controller controller;
	static SoundController sound = new SoundController();
	
	
	public MainFrame()	{
	
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int f_xpos = (int) (screen.getWidth() / 2 - gGameWidth / 2 );//창 위치를 모니터 화면의 중앙에 위치시키기 위한 값
		int f_ypos = (int) (screen.getHeight() / 2 - gGameHeight / 2);
		
		//창 선언
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//창 닫기 가능
		setUndecorated(false);//프레임 있음
		
		setTitle("Dungeon & Adventure");
		//set
		setBackground(new Color(0xffffff));//윈도우 기본 배경색 지정 (R=ff, G=ff, B=ff : 하얀색)
		//setTitle("Asteroid Belt");//윈도우 이름 지정
		setLayout(null);//윈도우의 레이아웃을 프리로 설정
		setBounds(f_xpos,f_ypos,gGameWidth,gGameHeight);//윈도우의 시작 위치와 너비 높이 지정
		setResizable(false);//윈도우의 크기를 변경할 수 없음
		setVisible(true);//윈도우 표시
		this.setFocusable(true);
		addKeyListener(this);//키 리스너 등록
		
		
		
		controller=new Controller(this);//화면 묘화를 위한 캔버스 객체		
		controller.setBounds(0,0,gGameWidth,gGameHeight);
		add(controller);//Canvas 객체를 프레임에 올린다
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Menu();

		sound.Sound("rsc/sound/main.wav", true, 10);
		
	/*	new Ox(1);
		new Ox(2);
		new Ox(3);
		new Quiz();
		new RandomBox();
		new DIFF();*/
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		controller.keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		controller.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		controller.keyReleased(e);
	}

}
