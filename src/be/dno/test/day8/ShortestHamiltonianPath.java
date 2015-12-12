package be.dno.test.day8;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

import org.apache.commons.io.FileUtils;

public class ShortestHamiltonianPath {

	public static int[] getShortestHamiltonianPath(int[][] dist) {
		int n = dist.length;
		int[][] dp = new int[1 << n][n];
		
		//fill with very small values
		for (int[] d : dp){
			Arrays.fill(d, Integer.MAX_VALUE / 2); //was max
		}
		
		
		//put 0 if i == j
		for (int i = 0; i < n; i++){
			dp[1 << i][i] = 0;
		}
		
		
		
		for (int mask = 0; mask < 1 << n; mask++) {
			for (int i = 0; i < n; i++) {
				if ((mask & 1 << i) != 0) {
					for (int j = 0; j < n; j++) {
						if ((mask & 1 << j) != 0) {
							/*System.out.println("dp[mask][i]: " + dp[mask][i]);
							System.out.println("dp[mask ^ (1 << i)][j]: " + dp[mask ^ (1 << i)][j]);
							System.out.println("dist[j][i]: " + dist[j][i]);
							System.out.println(" -----  ");*/
							dp[mask][i] = Math.min(dp[mask][i], dp[mask ^ (1 << i)][j] - dist[j][i]); 
						}
					}
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < dp.length; i++){
			for(int j = 0; j < dp[i].length; j++){
				min = Math.min(min, dp[i][j]);
			}
		}
		System.out.println(min*-1);
		
		//printDoubleArray(dp);

		// reconstruct path
		int cur = (1 << n) - 1;
		int[] order = new int[n];
		int last = -1;
		for (int i = n - 1; i >= 0; i--) {
			int bj = -1;
			for (int j = 0; j < n; j++) {
				if ((cur & 1 << j) != 0	&& (bj == -1 || dp[cur][bj]	+ (last == -1 ? 0 : dist[bj][last]) < dp[cur][j] - (last == -1 ? 0 : dist[j][last]))) {
					bj = j;
				}
			}
			order[i] = bj;
			cur ^= 1 << bj;
			last = bj;
		}
		//System.out.println(Arrays.toString(order));
		return order;
	}
	
	private static Set<Path> getPaths(){
		Set<Path> paths = new HashSet<Path>();
		try {
			for(String line : FileUtils.readLines(new File("C:\\Temp\\input.txt"), Charset.forName("UTF-8"))){
				int indexOfEqual = line.indexOf(" = ");
				String[] cities = line.substring(0, indexOfEqual).split(" to ");
				int distance = Integer.parseInt(line.substring(indexOfEqual + 3));
				paths.add(new Path(cities[0], cities[1], distance));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return paths;
	}

	public static void main(String[] args) {
		Set<Path> paths = getPaths();
		Set<String> names = new TreeSet<String>();
		for(Path path : paths){
			names.add(path.source);
			names.add(path.target);
		}
		String[] n = names.toArray(new String[names.size()]);
		Path[] cities = paths.toArray(new Path[paths.size()]);
		
		int[][] dist = new int[n.length][n.length];
		for(int i = 0; i < n.length; i++){
			for(int j = 0; j < n.length; j++){
				if (i == j){
					dist[i][j] = 0;
					continue;
				}
				dist[i][j] = getDistanceBetween(cities, n[i], n[j], false);
			}
		}		
		
		int[] travel = getShortestHamiltonianPath(dist);
		int total = 0;
		for (int t  = 0; t < travel.length-1; t++) {
			total += getDistanceBetween(cities, n[travel[t]],n[travel[t+1]], false);
			//System.out.println(n[travel[t]] + " -> " + n[travel[t+1]]);
		}
		//System.out.println(total);
	}
	
	private static void printDoubleArray(int[][] array){
		System.out.print("\n");
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array[i].length; j++){
				System.out.print(array[i][j] + "|");
			}
			System.out.print("\n");
		}
		
	}
	
	private static int getDistanceBetween(Path[] cities, String s1, String s2, boolean debug){
		for(int i = 0; i < cities.length; i++){
			if ((cities[i].source.equals(s1) && cities[i].target.equals(s2)) || (cities[i].source.equals(s2) && cities[i].target.equals(s1))){
				if (debug){
					System.out.println("distance between " + s1 + " and " + s2 + " is " + cities[i].distance);
				}
				return cities[i].distance;
			}
		}
		System.err.println("NO distance between " + s1 + " and " + s2);
		return 0;
	}
	
	
	private static class Path {
		public String source;
		public String target;
		public int distance;
		
		public Path(String source, String target, int distance) {
			super();
			this.source = source;
			this.target = target;
			this.distance = distance;
		}
	}
}