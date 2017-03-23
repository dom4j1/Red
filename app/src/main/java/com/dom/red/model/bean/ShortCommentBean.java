package com.dom.red.model.bean;

import java.util.List;

/**
 * Created by dom4j on 2017/3/14.
 */

public class ShortCommentBean {

    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * author : Xiaole说
         * id : 545721
         * content : 就吃了个花生米，呵呵
         * likes : 0
         * time : 1413600071
         * avatar : http://pic1.zhimg.com/c41f035ab_im.jpg
         */

        private String author;
        private int id;
        private String content;
        private int likes;
        private int time;
        private String avatar;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
