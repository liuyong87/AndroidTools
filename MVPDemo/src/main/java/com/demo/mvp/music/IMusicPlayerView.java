package com.demo.mvp.music;

import com.mvp.sdk.InterfaceView;

/**
 * Created by win on 2019/10/10.
 */

public interface IMusicPlayerView extends InterfaceView{

    void onShowSongName(String songName);

    void onShowArtistName(String artistName);

    void onShowAlbumName(String albumName);
}
