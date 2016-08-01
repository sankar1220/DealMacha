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
DealMachaTerminal.controller('FooterController',function($scope,$rootScope,HTTPService,ToastrService){
	$scope.UserEmail=function(jsonData){
	var data = JSON.stringify(jsonData);
	console.log(data);
	var url ="/users/emailSubscription/create";
	var success = function(data){
		if(data.subscriptionStatus == "SUBSCRIBED"){
				ToastrService.showToastr('success','Successfully Subscribed','Success!');
					
			$scope.subStatus = "Thank You For Subscribing"
		}else{
			$scope.subStatus = "Already Subscribed"
			ToastrService.showToastr('error','You Subscribed with us!','Error!');
		}
	}
	var error = function() {
						ToastrService.showToastr('error','Something went wrong!','Error!');
						}
		HTTPService.processServerRes(url, 'POST', success,
								error,data);
		// if(UserEmail == "SUBSCRIBED"){
		// 	 success: function(response) {
		// 	},
		// 	error:function(){
	           
	 //       }

		// };
	}

$scope.scrollTop=function(event){
 $('html,body').animate({scrollTop: $(scope.scrollTo).offset().top }, 600,"easeOutBounce");


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
		event.preventDefault();
			$scope.user = user;
							
						var data = JSON.stringify($scope.user);
						console.log(data);
						var id = sessionStorage.getItem('userId');
						var url ="/users/"+id+"/edit";
						var success = function(data) {
							window.location.href='/myaccount'
							ToastrService.showToastr('success','Successfully Updated','Success!');
						}
						var error = function() {
							ToastrService.showToastr('error','Something went wrong! PLease check the details','Error!');
						}
						HTTPService.processServerRes(url, 'POST', success,
								error, data);

	}

	$scope.setIntialValues =function(key,value){
		$scope.user[key] = value;
	}
		
		$scope.changePassword = function(user1){

			var password = document.getElementById("newPassword");
  			var confirm_password = document.getElementById("confirmPassword");
					if(password.value != confirmPassword.value) {

						$(event.currentTarget).parents('.user-chgpwd').find('#wrg_pass').addClass('show').addClass('error');
    				confirm_password.setCustomValidity("Passwords Don't Match");
							return false;
  					} else {
  						$(event.currentTarget).parents('.user-chgpwd').find('#wrg_pass').removeClass('show').removeClass('error');
    						confirm_password.setCustomValidity('');
  					}
		$scope.chgpwd = {};
		$scope.chgpwd = user1;		
					
						var data = JSON.stringify($scope.chgpwd);
						console.log(data);
						var url ="/users/changePassword";
						var success = function(data) {
								/*window.location.href="/myaccount";	*/	
									ToastrService.showToastr('success','Password Changed Successfully','Done!');
							var frm = document.getElementsByName('chg_pass_form')[0];
							frm.reset();		
						}
						var error = function() {
								ToastrService.showToastr('error','Please try again','Error!');
							
						}
						HTTPService.processServerRes(url, 'POST', success,
								error, data);
	}


});
DealMachaTerminal.controller('MyAccountWalletController',function($scope,$rootScope,$http, baseURL,HTTPService,ToastrService){
	$scope.getRedeemCashbackAmount=function(event){
		$http({
    method: "GET",
    url: baseURL.IP+'/users/account/cashBackAmount',
    data: '',
    headers: {
           'Content-Type': "application/json",
           'Accept': "application/json"
        }
      }).success(function(data) {
      	$.each(data,function(k,v){
      		$scope.cashBack=v.cashbackAmount;
      	});
     }).error(function(error){
        $scope.error = error;
     });
					$("#walletModal").modal('show');
						
     $http({
    method: "GET",
    url: baseURL.IP+'/admin/requestTypes/all',
    data: '',
    headers: {
           'Content-Type': "application/json",
           'Accept': "application/json"
        }
      }).success(function(data) {
       $scope.requestDetails = data;
     }).error(function(error){
        $scope.error = error;
     });
 }
  $scope.attributes=[];
 $scope.requestType= function(requestTypesId) {
 	  // alert('success')
 	 
    	/*url:baseURL.IP+'/admin/requestTypes/'+requestTypesId,'GET','',function(Data){
    		$scope.singleRequestTypesDetails=Data;

    	}*/
 $scope.attributes=[];
$scope.requestAttributes = {};
    	$http({
    method: "GET",
    url:baseURL.IP+'/admin/requestTypes/'+requestTypesId,
  
    headers: {
           'Content-Type': "application/json",
           'Accept': "application/json"
        }
      }).success(function(data) {
       $scope.attributes = data.requestTypeAttributesModels;
     
     }).error(function(error){
        $scope.error = error;
     });
		}
	
$scope.request = {};
	$scope.saveRequest = function(request){
		var userRequestAttributes = "";
			
			for(var k in request.requestAttributes){

				userRequestAttributes += k+":::"+request.requestAttributes[k]+"," ;

			}
			userRequestAttributes = userRequestAttributes.substring(0,userRequestAttributes.length-1);
				

				var requestJson = {};
				requestJson['requestTypesId'] = request.requestTypesId;
				requestJson['requestComments'] = request.requestComments;
				requestJson['requestType'] = userRequestAttributes;

				console.log(JSON.stringify(requestJson));
				 var url ="/admin/requests/create";
					var success = function(requestJson){
			//	$state.go('/MyAccountRequestFragment')
			$('[href="#request"]').trigger("click");
			
		}

		var error = function() {
									ToastrService.showToastr('error','Something went wrong! PLease check the details','Error!');
				
						}
				HTTPService.processServerRes(url, 'POST', success, error, requestJson);
	}
	});

	
