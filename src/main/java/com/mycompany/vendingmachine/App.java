/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine;

import com.mycompany.vendingmachine.Controller.VendingMachineController;
import com.mycompany.vendingmachine.dao.VendingMachineAuditDao;
import com.mycompany.vendingmachine.dao.VendingMachineAuditDaoImpl;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachineDaoImpl;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.NoItemInventoryException;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayer;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.mycompany.vendingmachine.ui.UserIO;
import com.mycompany.vendingmachine.ui.UserIOConsoleImpl;
import com.mycompany.vendingmachine.ui.VendingMachineView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Mike
 */
public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
  /*   UserIO myIo = new UserIOConsoleImpl();
       VendingMachineView myView = new VendingMachineView(myIo);
       VendingMachineDao myDao = new VendingMachineDaoImpl();
       VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
       VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
       VendingMachineController controller;
       controller = new VendingMachineController(myService, myView);
       controller.run(); */
     AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
     appContext.scan("com.mycompany.vendingmachine");
     appContext.refresh();
     VendingMachineController controller = appContext.getBean("vendingMachineController",VendingMachineController.class);
     controller.run(); 
    }
}
