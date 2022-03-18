package com.example.bottleui;// WEEK 3 T 1 OPNRO 0567643 //
import android.widget.TextView;
import java.util.ArrayList;


public class BottleDispenser {

    private static BottleDispenser dispenser = new BottleDispenser();
    public ArrayList<Bottle> bottle_arraylist = new ArrayList<>();
    private double money;

    private BottleDispenser() {
        money = 0;
        // Add Bottle-objects to the arraylist
        bottle_arraylist.add(new Bottle("Pepsi Max", "Pepsi" , 1.5, 2.2));
        bottle_arraylist.add(new Bottle("Pepsi Max", "Pepsi" , 1.5, 2.2));
        bottle_arraylist.add(new Bottle("Pepsi Max", "Pepsi" , 1.5, 2.2));
        bottle_arraylist.add(new Bottle("Pepsi Max", "Pepsi" , 1.5, 2.2));
        bottle_arraylist.add(new Bottle("Coca-Cola Zero", "Coca-Cola" , 0.5, 2.0));
        bottle_arraylist.add(new Bottle("Coca-Cola Zero", "Coca-Cola" , 1.5, 2.5));
        bottle_arraylist.add(new Bottle("Fanta Zero", "Coca-Cola" , 0.5, 1.95));
    }

    public static BottleDispenser getInstance(){
        if (dispenser == null){
            dispenser = new BottleDispenser();
        }
        return dispenser;
    }

    public void addmoreMoney(TextView text, int moneyAmount){
        money = money + moneyAmount;
        String s = "Klink! Added " + moneyAmount + "!";
        text.setText(s);
    }

    public void buyBottle(TextView text, Bottle x) {

        try {
            if (money < x.getPrize()){
                String s = "Add money first!";
                text.setText(s);
            }else {
                money -= x.getPrize();
                String s = "KACHUNK! " + x.getName() + " came out of the dispenser!";
                text.setText(s);
            }

        } catch (IndexOutOfBoundsException exception){
            String s = "Add money first!";
            text.setText(s);
        }


    }

    public void returnMoney(TextView text) {
        if (money != 0){
            String s = "Klink klink. Money came out! You got "+ String.format("%,2f", money) + "€ back";
            text.setText(s);
            money = 0;
        }else {
            System.out.println("Klink klink!! All money gone!");
        }
    }
    public ArrayList<Bottle> readDispenser(){
        return bottle_arraylist;
    }
    public double getmoney(){ return money; }
}

