/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.vendingmachine.service.NoItemInventoryException;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayer;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.mycompany.vendingmachine.dao.Coin;
import com.mycompany.vendingmachine.dao.VendingMachineAuditDao;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Item;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mike
 */


@Component
public class VendingMachineServiceLayerImplTest {
    @Autowired
    private VendingMachineServiceLayer service;
    public VendingMachineServiceLayerImplTest() {
    VendingMachineDao dao = new VendingMachineDaoStubImpl();
    VendingMachineAuditDao auditdao = new VendingMachineAuditDaoStubImpl();
    service = new VendingMachineServiceLayerImpl(dao,auditdao);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testVendItem() throws Exception {  // test valid vedning 
        int ID= 1;
        int amount= 255;
        try{
            service.VendItem(ID, amount);
        } catch (InsufficientFundsException
            |  NoItemInventoryException
            | VendingMachinePersistenceException e) {
        fail("Vedning was valid. No exception should have been thrown.");
        }
    }
     @Test
      public void unValidVedning() throws Exception{   // test unvalid vending
        int ID= 1;
        int amount= 200;
        try {
        service.VendItem(ID, amount);
        fail("Expected InsufficientFundsException was not thrown.");
        } catch (NoItemInventoryException  
                | VendingMachinePersistenceException e){
            fail("Incorrect exception was thrown.");
        }
        catch (InsufficientFundsException e){
            return;
        }
      }

       @Test
     public void testgetallItems() throws Exception{   // test if the system will return all objects 
        int ID= 1;
        String Name = "Sprite";
        int cost = 251;
        int quantity = 40;
        int ID2 =2;
        String Name2 = "Fanta";
        int cost2 = 240;
        int quantity2 = 0;
        List<Item> list1 = new ArrayList<>();
        Item item1 = new Item(ID,Name,cost,quantity);
        Item item2 = new Item(ID2,Name2,cost2,quantity2);
        list1.add(item1);
        list1.add(item2);
        List<Item> list2 = service.getallItems();
        assertEquals(2,list2.size());
        assertTrue(list2.contains(item1));
        assertTrue(list2.contains(item2));
        assertEquals(list1,list2);
    }
    @Test
    public void testGetItemName() throws Exception  // test if the system will return the item name 
       {
        int id = 1;
        String name1 = "Sprite";
        String name2 = service.getItemName(id);
        assertEquals(name1,name2);
      }
    @Test
    public void testGetObject() throws Exception  // test if the system will return teh correct object 
    {
        int ID= 1;
        String Name = "Sprite";
        int cost = 251;
        int quantity = 40;
        Item item = new Item(ID,Name,cost,quantity);
        Item item2 = service.getItemObject(ID);
        assertNotNull(item2);
        assertEquals(item,item2);
        
    }
    @Test
    public void test_get_all_quarters() throws Exception // test if the system will return teh correct list 
    {
        Coin coin = Coin.quarter;
        List<Coin> list1 = new ArrayList<>();
        list1.add(coin);
        List<Coin> list2 = service.get_all_quarters(25);
        assertEquals(1,list2.size());
        assertTrue(list2.contains(coin));
        assertEquals(list1,list2);
    }
    @Test 
    public void test_get_all_dimes () throws Exception // test if the system will return teh correct list  
    {
        Coin coin = Coin.dime;
        List<Coin> list1 = new ArrayList<>();
        list1.add(coin);
        List<Coin> list2 = service.get_all_dimes(10);
        assertEquals(1,list2.size());
        assertTrue(list2.contains(coin));
        assertEquals(list1,list2);
    }
    @Test 
    public void test_get_all_nickels () throws Exception // test if the system will return teh correct list  
    {
        Coin coin = Coin.nickel;
        List<Coin> list1 = new ArrayList<>();
        list1.add(coin);
        List<Coin> list2 = service.get_all_nickels(5);
        assertEquals(1,list2.size());
        assertTrue(list2.contains(coin));
        assertEquals(list1,list2);   
    }
    @Test
    public void test_get_all_pennies ()  throws Exception // test if the system will return teh correct list  
    {
        Coin coin = Coin.penny;
        List<Coin> list1 = new ArrayList<>();
        list1.add(coin);
        List<Coin> list2 = service.get_all_pennies(1);
        assertEquals(1,list2.size());
        assertTrue(list2.contains(coin));
        assertEquals(list1,list2);
    }
    
}
    

