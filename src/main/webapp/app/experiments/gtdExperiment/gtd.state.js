(function() {
    'use strict';

    angular
        .module('drillTheDataApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('gtd', {
            parent: 'app',
            url: '/experiments/gtd',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'app/experiments/gtdExperiment/gtd.html',
                    controller: 'GTDController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
