/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.GrupoProduto;
import facade.GrupoProdutoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class GrupoProdutoControle implements Serializable{
    
    private GrupoProduto grupoProduto;
    @EJB
    private GrupoProdutoFacade grupoProdutoFacade;
    private ConverterGenerico converterEstado;

    public ConverterGenerico getConverterGrupo() {
        if(converterEstado == null){
            converterEstado = new ConverterGenerico(grupoProdutoFacade, GrupoProduto.class);
        }
        return converterEstado;
    }

    public void setConverterGrupo(ConverterGenerico converterEstado) {
        this.converterEstado = converterEstado;
    }
    
    public List<GrupoProduto> completaGrupo(String filtro){
        return grupoProdutoFacade.listaFiltrando(filtro, "nome");
    }
    
    
    public void salvar(){
        grupoProdutoFacade.salvar(grupoProduto);
    }
    
    public void novo(){
        grupoProduto = new GrupoProduto();
    }
    
    public void excluir(ActionEvent e){
        grupoProduto = (GrupoProduto) e.getComponent().getAttributes().get("grp");
        grupoProdutoFacade.remover(grupoProduto);
    }

    
    public List<GrupoProduto> listaGrupo(){
        return grupoProdutoFacade.listaTodos();
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }
    
    
}