var app = angular.module('myApp', []);

google.load('visualization', '1', {
    packages: ['corechart', 'table']
});


app.controller('myCtrl', function($scope, $http, $interval) {

    $scope.loadData = function() {
        $http.get("/test").then(function(response) {
            drawChart(response.data);
            drawTable(response.data);
        });
    };

    $scope.loadData();

    $interval(function() {
        $scope.loadData();
    }, 600000);
    // Set a callback to run when the Google Visualization API is loaded.
    //google.setOnLoadCallback(drawChart);

    // Callback that creates and populates a data table,
    // instantiates the pie chart, passes in the data and
    // draws it.
    function drawChart(tempData) {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('datetime', 'Date');
        data.addColumn('number', 'Temperature');
        angular.forEach(tempData, function(key, value){
            data.addRow([new Date(key.recordedTime), key.temperature]);
        });

        // Set chart options
        var options = {'title':'Temperature Over Time',
            'width':700,
            'height':700};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        var table = new google.visualization.Table(document.getElementById('table_div'));

        chart.draw(data, options);
        table.draw(data);
    }
});