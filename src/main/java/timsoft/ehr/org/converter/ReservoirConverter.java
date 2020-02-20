package timsoft.ehr.org.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import timsoft.ehr.org.model.Reservoir;

@FacesConverter("reservoirConverter")
public class ReservoirConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String id) {
    	if(id==null || id.isEmpty()){
    		return null;
    	}
    	else{
    	Reservoir depart = new Reservoir(Long.valueOf(id));	
    	return depart;
    	}
    	
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Reservoir) object).getId());
        }
        else {
            return null;
        }
    }   

}