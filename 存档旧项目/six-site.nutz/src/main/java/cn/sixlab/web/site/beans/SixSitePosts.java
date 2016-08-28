/*
 * Copyright (c) 1995 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
package cn.sixlab.web.site.beans;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
@Table("six_site_posts")
public class SixSitePosts {

    @Id
    private int id;
    @Column("post_author")
    private int postAuthor;
    @Column("post_date")
    private Date postDate;
    @Column("post_date_gmt")
    private Date postDateGmt;
    @Column("post_content")
    private String postContent;
    @Column("post_title")
    private String postTitle;
    @Column("post_status")
    private String postStatus;
    @Column("comment_status")
    private String commentStatus;
    @Column("post_password")
    private String postPassword;
    @Column("post_modified")
    private Date postModified;
    @Column("post_modified_gmt")
    private Date postModifiedGmt;
    @Column("post_parent")
    private int postParent;
    @Column("page_uri")
    private String pageUri;
    @Column("post_type")
    private String postType;
    @Column("post_mime_type")
    private String postMimeType;
    @Column("comment_count")
    private int commentCount;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostAuthor () {
        return postAuthor;
    }

    public void setPostAuthor(int postAuthor) {
        this.postAuthor = postAuthor;
    }

    public Date getPostDate () {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getPostDateGmt () {
        return postDateGmt;
    }

    public void setPostDateGmt(Date postDateGmt) {
        this.postDateGmt = postDateGmt;
    }

    public String getPostContent () {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostTitle () {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostStatus () {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public String getCommentStatus () {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getPostPassword () {
        return postPassword;
    }

    public void setPostPassword(String postPassword) {
        this.postPassword = postPassword;
    }

    public Date getPostModified () {
        return postModified;
    }

    public void setPostModified(Date postModified) {
        this.postModified = postModified;
    }

    public Date getPostModifiedGmt () {
        return postModifiedGmt;
    }

    public void setPostModifiedGmt(Date postModifiedGmt) {
        this.postModifiedGmt = postModifiedGmt;
    }

    public int getPostParent () {
        return postParent;
    }

    public void setPostParent(int postParent) {
        this.postParent = postParent;
    }

    public String getPageUri () {
        return pageUri;
    }

    public void setPageUri(String pageUri) {
        this.pageUri = pageUri;
    }

    public String getPostType () {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostMimeType () {
        return postMimeType;
    }

    public void setPostMimeType(String postMimeType) {
        this.postMimeType = postMimeType;
    }

    public int getCommentCount () {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

}