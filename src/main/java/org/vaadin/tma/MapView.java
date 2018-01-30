/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.tma;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.vaadin.addon.leaflet.LMarker;
import org.vaadin.addon.leaflet.LTileLayer;
import org.vaadin.addon.vleafletproj.LProjectionMap;
import org.vaadin.addon.vleafletproj.client.CustomProjection;
import org.vaadin.touchkit.ui.NavigationView;

/**
 *
 * @author mstahv
 */
public class MapView extends NavigationView {

    private final Button locate;
    private MyPositionMarker myPositionMarker;
    private final LProjectionMap map;

    public MapView() {
        setCaption("Mapant O' Map");
                LProjectionMap lProjectionMap = new LProjectionMap();
        CustomProjection customProjection = new CustomProjection();
        customProjection.setCode("EPSG:3067");
        customProjection.setProj4def("+proj=utm +zone=35 +ellps=GRS80 +units=m +towgs84=0,0,0,-0,-0,-0,0 +no_defs");
        customProjection.setProjectedBounds("[-548576.0, 6291456.0, 1548576.0, 8388608]");
        customProjection.setOptions("{" +
"        \"resolutions\": [" +
"            8192," +
"            4096," +
"            2048," +
"            1024," +
"            512," +
"            256," +
"            128," +
"            64," +
"            32," +
"            16," +
"            8," +
"            4," +
"            2," +
"            1," +
"            0.5," +
"            0.25," +
"            0.125," +
"            0.0625," +
"            0.03125," +
"            0.015625" +
"        ], \"origin\":[-548576.0, 8388608]}");
        lProjectionMap.setCustomTmsCrs(customProjection);

        String tileTemplate = "https://wmts.mapant.fi/wmts.php?z={z}&x={x}&y={y}";
        LTileLayer lTileLayer = new LTileLayer(tileTemplate);
        lTileLayer.setAttributionString("<a href=\"http://www.maanmittauslaitos.fi/en/digituotteet/laser-scanning-data\" target=\"_blank\">Laser scanning</a> and <a href=\"http://www.maanmittauslaitos.fi/en/digituotteet/topographic-database\" target=\"_blank\">topographic</a> data provided by the <a href=\"http://www.maanmittauslaitos.fi/en\" target=\"_blank\">National Land Survey of Finland</a> under the <a href=\"https://creativecommons.org/licenses/by/4.0/legalcode\">Creative Commons license</a>.");
        
        lTileLayer.setDetectRetina(true);
        
        lProjectionMap.addLayer(lTileLayer);
                
        lProjectionMap.setSizeFull();
        // N=6711010.287, E=241608.383(ETRS-TM35FIN), if you need to project, do that on the server side
        LMarker lMarker = new LMarker(60.452405, 22.300335);
        lMarker.setPopup("Sponsored by <a href='https://vaadin.com/'>Vaadin Ltd</a>");
        lProjectionMap.addComponent(lMarker);
        
        lProjectionMap.addClickListener(e -> {
            Notification.show("Clicked at " + e.getPoint());
        });
        
        lProjectionMap.zoomToContent();
        lProjectionMap.setZoomLevel(12);
        this.map = lProjectionMap;
        
        locate = new Button(FontAwesome.BULLSEYE);
        locate.addClickListener(e -> {
            if(myPositionMarker == null) {
                myPositionMarker = new MyPositionMarker();
                myPositionMarker.setMap(this);
            }
            myPositionMarker.locate(true);
            locate.setEnabled(false);
        });
        setLeftComponent(locate);
        
        setContent(lProjectionMap);
    }

    public Button getLocate() {
        return locate;
    }

    public LProjectionMap getMap() {
        return map;
    }
    
}
