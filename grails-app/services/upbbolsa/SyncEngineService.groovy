package upbbolsa

import edu.upb.upbBolsa.VariablesSistema

class SyncEngineService {

    def executorService;
    static def ciclo;

    def runEngine() {
        executorService.submit({
            try {

                print "Engine Started";
                def fechaInicio = VariablesSistema.findByNombre("fechaInicio");
                def horaInicio = VariablesSistema.findByNombre("horaInicio");
                def cicloFin = VariablesSistema.findByNombre("cicloFin");
                def interTiempo = VariablesSistema.findByNombre("interTiempo");
                def puntoInicial = VariablesSistema.findByNombre("puntoInicial")

                ciclo = -1;
                def initDate = Date.parse("dd/MM/yyyy HH:mm", fechaInicio.value + " " + horaInicio.value);
                print initDate.toString() + "-" + new Date().toString();

                print initDate.getTime() - (new Date()).getTime()
                sleep(initDate.getTime() - (new Date()).getTime());
                ciclo = Integer.parseInt(puntoInicial.value) - 1;
                while (true) {
                    print "Runnig Engine"
                    if (ciclo == Integer.parseInt(cicloFin.value)) {
                        break;
                    }
                    ciclo = ciclo+1;
                    sleep(1000 * Integer.parseInt(interTiempo.value));
                    print ciclo+"";
                }
                print "Engine Finished"
            } catch (Exception e) {
                print "Some thing when wrong!!!!"
                print e.toString();
            }
        } as GroovyCallable)
    }

    static def getInstance() {
        return instance;
    }

    static def getCiclo() {
        return ciclo;
    }
}
