package edu.upb.upbBolsa

class SimulationController {

    def syncEngineService

    def start() {
        syncEngineService.runEngine();
        redirect(controller: Serie);
    }

}
