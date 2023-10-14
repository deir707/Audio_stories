package com.unipi.diodeir.unipi_audio_stories;

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

public class Welcome extends AppCompat {
    TextView textView;
    ImageView imageView;
    StorageReference storageReference;
    TTS tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        storageReference = FirebaseStorage.getInstance().getReference();
        tts = new TTS(this);

        StorageReference image1 = storageReference.child("welcome_image.jpg");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image1.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageView.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void go1(View view){
        tts.speak(textView.getText().toString());
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}