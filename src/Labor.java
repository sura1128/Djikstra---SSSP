
// Copy paste this Java Template and save it as "Labor.java"
import java.util.*;
import java.io.*;

// write your matric number here: A0102800A
// write your name here: Suranjana Sengupta
// write list of collaborators here: Akshat Dubey
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class Labor {
	private int V; // number of vertices in the graph (number of junctions in
					// Singapore map)
	private int Q; // number of queries
	private Vector<Vector<IntegerPair>> AdjList; // the weighted graph (the
													// Singapore map), the
													// length of each edge
													// (road) is stored here
													// too, as the weight of
													// edge

	private boolean visited[];
	private int shortPath[][];

	public Labor() {

	}

	void PreProcess() {
		shortPath = new int[V][V];

		for (int i = 0; i < V; i++) {
			visited = new boolean[V];
			DFS(i, i, 0);
		}

	}
	

	int Query(int s, int t, int k) {
		int ans = -1;

		ans = shortPath[s][t];

		return ans;
	}

	void DFS(int source, int u, int weight) {

		visited[u] = true;
		
		Vector<IntegerPair> neighbours = AdjList.get(u);

		for (int i = 0; i < neighbours.size(); i++) {
			int x = neighbours.get(i).first();
			int w = neighbours.get(i).second();

			if (visited[x] == false) {
				if (source == x) {
					shortPath[source][x] = 0;
					shortPath[x][source] = 0;
				} else {
						shortPath[source][x] = weight + w;
						shortPath[x][source] = shortPath[source][x];
					DFS(source, x, shortPath[source][x]);
				}

				
			}

		}

	}

	void run() throws Exception {
		// you can alter this method if you need to do so
		IntegerScanner sc = new IntegerScanner(System.in);
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int TC = sc.nextInt(); // there will be several test cases
		while (TC-- > 0) {
			V = sc.nextInt();

			// clear the graph and read in a new graph as Adjacency List
			AdjList = new Vector<Vector<IntegerPair>>();
			for (int i = 0; i < V; i++) {
				AdjList.add(new Vector<IntegerPair>());

				int k = sc.nextInt();
				while (k-- > 0) {
					int j = sc.nextInt(), w = sc.nextInt();
					AdjList.get(i).add(new IntegerPair(j, w)); // edge (road)
																// weight (in
																// minutes) is
																// stored here
				}
			}

			PreProcess(); // optional

			Q = sc.nextInt();
			while (Q-- > 0)
				pr.println(Query(sc.nextInt(), sc.nextInt(), sc.nextInt()));

			if (TC > 0)
				pr.println();
		}

		pr.close();
	}

	public static void main(String[] args) throws Exception {
		// do not alter this method
		Labor ps5 = new Labor();
		ps5.run();
	}
}

class IntegerScanner { // coded by Ian Leow, using any other I/O method is not
						// recommended
	BufferedInputStream bis;

	IntegerScanner(InputStream is) {
		bis = new BufferedInputStream(is, 1000000);
	}

	public int nextInt() {
		int result = 0;
		try {
			int cur = bis.read();
			if (cur == -1)
				return -1;

			while ((cur < 48 || cur > 57) && cur != 45) {
				cur = bis.read();
			}

			boolean negate = false;
			if (cur == 45) {
				negate = true;
				cur = bis.read();
			}

			while (cur >= 48 && cur <= 57) {
				result = result * 10 + (cur - 48);
				cur = bis.read();
			}

			if (negate) {
				return -result;
			}
			return result;
		} catch (IOException ioe) {
			return -1;
		}
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	Integer _first, _second;

	public IntegerPair(Integer f, Integer s) {
		_first = f;
		_second = s;
	}

	public int compareTo(IntegerPair o) {
		if (!this.first().equals(o.first()))
			return this.first() - o.first();
		else
			return this.second() - o.second();
	}

	Integer first() {
		return _first;
	}

	Integer second() {
		return _second;
	}
}