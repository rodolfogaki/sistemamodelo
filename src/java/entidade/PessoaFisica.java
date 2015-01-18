/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PessoaFisica extends Pessoa implements Serializable{
    
    @Column(nullable=true)
    private String cpf;
    private String rg;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + cpf;
    }

    @Override
    public String getCpfCnpj() {
       return this.cpf;
    }
    
}
