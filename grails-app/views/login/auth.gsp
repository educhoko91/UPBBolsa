<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>LOG IN</title>
    <meta name="layout" content="upbolsa">
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'transacciones.css')}" media="screen" title="style (screen)" />

</head>

<body style="align : left">
<g:if test='${flash.message}'>
    <div class='flash-msg'>${flash.message}</div>
</g:if>
        <table>
            <tr>
                <td width="200" valign="top">
                    <div id='login'>
                        <div class='inner'>
                            <form action='${postUrl}' method='POST' id='loginForm' class='cssform'>
                                <h2>Inicio de Sesion</h2>
                                <p>
                                    <label title="Debe introducir su nombre de usuario" for='username'><g:message code="springSecurity.login.username.label"/>:</label>
                                    <input  required="" type='text' class='text' name='j_username' id='username'/>
                                    <label title="Debe introducir su password, si no lo recuerda comuniquesé con el administrador" for='password'><g:message code="springSecurity.login.password.label"/>:</label>
                                    <input required="" type='password' class='text' name='j_password' id='password'/>
                                    </br>
                                     <!--
                                    <comment id="remember_me_holder">
                                        <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                                        </br>
                                        <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
                                    </comment>  -->
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
                                <label for="username" class="text">Usuario</label>
                                <g:textField required="" name="username" value="${user?.username}" class="text"/>
                                <label  for="email" class="text">Email</label>
                                <g:textField  required="" name="email" value="${user?.email}" class="text"/>
                                <label for="fullname">Nombre y apellido</label>
                                <g:textField required="" name="fullname" value="${user?.fullname}" class="text"/>
                                <label for="password">Password</label>
                                <g:passwordField required="" name="password" value="${user?.password}" class="text"/>
                                <label for="verify_password">Verif. Password</label>
                                <g:passwordField required="" name="verify_password" value="${user?.verify_password}" class="text"/>
                                </br></br>
                                <g:submitButton class="button" name="submitButton" value="Resgistarse"/>
                            </p>
                        </g:form>
                    </div>
                </td>
            </tr>
        </table>
</body>
</html>