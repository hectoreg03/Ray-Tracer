package edu.up.isgc.cg.raytracer.Lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Materials.Lambert;
import edu.up.isgc.cg.raytracer.Materials.Material;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.tools.Vector3D;
import edu.up.isgc.cg.raytracer.objects.Position3D;

import java.awt.*;

/**
 * The type Light.
 */
public abstract class Light extends Position3D {

    /**
     * The Diffuse intensity.
     */
    protected double diffuseIntensity;
    /**
     * The Specular intensity.
     */
    protected double specularIntensity;
    private Material material;

    /**
     * Instantiates a new Light.
     *
     * @param position         the position
     * @param color            the color
     * @param diffuseIntensity the diffuse intensity
     */
    public Light(Vector3D position, Color color, double diffuseIntensity) {
        super(position);
        setDiffuseIntensity(diffuseIntensity);
        setSpecularIntensity(diffuseIntensity);
        material= new Lambert(color);
    }

    /**
     * Instantiates a new Light.
     *
     * @param position          the position
     * @param color             the color
     * @param diffuseIntensity  the diffuse intensity
     * @param specularIntensity the specular intensity
     * @param ambientIntensity  the ambient intensity
     */
    public Light(Vector3D position, Color color, double diffuseIntensity, double specularIntensity, double ambientIntensity) {
        super(position);
        setDiffuseIntensity(diffuseIntensity);
        setSpecularIntensity(specularIntensity);
        material= new Lambert(color);
    }

    /**
     * Gets intensity.
     *
     * @param pPosition the p position
     * @return the intensity
     */
    public double getIntensity(Vector3D pPosition) {
        return diffuseIntensity;
    }

    /**
     * Sets diffuse intensity.
     *
     * @param diffuseIntensity the diffuse intensity
     */
    public void setDiffuseIntensity(double diffuseIntensity) {
        this.diffuseIntensity = diffuseIntensity;
    }

    /**
     * Gets n dot l.
     *
     * @param intersection the intersection
     * @return the n dot l
     */
    public abstract double getNDotL(Intersection intersection);

    /**
     * Get intersection intersection.
     *
     * @param ray the ray
     * @return the intersection
     */
    public Intersection getIntersection(Ray ray){
        return new Intersection(Vector3D.ZERO(), -1, Vector3D.ZERO(), null);
    }

    /**
     * Shadow ray ray.
     *
     * @param pointposition the pointposition
     * @return the ray
     */
    public abstract Ray shadowRay(Vector3D pointposition);

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.material.getColor();
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.material.setColor(color);
    }

    /**
     * Gets specular intensity.
     *
     * @return the specular intensity
     */
    public double getSpecularIntensity() {
        return specularIntensity;
    }

    /**
     * Sets specular intensity.
     *
     * @param specularIntensity the specular intensity
     */
    public void setSpecularIntensity(double specularIntensity) {
        this.specularIntensity = specularIntensity;
    }

    /**
     * Gets distance.
     *
     * @param pointposition the pointposition
     * @return the distance
     */
    public abstract double getDistance(Vector3D pointposition);

    /**
     * Gets direction.
     *
     * @param pointposition the pointposition
     * @return the direction
     */
    public abstract Vector3D getDirection(Vector3D pointposition);

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
     * Gets diffuse intensity.
     *
     * @return the diffuse intensity
     */
    public double getDiffuseIntensity() {
        return diffuseIntensity;
    }


}
