package classhu;

import java.util.Date;

public class N_Queen {
	private static final short K = 15; // 使用常量来定义，方便之后解N皇后问题
	private static int count = 0; // 结果计数器
	private static short N = 0;

	public static void main(String[] args) {
		for (N = 9; N <= K; N++) {
			Date begin = new Date();
			/**
			 * 初始化棋盘，使用一维数组存放棋盘信息 chess[n]=X:表示第n行X列有一个皇后
			 */
			short chess[] = new short[N];
			for (int i = 0; i < N; i++) {
				chess[i] = 0;
			}

			putQueenAtRow(chess, (short) 0);
			Date end = new Date();
			System.out.println(N + " " + String.valueOf(end.getTime() - begin.getTime()) + "ms，Count：" + count);
		}
	}

	private static void putQueenAtRow(short[] chess, short row) {
		/**
		 * 递归终止判断：如果row==N，则说明已经成功摆放了8个皇后 输出结果，终止递归
		 */
		if (row == N) {
			count++;
			// System.out.println("第 "+ count +" 种解：");
			// for(int i=0;i<N;i++){
			// for(int j=0;j<N;j++){
			// System.out.print(chess[i][j]+" ");
			// }
			// System.out.println();
			// }
			return;
		}

		short[] chessTemp = chess.clone();

		/**
		 * 向这一行的每一个位置尝试排放皇后 然后检测状态，如果安全则继续执行递归函数摆放下一行皇后
		 */
		for (short i = 0; i < N; i++) {
			// 摆放这一行的皇后
			chessTemp[row] = i;

			if (isSafety(chessTemp, row, i)) {
				putQueenAtRow(chessTemp, (short) (row + 1));
			}
		}
	}

	private static boolean isSafety(short[] chess, short row, short col) {
		// 判断中上、左上、右上是否安全
		short step = 1;
		for (short i = (short) (row - 1); i >= 0; i--) {
			if (chess[i] == col) // 中上
				return false;
			if (chess[i] == col - step) // 左上
				return false;
			if (chess[i] == col + step) // 右上
				return false;

			step++;
		}

		return true;
	}
}
