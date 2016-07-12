var DealMachaTerminal = angular.module("DealMachaTerminal",[]);

DealMachaTerminal.controller('HeaderController',function($scope,$rootScope,$http){

	$scope.setSessionVariables = function(userName, userId) {

		if (window.sessionStorage) {
			window.sessionStorage.setItem('userId', userId)
			window.sessionStorage.setItem('userName', userName)
		}

	}
	$scope.clearSession = function(){
		if(window.sessionStorage){
				window.sessionStorage.removeItem('userId')
			window.sessionStorage.removeItem('userName')
		}
	}
});
DealMachaTerminal.controller('MyAccountDetailsController',function($scope,$rootScope,HTTPService,ToastrService){


$scope.user  ={},
	$scope.editUser  = function(event){
		
		$('.editable').removeClass('hide');
		$('.no_edit').addClass('hide');
		
	}
	$scope.cancelEdit = function(event){
		$('.editable').addClass('hide');
		$('.no_edit').removeClass('hide');
	}
	$scope.updateUser = function(user){
			$scope.user = user;
							
						var data = JSON.stringify($scope.user);
						console.log(data);
						var id = sessionStorage.getItem('userId');
						var url ="/users/"+id+"/edit";
						var success = function(data) {
							window.location.href='/myaccount'
						}
						var error = function() {
							alert('error')
						}
						HTTPService.processServerRes(url, 'POST', success,
								error, data);

	}

	$scope.setIntialValues =function(key,value){
		$scope.user[key] = value;
	}
		
		$scope.changePassword = function(user1){
		$scope.chgpwd = {};
		$scope.chgpwd = user1;		
					
						var data = JSON.stringify($scope.chgpwd);
						console.log(data);
						var url ="/users/changePassword";
						var success = function(data) {
							ToastrService.showToastr('success','Password Changed Successfully','Done!');
							window.location.href="/myaccount";						
						}
						var error = function() {
								ToastrService.showToastr('error','Please try again','Error!');
							
						}
						HTTPService.processServerRes(url, 'POST', success,
								error, data);
	}
});




/*==============================================HTTP SERVICE=========================================*/
DealMachaTerminal.service('HTTPService', function($http) {
	this.processServerRes = function(url, type, success, error, data) {
		$http({
			url : url,
			method : type,
			data : data,
			headers : {
				'Content-Type' : "application/json",
				'Accept' : "application/json"
			}

		}).success(success).error(error);
	};
		this.processServerHTMLRes = function(url, type, success, error) {
		$http({
			url : url,
			method : type,
			dataType : "text/html",
			headers : {
				'Content-Type' : "text/html",
				'Accept' : "text/html"
			}

		}).success(success).error(error);
	};
});

/*======================================Toastr Service================================================*/
DealMachaTerminal.service('ToastrService', function() {

	this.showToastr = function(msgType,msg,title){
				toastr.options = {
                                      "closeButton": true,
                                      "debug": false,
                                      "positionClass": "toast-top-right",
                                      "onclick": null,
                                      "showDuration": "1000",
                                      "hideDuration": "1000",
                                      "timeOut": "5000",
                                      "extendedTimeOut": "1000",
                                      "showEasing": "swing",
                                      "hideEasing": "linear",
                                      "showMethod": "fadeIn",
                                      "hideMethod": "fadeOut",
                                      "progressBar":"true"
                                 }
                                    toastr[msgType](msg, title)
                               }
})

