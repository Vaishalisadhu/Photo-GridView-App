package com.photogridviewapp.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.photogridviewapp.R;
import com.photogridviewapp.adapter.FullScreenImageAdapter;
import com.photogridviewapp.helper.CubeTransformer;
import com.photogridviewapp.model.ImageDataModel;

import java.util.ArrayList;
import java.util.Objects;

public class FullScreen extends AppCompatActivity {


    private FullScreenImageAdapter adapter;
    private ArrayList<ImageDataModel> imageDataModelList;
    private ViewPager viewPager;
    private int selectedImage;
    private Toolbar toolbar;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        init();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imageDataModelList = bundle.getParcelableArrayList("imageDataList");
            selectedImage = bundle.getInt("position");

        }


        try {

            adapter = new FullScreenImageAdapter(FullScreen.this, imageDataModelList);
            viewPager.setPageTransformer(true, new CubeTransformer());
            viewPager.setAdapter(adapter);

            // displaying selected image first
            viewPager.setCurrentItem(selectedImage);


            imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void init() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        imgBack = findViewById(R.id.imgBack);

        viewPager = findViewById(R.id.viewPager);

        imageDataModelList = new ArrayList<>();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
