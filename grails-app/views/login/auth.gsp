<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>LOG IN</title>
    <meta name="layout" content="upbolsa">
</head>

<body>
<div class="wrap">
    <div id="content">
        <table width="650" valign="top">
            <tr>
                <td width="200" valign="top">
                    <div id='login'>
                        <div class='inner'>
                            <div class='fheader'><g:message code="springSecurity.login.header"/></div>

                            <g:if test='${flash.message}'>
                                <div class='login_message'>${flash.message}</div>
                            </g:if>

                            <form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
                                <p>
                                    <label for='username'><g:message
                                            code="springSecurity.login.username.label"/>:</label>
                                    <input type='text' class='text_' name='j_username' id='username'/>
                                </p>

                                <p>
                                    <label for='password'><g:message
                                            code="springSecurity.login.password.label"/>:</label>
                                    <input type='password' class='text_' name='j_password' id='password'/>
                                </p>

                                <comment id="remember_me_holder">
                                    <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me'
                                           <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                                    <label for='remember_me'><g:message
                                            code="springSecurity.login.remember.me.label"/></label>
                                </comment>
                                <button><input type='submit' id="submit"
                                               value='${message(code: "springSecurity.login.button")}'/></button>
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
                        <g:form class="cssform" url="[controller: 'login', action: 'save']">
                            <h4>Registrarse</h4>

                            <p>
                                <label for="username">Nombre y Apellido</label>
                                <g:textField name="username" value="${user?.username}"/>
                            </p>

                            <p>
                                <label for="email">Correo Electronico</label>
                                <g:textField name="email" value="${user?.email}"/>
                            </p>

                            <p>
                                <label for="password">Password</label>
                                <g:passwordField name="password" value="${user?.password}"/>
                            </p>

                            <p>
                                <label for="password_confirm">Verif. Password</label>
                                <g:passwordField name="password_confirm" value=""/>
                            </p>

                            <p class="button">
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