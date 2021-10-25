package com.draobemag.mariokart;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GlobalDefine {
    public static String marioUrl = "file:src/main/resources/images/mario_sprite.png";
    public static String luigiUrl = "file:src/main/resources/images/luigi_sprite.jpg";
    public static String bowserUrl = "file:src/main/resources/images/bowser_sprite.png";
    public static String peachUrl = "file:src/main/resources/images/peach_sprite.png";
    public static int payWallPrice = 400;
    public static ArrayList<String> sprites = new ArrayList<String>() {
        {
            add(marioUrl);
            add(luigiUrl);
            add(bowserUrl);
            add(peachUrl);
        }
    };;
    public static int boardMaxL = 12;
    public static Point[] coords = new Point[] {
            new Point(1,1),
            new Point(2,1),
            new Point(3,1),
            new Point(4,1),
            new Point(5,1),
            new Point(5,2),
            new Point(6,2),
            new Point(7,2),
            new Point(7,1),
            new Point(8,1),
            new Point(9,1),
            new Point(9,2),
            new Point(10,2),
            new Point(10,3),
            new Point(10,4),
            new Point(11,4),
            new Point(11,5),
            new Point(11,6),
            new Point(10,6),
            new Point(10,7),
            new Point(10,8),
            new Point(11,8),
            new Point(11,9),
            new Point(11,10),
            new Point(11,11),
            new Point(10,11),
            new Point(9,11),
            new Point(8,11),
            new Point(8,10),
            new Point(7,10),
            new Point(7,9),
            new Point(7,8),
            new Point(8,8),
            new Point(8,7),
            new Point(8,6),
            new Point(8,5),
            new Point(8,4),
            new Point(7,4),
            new Point(6,4),
            new Point(6,5),
            new Point(6,6),
            new Point(5,6),
            new Point(5,7),
            new Point(5,8),
            new Point(5,9),
            new Point(5,10),
            new Point(4,10),
            new Point(3,10),
            new Point(3,9),
            new Point(3,8),
            new Point(2,8),
            new Point(2,7),
            new Point(2,6),
            new Point(3,6),
            new Point(3,5),
            new Point(3,4),
            new Point(3,3),
            new Point(2,3),
            new Point(1,3),
            new Point(1,2)
    };
}
