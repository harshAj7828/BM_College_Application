package com.example.bmcollege.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bmcollege.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private ImageSlider imageSlider;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_home, container, false);
        imageSlider = view.findViewById(R.id.slider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image01, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);


//        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
//        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//        sliderLayout.setScrollTimeInSec(3);

        setSliderView();

        imageView = view.findViewById(R.id.map);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        return view;

    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=BM Group of Institutions,Gram Gokanya,NearChokhiDhani,Khandwa Road, Indore,Madhya Pradesh-452020");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void setSliderView() {
        for(int i=0; i<5; i++){
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i){
                case 0:
                    sliderView.setImageDrawable(R.drawable.headerimage);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.teacher);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.image01);
                    break;
            }
//            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageSlider.setImageList(slideModels\,ScaleTypes.FIT);

//            sliderLayout.addSliderView(sliderView);
        }
    }
}