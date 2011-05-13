package bukualamat.ui.tapestry.controller;

import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.record.PropertyChangeObserver;

import com.javaforge.tapestry.spring.annotations.InjectSpring;

import bukualamat.entity.Grup;
import bukualamat.service.BukuAlamatService;

public abstract class GrupController extends BasePage {
	private Grup grup = new Grup();
	
	public void simpan(){
		getBukuAlamatService().simpan(grup);
		System.out.println("Method simpan ditekan");
	}

	@InjectSpring("bukuAlamatService")
	public abstract BukuAlamatService getBukuAlamatService();

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
