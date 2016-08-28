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
@Table("six_site_users")
public class SixSiteUsers {

    @Id
    private int id;
    @Column("user_login")
    private String userLogin;
    @Column("user_pass")
    private String userPass;
    @Column("user_nicename")
    private String userNicename;
    @Column("user_email")
    private String userEmail;
    @Column("user_url")
    private String userUrl;
    @Column("user_registered")
    private Date userRegistered;
    @Column("user_activation_key")
    private String userActivationKey;
    @Column("user_status")
    private int userStatus;
    @Column("display_name")
    private String displayName;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserLogin () {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPass () {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserNicename () {
        return userNicename;
    }

    public void setUserNicename(String userNicename) {
        this.userNicename = userNicename;
    }

    public String getUserEmail () {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserUrl () {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public Date getUserRegistered () {
        return userRegistered;
    }

    public void setUserRegistered(Date userRegistered) {
        this.userRegistered = userRegistered;
    }

    public String getUserActivationKey () {
        return userActivationKey;
    }

    public void setUserActivationKey(String userActivationKey) {
        this.userActivationKey = userActivationKey;
    }

    public int getUserStatus () {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getDisplayName () {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}