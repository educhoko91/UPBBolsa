
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Venta</title>
    <meta name="layout" content="upbolsa">
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'transacciones.css')}" media="screen" title="style (screen)" />
    <script type="text/javascript">
        var precioAccion = 0;
        var primeraVez = true;
        function updateValues(){
            var companyNombre = $('.empresaNombre').val();
            console.log('no hay compania',companyNombre);
            if(companyNombre == 'null'){
                console.log('no hay compania',companyNombre);
                $('.precio-accion').html(0)
            } else {
                console.log('nombreCompania', companyNombre);
                $.getJSON("${createLink(controller: 'transacciones', action: 'actualizarValores')}",{'nombre':companyNombre}, function(data) {
                    $('.precio-accion').html(data.precio)
                    precioAccion = data.precio;
                    console.log("precio actual",data.precio)
                });
            }
        }

        function calcularTotal(){
            var cantidad = $('.cantidad').val();
            cantidad = parseFloat(cantidad);
            var total = cantidad*precioAccion;
            $('.total-venta').html(total);

        }

        var tiempoIntervalo = ${edu.upb.upbBolsa.VariablesSistema.findByNombre("interTiempo").value};
        var primerTiempo = ${upbbolsa.SyncEngineService.getInter()};
        primerTiempo = parseFloat(primerTiempo)
        tiempoIntervalo = parseInt(tiempoIntervalo);
        if (primeraVez) {
            window.setInterval(function(){
                updateValues();
                calcularTotal();
            },primerTiempo-100);
            primeraVez = false;
        } else {
            window.setInterval(function(){
                updateValues();
                calcularTotal();
            },tiempoIntervalo*900);
        }



    </script>
</head>

<body class="form">
    <h1>Vender Acciones</h1>
    <br />
    <g:if test='${flash.message}'>
        <div class='flash-msg'>${flash.message}</div>
    </g:if>
    <br/>
    <p>
        <div class="info-venta">
            Dinero en mi cuenta: <span class="bold">${user.capital}</span>
        </div>
        <br/>
        <g:form action="ventaAccion">
            <div class="data-venta">
                <span>Empresa:</span> <g:select name="empresa" class="empresaNombre" onchange="updateValues()" noSelection="${['null':'Seleccione una empresa...']}" from="${empresas}" >Empresa</g:select><br />
                <br/>
                <span>Cantidad:</span> <input class="cantidad" type="number" name="cantidad" required="" onchange="calcularTotal()" onkeypress="calcularTotal()">
                <g:hiddenField name="precio" class="precio-accion"></g:hiddenField>
                <g:hiddenField name="costoTransfer" value="${edu.upb.upbBolsa.VariablesSistema.findByNombre('costoTransfer').value}"></g:hiddenField>
            </div>
            <div class="data-simulacion">
                <span>Precio de la accion:</span> <span id="precio-accion" class="precio-accion">${precio} </span><br/>
                <span>Precio total:</span><span class="total-venta"></span><br/><br/>
            </div>
            <div class="buttons-venta">
                %{--<g:link controller="transacciones" action="ventaAccion"><button type="button">Vender</button></g:link>--}%
                <g:submitButton name="Vender" class="buttons"></g:submitButton>
            </div>
        </g:form>
    </p>
</body>
</html>