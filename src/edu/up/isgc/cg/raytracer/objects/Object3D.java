package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Materials.Material;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

import java.awt.*;

/**
 * The type Object 3 d.
 */
public abstract class Object3D extends Position3D implements IIntersectable{
    private Material material;
    private Vector3D position;
    private Vector3D rotation;
    private Vector3D scale;

    /**
     * Instantiates a new Object 3 d.
     *
     * @param position  the position
     * @param nmaterial the nmaterial
     */
    public Object3D(Vector3D position, Material nmaterial) {
        super(position);
        setMaterial(nmaterial);
        setRotation(Vector3D.ZERO());
        setScale(new Vector3D(1,1,1));
    }

    /**
     * Instantiates a new Object 3 d.
     *
     * @param position  the position
     * @param rotation  the rotation
     * @param scale     the scale
     * @param nmaterial the nmaterial
     */
    public Object3D(Vector3D position, Vector3D rotation, Vector3D scale, Material nmaterial) {
        super(position,rotation);
        setMaterial(nmaterial);
        setScale(scale);
    }

    /**
     * Instantiates a new Object 3 d.
     *
     * @param position  the position
     * @param rotation  the rotation
     * @param nmaterial the nmaterial
     */
    public Object3D(Vector3D position, Vector3D rotation, Material nmaterial) {
        super(position,rotation);
        setMaterial(nmaterial);
        setScale(new Vector3D(1,1,1));
    }

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Vector3D getRotation() {
        return rotation;
    }

    public void setRotation(Vector3D rotation) {
        this.rotation = rotation;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return material.getColor();
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
       material.setColor(color);
    }

    /**
     * Gets material.
     *
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets material.
     *
     * @param material the material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Gets scale.
     *
     * @return the scale
     */
    public Vector3D getScale() {
        return scale;
    }

    /**
     * Sets scale.
     *
     * @param scale the scale
     */
    public void setScale(Vector3D scale) {
        this.scale = scale;
    }
}

