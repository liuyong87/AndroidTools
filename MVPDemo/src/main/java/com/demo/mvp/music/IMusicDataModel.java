package com.demo.mvp.music;

import com.mvp.sdk.InterfaceModel;

/**
 * Created by win on 2019/10/10.
 */

public interface IMusicDataModel extends InterfaceModel {

    String getSongName();

    String getArtistName();

    String getAlbumName();

}
