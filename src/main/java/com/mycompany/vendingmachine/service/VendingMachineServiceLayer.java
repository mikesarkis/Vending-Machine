/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.Coin;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface VendingMachineServiceLayer {
     public  int VendItem(int id, int amount)throws InsufficientFundsException,NoItemInventoryException,VendingMachinePersistenceException;
     public List<Item> getallItems() throws VendingMachinePersistenceException;
     public String getItemName(int id)throws NoItemInventoryException,VendingMachinePersistenceException;
     public Item getItemObject(int id) throws NoItemInventoryException,VendingMachinePersistenceException;
     public List<Coin> get_all_quarters( int amount);
     public List<Coin> get_all_dimes( int amount);
     public List<Coin> get_all_nickels( int amount);
     public List<Coin> get_all_pennies(int amount);
     public boolean CheckItem(int id) throws VendingMachinePersistenceException, NoItemInventoryException;
    
}
