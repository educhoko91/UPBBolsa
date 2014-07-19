<%--
  Created by IntelliJ IDEA.
  User: Omar
  Date: 13/07/2014
  Time: 11:30 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>UPBBolsa</title>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/theme', file: 'style.css')}" media="screen" title="style (screen)" />
    <g:layoutHead/>
    <r:layoutResources />
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
                <li><a href="http://localhost:8080/UPBBolsa/logout">Log-out</a></li>
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
        <g:layoutBody/>
    </div>
</div>
<div id="footer"><div class="wrap">

    <div class="clearer">&nbsp;</div><br /><br />
    <p class="foot">
        Template by MOROCHO

    </p>
</div></div> </body>
<r:layoutResources />
</html>