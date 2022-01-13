/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.Coin;
import com.mycompany.vendingmachine.dao.VendingMachineDaoImpl;
import com.mycompany.vendingmachine.dto.Item;
import java.io.IOException;
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
public class VendingMachineDaoImplTest {
    @Autowired
    VendingMachineDao testdao;
    VendingMachineDao testdao2;
    
    public VendingMachineDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        String testfile= "testMachine.txt";
        String testfile2= "testMachine2";
        testdao = new VendingMachineDaoImpl(testfile);
        testdao2 = new VendingMachineDaoImpl(testfile2);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getItemObject() throws Exception {
        int ID= 1;
        String Name = "Sprite";
        int cost = 251;
        int quantity = 28;
        Item item = new Item(ID,Name,cost,quantity);
        Item testItem = testdao.getItemObject(ID);
        assertEquals(item.getID(),testItem.getID());
        assertEquals(item.getName(),testItem.getName());
        assertEquals(item.getCost(),testItem.getCost());
        assertEquals(item.getQuantity(),testItem.getQuantity());
        
    }
    @Test
    public void getItemName () throws Exception
    {
        int ID= 1;
        String Name = "Sprite";
        assertEquals(Name,testdao.getItemName(ID));
        
    }
    @Test
    public void getallItems() throws Exception
    { 
        int ID1= 1;
        String Name1 = "Sprite";
        int cost1 = 251;
        int quantity1 = 28;
        Item item1 = new Item(ID1,Name1,cost1,quantity1);
        int ID2= 2;
        String Name2 = "Fanta";
        int cost2 = 244;
        int quantity2 = 19;
        Item item2 = new Item(ID2,Name2,cost2,quantity2);
        List<Item> list1;
        list1 = new ArrayList<>();
        list1.add(item1);
        list1.add(item2);
        List<Item> list2 = testdao.getallItems();
        assertNotNull(list2);
        assertEquals(2,list2.size());
        assertEquals(list1,list2);
        assertTrue(list2.contains(item1));
        assertTrue(list2.contains(item2));
        
    }
    @Test
    public void VendItem() throws Exception
    {
      int id1  = 1;
      int amount = 300;
      int change1 = amount - 251;
      int change2 = testdao2.VendItem(id1, amount);
      assertEquals(change1,change2);
    }
    @Test
    public void get_all_quarters()
    {
        Coin quarter = Coin.quarter;
        List<Coin> list1 =  new ArrayList<>();
        list1.add(quarter);
        List<Coin> List2 = testdao.get_all_quarters(25);
        assertNotNull(List2);
        assertEquals(1, List2.size());
        assertEquals(list1,List2);
    }
    @Test
    public void get_all_dimes()
    {
        Coin dime  = Coin.dime;
        List<Coin> list1 =  new ArrayList<>();
        list1.add(dime);
        List<Coin> List2 = testdao.get_all_dimes(10);
        assertNotNull(List2);
        assertEquals(1, List2.size());
        assertEquals(list1,List2);
        
    }
    @Test
    public void get_all_nickels()
    {
        Coin nickle  = Coin.nickel;
        List<Coin> list1 =  new ArrayList<>();
        list1.add(nickle);
        List<Coin> List2 = testdao.get_all_nickels(5);
        assertNotNull(List2);
        assertEquals(1, List2.size());
        assertEquals(list1,List2);
        
    }
    @Test
    public void get_all_pennies()
    {
        Coin penny  = Coin.penny;
        List<Coin> list1 =  new ArrayList<>();
        list1.add(penny);
        List<Coin> List2 = testdao.get_all_pennies(1);
        assertNotNull(List2);
        assertEquals(1, List2.size());
        assertEquals(list1,List2);
        
    }
    
}
