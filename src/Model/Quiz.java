package Model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Quiz extends JFrame {
	final public int gGameWidth = 560;
	final public int gGameHeight = 300;
	JPanel panel = new JPanel();
	JButton one = new JButton("우표");
	JButton two = new JButton("짐");
	JButton three = new JButton("밥풀");
	JButton forth = new JButton("말");
	JLabel label = new JLabel("다음 중에서 <부치는>것은?");
	
	public Quiz(){
		
		super("QUIZ");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int f_xpos = (int) (screen.getWidth() / 2 - gGameWidth / 2);// 창 위치를 모니터
		int f_ypos = (int) (screen.getHeight() / 2 - gGameHeight / 2);
		setLayout(null);
		setBounds(f_xpos, f_ypos, gGameWidth, gGameHeight);
		panel.setBackground(Color.BLACK);
		this.setBackground(Color.BLACK);
		Container cp = getContentPane();
		cp.setBackground(Color.white);
		panel.add(one);
		one.setBackground(Color.white);
		one.setBounds(40, 160, 100, 80);
		this.add(one);
		one.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.Controller.key=-1;
			}
		});
		
		panel.add(two);
		two.setBounds(160, 160, 100, 80);
		two.setBackground(Color.white);
		this.add(two);
		two.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "RIGHT");
				Controller.Controller.key=1;
				setVisible(false);
			}
		});
		
		panel.add(three);
		three.setBounds(280, 160, 100, 80);
		three.setBackground(Color.white);
		this.add(three);
		three.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.Controller.key=-1;
			}
		});
		
		panel.add(forth);
		forth.setBounds(400, 160, 100, 80);
		forth.setBackground(Color.white);
		this.add(forth);
		forth.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.Controller.key=-1;
			}
		});
		
		panel.add(label);
		label.setBounds(200, 30, 200, 50);
		this.add(label);
		setResizable(false);
		setVisible(true);
	}
}
