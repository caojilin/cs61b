import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.ArrayList;
/**
 * This class provides a <code>shortestPath</code> method and <code>routeDirections</code> for
 * finding routes between two points on the map.
 */
public class Router {
    /**
     * Return a <code>List</code> of vertex IDs corresponding to the shortest path from a given
     * starting coordinate and destination coordinate.
     * @param g <code>GraphDB</code> data source.
     * @param stlon The longitude of the starting coordinate.
     * @param stlat The latitude of the starting coordinate.
     * @param destlon The longitude of the destination coordinate.
     * @param destlat The latitude of the destination coordinate.
     * @return The <code>List</code> of vertex IDs corresponding to the shortest path.
     */
    public static List<Long> shortestPath(GraphDB g,
                                          double stlon, double stlat,
                                          double destlon, double destlat) {
        HashMap<Long, Long> predecessor = new HashMap<>();
        HashMap<Long, Double> distance = new HashMap<>();
        PriorityQueue<Long> fri = new PriorityQueue(g.nodes.size(),
                (x, y) -> distance.get(x).compareTo(distance.get(y)));
        HashSet<Long> visited = new HashSet<>();
        long stop = g.closest(destlon, destlat);
        long begin = g.closest(stlon, stlat);
        fri.add(begin);
        distance.put(begin, 0.0);
        predecessor.put(begin, begin);
        long itDriveMeCrazy;
        while (!fri.isEmpty() && !visited.contains(stop)) {
            itDriveMeCrazy = fri.poll();
            visited.add(itDriveMeCrazy);
            for (long omg : g.adjacent(itDriveMeCrazy)) {
                if (!visited.contains(omg)) {
                    if (!fri.contains(omg)
                            || g.distance(itDriveMeCrazy, omg)
                            + distance.get(itDriveMeCrazy) < distance.get(omg)) {
                        distance.put(omg, distance.get(itDriveMeCrazy)
                                + g.distance(itDriveMeCrazy, omg));
                        fri.add(omg);
                        predecessor.put(omg, itDriveMeCrazy);
                    }
                }
            }
        }
        ArrayList<Long> bearpath = new ArrayList<>();
        try {
            for (long xqd = stop; xqd != begin && !predecessor.isEmpty();
                 xqd = predecessor.get(xqd)) {
                bearpath.add(0, xqd);
            }
            bearpath.add(0, begin);
        } catch (NullPointerException e) {
            return Collections.emptyList();
        }
        return bearpath;
    }

    // public static List<Long> shortestPath(GraphDB g,
    //                                       double stlon, double stlat,
    //                                       double destlon, double destlat) {
    //     PriorityQueue<Map.Entry<Long, Double>> fringe =
    //             new PriorityQueue<Map.Entry<Long, Double>>(
    //                     (Map.Entry<Long, Double> x, Map.Entry<Long, Double> y) ->
    //                             (int) (x.getValue() - y.getValue())
    //             );

    //     Long start = g.closest(stlon, stlat);
    //     Long end = g.closest(destlon, destlat);

    //     HashSet<Long> marked = new HashSet<>();
    //     Map<Long, Long> parent = new HashMap<>();
    //     Map<Long, Double> best = new HashMap<>();
    //     List<Long> path = new ArrayList<>();

    //     for (Long k : g.getAllVertices()) {
    //         best.put(k, Double.MAX_VALUE);
    //     }
    //     best.put(start, 0.0);
    //     parent.put(start, start);
    //     fringe.add(new AbstractMap.SimpleEntry<Long, Double>
    //              (start, g.distance(start, end) * 1000000));

    //     while (!fringe.isEmpty()) { // && fringe.peek().getKey() != end) {
    //         Long f = fringe.poll().getKey();

    //         if (marked.contains(f)) {
    //             continue;
    //         }
    //         if (f == end) {
    //             break;
    //         }
    //         marked.add(f);

    //         for (long n : g.adjacent(f)) {
    //             if (n != parent.get(f)) {
    //                 //System.out.println("n : " + n);
    //                 double disF2N = g.distance(f, n);
    //                 double disS2N = best.get(f) + disF2N;
    //                 //System.out.println("bestF : " + best.get(f)
    //                  + " f2n : " + disF2N + " bestN : " + best.get(n) + " fringe : " + fringe);
    //                 if (disS2N < best.get(n)) {
    //                     parent.put(n, f);
    //                     //System.out.println("parent " + parent);
    //                     best.put(n, disS2N);
    //                     fringe.add(new AbstractMap.SimpleEntry<Long, Double>
    //                                  (n, (disS2N + g.distance(n, end))* 1000000));
    //                 }
    //             }

