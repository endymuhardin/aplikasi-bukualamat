package bukualamat.ui.tapestry.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.record.PropertyChangeObserver;

import bukualamat.entity.Kontak;
import bukualamat.service.BukuAlamatService;

import com.javaforge.tapestry.spring.annotations.InjectSpring;


public abstract class KontakController extends BasePage {

	@InjectSpring("bukuAlamatService")
	public abstract BukuAlamatService getBukuAlamatService();

	private List<Kontak> daftarKontak= new ArrayList<Kontak>();
		
	public List<Kontak> getDaftarKontak() {
		daftarKontak = getBukuAlamatService().semuaKontak();
		return daftarKontak;
	}

	public void setDaftarKontak(List<Kontak> daftarKontak) {
		this.daftarKontak = daftarKontak;
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
