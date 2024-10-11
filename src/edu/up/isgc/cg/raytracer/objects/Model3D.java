package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Materials.BlinphongMaterial;
import edu.up.isgc.cg.raytracer.Materials.Material;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The type Model 3 d.
 */
public class Model3D extends Object3D{
    private List<Triangle> triangles;

    /**
     * Instantiates a new Model 3 d.
     *
     * @param position  the position
     * @param triangles the triangles
     * @param color     the color
     */
    public Model3D(Vector3D position, Triangle[] triangles, Color color) {
        super(position, new BlinphongMaterial(color,32.0));
        setTriangles(triangles);
    }

    /**
     * Instantiates a new Model 3 d.
     *
     * @param position  the position
     * @param triangles the triangles
     * @param nmaterial the nmaterial
     */
    public Model3D(Vector3D position, Triangle[] triangles, Material nmaterial) {
        super(position, nmaterial);
        setTriangles(triangles);
    }

    /**
     * Instantiates a new Model 3 d.
     *
     * @param position  the position
     * @param rotation  the rotation
     * @param triangles the triangles
     * @param nmaterial the nmaterial
     */
    public Model3D(Vector3D position,Vector3D rotation, Triangle[] triangles, Material nmaterial) {
        super(position, rotation, nmaterial);
        setTriangles(triangles);
    }

    /**
     * Instantiates a new Model 3 d.
     *
     * @param position  the position
     * @param rotation  the rotation
     * @param scale     the scale
     * @param triangles the triangles
     * @param nmaterial the nmaterial
     */
    public Model3D(Vector3D position,Vector3D rotation, Vector3D scale, Triangle[] triangles, Material nmaterial) {
        super(position, rotation, scale, nmaterial);
        setTriangles(triangles);
    }

    /**
     * Gets triangles.
     *
     * @return the triangles
     */
    public List<Triangle> getTriangles() {
        return triangles;
    }

    /**
     * Sets triangles.
     *
     * @param triangles the triangles
     */
    public void setTriangles(Triangle[] triangles) {
        Vector3D position = getPosition();
        Set<Vector3D> uniqueVertices = new HashSet<>();
        Set<Vector3D> uniqueNormals= new HashSet<>();
        for(Triangle triangle : triangles){
            uniqueVertices.addAll(Arrays.asList(triangle.getVertices()));
            uniqueNormals.addAll(Arrays.asList(triangle.getNormals()));
        }

        for(Vector3D vertex : uniqueVertices){
            Vector3D aux = new Vector3D(vertex.getX(),vertex.getY(),vertex.getZ());
            aux = Vector3D.rotateX(aux,getRotation().getX());
            aux = Vector3D.rotateY(aux,getRotation().getY());
            aux = Vector3D.rotateZ(aux,getRotation().getZ());
            aux.setX(aux.getX()*getScale().getX());
            aux.setY(aux.getY()*getScale().getY());
            aux.setZ(aux.getZ()*getScale().getZ());
            vertex.setX(aux.getX() + position.getX());
            vertex.setY(aux.getY() + position.getY());
            vertex.setZ(aux.getZ() + position.getZ());
        }

        for(Vector3D normal : uniqueNormals){
            Vector3D aux = new Vector3D(normal.getX(),normal.getY(),normal.getZ());
            aux = Vector3D.rotateX(aux,getRotation().getX());
            aux = Vector3D.rotateY(aux,getRotation().getY());
            aux = Vector3D.rotateZ(aux,getRotation().getZ());
            normal.setX(aux.getX());
            normal.setY(aux.getY());
            normal.setZ(aux.getZ());
        }

        this.triangles = Arrays.asList(triangles);
    }

    @Override
    public Intersection getIntersection(Ray ray, boolean invertedNormal) {
        double distance = -1;
        Vector3D normal = Vector3D.ZERO();
        Vector3D position = Vector3D.ZERO();

        for(Triangle triangle : getTriangles()){
            Intersection intersection = triangle.getIntersection(ray, invertedNormal);
            double intersectionDistance = intersection.getDistance();
            if(intersection != null && intersectionDistance > 0 &&
                    (intersectionDistance < distance || distance < 0)){
                distance = intersectionDistance;
                position = Vector3D.add(ray.getOrigin(), Vector3D.scalarMultiplication(ray.getDirection(), distance));
                normal = triangle.getNormal(position);
            }
        }

        if(distance == -1){
            return null;
        }
        return new Intersection(position, distance, normal, this);
    }



}
