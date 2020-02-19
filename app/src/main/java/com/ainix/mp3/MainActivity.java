package com.ainix.mp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
private Button playBtn,pauseBtn,nextBtn;
    ListView mainList;
    int num=0;

    //Add a list items in String
    final String[] listContent = {"Africa", "He bore","Le menusier de ta vie","No vamos","Pire","Suze Dob","Ta vie dose","Wale","Wassa"};
    final int[] resId ={R.raw.africa,R.raw.he_bore,R.raw.le_menusier_de_ta_vie,R.raw.no_vamos,R.raw.pire,R.raw.suze_dob,R.raw.ta_vie_dose,R.raw.wale,R.raw.wassa};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainList = (ListView) findViewById(R.id.mp3list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listContent);
//Set your adapter to listView.
        mainList.setAdapter(adapter);
        playBtn=(Button)findViewById(R.id.play);
        pauseBtn=(Button)findViewById(R.id.pause);
        nextBtn=(Button)findViewById(R.id.next);
        mp=MediaPlayer.create(this, R.raw.africa);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSong(v);
            }
        });
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseSong(v);
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextSong();
            }
        });

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mp.reset();// stops any current playing song
                mp = MediaPlayer.create(getApplicationContext(), resId[i]);
                mp.start(); // starting mediaplayer
                num=i;
            }
        });
    }

    public void playSong(View v){
        mp.start();
    }
    public void nextSong(){
        num++;
        num %= listContent.length;
        mp.stop();
        mp = MediaPlayer.create(this,resId[num]);
        mp.start();
    }
    public  void prevSong(){
        num--;
        num = (num == -1)? listContent.length-1 : num;
        mp.stop();
        mp=MediaPlayer.create(this,resId[num]);
        mp.start();
    }

    public void pauseSong(View v) {
        mp.pause();
    }

    public void stopSong(View v) {
        mp.stop();
        mp=MediaPlayer.create(this, R.raw.africa);
    }

}
