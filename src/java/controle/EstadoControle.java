
package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import entidade.Estado;
import facade.EstadoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;


@ManagedBean
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(id = "listaest", pattern = "/estado/listar", viewId = "/faces/estado/estadolistar.xhtml"),
    @URLMapping(id = "novoest", pattern = "/estado/novo", viewId = "/faces/estado/estadoeditar.xhtml"),
    @URLMapping(id = "editaest", pattern = "/estado/editar/#{estadoControle.codigo}", viewId = "/faces/estado/estadoeditar.xhtml")
})
public class EstadoControle implements Serializable{
    
    private Estado estado;
    @EJB
    private EstadoFacade estadoFacade;
    private Long codigo;
        
    public void salvar(){
        estadoFacade.salvar(estado);
    }
    @URLAction(mappingId="novoest")
    public void novo(){
        estado = new Estado();
    }
    
    @URLAction(mappingId = "editaest", phaseId = URLAction.PhaseId.RENDER_RESPONSE)
    public void editarPretty(){
        estado = (Estado) estadoFacade.recuperar(Estado.class, codigo);
    }
    
    public void excluir(ActionEvent e){
        estado = (Estado) e.getComponent().getAttributes().get("est");
        estadoFacade.remover(estado);
    }
    
//    public void editar(ActionEvent e){
//        estado = (Estado) e.getComponent().getAttributes().get("est");
//    }
    
    public List<Estado> listaEstados(){
        return estadoFacade.listaTodos();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
}
