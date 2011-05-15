package bukualamat.ui.tapestry.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tapestry.form.IPropertySelectionModel;
import org.apache.tapestry.form.StringPropertySelectionModel;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.record.PropertyChangeObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bukualamat.entity.Grup;
import bukualamat.entity.Kontak;
import bukualamat.service.BukuAlamatService;

import com.javaforge.tapestry.spring.annotations.InjectSpring;


public abstract class KontakController extends BasePage {
	
	private final Logger LOGGER = LoggerFactory.getLogger(KontakController.class);
	
	@InjectSpring("bukuAlamatService")
	public abstract BukuAlamatService getBukuAlamatService();

	private Kontak kontak = new Kontak();
	private Kontak entriKontak = new Kontak();
	private List<Kontak> daftarKontak= new ArrayList<Kontak>();
	
	public abstract String getSelectedGroupId();
		
	public List<Kontak> getDaftarKontak() {
		daftarKontak = getBukuAlamatService().semuaKontak();
		return daftarKontak;
	}

	public void setDaftarKontak(List<Kontak> daftarKontak) {
		this.daftarKontak = daftarKontak;
	}
	
	public String simpan() {
		LOGGER.info("SelectedGroupId [{}]", getSelectedGroupId());
		Grup grup = getBukuAlamatService().cariGrupByKode(getSelectedGroupId());
		LOGGER.info("Grup null [{}] ", grup);
		kontak.setGrup(grup);
		grup.addKontak(entriKontak);
		getBukuAlamatService().simpan(grup);
		entriKontak = new Kontak();
		
		return "DaftarKontak";
	}
	
	public IPropertySelectionModel getDaftarGroups() {
		List<Grup> daftarGrup = getBukuAlamatService().semuaGrup();
		String[] kodeGrup = new String[daftarGrup.size()];
		for (int i=0; i<daftarGrup.size();i++) {
			kodeGrup[i] = daftarGrup.get(i).getKode();
		}
		
		return new StringPropertySelectionModel(kodeGrup);
	}

	public Kontak getEntriKontak() {
		return entriKontak;
	}

	public void setEntriKontak(Kontak entriKontak) {
		this.entriKontak = entriKontak;
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
