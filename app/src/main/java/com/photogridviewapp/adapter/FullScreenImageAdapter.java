package com.photogridviewapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.photogridviewapp.R;
import com.photogridviewapp.helper.ConstantDataMember;
import com.photogridviewapp.model.ImageDataModel;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class FullScreenImageAdapter extends PagerAdapter {

    private Activity _activity;
    private List<ImageDataModel> imageDataModels;
    private LayoutInflater inflater;
    private Typeface DROIDSANS, DROIDSANS_BOLD;

    // constructor
    public FullScreenImageAdapter(Activity activity,
                                  List<ImageDataModel> imageDataModels) {
        this._activity = activity;
        this.imageDataModels = imageDataModels;
        DROIDSANS = Typeface.createFromAsset(_activity.getAssets(), ConstantDataMember.DROIDSANS_FONT_STYLE);
        DROIDSANS_BOLD = Typeface.createFromAsset(_activity.getAssets(), ConstantDataMember.DROIDSANS_BOLD_FONT_STYLE);

    }

    @Override
    public int getCount() {
        return this.imageDataModels.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SimpleDraweeView imgDisplay;
        TextView txtTitle, txtDescription, txtDate;
        LinearLayout llManiDesc;

        inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,
                false);


        llManiDesc = viewLayout.findViewById(R.id.llManiDesc);

        imgDisplay = viewLayout.findViewById(R.id.imgDisplay);

        txtDate = viewLayout.findViewById(R.id.txtDate);
        txtDate.setTypeface(DROIDSANS);

        txtTitle = viewLayout.findViewById(R.id.txtTitle);
        txtTitle.setTypeface(DROIDSANS_BOLD);


        txtDescription = viewLayout.findViewById(R.id.txtDescription);
        txtDescription.setTypeface(DROIDSANS);

        View bottomSheet = viewLayout.findViewById(R.id.design_bottom_sheet);

        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);


        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {

                    case BottomSheetBehavior.STATE_DRAGGING:

                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("BottomSheetCallback", "slideOffset: " + slideOffset);
            }
        });

        llManiDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {

                    behavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });


        if (imageDataModels != null) {

            if (imageDataModels.get(position).getImageUrl() != null && !TextUtils.isEmpty(imageDataModels.get(position).getImageUrl())) {

                Uri imageUri = Uri.parse(imageDataModels.get(position).getImageUrl());
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(imageUri)
                        .build();
                imgDisplay.setController(controller);
            }
            if (imageDataModels.get(position).getTitle() != null &&
                    !TextUtils.isEmpty(imageDataModels.get(position).getTitle())) {

                txtTitle.setText(imageDataModels.get(position).getTitle());

            }
            if (imageDataModels.get(position).getDiscription() != null &&
                    !TextUtils.isEmpty(imageDataModels.get(position).getDiscription())) {

                txtDescription.setText(imageDataModels.get(position).getDiscription());

            }
            if (imageDataModels.get(position).getDate() != null &&
                    !TextUtils.isEmpty(imageDataModels.get(position).getDate())) {

                txtDate.setText(imageDataModels.get(position).getDate());
            }
        }


        (container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView((LinearLayout) object);

    }
}
