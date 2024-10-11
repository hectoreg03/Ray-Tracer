package edu.up.isgc.cg.raytracer;

import edu.up.isgc.cg.raytracer.tools.Vector3D;

/**
 * The type Shade state.
 */
public class ShadeState {
    private Intersection intersection;
    private Vector3D pvRayDirection;
    private Double intensity;
    private Boolean insideObj;

    /**
     * Instantiates a new Shade state.
     *
     * @param intersection   the intersection
     * @param pvRayDirection the pv ray direction
     * @param intensity      the intensity
     * @param insideObj      the inside obj
     */
    public ShadeState(Intersection intersection, Vector3D pvRayDirection, Double intensity, boolean insideObj) {
        setIntensity(intensity);
        setIntersection(intersection);
        setPvRayDirection(pvRayDirection);
        setInsideObj(insideObj);
    }

    /**
     * Gets intersection.
     *
     * @return the intersection
     */
    public Intersection getIntersection() {
        return intersection;
    }

    /**
     * Sets intersection.
     *
     * @param intersection the intersection
     */
    public void setIntersection(Intersection intersection) {
        this.intersection = intersection;
    }

    /**
     * Gets pv ray direction.
     *
     * @return the pv ray direction
     */
    public Vector3D getPvRayDirection() {
        return pvRayDirection;
    }

    /**
     * Sets pv ray direction.
     *
     * @param pvRayDirection the pv ray direction
     */
    public void setPvRayDirection(Vector3D pvRayDirection) {
        this.pvRayDirection = pvRayDirection;
    }

    /**
     * Gets intensity.
     *
     * @return the intensity
     */
    public Double getIntensity() {
        return intensity;
    }

    /**
     * Sets intensity.
     *
     * @param intensity the intensity
     */
    public void setIntensity(Double intensity) {
        this.intensity = intensity;
    }

    /**
     * Gets inside obj.
     *
     * @return the inside obj
     */
    public Boolean getInsideObj() {
        return insideObj;
    }

    /**
     * Sets inside obj.
     *
     * @param insideObj the inside obj
     */
    public void setInsideObj(Boolean insideObj) {
        this.insideObj = insideObj;
    }
}
