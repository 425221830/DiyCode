package com.xiseven.diycode.bean;

/**
 * Created by XISEVEN on 2016/12/17.
 */

public class TopicReplies {

    /**
     * id : 2524
     * body_html : <p>好东西，已经申请beta</p>
     * created_at : 2016-12-15T15:40:26.441+08:00
     * updated_at : 2016-12-15T15:40:26.441+08:00
     * deleted : false
     * topic_id : 504
     * user : {"id":781,"login":"keyu404","name":"风清","avatar_url":"https://diycode.cc/system/letter_avatars/2/K/155_206_95/240.png"}
     * likes_count : 0
     * abilities : {"update":false,"destroy":false}
     */

    private int id;
    private String body_html;
    private String created_at;
    private String updated_at;
    private boolean deleted;
    private int topic_id;
    private UserBean user;
    private int likes_count;
    private AbilitiesBean abilities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public AbilitiesBean getAbilities() {
        return abilities;
    }

    public void setAbilities(AbilitiesBean abilities) {
        this.abilities = abilities;
    }

    public static class UserBean {
        /**
         * id : 781
         * login : keyu404
         * name : 风清
         * avatar_url : https://diycode.cc/system/letter_avatars/2/K/155_206_95/240.png
         */

        private int id;
        private String login;
        private String name;
        private String avatar_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }

    public static class AbilitiesBean {
        /**
         * update : false
         * destroy : false
         */

        private boolean update;
        private boolean destroy;

        public boolean isUpdate() {
            return update;
        }

        public void setUpdate(boolean update) {
            this.update = update;
        }

        public boolean isDestroy() {
            return destroy;
        }

        public void setDestroy(boolean destroy) {
            this.destroy = destroy;
        }
    }
}
