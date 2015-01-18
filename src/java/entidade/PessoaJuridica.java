/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class PessoaJuridica extends Pessoa implements Serializable{
    
    private String cnpj;
    private String ie;
    private String nomeFantasia;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }


    @Override
    public String toString() {
        return super.toString() + " - " + cnpj;
    }

    @Override
    public String getCpfCnpj() {
        return this.cnpj;
    }
    
}
