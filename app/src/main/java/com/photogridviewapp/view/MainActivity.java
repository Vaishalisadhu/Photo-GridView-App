package com.photogridviewapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.photogridviewapp.R;
import com.photogridviewapp.adapter.PhotoGalleryAdapter;
import com.photogridviewapp.helper.ConstantDataMember;
import com.photogridviewapp.model.ImageDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPhotoGallery;
    private ArrayList<ImageDataModel> imageData;
    private PhotoGalleryAdapter photoGalleryAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private Typeface DROIDSANS, DROIDSANS_BOLD;
    private TextView toolbar_title;
    private Toolbar toolbarTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setData();
    }


    private void init() {


        //initialized Typeface
        DROIDSANS = Typeface.createFromAsset(this.getAssets(), ConstantDataMember.DROIDSANS_FONT_STYLE);
        DROIDSANS_BOLD = Typeface.createFromAsset(this.getAssets(), ConstantDataMember.DROIDSANS_BOLD_FONT_STYLE);

        // get the reference of RecyclerView
        rvPhotoGallery = findViewById(R.id.rvPhotoGallery);

        rvPhotoGallery.setHasFixedSize(true);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        rvPhotoGallery.setLayoutManager(staggeredGridLayoutManager);

        //set Animation to Recyclerview
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        rvPhotoGallery.setLayoutAnimation(animation);


        toolbarTop = findViewById(R.id.toolbar);
        toolbar_title = toolbarTop.findViewById(R.id.toolbar_title);

        toolbar_title.setTypeface(DROIDSANS_BOLD);
        toolbar_title.setText(R.string.app_name);


        imageData = new ArrayList<>();
    }


    private void setData() {

        try {
            // get JSONarray from JSON file
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());

            // implement for loop for getting users list data
            for (int i = 0; i < jsonArray.length(); i++) {

                // create a JSONObject for fetching single user data
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // fetch imgUrl and title and store it in arraylist
                ImageDataModel imageDataModel = new ImageDataModel();
                imageDataModel.setImageUrl(jsonObject.getString("url"));
                imageDataModel.setTitle(jsonObject.getString("title"));
                imageDataModel.setDiscription(jsonObject.getString("explanation"));
                imageDataModel.setDate(jsonObject.getString("date"));
                imageData.add(imageDataModel);

            }


            if (imageData != null && imageData.size() > 0) {

                photoGalleryAdapter = new PhotoGalleryAdapter(imageData, this);
                rvPhotoGallery.setAdapter(photoGalleryAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
