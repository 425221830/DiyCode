package com.xiseven.diycode.bean;

import java.util.List;

/**
 * Created by XISEVEN on 2016/12/1.
 */

public class Sites {

    /**
     * sites : [{"name":"botlist","url":"http://botlist.co","avatar_url":"https://favicon.b0.upaiyun.com/ip2/botlist.co.ico"},{"name":"Producthunt","url":"http://www.producthunt.com","avatar_url":"https://favicon.b0.upaiyun.com/ip2/www.producthunt.com.ico"},{"name":"Hacker News","url":"http://news.ycombinator.com","avatar_url":"https://favicon.b0.upaiyun.com/ip2/news.ycombinator.com.ico"},{"name":"小众软件","url":"http://www.appinn.com","avatar_url":"https://favicon.b0.upaiyun.com/ip2/www.appinn.com.ico"},{"name":"NEXT","url":"http://next.36kr.com","avatar_url":"https://favicon.b0.upaiyun.com/ip2/next.36kr.com.ico"},{"name":"Startup News","url":"http://news.dbanotes.net","avatar_url":"https://favicon.b0.upaiyun.com/ip2/news.dbanotes.net.ico"},{"name":"mindstore","url":"http://mindstore.io","avatar_url":"http://diycode.b0.upaiyun.com/site/2016/4b347afdafb20540e072eae3424dc40d.ico"},{"name":"WooYun","url":"http://wiki.wooyun.org","avatar_url":"https://favicon.b0.upaiyun.com/ip2/wiki.wooyun.org.ico"},{"name":"少数派","url":"http://sspai.com","avatar_url":"https://favicon.b0.upaiyun.com/ip2/sspai.com.ico"},{"name":"leetcode","url":"http://leetcode.com","avatar_url":"https://favicon.b0.upaiyun.com/ip2/leetcode.com.ico"},{"name":"Designernews","url":"http://www.designernews.co","avatar_url":"https://favicon.b0.upaiyun.com/ip2/www.designernews.co.ico"},{"name":"前辈之路","url":"http://www.52cs.org","avatar_url":"https://favicon.b0.upaiyun.com/ip2/www.52cs.org.ico"},{"name":"代码时间","url":"http://codetimecn.com","avatar_url":"https://favicon.b0.upaiyun.com/ip2/codetimecn.com.ico"},{"name":"牛客网","url":"http://www.nowcoder.com","avatar_url":"https://favicon.b0.upaiyun.com/ip2/www.nowcoder.com.ico"},{"name":"pmcaff","url":"http://www.pmcaff.com","avatar_url":"https://favicon.b0.upaiyun.com/ip2/www.pmcaff.com.ico"},{"name":"利器IO","url":"http://liqi.io","avatar_url":"http://diycode.b0.upaiyun.com/site/2016/445f3b1330c26e275eb886e4d777af84.png"},{"name":"湾区日报","url":"http://wanqu.co","avatar_url":"http://diycode.b0.upaiyun.com/site/2016/bfb10ca7c264f60ab57d4001714e3574.png"},{"name":"硅谷密探","url":"http://www.svinsight.com","avatar_url":"http://diycode.b0.upaiyun.com/site/2016/9a96c32771192cc09b2beac1fa8ceded.ico"}]
     * name : FUN & COOL
     * id : 18
     */

    private String name;
    private int id;
    private List<SitesBean> sites;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SitesBean> getSites() {
        return sites;
    }

    public void setSites(List<SitesBean> sites) {
        this.sites = sites;
    }

    public static class SitesBean {
        /**
         * name : botlist
         * url : http://botlist.co
         * avatar_url : https://favicon.b0.upaiyun.com/ip2/botlist.co.ico
         */

        private String name;
        private String url;
        private String avatar_url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }
}