    //         }
    //         //System.out.println(fringe);
    //     }

    //     /*
    //     Long c = end;

    //     while (c != start) {
    //         path.add(c);
    //         //if (parent.get(c) == start) {
    //         //    break;
    //         //}
    //         c = parent.get(c);
    //     }
    //     path.add(start);
    //     */

    //     path.add(0, end);
    //     long c = end;
    //     while (c != start) {
    //         path.add(0, parent.get(c));
    //         c = parent.get(c);
    //         //c = parent.get(0);
    //     }
    //     //path.add(start);

    //     //for (int i = path.size() -1; i >= 0; i--) {

    //     //}

    //     return path;
    // }



    // public static List<Long> shortestPath3(GraphDB g,
    //                                       double stlon, double stlat,
    //                                       double destlon, double destlat) {

    //     PriorityQueue<Map.Entry<Long, Double>> fringe =
    //             new PriorityQueue<Map.Entry<Long, Double>>(
    //                     (Map.Entry<Long, Double> x, Map.Entry<Long, Double> y) ->
    //                             (int) (x.getValue()  - y.getValue())
    //             );
    //     HashSet<Long> marked = new HashSet<>();
    //     HashMap<Long, Double> best = new HashMap<Long, Double>();
    //     HashMap<Long, Long> parent = new HashMap<Long, Long>();
    //     //List<Long> path = new ArrayList<>();
    //     List<Long> spath = new ArrayList<>();
    //     Long fromIntersection = g.closest(stlon, stlat);
    //     Long toIntersection = g.closest(destlon, destlat);

    //     for (Long k : g.getAllVertices()) {
    //         best.put(k, Double.MAX_VALUE);
    //     }

    //     fringe.add(new AbstractMap.SimpleEntry<Long, Double>
    //           (fromIntersection, g.distance(fromIntersection, toIntersection)*1000000));
    //     //best.put(fromIntersection, 0.0);

    //     while (!fringe.isEmpty()) {
    //         //System.out.println("fringe in : " + fringe);

    //         Map.Entry<Long, Double> v = fringe.poll();
    //         if (v.getKey() == toIntersection) {
    //             break;
    //         }

    //         if (marked.contains(v.getKey())) {
    //             //System.out.println("marked");
    //             continue;
    //         }



    //         //path.add(v.getKey());

    //         marked.add(v.getKey());
    //         //System.out.println(v.getKey());
    //         //path.add(v.getKey());
    //         for (Long e : g.adjacent(v.getKey())) {
    //             //System.out.println("Edge " + e + " e " + e + " marked: " + marked);
    //             //if (!marked.contains(e)) {
    //             //if (parent.get(v.getKey()) != e) {
    //                 //if (!best.containsKey(e)) {
    //                 //    best.put(e, Double.MAX_VALUE);
    //                 //}
    //                 //if (!best.containsKey(v.getKey())) {
    //                 //    best.put(v.getKey(), 0.0);
    //                 //}
    //                 Double d = g.distance(v.getKey(), e);
    //                 Double bestD = best.get(v.getKey());
    //                 Double bestE = best.get(e);
    //                 if ((bestD + d) < bestE ) {
    //                     //System.out.println("e : " + e + " best v : "
    //                     + bestD + " d : " + d + " best e: " + bestE
    //                     + " h : " +  g.distance(e, toIntersection));

    //                     parent.put(e, v.getKey());
    //                     best.put(e, bestD + d);
    //                     fringe.add(new AbstractMap.SimpleEntry<Long, Double>(e,
    //                             bestD + d + g.distance(e, toIntersection) * 1000000));

    //                     //System.out.println("Bests " + best);
    //                 }
    //                 /*
    //                 best.put(e.getDestId(), best.get(v.getKey()) + e.getDist());
    //                 fringe.add(new AbstractMap.SimpleEntry<Long, Double>(e.getDestId(),
    //                         best.get(v.getKey()) + e.getDist()
    //                                + g.distance(e.getDestId(), toIntersection)));
    //                 parent.put(e.getDestId(), v.getKey());
    //                 */
    //                 //System.out.println("fringe out: " + fringe);
    //             //}
    //         }
    //         System.out.println("fringe out: " + fringe);
    //     }

