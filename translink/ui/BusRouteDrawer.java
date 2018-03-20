package ca.ubc.cs.cpsc210.translink.ui;

import android.content.Context;
import android.graphics.Canvas;
import ca.ubc.cs.cpsc210.translink.BusesAreUs;
import ca.ubc.cs.cpsc210.translink.model.*;
import ca.ubc.cs.cpsc210.translink.util.Geometry;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// A bus route drawer
public class BusRouteDrawer extends MapViewOverlay {
    /**
     * overlay used to display bus route legend text on a layer above the map
     */
    private BusRouteLegendOverlay busRouteLegendOverlay;
    /**
     * overlays used to plot bus routes
     */
    private List<Polyline> busRouteOverlays;

    /**
     * Constructor
     *
     * @param context the application context
     * @param mapView the map view
     */
    public BusRouteDrawer(Context context, MapView mapView) {
        super(context, mapView);
        busRouteLegendOverlay = createBusRouteLegendOverlay();
        busRouteOverlays = new ArrayList<>();
    }

    /**
     * Plot each visible segment of each route pattern of each route going through the selected stop.
     */
    public void plotRoutes(int zoomLevel) {
        float lineWidth = getLineWidth(zoomLevel);
        Stop selectedStop = StopManager.getInstance().getSelected();
        updateVisibleArea();
        busRouteOverlays.clear();
        busRouteLegendOverlay.clear();

        if (selectedStop != null) {

            for (Route nextRoute : selectedStop.getRoutes()) {
                busRouteLegendOverlay.draw(new Canvas(), mapView, false);
                busRouteLegendOverlay.add(nextRoute.getNumber());
                int routeColor = busRouteLegendOverlay.getColor(nextRoute.getNumber());


                for (RoutePattern rp : nextRoute.getPatterns()) {
                    List<LatLon> rpPath = rp.getPath();

                    for (int i = 0; i < rpPath.size() - 1; i++) {
                        if (Geometry.rectangleIntersectsLine(northWest, southEast, rpPath.get(i), rpPath.get(i+1))) {
                            Polyline newLine = new Polyline(context);
                            newLine.setColor(routeColor);
                            newLine.setWidth(lineWidth);

                            List<GeoPoint> points = new ArrayList<>();
                            points.add(new GeoPoint(rpPath.get(i).getLatitude(), rpPath.get(i).getLongitude()));
                            points.add(new GeoPoint(rpPath.get(i+1).getLatitude(), rpPath.get(i+1).getLongitude()));

                            newLine.setPoints(points);
                            busRouteOverlays.add(newLine);
                        }
                    }
                }
            }
        }
    }

    public List<Polyline> getBusRouteOverlays() {
        return Collections.unmodifiableList(busRouteOverlays);
    }

    public BusRouteLegendOverlay getBusRouteLegendOverlay() {
        return busRouteLegendOverlay;
    }


    /**
     * Create text overlay to display bus route colours
     */
    private BusRouteLegendOverlay createBusRouteLegendOverlay() {
        ResourceProxy rp = new DefaultResourceProxyImpl(context);
        return new BusRouteLegendOverlay(rp, BusesAreUs.dpiFactor());
    }

    /**
     * Get width of line used to plot bus route based on zoom level
     *
     * @param zoomLevel the zoom level of the map
     * @return width of line used to plot bus route
     */
    private float getLineWidth(int zoomLevel) {
        if (zoomLevel > 14)
            return 7.0f * BusesAreUs.dpiFactor();
        else if (zoomLevel > 10)
            return 5.0f * BusesAreUs.dpiFactor();
        else
            return 2.0f * BusesAreUs.dpiFactor();
    }
}
