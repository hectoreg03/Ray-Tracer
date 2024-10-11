/**
 * [1968] - [2022] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.tools;

import edu.up.isgc.cg.raytracer.Materials.BlinphongMaterial;
import edu.up.isgc.cg.raytracer.Materials.Material;
import edu.up.isgc.cg.raytracer.objects.Model3D;
import edu.up.isgc.cg.raytracer.objects.Triangle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Obj reader.
 */
public abstract class OBJReader {
    /**
     * Get model 3 d model 3 d.
     *
     * @param path   the path
     * @param origin the origin
     * @param color  the color
     * @return the model 3 d
     */
    public static Model3D getModel3D(String path, Vector3D origin, Color color){
        return getModel3D(path, origin, Vector3D.ZERO(), new BlinphongMaterial(color,32.0));
    }

    /**
     * Get model 3 d model 3 d.
     *
     * @param path      the path
     * @param origin    the origin
     * @param nmaterial the nmaterial
     * @return the model 3 d
     */
    public static Model3D getModel3D(String path, Vector3D origin, Material nmaterial){
        return getModel3D(path, origin, Vector3D.ZERO(), nmaterial);
    }

    /**
     * Gets model 3 d.
     *
     * @param path      the path
     * @param origin    the origin
     * @param rotation  the rotation
     * @param nmaterial the nmaterial
     * @return the model 3 d
     */
    public static Model3D getModel3D(String path, Vector3D origin, Vector3D rotation, Material nmaterial) {
        return  getModel3D(path,origin,rotation,new Vector3D(1.0,1.0,1.0),nmaterial);
    }

