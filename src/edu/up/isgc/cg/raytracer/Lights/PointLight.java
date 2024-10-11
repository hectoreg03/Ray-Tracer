package edu.up.isgc.cg.raytracer.Lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

import java.awt.*;

/**
 * The type Point light.
 */
public class PointLight extends Light{

    /**
     * Instantiates a new Point light.
     *
     * @param position  the position
     * @param color     the color
     * @param intensity the intensity
     */
    public PointLight(Vector3D position, Color color, double intensity) {
        super(position, color, intensity);
    }

    @Override
    public double getNDotL(Intersection intersection) {
        return Math.max(Vector3D.dotProduct(intersection.getNormal(),getDirection(intersection.getPosition())), 0.0);
    }

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
