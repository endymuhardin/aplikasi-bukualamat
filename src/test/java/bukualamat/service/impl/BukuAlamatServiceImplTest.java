package bukualamat.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bukualamat.entity.Grup;
import bukualamat.service.BukuAlamatService;

public class BukuAlamatServiceImplTest {

	private static BukuAlamatService bukuAlamatService;
	private static DataSource dataSource;
	
	@BeforeClass
	public static void inisialisasi(){
		AbstractApplicationContext ctx = 
			new ClassPathXmlApplicationContext
			("classpath*:bukualamat/**/applicationContext.xml");
		ctx.registerShutdownHook();
		
		bukuAlamatService = (BukuAlamatService) 
		ctx.getBean("bukuAlamatService");
		
		dataSource = (DataSource) ctx.getBean("dataSource");
	}
	
	@Before
	public void resetDatabase() throws Exception {
		String sql = "truncate table t_grup";
		Connection conn = dataSource.getConnection();
		conn.createStatement().executeUpdate(sql);
		conn.close();
	}
	
	@Test
	public void testSimpanGrup() throws Exception {
		Grup g = new Grup();
		g.setKode("G-001");
		g.setNama("Teman Kuliah");
		
		bukuAlamatService.simpan(g);
		testIsiTabel("t_grup", 1);
	}
	
	private void testIsiTabel(String namatabel, Integer jumlahRecord)
	throws Exception {
		String sql = "select count(*) from "+namatabel;
		Connection conn = dataSource.getConnection();
		ResultSet rs = conn.createStatement().executeQuery(sql);
		
		Assert.assertTrue(rs.next());
		Assert.assertEquals(Long.valueOf(jumlahRecord), 
				Long.valueOf(rs.getLong(1)));
		
		conn.close();
	}

}
