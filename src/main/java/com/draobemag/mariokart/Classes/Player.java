package com.draobemag.mariokart.Classes;

import com.draobemag.mariokart.GlobalDefine;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.util.Random;

public class Player {
    int position;
    Image sprite;
    String name;
    int money;
    private Label player_label;
    int minigameWins;
    int paidPaywalls;

    public Player(Image sprite, int money, String name) {
        this.sprite = sprite;
        position = 0;
        this.money = money;
        this.name = name;
        minigameWins = 0;
        paidPaywalls = 0;
    }

    public Image getSprite() {
        return this.sprite;
    }
    public void setSprite(int sprite_ind) {
        //must be between 1 & 4
        this.sprite = new Image(GlobalDefine.sprites.get(sprite_ind - 1), 40, 40, false, false);
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
        this.money = (money < 0) ? 0 : money;
    }
    public void addMoney(int money) {
        this.money = (this.money + money < 0) ? 0 : this.money + money;
    }

    public int getMinigameWins() {
        return minigameWins;
    }

    public void addMinigameWin() {
        minigameWins++;
    }
    public void setMinigameWins(int minigameWins)
    {
        this.minigameWins = minigameWins;
    }

    public void addPaidPaywall() {
        paidPaywalls++;
    }

    public int getPaidPaywalls() {
        return paidPaywalls;
    }

    public void setPaidPaywalls(int paidPaywalls) {
        this.paidPaywalls = paidPaywalls;
    }
}
