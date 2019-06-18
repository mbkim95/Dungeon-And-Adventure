package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.Controller;
import Controller.Controller;
@SuppressWarnings("serial")
public class CharacterSelect extends JFrame {
	JPanel panel = new JPanel();
	JButton bt1, bt2;
	JScrollPane scroll;
	ImageIcon icon, icon1, icon2;
	final public int gGameWidth = 638;
	final public int gGameHeight = 360;
	
	public CharacterSelect(){
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int f_xpos = (int) (screen.getWidth() / 2 - gGameWidth / 2);// 창 위치를 모니터
		int f_ypos = (int) (screen.getHeight() / 2 - gGameHeight / 2);
		
		icon = new ImageIcon("rsc/");
		icon1 = new ImageIcon("rsc/select0.png");
		icon2 = new ImageIcon("rsc/select1.png");
		setTitle("Character Select");
		JPanel background = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		background.setLayout(null);
		bt1 = new JButton(""){
			public void paintComponent(Graphics g){
				g.drawImage(icon1.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		bt2 = new JButton(""){
			public void paintComponent(Graphics g){
				g.drawImage(icon2.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		background.add(this.bt1);
		bt1.setSize(319, 324);
		this.setButton(bt1);
		bt1.setBorderPainted(true);
		bt1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Controller.characterchoice=0;
				new MainFrame();
				setVisible(false);
			}
		});
		
		background.add(this.bt2);
		bt2.setBounds(319, 0, 319, 324);
		this.setButton(bt2);
		bt2.setBorderPainted(true);
		bt2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Controller.characterchoice=1;
				new MainFrame();
				setVisible(false);
			}
		});
		
		scroll = new JScrollPane(background);
		setContentPane(scroll);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(f_xpos, f_ypos, 638, 360);
		setResizable(false);
		setVisible(true);
	}
	
	public void setButton(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
	}
}
