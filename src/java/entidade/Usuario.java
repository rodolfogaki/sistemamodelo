/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Danilo_2
 */
@Entity
public class Usuario extends PessoaFisica{

    @Column(nullable=true, unique=true)
    private String  login;
    @Column(nullable=true)
    private String  senha;
    @Column(columnDefinition = "BOOLEAN")
    private boolean ativo;
    @ManyToMany
    private List<Autorizacao> autorizacoes;
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    } 

    public List<Autorizacao> getAutorizacoes() {
        return autorizacoes;
    }

    public void setAutorizacoes(List<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + login;
    }
    
}
