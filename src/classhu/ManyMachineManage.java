package classhu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class ManyMachineManage {
	public static class JOB implements Comparable {
		int id;
		int TIME;

		public JOB(int id, int TIME) {
			this.id = id;
			this.TIME = TIME;
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			int times = ((JOB) o).TIME;
			if (TIME > times)
				return -1;
			if (TIME == times)
				return 0;
			return 1;
		}
	}

	public static class MACHINE implements Comparable {
		int id;
		int avail;

		public MACHINE(int id, int avail) {
			this.id = id;
			this.avail = avail;
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			int xs = ((MACHINE) o).avail;
			if (avail < xs)
				return -1;
			if (avail == xs)
				return 0;
			return 1;
		}
	}

	public static int greedy(int[] a, int m) {
		int n = a.length - 1;
		int sum = 0;
		if (n <= m) {
			for (int i = 0; i < n; i++)
				sum += a[i + 1];
			System.out.println("hand out machine");
			return sum;
		}
		ArrayList<JOB> d = new ArrayList<JOB>();
		for (int i = 0; i < n; i++) {
			JOB job = new JOB(i + 1, a[i + 1]);
			d.add(job);
		}
		Collections.sort(d);
		LinkedList<MACHINE> h = new LinkedList<MACHINE>();
		for (int i = 1; i <= m; i++) {
			MACHINE x = new MACHINE(i, 0);
			h.add(x);
		}
		int test = h.size();
		for (int i = 0; i < n; i++) {
			Collections.sort(h);
			MACHINE x = h.peek();
			System.out.println(
					"将机器" + x.id + "从" + x.avail + "到" + (x.avail + d.get(i).TIME) + "的时间段分配给作业" + d.get(i).id);
			x.avail += d.get(i).TIME;
			sum = x.avail;
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] a = { 0, 2, 14, 4, 16, 6, 5, 3 };
		int m = 3;
		int sum = greedy(a, m);
		System.out.println("Total Time: " + sum);
	}
}
