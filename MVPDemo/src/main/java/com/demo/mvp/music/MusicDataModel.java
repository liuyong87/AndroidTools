package com.demo.mvp.music;

/**
 * Created by win on 2019/10/10.
 */

public class MusicDataModel implements IMusicDataModel {

    @Override
    public String getSongName() {
        return "My love !!!";
    }

    @Override
    public String getArtistName() {
        return "JackLiu";
    }

    @Override
    public String getAlbumName() {
        return "我的专辑";
    }
}
