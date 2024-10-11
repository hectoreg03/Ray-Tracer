package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.tools.Vector3D;

/**
 * The type Triangle.
 */
public class Triangle implements IIntersectable{
    /**
     * The constant EPSILON.
     */
    public static final double EPSILON = 0.0000001;
    private Vector3D[] vertices;
    private Vector3D[] normals;

    /**
     * Instantiates a new Triangle.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @param v3 the v 3
     */
    public Triangle(Vector3D v1, Vector3D v2, Vector3D v3) {
        setVertices(v1, v2, v3);
        setNormals(null);

    }

    /**
     * Instantiates a new Triangle.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @param v3 the v 3
     * @param v4 the v 4
     * @param v5 the v 5
     * @param v6 the v 6
     */
    public Triangle(Vector3D v1, Vector3D v2, Vector3D v3, Vector3D v4, Vector3D v5, Vector3D v6) {
        setVertices(v1, v2, v3);
        setNormals(v4, v5, v6);
        if(v4==null || v5 == null || v6 == null) System.out.println("Error, normales incompletas");
    }

    /**
     * Get vertices vector 3 d [ ].
     *
     * @return the vector 3 d [ ]
     */
    public Vector3D[] getVertices() {
        return vertices;
    }

    private void setVertices(Vector3D[] vertices) {
        this.vertices = vertices;
    }

    /**
     * Sets vertices.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @param v3 the v 3
     */
    public void setVertices(Vector3D v1, Vector3D v2, Vector3D v3) {
        setVertices(new Vector3D[]{v1, v2, v3});
    }

    /**
     * Get normals vector 3 d [ ].
     *
     * @return the vector 3 d [ ]
     */
    public Vector3D[] getNormals() {
        if(normals==null){
            Vector3D normal=getNormal(Vector3D.ZERO());
            normals= new Vector3D[]{normal,normal,normal};
        }
        return normals;
    }

    /**
     * Get normal vector 3 d.
     *
     * @param position the position
     * @return the vector 3 d
     */
    public Vector3D getNormal(Vector3D position){
        Vector3D normal = Vector3D.ZERO();
        if(normals == null){
            Vector3D[] vertices = getVertices();
            //1 to 0 and 2 to 0, multiplied by -1 or we can use 1 to 2 and 0 to 2
            Vector3D v = Vector3D.substract(vertices[1], vertices[2]);
            Vector3D w = Vector3D.substract(vertices[0], vertices[2]);
            normal = Vector3D.normalize(Vector3D.crossProduct(v, w));
        } else{
            Double u=0.0,v=0.0,w=0.0;
        Vector3D relCoordinates=Barycentric(position,vertices[0],vertices[1],vertices[2]);
        u=relCoordinates.getX();
        v=relCoordinates.getY();
        w=relCoordinates.getZ();
        /*
        System.out.println(u);
        System.out.println(v);
        System.out.println(w);*/
        normal= Vector3D.scalarMultiplication(normals[0],u);
        normal= Vector3D.add(normal,Vector3D.scalarMultiplication(normals[1],v));
        normal= Vector3D.add(normal,Vector3D.scalarMultiplication(normals[2],w));
        }
        return normal;

    }

    private void setNormals(Vector3D[] normals) {
        this.normals = normals;
    }

    private void setNormals(Vector3D v1, Vector3D v2, Vector3D v3) {
        setNormals(new Vector3D[]{ v1, v2, v3});
    }

    private Vector3D Barycentric(Vector3D p, Vector3D a, Vector3D b, Vector3D c)
    {   Double u, v, w;
        Vector3D v0 = Vector3D.substract(b,a), v1 = Vector3D.substract(c,a), v2 = Vector3D.substract(p,a);
        Double d00 = Vector3D.dotProduct(v0, v0);
        Double d01 = Vector3D.dotProduct(v0, v1);
        Double d11 = Vector3D.dotProduct(v1, v1);
        Double d20 = Vector3D.dotProduct(v2, v0);
        Double d21 = Vector3D.dotProduct(v2, v1);
        Double denom = d00 * d11 - d01 * d01;
        v = (d11 * d20 - d01 * d21) / denom;
        w = (d00 * d21 - d01 * d20) / denom;
        u = 1.0f - v - w;
        return new Vector3D(u,v,w);
    }

    @Override
    public Intersection getIntersection(Ray ray, boolean invertedNormal) {
        Intersection intersection = new Intersection(null, -1, null, null);

        Vector3D[] vert = getVertices();
        Vector3D v2v0 = Vector3D.substract(vert[2], vert[0]);
        Vector3D v1v0 = Vector3D.substract(vert[1], vert[0]);
        Vector3D vectorP = Vector3D.crossProduct(ray.getDirection(), v1v0);
        double determinant = Vector3D.dotProduct(v2v0, vectorP);
        double invDet = 1.0 / determinant;
        Vector3D vectorT = Vector3D.substract(ray.getOrigin(), vert[0]);
        double u = invDet * Vector3D.dotProduct(vectorT, vectorP);

        if(!(u < 0 || u > 1)){
            Vector3D vectorQ = Vector3D.crossProduct(vectorT, v2v0);
            double v = invDet * Vector3D.dotProduct(ray.getDirection(), vectorQ);
            if(!(v < 0 || (u + v) > (1.0 + EPSILON))){
                double t = invDet * Vector3D.dotProduct(vectorQ, v1v0);
                intersection.setDistance(t);
                intersection.setNormal(Vector3D.normalize(Vector3D.crossProduct(Vector3D.substract(vertices[0],vertices[1]),Vector3D.substract(vertices[1],vertices[2]))));
                if(Double.isInfinite(t))intersection.setDistance(-1.0);
            }
        }
        return intersection;
    }


}