    //     spath.add(0, toIntersection);
    //     long c = toIntersection;
    //     while (c != fromIntersection) {
    //         spath.add(0, parent.get(c));
    //         c = parent.get(c);
    //     }
    //     System.out.println(spath);
    //     System.out.println("end");
    //     return spath;
    // }
    /**
     * Given a <code>route</code> of vertex IDs, return a <code>List</code> of
     * <code>NavigationDirection</code> objects representing the travel directions in order.
     * @param g <code>GraphDB</code> data source.
     * @param route The shortest-path route of vertex IDs.
     * @return A new <code>List</code> of <code>NavigationDirection</code> objects.
     */
    public static List<NavigationDirection> routeDirections(GraphDB g, List<Long> route) {
        NavigationDirection n = new NavigationDirection();
        n.way = g.nodes.get(route.get(0)).name;
        List<NavigationDirection> directions = new ArrayList<>();
        for (int a = 1; a < route.size(); a += 1) {
            long tempId = route.get(a);
            if (g.nodes.get(tempId).name != n.way) {
                directions.add(n);
                n = new NavigationDirection();
            } else {
                n.distance += g.distance(tempId, route.get(a - 1));
            }
            if (a == route.size()) {
                a = a - 1;
            }
        }
        return directions;
    }


    /**
     * Class to represent a navigation direction, which consists of 3 attributes:
     * a direction to go, a way, and the distance to travel for.
     */
    public static class NavigationDirection {

        /** Integer constants representing directions. */
        public static final int START = 0, STRAIGHT = 1, SLIGHT_LEFT = 2, SLIGHT_RIGHT = 3,
                RIGHT = 4, LEFT = 5, SHARP_LEFT = 6, SHARP_RIGHT = 7;

        /** Number of directions supported. */
        public static final int NUM_DIRECTIONS = 8;

        /** A mapping of integer values to directions.*/
        public static final String[] DIRECTIONS = new String[NUM_DIRECTIONS];

        static {
            DIRECTIONS[START] = "Start";
            DIRECTIONS[STRAIGHT] = "Go straight";
            DIRECTIONS[SLIGHT_LEFT] = "Slight left";
            DIRECTIONS[SLIGHT_RIGHT] = "Slight right";
            DIRECTIONS[RIGHT] = "Turn right";
            DIRECTIONS[LEFT] = "Turn left";
            DIRECTIONS[SHARP_LEFT] = "Sharp left";
            DIRECTIONS[SHARP_RIGHT] = "Sharp right";
        }

        /** The direction represented.*/
        int direction;
        /** The name of this way. */
        String way;
        /** The distance along this way. */
        double distance = 0.0;

        public String toString() {
            return String.format("%s on %s and continue for %.3f miles.",
                    DIRECTIONS[direction], way, distance);
        }

        /**
         * Returns a new <code>NavigationDirection</code> from a string representation.
         * @param dirAsString <code>String</code> instructions for a navigation direction.
         * @return A new <code>NavigationDirection</code> based on the string, or <code>null</code>
         * if unable to parse.
         */
        public static NavigationDirection fromString(String dirAsString) {
            String regex = "([a-zA-Z\\s]+) on ([\\w\\s]*) and continue for ([0-9\\.]+) miles\\.";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(dirAsString);
            NavigationDirection nd = new NavigationDirection();
            if (m.matches()) {
                String direction = m.group(1);
                if (direction.equals("Start")) {
                    nd.direction = NavigationDirection.START;
                } else if (direction.equals("Go straight")) {
                    nd.direction = NavigationDirection.STRAIGHT;
                } else if (direction.equals("Slight left")) {
                    nd.direction = NavigationDirection.SLIGHT_LEFT;
                } else if (direction.equals("Slight right")) {
                    nd.direction = NavigationDirection.SLIGHT_RIGHT;
                } else if (direction.equals("Turn right")) {
                    nd.direction = NavigationDirection.RIGHT;
                } else if (direction.equals("Turn left")) {
                    nd.direction = NavigationDirection.LEFT;
                } else if (direction.equals("Sharp left")) {
                    nd.direction = NavigationDirection.SHARP_LEFT;
                } else if (direction.equals("Sharp right")) {
                    nd.direction = NavigationDirection.SHARP_RIGHT;
                } else {
                    return null;
                }

                nd.way = m.group(2);
                try {
                    nd.distance = Double.parseDouble(m.group(3));
                } catch (NumberFormatException e) {
                    return null;
                }
                return nd;
            } else {
                // Not a valid nd
                return null;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof NavigationDirection) {
                return direction == ((NavigationDirection) o).direction
                        && way.equals(((NavigationDirection) o).way)
                        && distance == ((NavigationDirection) o).distance;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(direction, way, distance);
        }
    }
}