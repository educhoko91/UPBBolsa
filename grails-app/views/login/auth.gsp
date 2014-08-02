<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>LOG IN</title>
    <meta name="layout" content="upbolsa">

</head>

<body>
<t
<g:if test='${flash.message}'>
    <div class='login_message'>${flash.message}</div>
</g:if>
<div class="wrap">
    <div id="content">
        <table width="650" valign="top" class="cssform">
            <tr>
                <td width="200" valign="top">
                    <div id='login'>
                        <div class='inner'>
                            <form action='${postUrl}' method='POST' id='loginForm' class='cssform'>
                                <h2>Login</h2>
                                <p>
                                    <label for='username'><g:message code="springSecurity.login.username.label"/>:</label>
                                    <input type='text' class='text' name='j_username' id='username'/>
                                    <label for='password'><g:message code="springSecurity.login.password.label"/>:</label>
                                    <input type='password' class='text' name='j_password' id='password'/>
                                    </br>

                                    <comment id="remember_me_holder">
                                        <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                                        </br>
                                        <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
                                    </comment>
                                </br>
                                <button><input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/></button>
                                </p>
                            </form>
                        </div>
                    </div>

                    <script type='text/javascript'>
                        <!--
                        (function () {
                            document.forms['loginForm'].elements['j_username'].focus();
                        })();
                        // -->
                    </script>
                </td>
                <td width="50"></td>
                <td width="400" valign="top">
                    <div id="save">
                        <g:form class="cssform" url="[controller: 'login', action: 'save']" >
                            <h2>Registrarse</h2>
                            <p>
                                <label for="username" class="text">Email</label>
                                <g:textField name="username" value="${user?.username}" class="text"/>
                                <label for="fullname">Nombre y apellido</label>
                                <g:textField name="fullname" value="${user?.fullname}" class="text"/>
                                <label for="password">Password</label>
                                <g:passwordField name="password" value="${user?.password}" class="text"/>
                                <label for="verify_password">Verif. Password</label>
                                <g:passwordField name="verify_password" value="${user?.verify_password}" class="text"/>
                                </br></br>
                                <g:submitButton class="button" name="submitButton" value="Resgistarse"/>
                            </p>
                        </g:form>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>