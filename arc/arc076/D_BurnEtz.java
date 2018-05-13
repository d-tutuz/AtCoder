package arc076;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;


public class D_BurnEtz {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		Town[] towns = new Town[n];
		Town[] copy = new Town[n];
		for(int i = 0; i < n; i++){
			String[] tmpArray = br.readLine().split(" ");
			towns[i] = new Town(Integer.parseInt(tmpArray[0]), Integer.parseInt(tmpArray[1]));
			copy[i] = towns[i];
		}

		//xについてソート
		Arrays.sort(copy, new Comparator<Town>() {
			public int compare(Town t1, Town t2){
				return t1.x - t2.x;
			}
		});

		//隣接する街のコスト計算
		for(int i = 0; i < n - 1; i++){
			int cost = Math.min(copy[i+1].x - copy[i].x, Math.abs(copy[i+1].y - copy[i].y));
			copy[i].addCost(copy[i+1], cost);
			copy[i+1].addCost(copy[i], cost);
		}

		//yについてソート
		Arrays.sort(copy, new Comparator<Town>() {
			public int compare(Town t1, Town t2){
				return t1.y - t2.y;
			}
		});

		//隣接する街のコスト計算
		for(int i = 0; i < n - 1; i++){
			int cost = Math.min(copy[i+1].y - copy[i].y, Math.abs(copy[i+1].x - copy[i].x));
			copy[i].addCost(copy[i+1], cost);
			copy[i+1].addCost(copy[i], cost);
		}

		long result = MST(towns);
		System.out.println(result);
	}

	static long MST(Town[] towns){
		long cost = 0;
		int n = towns.length;
		boolean[] visited = new boolean[n];

		PriorityQueue<EdgeBrun> pq = new PriorityQueue<EdgeBrun>();
		//0番目の街から始める
		visited[0] = true;
		EdgeBrun[] edges = towns[0].getEdges();
		for(int i = 0; i < edges.length ; i++){
			pq.add(edges[i]);
		}

		int count = 0;
		while(!pq.isEmpty()){
			//最小の辺を探す
			EdgeBrun minEdge = pq.poll();
			if(visited[minEdge.to]){
				continue;
			}
			visited[minEdge.to] = true;
			cost += minEdge.cost;
			Town nextTown = towns[minEdge.to];

			edges = nextTown.getEdges();

			for(int i = 0; i < edges.length ; i++){
				pq.add(edges[i]);
			}
			count++;

		}

		return cost;
	}

}

class EdgeBrun implements Comparable<EdgeBrun>{
	int from;
	int to;
	int cost;

	public EdgeBrun(Town t1, Town t2, int cost){
		from = t1.id;
		to = t2.id;
		this.cost = cost;
	}

	public int compareTo(EdgeBrun e){
		return this.cost - e.cost;
	}

	public String toString(){
		return "from "+from+" to "+to+" (cost "+cost+")";
	}
}
class Town {
	int x;
	int y;
	int id;
	static int IDSEED = 0;
	HashMap<Town, Integer> map = new HashMap<Town, Integer>();

	public Town(int x, int y){
		this.id = IDSEED;
		IDSEED++;
		this.x = x;
		this.y = y;
	}

	public void addCost(Town t, int cost){
		map.put(t, cost);
	}

	public EdgeBrun[] getEdges(){
		EdgeBrun[] result = new EdgeBrun[map.size()];
		Set<Town> set = map.keySet();
		Iterator<Town> it = set.iterator();
		int i = 0;
		while(it.hasNext()){
			Town tmpTown = it.next();
			result[i] = new EdgeBrun(this, tmpTown, map.get(tmpTown));
			i++;
		}

		return result;
	}
}
