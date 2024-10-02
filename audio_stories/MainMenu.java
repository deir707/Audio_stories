package com.example.audio_stories;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MainMenu extends AppCompat {
    ImageButton imageButton;
    ImageView imageView5, imageView6;
    StorageReference storageReference;
    TTS tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        imageButton = findViewById(R.id.imageButton);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        storageReference = FirebaseStorage.getInstance().getReference();
        tts = new TTS(this);

        StorageReference image1 = storageReference.child("language.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image1.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageButton.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageReference image2 = storageReference.child("stories.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image2.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageView5.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageReference image3 = storageReference.child("statistics.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image3.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageView6.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void go2(View view){
        LanguageManager languageManager = new LanguageManager(this);
        languageManager.updateResource(languageManager.getLang());
        if (languageManager.getLang().equals("en")){
            tts.speak("Select your language");
        }
        else if (languageManager.getLang().equals("el")){
            tts.speak("Επιλέξτε τη γλώσσα που επιθυμείτε");
        }
        else if (languageManager.getLang().equals("fr")){
            tts.speak("Choisissez votre langue");
        }
        Intent intent = new Intent(this,Languages.class);
        startActivity(intent);
    }
    public void go6(View view){
        LanguageManager languageManager = new LanguageManager(this);
        languageManager.updateResource(languageManager.getLang());
        if (languageManager.getLang().equals("en")){
            tts.speak("Select a story");
        }
        else if (languageManager.getLang().equals("el")){
            tts.speak("Διαλέξτε μία ιστορία");
        }
        else if (languageManager.getLang().equals("fr")){
            tts.speak("Sélectionnez une histoire");
        }
        Intent intent = new Intent(this,List_Stories.class);
        startActivity(intent);
    }
    public void go7(View view){
        LanguageManager languageManager = new LanguageManager(this);
        languageManager.updateResource(languageManager.getLang());
        if (languageManager.getLang().equals("en")){
            tts.speak("Here is the list of the 6 most read stories");
        }
        else if (languageManager.getLang().equals("el")){
            tts.speak("Ακολουθεί η λίστα με τις 6 πιο διαβασμένες ιστορίες");
        }
        else if (languageManager.getLang().equals("fr")){
            tts.speak("Voici la liste des 6 histoires les plus lues");
        }
        Intent intent = new Intent(this,Statistics.class);
        startActivity(intent);
    }
}