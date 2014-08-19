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
    <title></title>
</head>





<body>
<h1>Series</h1>
<table>
    <%=compTable%>
</table>
</body>
<g:javascript>
    Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
    <g:each in="${companies}" var="c">
        $("#container${c.serie.id}").highcharts('StockChart',{
    /*chart : {
        events : {
            load : function() {

                // set up the updating of the chart each second
                var series = this.series[0];
                setInterval(function() {
                    var x = (new Date()).getTime(), // current time
                    y = Math.round(Math.random() * 100);
                    series.addPoint([x, y], true, true);
                }, 1000);
            }
        }
    },*/

    rangeSelector: {
        enabled: false
    },

    exporting: {
        enabled: false
    },
    series : [{
        name : 'Random data',
        data : [<g:each in="${c.serie.datos.sort(new Comparator<DatoSerie>() {
        int compare(DatoSerie o1, DatoSerie o2) {
            return o1.period.compareTo(o2.period);
        }
    })}" var="d">
                    <g:if test="${d.period <= new BigInteger(ciclo)}">

                        [parseTime("${d.time}"),${d.price}],
                    </g:if>
                </g:each>]
    }]
});
</g:each>
function parseTime(time) {
    var parts = time.split(':');
    var date = new Date();
    date.setHours(parts[0],parts[1],parts[2]);
    return date.getTime();
}
</g:javascript>

</html>