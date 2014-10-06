package upbbolsa

import edu.upb.upbBolsa.VariablesSistema

class SyncEngineService {

    def executorService;
    static int ciclo = -2;
    static Date sleepStart;
    static runnig = false;

    def runEngine() {
        executorService.submit({
            try {

                print "Engine Started";
                runnig = true;
                def fechaInicio = VariablesSistema.findByNombre("fechaInicio");
                def horaInicio = VariablesSistema.findByNombre("horaInicio");
                def cicloFin = VariablesSistema.findByNombre("cicloFin");
                def interTiempo = VariablesSistema.findByNombre("interTiempo");
                def puntoInicial = VariablesSistema.findByNombre("puntoInicial")

                ciclo = -1;
                def initDate = Date.parse("dd/MM/yyyy HH:mm", fechaInicio.value + " " + horaInicio.value);
                print initDate.toString() + "-" + new Date().toString();

                print initDate.getTime() - (new Date()).getTime()
                sleepStart = initDate;
                sleep(initDate.getTime() - (new Date()).getTime());
                ciclo = Integer.parseInt(puntoInicial.value) - 1;
                while (runnig) {
                    print "Runnig Engine"
                    if (ciclo == Integer.parseInt(cicloFin.value)) {
                        break;
                    }
                    ciclo = ciclo+1;
                    sleepStart = new Date();
                    sleep(1000 * Integer.parseInt(interTiempo.value));
                    print ciclo+"";
                }
                ciclo = -2;
                print "Engine Finished"
            } catch (Exception e) {
                print "Something when wrong!!!!"
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

    static def getInter() {
        if(new Date().getTime() > sleepStart.getTime())
            return (new Date().getTime())-(sleepStart.getTime());
        else
            return (sleepStart.getTime())-(new Date().getTime())+20;
    }
    static def stop() {
        runnig = false;
    }
}
