package org.vaadin.tma;

import com.vaadin.ui.UI;
import org.vaadin.addon.leaflet.*;
import org.vaadin.addon.leaflet.shared.Point;

/**
 * @author mstahv
 */
public class MyPositionMarker implements LeafletLocateListener {

    private LMap map;
    private LCircle accuracy = new LCircle();
    private LCircleMarker dot = new LCircleMarker();
    private LMarker you = new LMarker();
    private LPolyline snake;
    private boolean centerNextPosition = false;
    private MapView ui;

    public void setMap(MapView ui) {
        if (this.map == null) {
            this.map = ui.getMap();
            map.addLocateListener(this);
            this.ui = ui;
        }
    }

    public void locate() {
        locate(true);
    }

    public void locate(boolean centerNextLocation) {
        map.locate(true, true, false);
        this.centerNextPosition = centerNextLocation;
    }

    public void stopLocate() {
        map.stopLocate();
    }

    @Override
    public void onLocate(LeafletLocateEvent event) {
        if(event.getAccuracy() > 100) {
            return;
        }
        Point myloc = event.getPoint();
        if (you.getParent() == null) {
            you.setPopup("You");
            dot.setColor("red");
            dot.setRadius(1);
            accuracy.setPoint(myloc);
            accuracy.setColor("yellow");
            accuracy.setStroke(false);
            snake = new LPolyline(myloc);
            snake.setColor("red");
            snake.getStyle().setWeight(1);

            you.setPoint(myloc);
            dot.setPoint(myloc);
            accuracy.setRadius(event.getAccuracy());
            map.addComponents(accuracy, dot, snake, you);
            map.setLayersToUpdateOnLocate(accuracy, dot, snake, you);
        }
        double zoomlevel = findAppropriateZoomlevel(event.getAccuracy());
        if (centerNextPosition) {
            map.setCenter(myloc);
            centerNextPosition = false;
        }

        ui.getLocate().setEnabled(true);
    }

    double findAppropriateZoomlevel(double accuracy) {
        final int screenW = UI.getCurrent().getPage().
                getBrowserWindowWidth();
        double[] mPerPixel = new double[]{156412, 78206, 39103, 19551, 9776, 4888, 2444, 1222, 610.984, 305.492, 152.746, 76.373, 38.187, 19.093, 9.547, 4.773, 2.387, 1.193, 0.596, 0.298};
        int zoomLevel = 0;
        for (int i = 0; i < mPerPixel.length; i++) {
            double metersOnScreen = mPerPixel[i] * screenW;
            if (metersOnScreen / 15.0 > accuracy) {
                zoomLevel = i;
            } else {
                break;
            }
        }
        if (zoomLevel > 15) {
            zoomLevel = 15;
        }
        return zoomLevel;
    }

}
