/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author Danilo_2
 */
@Entity
public class ItemCompra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal quantidade;
    private BigDecimal valor;
    private BigDecimal desconto;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Compra compra;
    //Este atributo transient só vai existir no objeto, não será persistido no banco de dados
    @Transient
    private Long criadoEm;

    public ItemCompra() {
        this.criadoEm   = System.nanoTime();
        this.quantidade = BigDecimal.ZERO;
        this.valor      = BigDecimal.ZERO;
        this.desconto   = BigDecimal.ZERO;
    }
        
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public BigDecimal getQuantidade() {return quantidade;}
    public void setQuantidade(BigDecimal quantidade) {this.quantidade = quantidade;}
    public BigDecimal getValor() {return valor;}
    public void setValor(BigDecimal valor) {this.valor = valor;}
    public BigDecimal getDesconto() {return desconto;}
    public void setDesconto(BigDecimal desconto) {this.desconto = desconto;}
    public Produto getProduto() {return produto;}
    public void setProduto(Produto produto) {this.produto = produto;}
    public Compra getCompra() {return compra;}
    public void setCompra(Compra compra) {this.compra = compra;}
    public Long getCriadoEm() {return criadoEm;}
    public void setCriadoEm(Long criadoEm) {this.criadoEm = criadoEm;}
    
     public BigDecimal getSubTotal(){
        return valor.multiply(quantidade).multiply(BigDecimal.ONE.subtract(desconto.divide(new BigDecimal(100))));
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }        
        ItemCompra other = (ItemCompra) obj;
        if (this.criadoEm != other.criadoEm && (this.criadoEm == null || !this.criadoEm.equals(other.criadoEm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ItemCompra[ id=" + id + " ]";
    }
    
}
