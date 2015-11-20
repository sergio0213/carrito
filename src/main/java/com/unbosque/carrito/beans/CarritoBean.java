package com.unbosque.carrito.beans;

import com.unbosque.carrito.controlerjpa.ClienteJpaController;
import com.unbosque.carrito.controlerjpa.PedidoJpaController;
import com.unbosque.carrito.controlerjpa.ProductoJpaController;
import com.unbosque.carrito.entidades.Cliente;
import com.unbosque.carrito.entidades.Clientepedido;
import com.unbosque.carrito.entidades.Pedido;
import com.unbosque.carrito.entidades.Producto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@SessionScoped

public class CarritoBean implements Serializable {

    private List<Producto> productos;
    Pedido pedido;
    Clientepedido clientepedido;
    int idpedido;
    PedidoJpaController contr ;
    public CarritoBean() {
        contr= new PedidoJpaController();
        ProductoJpaController cnt = new ProductoJpaController();
        productos = cnt.findProductoEntities();
        idpedido = contr.getPedidoCount()+1;
    }
    
    
    public void agregar(int idproducto) {
    
        try {
            contr= new PedidoJpaController();
            pedido.setIdpedido(BigDecimal.valueOf(this.idpedido));
            pedido.setCantidad(pedido.getCantidad().add(BigInteger.ONE));
            contr.create(pedido);
            System.out.println("com.unbosque.carrito.beans.CarritoBean.agregar() producto ="+idproducto);
        } catch (Exception ex) {
            Logger.getLogger(CarritoBean.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("Error agregar");
        }

    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

   

}
