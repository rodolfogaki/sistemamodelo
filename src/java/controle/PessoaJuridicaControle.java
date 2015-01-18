/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.PessoaJuridica;
import facade.CidadeFacade;
import facade.PessoaJuridicaFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class PessoaJuridicaControle implements Serializable{
    
    private PessoaJuridica pessoaJuridica;
    @EJB
    private PessoaJuridicaFacade pessoaJuridicaFacade;
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
        pessoaJuridicaFacade.salvar(pessoaJuridica);
        
    }
    
    public void novo(){
        pessoaJuridica = new PessoaJuridica();
    }
    
    public void excluir(ActionEvent e){
        pessoaJuridica = (PessoaJuridica) e.getComponent().getAttributes().get("pes");
        pessoaJuridicaFacade.remover(pessoaJuridica);
    }
    
    public List<PessoaJuridica> listar(){
        return pessoaJuridicaFacade.listaTodos();
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }
    
}
