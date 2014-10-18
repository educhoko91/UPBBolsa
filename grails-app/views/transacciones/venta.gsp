
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Venta</title>
    <meta name="layout" content="upbolsa">
    <script type="text/javascript">
        function updateValues(){
            var companyNombre = $('.empresaNombre').val();
            console.log('no hay compania',companyNombre);
            if(companyNombre == 'null'){
                console.log('no hay compania',companyNombre);
                $('.precio-accion').html('0')
            } else {
                console.log('nombreCompania', companyNombre);
                $.getJSON("${createLink(controller: 'transacciones', action: 'actualizarValores')}",{'nombre':companyNombre}, function(data) {
                    $('.precio-accion').html(data.precio)
                    console.log("precio actual",data.precio)
                });
            }
        }


    </script>
</head>

<body>
    <h1>Vender Acciones</h1>
    <g:if test='${flash.message}'>
        <div class='flash-msg'>${flash.message}</div>
    </g:if>
    <div class="info-venta">
        Dinero en mi cuenta: ${user.capital}
    </div>
    <div class="data-simulacion">
        <span>Precio de la accion:</span> <span class="precio-accion">${precio} </span><br/>
        <span>Precio total: 200</span><br/><br/>
    </div>
    <g:form action="ventaAccion">
        <div class="data-venta">
            <span>Empresa:</span> <g:select name="empresa" class="empresaNombre" onchange="updateValues()" noSelection="${['null':'Seleccione una empresa...']}" from="${edu.upb.upbBolsa.Company.findAll().name}" >Empresa</g:select><br />
            <span>Cantidad:</span> <input type="number" name="cantidad" required="">
            <g:hiddenField name="precio" class="precio-accion"></g:hiddenField>
            <g:hiddenField name="costoTransfer" value="${edu.upb.upbBolsa.VariablesSistema.findByNombre('costoTransfer').value}"></g:hiddenField>
        </div>
        <div class="buttons-venta">
            %{--<g:link controller="transacciones" action="ventaAccion"><button type="button">Vender</button></g:link>--}%
            <g:submitButton name="Vender" class="buttons"></g:submitButton>
        </div>
    </g:form>
</body>
</html>