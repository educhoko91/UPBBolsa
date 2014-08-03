package upbbolsa

import edu.upb.upbBolsa.VariablesSistema

class SyncEngineService {

    def executorService;

    def runEngine() {
        executorService.submit({
            try {

                print "Engine Started";
                def fechaInicio = VariablesSistema.findByNombre("fechaInicio");
                def horaInicio = VariablesSistema.findByNombre("horaInicio");
                def cicloFin = VariablesSistema.findByNombre("cicloFin");
                def ciclo = VariablesSistema.findByNombre("ciclo");
                def interTiempo = VariablesSistema.findByNombre("interTiempo");
                def puntoInicial = VariablesSistema.findByNombre("puntoInicial")

                ciclo.value = -1;
                ciclo.save();
                def initDate = Date.parse("dd/MM/yyyy HH:mm", fechaInicio.value + " " + horaInicio.value);
                print initDate.toString() + "-" + new Date().toString();

                print initDate.getTime() - (new Date()).getTime()
                sleep(initDate.getTime() - (new Date()).getTime());
                ciclo.value = puntoInicial.value - 1;
                ciclo.save();
                while (true) {
                    print "Runnig Engine"
                    if (Integer.parseInt(ciclo.value) == Integer.parseInt(cicloFin.value)) {
                        break;
                    }
                    ciclo.value = Integer.parseInt(ciclo.value)+1;
                    ciclo.save();
                    sleep(1000 * Integer.parseInt(interTiempo.value));
                    print ciclo.value
                }
                ciclo.value = -2;
                ciclo.save()
                print "Engine Finished"
            } catch (Exception e) {
                print "Some thing when wrong!!!!"
                print e.toString();
            }
        } as GroovyCallable)
    }
}
