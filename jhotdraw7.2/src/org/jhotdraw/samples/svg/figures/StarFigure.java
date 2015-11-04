/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.samples.svg.figures;

import java.awt.Polygon;

/**
 *
 * @author Mads
 */
public class StarFigure extends Polygon implements Cloneable {
    int x;
    int y;
    int r;
    int innerR;
    int vertexCount;
    double startAngle;
    
    public StarFigure(int x, int y, int r, int innerR, int vertexCount) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.innerR = innerR;
        this.vertexCount = vertexCount;
        startAngle = 0;
    }

    public StarFigure(int x, int y, int r, int innerR, int vertexCount, double startAngle) {
        super(getXCoordinates(x, y, r, innerR, vertexCount, startAngle), getYCoordinates(x, y, r, innerR, vertexCount, startAngle), vertexCount * 2);
    }

    protected static int[] getXCoordinates(int x, int y, int r, int innerR, int vertexCount, double startAngle) {
        int res[] = new int[vertexCount * 2];
        double addAngle = 2 * Math.PI / vertexCount;
        double angle = startAngle;
        double innerAngle = startAngle + Math.PI / vertexCount;
        for (int i = 0; i < vertexCount; i++) {
            res[i * 2] = (int) Math.round(r * Math.cos(angle)) + x;
            angle += addAngle;
            res[i * 2 + 1] = (int) Math.round(innerR * Math.cos(innerAngle)) + x;
            innerAngle += addAngle;
        }
        return res;
    }

    protected static int[] getYCoordinates(int x, int y, int r, int innerR, int vertexCount, double startAngle) {
        int res[] = new int[vertexCount * 2];
        double addAngle = 2 * Math.PI / vertexCount;
        double angle = startAngle;
        double innerAngle = startAngle + Math.PI / vertexCount;
        for (int i = 0; i < vertexCount; i++) {
            res[i * 2] = (int) Math.round(r * Math.sin(angle)) + y;
            angle += addAngle;
            res[i * 2 + 1] = (int) Math.round(innerR * Math.sin(innerAngle)) + y;
            innerAngle += addAngle;
        }
        return res;
    }
    @Override
    public Object clone() {
	try {
	    return super.clone();
	} catch (CloneNotSupportedException e) {
	    throw new InternalError();
	}
    }
}