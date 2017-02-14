package tranzmate.exercise;

import java.util.*;

public class RectanglesStore implements IRectanglesStore {

    Map<Integer, LinkedList<IRectangle>> x_axis;
    Map<Integer, LinkedList<IRectangle>> y_axis;

    /**
     * implementation from IRectanglesStore
     * by given rectangles and bounds will store the rectangles at the bounded area
     * x_axis and y_axis represented by map with K - the axis index, V - the co-responded rectangles list
     * for example for rectangles: R1 - (bottom=2, left=2, top=4, right=4)
     *                             R2 - (bottom=3, left=3, top=5, right=5)
     * x_axis:  { 2->R1 ; 3->R1,R2 ; 4->R1,R2 ; 5->R2 }
     * y_axis:  { 2->R1 ; 3->R1,R2 ; 4->R1,R2 ; 5->R2 }
     * later for a given point, for example (2,3) we will take the intersection for the relevant axis:
     * x_axis(x=2): {2->R1} intersection with y_axis(y=3): {3->R1,R2}  ==> R1, this will be the result
     * and another example at point (3,3):
     * x_axis(x=3): {3->R1,R2} intersection with y_axis(y=3): {3->R1,R2}  ==> R1,R2 the result will be with the biggest top
     * @param bounds - area bounds
     * @param rectangles - all rectangles collection
     */
    @Override
    public void initialize(IRectangle bounds, Collection<IRectangle> rectangles) {
        if (bounds==null || rectangles==null){
            System.out.println("your bounds or your rectangle collections hasn't been initialized");
            return; //can be added logger or consider relevant exception
        }

        x_axis = new HashMap<>();
        y_axis = new HashMap<>();

        for (IRectangle rec : rectangles) {
            if (isRecAtBounds(rec,bounds)) {
                storeRectanglesTo_X_Axis(x_axis, rec);
                storeRectanglesTo_Y_Axis(y_axis, rec);
            }
        }
    }

    /**
     * insert rectangle to the X axis
     * @param x_axis - the x axis represent by Map<Integer, LinkedList<IRectangle>>
     * @param rec - the rec that should be added to the x_axis
     */
    private void storeRectanglesTo_X_Axis(Map<Integer, LinkedList<IRectangle>> x_axis, IRectangle rec) {
        for (int i = rec.getLeft(); i <= rec.getRight(); i++) {
            if (!x_axis.containsKey(i)) {
                x_axis.put(i, new LinkedList<>());
                x_axis.get(i).add(rec);
            } else {
                x_axis.get(i).add(rec);
            }
        }
    }

    /**
     * insert rectangle to the Y axis
     * @param y_axis - the y axis represent by Map<Integer, LinkedList<IRectangle>>
     * @param rec - the rec that should be added to the y_axis
     */
    private void storeRectanglesTo_Y_Axis(Map<Integer, LinkedList<IRectangle>> y_axis, IRectangle rec) {
        for(int i=rec.getBottom(); i<=rec.getTop(); i++) {
            if (!y_axis.containsKey(i)) {
                y_axis.put(i, new LinkedList<>());
                y_axis.get(i).add(rec);
            } else {
                y_axis.get(i).add(rec);
            }
        }
    }

    /**
     * implementation from IRectanglesStore
     * by given point (x,y) will return the topmost rectangle
     * intersection between x_axis and y_axis will give all relevant rectangles for that specific point.
     * for example if x_axis => (R1,R2,R3) and y_axis=> (R1,R2,R5)
     * intersection between them will be intersectionSet => (R1,R2)
     * for point (x,y) the topmost rectangle can be R1 or R2
     * @param x - x axis
     * @param y - y axis
     * @return the topmost rectangle
     */
    @Override
    public IRectangle findRectangleAt(int x, int y) {
        if (x_axis.get(x)==null || y_axis.get(y)==null) {
            return null;
        }
        Set<IRectangle> xSet = new HashSet<>(x_axis.get(x));
        Set<IRectangle> ySet = new HashSet<>(y_axis.get(y));
        Set<IRectangle> intersectionSet = new HashSet<>(xSet);
        intersectionSet.retainAll(ySet);
        return getTopMost(intersectionSet);
    }

    /***
     * find the topmost rectangle within a set of rectangles.
     * @param set - rectangles set
     * @return the topmost rectangle
     */
    private IRectangle getTopMost(Set<IRectangle> set) {
        IRectangle res = null;
        int max = Integer.MIN_VALUE ;
        for (IRectangle rec:set){
            if (rec.getTop()>max){
                max = rec.getTop();
                res = rec;
            }
        }
        return res;
    }

    /***
     * check if the rectangle located inside the bounds
     * @param rec - the rectangle
     * @param bounds - the bounds
     * @return true if the rectangle inside the bounds and flase otherwise
     */
    private boolean isRecAtBounds(IRectangle rec,IRectangle bounds){
        return (rec.getRight()<=bounds.getRight() &&
                rec.getLeft()>=bounds.getLeft() &&
                rec.getTop()<=bounds.getTop() &&
                rec.getBottom()>=bounds.getBottom());
    }
}
