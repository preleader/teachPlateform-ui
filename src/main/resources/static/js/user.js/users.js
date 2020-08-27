import {Box} from '../common.js';
angular.module("userApp",[]).
controller("userController",function($scope,$http,$location,$window){
	$scope.userList = null;
	$scope.list = function(id){
		let url_list ="/api?address=api/users/";
		if(id)
			url_list += id;
		$http({
			method: 'GET',
			headers: {'Content-Type': "application/x-www-form-urlencoded"},
			url: url_list
		}).then(function successCallback(response) {
				console.log(response);
				$scope.userList = response.data.userList;
				$scope.userList.push({});
			}, function errorCallback(response) {
				console.log(response);
		});
	};
	$scope.list();
	$scope.del = function(index){
		var id = $scope.userList[index].userID;
		$http({
			method: 'DELETE',
			headers: {'Content-Type': "application/x-www-form-urlencoded"},
			url: '/api?address=api/users/delete/'+id
		}).then(function successCallback(response) {
				console.log(response);
				if(response.data.success){
					$scope.userList.splice(index,1);
					Box.alert(response.data.msg,2000,'success');
				}
			}, function errorCallback(response) {
				console.log(response);
				Box.alert("删除出错",2000,'danger');
		});
	};
	$scope.save = function(index){
		let id = $scope.userList[index].userID,
			updateurl = "/api?address=api/users/create/";
		if(id){
			updateurl = "/api?address=api/users/update/";
		}
		let userupdate = $scope.userList[index];
		console.log(userupdate)
		$http({
			method: 'POST',
			url: updateurl,
			headers: {'Content-Type': "application/x-www-form-urlencoded"},
			data: $.param(userupdate)  
		}).then(function successCallback(response) {
				console.log(response);
				$window.location.reload();
				Box.alert(response.data.msg,2000,'success');
			}, function errorCallback(response) {
				console.log(response);
				$window.location.reload();
				Box.alert("操作出错",2000,'danger');
		});
	};
});