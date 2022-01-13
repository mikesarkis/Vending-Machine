/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.Controller;

import com.mycompany.vendingmachine.dao.Coin;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.NoItemInventoryException;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayer;
import com.mycompany.vendingmachine.ui.VendingMachineView;
import com.mycompany.vendingmachine.dto.Item;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mike
 */
@Component
public class VendingMachineController {
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    @Autowired
    public VendingMachineController(VendingMachineServiceLayer service,VendingMachineView view )
    {
        this.service= service;
        this.view=view;
    }
    public void run() throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException
    {
        int choice;
        while(true)
        {
            choice = getChoice();
            if(choice ==0)
            {
            exitApp();
            }   
            else
            {
            VendItem(choice);
            }
        }

        
    }
    public int getChoice() throws VendingMachinePersistenceException // will display the menu and get the user choice 
    {
        List<Item> list = service.getallItems();
        return view.DisplayItemsMenu(list);
    }
    public void exitApp()  // will exit the app 
    {
        view.exitAppview();
    }
    public void VendItem(int choice) throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException // will vend the item and display the change 
    {
        int amount;
        int change1;
        boolean available = service.CheckItem(choice);
        if(available)
        {
        amount = view.askForMoney();
        change1 = service.VendItem(choice, amount);
        if(change1 >0)
        {
        System.out.println(change1);
         view.displayitemName(service.getItemName(choice));
            List<Coin> list1 = service.get_all_quarters(change1);
            List<Coin> list2 = service.get_all_dimes(change1);
            List<Coin> list3 = service.get_all_nickels(change1);
            List<Coin> list4 = service.get_all_pennies(change1);
            view.DisplayChange(change1, list1, list2, list3, list4);
        }
        }
    }
}
