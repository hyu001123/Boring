package com.susion.boring.music.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.susion.boring.R;
import com.susion.boring.music.activity.PlayMusicActivity;
import com.susion.boring.music.model.Song;

/**
 * Created by susion on 17/2/13.
 */
public class MusicControlView extends LinearLayout{

    private Context mContext;
    private SimpleDraweeView mSdvAlbum;
    private TextView mTvArtist;
    private TextView mTvSongName;
    private ImageView mIvPlay;
    private ImageView mIvNext;
    private boolean mIsPlay;
    private Song mSong;

    private MusicControlViewListener mListener;

    public MusicControlView(Context context) {
        super(context);
        init();
    }

    public MusicControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MusicControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mContext = getContext();
        View.inflate(mContext, R.layout.view_music_control, this);
        findView();
        initListener();
        initView();
    }

    private void initView() {
        mIsPlay = false;
        setPlay(mIsPlay);
    }

    private void findView() {
        mSdvAlbum = (SimpleDraweeView) findViewById(R.id.view_music_control_sdv_album);
        mTvArtist = (TextView) findViewById(R.id.view_music_control_tv_song_artist);
        mTvSongName = (TextView) findViewById(R.id.view_music_control_tv_song_name);
        mIvPlay = (ImageView) findViewById(R.id.view_music_control_iv_play);
        mIvNext = (ImageView) findViewById(R.id.view_music_control_iv_next);
    }

    private void initListener() {

        mIvPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setPlay(!mIsPlay);
                if (mListener != null) {
                    mListener.onPlayClick(mIsPlay);
                }
            }
        });

        mIvNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onNextClick();
                }
            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSong != null) {
                    //跳转到音乐界面
                    PlayMusicActivity.startFromLittlePanel(mContext, mSong);
                }
            }
        });
    }

    public void setPlay(boolean isPlay){
        mIsPlay = isPlay;
        if (mIsPlay) {
            mIvPlay.setImageResource(R.mipmap.little_stop);
        } else {
            mIvPlay.setImageResource(R.mipmap.little_play);
        }
    }

    public void setMusic(Song song){
        mSong = song;
        setAlbum(song.album.picUrl);
        setSongInfo(song.artists.get(0).name, song.name);
    }

    public void setAlbum(String uri){
        mSdvAlbum.setImageURI(uri);
    }

    public void setSongInfo(String artist, String songName){
        mTvArtist.setText(artist+"");
        mTvSongName.setText(songName+"");
    }

    public void seMusicControlListener(MusicControlViewListener mListener) {
        this.mListener = mListener;
    }

    public interface MusicControlViewListener{
        void onPlayClick(boolean isPlay);
        void onNextClick();
    }

}
