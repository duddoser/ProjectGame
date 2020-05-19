package com.example.projectgame.game;

import android.content.Context;

import com.example.projectgame.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

//in this class resource districts are drawn
public class Coordinates {
    private GoogleMap googleMap;
    private Context context;

    public Coordinates(GoogleMap googleMap, Context context){
        this.googleMap = googleMap;
        this.context = context;
        districtW();
        districtS();
        districtM();
        districtI();
    }

    //wood district
    public void districtW(){
        Polygon polygon = this.googleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(55.7565494,37.6156049),
                        new LatLng(55.7565494,37.369442),
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
                        new LatLng(55.904503, 37.525038), // 55.902557,37.6156049 - где должно закончиться
                        new LatLng(55.906351, 37.531791),
                        new LatLng(55.90787,37.5409853),
                        new LatLng(55.90787,37.5409853),
                        new LatLng(55.911058, 37.574679),
                        new LatLng(55.910900, 37.579976),
                        new LatLng(55.910372, 37.585656),
                        new LatLng(55.902557,37.6156049)).
                        strokeColor(context.getResources().getColor(R.color.polygonW)).
                        fillColor(context.getResources().getColor(R.color.polygonW)));
    }

    //stone district
    public void districtS(){
        Polygon polygon = this.googleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(55.7565494,37.6156049),
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
                        new LatLng(55.813940, 37.838898), // 55.7565494,37.842021
                        new LatLng(55.798438, 37.840186),
                        new LatLng(55.776930, 37.842713),
                        new LatLng(55.776930, 37.842713),
                        new LatLng(55.772195, 37.843246),
                        new LatLng(55.768867, 37.843087),
                        new LatLng(55.7565494,37.842021)).
                        strokeColor(context.getResources().getColor(R.color.polygonS)).
                        fillColor(context.getResources().getColor(R.color.polygonS)));
    }

    //metal district
    public void districtM(){
        Polygon polygon = this.googleMap.addPolygon(new PolygonOptions().
                add(new LatLng(55.7565494,37.6156049),
                        new LatLng(55.7565494,37.842021),
                        new LatLng(55.742233, 37.841683),
                        new LatLng(55.715138, 37.838496),
                        new LatLng(55.713540, 37.837901),
                        new LatLng(55.710897, 37.836500),
                        new LatLng(55.699867, 37.830547),
                        new LatLng(55.697183, 37.829706),
                        new LatLng(55.695446, 37.829391),
                        new LatLng(55.693630, 37.829216),
                        new LatLng(55.691893, 37.829356),
                        new LatLng(55.688696, 37.829847),
                        new LatLng(55.680858, 37.832963),
                        new LatLng(55.672209, 37.836080),
                        new LatLng(55.664961, 37.838811),
                        new LatLng(55.662946, 37.839372),
                        new LatLng(55.661148, 37.839582),
                        new LatLng(55.659450, 37.839582),
                        new LatLng(55.657533, 37.839197),
                        new LatLng(55.655281, 37.838356),
                        new LatLng(55.653147, 37.836990),
                        new LatLng(55.650519, 37.834504),
                        new LatLng(55.648781, 37.832263),
                        new LatLng(55.626129, 37.797524),
                        new LatLng(55.619109, 37.784601),
                        new LatLng(55.610308, 37.768738),
                        new LatLng(55.602000, 37.753469),
                        new LatLng(55.591751, 37.729446),
                        new LatLng(55.580747, 37.700905),
                        new LatLng(55.574352, 37.683606),
                        new LatLng(55.573105, 37.678983), //55.574806,37.6156049
                        new LatLng(55.572432, 37.674571),
                        new LatLng(55.571937, 37.670298),
                        new LatLng(55.572135, 37.663750),
                        new LatLng(55.574806,37.6156049),
                        new LatLng(55.575580, 37.6156049)).
                        fillColor(context.getResources().getColor(R.color.polygonM))
                        .strokeColor(context.getResources().getColor(R.color.polygonM)));
    }

    //iron district
    public void districtI(){
        Polygon polygon = this.googleMap.addPolygon(new PolygonOptions().
                add(new LatLng(55.7565494,37.6156049),
                        new LatLng(55.575580, 37.6156049),
                        new LatLng(55.576015, 37.596723),
                        new LatLng(55.576877, 37.590546),
                        new LatLng(55.594259, 37.517735),
                        new LatLng(55.595281, 37.514424),
                        new LatLng(55.596477, 37.511113),
                        new LatLng(55.598097, 37.507938),
                        new LatLng(55.599544, 37.505754),
                        new LatLng(55.601279, 37.503603),
                        new LatLng(55.623836, 37.477080),
                        new LatLng(55.627055, 37.473462),
                        new LatLng(55.652774, 37.443320),
                        new LatLng(55.658301, 37.436732),
                        new LatLng(55.664347, 37.430690),
                        new LatLng(55.673877, 37.423487),
                        new LatLng(55.682923, 37.416763),
                        new LatLng(55.684982, 37.415670),
                        new LatLng(55.690019, 37.413575),
                        new LatLng(55.691926, 37.411939),
                        new LatLng(55.694504, 37.409038),
                        new LatLng(55.700287, 37.401156),
                        new LatLng(55.704247, 37.395913),
                        new LatLng(55.708332, 37.390521),
                        new LatLng(55.710260, 37.388365),
                        new LatLng(55.712019, 37.386766),
                        new LatLng(55.713653, 37.385836),
                        new LatLng(55.747447,37.3666513),
                        new LatLng(55.7565494,37.369442)).
                        strokeColor(this.context.getResources().getColor(R.color.polygonI)).
                        fillColor(this.context.getResources().getColor(R.color.polygonI)));
    }

}
