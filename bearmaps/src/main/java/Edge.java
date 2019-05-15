



public class Edge implements Comparable<Edge> {
    long id;
    long srcId;
    long destId;
    double dist;
    String name;
    String maxspeed;
    boolean oneway;
    String building;
    String amenity;
    String parking;
    int lanes;
    String access;

    public Edge(long id , long srcId, long destId) {
        this.id = id;
        this.srcId = srcId;
        this.destId = destId;
        this.dist = -1;
    }

    public Edge(long id, long srcId, long destId, double dist) {
        this.id = id;
        this.srcId = srcId;
        this.destId = destId;
        this.dist = dist;
    }

    public Edge(long id, long srcId, long destId, double dist, String name, String speed) {
        this.id = id;
        this.srcId = srcId;
        this.destId = destId;
        this.dist = dist;
        this.name = name;
        this.maxspeed = speed;
    }

    public int compareTo(Edge other) {
        return 0;

    }

    /* Returns the string representation of an edge. */
    public String toString() {
        return "{" + srcId + ", " + destId + "} -> " + dist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSrcId() {
        return this.srcId;
    }

    public long getDestId() {
        return this.destId;
    }

    public double getDist() {
        return this.dist;
    }

    public String getName() {
        return this.name;
    }

    public void setDist(Double dist) {
        this.dist = dist;
    }

    public String getMaxspeed() {
        return this.maxspeed;
    }

    public void setMaxspeed(String maxspeed) {
        this.maxspeed = maxspeed;
    }

    public Long getId() {
        return this.id;
    }


}
