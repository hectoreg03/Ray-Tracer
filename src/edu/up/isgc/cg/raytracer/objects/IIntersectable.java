package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;

/**
 * The interface Intersectable.
 */
public interface IIntersectable {
    /**
     * Gets intersection.
     *
     * @param ray            the ray
     * @param invertedNormal the inverted normal
     * @return the intersection
     */
    Intersection getIntersection(Ray ray, boolean invertedNormal);
}
