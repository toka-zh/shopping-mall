package com.tokaku.shoppingmall;

import java.io.Serializable;

public class GoodsBean implements Serializable {
    private String imageUrl;
    private String price;
    private String name;
    private String id;

    public GoodsBean(String imageUrl, String price, String name, String id) {
        this.imageUrl = imageUrl;
        this.price = price;
        this.name = name;
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "imageUrl='" + imageUrl + '\'' +
                ", price='" + price + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
