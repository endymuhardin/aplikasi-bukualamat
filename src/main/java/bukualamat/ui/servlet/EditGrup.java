package bukualamat.ui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bukualamat.entity.Grup;
import bukualamat.service.BukuAlamatService;

@SuppressWarnings("serial")
public class EditGrup extends HttpServlet {
	
	private BukuAlamatService service;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ApplicationContext ctx = WebApplicationContextUtils
		.getWebApplicationContext(config.getServletContext());
		
		service = (BukuAlamatService) ctx.getBean("bukuAlamatService");
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		String output = "<html>";
		output += "<head>";
		output += "<title>Daftar Grup</title>";
		output += "</head>";
		output += "<body>";
		output += "<h1>Edit Grup</h1>";

		output += "<form method='post' action='simpan'>";
		output += "<table>";
		output += "<tr>";
		output += "<td>Kode</td>";
		output += "<td><input type='text' name='kode'></td>";
		output += "</tr>";
		
		output += "<tr>";
		output += "<td>Nama</td>";
		output += "<td><input type='text' name='nama'></td>";
		output += "</tr>";
		
		output += "<tr>";
		output += "<td>&nbsp;</td>";
		output += "<td><input type='submit' value='Simpan'></td>";
		output += "</tr>";
		
		
		output += "</table>";
		output += "</form>";
		
		output += "</body>";
		output += "</html>";
		
		resp.getWriter().print(output);
		resp.getWriter().close();
		
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String kode = req.getParameter("kode");
		String nama = req.getParameter("nama");
		
		Grup g = new Grup();
		g.setKode(kode);
		g.setNama(nama);
		
		service.simpan(g);
		resp.sendRedirect("list.asp");
	}
	
	
	
}
