package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Intro extends JFrame {
	JScrollPane scroll = new JScrollPane();
	JButton back = new JButton("BACK");
	JLabel label = new JLabel("INTRO");
	ImageIcon icon;
	final public int gGameWidth = 780;
	final public int gGameHeight = 480;

	Intro(){
		super("Intro");
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int f_xpos = (int) (screen.getWidth() / 2 - gGameWidth / 2);// 창 위치를 모니터
		int f_ypos = (int) (screen.getHeight() / 2 - gGameHeight / 2);
		icon=new ImageIcon("rsc/intro.png");
		
		JPanel panel=new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		panel.setLayout(null);
		
		panel.add(this.back);
		this.setButton(back);
		back.setBorderPainted(true);
		back.setBounds(600, 380, 100, 30);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Menu();
			}
		});
		
		
		scroll= new JScrollPane(panel);
		setContentPane(scroll);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(f_xpos, f_ypos, gGameWidth, gGameHeight);
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
