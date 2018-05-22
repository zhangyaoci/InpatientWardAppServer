package action;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/*获取对应的Session*/
public class BaseAction implements SessionAware,ServletRequestAware, ServletContextAware {

    protected ServletContext application;
    protected HttpServletRequest request;
    protected Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session =  map;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request =  httpServletRequest;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.application = servletContext;
    }
}
