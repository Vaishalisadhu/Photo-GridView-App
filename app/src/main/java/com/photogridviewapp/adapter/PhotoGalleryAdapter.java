package com.photogridviewapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.photogridviewapp.R;
import com.photogridviewapp.helper.ConstantDataMember;
import com.photogridviewapp.model.ImageDataModel;
import com.photogridviewapp.view.FullScreen;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryAdapter.ViewHolder> {

    private ArrayList<ImageDataModel> imageDataModels;
    private Context context;
    private String imgUrl, title, description;
    private Typeface DROIDSANS, DROIDSANS_BOLD;

    public PhotoGalleryAdapter(ArrayList<ImageDataModel> imageDataModels, Context context) {

        this.imageDataModels = imageDataModels;
        this.context = context;
        DROIDSANS = Typeface.createFromAsset(context.getAssets(), ConstantDataMember.DROIDSANS_FONT_STYLE);
        DROIDSANS_BOLD = Typeface.createFromAsset(context.getAssets(), ConstantDataMember.DROIDSANS_BOLD_FONT_STYLE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recycler_layout, parent, false);
        return new PhotoGalleryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final ImageDataModel imageDataModel = imageDataModels.get(position);

        if (imageDataModel != null) {

            if (imageDataModel.getImageUrl() != null && !TextUtils.isEmpty(imageDataModel.getImageUrl())) {

                imgUrl = imageDataModel.getImageUrl();
                Uri imageUri = Uri.parse(imgUrl);
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(imageUri)
                        .build();
                holder.selectedImage.setController(controller);


            }

            if (imageDataModel.getTitle() != null && !TextUtils.isEmpty(imageDataModel.getTitle())) {

                holder.txtTitle.setText(imageDataModel.getTitle());
                title = imageDataModel.getTitle();
            }
            if (imageDataModel.getDiscription() != null && !TextUtils.isEmpty(imageDataModel.getDiscription())) {
                description = imageDataModel.getDiscription();
            }

            holder.selectedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, FullScreen.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("imageDataList", imageDataModels);
                    bundle.putInt("position", position);
                    i.putExtras(bundle);
                    context.startActivity(i);


                }
            });


        }


    }

    @Override
    public int getItemCount() {
        return imageDataModels.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        private SimpleDraweeView selectedImage;
        private TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            selectedImage = itemView.findViewById(R.id.selectedImage);
            txtTitle = itemView.findViewById(R.id.txtTitle);

            txtTitle.setTypeface(DROIDSANS);
        }

    }
}


