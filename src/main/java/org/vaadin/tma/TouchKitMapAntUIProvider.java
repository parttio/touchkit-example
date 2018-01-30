package org.vaadin.tma;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class TouchKitMapAntUIProvider extends UIProvider {

    @Override
    public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {

        boolean mobileUserAgent = event.getRequest().getHeader("user-agent")
                .toLowerCase().contains("mobile");
        boolean mobileParameter = event.getRequest().getParameter("mobile") != null;

        /* Uncomment for desktop UI support, this example don't really neeed it
        if (overrideMobileUA() || mobileUserAgent || mobileParameter) {
            return TouchKitMapAntTouchKitUI.class;
        } else {
            return TouchKitMapAntFallbackUI.class;
        }
        */
        return TouchKitMapAntTouchKitUI.class;

    }

    private boolean overrideMobileUA() {
        VaadinSession session = VaadinSession.getCurrent();
        return session != null && session.getAttribute("mobile") != null;
    }
}
