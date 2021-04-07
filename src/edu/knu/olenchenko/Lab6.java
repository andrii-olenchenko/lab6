package edu.knu.olenchenko;

import java.awt.*;
import java.awt.event.*;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Lab6 {

	static class LabFrame extends Frame {

		private static final long serialVersionUID = 1L;

		private Polygon p = null;
		private Color polygonColor = Color.WHITE;

		public LabFrame() {
			p = new Polygon();
			for (int i = 0; i < 6; i++) {
				int x = (int) (100 + 50 * Math.cos(i * 2 * Math.PI / 6));
				int y = (int) (100 + 50 * Math.sin(i * 2 * Math.PI / 6));
				p.addPoint(x, y);
			}
		}

		public void setPolygonColor(Color color) {
			this.polygonColor = color;
		}

		@Override
		public void paint(Graphics g) {
			g.setColor(Color.BLACK);
			g.drawPolygon(p);
			g.setColor(polygonColor);
			g.fillPolygon(p);
		}
	}

	public static void main(String[] args) {
		LabFrame f = new LabFrame();
		f.setTitle("Graphics");

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				f.dispose();
			}
		});

		Button b1 = new Button("RED");
		b1.setBounds(170, 100, 100, 30);
		b1.setBackground(Color.RED);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent s) {
				f.setPolygonColor(Color.RED);
				f.repaint();
			}
		});

		Button b2 = new Button("YELLOW");
		b2.setBounds(200, 200, 100, 30);
		b2.setBackground(Color.YELLOW);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent s) {
				f.setPolygonColor(Color.YELLOW);
				f.repaint();
			}
		});

		Button b3 = new Button("Reset");
		b3.setBounds(100, 200, 100, 30);
		b3.setBackground(Color.WHITE);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setPolygonColor(Color.WHITE);
				f.repaint();
			}
		});

		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.setSize(300, 300);
		f.setLayout(null);
		f.setVisible(true);
	}
}
