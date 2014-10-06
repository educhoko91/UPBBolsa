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
<h1>${companie.name} (${companie.code})</h1>

<div>
    <h2>
    <table>
        <tr>
            <td>
                Promedio:
            </td>
            <td style = "padding-left: 20" id="mean">
                 ${formatNumber(number: stats.mean, format: "##.00", locale: Locale.US)}
            </td>
        </tr>
        <tr>
            <td>
                Min:
            </td>
            <td style = "padding-left: 20" id="min">
                ${formatNumber(number: stats.min, format: "##.00", locale: Locale.US)}
            </td>
        </tr>
        <tr>
            <td>
                Max:
            </td>
            <td style = "padding-left: 20" id="max">
                ${formatNumber(number: stats.max, format: "##.00", locale: Locale.US)}
            </td>
        </tr>

    </table>
    </h2>
</div>


<div id="container"></div>
</body>
<g:javascript>

$(document).ready(function() {
    Highcharts.setOptions({
		global : {
			useUTC : false
		},
		tooltip: {
		    valueDecimals: 2
		}
	});
    var chart =  $('#container').highcharts('StockChart',{

    chart : {
        events : {
            load : function() {

                // set up the updating of the chart each second
                var series = this.series[0];
                var inter = ${inter};
                //var inter = 10000;
                var succes = false;
                var ytime = -9999;
                setInterval(function() {
                    $.getJSON("${createLink(controller: 'series', action: 'updateSeries')}",{'id':${companie.serie.id}}, function(data) {
                        succes = data.succes;
                        if(succes && ytime != data.price) {
                            x = parseTime(data.time);
                            y = data.price;
                            ytime = y;
                            series.addPoint([x, y], true);
                        }
                        inter = data.inter;
                        //inter = 10000;
                    });

                }, inter);
            }
        }

    },

    rangeSelector: {
        enabled: false
    },

    exporting: {
        enabled: false
    },
    series : [{
        name : 'Precio',
        data : [<g:each in="${companie.serie.datos.sort(new Comparator<DatoSerie>() {
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
    });
function parseTime(time) {
    var parts = time.split(':');
    var date = new Date();
    date.setHours(parts[0],parts[1],parts[2]);
    return date.getTime();
}

function updateStats(stats) {
    $("")
}

</g:javascript>

</html>