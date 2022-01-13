/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;


/**
 *
 * @author Mike
 */
public enum Coin {
    quarter(25), dime(10), nickel(5),penny(1);
    private final int  value;
    private Coin(int  value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return this.value;
    }
    
}
