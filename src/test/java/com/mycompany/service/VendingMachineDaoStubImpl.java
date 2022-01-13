/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.vendingmachine.dao.Change;
import com.mycompany.vendingmachine.dao.Coin;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mike
 */
@Component
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    public Item Item1;
    public Item Item2;
    @Autowired
    public VendingMachineDaoStubImpl()
    {
        int ID = 1;
        String Name = "Sprite";
        int cost = 251;
        int quantity = 40;
        Item1 = new Item(ID,Name,cost,quantity);
        int ID2 = 2;
        String Name2 = "Fanta";
        int cost2 = 240;
        int quantity2 = 0;
        Item2 = new Item(ID2,Name2,cost2,quantity2);
    }

    @Override
    public int VendItem(int id, int amount) throws VendingMachinePersistenceException {
        int change =-1;
        if(this.Item1.getID()==id)
        {
            change = amount - Item1.getCost();
            return change;
        }
        if(this.Item2.getID()==id)
        {
            change = amount - Item2.getCost();
            return change;
        }
        else
             return change;
            
        
    }

    @Override
    public List<Item> getallItems() throws VendingMachinePersistenceException {
        List<Item> list1= new ArrayList<>(); 
        list1.add(Item1);
        list1.add(Item2);
        return list1;
    }

    @Override
    public String getItemName(int id) throws VendingMachinePersistenceException {
         if(Item1.getID()==id )
         {
             return Item1.getName();
         }
         else
             return null;
    }

    @Override
    public Item getItemObject(int id) throws VendingMachinePersistenceException {
         if(Item1.getID()==id )
         {
             return Item1;
         }
         else
             return null;
    }

    @Override
    public List<Coin> get_all_quarters(int amount) {
        Change change = new Change(25);
        List<Coin> list1 = new ArrayList<>();
        if(amount == 25)
        {
        String s = String.valueOf(amount);
        BigDecimal a = new BigDecimal(s);
        BigDecimal value = a.setScale(2,RoundingMode.HALF_UP);
        list1 = change.get_all_coins(value);
        }
        return change.get_all_quarters(list1);
    }

    @Override
    public List<Coin> get_all_dimes(int amount) {
        Change change = new Change(10);
        List<Coin> list1 = new ArrayList<>();
        if(amount == 10)
        {
        String s = String.valueOf(amount);
        BigDecimal a = new BigDecimal(s);
        BigDecimal value = a.setScale(2,RoundingMode.HALF_UP);
        list1 = change.get_all_coins(value);
        }
        return change.get_all_dimes(list1) ;
        
    }

    @Override
    public List<Coin> get_all_nickels(int amount) {
        Change change = new Change(5);
        List<Coin> list1 = new ArrayList<>();
        if(amount == 5)
        {
        String s = String.valueOf(amount);
        BigDecimal a = new BigDecimal(s);
        BigDecimal value = a.setScale(2,RoundingMode.HALF_UP);
        list1 = change.get_all_coins(value);
        }
        return change.get_all_nickels(list1);
        
    }

    @Override
    public List<Coin> get_all_pennies(int amount) {
        Change change = new Change(1);
        List<Coin> list1 = new ArrayList<>();
        if(amount == 1)
        {
        String s = String.valueOf(amount);
        BigDecimal a = new BigDecimal(s);
        BigDecimal value = a.setScale(2,RoundingMode.HALF_UP);
        list1 = change.get_all_coins(value);
        }
        return change.get_all_pennies(list1);
        
    }
    
}
