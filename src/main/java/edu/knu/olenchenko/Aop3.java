package edu.knu.olenchenko;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

// Створіть на Java прості класи та продемонструйте їх функції. Кожен клас мусить мати конструктор, атрибути та методи
// Клас «матриця 2х3», який описує матрицю з 3 стовпчиками і 2 рядками. 
// Методи класу дозволяють множити матрицю на число, додавати до іншої матриці, знаходити транспоновану матрицю.
public class Aop3 {

	static class Matrix {
		private int cols;
		private int rows;
		private int[][] arr;


		public Matrix(int cols, int rows) {
			this.cols = cols;
			this.rows = rows;
			this.arr = new int[cols][rows];
			this.fill();
		};

		public int getCols() {
			return cols;
		}

		public int getRows() {
			return rows;
		}

		public int[][] getArr() {
			return arr;
		}

		private void fill() {
			Random randNum = new Random();
			for (int j = 0; j < this.rows; j++) {
				for (int i = 0; i < this.cols; i++) {
					arr[i][j] = randNum.nextInt(10) + 1;
				}
			}
		}

		public void show() {
			for (int j = 0; j < this.rows; j++) {
				for (int i = 0; i < this.cols; i++) {
					System.out.print(arr[i][j] + "  ");
				}
				System.out.println();
			}
		};

		public void multiply(int m) {
			for (int j = 0; j < this.rows; j++) {
				for (int i = 0; i < this.cols; i++) {
					arr[i][j] = arr[i][j] * m;
				}
			}
		};

		public Matrix transpose() {
			Matrix dst = new Matrix(this.rows, this.cols);
			for (int j = 0; j < this.rows; j++) {
				for (int i = 0; i < this.cols; i++) {
					dst.getArr()[j][i] = arr[i][j];
				}
			}
			return dst;
		};

		public void sum(Matrix another) {
			if (another.getCols() != this.cols || another.getRows() != this.rows) {
				throw new IllegalArgumentException("Invalid matrix to sum.");
			}
			for (int j = 0; j < this.rows; j++) {
				for (int i = 0; i < this.cols; i++) {
					arr[i][j] += another.getArr()[i][j];
				}
			}
		};
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("1 - Start operation;");
			System.out.println("2 - Exit");
			System.out.print("\nEnter a number;");
			int j = in.nextInt();
			if (j == 1) {
				System.out.println("\nInit matrix");
				Matrix matrix = new Matrix(3,2);
				matrix.show();

				System.out.println("\nMultiply matrix by 5");
				matrix.multiply(5);
				matrix.show();

				System.out.println("\nTranspose matrix");
				Matrix transposed = matrix.transpose();
				transposed.show();

				System.out.print("\nEnter a number;");
			} else {
				loop = false;
				System.out.print("\nДо побачення!");
			}
		}
	}
}
