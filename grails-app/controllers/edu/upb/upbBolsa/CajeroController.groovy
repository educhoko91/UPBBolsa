package edu.upb.upbBolsa
import edu.upb.upbBolsa.Cajero

class CajeroController {

    def index() {
//        redirect(action: "Habilitaciones", params: params)
        render view: 'Habiltaciones', params: params
    }

    def save(){
//        int selfId = this.
        print(params)
    }
}
