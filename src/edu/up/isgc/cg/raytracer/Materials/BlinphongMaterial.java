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
 * The type Blinphong material.
 */
public class BlinphongMaterial extends Material{
    /**
     * The constant Bronze.
     */
    public static BlinphongMaterial Bronze = new BlinphongMaterial( new Color(	0.714f,	0.4284f,	0.18144f),new Color(0.2125f,	0.1275f,	0.054f),new Color(	0.393548f,	0.271906f,	0.166721f),	25.6);
    /**
     * The constant Rubber.
     */
    public static BlinphongMaterial Rubber = new BlinphongMaterial(new Color(	0.01f,	0.01f,	0.01f), new Color(0.02f,	0.02f,	0.02f),new Color(	0.4f,	0.4f,	0.4f),	10);
    /**
     * The constant CyanPlastic.
     */
    public static BlinphongMaterial CyanPlastic = new BlinphongMaterial(new Color(	0.0f,	0.50980392f,	0.50980392f), new Color(0.0f,0.1f,0.06f),new Color(	0.50196078f,	0.50196078f,	0.50196078f),32);
    /**
     * The constant CyanMirror.
     */
    public static BlinphongMaterial CyanMirror = new BlinphongMaterial(new Color(	0.0f,	0.50980392f,	0.50980392f), new Color(0.0f,0.1f,0.06f),new Color(	0.50196078f,	0.50196078f,	0.50196078f),128,1.0);
    /**
     * The constant RedPlastic.
     */
    public static BlinphongMaterial RedPlastic = new BlinphongMaterial(new Color(	0.5f,	0f,	0f),Color.BLACK,new Color(	0.7f,	0.6f,	0.6f),32);
    /**
     * The constant BlackPlastic.
     */
    public static BlinphongMaterial BlackPlastic = new BlinphongMaterial(new Color(	0.01f,	0.01f,	0.01f),Color.BLACK,new Color(	0.5f,	0.5f,	0.5f),32);
    /**
     * The constant Pearl.
     */
    public static BlinphongMaterial Pearl = new BlinphongMaterial(new Color(	1f,	0.829f,	0.829f),new Color(0.25f,0.20725f,	0.20725f),new Color(	0.296648f,	0.296648f,	0.296648f),11.264);
    /**
     * The constant Chrome.
     */
    public static BlinphongMaterial Chrome = new BlinphongMaterial(new Color(0.4f,0.4f,0.4f),new Color(0.25f,0.25f,0.25f),new Color(0.774597f,0.774597f,0.774597f),76.8);
    private Color ambientColor;
    private Color specularColor;
    private double shininess;

    /**
     * Instantiates a new Blinphong material.
     *
     * @param color         the color
     * @param ambientColor  the ambient color
     * @param specularColor the specular color
     * @param shininess     the shininess
     * @param ambientlight  the ambientlight
     */
    public BlinphongMaterial(Color color, Color ambientColor, Color specularColor, double shininess, Light ambientlight) {
        super(color);
        this.ambientColor = ambientColor;
        this.ambientColor= multiplyColor(ambientlight.getColor(),getAmbientColor(), ambientlight.getIntensity(ambientlight.getPosition()));
        this.specularColor = specularColor;
        this.shininess = shininess;
    }

    /**
     * Instantiates a new Blinphong material.
     *
     * @param color     the color
     * @param shininess the shininess
     */
    public BlinphongMaterial(Color color, double shininess) {
        super(color);
        this.ambientColor = color;
        this.specularColor =color;
        this.shininess = shininess;
    }

    /**
     * Instantiates a new Blinphong material.
     *
     * @param ambientColor  the ambient color
     * @param diffuseColor  the diffuse color
     * @param specularColor the specular color
     * @param shininess     the shininess
     */
    public BlinphongMaterial(Color ambientColor, Color diffuseColor, Color specularColor, double shininess) {
        super(diffuseColor);
        this.ambientColor = ambientColor;
        this.specularColor = specularColor;
        this.shininess=shininess;
    }

    /**
     * Instantiates a new Blinphong material.
     *
     * @param ambientColor  the ambient color
     * @param diffuseColor  the diffuse color
     * @param specularColor the specular color
     * @param shininess     the shininess
     * @param transparency  the transparency
     */
    public BlinphongMaterial(Color ambientColor, Color diffuseColor, Color specularColor, double shininess, double transparency) {
        super(diffuseColor);
        this.ambientColor = ambientColor;
        this.specularColor = specularColor;
        this.shininess=shininess;
        setTransparency(transparency);
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

    /**
     * Bronze blinphong material.
     *
     * @param newColor the new color
     * @return the blinphong material
     */
    public static BlinphongMaterial Bronze(Color newColor) {
        return new BlinphongMaterial( multiplyColor(Bronze.getColor(),newColor,1.0),multiplyColor(Bronze.getSpecularColor(),newColor,1.0),multiplyColor(Bronze.getAmbientColor(),newColor,1.0), Bronze.getShininess());
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
            Vector3D halfwayVec = Vector3D.normalize(Vector3D.add(viewDir,light.getDirection(closestIntersection.getPosition())));
            Double specFactor = pow(max(Vector3D.dotProduct(viewDir, halfwayVec), 0.0), getShininess());
            Color spec = multiplyColor(light.getColor(),getSpecularColor(),specFactor*intensity);
            answer=addColor(answer,addColor(spec,diff));
        }
        //answer=addColor(answer,ambient);
        return answer;
    }
}
