package edu.up.isgc.cg.raytracer;

import edu.up.isgc.cg.raytracer.Lights.Filter;
import edu.up.isgc.cg.raytracer.objects.Object3D;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

/**
 * The type Intersection.
 */
public class Intersection {
    private double distance;
    private Vector3D normal;
    private Vector3D position;
    private Object3D object;
    private Filter passed;

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets distance.
     *
     * @param distance the distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Gets normal.
     *
     * @return the normal
     */
    public Vector3D getNormal() {
        return normal;
    }

    /**
     * Sets normal.
     *
     * @param normal the normal
     */
    public void setNormal(Vector3D normal) {
        this.normal = normal;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Vector3D getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(Vector3D position) {
        this.position = position;
    }

    /**
     * Gets object.
     *
     * @return the object
     */
    public Object3D getObject() {
        return object;
    }

    /**
     * Sets object.
     *
     * @param object the object
     */
    public void setObject(Object3D object) {
        this.object = object;
    }

    /**
     * Gets passed.
     *
     * @return the passed
     */
    public Filter getPassed() {
        return passed;
    }

    /**
     * Sets passed.
     *
     * @param passed the passed
     */
    public void setPassed(Filter passed) {
        this.passed = passed;
    }

    /**
     * Instantiates a new Intersection.
     *
     * @param position the position
     * @param distance the distance
     * @param normal   the normal
     * @param object   the object
     */
    public Intersection(Vector3D position, double distance, Vector3D normal, Object3D object) {
        setPosition(position);
        setDistance(distance);
        setNormal(normal);
        setObject(object);
        setPassed(null);
    }
}
