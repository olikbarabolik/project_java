package ru.stqa.pft.sandbox;

public class Point {
    public int x1,y1;
    public double rasst;

    public Point(int x, int y){
        this.x1 = x;
        this.y1 = y;
    }

    public double distance(Point p){
        /*System.out.println("Point 1 =" + p.x1 + " " + p.y1);
        System.out.println("Point 2 =" + this.x1 + " " + this.y1);*/
        rasst = Math.sqrt( Math.pow((p.x1-this.x1),2)+ Math.pow((p.y1-this.y1),2) );
        return rasst;
    }
}
