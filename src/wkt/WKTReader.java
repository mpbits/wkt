/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wkt;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Point;
import com.sinergise.geometry.Polygon;
import java.util.Arrays;

/**
 *
 * @author apac
 */
public class WKTReader {
    
    /**
     * Transforms the input WKT-formatted String into Geometry object
     */
    public Geometry read(String wktString) {
        //TODO: Implement this
        Geometry geom = null;
        String string = "";
        String[] pointsArray = null;
        String wktStringArray[] = wktString.split(" ", 2);
        
        switch (wktStringArray[0]){
            case "POINT":
                int[] points = PointToArray(wktStringArray[1]);
                geom = new Point(points[0], points[1]);
                break;
            case "LINESTRING":
                double[] linestring = LineStringToArray(wktStringArray[1]);
                geom = new LineString(linestring);
                break;

        }

        
        return geom;  
    }
    
    private int[] PointToArray(String point){
        String string = point.replace("(", "").replace(")", "");
        String[] pointsArray = string.trim().split(" ");
        int[] points = new int[]{Integer.parseInt(pointsArray[0]), Integer.parseInt(pointsArray[1])};       
        return points;
    }
    
    private double[] LineStringToArray(String lineString){
        String string = lineString.replace("(", "").replace(")", "").replace(" ", ",").replace(",,", ",");
        String[] pointsArray = string.split(",");
        double[] points = new double[pointsArray.length];
        for(int i=0; i<points.length; i++){
            points[i] = Double.parseDouble(pointsArray[i]);
        }    
        return points;
    }
}
