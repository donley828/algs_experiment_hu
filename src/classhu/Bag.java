package classhu;

public class Bag {
	public static void main(String[] args) {
		int m = 10;
		int n = 4;
		int w[] = { 5, 4, 6, 3 };
		int p[] = { 10, 40, 30, 50 };
		int c[][] = Bag_Solution(m, n, w, p);
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				System.out.print(c[i][j] + "\t");
				if (j == m) {
					System.out.println();
				}
			}
		}
		// printPack(c, w, m, n);

	}

	/**
	 * @param c
	 *            表示背包的最大容量
	 * @param n
	 *            表示商品个数
	 * @param w
	 *            表示商品重量数组
	 * @param p
	 *            表示商品价值数组
	 */
	public static int[][] Bag_Solution(int c, int n, int[] w, int[] p) {
		// c[i][v]表示前i件物品恰放入一个重量为m的背包可以获得的最大价值
		int temp[][] = new int[n + 1][c + 1];
		for (int i = 0; i < n + 1; i++)
			temp[i][0] = 0;
		for (int j = 0; j < c + 1; j++)
			temp[0][j] = 0;

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < c + 1; j++) {
				// 当物品为i件重量为j时，如果第i件的重量(w[i-1])小于重量j时，c[i][j]为下列两种情况之一：
				// (1)物品i不放入背包中，所以c[i][j]为c[i-1][j]的值
				// (2)物品i放入背包中，则背包剩余重量为j-w[i-1],所以c[i][j]为c[i-1][j-w[i-1]]的值加上当前物品i的价值
				if (w[i - 1] <= j) {
					if (temp[i - 1][j] < (temp[i - 1][j - w[i - 1]] + p[i - 1]))
						temp[i][j] = temp[i - 1][j - w[i - 1]] + p[i - 1];
					else
						temp[i][j] = temp[i - 1][j];
				} else
					temp[i][j] = temp[i - 1][j];
			}
		}
		return temp;
	}
}
