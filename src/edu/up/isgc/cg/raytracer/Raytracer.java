package edu.up.isgc.cg.raytracer;


import edu.up.isgc.cg.raytracer.Lights.*;
import edu.up.isgc.cg.raytracer.Materials.BlinphongMaterial;
import edu.up.isgc.cg.raytracer.Materials.Lambert;
import edu.up.isgc.cg.raytracer.Materials.Material;
import edu.up.isgc.cg.raytracer.objects.*;
import edu.up.isgc.cg.raytracer.tools.OBJReader;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static edu.up.isgc.cg.raytracer.tools.ColorOperations.*;

/**
 * The type Raytracer.
 */
public class Raytracer {
    /**
     * The constant MAX_RECURSION.
     */
    public final static int MAX_RECURSION =5;
    /**
     * The constant MIN_EFFECT.
     */
    public final static Double MIN_EFFECT =0.000001;
    /**
     * The constant EPSILON.
     */
    public final static Double EPSILON=0.00001;
    /**
     * The constant ambientLight.
     */
    public static Light ambientLight;
    /**
     * The Positions to raytrace.
     */
    public static Vector3D[][] positionsToRaytrace;
    /**
     * The Images.
     */
    public static BufferedImage[] images;
    /**
     * The constant cameraZ.
     */
    public static double cameraZ;
    /**
     * The Near far planes.
     */
    public static double[] nearFarPlanes;
    private static Scene generatescene1(){
        Scene scene01 = new Scene();
        scene01.setCamera(new Camera(new Vector3D(0, 0, -4), 60, 60, 800, 800, 0.6, 50.0));
        scene01.addLight(new DirectionalLight(new Vector3D(0.0,  0, 1), Color.white, 1.1));
        scene01.addObject(new Sphere(new Vector3D(3.0, -4.0, 5.0), 2.0, Color.RED));
        scene01.addObject(new Sphere(new Vector3D(3.0, -4.0, 2.0), 0.5, Color.BLUE));
        scene01.addObject(new Model3D(new Vector3D(-1,-1,3),
                new Triangle[]{
                        new Triangle(Vector3D.ZERO(), new Vector3D(1,0,0), new Vector3D(1,-1,0)),
                        new Triangle(Vector3D.ZERO(), new Vector3D(1,-1,0), new Vector3D(0,-1,0))},
                Color.GREEN));
        scene01.addObject(OBJReader.getModel3D("SmallTeapot.obj", new Vector3D(0, -2.5, 1), new Vector3D(1,  0, 0), new Lambert(Color.CYAN)));
        scene01.addObject(OBJReader.getModel3D("Ring.obj", new Vector3D(-2.0, 0, 1), Color.RED));
        return scene01;
    }
    private static Scene generatescene2(){
        Scene scene02 = new Scene();
        scene02.setCamera(new Camera(new Vector3D(0, 0, -4), 60, 60, 800, 800, 0.6, 50.0, 25.0));
        scene02.addLight(new PointLight(new Vector3D(0.0, 1.0, 0.0), Color.WHITE, 0.9));
        scene02.addLight(new DirectionalLight(new Vector3D(0.0, 0.0, 1.0), Color.WHITE, 1.1));
        scene02.addLight(new DirectionalLight(new Vector3D(0.0, -1.0, 0.0), Color.RED, 1.1));
        scene02.addObject(new Sphere(new Vector3D(0.0, 1.0, 5.0), 0.5, new Lambert(Color.RED)));
        scene02.addObject(new Sphere(new Vector3D(0.5, 1.0, 4.5), 0.25, new Lambert(new Color(200, 255, 0))));
        scene02.addObject(new Sphere(new Vector3D(0.35, 1.0, 4.5), 0.3, new Lambert(Color.BLUE)));
        scene02.addObject(new Sphere(new Vector3D(4.85, 1.0, 4.5), 0.3, new Lambert(Color.PINK)));
        scene02.addObject(new Sphere(new Vector3D(2.85, 1.0, 304.5), 0.5, new Lambert(Color.BLUE)));
        scene02.addObject(OBJReader.getModel3D("Cube.obj", new Vector3D(0f, -2.5, 1.0), new Lambert(Color.white)));
        scene02.addObject(OBJReader.getModel3D("CubeQuad.obj", new Vector3D(-3.0, -2.5, 3.0), BlinphongMaterial.Bronze(Color.GREEN)));
        scene02.addObject(OBJReader.getModel3D("SmallTeapot.obj", new Vector3D(2.0, -1.0, 1.5), BlinphongMaterial.Bronze));
        scene02.addObject(OBJReader.getModel3D("Ring.obj", new Vector3D(2.0, -1.0, 1.5), new Lambert(Color.BLUE)));
        return scene02;
    }
    private static Scene generatescene3(){
        Scene scene03 = new Scene();
        scene03.setCamera(new Camera(new Vector3D(0, 0, -4), 60, 60, 800, 800, 0.6, 50.0, 25.0));
        scene03.addLight(new PointLight(new Vector3D(0.0, 1.0, 0.0), Color.WHITE, 15));
        scene03.addObject(OBJReader.getModel3D("SmallTeapot.obj", new Vector3D(2.0, -1.0, 1.5), BlinphongMaterial.Bronze));
        return scene03;
    }

