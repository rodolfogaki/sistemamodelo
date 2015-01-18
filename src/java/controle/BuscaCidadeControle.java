/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class BuscaCidadeControle implements Serializable{
    @EJB
    private CidadeFacade cidadeFacade;
    
    @EJB
    private EstadoFacade estadoFacade;
    
    private ConverterGenerico converterCidade;
    private ConverterGenerico converterEstado;
    private Estado estado;

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public ConverterGenerico getConverterCidade() {
        if(converterCidade == null) {
            converterCidade = new ConverterGenerico(cidadeFacade, Cidade.class);
        }
        
        return converterCidade;
    }

    public void setConverterCidade(ConverterGenerico converterCidade) {
        this.converterCidade = converterCidade;
    }

    public ConverterGenerico getConverterEstado() {
        if(converterEstado == null) {
            converterEstado = new ConverterGenerico(estadoFacade, Estado.class);
        }
        
        
        return converterEstado;
    }

    public void setConverterEstado(ConverterGenerico converterEstado) {
        this.converterEstado = converterEstado;
    }
    
    
    
    public List<Cidade> completaCidade(String filtro){
        return cidadeFacade.listaFiltrandoPorEstado(filtro, estado);
    }
 
    public List<Estado> completaEstado(String filtro){
        return estadoFacade.listaFiltrando(filtro, "nome");
    }
    
    
}
