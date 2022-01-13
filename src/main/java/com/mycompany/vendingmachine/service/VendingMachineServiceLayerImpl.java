/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.Coin;
import com.mycompany.vendingmachine.dao.VendingMachineAuditDao;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Item;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mike
 */
@Component
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    private VendingMachineAuditDao auditdao;
    VendingMachineDao dao;
    @Autowired
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditdao)
    {
        this.dao=dao;
        this.auditdao= auditdao;
    }
    private boolean ValdiateFunds(int id,int amount)throws InsufficientFundsException, VendingMachinePersistenceException{ // will check if the amount is larger than the item price 
        int price = dao.getItemObject(id).getCost();
       try{ if(amount < price)
        {
            throw new InsufficientFundsException("the amount that you entered is not enough to buy the selected item");
            
        }
       else 
           return true;}
        catch(InsufficientFundsException e)
                {
                System.out.println(e.getMessage());
                return false;
                }
    }
    private boolean ValdiateItem(int id) throws VendingMachinePersistenceException, NoItemInventoryException  // will check if the item already exist in the list 
    {
        Item temp = dao.getItemObject(id);
         
       try{ if(temp == null )
        {
           throw new  NoItemInventoryException("The item that you have selected dose not exist");
           
        }
       else 
           return true;}
               catch(NoItemInventoryException e)
                {          
                System.out.println(e.getMessage());
                 return false;
                }
        
               
               
    }
    private boolean ValdiateItemquantity(int id) throws VendingMachinePersistenceException, NoItemInventoryException // will check the quantity of the selected item 
    {
        int quantity = dao.getItemObject(id).getQuantity();
       try{ if(quantity<=0)
        {
            throw new NoItemInventoryException("The item that you have selected is out of stock");
        }
       else 
           return true;
        }catch (NoItemInventoryException e)
                {
                System.out.println(e.getMessage());
                return false;
                }
        
    }

    @Override
    public int VendItem(int id, int amount) throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException { // will vend the item after using the valdiation and return the change amount in cents 
       if(ValdiateFunds(id,amount) && ValdiateItemquantity(id)) {
        
        auditdao.writeAuditEntry("The item "+dao.getItemName(id)+" has been purchased.");
        return dao.VendItem(id, amount);
       }
       else 
           return -1;
    }
    public boolean CheckItem(int id) throws VendingMachinePersistenceException, NoItemInventoryException
    {
       if(ValdiateItem(id) && ValdiateItemquantity(id) )
               {
                   return true;
               }
       else
           return false;
    }

    @Override
    public List<Item> getallItems() throws VendingMachinePersistenceException {   // will display all items in the lsit 
         return dao.getallItems();
    }

    @Override
    public String getItemName(int id) throws NoItemInventoryException, VendingMachinePersistenceException { // will return the item name 
        ValdiateItem(id);
        return dao.getItemName(id);
    }

    @Override
    public Item getItemObject(int id) throws NoItemInventoryException, VendingMachinePersistenceException { // will return the item itself 
        ValdiateItem(id);
        return dao.getItemObject(id);
    }

    @Override
    public List<Coin> get_all_quarters(int amount) {  // will return all the quarters in the change 
        return dao.get_all_quarters(amount);
    }

    @Override
    public List<Coin> get_all_dimes(int amount) { // will return all the dimes in the change 
        return dao.get_all_dimes( amount);
    }

    @Override
    public List<Coin> get_all_nickels( int amount) { // will return all the nickels in the change 
        return dao.get_all_nickels(amount);
    }

    @Override
    public List<Coin> get_all_pennies( int amount) { // will return all the pennies in the change 
        return dao.get_all_pennies(amount);
    }
    
}
