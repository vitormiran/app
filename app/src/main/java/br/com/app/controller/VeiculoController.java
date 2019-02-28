package br.com.app.controller;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.app.model.db.Veiculo;
import br.com.app.model.form.VeiculoForm;
import br.com.app.service.MailService;
import br.com.app.service.VeiculoService;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController extends BaseController {

	private VeiculoService veiculoService;
	
	public VeiculoController(VeiculoService veiculoService, MailService mailService) {
		this.veiculoService = veiculoService;
	}


	@GetMapping({"", "/", "/index"})
	public String list(Model model) {
		model.addAttribute("listagem", this.veiculoService.list());
		return "veiculo/list";
	}
	
	
	@GetMapping("/novo")
	public String novo(VeiculoForm form, Model model, HttpServletRequest request) { 
		return "veiculo/novo";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid VeiculoForm form, BindingResult result, Model model, RedirectAttributes redir, HttpServletRequest request) {
		if ( result.hasErrors() ) {
			return "veiculo/novo";
		}
		
		try {
			veiculoService.salvar(form);
			sucessoRegistroSalvo(redir);
			return "redirect:index";
			
		} catch (Exception e) {
			erroRegistroExistente(model);
			return "cliente/usuario/novo";
		}
	}

	@GetMapping({"/edit/{id}"})
	public String edit(VeiculoForm form, @PathVariable("id") long id) {
		
		Veiculo veiculoLoad = veiculoService.get(id);
		form.setId(veiculoLoad.getId())
			.setChassi(veiculoLoad.getChassi())
			.setId(veiculoLoad.getId())
			.setKmRodados(veiculoLoad.getKmRodados())
			.setModelo(veiculoLoad.getModelo())
			.setPlaca(veiculoLoad.getPlaca())
			.setVencimentoSeguro(veiculoLoad.getVencimentoSeguro());
		return "veiculo/edit";
	}
	
	@GetMapping({"/delete/{id}"})
	public String delete(@PathVariable("id") Long id, RedirectAttributes redir, Model model) {
		veiculoService.delete(id);
		sucessoRegistroExcluido(redir);
		model.addAttribute("listagem", this.veiculoService.list());
		return "redirect:/veiculo";
	}

	@GetMapping({"/pdf"})
	public void listPdf(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			List<Veiculo> list = this.veiculoService.list();
			generatePDF(list);
			downloadPDF(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void generatePDF(List<Veiculo> list) throws Exception {
	    Document doc = new Document();
	    File file = new File("C://TEMP//itext_Test.pdf");
	    FileOutputStream pdfFileout = new FileOutputStream(file);
	    PdfWriter.getInstance(doc, pdfFileout);
	    doc.open();
	    
	    PdfPTable table = new PdfPTable(5);
    	addTableHeader(table);

    	java.util.Iterator<Veiculo> it = list.iterator();
    	while (it.hasNext()) {
    		Veiculo currVeiculo = it.next();
    		addRows(table, currVeiculo);
    	}
    	doc.add(table);
	    doc.close();
	}
	 
	public void downloadPDF(HttpServletRequest request, HttpServletResponse response)
	    throws IOException{
	    response.setContentType("application/pdf");
	    response.setHeader("Content-disposition","attachment;filename="+ "testPDF.pdf");
	    try {
	        File f = new File("C://TEMP//Itext_Test.pdf");
	        FileInputStream fis = new FileInputStream(f);
	        DataOutputStream os = new DataOutputStream(response.getOutputStream());
	        response.setHeader("Content-Length",String.valueOf(f.length()));
	        byte[] buffer = new byte[1024];
	        int len = 0;
	        while ((len = fis.read(buffer)) >= 0) {
	            os.write(buffer, 0, len);
	        }
	        fis.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void addTableHeader(PdfPTable table) {
	    Stream.of("Placa", "Modelo", "KM Rodados", "Nro. Chassi", "Vencimento do Seguro")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
	private void addRows(PdfPTable table, Veiculo veiculo) {
	    table.addCell(veiculo.getPlaca());
	    table.addCell(veiculo.getModelo());
	    table.addCell(veiculo.getKmRodados());
	    table.addCell(veiculo.getChassi());
	    table.addCell(veiculo.getVencimentoSeguro());
	}
}