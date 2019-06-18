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
		int f_xpos = (int) (screen.getWidth() / 2 - gGameWidth / 2 );//â ��ġ�� ����� ȭ���� �߾ӿ� ��ġ��Ű�� ���� ��
		int f_ypos = (int) (screen.getHeight() / 2 - gGameHeight / 2);
		
		//â ����
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//â �ݱ� ����
		setUndecorated(false);//������ ����
		
		setTitle("Dungeon & Adventure");
		//set
		setBackground(new Color(0xffffff));//������ �⺻ ���� ���� (R=ff, G=ff, B=ff : �Ͼ��)
		//setTitle("Asteroid Belt");//������ �̸� ����
		setLayout(null);//�������� ���̾ƿ��� ������ ����
		setBounds(f_xpos,f_ypos,gGameWidth,gGameHeight);//�������� ���� ��ġ�� �ʺ� ���� ����
		setResizable(false);//�������� ũ�⸦ ������ �� ����
		setVisible(true);//������ ǥ��
		this.setFocusable(true);
		addKeyListener(this);//Ű ������ ���
		
		
		
		controller=new Controller(this);//ȭ�� ��ȭ�� ���� ĵ���� ��ü		
		controller.setBounds(0,0,gGameWidth,gGameHeight);
		add(controller);//Canvas ��ü�� �����ӿ� �ø���
		
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
