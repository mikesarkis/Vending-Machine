/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.vendingmachine.dao.VendingMachineAuditDao;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;

/**
 *
 * @author Mike
 */
public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao{ 
    @Override
    public void writeAuditEntry(String entry)  throws VendingMachinePersistenceException
    {
        
    }
    
}
