import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class EmailVerificationBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email;
	
    public String getParam(){
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String projectId = paramMap.get("e");
        return projectId;
    }

}
