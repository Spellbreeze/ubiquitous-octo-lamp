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

<<<<<<< HEAD
    public boolean move(int amount) {
        if (position + amount <= GlobalDefine.coords.length-1) {
            position += amount;
        } else {
            position = GlobalDefine.coords.length-1;
        }
        /*
        if (position >= GlobalDefine.coords.length - 1) {
            position -= GlobalDefine.coords.length - 1;
        }
        position += amount;
         */
        return false;
    }

    public void updateMoney() {
        if (position % 12 == 0) {
            Random rand = new Random();
            int val = rand.nextInt(10);
            if (this.money > 100) {
                this.money -= val + 10;
            } else {
                this.money += val + 10;
            }
        } else if (position % 2 == 0) {
            this.money -= 5;
        } else {
            this.money += 5;
        }
=======
    // Returns true if the game is over, false otherwise
    public boolean move(int amount) {
        position += amount;
        return position >= GlobalDefine.coords.length - 1;
>>>>>>> 4df051c28bc4e525557f54096faa00a501200fe2
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
