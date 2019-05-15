/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    /** The max image depth level. */
    public static final int MAX_DEPTH = 7;

    /**
     * Takes a user query and finds the grid of images that best matches the query. These images
     * will be combined into one big image (rastered) by the front end. The grid of images must obey
     * the following properties, where image in the grid is referred to as a "tile".
     * <ul>
     *     <li>The tiles collected must cover the most longitudinal distance per pixel (LonDPP)
     *     possible, while still covering less than or equal to the amount of longitudinal distance
     *     per pixel in the query box for the user viewport size.</li>
     *     <li>Contains all tiles that intersect the query bounding box that fulfill the above
     *     condition.</li>
     *     <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     * </ul>
     * @param params The RasterRequestParams containing coordinates of the query box and the browser
     *               viewport width and height.
     * @return A valid RasterResultParams containing the computed results.
     */
    public RasterResultParams getMapRaster(RasterRequestParams params) {

        double lonDpp = lonDPP(params.lrlon, params.ullon, params.w);
        int n;
        for (n = 0; n < 8; n += 1) {
            double lon = MapServer.ROOT_LONDPP / Math.pow(2, n);
            if (lon <= lonDpp) {
                break;
            }
        }
        if (n == 8) {
            n = 7;
        }
        double latti = MapServer.ROOT_LAT_DELTA / Math.pow(2, n);
        double lonti = MapServer.ROOT_LON_DELTA / Math.pow(2, n);
        int left, right, up, down;
        left = (int) ((params.ullon - MapServer.ROOT_ULLON) / lonti);
        right = (int) ((params.lrlon - MapServer.ROOT_ULLON) / lonti);
        up = (int) ((MapServer.ROOT_ULLAT - params.ullat) / latti);
        down = (int) ((MapServer.ROOT_ULLAT - params.lrlat) / latti);
        String [][] renderGrid = new String [down - up + 1][right - left + 1];

        for (int a = 0; a <= down - up; a += 1) {
            for (int x = 0; x <= right - left; x += 1) {
                renderGrid[a][x] = "d" + n + "_x" + (x + left) + "_y" + (a + up) + ".png";
            }
        }

        double rasterUllat = MapServer.ROOT_ULLAT - up * latti;
        double rasterLrlat = MapServer.ROOT_ULLAT - (down + 1) * latti;
        double rasterUllon = MapServer.ROOT_ULLON + left * lonti;
        double rasterLrlon = MapServer.ROOT_ULLON + (right + 1) * lonti;

        RasterResultParams resultParams = new RasterResultParams.Builder()
                .setRenderGrid(renderGrid)
                .setRasterUlLat(rasterUllat)
                .setRasterLrLat(rasterLrlat)
                .setRasterUlLon(rasterUllon)
                .setRasterLrLon(rasterLrlon)
                .setDepth(n)
                .setQuerySuccess(true)
                .create();

        if (rasterLrlat > rasterUllat || rasterLrlon < rasterUllon) {
            return RasterResultParams.queryFailed();
        } else if (rasterLrlat < MapServer.ROOT_LRLAT || rasterLrlon > MapServer.ROOT_LRLON
                || rasterUllat > MapServer.ROOT_ULLAT || rasterUllon < MapServer.ROOT_ULLON) {
            return RasterResultParams.queryFailed();
        } else {
            return resultParams;
        }
    }


    /**
     * Calculates the lonDPP of an image or query box
     * @param lrlon Lower right longitudinal value of the image or query box
     * @param ullon Upper left longitudinal value of the image or query box
     * @param width Width of the query box or image
     * @return lonDPP
     */
    private double lonDPP(double lrlon, double ullon, double width) {
        return (lrlon - ullon) / width;
    }
}
