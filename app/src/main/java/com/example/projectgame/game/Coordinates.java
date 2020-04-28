package com.example.projectgame.game;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class Coordinates {
    private GoogleMap googleMap;

    public Coordinates(GoogleMap googleMap){
        this.googleMap = googleMap;
        districtW();
        districtS();
    }

    public void districtW(){
        Polygon polygon = this.googleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(55.7565494,37.6156049),
                        new LatLng(55.713813,37.3836143),
                        new LatLng(55.747447,37.3666513),
                        new LatLng(55.786163,37.3682193),
                        new LatLng(55.790378,37.3710113),
                        new LatLng(55.800237,37.3805813),
                        new LatLng(55.805621,37.3847943),
                        new LatLng(55.832757, 37.395692),
                        new LatLng(55.836947, 37.396144),
                        new LatLng(55.840223, 37.395240),
                        new LatLng(55.845148, 37.392120),
                        new LatLng(55.848449, 37.392029),
                        new LatLng(55.859896, 37.398134),
                        new LatLng(55.865885, 37.403832),
                        new LatLng(55.870401, 37.410841),
                        new LatLng(55.8709594,37.4204727),
                        new LatLng(55.881665, 37.445163),
                        new LatLng(55.882601, 37.452988),
                        new LatLng(55.8846053,37.4747271),
                        new LatLng(55.890767, 37.493352),
                        new LatLng(55.904503, 37.525038),
                        new LatLng(55.906351, 37.531791),
                        new LatLng(55.90787,37.5409853)));
    }

    public void districtS(){
        Polygon polygon = this.googleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(55.7565494,37.6156049),
                        new LatLng(55.90787,37.5409853),
                        new LatLng(55.911058, 37.574679),
                        new LatLng(55.910900, 37.579976),
                        new LatLng(55.910372, 37.585656),
                        new LatLng(55.902297, 37.616266),
                        new LatLng(55.899828, 37.627977),
                        new LatLng(55.897293, 37.644404),
                        new LatLng(55.895851, 37.659381),
                        new LatLng(55.895192, 37.674831),
                        new LatLng(55.894439, 37.695487),
                        new LatLng(55.894031, 37.698846),
                        new LatLng(55.892681, 37.704444),
                        new LatLng(55.890138, 37.710433),
                        new LatLng(55.855996, 37.777206),
                        new LatLng(55.829960, 37.827780),
                        new LatLng(55.828494, 37.830316),
                        new LatLng(55.826663, 37.832925),
                        new LatLng(55.824303, 37.835243),
                        new LatLng(55.821481, 37.837222),
                        new LatLng(55.818196, 37.838091),
                        new LatLng(55.813940, 37.838898),
                        new LatLng(55.798438, 37.840186),
                        new LatLng(55.776930, 37.842713)));
    }

}
