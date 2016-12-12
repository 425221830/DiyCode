package com.xiseven.diycode.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XISEVEN on 2016/12/6.
 */

public class Project implements Parcelable {

    /**
     * id : 453
     * name : scikit-learn
     * description : 一种基于 NumPy、SciPy 和 matplotlib 的用于数据挖掘和数据分析的工具，其不仅使用起来简单高效，而且还是开源的，可供所有人使用，并且拥有商业可用的 BSD 许可证，在不同的环境下都能很好的被使用。
     * readme : [![](https://camo.githubusercontent.com/590475799489c962f111c9fc5c1432ecbc577578/68747470733a2f2f6170692e7472617669732d63692e6f72672f7363696b69742d6c6561726e2f7363696b69742d6c6561726e2e7376673f6272616e63683d6d6173746572)](https://travis-ci.org/scikit-learn/scikit-learn) [![](https://camo.githubusercontent.com/487f70a8558fd351b32bf769bfb2fb9060850cc6/68747470733a2f2f63692e6170707665796f722e636f6d2f6170692f70726f6a656374732f7374617475732f6769746875622f7363696b69742d6c6561726e2f7363696b69742d6c6561726e3f6272616e63683d6d6173746572267376673d74727565)](https://ci.appveyor.com/project/sklearn-ci/scikit-learn/history) [![](https://camo.githubusercontent.com/9fbfd5d48f705d586edcedff6c7d19c48504e3eb/68747470733a2f2f636f766572616c6c732e696f2f7265706f732f7363696b69742d6c6561726e2f7363696b69742d6c6561726e2f62616467652e7376673f6272616e63683d6d617374657226736572766963653d676974687562)](https://coveralls.io/r/scikit-learn/scikit-learn) [![](https://camo.githubusercontent.com/d2913194913f85128f908483a265e64dcd6d31e4/68747470733a2f2f636972636c6563692e636f6d2f67682f7363696b69742d6c6561726e2f7363696b69742d6c6561726e2f747265652f6d61737465722e7376673f7374796c653d736869656c6426636972636c652d746f6b656e3d3a636972636c652d746f6b656e)](https://circleci.com/gh/scikit-learn/scikit-learn) [![](https://camo.githubusercontent.com/352488c0cbba0e8f6da11ae0761444dd0c93489c/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f707974686f6e2d322e372d626c75652e737667) ](https://badge.fury.io/py/scikit-learn)[![](https://camo.githubusercontent.com/53aa0b9151bc545b404852175644228ee0efffe2/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f707974686f6e2d332e352d626c75652e737667) ](https://badge.fury.io/py/scikit-learn)[![](https://camo.githubusercontent.com/9f0ed32d05350afa18a801573e4da7f4a240e181/68747470733a2f2f62616467652e667572792e696f2f70792f7363696b69742d6c6561726e2e737667) ](https://badge.fury.io/py/scikit-learn)[![](https://camo.githubusercontent.com/73c63e44b8bee62df142664048c58f83ec8ad95c/68747470733a2f2f7a656e6f646f2e6f72672f62616467652f32313336392f7363696b69742d6c6561726e2f7363696b69742d6c6561726e2e737667)](https://zenodo.org/badge/latestdoi/21369/scikit-learn/scikit-learn)


     ## scikit-learn

     scikit-learn is a Python module for machine learning built on top of SciPy and distributed under the 3-Clause BSD license.

     The project was started in 2007 by David Cournapeau as a Google Summer of Code project, and since then many volunteers have contributed. See the AUTHORS.rst file for a complete list of contributors.

     It is currently maintained by a team of volunteers.

     Website: http://scikit-learn.org

     ## Installation

     Dependencies

     scikit-learn requires:

     - Python (>= 2.7 or >= 3.3)
     - NumPy (>= 1.6.1)
     - SciPy (>= 0.9)

     scikit-learn also uses CBLAS, the C interface to the Basic Linear Algebra Subprograms library. scikit-learn comes with a reference implementation, but the system CBLAS will be detected by the build system and used if present. CBLAS exists in many implementations; see [Linear algebra libraries](http://scikit-learn.org/stable/modules/computational_performance.html#linear-algebra-libraries) for known issues.

     ## User installation

     If you already have a working installation of numpy and scipy, the easiest way to install scikit-learn is using pip

     ```
     pip install -U scikit-learn
     ```
     or conda:

     ```
     conda install scikit-learn
     ```
     The documentation includes more detailed[ installation instructions](http://scikit-learn.org/stable/install.html).

     ## Development

     We welcome new contributors of all experience levels. The scikit-learn community goals are to be helpful, welcoming, and effective. The [Development Guide ](http://scikit-learn.org/stable/developers/index.html)has detailed information about contributing code, documentation, tests, and more. We've included some basic information in this README.

     ## Important links

     - Official source code repo: https://github.com/scikit-learn/scikit-learn
     - Download releases: https://pypi.python.org/pypi/scikit-learn
     - Issue tracker: https://github.com/scikit-learn/scikit-learn/issues

     ## Source code

     You can check the latest sources with the command:
     ```
     git clone https://github.com/scikit-learn/scikit-learn.git
     ```

     ## Setting up a development environment

     Quick tutorial on how to go about setting up your environment to contribute to scikit-learn: https://github.com/scikit-learn/scikit-learn/blob/master/CONTRIBUTING.md

     ## Testing

     After installation, you can launch the test suite from outside the source directory (you will need to have the nose package installed):
     ```
     nosetests -v sklearn
     ```
     Under Windows, it is recommended to use the following command (adjust the path to the python.exe program) as using the nosetests.exe program can badly interact with tests that use multiprocessing:
     ```
     C:\Python34\python.exe -c "import nose; nose.main()" -v sklearn
     ```
     See the web page http://scikit-learn.org/stable/developers/advanced_installation.html#testing for more information.

     >Random number generation can be controlled during testing by setting the SKLEARN_SEED environment variable.

     ## Submitting a Pull Request

     Before opening a Pull Request, have a look at the full Contributing page to make sure your code complies with our guidelines: http://scikit-learn.org/stable/developers/index.html


     ## Project History

     The project was started in 2007 by David Cournapeau as a Google Summer of Code project, and since then many volunteers have contributed. See the AUTHORS.rst file for a complete list of contributors.

     The project is currently maintained by a team of volunteers.

     **Note:** scikit-learn was previously referred to as scikits.learn.

     ## Help and Support

     **Documentation**

     - HTML documentation (stable release): http://scikit-learn.org
     - HTML documentation (development version): http://scikit-learn.org/dev/
     - FAQ: http://scikit-learn.org/stable/faq.html

     **Communication**

     - Mailing list: https://mail.python.org/mailman/listinfo/scikit-learn
     - IRC channel: #scikit-learn at irc.freenode.net
     - Stack Overflow: http://stackoverflow.com/questions/tagged/scikit-learn
     - Website: http://scikit-learn.org

     **Citation**

     If you use scikit-learn in a scientific publication, we would appreciate citations: http://scikit-learn.org/stable/about.html#citing-scikit-learn

     * github : https://github.com/scikit-learn/scikit-learn
     * website : http://scikit-learn.org
     * download : https://api.github.com/repos/scikit-learn/scikit-learn/zipball
     * star : 13298
     * fork : 7662
     * watch : 1366
     * project_cover_url : http://diycode.b0.upaiyun.com/developer_organization/avatar/1003.jpg
     * label_str : machine,NumPy,SciPy,matplotlib,learning
     * category : {"name":"机器学习 Machine Learning","id":7}
     * sub_category : {"name":"Python","id":68}
     * last_updated_at : 2016-09-11T20:21:16.000+08:00
     */