DealMachaTerminal.controller('MyAccountRequestController',function($scope,$rootScope,$http, baseURL,HTTPService,ToastrService){
			$scope.getSingleRequest=function(requestsId){
		$http({
    method: "GET",
    url:baseURL.IP+'/admin/requests/'+requestsId,
  
    headers: {
           'Content-Type': "application/json",
           'Accept': "application/json"
        }
      }).success(function(data) {
      	$scope.requestAttributes=[];
      	$scope.singleRequestDetails=data;
      	var str = data.requestType;
      	var RequestDetails = str.split(',')

      	$scope.requestObj = {};
				for (var i = 0; i < RequestDetails.length; i++) {
				    var split = RequestDetails[i].split(':::');
				    $scope.requestObj[split[0].trim()] = split[1].trim();
				    console.log(JSON.stringify($scope.requestObj));
				}
     }).error(function(error){
        $scope.error = error;
     });
     $("#requestModal").modal('show');
     // HTTPService.processServerRes(url, 'GET', success, error, requestJson);
   }
	});

DealMachaTerminal.controller('RegistrationUserController',function($scope,$rootScope,$http, baseURL,HTTPService,ToastrService){
	


	$scope.userRegistration=function(register){
		var password = document.getElementById("password");
  			var confirm_password = document.getElementById("confirmPassword");
					if(password.value != confirmPassword.value) {

						$(event.currentTarget).parent().find('#wrg_pass').addClass('show').addClass('error');
    				confirm_password.setCustomValidity("Passwords Don't Match");
							return false;
  					} else {
  						$(event.currentTarget).parent().find('#wrg_pass').removeClass('show').removeClass('error');
    						confirm_password.setCustomValidity('');
  					}
  					register['mobileNo']=$scope.register.mobileNo+'';

		console.log(JSON.stringify(register));
				$http({
		    method: "POST",
		    url:'/users/create',
		  	data:register,
		    headers: {
		           'Content-Type': "application/json",
		           'Accept': "application/json"
		        }
		      }).success(function(data) {
		      	$("#loader").addClass('show');
							if(data.emailStatus=="DUPLICATEEM"){
							ToastrService.showToastr('error','Your Details already exists,Give Someother or click forgot password','Done!');	
							}
							else if(data.emailStatus=="DUPLICATEE"){
							ToastrService.showToastr('error','Your EmailId already exists,Give Someother or click forgot password','Done!');	
							}
							else if(data.emailStatus=="DUPLICATEM"){
							ToastrService.showToastr('error','Your Mobile.No already exists,Give Someother or click forgot password','Done!');	
							}
							else{
							ToastrService.showToastr('success','You have Successfully registered with us!','Done!');
			
									window.location.href="/";
												
							
							}

		      
		     }).error(function(error){
		     	ToastrService.showToastr('error','Something went wrong!','Error!');
			
		        $scope.error = error;
		     });
		     $("#requestModal").modal('show');
		     // HTTPService.processServerRes(url, 'GET', success, error, requestJson);
		   }
			});

DealMachaTerminal.controller("ProductListController",function(HTTPService,$scope){
$scope.loadingShowStatus = false;

$('.load_more').removeClass('hide');
$scope.pageNo = 1;
$scope.paginationStatus = true;
 $scope.QueryString = (function(a) {
        if (a == "") return {};
        var b = {};
        for (var i = 0; i < a.length; ++i)
        {
            var p=a[i].split('=');
            if (p.length != 2) continue;
            b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
        }
        return b;
    })(window.location.search.substr(1).split('&'))
$scope.nextPage=function(){
$scope.loadingShowStatus = true;
var requestUrl ="/products/category/page";
	var requestCategory = $scope.QueryString['category'];

	if($scope.QueryString['pg']==undefined){
		$scope.pageNo +=$scope.pageNo; 
	}else{
		$scope.pageNo =$scope.QueryString['pg']+1; 
}
requestUrl = requestUrl+'?category='+requestCategory+"&pageNo="+$scope.pageNo;

var success =function(data){
if(data != ""){

	$('.prod-list').append(data);
$scope.loadingShowStatus = false;
}else{
	$scope.loadingShowStatus = false;
	$('.load_more').addClass('hide');
	$('.load_more_div').addClass('show');
}


}
var error = function(data){
	alert("error");
}
HTTPService.processServerRes(requestUrl,"GET",success,error,'');
}


})


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

DealMachaTerminal.service('baseURL', function() {
	return {
			IP:"http://192.168.0.73:8081"
}
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
});

DealMachaTerminal.directive('passwordmatch',[function(){
    return {
    require: 'ngModel',
    scope: {

      reference: '=validPasswordC'

    },
    link: function(scope, elm, attrs, ctrl) {
      ctrl.$parsers.unshift(function(viewValue, $scope) {

        var noMatch = viewValue != scope.reference
        ctrl.$setValidity('noMatch', !noMatch);
        return (noMatch)?noMatch:!noMatch;
      });

      scope.$watch("reference", function(value) {;
        ctrl.$setValidity('noMatch', value === ctrl.$viewValue);

      });
    }
  }
 
}]);

