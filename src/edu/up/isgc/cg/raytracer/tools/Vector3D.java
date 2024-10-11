package edu.up.isgc.cg.raytracer.tools;

import java.lang.Math;

import static java.lang.Math.pow;

/**
 * The type Vector 3 d.
 */
public class Vector3D {
    private static final Vector3D ZERO = new Vector3D(0.0, 0.0, 0.0);
    private double x, y, z;

    /**
     * Instantiates a new Vector 3 d.
     *
     * @param x the x
     * @param y the y
     * @param z the z
     */
    public Vector3D(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets z.
     *
     * @return the z
     */
    public double getZ() {
        return z;
    }

    /**
     * Sets z.
     *
     * @param z the z
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Clone vector 3 d.
     *
     * @return the vector 3 d
     */
    public Vector3D clone() {
        return new Vector3D(getX(), getY(), getZ());
    }

    /**
     * Zero vector 3 d.
     *
     * @return the vector 3 d
     */
    public static Vector3D ZERO() {
        return ZERO.clone();
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Vector3D{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", z=" + getZ() +
                "}";
    }

    /**
     * Dot product double.
     *
     * @param vectorA the vector a
     * @param vectorB the vector b
     * @return the double
     */
    public static double dotProduct(Vector3D vectorA, Vector3D vectorB) {
        return (vectorA.getX() * vectorB.getX())+ (vectorA.getY() * vectorB.getY()) + (vectorA.getZ() * vectorB.getZ());
    }

    /**
     * Cross product vector 3 d.
     *
     * @param vectorA the vector a
     * @param vectorB the vector b
     * @return the vector 3 d
     */
    public static Vector3D crossProduct(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D((vectorA.getY() * vectorB.getZ()) - (vectorA.getZ() * vectorB.getY()),
                (vectorA.getZ() * vectorB.getX()) - (vectorA.getX() * vectorB.getZ()),
                (vectorA.getX() * vectorB.getY()) - (vectorA.getY() * vectorB.getX()));
    }

    /**
     * Magnitude double.
     *
     * @param vectorA the vector a
     * @return the double
     */
    public static double magnitude (Vector3D vectorA){
        return Math.sqrt(dotProduct(vectorA, vectorA));
    }

    /**
     * Add vector 3 d.
     *
     * @param vectorA the vector a
     * @param vectorB the vector b
     * @return the vector 3 d
     */
    public static Vector3D add(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY(), vectorA.getZ() + vectorB.getZ());
    }

    /**
     * Div dot product double.
     *
     * @param vectorA the vector a
     * @param vectorB the vector b
     * @return the double
     */
    public static Double divDotProduct(Vector3D vectorA, Vector3D vectorB){
        return vectorB.getX()>0? (vectorA.getX() / vectorB.getX()):0 + vectorB.getY()>0?(vectorA.getY() / vectorB.getY()):0 + vectorB.getZ()>0?(vectorA.getZ() / vectorB.getZ()):0;
    }

    /**
     * Substract vector 3 d.
     *
     * @param vectorA the vector a
     * @param vectorB the vector b
     * @return the vector 3 d
     */
    public static Vector3D substract(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D(vectorA.getX() - vectorB.getX(), vectorA.getY() - vectorB.getY(), vectorA.getZ() - vectorB.getZ());
    }

    /**
     * Normalize vector 3 d.
     *
     * @param vectorA the vector a
     * @return the vector 3 d
     */
    public static Vector3D normalize(Vector3D vectorA){
        double mag = Vector3D.magnitude(vectorA);
        return new Vector3D(vectorA.getX() / mag, vectorA.getY() / mag, vectorA.getZ() / mag);
    }

    /**
     * Scalar multiplication vector 3 d.
     *
     * @param vectorA the vector a
     * @param scalar  the scalar
     * @return the vector 3 d
     */
    public static Vector3D scalarMultiplication(Vector3D vectorA, double scalar){
        return new Vector3D(vectorA.getX() * scalar, vectorA.getY() * scalar, vectorA.getZ() * scalar);
    }

    /**
     * Rotate z vector 3 d.
     *
     * @param vectorA the vector a
     * @param gamma   the gamma
     * @return the vector 3 d
     */
    public static Vector3D rotateZ(Vector3D vectorA, Double gamma){
        return new Vector3D(vectorA.getX()*Math.cos(gamma)-vectorA.getY()*Math.sin(gamma), vectorA.getY()*Math.cos(gamma)+vectorA.getX()*Math.sin(gamma), vectorA.getZ() );
    }

    /**
     * Rotate x vector 3 d.
     *
     * @param vectorA the vector a
     * @param alpha   the alpha
     * @return the vector 3 d
     */
    public static Vector3D rotateX(Vector3D vectorA, Double alpha){
        return new Vector3D(vectorA.getX(), vectorA.getY()*Math.cos(alpha) - vectorA.getZ()*Math.sin(alpha), vectorA.getZ()*Math.cos(alpha) + vectorA.getY()*Math.sin(alpha));
    }

    /**
     * Rotate y vector 3 d.
     *
     * @param vectorA the vector a
     * @param beta    the beta
     * @return the vector 3 d
     */
    public static Vector3D rotateY(Vector3D vectorA, Double beta){
        return new Vector3D(vectorA.getX()*Math.cos(beta) + vectorA.getZ()*Math.sin(beta), vectorA.getY(), -vectorA.getX()*Math.sin(beta) + vectorA.getZ()*Math.cos(beta));
    }

    /**
     * Anglecos double.
     *
     * @param vectorA the vector a
     * @param vectorB the vector b
     * @return the double
     */
    public static double anglecos(Vector3D vectorA, Vector3D vectorB){
        Double ans = dotProduct(vectorA,vectorB)/(magnitude(vectorA)*magnitude(vectorB));
        return ans;
    }

    /**
     * Reflection vector 3 d.
     *
     * @param vectorA the vector a
     * @param vectorB the vector b
     * @return the vector 3 d
     */
    public static Vector3D reflection(Vector3D vectorA, Vector3D vectorB){
        return  Vector3D.substract(vectorA,Vector3D.scalarMultiplication(vectorB,2*Vector3D.dotProduct(vectorA,vectorB)));
    }

    /**
     * Refraction vector 3 d.
     *
     * @param normal          the normal
     * @param viewer          the viewer
     * @param refractiveIndex the refractive index
     * @return the vector 3 d
     */
    public static Vector3D refraction(Vector3D normal, Vector3D viewer, Double refractiveIndex){
        Double nDotV= Vector3D.dotProduct(normal,viewer);
        Double refractiveFactor=(1-(refractiveIndex*refractiveIndex*(1-(nDotV*nDotV))));
        if(refractiveFactor<0)return null;
        refractiveFactor= pow(refractiveFactor,0.5);
        Double normalRefractiveFactor = refractiveIndex*nDotV-refractiveFactor;
        return Vector3D.substract(Vector3D.scalarMultiplication(normal,normalRefractiveFactor),Vector3D.scalarMultiplication(viewer,refractiveIndex));
    }

}
