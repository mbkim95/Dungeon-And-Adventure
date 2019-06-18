package Model;

import java.awt.*;
import Controller.Controller;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class RandomBox extends JFrame{
	final public int gGameWidth = 600;
	final public int gGameHeight = 300;
	JPanel panel = new JPanel();
	JButton o1 = new JButton("1");
	JButton o2 = new JButton("2");
	JButton o3 = new JButton("3");
	JButton o4 = new JButton("4");
	JButton o5 = new JButton("5");
	JButton o6 = new JButton("6");
	JButton o7 = new JButton("7");
	JButton o8 = new JButton("8");
	JButton o9 = new JButton("9");
	JButton o10 = new JButton("10");
	JLabel label = new JLabel();
	
	public RandomBox(){
		super("RANDOMBOX");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int f_xpos = (int) (screen.getWidth() / 2 - gGameWidth / 2);// 창 위치를 모니터
		int f_ypos = (int) (screen.getHeight() / 2 - gGameHeight / 2);
		Container cp =getContentPane();
		
		cp.setBackground(Color.white);
		setLayout(null);
		setBounds(f_xpos, f_ypos, gGameWidth, gGameHeight);
		panel.setBackground(Color.BLACK);
		this.setBackground(Color.BLACK);
		
		panel.add(o1);
		o1.setBounds(50, 50, 80, 60);
		this.add(o1);
		o1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.key=-1;
			}
		});
		
		panel.add(o2);
		o2.setBounds(150, 50, 80, 60);
		this.add(o2);
		o2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.key=-1;
			}
		});
		
		panel.add(o3);
		o3.setBounds(250, 50, 80, 60);
		this.add(o3);
		o3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "RIGHT");
				Controller.key=1;
				setVisible(false);
			}
		});
		
		panel.add(o4);
		o4.setBounds(350, 50, 80, 60);
		this.add(o4);
		o4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.key=-1;
			}
		});
		
		panel.add(o5);
		o5.setBounds(450, 50, 80, 60);
		this.add(o5);
		o5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.key=-1;
			}
		});
		
		panel.add(o6);
		o6.setBounds(50, 180, 80, 60);
		this.add(o6);
		o6.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.key=-1;
			}
		});
		
		panel.add(o7);
		o7.setBounds(150, 180, 80, 60);
		this.add(o7);
		o7.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.key=-1;
			}
		});
		panel.add(o8);
		o8.setBounds(250, 180, 80, 60);
		this.add(o8);
		o8.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.key=-1;
			}
		});
		
		panel.add(o9);
		o9.setBounds(350, 180, 80, 60);
		this.add(o9);
		o9.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.key=-1;
			}
		});
		
		panel.add(o10);
		o10.setBounds(450, 180, 80, 60);
		this.add(o10);
		o10.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.key=-1;
			}
		});
		
		panel.add(label);
		label.setBounds(80, 50, 700, 200);
		this.add(label);
		label.setText("백신을 찾아주세요. 정답이 틀릴경우 체력이 감소합니다.");
		
		setResizable(false);
		setVisible(true);
	}
}
