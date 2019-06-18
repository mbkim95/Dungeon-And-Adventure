package Model;

import javax.swing.*;

import Controller.Controller;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Ox extends JFrame {
	final public int gGameWidth = 400;
	final public int gGameHeight = 200;
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	JButton o = new JButton();
	JButton x = new JButton();
	Random r = new Random();

	public Ox(int i) {
		super("Ox");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int f_xpos = (int) (screen.getWidth() / 2 - gGameWidth / 2);// 창 위치를 모니터
		int f_ypos = (int) (screen.getHeight() / 2 - gGameHeight / 2);
		
		Container cp = this.getContentPane();
		cp.setBackground(Color.white);
		setLayout(null);
		setBounds(f_xpos, f_ypos, gGameWidth, gGameHeight);
		setResizable(false);
		
		if (i == 1) {
			makeOx1();
		}
		if (i == 2) {
			makeOx2();
		}
		if (i == 3) {
			makeOx3();
		}
		setVisible(true);

	}

	public void makeOx1() {
		panel.add(o);
		o.setBackground(Color.white);
		this.setButton(o);
		o.setBorderPainted(true);
		o.setBounds(70, 100, 100, 50);
		this.add(o);
		o.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "RIGHT");
				Controller.key=1;
				setVisible(false);
			}
		});
		o.setText("O");

		panel.add(x);
		this.setButton(x);

		x.setBorderPainted(true);
		x.setBounds(230, 100, 100, 50);
		this.add(x);
		x.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.key=-1;
				setVisible(false);
			}
		});
		x.setText("X");

		panel.add(label);
		label.setBounds(40, 40, 800, 40);
		this.add(label);
		label.setText("단 것을 많이 먹으면 눈이 나빠진다.");

	}

	public void makeOx2() {
		panel.add(o);
		this.setButton(o);
		o.setBorderPainted(true);
		o.setBounds(70, 100, 100, 50);
		this.add(o);
		o.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.key=-1;
				setVisible(false);
			}
		});
		o.setText("O");

		panel.add(x);
		this.setButton(x);
		;
		x.setBorderPainted(true);
		x.setBounds(230, 100, 100, 50);
		this.add(x);
		x.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "RIGHT");
				Controller.key=1;
				setVisible(false);
			}
		});
		x.setText("X");

		panel.add(label);
		label.setBounds(40, 40, 800, 40);
		this.add(label);
		label.setText("계란은 순수한 우리말이다.");

	}

	public void makeOx3() {
		panel.add(o);
		this.setButton(o);
		o.setBorderPainted(true);
		o.setBounds(70, 100, 100, 50);
		this.add(o);
		o.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "RIGHT");
				Controller.key=1;
				setVisible(false);
			}
		});
		o.setText("O");

		panel.add(x);
		this.setButton(x);
		;
		x.setBorderPainted(true);
		x.setBounds(230, 100, 100, 50);
		this.add(x);
		x.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "WRONG");
				Controller.key=-1;
				setVisible(false);
			}
		});
		x.setText("X");

		panel.add(label);
		label.setBounds(40, 40, 800, 40);
		this.add(label);
		label.setText("최윤정 교수님은 미인이시다.");

	}

	public void setButton(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
	}
}
