

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>LOG IN</title>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/theme', file: 'style.css')}" media="screen" title="style (screen)" />
</head>

<body>
<div id="head">
    <div class="wrap">
        <h1>out of the box</h1>
        <h2>inspired by <a href="http://zenhabits.net">LOS SISTEMATICOS</a></h2>
        <div id="nav">
            <ul>
                <li><a href="#">home</a></li>
                <li><a href="#">about</a></li>
                <li><a href="#">blog</a></li>
                <li><a href="#">contact</a></li>
                <li><a href="#">about</a></li>
                <li><a href="#">blog</a></li>
                <li><a href="#">contact</a></li>
                <li><a href="http://localhost:8080/UPBBolsa/login">Log-in</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="wrap">
    <div id="right">
        <h1>About out of the box</h1>
        <p>Lorem ipsum dolor sit amet lorem. Ipsum dolor sit amet consectur.</p>
        <h1>Recent Posts</h1>
        <ul>
            <li><a href="http://demusdesign.com">A New Template</a></li>
            <li><a href="http://demusdesign.com/">From DemusDesign</a></li>
            <li><a href="http://validator.w3.org/check?uri=referer">XHTML 1.0 Strict</a></li>
            <li><a href="http://jigsaw.w3.org/css-validator/check/referer">Valid CSS</a></li>
            <li><a href="http://opendesigns.org">OpenDesigns</a></li>
        </ul>
    </div>
    <div id="content">
        <h1>Easy Log In</h1>

        <div id='login'>
            <div class='inner'>
                <div class='fheader'><g:message code="springSecurity.login.header"/></div>

                <g:if test='${flash.message}'>
                    <div class='login_message'>${flash.message}</div>
                </g:if>

                <form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
                    <p>
                        <label for='username'><g:message code="springSecurity.login.username.label"/>:</label>
                        <input type='text' class='text_' name='j_username' id='username'/>
                    </p>

                    <p>
                        <label for='password'><g:message code="springSecurity.login.password.label"/>:</label>
                        <input type='password' class='text_' name='j_password' id='password'/>
                    </p>

                    <p id="remember_me_holder">
                        <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                        <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
                    </p>

                    <p>
                        <input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>
                    </p>
                </form>
            </div>
        </div>
        <script type='text/javascript'>
            <!--
            (function() {
                document.forms['loginForm'].elements['j_username'].focus();
            })();
            // -->
        </script>

</div>
</body>
</html>