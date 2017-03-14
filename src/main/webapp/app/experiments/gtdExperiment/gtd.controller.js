(function() {
    'use strict';

    angular
        .module('drillTheDataApp')
        .controller('GTDController', GTDController);

    GTDController.$inject = ['$http', 'GTDCharts','$scope'];

    function GTDController ($http, GTDCharts, $scope) {
        var vm = this;
        vm.selectedCountries = [];
        vm.countries = [];
        vm.incidents = [];

        loadCountries();


        vm.loadData = function () {
            $http.post('api/allIncidentsByListOfCountries', vm.selectedCountries).success(function (data) {
                vm.incidents = data;
                GTDCharts.makeGraphs(data);
            });
        };

        vm.resetData = function () {
          if(vm.selectedCountries.length == 0)
          {
              vm.incidents = [];
          }
          else{
              vm.loadData();
          }
        };

        function loadCountries() {
            $http.get('api/allCountries').success(function (data) {
                vm.countries = data;
            });
        }

        $scope.$on('windowResized', function() {
            GTDCharts.renderAll();
        });

    }
})();
