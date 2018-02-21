angular.module("UserApp").controller("UserController", function($scope, $http) {

  $scope.pageTitle = "User Management Page";
  $scope.newUser = {};

  $scope.post = function(){

      var data = {
        "name": $scope.newUser.name,
        "birthday": $scope.newUser.birthday,
        "code": $scope.newUser.code,
        "value": $scope.newUser.value
      };

      var config = { headers: { 'Content-Type': 'application/json' } }

      var url = "http://localhost:8081/crudmongo/user";

      $http.post(url, data, config)
           .then(function () {
              console.log('Success');
           }, function(error) {
              console.log('Error: ' + error);
           });
  }
});
