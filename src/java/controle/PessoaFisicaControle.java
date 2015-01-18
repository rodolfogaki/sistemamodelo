/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.PessoaFisica;
import facade.CidadeFacade;
import facade.PessoaFisicaFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class PessoaFisicaControle implements Serializable{
    
    private PessoaFisica pessoaFisica;
    @EJB
    private PessoaFisicaFacade pessoaFisicaFacade;
    
    @EJB
    private CidadeFacade cidadeFacade;
    private ConverterGenerico converterCidade;

    public ConverterGenerico getConverterCidade() {
        if(converterCidade == null){
            converterCidade = new ConverterGenerico(cidadeFacade, Cidade.class);
        }
        return converterCidade;
    }

    public void setConverterCidade(ConverterGenerico converterEstado) {
        this.converterCidade = converterEstado;
    }
    
    public List<Cidade> completaCidade(String filtro){
        return cidadeFacade.listaFiltrando(filtro, "nome");
    }
    
    public void salvar(){
        pessoaFisicaFacade.salvar(pessoaFisica);
        
    }
    
    public void novo(){
        pessoaFisica = new PessoaFisica();
    }
    
    public void excluir(ActionEvent e){
        pessoaFisica = (PessoaFisica) e.getComponent().getAttributes().get("pes");
        pessoaFisicaFacade.remover(pessoaFisica);
    }
    
    public List<PessoaFisica> listar(){
        return pessoaFisicaFacade.listaTodos();
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

}
