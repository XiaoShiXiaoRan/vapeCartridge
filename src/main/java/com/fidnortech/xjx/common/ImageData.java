package com.fidnortech.xjx.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class ImageData {

    String url;
    String alt;
    String href;
    public ImageData(String name) {
        this.url = "http://192.168.0.160:62001/api/article/image/"+name;
        this.alt = name;
        this.href = "http://192.168.0.160:62001/api/article/image/"+name;
    }
}