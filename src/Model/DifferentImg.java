package Model;

import java.awt.*;
import Controller.Controller;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class DifferentImg extends Container {
	// 버퍼를 활용한 이미지 클래스
	BufferedImage img;
	// 틀린부분의 좌표값 배열
	static int[] imageX = { 480, 529, 639, 709, 738 };
	static int[] imageY = { 198, 132, 354, 11, 370 };
	// 틀린부분의 범위 기본 설정값
	static final int range = 15;
	boolean f1 = false;
	boolean f2 = false;
	boolean f3 = false;
	boolean f4 = false;
	boolean f5 = false;
	boolean life = true;
	int count = 0;

	public boolean isRangeof(int index, Point p) {
		// point x = pointX
		int pointX = p.x;
		// point y = pointY
		int pointY = p.y;
		// 배열 순번과 해당좌표를 입력해 반환
		return (pointX >= imageX[index] - range) && (pointX <= imageX[index] + range)
				&& (pointY >= imageY[index] - range) && (pointY <= imageY[index] + range);
	}// isRangeof()

	class CustomMouseAdapter extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			// 마우스 클릭한 곳의 x,y좌표값을 얻어와 Point 객체를 생성
			Point p = e.getPoint();
			System.out.println(p);
			if (isRangeof(0, p)) {
				// jtf.setText(p.toString() + "t" + "문")
				f1 = true;
				repaint();
			} else if (isRangeof(1, p)) {
				// 첨탑
				f2 = true;
				repaint();
			} else if (isRangeof(2, p)) {
				// 원
				f3 = true;
				repaint();
			} else if (isRangeof(3, p)) {
				// 구름
				f4 = true;
				repaint();
			} else if (isRangeof(4, p)) {
				// 꼬마조형
				f5 = true;
				repaint();
			} else if (p.x >= 0 && p.x <= 395 && p.y >= 0 && p.y <= 486) {
				JOptionPane.showMessageDialog(null, "오른쪽 그림을 클릭해주세요.");
				repaint();
			} else if (p.x >= 396 && p.x <= 780) {
				JOptionPane.showMessageDialog(null, "다시 찾아주세요.");
				count++;
				repaint();
				if (count == 5) {
					JOptionPane.showMessageDialog(null, "Game Out\n창을 닫아주세요");
					Controller.key=-1;
				}
			} // if end

			// complete
			if (f1 == true && f2 == true && f3 == true && f4 == true && f5 == true) {
				JOptionPane.showMessageDialog(null, "Clear!\n 창을 닫아주세요");
				Controller.key=1;
			}
		}
	}

	class CustomKeyAdapter extends KeyAdapter {
		public void keyrealeased(KeyEvent e) {
			int keycode = e.getKeyChar();
			System.out.println(keycode);
			if (keycode == 'o') {

			}
		}
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null); // 그림을 그리는 메소드
		super.paint(g);
		// 동그라미 색깔 빨강으로 표시
		g.setColor(Color.RED);
		// 틀린그림부분에 동그라미 그리기
		if (f1 == true) {
			g.drawOval(455, 173, 50, 50);// 문
		}
		if (f2 == true) {
			g.drawOval(506, 100, 50, 50);// 첨탑
		}
		if (f3 == true) {
			g.drawOval(613, 328, 50, 50);// 원
		}
		if (f4 == true) {
			g.drawOval(680, 0, 50, 50);// 구름
		}
		if (f5 == true) {
			g.drawOval(713, 345, 50, 50);// 꼬마조형
		}
	}

	// 디폴트 생성자
	public  DifferentImg() {
		
		CustomMouseAdapter cma = new CustomMouseAdapter();
		CustomKeyAdapter cka = new CustomKeyAdapter();
		try {
			String filename = "rsc/정원.jpg"; // 파일의 상대 경로
												// 경로
			img = ImageIO.read(new File(filename)); // 이미지 파일을 불러오는 IO 메소드
			// 파일경로의 이미지 파일을 읽는다.
		} catch (IOException e) {
			e.printStackTrace();
		}
		addMouseListener(cma);
		addKeyListener(cka);
	}

	public Dimension getPreferredSize() {
		if (img == null) {
			return new Dimension(100, 100); // 그림 파일이 없을 경우 프레임의 크기를 100,100으로
											// 만든다
		} else {
			return new Dimension(img.getWidth(null), img.getHeight(null)); // 그림의
		}
	}
}
