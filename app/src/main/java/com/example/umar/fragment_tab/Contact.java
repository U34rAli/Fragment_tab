package com.example.umar.fragment_tab;

/**
 * Created by Umar on 7/19/2017.
 */

public class Contact {

    private int ID;
    private String image;
    private String name;
    private String phone;
    private byte[] bitmap_image;
    private int favourite;
    private String email;
    private String address;



    public Contact(String image, String name, String phone, byte[] bitmap_image, int ID, int f, String email , String address) {
        this.favourite = f;
        this.ID = ID;
        this.image = image;
        this.name = name;
        this.phone = phone;
        this.bitmap_image = bitmap_image;
        this.email = email;
        this.address = address;
    }



    public Contact(String image, String name, String phone, byte[] bitmap_image , String email , String address) {
        this.image = image;
        this.name = name;
        this.phone = phone;
        this.bitmap_image = bitmap_image;
        this.email = email;
        this.address = address;
    }





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public byte[] getBitmap_image() {
        return bitmap_image;
    }

    public void setBitmap_image(byte[] bitmap_image) {
        this.bitmap_image = bitmap_image;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEqual(String obj) {
        return phone.equals(obj);
    }

}
