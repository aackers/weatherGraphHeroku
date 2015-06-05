var app = angular.module('myApp', []);

app.run(function($rootScope) {
    $rootScope.name = "Avidan Ackerson";
});

app.controller('TemperatureControl', ['$scope', function($scope) {
    // get names using AngularJS AJAX API
    $scope.getData = function() {
        $http.get('/data').success(function(data) {
            $scope.temperatureRecords = data;
            $rootScope.temperatureRecords = data;
        });
    }
}]);