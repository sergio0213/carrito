package com.unbosque.carrito.beans;

import static com.sun.faces.facelets.util.Path.context;
import com.unbosque.carrito.controlerjpa.ClienteJpaController;
import com.unbosque.carrito.entidades.Cliente;
import static com.unbosque.carrito.entidades.Cliente_.usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

    Cliente cliente= new Cliente();
    boolean login = false;
    
    ClienteJpaController contr;
    
    public UsuarioBean() {
       
        System.out.println("UsuarioBean constructor");
    }

    public String registro() {
        try {
            System.out.println("com.unbosque.carrito.beans.UsuarioBean.registro()");
            contr = new ClienteJpaController();
            System.out.println("" + cliente.getUsuario() + "" + cliente.getNombre());
            contr.create(cliente);
            loginCarrito();

        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Return login");
        return "index";
    }

    public String loginCarrito() {
        contr = new ClienteJpaController();
        System.out.println("entro a login()" + cliente.getUsuario());

        if (contr.findClienteUsuario(cliente)) {

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Idcliente",cliente.getIdcliente());
            login = true;
            RequestContext context = RequestContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", cliente.getUsuario());
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", true);
          
    return "index";
        }
  return "";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean getLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

}
