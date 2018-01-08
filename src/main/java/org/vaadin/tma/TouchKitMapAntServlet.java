package org.vaadin.tma;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import org.vaadin.touchkit.server.TouchKitServlet;

@SuppressWarnings("serial")
@WebServlet("/*")
public class TouchKitMapAntServlet extends TouchKitServlet {

    private TouchKitMapAntUIProvider uiProvider = new TouchKitMapAntUIProvider();

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        getService().addSessionInitListener(new SessionInitListener() {
            @Override
            public void sessionInit(SessionInitEvent event) throws ServiceException {
                event.getSession().addUIProvider(uiProvider);
            }
        });
    }

}
