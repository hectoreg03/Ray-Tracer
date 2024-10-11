package edu.up.isgc.cg.raytracer.Lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

import java.awt.*;

/**
 * The type Directional light.
 */
public class DirectionalLight extends Light{
    private Vector3D direction;

    /**
     * Instantiates a new Directional light.
     *
     * @param direction the direction
     * @param color     the color
     * @param intensity the intensity
     */
    public DirectionalLight(Vector3D direction, Color color, double intensity) {
        super(Vector3D.ZERO(), color, intensity);
        setDirection(Vector3D.normalize(direction));
    }
    @Override
    public Vector3D getDirection(Vector3D pointposition) {
        return Vector3D.scalarMultiplication(direction,-1);
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }

    @Override
    public double getNDotL(Intersection intersection) {
        return Math.max(Vector3D.dotProduct(intersection.getNormal(), getDirection(null)), 0.0);
    }

    @Override
    public Ray shadowRay(Vector3D pointposition) {
        return new Ray(pointposition, getDirection(null));
    }
    @Override
    public double getIntensity(Vector3D pPosition) {
        return super.getIntensity(pPosition);
    }
    @Override
    public double getDistance(Vector3D pointposition) {
        return Double.POSITIVE_INFINITY;
    }

}
