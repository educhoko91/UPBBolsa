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
        <h1>a new template</h1>
        <h2>from DemusDesign</h2>
        <p><img class="right" src="${request.contextPath}/images/theme/blur.jpg" alt="" />Out of the box is the 11th template from <a href="http://demusdesign.com">DemusDesign</a>. I make no bones about the fact that I was inspired after visiting the <a href="http://zenhabits.net">Zen Habits</a> blog. All of us designers are constantly inspired. I think I've given this design its own look and feel, and Zen Habits was just a launching point. The background white/blue image will eventually give way to a white border on the right if you're on a 5-screen setup with a resolution over 4000px wide.</p>
        <img src="${request.contextPath}/images/theme/br.gif" alt="" />
        <p class="info">posted by <a href="http://demusdesign.com">demus</a>    |    on June 14, 2008 16:33:46 EDT    |    <a href="#">6</a> comments</p>
        <img src="${request.contextPath}/images/theme/br.gif" alt="" />
        <h1>and now time...</h1>
        <h2>For Some Style Demos</h2>
        <h3>Right-floated image</h3>
        <p><img class="right" src="${request.contextPath}/images/theme/small.jpg" alt="" />Vestibulum lobortis pharetra <a href="#">libero</a>. Suspendisse odio diam, eleifend sed, rutrum quis, commodo nec, ante. Ut non lectus. Nulla metus. Nullam convallis volutpat tortor. Fusce risus. Aliquam odio dui, bibendum id, sagittis non, posuere non, purus. Etiam ligula ante, pulvinar eget, nonummy vitae, pretium facilisis, turpis. Pellentesque commodo purus id sem. Praesent vitae metus. Duis ut diam. Praesent ut nisi aliquet odio iaculis porttitor. Etiam ligula ante, pulvinar eget, nonummy vitae, pretium facilisis, turpis. Pellentesque commodo purus id sem. Praesent vitae metus. Duis ut diam. Praesent ut nisi aliquet odio iaculis porttitor.</p>
        <h3>Left-floated image</h3>
        <p><img class="left" src="${request.contextPath}/images/theme/small.jpg" alt="" />Vestibulum lobortis pharetra <a href="#">libero</a>. Suspendisse odio diam, eleifend sed, rutrum quis, commodo nec, ante. Ut non lectus. Nulla metus. Nullam convallis volutpat tortor. Fusce risus. Aliquam odio dui, bibendum id, sagittis non, posuere non, purus. Etiam ligula ante, pulvinar eget, nonummy vitae, pretium facilisis, turpis. Pellentesque commodo purus id sem. Praesent vitae metus. Duis ut diam. Praesent ut nisi aliquet odio iaculis porttitor. Etiam ligula ante, pulvinar eget, nonummy vitae, pretium facilisis, turpis. Pellentesque commodo purus id sem. Praesent vitae metus. Duis ut diam. Praesent ut nisi aliquet odio iaculis porttitor.</p>
        <h3>Blockquote</h3>
        <div class="blockquote">Vestibulum lobortis pharetra <a href="#">libero</a>. Suspendisse odio diam, eleifend sed, rutrum quis, commodo nec, ante. Ut non lectus. Nulla metus. Nullam convallis volutpat tortor. Fusce risus.</div>
        <ol>
            <li><span>An Ordered List</span></li>
            <li><span>interdum semper,</span></li>
            <li><span>cursus at,</span></li>
        </ol>
        <ul>
            <li>An Unordered List</li>
            <li>interdum semper,</li>
            <li>cursus at,</li>
        </ul>
        <h3>Time for a Form</h3>
        <form action="#" method="post">
            <p>
                <label>Name:</label>
                <input type="text" class="text" name="name" />
                <label>Email:</label>
                <input type="text" class="text" name="email" />
                <label>Your Message:</label>
                <textarea rows="8" cols="30" name="message"></textarea>
                <input type="submit" class="button" value="Submit" />
                <input type="reset" class="button" value="Clear" />
            </p>
        </form>
    </div>
</div>
<div id="footer"><div class="wrap">
    <div id="footnav">
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
    <div class="linksright">
        <h4><a href="#">Recent Updates</a></h4>
        <ul>
            <li><a href="http://demusdesign.com">A New Template</a></li>
            <li><a href="http://demusdesign.com/">From DemusDesign</a></li>
            <li><a href="http://validator.w3.org/check?uri=referer">XHTML 1.0 Strict</a></li>
            <li><a href="http://jigsaw.w3.org/css-validator/check/referer">Valid CSS</a></li>
            <li><a href="http://opendesigns.org">OpenDesigns</a></li>
        </ul>
    </div>
    <div class="linksright">
        <h4>Recent Comments</h4>
        <ul>
            <li><a href="http://demusdesign.com">A New Template</a></li>
            <li><a href="http://demusdesign.com/">From DemusDesign</a></li>
            <li><a href="http://validator.w3.org/check?uri=referer">XHTML 1.0 Strict</a></li>
            <li><a href="http://jigsaw.w3.org/css-validator/check/referer">Valid CSS</a></li>
            <li><a href="http://opendesigns.org">OpenDesigns</a></li>
        </ul>
    </div>
    <div class="linksright">
        <h4>Recent Posts</h4>
        <ul>
            <li><a href="http://demusdesign.com">A New Template</a></li>
            <li><a href="http://demusdesign.com/">From DemusDesign</a></li>
            <li><a href="http://validator.w3.org/check?uri=referer">XHTML 1.0 Strict</a></li>
            <li><a href="http://jigsaw.w3.org/css-validator/check/referer">Valid CSS</a></li>
            <li><a href="http://opendesigns.org">OpenDesigns</a></li>
        </ul>
    </div>
    <div class="linksright">
        <h4>Recent Posts</h4>
        <ul class="null">
            <li><a href="http://demusdesign.com">A New Template</a></li>
            <li><a href="http://demusdesign.com/">From DemusDesign</a></li>
            <li><a href="http://validator.w3.org/check?uri=referer">XHTML 1.0 Strict</a></li>
            <li><a href="http://jigsaw.w3.org/css-validator/check/referer">Valid CSS</a></li>
            <li><a href="http://opendesigns.org">OpenDesigns</a></li>
        </ul>
    </div>
    <div class="clearer">&nbsp;</div><br /><br />
    <p class="foot">
        Template by <a class="credit" href="http://www.demusdesign.com">DemusDesign</a><br />
    &copy; Your Site 2007
    </p>
</div></div> </body>
</html>