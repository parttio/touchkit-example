package org.vaadin.tma;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import org.vaadin.touchkit.server.TouchKitServlet;
import org.vaadin.touchkit.settings.TouchKitSettings;

@SuppressWarnings("serial")
@WebServlet("/*")
public class TouchKitMapAntServlet extends TouchKitServlet {

    private TouchKitMapAntUIProvider uiProvider = new TouchKitMapAntUIProvider();

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        
        TouchKitSettings touchKitSettings = getTouchKitSettings();
        touchKitSettings.getWebAppSettings().setApplicationName("TouchKit mapant.fi example");
        touchKitSettings.getWebAppSettings().setApplicationShortName("TKMapAnt");
        touchKitSettings.getApplicationIcons().addApplicationIcon(192, 192, getServletContext().getContextPath()+"/VAADIN/icon128.png", true);
        touchKitSettings.getApplicationIcons().addApplicationIcon(512, 512, getServletContext().getContextPath()+"/VAADIN/icon512.png", true);
        
        getService().addSessionInitListener(new SessionInitListener() {
            @Override
            public void sessionInit(SessionInitEvent event) throws ServiceException {
                event.getSession().addUIProvider(uiProvider);
            }
        });
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    		/* This is here for demo purposes only and to get 100% score in Lighthouse PWA test, 
    		 * most often you'll want to do your HTTPS to HTTP redirect on some other level. */
    		String serverName = request.getServerName();
    		if(serverName.equals("virit.in") && request.getScheme().equals("http")) {
    			response.sendRedirect("https://virit.in" + request.getContextPath() + "/");
    		} else {
        		super.service(request, response);
    		}
    }

}
