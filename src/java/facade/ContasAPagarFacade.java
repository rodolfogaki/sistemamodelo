/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.ContasAPagar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Danilo_2
 */
@Stateless
public class ContasAPagarFacade extends AbstractFacade<ContasAPagar>{
    @PersistenceContext(unitName="sistemamodeloPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContasAPagarFacade() {
        super(ContasAPagar.class);
    }
}

