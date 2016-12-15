package com.xiseven.diycode.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XISEVEN on 2016/12/6.
 */

public class Topic implements Parcelable {
    /**
     * id : 482
     * title : 非常认真的求指导，关于 Android 开发，来自一个工作一年多的弱鸡
     * created_at : 2016-12-06T22:30:07.242+08:00
     * updated_at : 2016-12-06T22:30:07.242+08:00
     * replied_at : null
     * replies_count : 0
     * node_name : Android
     * node_id : 1
     * last_reply_user_id : null
     * last_reply_user_login : null
     * user : {"id":3010,"login":"alex322326","name":"燕皓","avatar_url":"http://diycode.b0.upaiyun.com/user/large_avatar/3010.jpg"}
     * deleted : false
     * excellent : false
     * abilities : {"update":false,"destroy":false}
     */

    private int id;
    private String title;
    private String created_at;
    private String updated_at;
    private String replied_at;
    private int replies_count;
    private String node_name;
    private int node_id;
    private String last_reply_user_id;
    private String last_reply_user_login;
    private UserBean user;
    private boolean deleted;
    private boolean excellent;
    private AbilitiesBean abilities;

    public AbilitiesBean getAbilities() {
        return abilities;
    }

    public void setAbilities(AbilitiesBean abilities) {
        this.abilities = abilities;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isExcellent() {
        return excellent;
    }

    public void setExcellent(boolean excellent) {
        this.excellent = excellent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_reply_user_id() {
        return last_reply_user_id;
    }

    public void setLast_reply_user_id(String last_reply_user_id) {
        this.last_reply_user_id = last_reply_user_id;
    }

    public String getLast_reply_user_login() {
        return last_reply_user_login;
    }

    public void setLast_reply_user_login(String last_reply_user_login) {
        this.last_reply_user_login = last_reply_user_login;
    }

    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getReplied_at() {
        return replied_at;
    }

    public void setReplied_at(String replied_at) {
        this.replied_at = replied_at;
    }

    public int getReplies_count() {
        return replies_count;
    }

    public void setReplies_count(int replies_count) {
        this.replies_count = replies_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public static class UserBean implements Parcelable {
        /**
         * id : 3010
         * login : alex322326
         * name : 燕皓
         * avatar_url : http://diycode.b0.upaiyun.com/user/large_avatar/3010.jpg
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.login);
            dest.writeString(this.name);
            dest.writeString(this.avatar_url);
        }

        public UserBean() {
        }

        protected UserBean(Parcel in) {
            this.id = in.readInt();
            this.login = in.readString();
            this.name = in.readString();
            this.avatar_url = in.readString();
        }

        public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
            @Override
            public UserBean createFromParcel(Parcel source) {
                return new UserBean(source);
            }

            @Override
            public UserBean[] newArray(int size) {
                return new UserBean[size];
            }
        };
    }

    public static class AbilitiesBean implements Parcelable {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.update ? (byte) 1 : (byte) 0);
            dest.writeByte(this.destroy ? (byte) 1 : (byte) 0);
        }

        public AbilitiesBean() {
        }

        protected AbilitiesBean(Parcel in) {
            this.update = in.readByte() != 0;
            this.destroy = in.readByte() != 0;
        }

        public static final Creator<AbilitiesBean> CREATOR = new Creator<AbilitiesBean>() {
            @Override
            public AbilitiesBean createFromParcel(Parcel source) {
                return new AbilitiesBean(source);
            }

            @Override
            public AbilitiesBean[] newArray(int size) {
                return new AbilitiesBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
        dest.writeString(this.replied_at);
        dest.writeInt(this.replies_count);
        dest.writeString(this.node_name);
        dest.writeInt(this.node_id);
        dest.writeString(this.last_reply_user_id);
        dest.writeString(this.last_reply_user_login);
        dest.writeParcelable(this.user, flags);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
        dest.writeByte(this.excellent ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.abilities, flags);
    }

    public Topic() {
    }

    protected Topic(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.created_at = in.readString();
        this.updated_at = in.readString();
        this.replied_at = in.readString();
        this.replies_count = in.readInt();
        this.node_name = in.readString();
        this.node_id = in.readInt();
        this.last_reply_user_id = in.readString();
        this.last_reply_user_login = in.readString();
        this.user = in.readParcelable(UserBean.class.getClassLoader());
        this.deleted = in.readByte() != 0;
        this.excellent = in.readByte() != 0;
        this.abilities = in.readParcelable(AbilitiesBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Topic> CREATOR = new Parcelable.Creator<Topic>() {
        @Override
        public Topic createFromParcel(Parcel source) {
            return new Topic(source);
        }

        @Override
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };
}
