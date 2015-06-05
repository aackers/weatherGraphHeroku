var app = angular.module('myApp', []);

app.run(function($rootScope) {
    $rootScope.name = "Avidan Ackerson";
});

app.controller('TemperatureControl', function($scope) {
    // get names using AngularJS AJAX API
    $http.get('/data').success(function(data){
        $scope.temperatureRecords = data;
        $rootScope.temperatureRecords = data;
    });
});