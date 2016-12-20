package com.xiseven.diycode.bean;

/**
 * Created by XISEVEN on 2016/12/20.
 */

public class MyReplies {

    /**
     * id : 2197
     * body_html : <p><a href="#reply43" class="at_floor" data-floor="43">#43楼</a> <a href="/wenen" class="at_user" title="@wenen"><i>@</i>wenen</a> 测试确实有问题，api有些是有问题的，楼上也有反映，只能等官方修复了  </p>
     * created_at : 2016-11-20T10:14:44.876+08:00
     * updated_at : 2016-12-12T10:33:06.893+08:00
     * deleted : false
     * topic_id : 411
     * user : {"id":3021,"login":"xiseven","name":"xiseven","avatar_url":"https://diycode.cc/system/letter_avatars/2/X/124_222_235/240.png"}
     * likes_count : 0
     * abilities : {"update":false,"destroy":false}
     * body : #43楼 @wenen 测试确实有问题，api有些是有问题的，楼上也有反映，只能等官方修复了
     * topic_title : 深夜发布个小消息，Diycode 社区、项目、News、sites 的 API 发布了
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
    private String body;
    private String topic_title;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTopic_title() {
        return topic_title;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }

    public static class UserBean {
        /**
         * id : 3021
         * login : xiseven
         * name : xiseven
         * avatar_url : https://diycode.cc/system/letter_avatars/2/X/124_222_235/240.png
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
