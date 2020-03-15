package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Task3 {

    @Test
    public void correctTest(){
        Point p1 = new Point(3,5);
        Point p2 = new Point(7,9);
        System.out.println("!!!! Running test 1");
        //Корректный тест
        Assert.assertEquals(p1.distance(p2), 5.656854249492381);
    }

    @Test
    public void failTest(){
        Point p1 = new Point(3,5);
        Point p2 = new Point(7,9);
        System.out.println("!!!! Running test 2");
        //Зафейленный тест
        Assert.assertEquals(p1.distance(p2), 2);
    }
}
