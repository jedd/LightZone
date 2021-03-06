/* Copyright (C) 2005-2011 Fabio Riccardi */

package com.lightcrafts.ui.region.curves;

import java.util.ArrayList;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.*;

/** An AbstractCurve whose updateShape() generates a closed quadratic
  * B-spline.
  */
public class QuadraticBasisSpline extends AbstractCurve {

    static ArrayList Bases = new ArrayList();

    static void ensureBases(int count) {
        while (Bases.size() < count) {
            int i = Bases.size();
            BasisFunction basis = new QuadraticBasisFunction(i);
            Bases.add(basis);
        }
    }

    // Update "shape" and "segments" from the current value of "points":
    void updateShape() {
        if (points.size() < 2) {
            shape = null;
            segments.clear();
            return;
        }
        // Pad out the control point list using wraparound,
        // to make a closed spline:

        ArrayList extraPoints = new ArrayList(points);
        extraPoints.add(0, points.get(points.size() - 1));
        extraPoints.add(points.get(0));

        GeneralPath path = new GeneralPath();

        int m = extraPoints.size();
        ensureBases(m);

        segments.clear();

        // Iterate over segments:
        for (int i=2; i<m; i++) {

            Polynomial xPoly = new Polynomial(0);
            Polynomial yPoly = new Polynomial(0);

            // Iterate over control points:
            for (int j=0; j<m; j++) {

                Point2D p = (Point2D) extraPoints.get(j);

                BasisFunction basis = (BasisFunction) Bases.get(j);

                double px = p.getX();
                double py = p.getY();

                Polynomial xSeg = basis.getSegment(i).multiply(px);
                Polynomial ySeg = basis.getSegment(i).multiply(py);

                xPoly = xPoly.add(xSeg);
                yPoly = yPoly.add(ySeg);
            }
            xPoly = xPoly.translate(i);
            yPoly = yPoly.translate(i);

            Shape curve = Polynomial.createShape(xPoly, yPoly);
            segments.add(curve);
            path.append(curve, true);
        }
        path.closePath();
        shape = path;
    }
}
