package org.project.youtube.Client.Model;

import java.util.List;
import java.util.UUID;

public class Playlist {
    UUID id;
    String name;
    String channelHandle;
    List<Video> videos;
    List<Short> shorts;

    public Playlist(UUID id, String name, String channelHandle, List<Video> videos, List<Short> shorts) {
        this.id = id;
        this.name = name;
        this.channelHandle = channelHandle;
        this.videos = videos;
        this.shorts = shorts;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannelHandle() {
        return channelHandle;
    }

    public void setChannelHandle(String channelHandle) {
        this.channelHandle = channelHandle;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Short> getShorts() {
        return shorts;
    }

    public void setShorts(List<Short> shorts) {
        this.shorts = shorts;
    }
}
