/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import java.util.List;




/**
 *
 * @author Mike
 */
public interface VendingMachineDao {    
    public int VendItem(int id, int amount) throws VendingMachinePersistenceException;
    public List<Item> getallItems()throws VendingMachinePersistenceException;
    public String getItemName(int id) throws VendingMachinePersistenceException;
    public Item getItemObject(int id) throws VendingMachinePersistenceException;
    public List<Coin> get_all_quarters( int amount);
    public List<Coin> get_all_dimes(int amount);
    public List<Coin> get_all_nickels(int amount);
    public List<Coin> get_all_pennies(int amount);
}
