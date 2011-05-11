package bukualamat.ui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bukualamat.entity.Grup;
import bukualamat.service.BukuAlamatService;

@SuppressWarnings("serial")
public class DaftarGrup extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ApplicationContext ctx = WebApplicationContextUtils
		.getWebApplicationContext(req.getSession().getServletContext());
		
		BukuAlamatService service = 
			(BukuAlamatService) ctx.getBean("bukuAlamatService");
		
		List<Grup> semuaGrup = service.semuaGrup();
		
		resp.setContentType("text/html");
		
		String output = "<html>";
		output += "<head>";
		output += "<title>Daftar Grup</title>";
		output += "</head>";
		output += "<body>";
		output += "<h1>Daftar Grup</h1>";
		
		output += "<table>";
		output += "<tr>";
		output += "<td>Kode</td>";
		output += "<td>Nama</td>";
		output += "<td>&nbsp;</td>";
		output += "</tr>";

		for (Grup grup : semuaGrup) {
			output += "<tr>";
			output += "<td>"+grup.getKode()+"</td>";
			output += "<td>"+grup.getNama()+"</td>";
			output += "<td><a href='edit?id="+grup.getId()+"'>edit</a>";
			output += " | <a href='delete?id="+grup.getId()+"'>delete</a></td>";
			output += "</tr>";
		}
		
		output += "</table>";
		
		output += "</body>";
		output += "</html>";
		
		resp.getWriter().print(output);
		resp.getWriter().close();
		
	}
	
}
