package com.draobemag.mariokart.Classes;

import javafx.scene.image.Image;

public class Player {
    int positionX;
    int positionY;
    Image sprite;
    String name;
    int money;

    public Player(Image sprite) {
        this.sprite = sprite;
        positionX = 0;
        positionY = 0;
    }

    public Image getSprite() {
        return this.sprite;
    }

    public int getX() {
        return this.positionX;
    }

    public int getY() {
        return this.positionY;
    }

    public void move() {
        if (positionX == 0 && positionY == 0) {
            positionY += 1;
        } else if (positionY < 9 && positionX == 0) {
            positionY += 1;
        } else if (positionY == 9 && positionX < 9) {
            positionX += 1;
        } else if (positionX == 9 && positionY > 0) {
            positionY -= 1;
        } else if (positionX > 0 && positionY == 0) {
            positionX -= 1;
        }
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public int getMoney()
    {
        return money;
    }
    public void setMoney()
    {
        this.money = money;
    }
}