    private int id;
    private String name;
    private String description;
    private String readme;
    private String github;
    private String website;
    private String download;
    private int star;
    private int fork;
    private int watch;
    private String project_cover_url;
    private String label_str;
    private CategoryBean category;
    private SubCategoryBean sub_category;
    private String last_updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getFork() {
        return fork;
    }

    public void setFork(int fork) {
        this.fork = fork;
    }

    public int getWatch() {
        return watch;
    }

    public void setWatch(int watch) {
        this.watch = watch;
    }

    public String getProject_cover_url() {
        return project_cover_url;
    }

    public void setProject_cover_url(String project_cover_url) {
        this.project_cover_url = project_cover_url;
    }

    public String getLabel_str() {
        return label_str;
    }

    public void setLabel_str(String label_str) {
        this.label_str = label_str;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public SubCategoryBean getSub_category() {
        return sub_category;
    }

    public void setSub_category(SubCategoryBean sub_category) {
        this.sub_category = sub_category;
    }

    public String getLast_updated_at() {
        return last_updated_at;
    }

    public void setLast_updated_at(String last_updated_at) {
        this.last_updated_at = last_updated_at;
    }

    public static class CategoryBean implements Parcelable {
        /**
         * name : 机器学习 Machine Learning
         * id : 7
         */

        private String name;
        private int id;

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeInt(this.id);
        }

        public CategoryBean() {
        }

        protected CategoryBean(Parcel in) {
            this.name = in.readString();
            this.id = in.readInt();
        }

        public static final Creator<CategoryBean> CREATOR = new Creator<CategoryBean>() {
            @Override
            public CategoryBean createFromParcel(Parcel source) {
                return new CategoryBean(source);
            }

            @Override
            public CategoryBean[] newArray(int size) {
                return new CategoryBean[size];
            }
        };
    }

    public static class SubCategoryBean implements Parcelable {
        /**
         * name : Python
         * id : 68
         */

        private String name;
        private int id;

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeInt(this.id);
        }

        public SubCategoryBean() {
        }

        protected SubCategoryBean(Parcel in) {
            this.name = in.readString();
            this.id = in.readInt();
        }

        public static final Creator<SubCategoryBean> CREATOR = new Creator<SubCategoryBean>() {
            @Override
            public SubCategoryBean createFromParcel(Parcel source) {
                return new SubCategoryBean(source);
            }

            @Override
            public SubCategoryBean[] newArray(int size) {
                return new SubCategoryBean[size];
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
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.readme);
        dest.writeString(this.github);
        dest.writeString(this.website);
        dest.writeString(this.download);
        dest.writeInt(this.star);
        dest.writeInt(this.fork);
        dest.writeInt(this.watch);
        dest.writeString(this.project_cover_url);
        dest.writeString(this.label_str);
        dest.writeParcelable(this.category, flags);
        dest.writeParcelable(this.sub_category, flags);
        dest.writeString(this.last_updated_at);
    }

    public Project() {
    }

    protected Project(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.readme = in.readString();
        this.github = in.readString();
        this.website = in.readString();
        this.download = in.readString();
        this.star = in.readInt();
        this.fork = in.readInt();
        this.watch = in.readInt();
        this.project_cover_url = in.readString();
        this.label_str = in.readString();
        this.category = in.readParcelable(CategoryBean.class.getClassLoader());
        this.sub_category = in.readParcelable(SubCategoryBean.class.getClassLoader());
        this.last_updated_at = in.readString();
    }

    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel source) {
            return new Project(source);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
}
