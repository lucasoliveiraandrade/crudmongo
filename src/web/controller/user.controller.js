angular.module("UserApp").controller("UserController", function($scope, $http) {

  $scope.pageTitle = "User Management Page";
  $scope.newUser = {};
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


      if($scope.newUser.id == undefined){
        $http.post(url, data, config).then(function () {
           $scope.show();
         }, function(error) {
         });
      }
      else {

        data["id"] = $scope.newUser.id;

        $http.put(url, data, config).then(function () {
           $scope.show();
         }, function(error) {
         });
      }
      $scope.show();
      $scope.cleanScreen();
  }

  $scope.show = function(){
    $http.get(url, config).then(function(result){
      $scope.users = result.data;
    }, function(){
    });
  }

  $scope.delete = function(userIdDelete){
    var deleteUrl = url + "/delete";

    if(userIdDelete != undefined){
      deleteUrl += "/"+userIdDelete;
    }

    $http.delete(deleteUrl, config).then(function(result){
      $scope.show();
    }, function(){
    });

    $scope.cleanScreen();
  }

  $scope.update = function(userIdUpdate){

    $http.get(url + "/" + userIdUpdate, config).then(function(result){
      var user = result.data;

      $scope.newUser.name = user.name;
      $scope.newUser.birthday = user.birthday;
      $scope.newUser.value = user.value;
      $scope.newUser.code = user.code;
      $scope.newUser.id = user.id;

    }, function(){
    });

    $scope.cleanScreen();
  }

  $scope.cleanScreen = function(){
    $scope.newUser.name = null;
    $scope.newUser.birthday = null;
    $scope.newUser.value = null;
    $scope.newUser.code = null;
    $scope.userId = null;

    $scope.userIdUpdate = null;
    $scope.userIdDelete = null;
  }
});
