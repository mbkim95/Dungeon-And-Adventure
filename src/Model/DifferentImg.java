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
	// ���۸� Ȱ���� �̹��� Ŭ����
	BufferedImage img;
	// Ʋ���κ��� ��ǥ�� �迭
	static int[] imageX = { 480, 529, 639, 709, 738 };
	static int[] imageY = { 198, 132, 354, 11, 370 };
	// Ʋ���κ��� ���� �⺻ ������
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
		// �迭 ������ �ش���ǥ�� �Է��� ��ȯ
		return (pointX >= imageX[index] - range) && (pointX <= imageX[index] + range)
				&& (pointY >= imageY[index] - range) && (pointY <= imageY[index] + range);
	}// isRangeof()

	class CustomMouseAdapter extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			// ���콺 Ŭ���� ���� x,y��ǥ���� ���� Point ��ü�� ����
			Point p = e.getPoint();
			System.out.println(p);
			if (isRangeof(0, p)) {
				// jtf.setText(p.toString() + "t" + "��")
				f1 = true;
				repaint();
			} else if (isRangeof(1, p)) {
				// ÷ž
				f2 = true;
				repaint();
			} else if (isRangeof(2, p)) {
				// ��
				f3 = true;
				repaint();
			} else if (isRangeof(3, p)) {
				// ����
				f4 = true;
				repaint();
			} else if (isRangeof(4, p)) {
				// ��������
				f5 = true;
				repaint();
			} else if (p.x >= 0 && p.x <= 395 && p.y >= 0 && p.y <= 486) {
				JOptionPane.showMessageDialog(null, "������ �׸��� Ŭ�����ּ���.");
				repaint();
			} else if (p.x >= 396 && p.x <= 780) {
				JOptionPane.showMessageDialog(null, "�ٽ� ã���ּ���.");
				count++;
				repaint();
				if (count == 5) {
					JOptionPane.showMessageDialog(null, "Game Out\nâ�� �ݾ��ּ���");
					Controller.key=-1;
				}
			} // if end

			// complete
			if (f1 == true && f2 == true && f3 == true && f4 == true && f5 == true) {
				JOptionPane.showMessageDialog(null, "Clear!\n â�� �ݾ��ּ���");
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
		g.drawImage(img, 0, 0, null); // �׸��� �׸��� �޼ҵ�
		super.paint(g);
		// ���׶�� ���� �������� ǥ��
		g.setColor(Color.RED);
		// Ʋ���׸��κп� ���׶�� �׸���
		if (f1 == true) {
			g.drawOval(455, 173, 50, 50);// ��
		}
		if (f2 == true) {
			g.drawOval(506, 100, 50, 50);// ÷ž
		}
		if (f3 == true) {
			g.drawOval(613, 328, 50, 50);// ��
		}
		if (f4 == true) {
			g.drawOval(680, 0, 50, 50);// ����
		}
		if (f5 == true) {
			g.drawOval(713, 345, 50, 50);// ��������
		}
	}

	// ����Ʈ ������
	public  DifferentImg() {
		
		CustomMouseAdapter cma = new CustomMouseAdapter();
		CustomKeyAdapter cka = new CustomKeyAdapter();
		try {
			String filename = "rsc/����.jpg"; // ������ ��� ���
												// ���
			img = ImageIO.read(new File(filename)); // �̹��� ������ �ҷ����� IO �޼ҵ�
			// ���ϰ���� �̹��� ������ �д´�.
		} catch (IOException e) {
			e.printStackTrace();
		}
		addMouseListener(cma);
		addKeyListener(cka);
	}

	public Dimension getPreferredSize() {
		if (img == null) {
			return new Dimension(100, 100); // �׸� ������ ���� ��� �������� ũ�⸦ 100,100����
											// �����
		} else {
			return new Dimension(img.getWidth(null), img.getHeight(null)); // �׸���
		}
	}
}