    private static Scene generatescene4(){
        Scene scene04 = new Scene();
        scene04.setCamera(new Camera(new Vector3D(0, 0, -4), 60, 60, 800, 800, 0.6, 50.0, 25.0));
        scene04.addLight(new PointLight(new Vector3D(0.0, 1.0, 0.0), Color.WHITE, 2.5));
        scene04.addObject(new Sphere(new Vector3D(0, 1.0, 3.0), 0.5, BlinphongMaterial.Bronze(Color.RED)));
        scene04.addObject(new Sphere(new Vector3D(0, 0.0, 2.0), 0.5, BlinphongMaterial.Bronze));
        return scene04;
    }

    private static Scene generatescene5(){
        Scene scene04 = new Scene();
        scene04.setCamera(new Camera(new Vector3D(0, 0, -4), 60, 60, 800, 800, 0.6, 50.0, 25.0));
        scene04.addLight(new PointLight(new Vector3D(0.0, 1.0, 0.0), Color.WHITE, 2.5));
        scene04.addLight(new DirectionalLight(new Vector3D(0.0, 0.0, 1.0), Color.WHITE, 1.1));
        scene04.addObject(new Sphere(new Vector3D(3.0, 0.0, 41.0), 30, BlinphongMaterial.Bronze(Color.RED)));
        Object3D glass =new Sphere(new Vector3D(3.0, 0.0, 5.0), 5, new BlinphongMaterial(Color.black,8));
        glass.getMaterial().setTransparency(1.0);
        scene04.addObject(glass);
        return scene04;
    }
    private static Scene generatesceneFinal(){
        Scene scene04 = new Scene();
        scene04.setCamera(new Camera(new Vector3D(0, 0, -4), 96, 60, 1920, 1080, 0.6, 50.0, 25.0));
        scene04.addLight(new PointLight(new Vector3D(0.0, 1.0, 0.0), Color.WHITE, 2.5));
        scene04.addLight(new PointLight(new Vector3D(5.7, 9.5, 3.0), Color.YELLOW, 5));
        scene04.addLight(new PointLight(new Vector3D(5.7, 9.5, 8.0), Color.YELLOW, 5));
        scene04.addLight(new PointLight(new Vector3D(5.7, 9.5, 13.0), Color.YELLOW, 5));
        scene04.addLight(new PointLight(new Vector3D(5.7, 9.5, 18.0), Color.YELLOW, 5));
        scene04.addLight(new PointLight(new Vector3D(5.7, 9.5, 23.0), Color.YELLOW, 5));
        scene04.addLight(new PointLight(new Vector3D(5.7, 9.5, 28.0), Color.YELLOW, 5));
        scene04.addLight(new DirectionalLight(new Vector3D(0, -1, 0), Color.WHITE, 1));
        scene04.addObject(OBJReader.getModel3D("floor.obj",new Vector3D(3.0, -2.0, 13.0),new BlinphongMaterial(Color.DARK_GRAY,5)));
        scene04.addObject(OBJReader.getModel3D("street lamp.obj",new Vector3D(6.0, -2.0, 13.0),Vector3D.ZERO(),new Vector3D(4.0,4.0,4.0), BlinphongMaterial.Chrome));
        scene04.addObject(OBJReader.getModel3D("street lamp.obj",new Vector3D(6.0, -2.0, 8.0),Vector3D.ZERO(),new Vector3D(4.0,4.0,4.0), BlinphongMaterial.Chrome));
        scene04.addObject(OBJReader.getModel3D("street lamp.obj",new Vector3D(6.0, -2.0, 18.0),Vector3D.ZERO(),new Vector3D(4.0,4.0,4.0), BlinphongMaterial.Chrome));
        scene04.addObject(OBJReader.getModel3D("street lamp.obj",new Vector3D(6.0, -2.0, 3.0),Vector3D.ZERO(),new Vector3D(4.0,4.0,4.0), BlinphongMaterial.Chrome));
        scene04.addObject(OBJReader.getModel3D("street lamp.obj",new Vector3D(6.0, -2.0, 23.0),Vector3D.ZERO(),new Vector3D(4.0,4.0,4.0), BlinphongMaterial.Chrome));
        scene04.addObject(OBJReader.getModel3D("street lamp.obj",new Vector3D(6.0, -2.0, 28.0),Vector3D.ZERO(),new Vector3D(4.0,4.0,4.0), BlinphongMaterial.Chrome));
        return scene04;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println(new Date());

        Double zor=26.0, zfin=13.0;
        images=new BufferedImage[61];
        for( int i=31; i<41; i++) {
            Scene scenebase = generatesceneFinal();
            Double zac=((zfin-zor)*i)/60.0;
            scenebase.addLight(new PointLight(new Vector3D(-2.5, 0, 2.0+zor+zac-13.0), Color.RED, 3));
            scenebase.addLight(new PointLight(new Vector3D(0.5, 0, 2.1+zor+zac-13.0), Color.RED, 3));
            scenebase.addObject(OBJReader.getModel3D("car.obj",new Vector3D(3.0, 0.0, zor+zac), BlinphongMaterial.Bronze(Color.RED)));
            scenebase.addObject(OBJReader.getModel3D("parachoques.obj",new Vector3D(3.0, 0.0, zor+zac), BlinphongMaterial.Chrome));
            scenebase.addObject(OBJReader.getModel3D("FrontBumper.obj",new Vector3D(3.0, 0.0, zor+zac), BlinphongMaterial.BlackPlastic));
            scenebase.addObject(OBJReader.getModel3D("wheels.obj",new Vector3D(3.0, -0.4, zor+zac), BlinphongMaterial.Rubber));
            scenebase.addObject(OBJReader.getModel3D("windows.obj",new Vector3D(3.0, 0.0, zor+zac), BlinphongMaterial.CyanMirror));
            scenebase.addObject(OBJReader.getModel3D("lights.obj",new Vector3D(3.0, 0.0, zor+zac),BlinphongMaterial.Chrome));
            BufferedImage image = raytrace(scenebase, i);
            String nombre="animacion"+i+".png";
            File outputImage = new File(nombre);
            System.out.println(outputImage.getName());
            try {
                ImageIO.write(image, "png", outputImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i=0; i<40; i++){
            String nombre="animacion"+i+".png";
            File outputImage = new File(nombre);
            try {
                images[i]=ImageIO.read(outputImage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String nombre="blurred.png";
        File outputImage = new File(nombre);
        BufferedImage blurred= new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        for( int i=31; i<35; i++){
            for( int j=0; j< blurred.getHeight(); j++){
                for( int k=0; k<blurred.getWidth(); k++){
                    if(i>31)
                        blurred.setRGB(k,j,addColor(scaleColor(new Color(images[i].getRGB(k,j)),1.0/4.0),new Color(blurred.getRGB(k,j))).getRGB());
                    else
                        blurred.setRGB(k,j,scaleColor(new Color(images[i].getRGB(k,j)),1.0/4.0).getRGB());
                }
            }
        }
        try {
            ImageIO.write(blurred,"png",outputImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(new Date());
    }

    /**
     * Raytrace buffered image.
     *
     * @param scene       the scene
     * @param scenenumber the scenenumber
     * @return the buffered image
     */
    public static BufferedImage raytrace(Scene scene, int scenenumber) {
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        Camera mainCamera = scene.getCamera();
        nearFarPlanes = mainCamera.getNearFarPlanes();
        cameraZ = mainCamera.getPosition().getZ();
        images[scenenumber] = new BufferedImage(mainCamera.getResolutionWidth(), mainCamera.getResolutionHeight(), BufferedImage.TYPE_INT_RGB);
        List<Object3D> objects = scene.getObjects();
        List<Light> lights = scene.getLights();
        ambientLight= new OmniLight(Vector3D.ZERO(),Color.BLACK,1);
        List<Color> lightcolors= new ArrayList<>();
        List<Double>intensities= new ArrayList<>();
        double expectedIntensity=0;
        for (Light light:lights) {
            lightcolors.add(light.getColor());
            expectedIntensity+=light.getIntensity(light.getPosition());
           intensities.add(light.getIntensity(light.getPosition()));
        }
        ambientLight.setColor(averageColor(lightcolors, intensities));
        for (Object3D object:
             objects) {
            object.getMaterial().setAmbientColor(ambientLight);
        }
        positionsToRaytrace = mainCamera.calculatePositionsToRay();
        for (int i = 0; i < positionsToRaytrace.length-1; i++) {
            for (int j = 0; j < positionsToRaytrace[i].length-1; j++) {
                Runnable runnable = draw(i,j,lights,objects,mainCamera, scenenumber);
                executorService.execute(runnable);
            }
        }

        executorService.shutdown();
        try{
            if(!executorService.awaitTermination(1800, TimeUnit.MINUTES)){
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if(!executorService.isTerminated()){
                System.err.println("Cancel non-finished");
            }
        }
        executorService.shutdownNow();

        return images[scenenumber];
    }

    /**
     * Draw runnable.
     *
     * @param x           the x
     * @param y           the y
     * @param lights      the lights
     * @param objects     the objects
     * @param mainCamera  the main camera
     * @param scenenumber the scenenumber
     * @return the runnable
     */
    public static Runnable draw(int x, int y, List<Light>lights, List<Object3D>objects, Camera mainCamera, int scenenumber){
        Runnable aRunnable = new Runnable() {
            @Override
            public void run() {
                Color color = decideColor(x,y,lights,objects,mainCamera);
                setRGB(x, y, color, scenenumber);
            }
        };

        return aRunnable;
    }

    /**
     * Decide color color.
     *
     * @param i          the
     * @param j          the j
     * @param lights     the lights
     * @param objects    the objects
     * @param mainCamera the main camera
     * @return the color
     */
    public static Color decideColor(int i,int j, List<Light>lights, List<Object3D>objects, Camera mainCamera){

        double x = positionsToRaytrace[i][j].getX() + mainCamera.getPosition().getX();
        double y = positionsToRaytrace[i][j].getY() + mainCamera.getPosition().getY();
        double z = positionsToRaytrace[i][j].getZ() + mainCamera.getPosition().getZ();

        Ray ray = new Ray(mainCamera.getPosition(), new Vector3D(x, y, z));
        Intersection closestIntersection = raycast(ray, objects, null, new double[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]},cameraZ+nearFarPlanes[2], false);
        Color pixelColor = Color.BLACK;
        if (closestIntersection != null) {
            pixelColor= generateColor(closestIntersection,lights,objects,ray.getDirection(), mainCamera.getPosition());
        }
        return pixelColor;
    }

    /**
     * Set rgb.
     *
     * @param x           the x
     * @param y           the y
     * @param pixelColor  the pixel color
     * @param scenenumber the scenenumber
     */
    public static synchronized void setRGB(int x, int y, Color pixelColor, int scenenumber){
        images[scenenumber].setRGB(x, y, pixelColor.getRGB());
    }

    /**
     * Generate color color.
     *
     * @param closestIntersection the closest intersection
     * @param lights              the lights
     * @param objects             the objects
     * @param pvRayDirection      the pv ray direction
     * @param viewer              the viewer
     * @return the color
     */
    public static Color generateColor(Intersection closestIntersection, List<Light> lights,List<Object3D> objects, Vector3D pvRayDirection, Vector3D viewer) {
        Color answerColor = Color.black;
        List<ShadeState> currentStates = new ArrayList<>();
        List<ShadeState> futureStates;
        currentStates.add(new ShadeState(closestIntersection,pvRayDirection,1.0, false));

        for(int i = 0; i< MAX_RECURSION && !currentStates.isEmpty(); i++){
            futureStates= new ArrayList<>();
            for(ShadeState acShadeState: currentStates) {
                boolean currInsideObj = acShadeState.getInsideObj();
                Double currentShininess = acShadeState.getIntensity();
                pvRayDirection=acShadeState.getPvRayDirection();
                closestIntersection= acShadeState.getIntersection();
                AbstractList<Light> collidingLights = new ArrayList<Light>();

                for (Light light : lights) {
                    Intersection shadow = shadowRaycast(light.shadowRay(closestIntersection.getPosition()), objects, closestIntersection.getObject(), null, light.getDistance(closestIntersection.getPosition()));
                    if (shadow == null) {
                        collidingLights.add(light);
                    } else{
                        if(shadow.getPassed()!=null&&shadow.getPassed().getTransparency()>0){
                            DirectionalLight temp=new DirectionalLight(Vector3D.scalarMultiplication(light.getDirection(closestIntersection.getPosition()),-1),multiplyColor(light.getColor(),shadow.getPassed().getColor(), 1.0), shadow.getPassed().getTransparency()*light.getIntensity(closestIntersection.getPosition()));
                            collidingLights.add(temp);
                        }
                    }
                }
                Color acColor = closestIntersection.getObject().getMaterial().calculateColor(closestIntersection, collidingLights, viewer);
                answerColor = addColor(answerColor, scaleColor(acColor, currentShininess));
                Double newShininess = currentShininess* closestIntersection.getObject().getMaterial().getShininess() / 128.0;
               if( newShininess> MIN_EFFECT&& !currInsideObj){
                   Ray reflectedRay;
                   reflectedRay = new Ray(closestIntersection.getPosition(), Vector3D.reflection(pvRayDirection, closestIntersection.getNormal()));
                   Intersection futureIntersection = raycast(reflectedRay, objects, closestIntersection.getObject(), null, Double.POSITIVE_INFINITY, currInsideObj);
                   if(futureIntersection!=null)
                    futureStates.add(new ShadeState(futureIntersection,reflectedRay.getDirection(),newShininess,currInsideObj ));
                }

                Double transparency=closestIntersection.getObject().getMaterial().getTransparency();

               if(transparency>0.0){
                    Ray refractedRay ;
                   if(!currInsideObj)refractedRay=new Ray(closestIntersection.getPosition(),Vector3D.refraction(closestIntersection.getNormal(),pvRayDirection,transparency));
                   else refractedRay = new Ray(closestIntersection.getPosition(),Vector3D.refraction(Vector3D.scalarMultiplication(closestIntersection.getNormal(),-1),pvRayDirection,transparency));
                   if(refractedRay.getDirection()!=null){
                        Intersection futureIntersection=raycast(refractedRay, objects, closestIntersection.getObject(), null, Double.POSITIVE_INFINITY, currInsideObj);
                        if(futureIntersection!=null)
                            futureStates.add(new ShadeState(futureIntersection,refractedRay.getDirection(),currentShininess*(currInsideObj?1.0/transparency:transparency),currInsideObj));
                    }
               }
            }
            currentStates= futureStates;
         }
        return answerColor;
    }


    /**
     * Raycast intersection.
     *
     * @param ray            the ray
     * @param objects        the objects
     * @param caster         the caster
     * @param clippingPlanes the clipping planes
     * @param blurplane      the blurplane
     * @param inverted       the inverted
     * @return the intersection
     */
    public static Intersection raycast(Ray ray, List<Object3D> objects, Object3D caster, double[] clippingPlanes, double blurplane, boolean inverted){
        if(inverted){
            return caster.getIntersection(ray,inverted);
        }
        Intersection closestIntersection = null;
        boolean unfocused=true;
        for(int k = 0; k < objects.size(); k++) {
            Object3D currentObj = objects.get(k);
            Intersection intersection = null;
            if( ((caster == null || !currentObj.equals(caster)))) {
                intersection = currentObj.getIntersection(ray, inverted);
                if (intersection != null) {
                    double distance = intersection.getDistance();
                    double intersectionZ = intersection.getPosition().getZ();
                    if (distance >= 0 &&
                            (closestIntersection == null || distance < closestIntersection.getDistance() || unfocused == true) &&
                            (clippingPlanes == null || (intersectionZ >= clippingPlanes[0] && intersectionZ <= blurplane))) {
                        closestIntersection = intersection;
                        unfocused = false;
                    }
                }
                if (closestIntersection == null || unfocused == true) {
                    intersection = null;//currentObj.getBoxIntersection(rayb);
                    if (intersection != null) {
                        double distance = intersection.getDistance();
                        double intersectionZ = intersection.getPosition().getZ();
                        if (distance >= 0 &&
                                (closestIntersection == null || distance < closestIntersection.getDistance()) &&
                                (clippingPlanes == null || (intersectionZ >= blurplane && intersectionZ <= clippingPlanes[1]))) {
                            closestIntersection = intersection;
                        }
                    }
                }
            }
        }

        return closestIntersection;
    }

    /**
     * Shadow raycast intersection.
     *
     * @param ray            the ray
     * @param objects        the objects
     * @param caster         the caster
     * @param clippingPlanes the clipping planes
     * @param lightdistance  the lightdistance
     * @return the intersection
     */
    public static Intersection shadowRaycast(Ray ray, List<Object3D> objects, Object3D caster, double[] clippingPlanes, double lightdistance){
        Intersection closestIntersection = null;

        for(int k = 0; k < objects.size()&&(closestIntersection==null||closestIntersection.getPassed().getTransparency()>EPSILON); k++){
            Object3D currentObj = objects.get(k);
            if(caster == null || !currentObj.equals(caster)){
                Intersection intersection = currentObj.getIntersection(ray, false);
                if(intersection != null){
                    double distance = intersection.getDistance();
                    double intersectionZ = intersection.getPosition().getZ();
                    if(distance >= 0 &&
                            lightdistance> distance &&
                            (closestIntersection == null || distance < closestIntersection.getDistance()) &&
                            (clippingPlanes == null || (intersectionZ >= clippingPlanes[0] && intersectionZ <= clippingPlanes[1]))){
                        if(closestIntersection==null){
                            closestIntersection = intersection;
                            closestIntersection.setPassed(new Filter(1.0,Color.white));
                        }
                        Material x =intersection.getObject().getMaterial();
                        closestIntersection.getPassed().setTransparency(closestIntersection.getPassed().getTransparency()*x.getTransparency());
                        closestIntersection.getPassed().setColor(substractColor(closestIntersection.getPassed().getColor(),inverseColor(scaleColor(x.getColor(),1.0-x.getTransparency()))));
                    }
                }
            }
        }
        return closestIntersection;
    }
}
