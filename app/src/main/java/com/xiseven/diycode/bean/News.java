package com.xiseven.diycode.bean;

/**
 * Created by XISEVEN on 2016/12/4.
 */

public class News {

    /**
     * id : 1660
     * title : 程序员怎样鉴定强悍的小团队
     * created_at : 2016-12-04T13:54:11.593+08:00
     * updated_at : 2016-12-04T13:54:11.593+08:00
     * user : {"id":648,"login":"greens1995","name":"振之","avatar_url":"http://diycode.b0.upaiyun.com/user/large_avatar/648.jpg"}
     * node_name : Interesting
     * node_id : 11
     * last_reply_user_id : null
     * last_reply_user_login : null
     * replied_at : null
     * address : http://www.jianshu.com/p/0caeb5e23bdd?from=timeline
     * replies_count : 0
     */

    private int id;
    private String title;
    private String created_at;
    private String updated_at;
    private UserBean user;
    private String node_name;
    private int node_id;
    private Object last_reply_user_id;
    private Object last_reply_user_login;
    private Object replied_at;
    private String address;
    private int replies_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public Object getLast_reply_user_id() {
        return last_reply_user_id;
    }

    public void setLast_reply_user_id(Object last_reply_user_id) {
        this.last_reply_user_id = last_reply_user_id;
    }

    public Object getLast_reply_user_login() {
        return last_reply_user_login;
    }

    public void setLast_reply_user_login(Object last_reply_user_login) {
        this.last_reply_user_login = last_reply_user_login;
    }

    public Object getReplied_at() {
        return replied_at;
    }

    public void setReplied_at(Object replied_at) {
        this.replied_at = replied_at;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getReplies_count() {
        return replies_count;
    }

    public void setReplies_count(int replies_count) {
        this.replies_count = replies_count;
    }

    public static class UserBean {
        /**
         * id : 648
         * login : greens1995
         * name : 振之
         * avatar_url : http://diycode.b0.upaiyun.com/user/large_avatar/648.jpg
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
}
