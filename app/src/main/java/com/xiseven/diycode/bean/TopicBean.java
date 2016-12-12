package com.xiseven.diycode.bean;

/**
 * Created by XISEVEN on 2016/12/8.
 */

public class TopicBean {

    /**
     * id : 430
     * title : Clipboard 还能玩出花
     * created_at : 2016-11-16T10:23:00.903+08:00
     * updated_at : 2016-11-16T23:25:17.758+08:00
     * replied_at : null
     * replies_count : 0
     * node_name : Android
     * node_id : 1
     * last_reply_user_id : null
     * last_reply_user_login : null
     * user : {"id":1282,"login":"eclipse_xu","name":"徐宜生","avatar_url":"http://diycode.cc/system/letter_avatars/2/E/120_132_205/240.png"}
     * deleted : false
     * excellent : false
     * abilities : {"update":false,"destroy":false}
     * body : # Clipboard还能玩出花

     > 本文在 [DiyCode](http://www.diycode.cc/topics/430)  和 [CSDN个人博客](http://blog.csdn.net/eclipsexys/article/details/53183802) 同时首发，关注作者的 [DiyCode帐号](http://www.diycode.cc/eclipse_xu) 或者 [作者微博](http://weibo.com/1904977584) 可第一时间收到新文章推送。


     Clipboard是Android提供的一个系统服务，它提供了一个全局的剪贴板，让文字、图片、数据，在多App间共享成为可能，今天，我们来了解下它的真面目，以及被玩坏的新姿势。

     老规矩，Google API文档镇楼：
     https://developer.android.com/guide/topics/text/copy-paste.html

     说实话，如果不是为了让Clipboard玩出花，我真不想写这一篇，因为——这文档写的真是太TM详细了。

     ## Clipboard应用

     我们先来看看一些App对Clipboard的应用，例如手机迅雷，如果你复制了一个链接，那么打开迅雷后，会自动检测并提示下载：

     ![](http://ac-mhke0kuv.clouddn.com/dad4a1794f03e6e3c7f1.png)

     再例如一些翻译软件，例如有道词典、沪江小D，他们都有一个功能，即复制查词，使用的也是这个原理，我这没装这些App，就不截图了，再例如比较常用的手淘喵口令，实际上也是利用这个功能，当然，也有一些比较专业的Clipboard App，例如Clipboard Actions：


     ![](http://ac-mhke0kuv.clouddn.com/8f3fa19c0c955dda99c5.png)

     我们可以看见，实际上，他就是帮你解析了各种可能的剪贴板，并对他们提供了各种后续功能的集合，确实非常实用，不过，看完今天的文章，相信你要写一个这样的App，估计也就分分钟。

     OK，这些就是一些Clipboard的基本使用场景，更多场景，没有做不到，只有想不到。

     ## 基本使用

     Clipboard的基本使用，就是三部曲。

     获得ClipboardManager：

     ```
     ClipboardManager mClipboardManager = mClipboardManager =
     (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
     ```

     Copy：

     ```
     ClipData mClipData;
     String text = "hello world";
     mClipData = ClipData.newPlainText("test", text);
     mClipboardManager.setPrimaryClip(mClipData);
     ```

     Paste：

     ```
     ClipData clipData = mClipboardManager.getPrimaryClip();
     ClipData.Item item = clipData.getItemAt(0);
     String text = item.getText().toString();
     ```

     结束了，简直不能再简单，API文档也写的非常详细，Demo都写了好几个。

     ## 不止于文字

     我们可以创建以下三种类型的ClipData：

     类型  | 描述
     ------------- | -------------
     Text newPlainText(label, text) | 返回ClipData对象，其中ClipData.Item对象包含一个String
     URI newUri(resolver, label, URI) | 返回ClipData对象，其中ClipData.Item对象包含一个URI
     Intent newIntent(label, intent) | 返回ClipData对象，其中ClipData.Item对象包含一个Intent

     对应的，我们也能获取到不同类型的ClipData。

     ## ClipboardManager管理

     ClipboardManager中有很多判断与操作方法：

     类型  | 描述
     ------------- | -------------
     getPrimaryClip() | 返回剪贴板上的当前Copy内容
     getPrimaryClipDescription() | 返回剪贴板上的当前Copy的说明
     hasPrimaryClip() | 如果当前剪贴板上存在Copy返回True
     setPrimaryClip(ClipData clip) | 设置剪贴板上的当前Copy
     setText(CharSequence text) | 设置文本到当前Copy
     getText() | 获取剪贴板复制的文本

     ## 玩出一朵小FaFa

     在了解了上面这些内容后，我们就可以做一些比较有意思的东西了，例如，我们可以通过监控用户剪贴板中的内容，来做一些自动的推断，例如，用户复制了一个英文单词，那么我们可以推断，用户可能要进行翻译，再例如，用户复制了一个链接，那么我们也可以推断，用户可能需要打开这个链接，等等。

     Google在文档中，直接给出了示例的代码：

     ```
     // Examines the item on the clipboard. If getText() does not return null, the clip item contains the
     // text. Assumes that this application can only handle one item at a time.
     ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);

     // Gets the clipboard as text.
     pasteData = item.getText();

     // If the string contains data, then the paste operation is done
     if (pasteData != null) {
     return;

     // The clipboard does not contain text. If it contains a URI, attempts to get data from it
     } else {
     Uri pasteUri = item.getUri();

     // If the URI contains something, try to get text from it
     if (pasteUri != null) {

     // calls a routine to resolve the URI and get data from it. This routine is not
     // presented here.
     pasteData = resolveUri(Uri);
     return;
     } else {

     // Something is wrong. The MIME type was plain text, but the clipboard does not contain either
     // text or a Uri. Report an error.
     Log.e("Clipboard contains an invalid data type");
     return;
     }
     }

     ```

     其实非常简单，就是判断三种复制类型，但是我们可以在App中设置一些类似Scheme的标记，用来进行一些功能的区分，就好像淘宝的喵口令——『喵口令XXXXXXX喵口令』，我们可以通过解析这些Scheme，来获取内容，并进行对应的操作。这也是我们前面提到的Clipboard Actions这个App做的事情。

     ## 玩出一朵大FaFa

     我们首先来看ClipData.Item.coerceToText()这样一个方法，这个方法可以将剪贴板里面的内容，直接转化为文字，但是这个转换，是有一定算法的，在API文档中有比较详细的说明，这里简单的看下：


     ![](http://ac-mhke0kuv.clouddn.com/f5ce9a77c1725ead1261.png)

     这个东西能干什么呢，我们知道，有些App会复制之后，打开一个Intent，为了简单，会直接通过ClipData.Item.coerceToText()来返回一个Intent的URI，然后通过解析URI来启动Intent，那么这里就可以被我们来利用了。

     ```
     public void fakeClipboard() {
     // 添加一个假的Intent，模拟用户最新加入的剪贴板内容
     Intent intent = new Intent();
     intent.setComponent(new ComponentName("com.hjwordgames", "com.hjwordgames.Splash"));
     intent.setAction("android.intent.action.VIEW");
     ClipData setClipData;
     setClipData = ClipData.newIntent("intent", intent);
     mClipboardManager.setPrimaryClip(setClipData);

     // 呵呵哒 App以为获取的是自己需要的Intent，结果却被狸猫换太子
     ClipData clipData = mClipboardManager.getPrimaryClip();
     ClipData.Item myItem;
     myItem = clipData.getItemAt(0);
     String clipDataString = myItem.coerceToText(this.getApplicationContext()).toString();
     try {
     Intent myIntent = Intent.parseUri(clipDataString, 0);
     startActivity(myIntent);
     } catch (URISyntaxException e) {
     e.printStackTrace();
     }
     }
     ```

     其实不一定是通过Fake Intent，其它的文字、图片等等，都可以被『偷天换日』。

     另外，要实现这个监听，我们需要注册一个回调——addPrimaryClipChangedListener，Android真是体贴到没朋友：

     ```
     mClipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
    @Override public void onPrimaryClipChanged() {
    Log.d("xys", "onPrimaryClipChanged: ");
    }
    });
     ```
     那么在这里，我们就可以完全实现剪贴板的『狸猫换太子』。那么假如我们是一个『某淘』软件的竞品，那么完全可以让『汪口令』失效，甚至替换为我们自己的应用，同理，还有一些翻译类软件也是一样，不过还好，也许是我的内心比较阴暗，目前还没有看见这样的App。

     欢迎关注我的公众号：

     ![](http://ac-mhke0kuv.clouddn.com/db7eb953589a06fed4c5.jpg)
     * body_html : <h2 id="Clipboard还能玩出花">Clipboard还能玩出花</h2>
     <blockquote>
     <p>本文在 <a href="http://www.diycode.cc/topics/430" target="_blank">DiyCode</a>  和 <a href="http://blog.csdn.net/eclipsexys/article/details/53183802" target="_blank">CSDN个人博客</a> 同时首发，关注作者的 <a href="http://www.diycode.cc/eclipse_xu" target="_blank">DiyCode帐号</a> 或者 <a href="http://weibo.com/1904977584" target="_blank">作者微博</a> 可第一时间收到新文章推送。</p>
     </blockquote>

     <p>Clipboard是Android提供的一个系统服务，它提供了一个全局的剪贴板，让文字、图片、数据，在多App间共享成为可能，今天，我们来了解下它的真面目，以及被玩坏的新姿势。</p>

     <p>老规矩，Google API文档镇楼：<br>
     <a href="https://developer.android.com/guide/topics/text/copy-paste.html" rel="nofollow" target="_blank">https://developer.android.com/guide/topics/text/copy-paste.html</a></p>

     <p>说实话，如果不是为了让Clipboard玩出花，我真不想写这一篇，因为——这文档写的真是太TM详细了。</p>
     <h2 id="Clipboard应用">Clipboard应用</h2>
     <p>我们先来看看一些App对Clipboard的应用，例如手机迅雷，如果你复制了一个链接，那么打开迅雷后，会自动检测并提示下载：</p>

     <p><img src="http://ac-mhke0kuv.clouddn.com/dad4a1794f03e6e3c7f1.png" title="" alt=""></p>

     <p>再例如一些翻译软件，例如有道词典、沪江小D，他们都有一个功能，即复制查词，使用的也是这个原理，我这没装这些App，就不截图了，再例如比较常用的手淘喵口令，实际上也是利用这个功能，当然，也有一些比较专业的Clipboard App，例如Clipboard Actions：</p>

     <p><img src="http://ac-mhke0kuv.clouddn.com/8f3fa19c0c955dda99c5.png" title="" alt=""></p>

     <p>我们可以看见，实际上，他就是帮你解析了各种可能的剪贴板，并对他们提供了各种后续功能的集合，确实非常实用，不过，看完今天的文章，相信你要写一个这样的App，估计也就分分钟。</p>

     <p>OK，这些就是一些Clipboard的基本使用场景，更多场景，没有做不到，只有想不到。</p>
     <h2 id="基本使用">基本使用</h2>
     <p>Clipboard的基本使用，就是三部曲。</p>

     <p>获得ClipboardManager：</p>
     <pre class="highlight plaintext"><code>ClipboardManager mClipboardManager = mClipboardManager =
     (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);</code></pre>

     <p>Copy：</p>
     <pre class="highlight plaintext"><code>ClipData mClipData;
     String text = "hello world";
     mClipData = ClipData.newPlainText("test", text);
     mClipboardManager.setPrimaryClip(mClipData);</code></pre>

     <p>Paste：</p>
     <pre class="highlight plaintext"><code>ClipData clipData = mClipboardManager.getPrimaryClip();
     ClipData.Item item = clipData.getItemAt(0);
     String text = item.getText().toString();</code></pre>

     <p>结束了，简直不能再简单，API文档也写的非常详细，Demo都写了好几个。</p>
     <h2 id="不止于文字">不止于文字</h2>
     <p>我们可以创建以下三种类型的ClipData：</p>
     <table class="table table-bordered table-striped">
     <tr>
     <th>类型</th>
     <th>描述</th>
     </tr>
     <tr>
     <td>Text newPlainText(label, text)</td>
     <td>返回ClipData对象，其中ClipData.Item对象包含一个String</td>
     </tr>
     <tr>
     <td>URI newUri(resolver, label, URI)</td>
     <td>返回ClipData对象，其中ClipData.Item对象包含一个URI</td>
     </tr>
     <tr>
     <td>Intent newIntent(label, intent)</td>
     <td>返回ClipData对象，其中ClipData.Item对象包含一个Intent</td>
     </tr>
     </table>
     <p>对应的，我们也能获取到不同类型的ClipData。</p>
     <h2 id="ClipboardManager管理">ClipboardManager管理</h2>
     <p>ClipboardManager中有很多判断与操作方法：</p>
     <table class="table table-bordered table-striped">
     <tr>
     <th>类型</th>
     <th>描述</th>
     </tr>
     <tr>
     <td>getPrimaryClip()</td>
     <td>返回剪贴板上的当前Copy内容</td>
     </tr>
     <tr>
     <td>getPrimaryClipDescription()</td>
     <td>返回剪贴板上的当前Copy的说明</td>
     </tr>
     <tr>
     <td>hasPrimaryClip()</td>
     <td>如果当前剪贴板上存在Copy返回True</td>
     </tr>
     <tr>
     <td>setPrimaryClip(ClipData clip)</td>
     <td>设置剪贴板上的当前Copy</td>
     </tr>
     <tr>
     <td>setText(CharSequence text)</td>
     <td>设置文本到当前Copy</td>
     </tr>
     <tr>
     <td>getText()</td>
     <td>获取剪贴板复制的文本</td>
     </tr>
     </table><h2 id="玩出一朵小FaFa">玩出一朵小FaFa</h2>
     <p>在了解了上面这些内容后，我们就可以做一些比较有意思的东西了，例如，我们可以通过监控用户剪贴板中的内容，来做一些自动的推断，例如，用户复制了一个英文单词，那么我们可以推断，用户可能要进行翻译，再例如，用户复制了一个链接，那么我们也可以推断，用户可能需要打开这个链接，等等。</p>

     <p>Google在文档中，直接给出了示例的代码：</p>
     <pre class="highlight plaintext"><code>// Examines the item on the clipboard. If getText() does not return null, the clip item contains the
     // text. Assumes that this application can only handle one item at a time.
     ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);

     // Gets the clipboard as text.
     pasteData = item.getText();

     // If the string contains data, then the paste operation is done
     if (pasteData != null) {
     return;

     // The clipboard does not contain text. If it contains a URI, attempts to get data from it
     } else {
     Uri pasteUri = item.getUri();

     // If the URI contains something, try to get text from it
     if (pasteUri != null) {

     // calls a routine to resolve the URI and get data from it. This routine is not
     // presented here.
     pasteData = resolveUri(Uri);
     return;
     } else {

     // Something is wrong. The MIME type was plain text, but the clipboard does not contain either
     // text or a Uri. Report an error.
     Log.e("Clipboard contains an invalid data type");
     return;
     }
     }</code></pre>

     <p>其实非常简单，就是判断三种复制类型，但是我们可以在App中设置一些类似Scheme的标记，用来进行一些功能的区分，就好像淘宝的喵口令——『喵口令XXXXXXX喵口令』，我们可以通过解析这些Scheme，来获取内容，并进行对应的操作。这也是我们前面提到的Clipboard Actions这个App做的事情。</p>
     <h2 id="玩出一朵大FaFa">玩出一朵大FaFa</h2>
     <p>我们首先来看ClipData.Item.coerceToText()这样一个方法，这个方法可以将剪贴板里面的内容，直接转化为文字，但是这个转换，是有一定算法的，在API文档中有比较详细的说明，这里简单的看下：</p>

     <p><img src="http://ac-mhke0kuv.clouddn.com/f5ce9a77c1725ead1261.png" title="" alt=""></p>

     <p>这个东西能干什么呢，我们知道，有些App会复制之后，打开一个Intent，为了简单，会直接通过ClipData.Item.coerceToText()来返回一个Intent的URI，然后通过解析URI来启动Intent，那么这里就可以被我们来利用了。</p>
     <pre class="highlight plaintext"><code>public void fakeClipboard() {
     // 添加一个假的Intent，模拟用户最新加入的剪贴板内容
     Intent intent = new Intent();
     intent.setComponent(new ComponentName("com.hjwordgames", "com.hjwordgames.Splash"));
     intent.setAction("android.intent.action.VIEW");
     ClipData setClipData;
     setClipData = ClipData.newIntent("intent", intent);
     mClipboardManager.setPrimaryClip(setClipData);

     // 呵呵哒 App以为获取的是自己需要的Intent，结果却被狸猫换太子
     ClipData clipData = mClipboardManager.getPrimaryClip();
     ClipData.Item myItem;
     myItem = clipData.getItemAt(0);
     String clipDataString = myItem.coerceToText(this.getApplicationContext()).toString();
     try {
     Intent myIntent = Intent.parseUri(clipDataString, 0);
     startActivity(myIntent);
     } catch (URISyntaxException e) {
     e.printStackTrace();
     }
     }</code></pre>

     <p>其实不一定是通过Fake Intent，其它的文字、图片等等，都可以被『偷天换日』。</p>

     <p>另外，要实现这个监听，我们需要注册一个回调——addPrimaryClipChangedListener，Android真是体贴到没朋友：</p>
     <pre class="highlight plaintext"><code>mClipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
    @Override public void onPrimaryClipChanged() {
    Log.d("xys", "onPrimaryClipChanged: ");
    }
    });</code></pre>

     <p>那么在这里，我们就可以完全实现剪贴板的『狸猫换太子』。那么假如我们是一个『某淘』软件的竞品，那么完全可以让『汪口令』失效，甚至替换为我们自己的应用，同理，还有一些翻译类软件也是一样，不过还好，也许是我的内心比较阴暗，目前还没有看见这样的App。</p>

     <p>欢迎关注我的公众号：</p>

     <p><img src="http://ac-mhke0kuv.clouddn.com/db7eb953589a06fed4c5.jpg" title="" alt=""></p>
     * hits : 456
     * likes_count : 2
     * suggested_at : null
     */

    private int id;
    private String title;
    private String created_at;
    private String updated_at;
    private Object replied_at;
    private int replies_count;
    private String node_name;
    private int node_id;
    private Object last_reply_user_id;
    private Object last_reply_user_login;
    private UserBean user;
    private boolean deleted;
    private boolean excellent;
    private AbilitiesBean abilities;
    private String body;
    private String body_html;
    private int hits;
    private int likes_count;
    private Object suggested_at;

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

    public Object getReplied_at() {
        return replied_at;
    }

    public void setReplied_at(Object replied_at) {
        this.replied_at = replied_at;
    }

    public int getReplies_count() {
        return replies_count;
    }

    public void setReplies_count(int replies_count) {
        this.replies_count = replies_count;
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
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

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public Object getSuggested_at() {
        return suggested_at;
    }

    public void setSuggested_at(Object suggested_at) {
        this.suggested_at = suggested_at;
    }

    public static class UserBean {
        /**
         * id : 1282
         * login : eclipse_xu
         * name : 徐宜生
         * avatar_url : http://diycode.cc/system/letter_avatars/2/E/120_132_205/240.png
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
