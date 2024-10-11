package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Materials.BlinphongMaterial;
import edu.up.isgc.cg.raytracer.Materials.Material;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

import java.awt.*;

/**
 * The type Sphere.
 */
public class Sphere extends Object3D{
    private double radius;

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets radius.
     *
     * @param radius the radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Instantiates a new Sphere.
     *
     * @param position the position
     * @param radius   the radius
     * @param color    the color
     */
    public Sphere(Vector3D position, double radius, Color color) {
        super(position, new BlinphongMaterial(color,32.0));
        setRadius(radius);
    }

    /**
     * Instantiates a new Sphere.
     *
     * @param position  the position
     * @param radius    the radius
     * @param nmaterial the nmaterial
     */
    public Sphere(Vector3D position, double radius, Material nmaterial) {
        super(position, nmaterial);
        setRadius(radius);
    }

    @Override
    public Intersection getIntersection(Ray ray, boolean invertedNormal) {
        Vector3D L = Vector3D.substract(ray.getOrigin(), getPosition());
        double tca = Vector3D.dotProduct(ray.getDirection(), L);
        double L2 = Math.pow(Vector3D.magnitude(L), 2);
        //Intersection
        double d2 = Math.pow(tca, 2) - L2 + Math.pow(getRadius(), 2);
        if(d2 >= 0){
            double d = Math.sqrt(d2);
            double t0 = -tca + d;
            double t1 = -tca - d;

            double distance = Math.min(t0, t1);
            if(invertedNormal)
                distance= Math.max(t0,t1);

            Vector3D position = Vector3D.add(ray.getOrigin(), Vector3D.scalarMultiplication(ray.getDirection(), distance));
            Vector3D normal = Vector3D.normalize(Vector3D.substract(position, getPosition()));
            return new Intersection(position, distance, normal, this);
        }

        return null;
    }


}
