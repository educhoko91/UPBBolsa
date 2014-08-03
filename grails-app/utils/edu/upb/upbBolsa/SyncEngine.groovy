package edu.upb.upbBolsa

import edu.upb.upbBolsa.VariablesSistema
import liquibase.executor.ExecutorService
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.transaction.annotation.Transactional
import sun.org.mozilla.javascript.internal.Callable



/**
 * Created by eliendojr on 7/30/14.
 */
class SyncEngine {

    def startEngine() {
        new Engine().start();
    }

    class Engine extends Thread {
        @Override
        void run() {
            runEngine()
        }

        def runEngine() {
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
                def initDate = Date.parse("dd/MM/yyyy HH:MM", fechaInicio.value + " " + horaInicio.value);
                print initDate.getTime() - (new Date()).getTime()
                sleep(initDate.getTime() - (new Date()).getTime());
                ciclo.value = puntoInicial.value - 1;
                ciclo.save();
                while (true) {
                    print "runnig Engine"
                    if (ciclo.value == cicloFin.value) {
                        break;
                    }
                    ciclo.value++;
                    ciclo.save();
                    sleep(1000 * interTiempo.value);
                    print ciclo.value
                }
                ciclo.value = -2;
                ciclo.save()
            } catch (Exception e) {
                print "Some thing when wrong!!!!"
                print e.toString();
            }
        }
    }
}
