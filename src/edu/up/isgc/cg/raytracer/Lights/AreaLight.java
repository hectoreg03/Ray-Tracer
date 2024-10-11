package edu.up.isgc.cg.raytracer.Lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

import java.awt.*;

/**
 * The type Area light.
 */
public class AreaLight extends Light{
    private Vector3D[]positions;

    /**
     * Instantiates a new Area light.
     *
     * @param position  the position
     * @param color     the color
     * @param intensity the intensity
     */
    public AreaLight(Vector3D position, Color color, double intensity) {
        super(position, color, intensity);
    }

    /**
     * Instantiates a new Area light.
     *
     * @param position1 the position 1
     * @param position2 the position 2
     * @param position3 the position 3
     * @param color     the color
     * @param intensity the intensity
     */
    public AreaLight(Vector3D position1, Vector3D position2, Vector3D position3, Color color, double intensity) {
        super(position1,color,intensity);
        setPosition(Vector3D.add(position2,Vector3D.scalarMultiplication(Vector3D.substract(position3,position2),0.5)));
        positions= new Vector3D[4];
        positions[0]=position1;
        positions[1]=position2;
        positions[2]=position3;
        positions[3]=Vector3D.add(position1,Vector3D.add(Vector3D.substract(position2,position1),Vector3D.substract(position3,position1)));
    }

    /**
     * It returns the modified Product point between the normal of the intersection and one of the vertex of the plane
     * @param intersection
     * @return
     */
    private double indGetNDotL(Intersection intersection, int a) {
        return Math.max(Vector3D.dotProduct(intersection.getNormal(),Vector3D.normalize(Vector3D.substract(positions[a],intersection.getPosition()))), 0.0);
    }


    /**
     *
     * @param intersection
     * @return
     */

    @Override
    public double getNDotL(Intersection intersection) {
        double ans =0;
        for( int i=0; i<4; i++){
            ans+=indGetNDotL(intersection,i);
        }
        ans/=4.0;
        return ans;
    }

    /**
     *
     * @param pointposition
     * @return
     */
    @Override
    public Ray shadowRay(Vector3D pointposition) {
        return new Ray(pointposition,Vector3D.normalize(Vector3D.substract(this.getPosition(),pointposition)));
    }

    @Override
    public double getDistance(Vector3D pointposition) {
        return Vector3D.magnitude(Vector3D.substract(getPosition(),pointposition));
    }

    @Override
    public Vector3D getDirection(Vector3D pointposition) {
        return Vector3D.normalize( Vector3D.substract(this.getPosition(),pointposition));
    }

    @Override
    public double getIntensity(Vector3D pPosition) {
        double dist= getDistance(pPosition);
        dist=Math.pow(dist,2);
        if(dist!=0)return  diffuseIntensity /dist;
        else return diffuseIntensity;
    }

}
