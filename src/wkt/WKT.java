/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wkt;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.Point;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.geometry.MultiPolygon;

/**
 *
 * @author apac
 */
public class WKT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        WKTWriter wktWriter = new WKTWriter();
        String wktString = wktWriter.write(new Point());        
        System.out.println(wktString);
        wktString = wktWriter.write(new Point(30, 30));        
        System.out.println(wktString);
        wktString = wktWriter.write(new LineString());        
        System.out.println(wktString);
        wktString = wktWriter.write(new LineString(new double[]{30, 10, 10, 30, 40, 40}));        
        System.out.println(wktString);
        wktString = wktWriter.write(new Polygon());        
        System.out.println(wktString);
        wktString = wktWriter.write(new Polygon(new LineString(), new LineString[]{new LineString(new double[]{30, 10, 10, 30, 40, 40, 30, 10})}));        
        System.out.println(wktString);
        wktString = wktWriter.write(new Polygon(new LineString(new double[]{30, 10, 10, 30, 40, 40, 30, 10}), new LineString[]{new LineString(new double[]{30, 10, 10, 30, 40, 40, 30, 10})}));        
        System.out.println(wktString);
        wktString = wktWriter.write(new GeometryCollection<Geometry>());        
        System.out.println(wktString);
        wktString = wktWriter.write(new GeometryCollection<Geometry>(new Geometry[]{new Point(4,6), new LineString(new double[] {4,6,7,10})}));        
        System.out.println(wktString);
        wktString = wktWriter.write(new MultiPoint());        
        System.out.println(wktString);
        wktString = wktWriter.write(new MultiPoint(new Point[]{new Point(30, 30), new Point(40, 40), new Point(50, 50)}));
        System.out.println(wktString);
        wktString = wktWriter.write(new MultiLineString());        
        System.out.println(wktString);
        wktString = wktWriter.write(new MultiLineString(new LineString[]{new LineString(new double[]{30, 10, 10, 30, 40, 40}), new LineString(new double[]{30, 10, 10, 30, 40, 40}), new LineString(new double[]{30, 10, 10, 30, 40, 40})}));
        System.out.println(wktString);
        wktString = wktWriter.write(new MultiPolygon());        
        System.out.println(wktString);
        wktString = wktWriter.write(new MultiPolygon(new Polygon[]{new Polygon(new LineString(), new LineString[]{new LineString(new double[]{30, 10, 10, 30, 40, 40, 30, 10})}), new Polygon(new LineString(), new LineString[]{new LineString(new double[]{30, 10, 10, 30, 40, 40, 30, 10})}), new Polygon(new LineString(), new LineString[]{new LineString(new double[]{30, 10, 10, 30, 40, 40, 30, 10})})}));
        System.out.println(wktString);
        
        WKTReader wktReader = new WKTReader();
        Geometry geom = wktReader.read("POINT (30 30)");
        System.out.println(geom.getClass().toString());
        geom = wktReader.read("LINESTRING (30 10, 10 30, 40 40)");
        System.out.println(geom.getClass().toString());
    }
    
}
