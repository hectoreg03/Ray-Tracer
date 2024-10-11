package edu.up.isgc.cg.raytracer.Materials;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Lights.Light;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

import java.awt.*;
import java.util.AbstractList;

import static edu.up.isgc.cg.raytracer.tools.ColorOperations.addColor;
import static edu.up.isgc.cg.raytracer.tools.ColorOperations.multiplyColor;

/**
 * The type Lambert.
 */
public class Lambert extends Material{
    /**
     * Instantiates a new Lambert.
     *
     * @param color the color
     */
    public Lambert(Color color) {
        super(color);
    }
    @Override
    public void setAmbientColor(Light ambientlight){
       return;
    }

    @Override
    public Double getShininess() {
        return 0.0;
    }

    @Override
    public Color calculateColor(Intersection closestIntersection, AbstractList<Light> lightList, Vector3D viewerPosition){
        Color answer = Color.BLACK;
        for (Light light:lightList) {
            double nDotL = light.getNDotL(closestIntersection);
            double intensity = light.getIntensity(closestIntersection.getPosition()) * nDotL;
            Color aux = multiplyColor(light.getColor(),getColor(),intensity);
            answer= addColor(answer,aux);
        }
        return answer;
    }
}
