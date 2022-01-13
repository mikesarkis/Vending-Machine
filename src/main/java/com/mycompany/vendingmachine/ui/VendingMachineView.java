/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.ui;

import com.mycompany.vendingmachine.dao.Coin;
import com.mycompany.vendingmachine.dto.Item;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mike
 */
@Component
public class VendingMachineView {
    private UserIO io;
    @Autowired
    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }
    public int DisplayItemsMenu(List<Item> Items_list)  // display the menu and get the user choice 
    {
        io.print("Items Menue :");
        for (Item currentItem : Items_list)
        {
            if(currentItem.getQuantity()>0)
            {
            String info = String.format("%s - %s , %s : %s , %s : %s",String.valueOf(currentItem.getID()), 
                    currentItem.getName(),
                    "price",
                    String.valueOf(currentItem.getCost()),
                    "quantity",
                    String.valueOf(currentItem.getQuantity()));
            io.print(info);
            }
            else
            {
                    String info = String.format("%s - %s , %s : %s , %s ",String.valueOf(currentItem.getID()), 
                    currentItem.getName(),
                    "price",
                    String.valueOf(currentItem.getCost()),
                    "out of stock");
            io.print(info);
            }
          
        }
        io.print("you can choose an item by entering the item number and to exit please enter 0");
        return io.readInt("please enter your choice");
    }
    public void displayitemName(String name)
    {
        io.print("vending,  "+name);
    }
    public int askForMoney()  // ask the user to enter an amount 
    {
       return io.readInt("please enter the amount of money  in order to choose an item (the amount shoulb be in US cents)");
    }
    public void NotEnough(int amount)
    {
        io.print("the amount that you entered is not enough to buy the selected item");
        io.print(String.valueOf(amount));
    }
    public void DisplayChange(int amount, List<Coin> coins_quarter, List<Coin> coins_dime,List<Coin> coins_nickel, List<Coin> coins_penny) // will display the change as coins 
    {
        if(amount ==0)
        {
            io.print("the maount you entered is equall to the price of the item you have selected");
            io.print("vending the selected item");
        }
        else
        {
            int quarter_count = coins_quarter.size();
            int dime_count = coins_dime.size();
            int nickel_count = coins_nickel.size();
            int penny_count = coins_penny.size();
            io.print("the maount you entered is larger than  the price of the item you have selected you will receive a change");
            if(quarter_count>0)
            {
                io.print("you will receive "+String.valueOf(quarter_count)+ " quarters in change");
            }
            if(dime_count>0)
            {
                io.print("you will receive "+String.valueOf(dime_count)+ " dimes in change");
            }
            if(nickel_count>0)
            {
                io.print("you will receive "+String.valueOf(nickel_count)+ " nickels in change");
            }
            if(penny_count>0)
            {
                io.print("you will receive "+String.valueOf(penny_count)+ " pennies  in change");
            }
            io.print("vending the selected item");
        }
    }
    public void exitAppview() // will print exit message 
    {
        io.print("System will be terminated");
        System.exit(0);
    }
}
