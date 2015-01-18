
package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import converter.ConverterGenerico;
import entidade.ContasReceber;
import entidade.Pessoa;
import facade.ContasReceberFacade;
import facade.PessoaFacade;
import facade.PessoaFisicaFacade;
import facade.PessoaJuridicaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;


@ManagedBean
@SessionScoped
/*
@URLMappings(mappings = {
    @URLMapping(id = "listaest", pattern = "/contasReceber/listar", viewId = "/faces/contasReceber/contasReceberlistar.xhtml"),
    @URLMapping(id = "novoest", pattern = "/contasReceber/novo", viewId = "/faces/contasReceber/contasRecebereditar.xhtml"),
    @URLMapping(id = "editaest", pattern = "/contasReceber/editar/#{contasReceberControle.codigo}", viewId = "/faces/contasReceber/contasRecebereditar.xhtml")
})
*/
public class ContasReceberControle implements Serializable{
    
    private ContasReceber contasReceber;
    @EJB
    private ContasReceberFacade contasReceberFacade;

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
        contasReceberFacade.salvar(contasReceber);
    }
    
    public void novo(){
        contasReceber = new ContasReceber();
    }
    
    public void editarPretty(){
        contasReceber = (ContasReceber) contasReceberFacade.recuperar(ContasReceber.class, codigo);
    }
    
    public void excluir(ActionEvent e){
        contasReceber = (ContasReceber) e.getComponent().getAttributes().get("cr");
        contasReceberFacade.remover(contasReceber);
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
    
    public List<ContasReceber> listaContasRecebers(){
        return contasReceberFacade.listaTodos();
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }
    
    
}
