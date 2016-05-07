
var TRComponent = React.createClass({
    getInitialState: function () {
        debugger;
        if(isLogin()){
            var username = getCookie(login_user_name);
            return {
                lText: username,
                rText: "退出"
            };
        }else{
            return {
                lText: "注册",
                rText: "登录"
            };
        }
    },
    handleLClick: function () {
        if (this.props.logined) {

        }else{
            React.render(
                <RegComponent />, $("#body")[0]
            );
        }
    },
    handleRClick: function () {
        if (this.props.logined) {
            React.render(
                <TRComponent />, $("#regLog")[0]
            );
        } else {
            React.render(
                <LoginComponent />, $("#body")[0]
            );
        }
    },
    render : function(){
        return (
            <span><a onClick={this.handleLClick}>{this.state.lText}</a> | <a onClick={this.handleRClick}>{this.state.rText}</a></span>
        );
    }
});

var RegComponent = React.createClass({
    handleConfirm: function () {
        var check = this.state.validate.form();
        if (!check) {
            return false;
        }
        $("#regFrm").ajaxSubmit({
            dataType: "json",
            success: function (data) {
                if ("1" == data.success) {
                    location.reload();
                } else {
                    jAlert(data.message, "提示", function () {
                        if (r) {
                            return;
                        }
                    });
                    return;
                }
            }
        });
    },
    componentDidMount: function () {
        var validate = $("#regFrm").validate({
            rules: {
                "username": {
                    remote: {
                        url: contextPath + "/user/checkUsername.lab",
                        data: {
                            'username': function () {
                                return $("#reg-username").val();
                            }
                        },
                        type: "post"
                    }
                }
            }
        });

        this.setState({
            validate: validate
        });
    },
    render: function () {
        var action = contextPath + "/user/submitReg.lab";
        return (
            <form id="regFrm" action={action}>
                <div>
                    <h2>欢迎注册</h2>
                </div>
                <div className="form-group">
                    <label htmlFor="reg-username">用户名</label>
                    <input name="username" id="reg-username" type="text"
                           className="form-control required"
                           ref="username" placeholder="中英文数字都可以，当昵称用" />
                </div>
                <div className="form-group">
                    <label htmlFor="reg-password">密码</label>
                    <input name="password" id="reg-password" type="password"
                           className="form-control required"
                           ref="password" placeholder="密码" />
                </div>
                <div className="form-group">
                    <label htmlFor="reg-rePwd">确认密码</label>
                    <input name="rePwd" id="reg-rePwd" type="password"
                           className="form-control required"
                           ref="rePwd" placeholder="确认密码" />
                </div>
                <button onClick={this.handleConfirm} className="btn btn-lg btn-primary btn-block"
                        type="button" id="submitBtn">提交
                </button>
            </form>
        );
    }
});

React.render(
    <TRComponent />, $("#regLog")[0]
);

var LoginComponent = React.createClass({
    handleConfirm: function () {
        debugger;
        $("#loginFrm").ajaxSubmit({
            dataType: "json",
            success: function (data) {
                if ("1" == data.success) {
                    location.href.load();
                } else {
                    jAlert(data.message, "提示", function () {
                        if (r) {
                            return;
                        }
                    });
                    return;
                }
            }
        });
    },
    render: function () {
        var action = contextPath + "/submitLogin.lab";
        return (
            <form id="loginFrm" action={action}>
                <div>
                    <h2>欢迎登录</h2>
                </div>
                <div className="form-group">
                    <label htmlFor="login-username">用户名</label>
                    <input name="username" id="login-username" type="text"
                           className="form-control required"
                           ref="username" placeholder="用户名" />
                </div>
                <div className="form-group">
                    <label htmlFor="login-password">密码</label>
                    <input name="password" id="login-password" type="password"
                           className="form-control required"
                           ref="password" placeholder="密码" />
                </div>
                <button onClick={this.handleConfirm} className="btn btn-lg btn-primary btn-block"
                        type="button" id="submitBtn">提交
                </button>
            </form>
        );
    }
});