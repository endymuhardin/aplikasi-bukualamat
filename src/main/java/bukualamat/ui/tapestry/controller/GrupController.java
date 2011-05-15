package bukualamat.ui.tapestry.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.record.PropertyChangeObserver;

import bukualamat.entity.Grup;
import bukualamat.service.BukuAlamatService;

import com.javaforge.tapestry.spring.annotations.InjectSpring;

public abstract class GrupController extends BasePage {
	@InjectSpring("bukuAlamatService")
	public abstract BukuAlamatService getBukuAlamatService();

	private Grup grup = new Grup();
	private List<Grup> daftarGrup = new ArrayList<Grup>();
		
	public String simpan(){
		getBukuAlamatService().simpan(grup);
		grup = new Grup();
		System.out.println("Method simpan ditekan");
		return "DaftarGrup";
	}

	public List<Grup> getDaftarGrup() {
		daftarGrup = getBukuAlamatService().semuaGrup();
		return daftarGrup;
	}

	public void setDaftarGrup(List<Grup> daftarGrup) {
		this.daftarGrup = daftarGrup;
	}

	public Grup getGrup() {
		return grup;
	}

	public void setGrup(Grup grup) {
		this.grup = grup;
	}

	@Override
	public PropertyChangeObserver getPropertyChangeObserver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getClientId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClientId(String arg0) {
		// TODO Auto-generated method stub
		
	}
}
