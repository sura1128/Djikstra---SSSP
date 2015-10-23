
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

	private int shortPath[][];
	PriorityQueue<IntegerPair> pq;
	private final int INFINITY = 1000000000;

	public Labor() {

	}

	void PreProcess() {
		shortPath = new int[V][V];

		for (int i = 0; (i < V) && (i < 10); i++) {
			pq = new PriorityQueue<IntegerPair>();
			shortestPath(i);
		}

	}

	int Query(int s, int t, int k) {
		int ans = -1;

		if (s == t) {
			ans = 0;
		} else {
			if (shortPath[s][t] == 0) {
				ans = -1;
			} else {
				ans = shortPath[s][t];
			}

		}
		return ans;
	}

	void shortestPath(int source) {

		int distance[] = new int[V];
		boolean visited[] = new boolean[V];

		// Assign tentative distance 0 to source, and 999 to others.

		for (int i = 0; i < V; i++) {
			distance[i] = INFINITY;
		}

		distance[source] = 0;
		pq.add(new IntegerPair(source, 0));

		while (!pq.isEmpty()) {

			IntegerPair u = pq.remove();
			visited[u.first()] = true;

			if (distance[u.first()] == u.second()) {
				Vector<IntegerPair> neighbours = AdjList.get(u.first());

				for (int i = 0; i < neighbours.size(); i++) {
					IntegerPair v = neighbours.get(i);

					if (visited[v.first()] == false) {

						if (distance[v.first()] > distance[u.first()] + v.second()) {
							distance[v.first()] = distance[u.first()] + v.second();

							shortPath[source][v.first()] = distance[v.first()];
							pq.add(new IntegerPair(v.first(), distance[v.first()]));
						}

					}

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
		if (!this.second().equals(o.second()))
			return this.second() - o.second();
		else
			return this.first() - o.first();
	}

	Integer first() {
		return _first;
	}

	Integer second() {
		return _second;
	}
}