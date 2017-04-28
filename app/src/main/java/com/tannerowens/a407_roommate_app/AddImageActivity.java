package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Nick on 4/28/2017.
 */

public class AddImageActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "AddImageActivity";

    CoordinatorLayout coordinatorLayout;
    AppCompatImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the views...
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        imgView = (AppCompatImageView) findViewById(R.id.imgView);

        configureBackButton();
        configureImageButton();
    }

    private void configureBackButton() {
        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void configureImageButton() {
        Button btnSelectImage = (Button) findViewById(R.id.addImg);
        btnSelectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });
    }

    /* Choose an image from Gallery */
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    String path = getPathFromURI(selectedImageUri);
                    Log.i(TAG, "Image Path : " + path);
                    // Set the image in ImageView
                    imgView.setImageURI(selectedImageUri);
                }
            }
        }
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

}
