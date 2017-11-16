package classhu;

import java.util.Scanner;

public class LCS {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str1 = in.nextLine();
		String str2 = in.nextLine();
		in.close();
		char[] X = str1.toCharArray();
		char[] Y = str2.toCharArray();
		LCS_LENGTH(X, Y);

	}

	public static void LCS_LENGTH(char[] x, char[] y) {
		int m = x.length;
		int n = y.length;
		int[][] c = new int[m][n];
		int[][] b = new int[m][n];
		for (int i = 1; i < m; i++) {
			c[i][0] = 0;
		}
		for (int j = 0; j < n; j++) {
			c[0][j] = 0;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (x[i] == y[j]) {
					c[i][j] = c[i - 1][j - 1] + 1;
					b[i][j] = 1; // upleft
				} else if (c[i - 1][j] >= c[i][j - 1]) {
					c[i][j] = c[i - 1][j];
					b[i][j] = 2;// up
				} else {
					c[i][j] = c[i][j - 1];
					b[i][j] = 3;// left
				}
			}
		}
		PRINT_LCS(b, x, m - 1, n - 1);
	}

	public static void PRINT_LCS(int[][] b, char[] X, int i, int j) {
		if (i == 0 || j == 0)
			return;
		if (b[i][j] == 1) {
			PRINT_LCS(b, X, i - 1, j - 1);
			System.out.print(X[i]);
		} else if (b[i][j] == 2) {
			PRINT_LCS(b, X, i - 1, j);
		} else {
			PRINT_LCS(b, X, i, j - 1);
		}

	}
}
