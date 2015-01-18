/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Compra;
import entidade.ItemCompra;
import entidade.ItemVenda;
import entidade.Pessoa;
import entidade.Produto;
import facade.CompraFacade;
import facade.PessoaFacade;
import facade.PessoaFisicaFacade;
import facade.PessoaJuridicaFacade;
import facade.ProdutoFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class CompraControle implements Serializable{
    
    private Compra compra;
    private ItemCompra itemCompra;
    @EJB
    private CompraFacade compraFacade;
    @EJB
    private PessoaFisicaFacade pessoaFisicaFacade;
    @EJB
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    @EJB
    private PessoaFacade pessoaFacade;
    @EJB
    private ProdutoFacade produtoFacade;

    private ConverterGenerico converterPessoa;
    private ConverterGenerico converterProduto;

    public ConverterGenerico getConverterPessoa() {
        if(converterPessoa == null){
            converterPessoa = new ConverterGenerico(pessoaFacade, Pessoa.class);
        }
        return converterPessoa;
    }

    public void setConverterPessoa(ConverterGenerico converterPessoa) {
        this.converterPessoa = converterPessoa;
    }
    
    public List<Pessoa> completaPessoa(String filtro) {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        lista.addAll(pessoaFisicaFacade.listaFiltrando(filtro, "nome", "cpf"));
        lista.addAll(pessoaJuridicaFacade.listaFiltrando(filtro, "nome", "nomeFantasia", "cnpj"));
        return lista;
    }
    
    public ConverterGenerico getConverterProduto() {
        if (converterProduto == null) {
            converterProduto = new ConverterGenerico(produtoFacade, Produto.class);
        }
        return converterProduto;
    }

    public void setConverterProduto(ConverterGenerico converterProduto) {
        this.converterProduto = converterProduto;
    }
    
    public List<Produto> completaProduto(String filtro) {
        return produtoFacade.listaFiltrando(filtro, "nome");
    }
    
    public void novo() {
        compra = new Compra();
        itemCompra = new ItemCompra();
    }
    
    public List<Compra> listaCompras() {
        return compraFacade.listaTodos();
    }
    
    public void editar() {
        itemCompra = new ItemCompra();
    }
    
    public void excluir(ActionEvent e){
        compra = (Compra) e.getComponent().getAttributes().get("cp");
        compraFacade.remover(compra);
    }

    public void addItem(){
        itemCompra.setCompra(compra);
        compra.getItemCompra().add(itemCompra);
        itemCompra = new ItemCompra();
    }
    
    public void removeItem(ActionEvent e){
        itemCompra = (ItemCompra) e.getComponent().getAttributes().get("item");
        compra.getItemCompra().remove(itemCompra);
        itemCompra = new ItemCompra();
        if(compra.getValorTotal().compareTo(BigDecimal.ZERO) == 0){
            compra.setDesconto(BigDecimal.ZERO); 
        }
    }
    
    
    public String salvar() {
        if(validaCompra()){
            compraFacade.salvar(compra);
            return "compralistar";
        }else{
            return "compraeditar";
        }    
    }
    
     private boolean validaCompra() {
        if(compra.getItemCompra().isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", "Deve conter pelo menos um item na compra."));
            return false;
        }
        return true;
    }
    
    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public ItemCompra getItemCompra() {
        return itemCompra;
    }

    public void setItemCompra(ItemCompra itemCompra) {
        this.itemCompra = itemCompra;
    }
    
    
}
