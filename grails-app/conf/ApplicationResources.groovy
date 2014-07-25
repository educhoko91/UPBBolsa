modules = {
    application {
        resource url:'js/application.js'
    }

    overrides {
        'jquery-theme' {
            resource id:'theme', url:'/css/jquery-ui-1.10.4.custom.min.css'
        }
    }
}