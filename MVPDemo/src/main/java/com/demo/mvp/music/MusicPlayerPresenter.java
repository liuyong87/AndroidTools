package com.demo.mvp.music;

import com.mvp.sdk.BasePresenter;

/**
 * Created by win on 2019/10/10.
 */

public class MusicPlayerPresenter<M extends IMusicDataModel,V extends IMusicPlayerView> extends BasePresenter<M,V> implements IMusicPlayerPresenter {



    @Override
    public void reqLoadSongName() {
        if(!isBindView()){
            return;
        }
        getView().onShowSongName(mModelRfr.getSongName());
    }

    @Override
    public void reqLoadArtistName() {
        if(!isBindView()){
            return;
        }
        getView().onShowArtistName(mModelRfr.getArtistName());
    }

    @Override
    public void reqLoadAlbumName() {
        if(!isBindView()){
            return;
        }
        getView().onShowAlbumName(mModelRfr.getAlbumName());
    }

    @Override
    protected void onViewDestroy() {

    }
}
