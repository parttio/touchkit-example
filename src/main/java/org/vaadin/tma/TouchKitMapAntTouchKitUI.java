package org.vaadin.tma;

import org.vaadin.tma.ui.*;
import org.vaadin.tma.gwt.client.*;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import org.vaadin.touchkit.annotations.CacheManifestEnabled;
import org.vaadin.touchkit.annotations.OfflineModeEnabled;
import org.vaadin.touchkit.extensions.OfflineMode;
import org.vaadin.touchkit.ui.NavigationManager;
import org.vaadin.touchkit.ui.TabBarView;

/**
 * The UI's "main" class
 */
@SuppressWarnings("serial")
@Widgetset("org.vaadin.tma.gwt.TouchKitMapAntWidgetSet")
@Theme("touchkit")
@Title("TKMapAnt")
// Cache static application files so as the application can be started
// and run even when the network is down.
@CacheManifestEnabled
// Switch to the OfflineMode client UI when the server is unreachable
@OfflineModeEnabled
// Make the server retain UI state whenever the browser reloads the app
@PreserveOnRefresh
public class TouchKitMapAntTouchKitUI extends UI {

    private final TouchKitMapAntPersistToServerRpc serverRpc = new TouchKitMapAntPersistToServerRpc() {
        @Override
        public void persistToServer() {
            // TODO this method is called from client side to store offline data
        }
    };

    @Override
    protected void init(VaadinRequest request) {
        final TabBarView tabBarView = new TabBarView();
        final NavigationManager navigationManager = new NavigationManager();
        navigationManager.setCaption("What!?");
        navigationManager.setCurrentComponent(new MenuView());
        Tab tab;
        tab = tabBarView.addTab(new MapView(), "Big O' Map");
        tab.setIcon(FontAwesome.GLOBE);
        tab = tabBarView.addTab(navigationManager);
        tab.setIcon(FontAwesome.BOOK);
        setContent(tabBarView);

        // Use of the OfflineMode connector is optional.
        OfflineMode offlineMode = new OfflineMode();
        offlineMode.extend(this);
        // Maintain the session when the browser app closes.
        offlineMode.setPersistentSessionCookie(true);
        // Define the timeout in secs to wait when a server request is sent
        // before falling back to offline mode.
        offlineMode.setOfflineModeTimeout(15);
    }
}

