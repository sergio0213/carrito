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
 * @author scabrera
 */
@Entity
@Table(name = "clientepedido", schema="grupo2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientepedido.findAll", query = "SELECT c FROM Clientepedido c"),
    @NamedQuery(name = "Clientepedido.findByIdcliente", query = "SELECT c FROM Clientepedido c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Clientepedido.findByIdpedido", query = "SELECT c FROM Clientepedido c WHERE c.idpedido = :idpedido")})
public class Clientepedido implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcliente")
    private BigDecimal idcliente;
    @Column(name = "idpedido")
    private BigInteger idpedido;

    public Clientepedido() {
    }

    public Clientepedido(BigDecimal idcliente) {
        this.idcliente = idcliente;
    }

    public BigDecimal getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(BigDecimal idcliente) {
        this.idcliente = idcliente;
    }

    public BigInteger getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(BigInteger idpedido) {
        this.idpedido = idpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientepedido)) {
            return false;
        }
        Clientepedido other = (Clientepedido) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unbosque.carrito.entidades.Clientepedido[ idcliente=" + idcliente + " ]";
    }
    
}
