import org.junit.Before;
import org.junit.Test;

import java.awt.image.Raster;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
public class TestfindDepth {
    Rasterer r = new Rasterer();
    public static final double ROOT_ULLAT = 37.892195547244356, ROOT_ULLON = -122.2998046875,
            ROOT_LRLAT = 37.82280243352756,  ROOT_LRLON = -122.2119140625;
    @Test
    public void test(){
        //expected 7
        System.out.println(r.findDepth(-122.241632,-122.24053,892.0));
    }

    @Test
    public void test2(){

        //d4_x2_y3
        int[] t1 = r.locationToPic(37.892195547244356,-122.2998046875,
                37.82280243352756,-122.2119140625);
        System.out.println(Arrays.toString(t1));
        //d3_x4_y3
//        int[] t = r.locationToPic(37.86617312960056,-122.255859375,
//                37.85749899038596,-122.244873046875);
//        System.out.println(Arrays.toString(t));
    }
    @Test
    public void test3(){
        //d4_x2_y3
//    double[] t = r.info(0,0,0);
//        System.out.println(Arrays.toString(t));
//    }
        //d7_x84_y28
    double[] t = r.info(7,84,28);
        System.out.println(Arrays.toString(t));
    }
    @Test
    public void test4(){
        //d4_x2_y3
//        Suppose params = {ullon=-122.241632, lrlon=-122.24053, w=892.0, h=875.0,
//                          ullat=37.87655, lrlat=37.87548}

        //double queryUlLon, double queryUlLat,
        //double queryLrLon, double queryLrLat, double queryWidth, int depth

        Rasterer.Point[][] d = r.upperLeft(-122.241632,37.87655,
                -122.24053,37.87548,892.0,7);
        //System.out.println(Arrays.toString(t));
        System.out.println(d);
    }

    @Test
    public void testconvertBoxtoName(){
        Rasterer.Point[][] d = r.upperLeft(-122.241632,37.87655,
                -122.24053,37.87548,892.0,7);
        String[][] a = r.convertBoxtoName(d,7);
        System.out.println(a);
    }
    @Test
    public void test12image() {
        //{lrlon=-122.20908713544797, ullon=-122.3027284165759, w=305.0,
        // h=300.0, ullat=37.88708748276975, lrlat=37.848731523430196}
        //expected
        //{raster_ul_lon=-122.2998046875, depth=1, raster_lr_lon=-122.2119140625,
        // raster_lr_lat=37.82280243352756, render_grid=[[d1_x0_y0.png, d1_x1_y0.png],
        // [d1_x0_y1.png, d1_x1_y1.png]], raster_ul_lat=37.892195547244356, query_success=true}

        //findDepth(double queryLrLon, double queryUlLon, double queryWidth)
        int depth = r.findDepth(-122.2104604264636,-122.30410170759153,1091.0);
        System.out.println("depth=" + depth);
        Rasterer.Point[][] d = r.upperLeft(-122.30410170759153,37.870213571328854,
                -122.2104604264636,37.8318576119893,1091.0,depth);
        String[][] a = r.convertBoxtoName(d,1);
        System.out.println(a);

    }
    @Test
    public void test9() {
        //{lrlon=-122.20908713544797, ullon=-122.3027284165759, w=305.0,
        // h=300.0, ullat=37.88708748276975, lrlat=37.848731523430196}
        //expected
        //{raster_ul_lon=-122.2998046875, depth=1, raster_lr_lon=-122.2119140625,
        // raster_lr_lat=37.82280243352756, render_grid=[[d1_x0_y0.png, d1_x1_y0.png],
        // [d1_x0_y1.png, d1_x1_y1.png]], raster_ul_lat=37.892195547244356, query_success=true}

        //findDepth(double queryLrLon, double queryUlLon, double queryWidth)
        int depth = r.findDepth(-122.24053369025242,-122.24163047377972,892.0);
        System.out.println("depth=" + depth);
        Rasterer.Point[][] d = r.upperLeft(-122.24163047377972,37.87655856892288,
                -122.24053369025242,37.87548268822065,892.0,depth);
        String[][] a = r.convertBoxtoName(d,depth);
        System.out.println(a);

    }
    @Test
    public void test1234() {
        //{lrlon=-122.20908713544797, ullon=-122.3027284165759, w=305.0,
        // h=300.0, ullat=37.88708748276975, lrlat=37.848731523430196}
        //expected
        //{raster_ul_lon=-122.2998046875, depth=1, raster_lr_lon=-122.2119140625,
        // raster_lr_lat=37.82280243352756, render_grid=[[d1_x0_y0.png, d1_x1_y0.png],
        // [d1_x0_y1.png, d1_x1_y1.png]], raster_ul_lat=37.892195547244356, query_success=true}

        //findDepth(double queryLrLon, double queryUlLon, double queryWidth)
        int depth = r.findDepth(-122.20908713544797,-122.3027284165759,305.0);
        System.out.println("depth=" + depth);
        Rasterer.Point[][] d = r.upperLeft(-122.3027284165759,37.88708748276975,
                -122.20908713544797,37.848731523430196,305.0,depth);
        String[][] a = r.convertBoxtoName(d,depth);
        System.out.println(a);

    }
}
*/
