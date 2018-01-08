package org.vaadin.tma.gwt;

import java.util.HashSet;
import java.util.Set;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.vaadin.server.widgetsetutils.ConnectorBundleLoaderFactory;
import com.vaadin.shared.ui.Connect.LoadStyle;

/**
 * This class is automatically built with the debug dialog in browser 
 * (triggered by ?debug query parameter). Currently it lists all connector used
 * in the app, but most optimal setup contains just the widgets on the initial
 * screen.
 * 
 * @author mstahv
 */
public class OptimizedConnectorBundleLoaderFactory extends
        ConnectorBundleLoaderFactory {

    private Set<String> eagerConnectors = new HashSet<String>();

    {
        eagerConnectors.add(com.vaadin.client.ui.ui.UIConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.button.ButtonConnector.class.getName());
        eagerConnectors.add(org.vaadin.touchkit.gwt.client.vcom.navigation.NavigationBarConnector.class.getName());
        eagerConnectors.add(org.vaadin.addon.leaflet.client.LeafletTileLayerConnector.class.getName());
        eagerConnectors.add(org.vaadin.touchkit.gwt.client.vcom.navigation.NavigationViewConnector.class.getName());
        eagerConnectors.add(org.vaadin.addon.vleafletproj.client.LeafletProjectionMapConnector.class.getName());
        eagerConnectors.add(org.vaadin.touchkit.gwt.client.vcom.TabBarConnector.class.getName());
        eagerConnectors.add(org.vaadin.touchkit.gwt.client.vcom.HorizontalButtonGroupConnector.class.getName());
        eagerConnectors.add(org.vaadin.addon.leaflet.client.LeafletMarkerConnector.class.getName());
        eagerConnectors.add(org.vaadin.touchkit.gwt.client.vcom.OfflineModeConnector.class.getName());
        eagerConnectors.add(org.vaadin.addon.leaflet.client.LeafletGridLayerConnector.class.getName());
        eagerConnectors.add(org.vaadin.addon.leaflet.client.LeafletMapConnector.class.getName());
        eagerConnectors.add(org.vaadin.touchkit.gwt.client.vcom.navigation.NavigationButtonConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.label.LabelConnector.class.getName());
        eagerConnectors.add(org.vaadin.touchkit.gwt.client.vcom.VerticalComponentGroupConnector.class.getName());
        eagerConnectors.add(org.vaadin.touchkit.gwt.client.vcom.navigation.NavigationManagerConnector.class.getName());
        eagerConnectors.add(org.vaadin.touchkit.gwt.client.vcom.DatePickerConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.textfield.TextFieldConnector.class.getName());
        eagerConnectors.add(org.vaadin.touchkit.gwt.client.vcom.EmailFieldConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.csslayout.CssLayoutConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.notification.NotificationConnector.class.getName());
        eagerConnectors.add(org.vaadin.addon.leaflet.client.LeafletCircleMarkerConnector.class.getName());
        eagerConnectors.add(org.vaadin.addon.leaflet.client.LeafletCircleConnector.class.getName());
        eagerConnectors.add(org.vaadin.addon.leaflet.client.LeafletPolylineConnector.class.getName());
    }

    @Override
    protected LoadStyle getLoadStyle(JClassType connectorType) {
        if (eagerConnectors.contains(connectorType.getQualifiedBinaryName())) {
            return LoadStyle.EAGER;
        } else {
            // Loads all other connectors immediately after the initial view has
            // been rendered
            return LoadStyle.DEFERRED;
            // NONE makes overall downloaded size smaller, but makes development bit harder as non-listend widgets would not work at all
            // return LoadStyle.NONE;
        }
    }
}
