package edu.up.isgc.cg.raytracer.Lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Materials.Lambert;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

import java.awt.*;

/**
 * The type Omni light.
 */
public class OmniLight extends Light{

    /**
     * Instantiates a new Omni light.
     *
     * @param position  the position
     * @param color     the color
     * @param intensity the intensity
     */
    public OmniLight(Vector3D position, Color color, double intensity) {
        super(position, color, intensity);
    }

    @Override
    public double getNDotL(Intersection intersection) {
        //System.out.println(this.getPosition());
        //System.out.println(intersection.getPosition());
        return 1.0;
    }

    @Override
    public Ray shadowRay(Vector3D pointposition) {
        return new Ray(pointposition,Vector3D.normalize(Vector3D.substract(this.getPosition(),pointposition)));
    }

    @Override
    public double getDistance(Vector3D pointposition) {
        return 0.0;
    }

    @Override
    public Vector3D getDirection(Vector3D pointposition) {
        return Vector3D.ZERO();
    }

    @Override
    public double getIntensity(Vector3D pPosition) {
        return diffuseIntensity;
    }

    /**
     * Clone light.
     *
     * @return the light
     * @throws CloneNotSupportedException the clone not supported exception
     */
    @Override
    public Light clone() throws CloneNotSupportedException
    {
        // Assign the shallow copy to
        // new reference variable t
        Light t = (Light) super.clone();

        // Creating a deep copy for c
        t.setMaterial(new Lambert(getColor()));
        t.setDiffuseIntensity(getDiffuseIntensity());
        t.setSpecularIntensity(getSpecularIntensity());

        // Create a new object for the field c
        // and assign it to shallow copy obtained,
        // to make it a deep copy
        return t;
    }
}