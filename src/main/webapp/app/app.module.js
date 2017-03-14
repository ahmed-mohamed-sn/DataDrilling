(function() {
    'use strict';

    angular
        .module('drillTheDataApp', [
            'ngStorage',
            'ngResource',
            'ngCookies',
            'ngAria',
            'ngSanitize',
            'ngCacheBuster',
            'ngFileUpload',
            'ui.bootstrap',
            'ui.bootstrap.datetimepicker',
            'ui.router',
            'infinite-scroll',
            // jhipster-needle-angularjs-add-module JHipster will add new module here
            'angular-loading-bar',
            'ui.select'
        ])
        .run(run);

    run.$inject = ['stateHandler', '$rootScope'];

    function run(stateHandler, $rootScope) {
        $( window ).resize(function() {
            $rootScope.$broadcast('windowResized');
        });
        stateHandler.initialize();
    }
})();
