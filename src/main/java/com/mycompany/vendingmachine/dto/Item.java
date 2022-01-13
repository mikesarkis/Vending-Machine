/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import java.util.Objects;

/**
 *
 * @author Mike
 */
public class Item {
    private int ID;

    @Override
    public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(this.ID);
    hash = 89 * hash + Objects.hashCode(this.Name);
    hash = 89 * hash + Objects.hashCode(this.cost);
    hash = 89 * hash + Objects.hashCode(this.quantity);
    return hash;
    }

    @Override
    public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    final Item other = (Item) obj;
    if (!Objects.equals(this.ID, other.ID)) {
        return false;
    }
    if (!Objects.equals(this.Name, other.Name)) {
        return false;
    }
    if (!Objects.equals(this.cost, other.cost)) {
        return false;
    }
    if (!Objects.equals(this.quantity, other.quantity)) {
        return false;
    }
    return true;
    }
    private String Name;
    private int cost;
    private int quantity;
    public  Item(){}
    public Item(int ID,String Name, int cost, int quantity)
    {
        this.ID = ID;
        this.Name = Name;
        this.cost = cost;
        this.quantity = quantity;
    }
    public int getID()
    {
        return ID;
    }
    public void setName(String Name)
    {
        this.Name = Name;
    }
    public String getName()
    {
        return Name;
    }
    public void setCost(int cost)
    {
        this.cost= cost;
    }
    public int getCost()
    {
        return cost;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    public int getQuantity()
    {
        return quantity;
    }
           
    
}
