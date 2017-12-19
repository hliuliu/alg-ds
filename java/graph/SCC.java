

import java.util.*;
import java.io.*;

public class SCC {

	public static boolean[] visited = null;
	public static Stack<Integer> stack  = new Stack<Integer>();

	public static LinkedList<HashSet<Integer>> sccs = new LinkedList<HashSet<Integer>>();

	

	public static void step1DFS(Diagraph G,int vertex) {
		visited[vertex] =  true;

		for (Integer nbr: G.arcs.get(vertex)) {
			if (!visited[(int)nbr]) {
				step1DFS(G,(int)nbr);
			}
		}
		stack.push((Integer)vertex);
	}

	public static void step2DFS(HashSet<Integer> scc, Diagraph Gt, int vertex) {
		visited[vertex] = true;

		for (Integer nbr: Gt.arcs.get(vertex)) {
			if (!visited[(int)nbr]) {
				step2DFS(scc,Gt, (int)nbr);
			}
		}
		scc.add((Integer)vertex);
	}

	public static void outputSCCs(Diagraph Gt) {
		for (int i=0; i<Gt.n; i++) {
			visited[i] =  false;
		}
		while (!stack.isEmpty()) {
			Integer vertex = stack.pop();
			if (!visited[(int)vertex]) {
				HashSet<Integer> currSCC = new HashSet<Integer>();
				sccs.add(currSCC);
				step2DFS(currSCC,Gt,vertex);
			}
		}
		System.out.println("The SCCs are :");
		for (HashSet<Integer> scc: sccs) {
			List<Integer> sccList = new ArrayList<Integer>(scc);
			Collections.sort(sccList);
			System.out.println(sccList);
		}
	}

	public static Diagraph readGraph(Scanner scan) {
		int n= scan.nextInt();
		int m= scan.nextInt();
		Diagraph G = new Diagraph(n);
		for (int i = 0; i<m;i++) {
			G.addEdge(scan.nextInt(), scan.nextInt());
		}
		return G;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Diagraph G = readGraph(scan);
		visited = new boolean[G.n];
		for (int i = 0;i<G.n; i++) {
			if (!visited[i]) {
				step1DFS(G,i);
			}
		}
		Diagraph Gt = G.transpose();
		outputSCCs(Gt);
	}
}


class Diagraph {
	int n;
	ArrayList<HashSet<Integer>> arcs;

	public Diagraph(int n) {
		this.n = n;
		arcs= new ArrayList<HashSet<Integer>>(n);
		for (int i =0; i<n; i++) {
			arcs.add(new HashSet<Integer>());
		}
	}

	public void addEdge(int u, int v) {
		arcs.get(u).add((Integer)v);
	}

	public void deleteEdge(int u, int v) {
		arcs.get(u).remove((Integer)v);
	}

	public Diagraph transpose() {
		Diagraph G = new Diagraph(n);
		for (int u=0; u<n; u++) {
			for (Integer v: arcs.get(u)) {
				G.addEdge((int)v, u);
			}
		}
		return G;
	}
}



