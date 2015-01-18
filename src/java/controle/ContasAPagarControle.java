/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.ContasAPagar;
import entidade.Pessoa;
import facade.ContasAPagarFacade;
import facade.PessoaFacade;
import facade.PessoaFisicaFacade;
import facade.PessoaJuridicaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Danilo_2
 */
@ManagedBean
@SessionScoped
public class ContasAPagarControle implements Serializable{
    
    private ContasAPagar contasAPagar;
    @EJB
    private ContasAPagarFacade contasAPagarFacade;

    @EJB
    private PessoaFacade pessoaFacade;
    
    @EJB
    private PessoaFisicaFacade pessoaFisicaFacade;
    
    @EJB
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    private ConverterGenerico converterPessoa;
    private Long codigo;

    public ConverterGenerico getConverterPessoa() {
        if (converterPessoa == null) {
            converterPessoa = new ConverterGenerico(pessoaFacade, Pessoa.class);
        }
        return converterPessoa;
    }

    public void setConverterPessoa(ConverterGenerico converterPessoa) {
        this.converterPessoa = converterPessoa;
    }

    
    public void salvar(){
        contasAPagarFacade.salvar(contasAPagar);
    }
    
    public void novo(){
        contasAPagar = new ContasAPagar();
    }
    
    public void editarPretty(){
        contasAPagar = (ContasAPagar) contasAPagarFacade.recuperar(ContasAPagar.class, codigo);
    }
    
    public void excluir(ActionEvent e){
        contasAPagar = (ContasAPagar) e.getComponent().getAttributes().get("cp");
        contasAPagarFacade.remover(contasAPagar);
    }
    
//    public void editar(ActionEvent e){
//        contasReceber = (ContasReceber) e.getComponent().getAttributes().get("est");
//    }
    
        
    public List<Pessoa> completaPessoa(String filtro) {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        lista.addAll(pessoaFisicaFacade.listaFiltrando(filtro, "nome", "cpf"));
        lista.addAll(pessoaJuridicaFacade.listaFiltrando(filtro, "nome", "nomeFantasia", "cnpj"));
        return lista;
    }
    
    public List<ContasAPagar> listaContasAPagar(){
        return contasAPagarFacade.listaTodos();
    }

    public ContasAPagar getContasAPagar() {
        return contasAPagar;
    }

    public void setContasAPagar(ContasAPagar contasAPagar) {
        this.contasAPagar = contasAPagar;
    }
   
}

