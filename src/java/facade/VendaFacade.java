/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.ContasReceber;
import entidade.ItemVenda;
import entidade.Produto;
import entidade.Venda;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author unipar
 */
@Stateless
public class VendaFacade extends AbstractFacade<Venda> {

    @PersistenceContext(unitName="sistemamodeloPU")
    private EntityManager em;
    
    @EJB
    private ProdutoFacade produtoFacade;
    
    @EJB
    private ContasReceberFacade contasReceberFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendaFacade() {
        super(Venda.class);
    }

    @Override
    public void salvar(Venda entity) {
        //super.salvar(entity);
        entity = em.merge(entity);        
        baixarEstoque(entity);
        geraContasReceber(entity);
    }

    private void baixarEstoque(Venda v) {
        Produto p = null;
        for(ItemVenda iv : v.getItemVendas()){
            p = iv.getProduto();
            p.setEstoque(p.getEstoque().subtract(iv.getQuantidade()));
            produtoFacade.salvar(p);
        }
    }

    private void geraContasReceber(Venda entity) {
        ContasReceber cr = new ContasReceber();
        cr.setDataLancamento(entity.getDataVenda());
        cr.setVencimento(entity.getDataVenda());
        cr.setValor(entity.getValorTotal());
        cr.setPessoa(entity.getPessoa());
        cr.setVenda(entity);
        cr.setDescricao("Gerado a partir da venda "+entity.getId());
        contasReceberFacade.salvar(cr);
    }
    
    
    
}
