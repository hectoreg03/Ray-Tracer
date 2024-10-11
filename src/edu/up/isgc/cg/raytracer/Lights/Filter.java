package edu.up.isgc.cg.raytracer.Lights;

import java.awt.*;

/**
 * The type Filter.
 */
public class Filter {
    private Double transparency;
    private Color color;

    /**
     * Instantiates a new Filter.
     *
     * @param transparency the transparency
     * @param color        the color
     */
    public Filter(Double transparency, Color color) {
       setColor(color);
       setTransparency(transparency);
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
}
