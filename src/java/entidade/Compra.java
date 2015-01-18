/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Danilo_2
 */
@Entity
public class Compra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataCompra;
    private BigDecimal valorTotal;
    private BigDecimal desconto;
    @ManyToOne
    private Pessoa pessoa;
    @OneToMany(mappedBy = "compra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemCompra> itemCompra;

    public Compra() {
        this.dataCompra = new Date();     
        this.valorTotal = BigDecimal.ZERO;
        this.desconto   = BigDecimal.ZERO;
        this.itemCompra = new ArrayList<ItemCompra>();
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public BigDecimal getValorTotal() {
        BigDecimal resultado = BigDecimal.ZERO;
        
        for (ItemCompra ic : itemCompra) {
            resultado = resultado.add(ic.getSubTotal());
        }
        resultado = resultado.subtract(desconto);
        if(resultado.compareTo(BigDecimal.ZERO) == -1){
            resultado = BigDecimal.ZERO;
            this.desconto = BigDecimal.ZERO; 
        }
        return resultado.setScale(2, RoundingMode.HALF_UP);
    }
    
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<ItemCompra> getItemCompra() {
        return itemCompra;
    }

    public void setItemCompra(List<ItemCompra> itemCompra) {
        this.itemCompra = itemCompra;
    }
       
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
        
    @Override
    public String toString() {
        return "entidade.Compra[ id=" + id + " ]";
    }
    
}
