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
             $scope.show();
           }, function(error) {
             $scope.outputMessage = "Error: " + error;
           });

      $scope.cleanScreen();
  }

  $scope.show = function(){
    $http.get(url).then(function(result){
      $scope.users = result.data;
    }, function(){
      $scope.outputMessage = "Error: " + error;
    });
  }

  $scope.delete = function(userId){
    var deleteUrl = url + "/delete";

    if(userId != undefined){
      deleteUrl += "/"+userId;
    }

    $http.delete(deleteUrl).then(function(result){
      $scope.show();
    }, function(){
      $scope.outputMessage = "Error: " + error;
    });

    $scope.cleanScreen();
  }

  $scope.cleanScreen = function(){
    $scope.newUser.name = null;
    $scope.newUser.birthday = null;
    $scope.newUser.value = null;
    $scope.newUser.code = null;
    $scope.userId = null;
  }
});
