/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mike
 */
@Component
public class VendingMachineDaoImpl implements VendingMachineDao{
    private Map<Integer, Item> List_Item = new HashMap<>(); 
    private String FILE;
    @Autowired 
    public VendingMachineDaoImpl()
    {
        FILE = "inventory.txt";
    }
        public VendingMachineDaoImpl(String name)
    {
        FILE = name;
    }
    
    public static final String DELIMITER = "::";
     private Item unmarshallDVD(String text)
    {
        String[] ItemTokens = text.split(DELIMITER);
        int  id = Integer.parseInt(ItemTokens[0]);
        String name = ItemTokens[1];
        int price = Integer.parseInt(ItemTokens[2]);
        int quantity = Integer.parseInt(ItemTokens[3]);
        Item temp = new Item(id,name,price,quantity);
        return temp;

    }
    private void LoadLibrary()throws  FileNotFoundException , VendingMachinePersistenceException{
        Scanner scan; 
        try{
            scan = new Scanner(new BufferedReader(new FileReader(FILE)));
    } catch (FileNotFoundException e){
        throw new VendingMachinePersistenceException(
                " Could not load Library data into memory.", e);
        }
         String currentLine;
         Item currentItem;
         while(scan.hasNextLine())
         {
             currentLine = scan.nextLine();
             currentItem = unmarshallDVD(currentLine);
             List_Item.put(currentItem.getID(),currentItem);
         }
         scan.close();

    }
    private String marshallDVD(Item item){
        String Itemtext = String.valueOf(item.getID())+ DELIMITER;
        Itemtext += item.getName() + DELIMITER;
        Itemtext += String.valueOf(item.getCost()) + DELIMITER;
        Itemtext += String.valueOf(item.getQuantity()) + DELIMITER;
        return Itemtext;
    }
    private void writeLibrary() throws FileNotFoundException , VendingMachinePersistenceException{
        PrintWriter out;
        try {
        out = new PrintWriter(new FileWriter(FILE));
    } catch (IOException e) {
        throw new VendingMachinePersistenceException(
                "Could not save Item data.", e);
    }
        
        String Itemtxt;
        List<Item> list1 =  this.getallItems();
        for (Item current : list1)
        {
            Itemtxt = marshallDVD(current);
            out.println(Itemtxt);
            out.flush();
        }
        out.close();
        
    }

    @Override
    public int VendItem(int id, int amount) throws VendingMachinePersistenceException {
        int change;
        change = 0;
        try {
            LoadLibrary();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VendingMachineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        
            int quantity = List_Item.get(id).getQuantity();
            quantity--;
            change = amount- List_Item.get(id).getCost();
            List_Item.get(id).setQuantity(quantity);
        
        
        try {
            writeLibrary();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VendingMachineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return change;
    }

    @Override
    public List<Item> getallItems() throws VendingMachinePersistenceException {
         try {
            LoadLibrary();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VendingMachineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>(List_Item.values());
    }

    @Override
    public String getItemName(int id) throws VendingMachinePersistenceException{
        try {
            LoadLibrary();
        } catch ( FileNotFoundException | VendingMachinePersistenceException ex) {
            Logger.getLogger(VendingMachineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String name = List_Item.get(id).getName();
        try {
            writeLibrary();
        } catch (FileNotFoundException | VendingMachinePersistenceException ex) {
            Logger.getLogger(VendingMachineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    @Override
    public Item getItemObject(int id) {
                 try {
            LoadLibrary();
        } catch (FileNotFoundException | VendingMachinePersistenceException ex) {
            Logger.getLogger(VendingMachineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Item temp = List_Item.get(id);
        try {
            writeLibrary();
        } catch (FileNotFoundException | VendingMachinePersistenceException ex) {
            Logger.getLogger(VendingMachineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
                 
                 
    }

    @Override
    public List<Coin> get_all_quarters(int amount) {
        Change change= new Change(amount);
        List<Coin> list1= change.get_all_coins(change.amount);
        return change.get_all_quarters(list1);
    }

    @Override
    public List<Coin> get_all_dimes(int amount) {
        Change change= new Change(amount);
        List<Coin> list1= change.get_all_coins(change.amount);
        return change.get_all_dimes(list1);
    }

    @Override
    public List<Coin> get_all_nickels(int amount) {
        Change change= new Change(amount);
        List<Coin> list1= change.get_all_coins(change.amount);
        return change.get_all_nickels(list1);
    }

    @Override
    public List<Coin> get_all_pennies(int amount) {
        Change change= new Change(amount);
        List<Coin> list1= change.get_all_coins(change.amount);
        return change.get_all_pennies(list1);
    }


        
    
}
