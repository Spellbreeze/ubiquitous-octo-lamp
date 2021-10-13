package com.draobemag.mariokart.Classes;

import com.draobemag.mariokart.GlobalDefine;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.util.Random;

public class Player {
    int position;
    Image sprite;
    String name;
    int money;
    private javafx.scene.control.Label player_label;

    public Player(Image sprite, int money, String name) {
        this.sprite = sprite;
        position = 0;
        this.player_label = new javafx.scene.control.Label(this.name + ": " + String.valueOf(this.money));
        this.money = money;
        this.name = name;
    }

    public Image getSprite() {
        return this.sprite;
    }

    public int getPosition() {
        return this.position;
    }

    public void move() {
        if (position == GlobalDefine.coords.length - 1) {
            position = 0;
        } else {
            position += 1;
        }
    }

    public void updateMoney() {
        if (position % 12 == 0) {
            Random rand = new Random();
            int val = rand.nextInt(30);
            if (val % 2 == 0) {
                this.money -= val;
            } else {
                this.money += val;
            }
        } else if (position % 2 == 0) {
            this.money -= 5;
        } else {
            this.money += 5;
        }
    }

    public void updateLabel() {
        this.player_label.setText(this.name + ": " + String.valueOf(this.money));
    }

    public javafx.scene.control.Label getLabel() {
        return this.player_label;
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
