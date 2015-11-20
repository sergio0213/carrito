/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unbosque.carrito.beans;


import com.unbosque.carrito.controlerjpa.ClienteJpaController;
import com.unbosque.carrito.entidades.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author scabrera
 */
public class Test {
     public static void main(String[] args) {
    try {

            
        Cliente c = new Cliente("prueba1","123","prueba prueba");
        ClienteJpaController contr= new ClienteJpaController();
        contr.create(c);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
