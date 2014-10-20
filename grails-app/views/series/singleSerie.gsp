<%--
  Created by IntelliJ IDEA.
  User: eliendojr
  Date: 8/3/14
  Time: 12:18 AM
--%>

<%@ page import="upbbolsa.SyncEngineService; edu.upb.upbBolsa.DatoSerie" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="upbolsa"/>
    <title></title>
</head>
<body>
<h1>${companie.name} (${companie.code})</h1>

<div>
    <g:link name="Comprar Acciones" controller="transacciones" action="compra" style="right: 100px;position: absolute;background-color: red ;color: #ffffff;font-size: 30px">Comprar Acciones</g:link>
    <br />
    <br />
    <br />
    <br />
    <br />
    <g:link name="Vender Acciones" controller="transacciones" action="venta" style="right: 100px;position: absolute;background-color: green ;color: #ffffff;font-size: 30px">Vender Acciones</g:link>
        <table>
        <tr>
            <td>
                <h2>
                Precio:
                </h2>
            </td>
            <td style = "padding-left: 20">
                <h2 id="price">
                ${formatNumber(number: stats.price, format: "##.00", locale: Locale.US)}
                </h2>
            </td>
        </tr>
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
</div>


<div id="container"></div>

<div>
    <span id="title" style="font-size: 24;">${lastNoticiaData.title}</span>
    <br />
    <br />

    <span id="contenido">${lastNoticiaData.descripcion}</span>
</div>

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
                            updateStats(data.stats);
                        }
                        inter = data.inter;
                        //inter = 10000;
                    });

                    $.getJSON("${createLink(controller: 'noticia', action: 'getNoticiasBySerieAndPeriod')}",{'serieId':${companie.serie.id}}, function(data){
                        console.log('data',data);
                        if(data.change){
                            if(data.title!=""){
                                $('#title').text(data.title);

                            }
                            if(data.contenido!=""){
                                $('#contenido').text(data.contenido);
                            }
                        }
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
    $("#price").text(stats.price.toFixed(2));
    $("#mean").text(stats.mean.toFixed(2));
    $("#min").text(stats.min.toFixed(2));
    $("#max").text(stats.max.toFixed(2));

}

</g:javascript>

</html>
