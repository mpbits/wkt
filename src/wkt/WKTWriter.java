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
import java.util.Arrays;

//import com.sinergise.geometry.LineString;


/**
 *
 * @author apac
 */
public class WKTWriter {
    /**
     * Transforms the input Geometry object into WKT-formatted String. e.g.
     * <pre><code>
     * new WKTWriter().write(new LineString(new double[]{30, 10, 10, 30, 40, 40}));
     * //returns "LINESTRING (30 10, 10 30, 40 40)"
     * </code></pre>
     * @param geom
     * @return 
     */
    public String write(Geometry geom) {
        //TODO: Implement this
        //return write(new GeometryCollection<Geometry>(new Geometry[]{new Point(4,6), new LineString(new double[] {4,6,7,10})}));
        
        String wktString = "";
        String geometryName = geom.getClass().getSimpleName().toUpperCase();
        
        if (geom.isEmpty()){
           return geometryName + " EMPTY";
        }
        
        switch (geometryName){
            case "POINT":
                Point point = (Point)geom;
                wktString = geometryName + " "  + PointToString(point);
                break;
            case "LINESTRING":
                LineString lineString = (LineString)geom;
                wktString = geometryName + " "  + LineStringToString(lineString);
                break;
            case "POLYGON":
                // Vaša implementacija polygona je v neskladju z definicijo iz Wikipedie
                // Spodnji izpis je po wikipediji
                Polygon polygon = (Polygon)geom;
                wktString = geometryName + " "  + PolygonToString(polygon);
                break;
            case "GEOMETRYCOLLECTION":
                GeometryCollection geometryCollection = (GeometryCollection)geom;
                wktString = (GeometryCollectionToString(geometryCollection));
                break;
            case "MULTIPOINT":
                MultiPoint multiPoint = (MultiPoint)geom;
                wktString = (MultiPointToString(multiPoint));
                break;
            case "MULTILINESTRING":
                MultiLineString multiLineString = (MultiLineString)geom;
                wktString = (MultiLineStringToString(multiLineString));
                break;
            case "MULTIPOLYGON":
                MultiPolygon multiPolygon = (MultiPolygon)geom;
                wktString = (MultiPolygonToString(multiPolygon));
                break;
        }

            
        return wktString;
    }
    
    private String PointToString(Point point){
        String string = (int)point.getX() + " " + (int)point.getY();
        return "(" + string + ")";
    }
        
    private String LineStringToString(LineString lineString){
        int numCoords = lineString.getNumCoords();
        String string = "";
        for(int i=0; i<numCoords; i++){
            string += (int)lineString.getX(i) + " " + (int)lineString.getY(i) + ", ";
        }
        string = string.substring(0, string.length() - 2);
        return "(" + string + ")";
    }
    
    private String PolygonToString(Polygon polygon){
        LineString outer = polygon.getOuter();
        int numHoles = polygon.getNumHoles();                
        String string = "";
        String outerString = "";
        if (!outer.isEmpty()){
            outerString = LineStringToString(outer) + ", ";
        }
        for(int i=0; i<numHoles; i++){
            string += LineStringToString(polygon.getHole(i)) + ",";
        }
        string = string.substring(0, string.length() - 1);
        return "(" + outerString + string + ")";
    }
    
    private String GeometryCollectionToString(GeometryCollection geometryCollection){
        String string = geometryCollection.getClass().getSimpleName().toUpperCase() + " (";
        for (int i=0; i<geometryCollection.size(); i++){
            string += this.write(geometryCollection.get(i)) + ", ";
        }
        string = string.substring(0, string.length() - 2) + ")";
        return string;
    }
    
    private String MultiPointToString(MultiPoint multiPoint){
        String string = multiPoint.getClass().getSimpleName().toUpperCase() + " (";
        for (int i=0; i<multiPoint.size(); i++){
            string += PointToString(multiPoint.get(i)) + ", ";
        }
        string = string.substring(0, string.length() - 2) + ")";
        return string;
    }
    
    private String MultiLineStringToString(MultiLineString multiLineString){
        String string = multiLineString.getClass().getSimpleName().toUpperCase() + " (";
        for (int i=0; i<multiLineString.size(); i++){
            string += LineStringToString(multiLineString.get(i)) + ", ";
        }
        string = string.substring(0, string.length() - 2) + ")";
        return string;
    }
    
    private String MultiPolygonToString(MultiPolygon multiPolygon){
        String string = multiPolygon.getClass().getSimpleName().toUpperCase() + " (";
        for (int i=0; i<multiPolygon.size(); i++){
            string += PolygonToString(multiPolygon.get(i)) + ", ";
        }
        string = string.substring(0, string.length() - 2) + ")";
        return string;
    }
}
