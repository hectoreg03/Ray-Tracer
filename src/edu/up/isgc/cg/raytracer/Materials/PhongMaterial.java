package edu.up.isgc.cg.raytracer.Materials;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Lights.Light;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

import java.awt.*;
import java.util.AbstractList;

import static edu.up.isgc.cg.raytracer.tools.ColorOperations.addColor;
import static edu.up.isgc.cg.raytracer.tools.ColorOperations.multiplyColor;
import static java.lang.Math.max;
import static java.lang.Math.pow;

/**
 * The type Phong material.
 */
public class PhongMaterial extends Material{
    /**
     * The constant Bronze.
     */
    public static PhongMaterial Bronze = new PhongMaterial(new Color(0.2125f,	0.1275f,	0.054f), new Color(	0.714f,	0.4284f,	0.18144f),new Color(	0.393548f,	0.271906f,	0.166721f),	6.4);
    private Color ambientColor;
    private Color specularColor;
    private double shininess;

    /**
     * Instantiates a new Phong material.
     *
     * @param color         the color
     * @param ambientColor  the ambient color
     * @param specularColor the specular color
     * @param shininess     the shininess
     * @param ambientlight  the ambientlight
     */
    public PhongMaterial(Color color, Color ambientColor, Color specularColor, double shininess, Light ambientlight) {
        super(color);
        this.ambientColor = ambientColor;
        this.ambientColor= multiplyColor(ambientlight.getColor(),getAmbientColor(), ambientlight.getIntensity(ambientlight.getPosition()));
        this.specularColor = specularColor;
        this.shininess = shininess;
    }

    /**
     * Instantiates a new Phong material.
     *
     * @param color     the color
     * @param shininess the shininess
     */
    public PhongMaterial(Color color, double shininess) {
        super(color);
        this.ambientColor = color;
        this.specularColor =color;
        this.shininess = shininess;
    }

    /**
     * Instantiates a new Phong material.
     *
     * @param ambientColor  the ambient color
     * @param diffuseColor  the diffuse color
     * @param specularColor the specular color
     * @param shininess     the shininess
     */
    public PhongMaterial(Color ambientColor, Color diffuseColor, Color specularColor, double shininess) {
        super(diffuseColor);
        this.ambientColor = ambientColor;
        this.specularColor = specularColor;
        this.shininess = shininess;
    }

    /**
     * Gets ambient color.
     *
     * @return the ambient color
     */
    public Color getAmbientColor() {
        return ambientColor;
    }

    /**
     * Gets specular color.
     *
     * @return the specular color
     */
    public Color getSpecularColor() {
        return specularColor;
    }

    public Double getShininess() {
        return shininess;
    }

    /**
     * Sets ambient color.
     *
     * @param ambientColor the ambient color
     */
    public void setAmbientColor(Color ambientColor) {
        this.ambientColor = ambientColor;
    }
    public void setAmbientColor(Light ambientlight) {
        this.ambientColor= multiplyColor(ambientlight.getColor(),getAmbientColor(), ambientlight.getIntensity(ambientlight.getPosition()));
    }

    /**
     * Sets specular color.
     *
     * @param specularColor the specular color
     */
    public void setSpecularColor(Color specularColor) {
        this.specularColor = specularColor;
    }

    /**
     * Sets shininess.
     *
     * @param shininess the shininess
     */
    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    @Override
    public Color calculateColor(Intersection closestIntersection, AbstractList<Light> lightList, Vector3D viewerPosition){
        Color answer = Color.BLACK;
        Color ambient = getAmbientColor();
        for (Light light:       lightList) {
            //Difuse
            double nDotL = light.getNDotL(closestIntersection);
            double intensity = light.getIntensity(closestIntersection.getPosition()) * nDotL;
            Color diff = multiplyColor(light.getColor(),getColor(),intensity);
            //Specular
            Vector3D viewDir = Vector3D.normalize(Vector3D.substract(viewerPosition,closestIntersection.getPosition()));
            Vector3D invLightDirection =Vector3D.scalarMultiplication(light.getDirection(closestIntersection.getPosition()),-1);
            Vector3D reflectDir = Vector3D.reflection(invLightDirection,closestIntersection.getNormal());
            Double specFactor = pow(max(Vector3D.dotProduct(viewDir, reflectDir), 0.0), getShininess());
            Color spec = multiplyColor(light.getColor(),getSpecularColor(),specFactor*intensity);
            answer=addColor(answer,addColor(spec,diff));
        }
        answer=addColor(answer,ambient);
        return answer;
    }
}
