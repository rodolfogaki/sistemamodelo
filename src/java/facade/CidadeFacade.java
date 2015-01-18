/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.Cidade;
import entidade.Estado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author unipar
 */
@Stateless
public class CidadeFacade extends AbstractFacade<Cidade> {

    @PersistenceContext(unitName="sistemamodeloPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CidadeFacade() {
        super(Cidade.class);
    }
    
    public List<Cidade> listaFiltrandoPorEstado(String filtro, Estado e) {
        String hql = "from Cidade obj where lower(obj.nome) like :param ";
        if (e != null) {
            hql += " and obj.estado = :est ";
        }
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("param", "%" + filtro.toLowerCase() + "%");
        if (e != null) {
            q.setParameter("est", e);
        }
        return q.getResultList();
    }
    
}
