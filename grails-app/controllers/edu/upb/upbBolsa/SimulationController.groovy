package edu.upb.upbBolsa

import upbbolsa.SyncEngineService

class SimulationController {

    def syncEngineService

    def start() {
        syncEngineService.runEngine();
        redirect(controller: "series");
    }
    def stop() {
        SyncEngineService.stop();
        redirect(controller: "configSis");
    }

}
