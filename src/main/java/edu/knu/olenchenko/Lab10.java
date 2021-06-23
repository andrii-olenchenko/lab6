package edu.knu.olenchenko;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

/*
 * ЗАДАНИЕ: Створити програму для малювання точок на екрані. Координати точок програма зчитує із INPUT.txt  файлу. 
 * Малює ці точки на екрані.
 */
public class Lab10 extends JFrame {

	private List<Integer> xx = new ArrayList<Integer>();
	private List<Integer> yy = new ArrayList<Integer>();

	public Lab10() {
		super("Lab10");
		setSize(400, 325);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		this.readFile();
		repaint();
	}

	private void readFile() {
		try (FileInputStream fis = new FileInputStream("src/main/java/INPUT.txt"); Scanner scanner = new Scanner(fis)) {
			while (scanner.hasNextInt()) {
				int x = scanner.nextInt();
				int y = scanner.hasNextInt() ? scanner.nextInt() : 0;
				if (y != 0) {
					xx.add(x);
					yy.add(y);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		for (int i = 0; i < xx.size(); i++) {
			g2.fillRect(xx.get(i), yy.get(i), 3, 3);
		}
	}

	public static void main(String[] args) {
		Lab10 app = new Lab10();
	}
}
