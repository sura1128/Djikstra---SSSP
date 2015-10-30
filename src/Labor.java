
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

	PriorityQueue<IntegerTriple> pq;
	private final int INFINITY = 1000000000;

	private int distMat[][];

	List<IntegerTriple> paths;

	public Labor() {

	}

	void PreProcess() {

	}

	int Query(int s, int t, int k) {
		int ans = -1;


		pq = new PriorityQueue<IntegerTriple>();
		distMat = new int[V][V + 1];

		ans = shortestPath(s, t, k);

		return ans;
	}

	int shortestPath(int source, int destination, int k) {

		int hops = 1;
		int path = -1;

		for (int i = 0; i < V; i++) {
			Arrays.fill(distMat[i], INFINITY);
		}

		distMat[source][1] = 0;
		pq.add(new IntegerTriple(0, 1, source));

		while (!pq.isEmpty()) {

			IntegerTriple u = pq.remove();

			Vector<IntegerPair> neighbours = AdjList.get(u.third());

			// u==t condition
			if (u.third() == destination) {
				path = u.first();
				break;
			} 

			// h[u] < k condition
			if (u.second() < k) {

				for (int i = 0; i < neighbours.size(); i++) {
					IntegerPair v = neighbours.get(i);
					hops = u.second() + 1;


					if (distMat[v.first()][hops] > u.first() + v.second()) {
						distMat[v.first()][hops] = u.first() + v.second();
						pq.add(new IntegerTriple(distMat[v.first()][hops], hops, v.first()));
					}
				}

			}

		}
		return path;

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

class IntegerTriple implements Comparable<IntegerTriple> {
	Integer _first, _second, _third;

	public IntegerTriple(Integer f, Integer s, Integer t) {
		_first = f;
		_second = s;
		_third = t;
	}

	public int compareTo(IntegerTriple o) {
		if (!this.first().equals(o.first()))
			return this.first() - o.first();
		else if (!this.second().equals(o.second()))
			return this.second() - o.second();
		else
			return this.third() - o.third();
	}

	Integer first() {
		return _first;
	}

	Integer second() {
		return _second;
	}

	Integer third() {
		return _third;
	}
}