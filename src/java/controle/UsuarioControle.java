/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Autorizacao;
import entidade.Cidade;
import entidade.Usuario;
import facade.CidadeFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import util.Util;

@ManagedBean
@SessionScoped
public class UsuarioControle implements Serializable{
    
    private Usuario usuario;
    @EJB
    private UsuarioFacade usuarioFacade;
    
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
        if (usuario.getId() == null) {
            Autorizacao auto = new Autorizacao(1L, "ROLE_USER");
            usuario.getAutorizacoes().add(auto);
            usuario.setAtivo(true);
            usuario.setSenha(Util.md5("123mudar"));
        }
        usuarioFacade.salvar(usuario);
    }
    
    public void novo(){
        usuario = new Usuario();
    }
    
    public void excluir(ActionEvent e){
        usuario = (Usuario) e.getComponent().getAttributes().get("usu");
        usuarioFacade.remover(usuario);
    }
    
    public List<Usuario> listar(){
        return usuarioFacade.listaTodos();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    

}
