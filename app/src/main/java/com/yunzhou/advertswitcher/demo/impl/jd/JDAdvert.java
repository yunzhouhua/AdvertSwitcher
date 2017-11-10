package com.yunzhou.advertswitcher.demo.impl.jd;

/**
 * Created by huayunzhou on 2017/11/9.
 */

public class JDAdvert {
    private long id;
    private String tag;
    private String content;

    public JDAdvert(long id, String tag, String content) {
        this.id = id;
        this.tag = tag;
        this.content = content;
    }

    public JDAdvert(String tag, String content) {
        this.tag = tag;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "JDAdvert{" +
                "tag='" + tag + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
