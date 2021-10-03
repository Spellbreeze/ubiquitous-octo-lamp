package com.draobemag.mariokart;

import java.util.ArrayList;

public class GlobalDefine {
    public static String marioUrl = "file:src/main/resources/images/mario_sprite.png";
    public static String luigiUrl = "file:src/main/resources/images/luigi_sprite.jpg";
    public static String bowserUrl = "file:src/main/resources/images/bowser_sprite.png";
    public static String peachUrl = "file:src/main/resources/images/peach_sprite.png";
    public static ArrayList<String> sprites = new ArrayList<String>() {
        {
            add(marioUrl);
            add(luigiUrl);
            add(bowserUrl);
            add(peachUrl);
        }
    };;
}
