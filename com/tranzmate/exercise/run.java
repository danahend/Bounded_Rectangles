package tranzmate.exercise;

import java.util.*;

public class run {


    public static void main(String[] args) {

        IRectangle rec1 = new Rectangle(2,2,4,4);
        IRectangle rec2 = new Rectangle(3,3,5,5);
        IRectangle rec3 = new Rectangle(2,1,5,6);
        IRectangle rec4 = new Rectangle(2,6,5,7);
        IRectangle rec5 = new Rectangle(6,0,7,7);
        IRectangle rec6 = new Rectangle(0,0,8,8);

        Collection<IRectangle> rectangles = new LinkedList<>();
        rectangles.add(rec1);
        rectangles.add(rec2);
        rectangles.add(rec3);
        rectangles.add(rec4);
        rectangles.add(rec5);
        rectangles.add(rec6);

        IRectanglesStore recStore = new RectanglesStore();
        recStore.initialize(new Rectangle(0,0,7,7), rectangles);
        System.out.println(print(recStore.findRectangleAt(4,4)));
        System.out.println(print(recStore.findRectangleAt(2,2)));
        System.out.println(print(recStore.findRectangleAt(1,1)));
        System.out.println(print(recStore.findRectangleAt(3,1)));
        System.out.println(print(recStore.findRectangleAt(3,7)));
        System.out.println(print(recStore.findRectangleAt(7,7)));
    }


    private static String print(IRectangle rec){
        return (rec == null)? "null" : rec.toString();
    }
}
