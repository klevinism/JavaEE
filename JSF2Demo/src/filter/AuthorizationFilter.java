package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		try {
			HttpServletRequest req = (HttpServletRequest)arg0;
			HttpServletResponse res = (HttpServletResponse)arg1;
			
			HttpSession session = req.getSession(false);
			
			String reqURI = req.getRequestURI();
			if(reqURI.indexOf("/login.xhtml")>=0 
					|| (session != null && session.getAttribute("username")!=null)
					|| reqURI.indexOf("/public/") >= 0
					|| reqURI.contains("java.faces.resource"))
				arg2.doFilter(arg0,arg1);
			else if(reqURI.indexOf("/signup.xhtml")>=0)
				arg2.doFilter(arg0, arg1);
			else
				res.sendRedirect(req.getContextPath() +  "/faces/login.xhtml");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
}
