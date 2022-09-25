package com.example.mediatekformationmobile.modele;

import com.example.mediatekformationmobile.outils.MesOutils;

import java.util.Date;

public class Formation implements Comparable {

    private int id;
    private Date publishedAt;
    private String title;
    private String description;
    private String videoId;
    private String cheminImage = "https://i.ytimg.com/vi/";

    public Formation(int id, Date publishedAt, String title, String description, String videoId) {
        this.id = id;
        this.publishedAt = publishedAt;
        this.title = title;
        this.description = description;
        this.videoId = videoId;
    }

    public int getId() {
        return id;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    /**
     * retourne la date en String au format jj/MM/yyyy
     * @return
     */
    public String getPublishedAtToString() {
        return MesOutils.convertDateToString(this.publishedAt);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMiniature() {
        return cheminImage+videoId+"/default.jpg";
    }

    public String getPicture() {
        return cheminImage+videoId+"/mqdefault.jpg";
    }

    public String getVideoId() {
        return videoId;
    }

    @Override
    public int compareTo(Object o) {
        return publishedAt.compareTo(((Formation)o).getPublishedAt());
    }

}

