/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entidade.BaseEntidade;
import facade.AbstractFacade;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author unipar
 */
public class ConverterGenerico implements Converter, Serializable {

    private AbstractFacade facade;
    private Class classe;

    public ConverterGenerico(AbstractFacade facade, Class classe) {
        this.facade = facade;
        this.classe = classe;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return facade.recuperar(classe, Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        BaseEntidade b = (BaseEntidade) value;
        return String.valueOf(b.getId());
    }
}
