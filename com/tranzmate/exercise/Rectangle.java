package tranzmate.exercise;

import java.util.Properties;

public class Rectangle implements IRectangle{
    int left;
    int bottom;
    int right;
    int top;
    Properties properties;


    public Rectangle(int left, int bottom, int right, int top){
        this.left = left;
        this.bottom = bottom;
        this.right =right;
        this.top = top;
    }

    public String toString(){
        return "(" + this.getLeft() + ", "
                + this.getBottom() + ", "
                + this.getRight() + ", "
                + this.getTop() + ")";
    }

    @Override
    public int getLeft() {
        return left;
    }

    @Override
    public int getTop() {
        return top;
    }

    @Override
    public int getRight() {
        return right;
    }

    @Override
    public int getBottom() {
        return bottom;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

}
