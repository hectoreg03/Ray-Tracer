package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.tools.Vector3D;

/**
 * The type Position 3 d.
 */
public abstract class Position3D {
    private Vector3D position;
    private Vector3D rotation;

    /**
     * Instantiates a new Position 3 d.
     *
     * @param position the position
     */
    public Position3D(Vector3D position) {
        setPosition(position);
        setRotation(Vector3D.ZERO());
    }

    /**
     * Instantiates a new Position 3 d.
     *
     * @param position the position
     * @param rotation the rotation
     */
    public Position3D(Vector3D position, Vector3D rotation) {
        setPosition(position);
        setRotation(rotation);
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
     * Gets rotation.
     *
     * @return the rotation
     */
    public Vector3D getRotation() {
        return rotation;
    }

    /**
     * Sets rotation.
     *
     * @param rotation the rotation
     */
    public void setRotation(Vector3D rotation) {
        this.rotation = rotation;
    }
}
