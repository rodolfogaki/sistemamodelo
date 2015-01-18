/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.Compra;
import entidade.ContasAPagar;
import entidade.ItemCompra;
import entidade.Produto;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class CompraFacade extends AbstractFacade<Compra> {

    @PersistenceContext(unitName="sistemamodeloPU")
    private EntityManager em;
    
    @EJB
    private ProdutoFacade produtoFacade;    

    @EJB
    private ContasAPagarFacade contasAPagarFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }
    
     @Override
    public void salvar(Compra entity) {
        //super.salvar(entity);
        entity = em.merge(entity);  
        incluirEstoque(entity);
        geraContasAPagar(entity);
    }

    private void incluirEstoque(Compra v) {
        Produto p = null;
        for(ItemCompra iv : v.getItemCompra()){
            p = iv.getProduto();
            p.setEstoque(p.getEstoque().add(iv.getQuantidade()));
            produtoFacade.salvar(p);
        }
    }

    private void geraContasAPagar(Compra entity) {
        ContasAPagar cp = new ContasAPagar();
        cp.setDataLancamento(entity.getDataCompra());
        cp.setDataVencimento(entity.getDataCompra());
        cp.setDesconto(BigDecimal.ZERO);
        cp.setJuro(BigDecimal.ZERO);
        cp.setValor(entity.getValorTotal());
        cp.setCompra(entity);
        cp.setDescricao("Gerado a partir da compra "+entity.getId());
        contasAPagarFacade.salvar(cp);
        
    }
        
}
