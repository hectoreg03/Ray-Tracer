package edu.up.isgc.cg.raytracer.tools;

import java.awt.*;
import java.util.List;

/**
 * The type Color operations.
 */
public abstract class ColorOperations {
    /**
     * Clamp float.
     *
     * @param value the value
     * @param min   the min
     * @param max   the max
     * @return the float
     */
    public static float clamp(double value, double min, double max){
        if(value < min){
            return (float)min;
        }
        if(value > max){
            return (float)max;
        }
        return (float)value;
    }

    /**
     * Add color color.
     *
     * @param original   the original
     * @param otherColor the other color
     * @return the color
     */
    public static Color addColor(Color original, Color otherColor){
        float red = clamp((original.getRed() / 255.0)+(otherColor.getRed() / 255.0), 0, 1);
        float green = clamp((original.getGreen() / 255.0)+(otherColor.getGreen() / 255.0), 0, 1);
        float blue = clamp((original.getBlue() / 255.0)+(otherColor.getBlue() / 255.0), 0, 1);
        return new Color(red, green, blue);
    }

    /**
     * Substract color color.
     *
     * @param original   the original
     * @param otherColor the other color
     * @return the color
     */
    public static Color substractColor(Color original, Color otherColor){
        return addColor(original,scaleColor(otherColor,-1));
    }

    /**
     * Average color color.
     *
     * @param colors      the colors
     * @param intensities the intensities
     * @return the color
     */
    public static Color averageColor(List<Color> colors, List<Double> intensities){
        double []rgb={0.0,0.0,0.0};
        double totalIntensities=0;
        for (int i=0; i<colors.size(); i++) {
            Color ac = colors.get(i);
            rgb[0]+=ac.getRed()*intensities.get(i);
            rgb[1]+=ac.getGreen()*intensities.get(i);
            rgb[2]+=ac.getBlue()*intensities.get(i);
            totalIntensities+=intensities.get(i);
        }
        for( int i=0; i<3; i++){
            rgb[i]/= totalIntensities;
        }
        return new Color((int)rgb[0],(int)rgb[1],(int)rgb[2]);
    }

    /**
     * Scale color color.
     *
     * @param a     the a
     * @param value the value
     * @return the color
     */
    public static Color scaleColor(Color a, double value){
        return new Color( clamp((a.getRed()*value)/255f,0,255),clamp((a.getGreen()*value)/255f,0,255),clamp((a.getBlue()*value)/255f,0,255));
    }

    /**
     * Inverse color color.
     *
     * @param a the a
     * @return the color
     */
    public static Color inverseColor(Color a){
        return new Color((255-a.getRed()), (255-a.getGreen()), (255-a.getBlue()));
    }

    /**
     * Multiply color color.
     *
     * @param colorA    the color a
     * @param colorB    the color b
     * @param intensity the intensity
     * @return the color
     */
    public static Color multiplyColor(Color colorA, Color colorB, double intensity){
        double[] lightColors = new double[]{colorA.getRed() / 255.0, colorA.getGreen() / 255.0, colorA.getBlue() / 255.0};
        double[] objColors = new double[]{colorB.getRed() / 255.0, colorB.getGreen() / 255.0, colorB.getBlue() / 255.0};
        for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
            objColors[colorIndex] *= intensity * lightColors[colorIndex];
        }
        return new Color(clamp(objColors[0], 0, 1),clamp(objColors[1], 0, 1),clamp(objColors[2], 0, 1));
    }

}
