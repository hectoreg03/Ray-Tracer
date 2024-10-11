package edu.up.isgc.cg.raytracer;

import edu.up.isgc.cg.raytracer.Lights.Light;
import edu.up.isgc.cg.raytracer.objects.Camera;
import edu.up.isgc.cg.raytracer.objects.Object3D;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Scene.
 */
public class Scene {

    private Camera camera;
    private List<Object3D> objects;
    private List<Light> lights;

    /**
     * Instantiates a new Scene.
     */
    public Scene() {
        setObjects(new ArrayList<>());
        setLights(new ArrayList<>());
    }

    /**
     * Gets camera.
     *
     * @return the camera
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Sets camera.
     *
     * @param camera the camera
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Add object.
     *
     * @param object the object
     */
    public void addObject(Object3D object){
        getObjects().add(object);
    }

    /**
     * Gets objects.
     *
     * @return the objects
     */
    public List<Object3D> getObjects() {
        if(objects == null){
            objects = new ArrayList<>();
        }
        return objects;
    }

    /**
     * Sets objects.
     *
     * @param objects the objects
     */
    public void setObjects(List<Object3D> objects) {
        this.objects = objects;
    }

    /**
     * Gets lights.
     *
     * @return the lights
     */
    public List<Light> getLights() {
        if(lights == null){
            setLights(new ArrayList<>());
        }
        return lights;
    }

    /**
     * Sets lights.
     *
     * @param lights the lights
     */
    public void setLights(List<Light> lights) {
        this.lights = lights;
    }

    /**
     * Add light.
     *
     * @param light the light
     */
    public void addLight(Light light){
        getLights().add(light);
    }
}

