package timsoft.ehr.org.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import timsoft.ehr.org.model.Excategory;

@FacesConverter("categoryConverter")
public class ExcategoryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String id) {
    	if(id==null || id.isEmpty()){
    		return null;
    	}
    	else{
    	Excategory depart = new Excategory(Long.valueOf(id));	
    	return depart;
    	}
    	
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Excategory) object).getId());
        }
        else {
            return null;
        }
    }   

}