package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.Pessoa;
import entidade.PessoaFisica;
import entidade.PessoaJuridica;
import facade.CidadeFacade;
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

@ManagedBean
@SessionScoped
public class PessoaControle implements Serializable{

    private Pessoa pessoa;
    
    @EJB
    private CidadeFacade cidadeFacade;
    @EJB
    private PessoaFisicaFacade pessoaFisicaFacade;
    @EJB
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    @EJB
    private PessoaFacade pessoaFacade;
    
    public void salvar() {
        if (pessoa instanceof PessoaFisica) {
            pessoaFisicaFacade.salvar((PessoaFisica) pessoa);
        } else {
            pessoaJuridicaFacade.salvar((PessoaJuridica) pessoa);
        }
    }
    
    public String redireciona(){
        if (pessoa instanceof PessoaFisica) {
            return "pessoafisicaeditar";
        } else {
            return "pessoajuridicaeditar";
        }  
    }
       
    public void editar(ActionEvent e) {
        pessoa = (Pessoa) e.getComponent().getAttributes().get("pessoa");
    }
    
    public void novoFisico() {
        pessoa = new PessoaFisica();
    }

    public void novoJuridica() {
        pessoa = new PessoaJuridica();
    }

    public void excluir(ActionEvent e) {
        pessoa = (Pessoa) e.getComponent().getAttributes().get("pessoa");
        if (pessoa instanceof PessoaFisica) {
            pessoaFisicaFacade.remover((PessoaFisica) pessoa);
        } else {
            pessoaJuridicaFacade.remover((PessoaJuridica) pessoa);
        }
    }


    public List<Pessoa> listaPessoas() {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        lista.addAll(pessoaFisicaFacade.listaTodos());
        lista.addAll(pessoaJuridicaFacade.listaTodos());
        return lista;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public boolean getValidaTelaJuridica() {
        if (pessoa instanceof PessoaJuridica) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getValidaTelaFisica() {
        if (pessoa instanceof PessoaFisica) {
            return true;
        } else {
            return false;
        }
    }
}
