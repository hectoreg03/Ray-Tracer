package edu.up.isgc.cg.raytracer.Materials;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Lights.Light;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

import java.awt.*;
import java.util.AbstractList;

/**
 * The type Material.
 */
public abstract class  Material {
    private Color color;
    private Double transparency;

    /**
     * Instantiates a new Material.
     *
     * @param color the color
     */
    public Material(Color color) {
        this.color = color;
        this.transparency=0.0;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets transparency.
     *
     * @return the transparency
     */
    public Double getTransparency() {
        return transparency;
    }

    /**
     * Sets transparency.
     *
     * @param transparency the transparency
     */
    public void setTransparency(Double transparency) {
        this.transparency = transparency;
    }

    /**
     * Sets ambient color.
     *
     * @param ambientlight the ambientlight
     */
    public abstract void setAmbientColor(Light ambientlight);

    /**
     * Gets shininess.
     *
     * @return the shininess
     */
    public abstract Double getShininess();

    /**
     * Calculate color color.
     *
     * @param closestIntersection the closest intersection
     * @param lightList           the light list
     * @param viewerPosition      the viewer position
     * @return the color
     */
    public abstract Color calculateColor(Intersection closestIntersection, AbstractList<Light> lightList, Vector3D viewerPosition);
}
