(function() {
    'use strict';

    angular
        .module('drillTheDataApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('experiment', {
            abstract: true,
            parent: 'app'
        });
    }
})();
