/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.GrupoProduto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GrupoProdutoFacade  extends AbstractFacade<GrupoProduto> {

    @PersistenceContext(unitName="sistemamodeloPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public GrupoProdutoFacade() {
        super(GrupoProduto.class);
    }
    
}
