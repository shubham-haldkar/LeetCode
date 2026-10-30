// 3970. Shortest Path With At Most K Consecutive Identical Characters

class ShortestPathWithAtMostKConsecutiveIdenticalCharacters {
    class Pair{
        int v;
        int d;
        Pair(int v,int d){
            this.v=v;
            this.d=d;
        }
    }
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        ArrayList<ArrayList<Pair>> adj=new ArrayList<>();

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] e:edges){
            adj.get(e[0]).add(new Pair(e[1],e[2]));
        }

        // smallest streak with which each node has already been popped
        int[] minRun=new int[n];
        Arrays.fill(minRun, Integer.MAX_VALUE);

        // {node, cost, streak}
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)-> Integer.compare(a[1],b[1]));
        pq.offer(new int[]{0,0,1});

        while(!pq.isEmpty()){
            int[] arr=pq.poll();
            int node=arr[0];
            int wt=arr[1];
            int run=arr[2];

            if(run >= minRun[node]) continue;   // dominated: cheaper-or-equal with shorter-or-equal streak seen
            minRun[node]=run;

            if(node==n-1) return wt;

            for(Pair nei:adj.get(node)){
                int v=nei.v;
                int newRun = (labels.charAt(v)==labels.charAt(node)) ? run+1 : 1;
                if(newRun>k) continue;
                if(newRun >= minRun[v]) continue;   // already have a better state at v

                pq.offer(new int[]{v, wt+nei.d, newRun});
            }
        }

        return -1;
    }
}
