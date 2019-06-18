package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Menu extends JFrame {
	JButton bt1, bt2, bt3;
	JScrollPane scrollPane;
	ImageIcon icon, icon1, icon2;
	final public int gGameWidth = 1024;
	final public int gGameHeight = 768;

	public Menu() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int f_xpos = (int) (screen.getWidth() / 2 - gGameWidth / 2);// 창 위치를 모니터
		int f_ypos = (int) (screen.getHeight() / 2 - gGameHeight / 2);
		icon = new ImageIcon("rsc/title.png");
		icon1= new ImageIcon("rsc/gamestart.png");
		icon2 = new ImageIcon("rsc/gameintro.png");
		// 배경 Panel 생성후 컨텐츠페인으로 지정
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				// Approach 1: Dispaly image at at full size
				g.drawImage(icon.getImage(), 0, 0, null);
				// Approach 2: Scale image to size of component
				// Dimension d = getSize();
				// g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				// Approach 3: Fix the image position in the scroll pane
				// Point p = scrollPane.getViewport().getViewPosition();
				// g.drawImage(icon.getImage(), p.x, p.y, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
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
		bt1.setBounds(400, 450, 200, 100);
		this.setButton(bt1);
	// 	bt1.setBorderPainted(true);		
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CharacterSelect();
				setVisible(false);
			}
		});
		background.add(this.bt1);
		
		bt2 = new JButton(""){
			public void paintComponent(Graphics g){
				g.drawImage(icon2.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		bt2.setBounds(400, 550, 200, 100);
		this.setButton(bt2);
	//	bt2.setBorderPainted(true);		
		bt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Intro();
				setVisible(false);
			}
		});
		background.add(this.bt2);

		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
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
