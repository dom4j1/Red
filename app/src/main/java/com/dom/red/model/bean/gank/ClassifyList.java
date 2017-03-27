package com.dom.red.model.bean.gank;

import java.util.List;

/**
 * Created by dom4j on 2017/3/24.
 */

public class ClassifyList {

    /**
     * _id : 58d49a42421aa93abf5d3b73
     * createdAt : 2017-03-24T12:02:10.180Z
     * desc : 一款很漂亮的 iOS Alert 通知框
     * images : ["http://img.gank.io/94d93222-bf12-4979-a0f0-867328a152c0"]
     * publishedAt : 2017-03-24T12:12:34.753Z
     * source : chrome
     * type : iOS
     * url : https://github.com/Codigami/CFAlertViewController
     * used : true
     * who : CG
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
