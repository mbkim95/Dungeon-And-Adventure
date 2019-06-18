package Model;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class DIFF {
	 public DIFF() {
		final JFrame f = new JFrame("틀린그림찾기");

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		});

		f.getContentPane().add(new DifferentImg());
		f.pack();
		f.setVisible(true);
	}

}
