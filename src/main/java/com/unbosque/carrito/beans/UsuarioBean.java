package com.unbosque.carrito.beans;

import static com.sun.faces.facelets.util.Path.context;
import com.unbosque.carrito.controlerjpa.ClienteJpaController;
import com.unbosque.carrito.entidades.Cliente;
import static com.unbosque.carrito.entidades.Cliente_.usuario;
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

    Cliente cliente;
    boolean login;
   ClienteJpaController contr;
    public UsuarioBean() {
        cliente= new Cliente();
        login = false;
    }
    
    
    public String registro() {
       try {
        System.out.println("com.unbosque.carrito.beans.UsuarioBean.registro()");
       contr = new ClienteJpaController();
        System.out.println(""+cliente.getUsuario()+""+cliente.getNombre());
        contr.create(cliente);
         loginCarrito();
          
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Return login");
        return "index";
    }
    
    public void loginCarrito(){
       contr= new ClienteJpaController();
      System.out.println("entro a login()"+cliente.getUsuario());
      
     if(contr.findClienteUsuario(cliente)){
     
    // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO",cliente.getUsuario());
    // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Idcliente",cliente.getIdcliente());
        login= true;
         RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message =  new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", cliente.getUsuario());
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", true);
     }
     
    
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

}
