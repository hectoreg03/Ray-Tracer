package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.tools.Vector3D;

/**
 * The type Camera.
 */
public class Camera extends Position3D {
    //FOV[0] = Horizontal | FOV[1] = Vertical
    private double[] fieldOfView = new double[2];
    private double defaultZ = 15.0;
    //0 is width
    //1 is height
    private int[] resolution;
    private double[] nearFarPlanes = new double[2];

    /**
     * Instantiates a new Camera.
     *
     * @param position              the position
     * @param fieldOfViewHorizontal the field of view horizontal
     * @param fieldOfViewVertical   the field of view vertical
     * @param widthResolution       the width resolution
     * @param heightResolution      the height resolution
     * @param nearPlane             the near plane
     * @param farPlane              the far plane
     * @param blurplane             the blurplane
     */
    public Camera(Vector3D position, double fieldOfViewHorizontal, double fieldOfViewVertical,
                  int widthResolution, int heightResolution, double nearPlane, double farPlane, double blurplane) {
        super(position);
        setFieldOfViewHorizontal(fieldOfViewHorizontal);
        setFieldOfViewVertical(fieldOfViewVertical);
        setResolution(new int[]{widthResolution, heightResolution});
        setNearFarPlanes(new double[]{nearPlane, farPlane, blurplane});
    }

    /**
     * Instantiates a new Camera.
     *
     * @param position              the position
     * @param fieldOfViewHorizontal the field of view horizontal
     * @param fieldOfViewVertical   the field of view vertical
     * @param widthResolution       the width resolution
     * @param heightResolution      the height resolution
     * @param nearPlane             the near plane
     * @param farPlane              the far plane
     */
    public Camera(Vector3D position, double fieldOfViewHorizontal, double fieldOfViewVertical,
                  int widthResolution, int heightResolution, double nearPlane, double farPlane) {
        super(position);
        setFieldOfViewHorizontal(fieldOfViewHorizontal);
        setFieldOfViewVertical(fieldOfViewVertical);
        setResolution(new int[]{widthResolution, heightResolution});
        setNearFarPlanes(new double[]{nearPlane, farPlane, farPlane});
    }

    /**
     * Get near far planes double [ ].
     *
     * @return the double [ ]
     */
    public double[] getNearFarPlanes() {
        return nearFarPlanes;
    }

    private void setNearFarPlanes(double[] nearFarPlanes) {
        this.nearFarPlanes = nearFarPlanes;
    }

    /**
     * Get field of view double [ ].
     *
     * @return the double [ ]
     */
    public double[] getFieldOfView() {
        return fieldOfView;
    }

    private void setFieldOfView(double[] fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    /**
     * Gets field of view horizontal.
     *
     * @return the field of view horizontal
     */
    public double getFieldOfViewHorizontal() {
        return fieldOfView[0];
    }

    /**
     * Sets field of view horizontal.
     *
     * @param fieldOfViewHorizontal the field of view horizontal
     */
    public void setFieldOfViewHorizontal(double fieldOfViewHorizontal) {
        fieldOfView[0] = fieldOfViewHorizontal;
    }

    /**
     * Gets field of view vertical.
     *
     * @return the field of view vertical
     */
    public double getFieldOfViewVertical() {
        return fieldOfView[1];
    }

    /**
     * Sets field of view vertical.
     *
     * @param fieldOfViewVertical the field of view vertical
     */
    public void setFieldOfViewVertical(double fieldOfViewVertical) {
        fieldOfView[1] = fieldOfViewVertical;
    }

    /**
     * Gets default z.
     *
     * @return the default z
     */
    public double getDefaultZ() {
        return defaultZ;
    }

    /**
     * Sets default z.
     *
     * @param defaultZ the default z
     */
    public void setDefaultZ(double defaultZ) {
        this.defaultZ = defaultZ;
    }

    /**
     * Get resolution int [ ].
     *
     * @return the int [ ]
     */
    public int[] getResolution() {
        return resolution;
    }

    /**
     * Gets resolution width.
     *
     * @return the resolution width
     */
    public int getResolutionWidth() {
        return resolution[0];
    }

    /**
     * Gets resolution height.
     *
     * @return the resolution height
     */
    public int getResolutionHeight() {
        return resolution[1];
    }

    private void setResolution(int[] resolution) {
        this.resolution = resolution;
    }

    /**
     * Calculate positions to ray vector 3 d [ ] [ ].
     *
     * @return the vector 3 d [ ] [ ]
     */
    public Vector3D[][] calculatePositionsToRay() {
        double angleMaxX = getFieldOfViewHorizontal() / 2.0;
        double radiusMaxX = getDefaultZ() / (double) Math.cos(Math.toRadians(angleMaxX));

        double maxX = (double) Math.sin(Math.toRadians(angleMaxX)) * radiusMaxX;
        double minX = -maxX;

        double angleMaxY = getFieldOfViewVertical() / 2.0;
        double radiusMaxY = getDefaultZ() / (double) Math.cos(Math.toRadians(angleMaxY));

        double maxY = (double) Math.sin(Math.toRadians(angleMaxY)) * radiusMaxY;
        double minY = -maxY;

        Vector3D[][] positions = new Vector3D[getResolutionWidth()+1][getResolutionHeight()+1];
        double posZ = getDefaultZ();

        for (int x = 0; x < positions.length; x++) {
            for (int y = 0; y < positions[x].length; y++) {
                double posX = minX + (((maxX - minX) / (double) getResolutionWidth()) * x);
                double posY = maxY - (((maxY - minY) / (double) getResolutionHeight()) * y);
                positions[x][y] = new Vector3D(posX, posY, posZ);
            }
        }

        return positions;
    }

}
