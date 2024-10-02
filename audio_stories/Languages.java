package com.example.audio_stories;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.io.IOException;

public class Languages extends AppCompat {
    TextView textView2;
    ImageView imageView2, imageView3, imageView4;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        textView2 = findViewById(R.id.textView2);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference image2 = storageReference.child("uk.png");
        try {
            File localFile = File.createTempFile("tempImage", "jpg");
            image2.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageView2.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        StorageReference image3 = storageReference.child("greece.png");
        try {
            File localFile = File.createTempFile("tempImage", "jpg");
            image3.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageView3.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageReference image4 = storageReference.child("france.png");
        try {
            File localFile = File.createTempFile("tempImage", "jpg");
            image4.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageView4.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void go3(View view) {
        LanguageManager lang = new LanguageManager(this);
        lang.updateResource("en");
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
    }

    public void go4(View view) {
        LanguageManager lang = new LanguageManager(this);
        lang.updateResource("el");
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
    }

    public void go5(View view) {
        LanguageManager lang = new LanguageManager(this);
        lang.updateResource("fr");
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
    }
}