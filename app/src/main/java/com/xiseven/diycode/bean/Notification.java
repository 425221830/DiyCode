package com.xiseven.diycode.bean;

/**
 * Created by XISEVEN on 2016/12/20.
 */

public class Notification {

    /**
     * id : 32316
     * type : Mention
     * read : true
     * actor : {"id":2982,"login":"wenen","name":"温梦剑","avatar_url":"https://diycode.cc/system/letter_avatars/2/W/194_194_194/240.png"}
     * mention_type : Reply
     * mention : {"id":2196,"body_html":"<p><a href=\"#reply39\" class=\"at_floor\" data-floor=\"39\">#39楼<\/a> <a href=\"/xiseven\" class=\"at_user\" title=\"@xiseven\"><i>@<\/i>xiseven<\/a> /topics/:id/favorite.json<\/p>","created_at":"2016-11-20T09:39:08.530+08:00","updated_at":"2016-12-12T10:33:06.709+08:00","deleted":false,"topic_id":411,"user":{"id":2982,"login":"wenen","name":"温梦剑","avatar_url":"https://diycode.cc/system/letter_avatars/2/W/194_194_194/240.png"},"likes_count":0,"abilities":{"update":false,"destroy":false}}
     * topic : null
     * reply : null
     * node : null
     * created_at : 2016-11-20T09:39:08.571+08:00
     * updated_at : 2016-11-20T09:39:08.571+08:00
     */

    private int id;
    private String type;
    private boolean read;
    private ActorBean actor;
    private String mention_type;
    private MentionBean mention;
    private Object topic;
    private Object reply;
    private Object node;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public ActorBean getActor() {
        return actor;
    }

    public void setActor(ActorBean actor) {
        this.actor = actor;
    }

    public String getMention_type() {
        return mention_type;
    }

    public void setMention_type(String mention_type) {
        this.mention_type = mention_type;
    }

    public MentionBean getMention() {
        return mention;
    }

    public void setMention(MentionBean mention) {
        this.mention = mention;
    }

    public Object getTopic() {
        return topic;
    }

    public void setTopic(Object topic) {
        this.topic = topic;
    }

    public Object getReply() {
        return reply;
    }

    public void setReply(Object reply) {
        this.reply = reply;
    }

    public Object getNode() {
        return node;
    }

    public void setNode(Object node) {
        this.node = node;
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

    public static class ActorBean {
        /**
         * id : 2982
         * login : wenen
         * name : 温梦剑
         * avatar_url : https://diycode.cc/system/letter_avatars/2/W/194_194_194/240.png
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

    public static class MentionBean {
        /**
         * id : 2196
         * body_html : <p><a href="#reply39" class="at_floor" data-floor="39">#39楼</a> <a href="/xiseven" class="at_user" title="@xiseven"><i>@</i>xiseven</a> /topics/:id/favorite.json</p>
         * created_at : 2016-11-20T09:39:08.530+08:00
         * updated_at : 2016-12-12T10:33:06.709+08:00
         * deleted : false
         * topic_id : 411
         * user : {"id":2982,"login":"wenen","name":"温梦剑","avatar_url":"https://diycode.cc/system/letter_avatars/2/W/194_194_194/240.png"}
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
             * id : 2982
             * login : wenen
             * name : 温梦剑
             * avatar_url : https://diycode.cc/system/letter_avatars/2/W/194_194_194/240.png
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
}
