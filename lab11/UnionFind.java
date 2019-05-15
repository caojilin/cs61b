public class UnionFind {

    private int[] arr;


    /* Creates a UnionFind data structure holding N vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int N) {
        arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        valid(v);

        int par = parent(v);
        // if par == 0;
        // root
        if (par < 0) {
            return -arr[par];
        } else {
            while (arr[par] >= 0) {
                par = parent(par);
            }
            return -arr[par];
        }
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        valid(v);

        if (arr[v] < 0) {
            // root
            return v;
        }
        return arr[v];
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        valid(v1);
        valid(v2);
        if (find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid vertices are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        valid(v);

        //v is the root
        if (arr[v] < 0) {
            return v;
        }
        int par = parent(v);

        while (arr[par] >= 0) {
            par = parent(par);
        }
        // path-compression

        int par1 = parent(v);
        arr[v] = par;
        while (arr[par1] >= 0) {
            int temp = parent(par1);
            arr[par1] = par;
            par1 = temp;
        }
        // path-compression
        arr[v] = par;
        return par;
    }

    /* Connects two elements V1 and V2 together. V1 and V2 can be any element,
       and a union-by-size heuristic is used. If the sizes of the sets are
       equal, tie break by connecting V1's root to V2's root. Union-ing a vertex
       with itself or vertices that are already connected should not change the
       structure. */
    public void union(int v1, int v2) {
        valid(v1);
        valid(v2);

        int sizev1 = sizeOf(v1);
        int sizev2 = sizeOf(v2);
        int par1 = find(v1);
        int par2 = find(v2);
        // same set
        if (par1 == par2) {
            return;
        }
        if (sizev1 > sizev2) {

            arr[par2] = par1;
            arr[par1] -= sizev2;

        } else {
            arr[par1] = par2;
            arr[par2] -= sizev1;
        }
    }

    public void valid(int v) {
        if (v < 0 || v > arr.length - 1) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        UnionFind u = new UnionFind(8);
        u.union(0, 1);
        u.union(0, 1);
        u.union(1, 2);
        u.union(3, 2);
        u.union(4, 5);
        u.union(5, 6);
        int f = u.find(3);
        System.out.println("" + f);
        int s = u.sizeOf(2);
        System.out.println("" + s);
    }
}