    /**
     * Gets model 3 d.
     *
     * @param path      the path
     * @param origin    the origin
     * @param rotation  the rotation
     * @param scale     the scale
     * @param nmaterial the nmaterial
     * @return the model 3 d
     */
    public static Model3D getModel3D(String path, Vector3D origin, Vector3D rotation, Vector3D scale, Material nmaterial) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            Boolean inSmoothingGroup=false;
            List<Triangle> triangles = new ArrayList<Triangle>();
            List<Vector3D> vertices = new ArrayList<Vector3D>();
            List<Vector3D> normals = new ArrayList<Vector3D>();
            List<List<Integer>> tempTriangles = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("v ") || line.startsWith("vn ")) {
                    String[] vertexComponents = line.split("(\\s)+");
                    if (vertexComponents.length >= 4) {
                        double x = Double.parseDouble(vertexComponents[1]);
                        double y = Double.parseDouble(vertexComponents[2]);
                        double z = Double.parseDouble(vertexComponents[3]);
                        if (line.startsWith("v ")) {
                            vertices.add(new Vector3D(x, y, z));
                        } else {
                            normals.add(new Vector3D(x, y, z));
                        }
                    }
                } else if (line.startsWith("f ")) {
                    String[] faceComponents = line.split("(\\s)+");
                    List<Integer> faceVertex = new ArrayList<Integer>();
                    List<Integer> faceNormal = new ArrayList<Integer>();

                    for (int i = 1; i < faceComponents.length; i++) {
                        String[] infoVertex = faceComponents[i].split("/");
                        if (infoVertex.length >= 3) {
                            int vertexIndex = Integer.parseInt(infoVertex[0]);
                            int normalIndex = Integer.parseInt(infoVertex[2]);
                            faceVertex.add(vertexIndex);
                            faceNormal.add(normalIndex);
                        }
                    }
                    if (faceVertex.size() >= 3) {
                        if(!inSmoothingGroup){

                            Vector3D[] triangleVertices = new Vector3D[faceVertex.size()];

                            for (int i = 0; i < faceVertex.size(); i++) {
                                triangleVertices[i] = (vertices.get(faceVertex.get(i) - 1));
                            }
                            Vector3D[] verticesNormals = new Vector3D[faceVertex.size()];
                            for ( int i=0; i<faceVertex.size(); i++){
                                if(normals.size()>(faceNormal.get(i)-1))
                                    verticesNormals[i] = (normals.get(faceNormal.get(i)-1));
                                else{
                                    verticesNormals= null;
                                    break;
                                }
                            }
                            //triangles.add(new Triangle(triangleVertices, verticesNormals));
                            if(verticesNormals!=null) {
                                triangles.add(new Triangle(triangleVertices[1], triangleVertices[0], triangleVertices[2], verticesNormals[1], verticesNormals[0], verticesNormals[2]));
                                if (faceVertex.size() == 4) {
                                    triangles.add(new Triangle(triangleVertices[2], triangleVertices[0], triangleVertices[3], verticesNormals[2], verticesNormals[0], verticesNormals[3]));
                                }
                            } else{
                                triangles.add(new Triangle(triangleVertices[1], triangleVertices[0], triangleVertices[2]));
                                if (faceVertex.size() == 4) {
                                    triangles.add(new Triangle(triangleVertices[2], triangleVertices[0], triangleVertices[3]));
                                }
                            }
                        } else{
                            tempTriangles.add(faceVertex);
                            tempTriangles.add(faceNormal);
                        }
                    }
                } else if (line.startsWith("s")) {
                    if(!line.startsWith("s off"))inSmoothingGroup=true;
                    else inSmoothingGroup=false;

                    Map<Integer, Vector3D> smoothedNormals = new HashMap<>();
                    Map<Integer,List<Integer>> toSmoothNormals = new HashMap<>();

                    // Reinterpreta los vertices que hay y genera una lista de las normales que tiene asignadas originalmente cada vertice
                    for( int i=0; i< tempTriangles.size(); i++){
                        for( int j=0; j< tempTriangles.get(i).size(); j++){
                            //given a vertex
                            if(!toSmoothNormals.containsKey(tempTriangles.get(i).get(j))){
                                //create a new list for that vertex
                                toSmoothNormals.put(tempTriangles.get(i).get(j),new ArrayList<Integer>());

                            }
                            // add the normal that is meant to be used also in that vertex
                            toSmoothNormals.get(tempTriangles.get(i).get(j)).add(tempTriangles.get(i+1).get(j));
                        }
                        i++;
                    }

                    // Calcula la normal de cada uno de los vertices para suavizarlos
                    for (Integer node:toSmoothNormals.keySet()) {
                        smoothedNormals.put(node,new Vector3D(0.0,0.0,0.0));
                        List<Integer> toaverage= toSmoothNormals.get(node);
                        Double dSize= (double) toaverage.size();
                        for( int i=0; i< toaverage.size(); i++){
                            smoothedNormals.put(node,  Vector3D.add(smoothedNormals.get(node),normals.get(toaverage.get(i)-1)));
                        }
                        smoothedNormals.put(node, Vector3D.scalarMultiplication(smoothedNormals.get(node),(1.0/dSize)));
                    }
                    //Genera todas las caras
                    for( int i=0; i< tempTriangles.size(); i++){
                        Vector3D[] triangleVertices = new Vector3D[tempTriangles.get(i).size()];
                        Vector3D[] verticesNormals = new Vector3D[tempTriangles.get(i+1).size()];
                        for (int j = 0; j < tempTriangles.get(i).size(); j++) {
                            triangleVertices[j] = (vertices.get(tempTriangles.get(i).get(j) - 1));
                        }
                        for ( int j=0; j<tempTriangles.get(i+1).size(); j++){
                            verticesNormals[j] = (smoothedNormals.get(tempTriangles.get(i).get(j)));
                        }
                        triangles.add(new Triangle(triangleVertices[1], triangleVertices[0], triangleVertices[2], verticesNormals[1],verticesNormals[0],verticesNormals[2]));
                        if (tempTriangles.get(i).size() == 4) {
                            triangles.add(new Triangle(triangleVertices[2], triangleVertices[0], triangleVertices[3], verticesNormals[2],verticesNormals[0],verticesNormals[3]));
                        }
                        i++;
                    }

                    tempTriangles= new ArrayList<>();
                }
            }
            if(!tempTriangles.isEmpty()){
                Map<Integer, Vector3D> smoothedNormals = new HashMap<>();
                Map<Integer,List<Integer>> toSmoothNormals = new HashMap<>();

                // Reinterpreta los vertices que hay y genera una lista de las normales que tiene asignadas originalmente cada vertice
                for( int i=0; i< tempTriangles.size(); i++){
                    for( int j=0; j< tempTriangles.get(i).size(); j++){
                        //given a vertex
                        if(!toSmoothNormals.containsKey(tempTriangles.get(i).get(j))){
                            //create a new list for that vertex
                            toSmoothNormals.put(tempTriangles.get(i).get(j),new ArrayList<Integer>());

                        }
                        // add the normal that is meant to be used also in that vertex
                        toSmoothNormals.get(tempTriangles.get(i).get(j)).add(tempTriangles.get(i+1).get(j));
                    }
                    i++;
                }

                // Calcula la normal de cada uno de los vertices para suavizarlos
                for (Integer node:toSmoothNormals.keySet()) {
                    smoothedNormals.put(node,new Vector3D(0.0,0.0,0.0));
                    List<Integer> toaverage= toSmoothNormals.get(node);
                    Double dSize= (double) toaverage.size();
                    for( int i=0; i< toaverage.size(); i++){
                        smoothedNormals.put(node,  Vector3D.add(smoothedNormals.get(node),normals.get(toaverage.get(i)-1)));
                    }
                    smoothedNormals.put(node, Vector3D.scalarMultiplication(smoothedNormals.get(node),(1.0/dSize)));
                }
                //Genera todas las caras
                for( int i=0; i< tempTriangles.size(); i++){
                    Vector3D[] triangleVertices = new Vector3D[tempTriangles.get(i).size()];
                    Vector3D[] verticesNormals = new Vector3D[tempTriangles.get(i+1).size()];
                    for (int j = 0; j < tempTriangles.get(i).size(); j++) {
                        triangleVertices[j] = (vertices.get(tempTriangles.get(i).get(j) - 1));
                    }
                    for ( int j=0; j<tempTriangles.get(i+1).size(); j++){
                        verticesNormals[j] = (smoothedNormals.get(tempTriangles.get(i).get(j)));
                    }
                    triangles.add(new Triangle(triangleVertices[1], triangleVertices[0], triangleVertices[2], verticesNormals[1],verticesNormals[0],verticesNormals[2]));
                    if (tempTriangles.get(i).size() == 4) {
                        triangles.add(new Triangle(triangleVertices[2], triangleVertices[0], triangleVertices[3], verticesNormals[2],verticesNormals[0],verticesNormals[3]));
                    }
                    i++;
                }


            }
            reader.close();
            return new Model3D(origin, rotation, scale, triangles.toArray(new Triangle[triangles.size()]), nmaterial);
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        } catch (IOException ex) {
            System.err.println("Exception found");
        }

        return null;
    }

}
