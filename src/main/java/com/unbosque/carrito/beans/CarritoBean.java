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
    private Pedido pedido;
    private Clientepedido clientepedido;
    private int idpedido;
    PedidoJpaController contr;
    
    
    public CarritoBean() {
        System.out.println("pedido constructor" + idpedido);
        contr = new PedidoJpaController();
        pedido = new Pedido();
        ProductoJpaController cnt = new ProductoJpaController();
        productos = cnt.findProductoEntities();
        idpedido=contr.getPedidoCount()+1;
        System.out.println("Constructor carrito.beans.CarritoBean.<init>()");
    }

    public void agregar(int idproducto) {

        try {
            System.out.println("product =" + idproducto);
            System.out.println("beans.CarritoBean.agregar() pedido =" + this.idpedido);
            contr = new PedidoJpaController();
            pedido.setIdpedido(BigDecimal.valueOf(this.idpedido));
            pedido.setCantidad(BigInteger.ONE);
            pedido.setIdproducto(BigInteger.valueOf(idproducto));
            contr.create(pedido);
            System.out.println("Se agrego el producto =" + idproducto);
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
