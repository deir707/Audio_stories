package com.example.audio_stories;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Story extends AppCompat {
    TextView textView19;
    ImageView imageView15;
    ToggleButton toggleButton7;
    DatabaseReference reference;
    TTS tts;
    int i,j;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        textView19 = findViewById(R.id.textView19);
        imageView15 = findViewById(R.id.imageView15);
        toggleButton7 = findViewById(R.id.toggleButton7);
        title = getIntent().getStringExtra("Title");
        reference = FirebaseDatabase.getInstance().getReference(title);
        tts = new TTS(this);
        LanguageManager languageManager = new LanguageManager(this);
        languageManager.updateResource(languageManager.getLang());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Picasso.get().load(Objects.requireNonNull(snapshot.child("Image_Url").getValue()).toString()).fit().into(imageView15);
                if (languageManager.getLang().equals("en")){
                    tts.speak(Objects.requireNonNull(snapshot.child("Title_ENG").getValue()).toString());
                    textView19.setText(Objects.requireNonNull(snapshot.child("English").getValue()).toString());
                }
                else if (languageManager.getLang().equals("el")){
                    tts.speak(Objects.requireNonNull(snapshot.child("Title_GR").getValue()).toString());
                    textView19.setText(Objects.requireNonNull(snapshot.child("Greek").getValue()).toString());
                }
                else if (languageManager.getLang().equals("fr")){
                    tts.speak(Objects.requireNonNull(snapshot.child("Title_FR").getValue()).toString());
                    textView19.setText(Objects.requireNonNull(snapshot.child("French").getValue()).toString());
                }
                textView19.setMovementMethod(new ScrollingMovementMethod());
                if (snapshot.child("Readings").exists()) {
                    i= Integer.parseInt(Objects.requireNonNull(snapshot.child("Readings").getValue()).toString());
                }
                i++;
                reference.child("Readings").setValue(Integer.toString(i));
                if (snapshot.child("Listenings").exists()) {
                    j = Integer.parseInt(Objects.requireNonNull(snapshot.child("Listenings").getValue()).toString());
                }
                if(snapshot.child("Favorite").exists()){
                    toggleButton7.setChecked(true);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                textView19.setText(error.getMessage());
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        tts.stop();
    }
    public void play(View view) {
        j++;
        reference.child("Listenings").setValue(Integer.toString(j));
        tts.speak(textView19.getText().toString());
    }
    public void favorite(View view){
        LanguageManager languageManager = new LanguageManager(this);
        languageManager.updateResource(languageManager.getLang());
        if (toggleButton7.isChecked()) {
            reference.child("Favorite").setValue(true);
            toggleButton7.setChecked(true);
            if (languageManager.getLang().equals("en")){
                Toast.makeText(this,"You have added this story to your favorites!",Toast.LENGTH_SHORT).show();
            }
            else if (languageManager.getLang().equals("el")){
                Toast.makeText(this,"Έχετε προσθέσει αυτήν την ιστορία στα αγαπημένα σας!",Toast.LENGTH_SHORT).show();
            }
            else if (languageManager.getLang().equals("fr")){
                Toast.makeText(this,"Vous avez ajouté cette histoire à vos favoris!",Toast.LENGTH_SHORT).show();
            }

        }
        else {
            reference.child("Favorite").removeValue();
            toggleButton7.setChecked(false);
            if (languageManager.getLang().equals("en")){
                Toast.makeText(this,"You have removed this story from your favorites!",Toast.LENGTH_SHORT).show();
            }
            else if (languageManager.getLang().equals("el")){
                Toast.makeText(this,"Καταργήσατε αυτήν την ιστορία από τα αγαπημένα σας!",Toast.LENGTH_SHORT).show();
            }
            else if (languageManager.getLang().equals("fr")){
                Toast.makeText(this,"Vous avez supprimé cette histoire de vos favoris!",Toast.LENGTH_SHORT).show();
            }

        }
    }
}