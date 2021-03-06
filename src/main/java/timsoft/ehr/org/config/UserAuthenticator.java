package timsoft.ehr.org.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import timsoft.ehr.org.utils.AppUtils;

public class UserAuthenticator implements Filter {

	
        @Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
        @Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)arg0;
		HttpServletResponse resp=(HttpServletResponse)arg1;
		HttpSession session =req.getSession(false);
		if(session !=null && session.getAttribute(AppUtils.LOGIN_USER) !=null){
			chain.doFilter(arg0, arg1);
		}
		else{
			resp.sendRedirect(req.getContextPath());
		}
		// TODO Auto-generated method stub
		
	}

	
        @Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
