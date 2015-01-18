/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.GrupoProduto;
import entidade.Produto;
import facade.GrupoProdutoFacade;
import facade.ProdutoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;


@ManagedBean
@SessionScoped
public class ProdutoControle implements Serializable{

    
    private Produto produto;
    @EJB
    private ProdutoFacade produtoFacade;
    @EJB
    private GrupoProdutoFacade grupoProdutoFacade;
    
    private ConverterGenerico converterGrupo;

    public ConverterGenerico getConverterGrupo() {
        if(converterGrupo == null){
            converterGrupo = new ConverterGenerico(grupoProdutoFacade, GrupoProduto.class);
        }
        return converterGrupo;
    }

    public void setConverterGrupo(ConverterGenerico converterEstado) {
        this.converterGrupo = converterEstado;
    }
    
    public List<GrupoProduto> completaGrupo(String filtro){
        return grupoProdutoFacade.listaFiltrando(filtro, "nome");
    }
    
    
    public void salvar(){
        produtoFacade.salvar(produto);
    }
    
    public void novo(){
        produto = new Produto();
    }
    
    public void excluir(ActionEvent e){
        produto = (Produto) e.getComponent().getAttributes().get("prd");
        produtoFacade.remover(produto);
    }

    
    public List<Produto> listar(){
        return produtoFacade.listaTodos();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


}
