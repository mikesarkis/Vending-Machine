/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Mike
 */
public class Change {
    BigDecimal amount;
    public Change(int amount)  // will take the change amount as int and convert it to BigDecimal and assign it to amount
    {
        String s = String.valueOf(amount);
        BigDecimal a = new BigDecimal(s);
        this.amount = a.setScale(2, RoundingMode.HALF_UP);
    }
    public List<Coin> get_all_coins(BigDecimal amount)  // will return a list that conatin all the coins in the change 
    {
        List<Coin> List_of_all_Coins = new ArrayList<>();
        for(Coin coin : Coin.values())
        {
            String c = String.valueOf(coin.getValue());
            BigDecimal v = new BigDecimal(c);
            BigDecimal value = v.setScale(2, RoundingMode.HALF_UP);
            while (amount.compareTo(value)>=0)
            {
                amount= amount.subtract(value);
                List_of_all_Coins.add(coin);
            }
        }
        return List_of_all_Coins;
    }
    public List<Coin> get_all_quarters(List<Coin> coins)  // will return a list that conatin all the quarters in the change 
    {
        return  coins.stream().filter((c)->c.getValue()== 25).collect(Collectors.toList());
    }
    public List<Coin> get_all_dimes(List<Coin> coins)  // will return a list that conatin all the dimes in the change 
    {
        return  coins.stream().filter((c)->c.getValue()== 10).collect(Collectors.toList());
    }
    public List<Coin> get_all_nickels(List<Coin> coins) // will return a list that conatin all the nickels in the change 
    {
        return  coins.stream().filter((c)->c.getValue()== 5).collect(Collectors.toList());
    }
    public List<Coin> get_all_pennies(List<Coin> coins) // will return a list that conatin all the pennies in the change 
    {
        return  coins.stream().filter((c)->c.getValue()== 1).collect(Collectors.toList());
    }   
    
}
