angular.module("UserApp").controller("UserController", function($scope, $http) {

  $scope.pageTitle = "User Management Page";
  $scope.newUser = {};
  $scope.outputMessage = "";
  $scope.users = {};

  var url = "http://localhost:8081/crudmongo/user";
  var config = { headers: { 'Content-Type': 'application/json' } }

  $scope.post = function(){
      var data = {
        "name": $scope.newUser.name,
        "birthday": $scope.newUser.birthday,
        "code": $scope.newUser.code,
        "value": $scope.newUser.value
      };

      $http.post(url, data, config)
           .then(function () {
             $scope.outputMessage = "Success";
           }, function(error) {
             $scope.outputMessage = "Error: " + error;
           });
  }

  $scope.show = function(){
    $http.get(url).then(function(result){
      $scope.users = result.data;
    }, function(){
      $scope.outputMessage = "Error: " + error;
    });
  }
});
