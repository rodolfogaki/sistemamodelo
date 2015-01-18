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
 * @author unipar
 */
@Entity
public class ItemVenda implements Serializable {

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
    private Venda venda;
    //Este atributo transient só vai existir no objeto, não será persistido no banco de dados
    @Transient
    private Long criadoEm;

    public ItemVenda() {
        this.criadoEm = System.nanoTime();
        this.valor = BigDecimal.ZERO;
        this.desconto = BigDecimal.ZERO;
        this.quantidade = BigDecimal.ZERO;
    }
    
    public BigDecimal getSubTotal(){
        // (valor X quantidade) X (1 - (desconto/100))
        return valor.multiply(quantidade).multiply(BigDecimal.ONE.subtract(desconto.divide(new BigDecimal(100))));
        //return BigDecimal.ONE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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
        final ItemVenda other = (ItemVenda) obj;
        if (this.criadoEm != other.criadoEm && (this.criadoEm == null || !this.criadoEm.equals(other.criadoEm))) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "entidade.ItemVenda[ id=" + id + " ]";
    }
}
