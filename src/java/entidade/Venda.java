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

@Entity
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataVenda;
    private BigDecimal valorTotal;
    private BigDecimal desconto;
    
    @ManyToOne
    private Pessoa pessoa;
    @OneToMany(mappedBy = "venda", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    
    private List<ItemVenda> itemVendas;

    public Venda() {
        this.dataVenda = new Date();
        this.valorTotal = BigDecimal.ZERO;
        this.desconto = BigDecimal.ZERO;
        this.itemVendas = new ArrayList<ItemVenda>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getValorTotal() {
        BigDecimal resultado = BigDecimal.ZERO;
        
        for (ItemVenda iv : itemVendas) {
            resultado = resultado.add(iv.getSubTotal());
        }
        resultado = resultado.subtract(desconto);
        if(resultado.compareTo(BigDecimal.ZERO) == -1){
            resultado = BigDecimal.ZERO;
            this.desconto = BigDecimal.ZERO; 
        }
        setValorTotal(resultado.setScale(2,RoundingMode.HALF_UP));
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

    public List<ItemVenda> getItemVendas() {
        return itemVendas;
    }

    public void setItemVendas(List<ItemVenda> itemVendas) {
        this.itemVendas = itemVendas;
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
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Venda[ id=" + id + " ]";
    }
}
