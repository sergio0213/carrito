/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unbosque.carrito.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ingeneo
 */
@Entity
@Table(name = "pedido", schema="grupo2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByCantidad", query = "SELECT p FROM Pedido p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Pedido.findByIdproducto", query = "SELECT p FROM Pedido p WHERE p.idproducto = :idproducto"),
    @NamedQuery(name = "Pedido.findByIdpedido", query = "SELECT p FROM Pedido p WHERE p.idpedido = :idpedido")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private BigInteger cantidad;
    @Column(name = "idproducto")
    private BigInteger idproducto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpedido")
    private BigDecimal idpedido;

    public Pedido() {
    }

    public Pedido(BigDecimal idpedido) {
        this.idpedido = idpedido;
    }

    public Pedido(BigDecimal idpedido, BigInteger cantidad) {
        this.idpedido = idpedido;
        this.cantidad = cantidad;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public BigInteger getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(BigInteger idproducto) {
        this.idproducto = idproducto;
    }

    public BigDecimal getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(BigDecimal idpedido) {
        this.idpedido = idpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedido != null ? idpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idpedido == null && other.idpedido != null) || (this.idpedido != null && !this.idpedido.equals(other.idpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unbosque.carrito.entidades.Pedido[ idpedido=" + idpedido + " ]";
    }
    
}
