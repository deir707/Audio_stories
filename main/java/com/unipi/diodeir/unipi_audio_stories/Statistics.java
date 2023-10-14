package com.unipi.diodeir.unipi_audio_stories;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Objects;

public class Statistics extends AppCompat {
    TextView textView16;
    ListView listView1, listView2;
    ImageView imageView13, imageView14;
    DatabaseReference databaseReference1, databaseReference2;
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayList<String> arrayList2 = new ArrayList<>();
    String item1,item2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        textView16 = findViewById(R.id.textView16);
        listView1 = findViewById(R.id.listView1);
        listView2 = findViewById(R.id.listView2);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList1);
        listView1.setAdapter(arrayAdapter1);
        databaseReference1 = FirebaseDatabase.getInstance().getReference();

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()){
                    item1 = Objects.requireNonNull(childSnapshot.getKey()) + "\n" + childSnapshot.child("Readings").getValue(String.class) + " times";
                    arrayList1.add(item1);
                }
                arrayAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList2);
        listView2.setAdapter(arrayAdapter2);
        databaseReference2 = FirebaseDatabase.getInstance().getReference();

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()){
                    item2 = Objects.requireNonNull(childSnapshot.getKey()) + "\n" + childSnapshot.child("Listenings").getValue(String.class) + " times";
                    arrayList2.add(item2);
                }
                arrayAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}