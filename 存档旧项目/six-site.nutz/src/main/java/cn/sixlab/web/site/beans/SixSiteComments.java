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
@Table("six_site_comments")
public class SixSiteComments {

    @Id
    private int commentId;
    @Column("comment_post_ID")
    private int commentPostId;
    @Column("comment_author")
    private String commentAuthor;
    @Column("comment_author_email")
    private String commentAuthorEmail;
    @Column("comment_author_url")
    private String commentAuthorUrl;
    @Column("comment_author_IP")
    private String commentAuthorIp;
    @Column("comment_date")
    private Date commentDate;
    @Column("comment_date_gmt")
    private Date commentDateGmt;
    @Column("comment_content")
    private String commentContent;
    @Column("comment_type")
    private String commentType;
    @Column("comment_parent")
    private int commentParent;
    @Column("user_id")
    private int userId;

    public int getCommentId () {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getCommentPostId () {
        return commentPostId;
    }

    public void setCommentPostId(int commentPostId) {
        this.commentPostId = commentPostId;
    }

    public String getCommentAuthor () {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getCommentAuthorEmail () {
        return commentAuthorEmail;
    }

    public void setCommentAuthorEmail(String commentAuthorEmail) {
        this.commentAuthorEmail = commentAuthorEmail;
    }

    public String getCommentAuthorUrl () {
        return commentAuthorUrl;
    }

    public void setCommentAuthorUrl(String commentAuthorUrl) {
        this.commentAuthorUrl = commentAuthorUrl;
    }

    public String getCommentAuthorIp () {
        return commentAuthorIp;
    }

    public void setCommentAuthorIp(String commentAuthorIp) {
        this.commentAuthorIp = commentAuthorIp;
    }

    public Date getCommentDate () {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Date getCommentDateGmt () {
        return commentDateGmt;
    }

    public void setCommentDateGmt(Date commentDateGmt) {
        this.commentDateGmt = commentDateGmt;
    }

    public String getCommentContent () {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentType () {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public int getCommentParent () {
        return commentParent;
    }

    public void setCommentParent(int commentParent) {
        this.commentParent = commentParent;
    }

    public int getUserId () {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}