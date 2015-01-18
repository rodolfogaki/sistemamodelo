package controle;

import converter.ConverterGenerico;
import entidade.ItemVenda;
import entidade.Pessoa;
import entidade.Produto;
import entidade.Venda;
import facade.PessoaFacade;
import facade.PessoaFisicaFacade;
import facade.PessoaJuridicaFacade;
import facade.ProdutoFacade;
import facade.VendaFacade;
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
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class VendaControle implements Serializable{

    private Venda       venda;
    private String      filtro;
    private List<Venda> vendas;
    
    private ItemVenda itemVenda;
    @EJB
    private VendaFacade vendaFacade;
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

    public ConverterGenerico getConverterProduto() {
        if (converterProduto == null) {
            converterProduto = new ConverterGenerico(produtoFacade, Produto.class);
        }
        return converterProduto;
    }

    public void setConverterProduto(ConverterGenerico converterProduto) {
        this.converterProduto = converterProduto;
    }

    public ConverterGenerico getConverterPessoa() {
        if (converterPessoa == null) {
            converterPessoa = new ConverterGenerico(pessoaFacade, Pessoa.class);
        }
        return converterPessoa;
    }

    public void setConverterPessoa(ConverterGenerico converterPessoa) {
        this.converterPessoa = converterPessoa;
    }

    public void addItem(){
        itemVenda.setVenda(venda);
        venda.getItemVendas().add(itemVenda);
        itemVenda = new ItemVenda();
    }
    
    public void removeItem(ActionEvent e){
        itemVenda = (ItemVenda) e.getComponent().getAttributes().get("item");
        venda.getItemVendas().remove(itemVenda);
        itemVenda = new ItemVenda();
        if(venda.getValorTotal().compareTo(BigDecimal.ZERO) == 0){
            venda.setDesconto(BigDecimal.ZERO); 
        }
    }
    
    public List<Pessoa> completaPessoa(String filtro) {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        lista.addAll(pessoaFisicaFacade.listaFiltrando(filtro, "nome", "cpf"));
        lista.addAll(pessoaJuridicaFacade.listaFiltrando(filtro, "nome", "nomeFantasia", "cnpj"));
        return lista;
    }

    public List<Produto> completaProduto(String filtro) {
        return produtoFacade.listaFiltrando(filtro, "nome");
    }

    public String salvar() {
        if(validaVenda()){
            vendaFacade.salvar(venda);
            return "vendalistar";
        }else{
            return "vendaeditar";
        }
        
    }

    public void novo() {
        venda = new Venda();
        itemVenda = new ItemVenda();
    }

    public void excluir(ActionEvent e) {
        venda = (Venda) e.getComponent().getAttributes().get("ve");
        vendaFacade.remover(venda);
    }

    public void editar() {
        // ActionEvent e venda = (Venda) e.getComponent().getAttributes().get("ve");
        itemVenda = new ItemVenda();
    }
    
    public void ListaProdutos() {
        RequestContext.getCurrentInstance().openDialog("Selecione o produto");
    }
    
    public List<Venda> listaVendas() {
        return vendaFacade.listaTodos();
       // return this.vendas;
    }
    
    public void consultaVenda() {
        this.vendas = vendaFacade.listaFiltrando(this.filtro, "nome");
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
    
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }

    private boolean validaVenda() {
        if(venda.getItemVendas().isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", "Deve conter pelo menos um item venda."));
            return false;
        }
        return true;
    }
    
    public void setaValorProduto(){
        itemVenda.setValor(itemVenda.getProduto().getPrecoVenda());
    }
}
