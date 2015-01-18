/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import util.Conexao;

/**
 *
 * @author Danilo_2
 */
@SessionScoped
@ManagedBean
public class relatorioControle implements Serializable{

    private Date dataInicial;
    private Date dataFinal;

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    
    public void gerarRelVendas() {
        try {
            String arquivoJasper = "relatoriovenda.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            Map m = new HashMap<Object, Object>();
            m.put("dtInicial", dataInicial);
            m.put("dtFinal", dataFinal);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/report/" + arquivoJasper), m, Conexao.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }  
}
