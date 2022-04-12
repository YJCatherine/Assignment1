package Assignment7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Assignment7 {

    //133. Clone Graph
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Deque<Node> q = new LinkedList<>();
        q.offer(node);
        Map<Integer, Node> map = new HashMap<>();
        map.put(node.val, new Node(node.val));
        while (!q.isEmpty()) {
            Node source = q.poll();
            List<Node> nodes = source.neighbors;
            Node dest = map.get(source.val);
            for (Node n: nodes) {
                if (!map.containsKey(n.val)) {
                    q.offer(n);
                    map.put(n.val, new Node(n.val));
                }
                dest.neighbors.add(map.get(n.val));
            }
        }

        return map.get(node.val);
    }

    //847. Shortest Path Visiting All Nodes
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] + a[3] - b[2] - b[3]);

        for(int i = 0;i < n;i++){
            pq.offer(new int[]{i,1 << i,n - 1,0});
        }

        while(!pq.isEmpty()){
            int[] info = pq.poll();
            int cur = info[0],state = info[1],remainStep = info[2],step = info[3];
            if(remainStep == 0) return step;
            for(int next : graph[cur]){
                int remain = remainStep - 1 + (state >> next & 1);
                pq.offer(new int[]{next,state | (1 << next),remain,step + 1});
            }
        }

        return -1;
    }


    //2065. Maximum Path Quality of a Graph
    private int max=0;
    private Map<Integer, Set<int[]>> graphMap;
    private int[] values;
    private int maxTime;
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        this.graphMap=new HashMap<>();
        this.values=values;
        this.maxTime=maxTime;
        int[] visited=new int[values.length];
        for(int[] edge:edges){
            this.graphMap.computeIfAbsent(edge[0],k->new HashSet<>()).add(edge);
            this.graphMap.computeIfAbsent(edge[1],k->new HashSet<>()).add(new int[]{edge[1],edge[0],edge[2]});
        }
        dfs(0,0,values[0],visited);
        return max;
    }
    private void dfs(int curTime,int curNode,int curValue,int[] visited){
        if(curTime>maxTime){
            return;
        }
        if(curNode==0){
            this.max=Math.max(max,curValue);
        }
        visited[curNode]++;
        for(int[] next:graphMap.getOrDefault(curNode,new HashSet<>())){
            if(visited[next[1]]>0){
                dfs(next[2]+curTime,next[1],curValue,visited);
            }else{
                dfs(next[2]+curTime,next[1],curValue+values[next[1]],visited);
            }
        }
        visited[curNode]--;
    }
}
