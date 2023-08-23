package com.example.musicplayer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.musicplayer.Adapter.Adapter;
import com.example.musicplayer.Class.Song;
import com.example.musicplayer.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    Adapter adapter;
    CircleImageView img_dia_nhac;
    TextView song_name, time_start, time_end;
    ImageButton next, play, back;
    SeekBar seekBar;
    ListView listView;
    MediaPlayer mediaPlayer;

    ArrayList<Song> songArrayList;
    int i;
    int index;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.app_name);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.pink));
        }
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        loadView();
        addSong();

        adapter = new Adapter(MainActivity.this, R.layout.dong_bai_hat, songArrayList);
        listView.setAdapter(adapter);

        mediaPlayer = MediaPlayer.create(MainActivity.this, songArrayList.get(i).getFile());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    index = i;
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MainActivity.this, songArrayList.get(i).getFile());
                    song_name.setText(songArrayList.get(i).getNameSong());
                    mediaPlayer.start();
                    play.setBackgroundResource(R.drawable.baseline_pause_circle_24);
                    setTimeTotal();
                    upDateTime();
                    starAnimation(index);
//                    img_dia_nhac.startAnimation(animation);


            }

        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                song_name.setText(songArrayList.get(index).getNameSong());
                if(mediaPlayer.isPlaying()){
                    // neu dang phat nhac -> dung phat nhac -> doi hinh play
                    mediaPlayer.pause();
                    play.setBackgroundResource(R.drawable.baseline_play_circle_filled_24);
                }
                else {
                    // neu dang dung nhac -> phat nhac -> doi hinh pause
                    mediaPlayer.start();
                    play.setBackgroundResource(R.drawable.baseline_pause_circle_24);
                    starAnimation(index);
//                    img_dia_nhac.startAnimation(animation);
                }
                setTimeTotal();
                upDateTime();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = index;
                if(i >= 9){
                    i = -1;
                    index = -1;
                }
                i++;

                if(i >= songArrayList.size()){
                    i = 0;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                khoiTaoMedia(i);
                mediaPlayer.start();
                starAnimation(i);
                play.setBackgroundResource(R.drawable.baseline_pause_circle_24);
                setTimeTotal();
                upDateTime();
                index++;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = index;
                i--;
                if(i < 0){
                    i = songArrayList.size() - 1;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                khoiTaoMedia(i);
                mediaPlayer.start();
                starAnimation(i);
                play.setBackgroundResource(R.drawable.baseline_pause_circle_24);
                setTimeTotal();
                upDateTime();
                if(i==songArrayList.size() -1 ){
                    index = i;
                }else {
                    index--;
                }



            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });


    }

    private void loadView() {
        img_dia_nhac = findViewById(R.id.dia_nhac);
        song_name = findViewById(R.id.song_name);
        time_start = findViewById(R.id.time_start);
        time_end = findViewById(R.id.time_end);
        next = findViewById(R.id.next);
        play = findViewById(R.id.play);
        back = findViewById(R.id.back);
        seekBar = findViewById(R.id.seekbar);
        listView = findViewById(R.id.list_song);
    }

    private void addSong(){
        songArrayList = new ArrayList<>();
        songArrayList.add(new Song("Em Của Ngày Hôm Qua", "Sơn Tùng MTP", R.drawable.sontung,R.raw.emcuangayhomqua));
        songArrayList.add(new Song("Níu Duyên", "Lê Bảo Bình", R.drawable.img,R.raw.niuduyen ));
        songArrayList.add(new Song("À Lôi", "Double2T,Masew", R.drawable.img_1,R.raw.aloi ));
        songArrayList.add(new Song("Mối Tình Chiều Mưa Bay", "Lâm Chấn Hải", R.drawable.img_2,R.raw.moitinhchieumuabay ));
        songArrayList.add(new Song("Nên Chờ Hay Nên Quên", "Phan Duy Anh", R.drawable.img_3,R.raw.nenchohaynenquen ));
        songArrayList.add(new Song("Có Mới Nới Cũ", "Hồ Gia Khánh", R.drawable.img_4,R.raw.comoinoicu ));
        songArrayList.add(new Song("Yêu Một Người Gian Dối", "Như Việt", R.drawable.img_5,R.raw.yeumotnguoigiandoi ));
        songArrayList.add(new Song("Trang Giấy Trắng", "Phạm Trưởng", R.drawable.img_6,R.raw.tranggiaytrang ));
        songArrayList.add(new Song("Em Ơi Em Đừng Khóc", "Cao Thành Nam", R.drawable.img_7,R.raw.emoiemdungkhoc ));
        songArrayList.add(new Song("Bật Tình Yêu Lên", "Tăng Duy Tân,Hòa Minzy", R.drawable.img_8,R.raw.battinhyeulen ));
    }

    private void upDateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat time = new SimpleDateFormat("mm:ss");
                time_start.setText(time.format(mediaPlayer.getCurrentPosition()));
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                // next bai hat
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        i++;
                        if(i >= songArrayList.size()){
                            i = 0;
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        khoiTaoMedia(i);
                        mediaPlayer.start();
                        play.setBackgroundResource(R.drawable.baseline_pause_circle_24);
                        setTimeTotal();
                        upDateTime();
                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    private void setTimeTotal() {
        SimpleDateFormat timetital = new SimpleDateFormat("mm:ss");
        time_end.setText(timetital.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void khoiTaoMedia(int index) {
        mediaPlayer = MediaPlayer.create(MainActivity.this, songArrayList.get(index).getFile());
        song_name.setText(songArrayList.get(index).getNameSong());
        mediaPlayer.setLooping(true);
    }
    private void starAnimation(int pos){
        img_dia_nhac.setImageResource(songArrayList.get(pos).getPicture());
        img_dia_nhac.startAnimation(animation);

    }
}