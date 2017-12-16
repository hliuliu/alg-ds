
import java.util.*;
import java.io.*;

public class NetworkFlow {


	private int numvert;
	private int[][] capacities,residuals;

	public NetworkFlow(int n) {
		numvert=n;
		capacities=new int[n][n];
		residuals=new int [n][n];
	}

	public NetworkFlow(int n, int[][]G) {
		this(n);
		int ni=min(n,G.length);
		for (int i=0;i<ni;i++) {
			int nj=min(n,G[i].length);
			for (int j=0;j<nj;j++) {
				capacities[i][j]=G[i][j];
				residuals[i][j]=G[i][j];
			}
		}
	}

	public NetworkFlow(int[][]G) {
		this(G.length,G);
	}


	public int capacity(int u, int v) {
		return capacities[u][v];
	}

	public int residualCapacity(int u, int v) {
		return residuals[u][v];
	}

	public boolean isEdge(int u, int v) {
		return capacity(u,v)>0;
	}

	public boolean isResidualEdge(int u, int v) {
		return residualCapacity(u,v)>0;
	}

	public ArrayList<Integer> outNeighbours(int u) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i=0;i<numvert;i++) {
			if (isEdge(u,i)) {
				al.add(i);
			}
		}
		return al;
	}

	public ArrayList<Integer> residualOutNeighbours(int u) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i=0;i<numvert;i++) {
			if (isResidualEdge(u,i)) {
				al.add(i);
			}
		}
		return al;
	}

	public void pushFlow(int u,int v,int f) {
		if (f<0) {
			pushFlow(v,u,-f);
		}else if (f<=residuals[u][v]) {
			residuals[u][v]-=f;
			residuals[v][u]+=f;
		}
	}

	public ArrayList<Integer> nextAugmentingPath() {
		return nextAugmentingPath(0,new ArrayList<Integer>(),new boolean[numvert]);
	}

	private ArrayList<Integer> nextAugmentingPath(int u,ArrayList<Integer> path,boolean [] pathcontains) {
		//System.out.println(u);
		path.add(u);
		pathcontains[u]=true;
		if (u==numvert-1) {
			return path;
		}

		for (int v:residualOutNeighbours(u)) {
			if (!pathcontains[v] && nextAugmentingPath(v,path,pathcontains)!=null) {
				return path;
			}
		}
		pathcontains[u]=false;
		path.remove(path.size()-1);
		return null;
	}

	public void reset() {
		for (int i=0;i<numvert;i++) {
			for (int j=0; j<numvert; j++) {
				residuals[i][j]=capacities[i][j];
			}
		}
	}

	public int flow(int u,int v) {
		return capacity(u,v)-residualCapacity(u,v);
	}

	public int flowValue() {
		int ans=0;
		for (int u=0;u<numvert-1;u++) {
			ans+=flow(u,numvert-1);
		}
		return ans;
	}


	public int bottleneck(ArrayList<Integer> path) {
		int u=-1,ans=-1;
		for (int v: path) {
			if (u>=0) {
				ans=ans<0? residuals[u][v]: min(ans,residuals[u][v]);
			}
			u=v;
		}
		return ans;	
	}

	public void updateFlow(ArrayList<Integer> path) {
		int bn=bottleneck(path);
		//System.out.println(bn);
		int u=-1;
		for (int v: path) {
			if (u>=0) {
				pushFlow(u,v,bn);
			}
			u=v;
		}
	}

	public int maxFlow() {
		// Ford Fulkerson's algorithm
		ArrayList<Integer> ap;
		while((ap=nextAugmentingPath())!=null) {
			//System.out.println(ap);
			updateFlow(ap);
		}
		return flowValue();
	}

	
	public static int min(int a,int b) {return a<b? a:b;}

	public static int[][] readGraph(Scanner input) {
		int n=input.nextInt();
		int m=input.nextInt();
		int [][] G=new int[n][n];
		for (int i=0;i<m;i++) {
			int u=input.nextInt();
			int v=input.nextInt();
			int c=input.nextInt();
			G[u][v]=c;
		}
		return G;
	}

	public static void main(String[] args) throws IOException {
		int [][] G1= {
			{0,10,5,0,0,0,0,0},
			{0,0,0,8,5,0,0,0},
			{0,0,0,0,7,0,0,0},
			{0,0,0,0,0,7,5,0},
			{0,0,0,0,0,0,8,0},
			{0,0,0,0,0,0,0,5},
			{0,0,0,0,0,0,0,10},
			{0,0,0,0,0,0,0,0}
		};

		NetworkFlow f1=new NetworkFlow(G1);
		/*ArrayList<Integer> p1_1=f1.nextAugmentingPath();
		System.out.println(p1_1);
		System.out.println(f1.bottleneck(p1_1));
		f1.updateFlow(p1_1);
		System.out.println(f1.flowValue());
		f1.reset();*/
		System.out.println(f1.maxFlow());

		int [][] G2= readGraph(new Scanner(new File("g2.txt")));

		NetworkFlow f2=new NetworkFlow(G2);

		System.out.println(f2.maxFlow());

		int [][] G3= readGraph(new Scanner(new File("g3.txt")));

		NetworkFlow f3=new NetworkFlow(G3);

		System.out.println(f3.maxFlow());


	}
	
}




