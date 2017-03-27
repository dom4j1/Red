package com.dom.red.model.bean.zhihu;

import java.util.List;

/**
 * Created by dom4j on 2017/3/8.
 */

public class HomeListBean {

    /**
     * date : 20170308
     * stories : [{"images":["http://pic2.zhimg.com/95a2ec29d084c6b18be1c25424cfde4d.jpg"],"type":0,"id":9273100,"ga_prefix":"030812","title":"大误 · 长得丑且胖"},{"images":["http://pic1.zhimg.com/14cd3768c29fb5a23890260d5d6dfd00.jpg"],"type":0,"id":9273119,"ga_prefix":"030811","title":"川菜的「家常味」是一种怎样的味道？"},{"images":["http://pic1.zhimg.com/bad46f1ab930a97e7f3f9ffe5c96215c.jpg"],"type":0,"id":9234397,"ga_prefix":"030810","title":"毕业后工作的成长速度差异是如何造成的？"},{"images":["http://pic1.zhimg.com/3822bcf0cb16dd7ddaeda8764d2837c8.jpg"],"type":0,"id":9270894,"ga_prefix":"030809","title":"美人税、人工智能与知识经济"},{"images":["http://pic4.zhimg.com/cd0587b0ecd0fe4c053bd07f2244d9c3.jpg"],"type":0,"id":9271506,"ga_prefix":"030808","title":"潜伏期是一场惊险刺激的暗战，只是宿主一无所知"},{"images":["http://pic2.zhimg.com/a83a6f180cc31578324f994b22643ce5.jpg"],"type":0,"id":9271528,"ga_prefix":"030807","title":"下次看到它，可别再说「咳，这不就是扇贝嘛？！」"},{"images":["http://pic2.zhimg.com/5f500d6a68470ca95b6ab925de26b90d.jpg"],"type":0,"id":9269719,"ga_prefix":"030807","title":"「你看，女性数量越少的学科，平均智商越高」"},{"images":["http://pic2.zhimg.com/ed46e347e59d67e1b02230908b22b61d.jpg"],"type":0,"id":9269717,"ga_prefix":"030807","title":"「更早生育」对职场女性来说是不是好事？"},{"images":["http://pic2.zhimg.com/700d90583b61d514ee014a6cf3e0a1dd.jpg"],"type":0,"id":9270613,"ga_prefix":"030806","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/b08e7d45c0e19bd75248bcbb1cd255c4.jpg","type":0,"id":9269719,"ga_prefix":"030807","title":"「你看，女性数量越少的学科，平均智商越高」"},{"image":"http://pic2.zhimg.com/84e2c9c4a1e983f77c8e7ccab06c147d.jpg","type":0,"id":9269717,"ga_prefix":"030807","title":"「更早生育」对职场女性来说是不是好事？"},{"image":"http://pic4.zhimg.com/994882d7bda69db8966a5d65bbf0432b.jpg","type":0,"id":9270915,"ga_prefix":"030714","title":"不要让几张截图成了狂欢，这本书在认真把性教育做得更好"},{"image":"http://pic4.zhimg.com/ffdb5675c1c697c1a12fca6ccf31804f.jpg","type":0,"id":9270556,"ga_prefix":"030713","title":"共享单车这么火，共享汽车会是下一个热点吗？"},{"image":"http://pic2.zhimg.com/05c67496e38f662958a141847a734ffd.jpg","type":0,"id":9269183,"ga_prefix":"030708","title":"有些热闹的「共享经济」，恐怕只是一个美好的童话"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }



    public static class StoriesBean {
        /**
         * images : ["http://pic2.zhimg.com/95a2ec29d084c6b18be1c25424cfde4d.jpg"]
         * type : 0
         * id : 9273100
         * ga_prefix : 030812
         * title : 大误 · 长得丑且胖
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;
        private boolean flag = false;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : http://pic1.zhimg.com/b08e7d45c0e19bd75248bcbb1cd255c4.jpg
         * type : 0
         * id : 9269719
         * ga_prefix : 030807
         * title : 「你看，女性数量越少的学科，平均智商越高」
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
