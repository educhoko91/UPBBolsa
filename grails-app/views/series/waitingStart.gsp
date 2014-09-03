<%--
  Created by IntelliJ IDEA.
  User: eliendojr
  Date: 8/3/14
  Time: 12:18 AM
--%>

<%@ page import="edu.upb.upbBolsa.DatoSerie" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="upbolsa"/>
    <script type="text/javascript" src="${resource(dir:'countdown', file: 'countdown.js')}"></script>
    <title></title>
</head>





<body>

<h1>Series</h1>
<br />
<br />
<h3>La Simulacion Iniciara en:</h3>
<br />
<script>
    CountdownImageFolder = "../../countdown/images/";
    var myCountdown1 = new Countdown({style: "flip",time:${time/1000}});
    setTimeout(function() {
        window.location.href = "${createLink(action: "index",absolute: true)}";

    }, ${time});
</script>
</body>

</html>