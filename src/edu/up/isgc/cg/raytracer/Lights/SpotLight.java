package edu.up.isgc.cg.raytracer.Lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

import java.awt.*;

/**
 * The type Spot light.
 */
public class SpotLight extends Light{
    private double angleFalloff;
    private Vector3D principalDirection;

    /**
     * Instantiates a new Spot light.
     *
     * @param position           the position
     * @param color              the color
     * @param intensity          the intensity
     * @param angleFalloff       the angle falloff
     * @param principalDirection the principal direction
     */
    public SpotLight(Vector3D position, Color color, double intensity, double angleFalloff, Vector3D principalDirection) {
        super(position, color, intensity);
        setAngleFalloff(angleFalloff);
        setPrincipalDirection(Vector3D.normalize(principalDirection));
    }

    /**
     * Gets angle falloff.
     *
     * @return the angle falloff
     */
    public double getAngleFalloff() {
        return angleFalloff;
    }

    /**
     * Sets angle falloff.
     *
     * @param angleFalloff the angle falloff
     */
    public void setAngleFalloff(double angleFalloff) {
        this.angleFalloff = angleFalloff;
    }

    /**
     * Gets principal direction.
     *
     * @return the principal direction
     */
    public Vector3D getPrincipalDirection() {
        return principalDirection;
    }

    /**
     * Sets principal direction.
     *
     * @param principalDirection the principal direction
     */
    public void setPrincipalDirection(Vector3D principalDirection) {
        this.principalDirection = principalDirection;
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
    public double getIntensity(Vector3D pPosition) {
        double dist= getDistance(pPosition);
        dist=Math.pow(dist,2);
        double angle = Vector3D.anglecos(principalDirection,Vector3D.normalize(Vector3D.substract(pPosition,getPosition())));
        if(dist!=0)return  diffuseIntensity /dist;
        else return diffuseIntensity;
    }
    @Override
    public Vector3D getDirection(Vector3D pointposition) {
        return Vector3D.normalize( Vector3D.substract(this.getPosition(),pointposition));
    }
}
