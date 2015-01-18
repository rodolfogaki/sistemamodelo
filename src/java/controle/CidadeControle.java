
package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.Estado;
import facade.CidadeFacade;
import facade.EstadoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;


@ManagedBean
@SessionScoped
public class CidadeControle implements Serializable{
    
    private Cidade cidade;
    @EJB
    private CidadeFacade cidadeFacade;
    @EJB
    private EstadoFacade estadoFacade;
    private ConverterGenerico converterEstado;

    public ConverterGenerico getConverterEstado() {
        if(converterEstado == null){
            converterEstado = new ConverterGenerico(estadoFacade, Estado.class);
        }
        return converterEstado;
    }

    public void setConverterEstado(ConverterGenerico converterEstado) {
        this.converterEstado = converterEstado;
    }
    
    public List<Estado> completaEstado(String filtro){
        return estadoFacade.listaFiltrando(filtro, "nome", "sigla");
    }
    
    public void salvar(){
        cidadeFacade.salvar(cidade);
    }
    
    public void novo(){
        cidade = new Cidade();
    }
    
    public void excluir(ActionEvent e){
        cidade = (Cidade) e.getComponent().getAttributes().get("cid");
        cidadeFacade.remover(cidade);
    }
    
    public void editar(ActionEvent e){
        cidade = (Cidade) e.getComponent().getAttributes().get("cid");
    }
    
    public List<Cidade> listaCidades(){
        return cidadeFacade.listaTodos();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    
}
