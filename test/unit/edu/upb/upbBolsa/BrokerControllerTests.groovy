package edu.upb.upbBolsa



import org.junit.*
import grails.test.mixin.*

@TestFor(BrokerController)
@Mock(Broker)
class BrokerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/broker/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.brokerInstanceList.size() == 0
        assert model.brokerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.brokerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.brokerInstance != null
        assert view == '/broker/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/broker/show/1'
        assert controller.flash.message != null
        assert Broker.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/broker/list'

        populateValidParams(params)
        def broker = new Broker(params)

        assert broker.save() != null

        params.id = broker.id

        def model = controller.show()

        assert model.brokerInstance == broker
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/broker/list'

        populateValidParams(params)
        def broker = new Broker(params)

        assert broker.save() != null

        params.id = broker.id

        def model = controller.edit()

        assert model.brokerInstance == broker
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/broker/list'

        response.reset()

        populateValidParams(params)
        def broker = new Broker(params)

        assert broker.save() != null

        // test invalid parameters in update
        params.id = broker.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/broker/edit"
        assert model.brokerInstance != null

        broker.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/broker/show/$broker.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        broker.clearErrors()

        populateValidParams(params)
        params.id = broker.id
        params.version = -1
        controller.update()

        assert view == "/broker/edit"
        assert model.brokerInstance != null
        assert model.brokerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/broker/list'

        response.reset()

        populateValidParams(params)
        def broker = new Broker(params)

        assert broker.save() != null
        assert Broker.count() == 1

        params.id = broker.id

        controller.delete()

        assert Broker.count() == 0
        assert Broker.get(broker.id) == null
        assert response.redirectedUrl == '/broker/list'
    }
}
