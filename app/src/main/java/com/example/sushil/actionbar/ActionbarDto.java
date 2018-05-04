package com.example.sushil.actionbar;

/**
 * Created by sushil on 29-07-2017.
 */

public class ActionbarDto {
    String country;
   int image;
   /* ActionbarDto(String country,int image)
    {
        this.country=country;
        this.image=image;
    }*/
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
