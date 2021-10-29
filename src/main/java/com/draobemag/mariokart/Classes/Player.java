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
    private Label player_label;

    public Player(Image sprite, int money, String name) {
        this.sprite = sprite;
        position = 0;
        this.money = money;
        this.name = name;
    }

    public Image getSprite() {
        return this.sprite;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    // Returns true if the game is over, false otherwise
    public boolean move(int amount) {
        position += amount;
        return position >= GlobalDefine.coords.length - 1;
    }

    public void setLabel(Label label) {
        this.player_label = label;
    }

    public void updateLabel() {
        this.player_label.setText(this.name + ": "
                + String.valueOf(this.money)  + " kmph");
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
    public void setMoney(int money)
    {
        this.money = money;
    }
}
