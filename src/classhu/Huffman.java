package classhu;

import java.util.Scanner;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Huffman {
	private static int R = 256;

	private static class Node implements Comparable<Node> {
		// 哈夫曼树中的节点
		private char ch;
		private int freq;
		private final Node left, right;

		Node(char ch, int freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		public int compareTo(Node that) {
			return this.freq - that.freq;
		}
	}

	// private static String[] buildCode(Node root) {
	// // 使用单词查找树构造编译表
	// String[] st = new String[R];
	// buildCode(st, root, "");
	// return st;
	// }

	private static void buildCode(String[] st, Node x, String s) {
		// 使用单词查找树构造编译表
		if (x.isLeaf()) {
			st[x.ch] = s;
			return;
		}
		buildCode(st, x.left, s + '0');
		buildCode(st, x.right, s + '1');
	}

	private static Node buildHuffman(int[] freq) {
		//
		MinPQ<Node> pq = new MinPQ<Node>();
		for (char c = 0; c < R; c++)
			if (freq[c] > 0)
				pq.insert(new Node(c, freq[c], null, null));
		while (pq.size() > 1) {
			// 合并两颗频率最小的树
			Node x = pq.delMin();
			Node y = pq.delMin();
			Node parent = new Node('\0', x.freq + y.freq, x, y);
			pq.insert(parent);
		}
		return pq.delMin();
	}

	private static void writeTrie(Node x) {
		// 输出树的比特字符串
		if (x.isLeaf()) {
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch);
			return;
		}
		BinaryStdOut.write(false);
		writeTrie(x.left);
		writeTrie(x.right);
	}

	private static Node readTrie() {
		if (BinaryStdIn.readBoolean())
			return new Node(BinaryStdIn.readChar(), 0, null, null);
		return new Node('\0', 0, readTrie(), readTrie());
	}

	public static void expand() {
		// 展开被编码的比特流
		Node root = readTrie();
		int N = BinaryStdIn.readInt();
		for (int i = 0; i < N; i++) {
			// 展开第i个编码所对应的字母
			Node x = root;
			while (!x.isLeaf())
				if (BinaryStdIn.readBoolean())
					x = x.right;
				else
					x = x.left;
			BinaryStdOut.write(x.ch);
		}
		BinaryStdOut.close();
	}

	public static void CHuffman(String s) {
		// // 读取输入
		// String s = BinaryStdIn.readString();
		char[] input = s.toCharArray();
		// 统计频率
		int[] freq = new int[R];
		for (int i = 0; i < input.length; i++)
			freq[input[i]]++;
		// 建立哈夫曼树
		Node root = buildHuffman(freq);
		// 构造编译表
		String[] st = new String[R];
		buildCode(st, root, "");

		writeTrie(root);

		BinaryStdOut.write(input.length);

		// Huffman处理输入
		for (int i = 0; i < input.length; i++) {
			String code = st[input[i]];
			for (int j = 0; j < code.length(); j++)
				if (code.charAt(j) == '1')
					BinaryStdOut.write(true);
				else
					BinaryStdOut.write(false);

		}
		BinaryStdOut.close();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = null;
		str = scanner.next();
		System.out.println(str);
		CHuffman(str);
		scanner.close();
	}
}
