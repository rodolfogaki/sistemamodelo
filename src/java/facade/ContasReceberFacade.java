/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.ContasReceber;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author unipar
 */
@Stateless
public class ContasReceberFacade extends AbstractFacade<ContasReceber> {

    @PersistenceContext(unitName="sistemamodeloPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContasReceberFacade() {
        super(ContasReceber.class);
    }
    
    
}
