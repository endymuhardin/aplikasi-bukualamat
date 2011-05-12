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
import bukualamat.entity.Kontak;
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
		Connection conn = dataSource.getConnection();
		conn.createStatement().executeUpdate("truncate table t_kontak");
		conn.createStatement().executeUpdate("truncate table t_grup");
		conn.close();
	}
	
	//@Test
	public void testSimpanGrup() throws Exception {
		Grup g = new Grup();
		g.setKode("G-001");
		g.setNama("Teman Kuliah");
		
		bukuAlamatService.simpan(g);
		testIsiTabel("t_grup", 1);
	}
	
	
	//@Test
	public void testCascadeSave() throws Exception {
		Grup g = new Grup();
		g.setKode("G-002");
		g.setNama("Teman Kerja");
		
		
		Kontak k = new Kontak();
		k.setNama("Endy Muhardin");
		k.setEmail("endy.muhardin@gmail.com");
		k.setMobile("123");
		
		//k.setGrup(g);  // set FK
		//g.getDaftarKontak().add(k); // supaya k ikut cascade save
		g.addKontak(k); // bikin shortcut method supaya gak lupa
		
		bukuAlamatService.simpan(g);
		//bukuAlamatService.simpan(k); // tidak perlu kalau sudah cascade dan diadd
	}
	
	@Test 
	public void testDeleteOrphan(){
		isiDataContoh();
		
		Grup g = bukuAlamatService.cariGrupByKode("G-001");
		System.out.println("Jumlah kontak : "+g.getDaftarKontak().size());
		
		Kontak k = g.getDaftarKontak().get(0);
		
		g.removeKontak(k);

		// update k manual, karena sudah diremove dari daftar
		// sehingga tidak kena cascade
		k.setGrup(null);
		bukuAlamatService.simpan(k);
		
		// kalau orphanRemoval = true, maka k akan didelete dari database
		// karena sudah dikeluarkan dari daftarKontak
		bukuAlamatService.simpan(g);
		System.out.println("Jumlah kontak : "+g.getDaftarKontak().size());
	}
	
	private void isiDataContoh(){
		Grup g = new Grup();
		g.setKode("G-001");
		g.setNama("Teman Kuliah");
		
		Kontak k = new Kontak();
		k.setNama("Endy Muhardin");
		k.setEmail("endy.muhardin@gmail.com");
		g.addKontak(k);
		
		Kontak k1 = new Kontak();
		k1.setNama("Adi Sulistiono");
		k1.setEmail("adi@gmail.com");
		g.addKontak(k1);
		
		bukuAlamatService.simpan(g);
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
