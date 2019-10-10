package com.demo.mvp.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.mvp.sdk.BaseMvpActivity;
import com.demo.mvp.R;

/**
 * Created by win on 2019/10/10.
 */

public class MusicPlayerActivity extends BaseMvpActivity<MusicDataModel,IMusicPlayerView,MusicPlayerPresenter<MusicDataModel,IMusicPlayerView>> implements IMusicPlayerView{

    void initData(){
        if(!isBindPresenter()){
            return;
        }
        mPresenter.reqLoadSongName();
        mPresenter.reqLoadArtistName();
        mPresenter.reqLoadAlbumName();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    @Override
    public void showToast() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onShowSongName(String songName) {
        Toast.makeText(this,"歌名："+songName,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowArtistName(String artistName) {
        Toast.makeText(this,"歌手："+artistName,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowAlbumName(String albumName) {
        Toast.makeText(this,"专辑："+albumName,Toast.LENGTH_SHORT).show();
    }

    //------------------------------------------------
    @Override
    public MusicDataModel createModel() {
        return new MusicDataModel();
    }

    @Override
    public IMusicPlayerView createView() {
        return this;
    }

    @Override
    public MusicPlayerPresenter createPresenter() {
        return new MusicPlayerPresenter();
    }
}
