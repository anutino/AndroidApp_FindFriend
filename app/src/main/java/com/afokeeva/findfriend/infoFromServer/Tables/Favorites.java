package com.afokeeva.findfriend.infoFromServer.Tables;

public class Favorites {
    private String id_fav;
    private String id_animal;
    private String id_user;

    public Favorites(String id_fav, String id_animal, String id_user) {
        this.id_fav = id_fav;
        this.id_animal = id_animal;
        this.id_user = id_user;
    }

    public String getId_fav() {
        return id_fav;
    }

    public void setId_fav(String id_fav) {
        this.id_fav = id_fav;
    }

    public String getId_animal() {
        return id_animal;
    }

    public void setId_animal(String id_animal) {
        this.id_animal = id_animal;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
