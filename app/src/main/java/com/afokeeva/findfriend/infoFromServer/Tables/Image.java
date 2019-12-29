package com.afokeeva.findfriend.infoFromServer.Tables;

public class Image {
    private String id;
    private String jpg_url;
    private String id_animal;

    public Image(String id, String jpg_url, String id_animal) {
        this.id = id;
        this.jpg_url = jpg_url;
        this.id_animal = id_animal;
    }

    public Image(String jpg_url, String id_animal) {
        this.jpg_url = jpg_url;
        this.id_animal = id_animal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJpg_url() {
        return jpg_url;
    }

    public void setJpg_url(String jpg_url) {
        this.jpg_url = jpg_url;
    }

    public String getId_animal() {
        return id_animal;
    }

    public void setId_animal(String id_animal) {
        this.id_animal = id_animal;
    }
}
