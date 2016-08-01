// DealMacha Controller Js
function MainCtrl($scope) {

	$scope.persons = [
		{
			id: '1',
			firstName: 'Monica',
			lastName: 'Smith'
		},
		{
			id: '2',
			firstName: 'Sandra',
			lastName: 'Jackson'
		},
		{
			id: '3',
			firstName: 'John',
			lastName: 'Underwood'
		},
		{
			id: '4',
			firstName: 'Chris',
			lastName: 'Johnatan'
		},
		{
			id: '5',
			firstName: 'Kim',
			lastName: 'Rosowski'
		}
	];
}
function HeadController($scope,$rootScope,mainMenu){
	$scope.Menu=mainMenu.role;
}
function DashboardPageController($scope,$rootScope,baseURL,processReqFactory){
	processReqFactory.processReq(baseURL.IP+'/home/all','GET','',function(Data){
		$scope.homeDetails=Data;

	},function(){});
	processReqFactory.processReq(baseURL.IP+'/transaction/failTransactions','GET','',function(Data){
		$scope.transactionDetails=Data;

	},function(){});
	processReqFactory.processReq(baseURL.IP+'/merchant/all','GET','',function(Data){
		$scope.merchantDetails=Data;

	},function(){});
		
}

function UserCreationController($scope,$state,processReqFactory,baseURL){
		$scope.UsersCreate=function(jsonData){
		var JsonUserData=angular.copy(jsonData);
		JsonUserData['userType']="CUSTOMER",
		JsonUserData['status']="ACTIVE",

		console.log(JSON.stringify(JsonUserData));
		processReqFactory.processReq(baseURL.IP+'/users/create','POST',JsonUserData,function(){
		swal({   title: "SUCCESS!",   text: "USERS CREATED SUCCESSFULLY",   type: "success",   confirmButtonText: "Success" });
		$state.go('Users.User')
		},function(){
		swal({   title: "ERROR!",   text: "USERS NOT CREATED",   type: "warning",   confirmButtonText: "OK" });
		});
	}
	
	}
 function UserController($scope,$http,processReqFactory,baseURL,dataTablesInitService){
			processReqFactory.processReq(baseURL.IP+'/users/all','GET','',function(Data){
		 var columns = [
			{ "data": "userCode" },
			{ "data": "userName" },
			{ "data": "mobileNo" },
			{ "data": "emailId" },
			{ "data": "status" },
			{ "data": "usersId" ,
				 "orderable": false,
		        "searchable": false,
			"render": function(data,type,row,meta) {
				var all = '<a href="#/Users/SingleUserTemplate/'+data+'">View</a>'
				return all;
				}
			}
		];
		dataTablesInitService.initDataTables(Data,columns,'#usersTable');
		console.log(JSON.stringify($scope.userData));
	},function(){});
 }

 function SingleUserController($scope,$rootScope,$http,processReqFactory,baseURL,$stateParams,$modal){
	processReqFactory.processReq(baseURL.IP+'/users/'+$stateParams.userId,'GET','',function(Data){
		$scope.singleUserDetails=Data;

		// console.log(JSON.stringify($scope.singleUserDetails));
	},function(){});
	$scope.editUser=function(data){
		var modalInstance = $modal.open({
			templateUrl: "Views/UserEditTemplate.html",
			controller:UserEditController
		});
		$rootScope.UserEditData=data;
	}
 }


 function UserEditController($scope, $http, baseURL, $modalInstance, $rootScope, processReqFactory){
 	$scope.UserEdit=function(UserEditData){
 		var JsonUserEditData=angular.copy(UserEditData);
		processReqFactory.processReq(baseURL.IP+"/users/"+UserEditData.id+"/edit","POST",JsonUserEditData,function(response){
			swal({  title: "SUCCESS!",   text: "USERS EDITED SUCCESSFULLY",   type: "success",   confirmButtonText: "Success" });
       location.path('Users/UserEditTemplate')
       },function(){
       	swal({ 	title: "ERROR!",   text: "USERS NOT EDITED",   type: "warning",   confirmButtonText: "OK" });
        });
 		}
 		$scope.cancel=function(){
		$modalInstance.close();
	}
}

 function RequestController($scope,$http,processReqFactory,baseURL,dataTablesInitService){
		processReqFactory.processReq(baseURL.IP+'/admin/requests/all','GET','',function(Data){
		 var columns = [
			{ "data": "userName" },
			{ "data": "requestTypeName"},
			{ "data": "requestType" ,
				 "orderable": true,
		      "searchable": true,
					"render": function(data,type,row,meta) {
      			var requestsData = data.split(',');
      			$scope.requestObj = {};
						for (var i = 0; i < requestsData.length; i++) {
					    var split = requestsData[i].split(':::');
					    $scope.requestObj[split[0]] = split[1];
						}
						return $scope.requestObj['Amount'];
					}
			},
			{ "data": "requestStatus" },
			{ "data": "requestsId" ,
				 "orderable": false,
		        "searchable": false,
			"render": function(data,type,row,meta) {
					var all = '<a href="#/Requests/SingleRequestsTemplate/'+data+'">View</a>'
				return all;
				}
			}
		];
		dataTablesInitService.initDataTables(Data,columns,'#requestsTable');
 });
	}


 function SingleRequestController($scope,$rootScope,$http,processReqFactory,baseURL,$stateParams,$modal){
	processReqFactory.processReq(baseURL.IP+'/admin/requests/'+$stateParams.requestsId,'GET','',function(Data){
		$scope.singleRequestsDetails=Data;
		var str = $scope.singleRequestsDetails.requestType;
      	var RequestDetails = str.split(',')
		$scope.requestObj = {};
				for (var i = 0; i < RequestDetails.length; i++) {
				    var split = RequestDetails[i].split(':::');
				    $scope.requestObj[split[0]] = split[1];
				   // return $scope.requestObj['Amount'];
				}
	},function(){});
		$scope.RequestsEdit=function(data){
			var modalInstance = $modal.open({
				templateUrl: "Views/RequestsEditTemplate.html",
				controller:RequestsEditController
			});
			$rootScope.RequestsEditData=data;
	}
}

  function RequestsEditController($scope, $http, baseURL, $modalInstance, $rootScope, processReqFactory){
 			$scope.cancel=function(){
		$modalInstance.close();
	}
}

function RequestsTypeCreationController($scope,$state,processReqFactory,baseURL){
	// $scope.requestJson={};
	var attr = {
    	"requestTypeAttribute":null
    }
    $scope.attributes=[];
    $scope.attributes.push(attr)
    $scope.addAttribute = function(){
    	var attr1 = {
    		"requestTypeAttribute":null
    } 
    	$scope.attributes.push(attr1);
    }

    $scope.removeattribute=function (number) {
    	$scope.attributes.splice(number,1);
    }


	$scope.RequestsTypeCreate=function(jsonData){
		var JsonRequestTypeData=angular.copy(jsonData);
		JsonRequestTypeData['requestTypeAttributesModels'] = $scope.attributes
		console.log(JSON.stringify(JsonRequestTypeData));
		// console.log(JSON.stringify($scope.attributes));
		processReqFactory.processReq(baseURL.IP+'/admin/requestTypes/create','POST',JsonRequestTypeData,function(){
		swal({   title: "SUCCESS!",  text: "RequestType CREATED SUCCESSFULLY",   type: "success",   confirmButtonText: "Success" });
		$state.go('Offers.Offer')
		},function(){
		swal({   title: "ERROR!",  text: "RequestType NOT CREATED",   type: "warning",   confirmButtonText: "OK" });
		});
	}
		
	}

function RequestsTypeController($scope,$http,processReqFactory,baseURL,dataTablesInitService){
		processReqFactory.processReq(baseURL.IP+'/admin/requestTypes/all','GET','',function(Data){
		 var columns = [
			{ "data":"requestType" },
			{ "data": "status" },
			{ "data":"requestTypesId" ,
				 "orderable": false,
		        "searchable": false,
			"render": function(data,type,row,meta) {
				var all = '<a href="#/Requests/SingleRequestsTypeTemplate/'+data+'">View</a>'
				return all;
				}
			}
		];
		dataTablesInitService.initDataTables(Data,columns,'#requestsTypeTable');
		console.log(JSON.stringify($scope.requestsTypeData));
	},function(){});
 }


 function SingleRequestsTypeController($scope,$rootScope,$http,processReqFactory,baseURL,$stateParams,$modal){
		processReqFactory.processReq(baseURL.IP+'/admin/requestTypes/'+$stateParams.requestTypesId,'GET','',function(Data){
		$scope.singleRequestTypesDetails=Data;

		// console.log(JSON.stringify($scope.singleRequestTypesDetails));
	
	},function(){});

				$scope.RequestTypeEdit=function(data){
		var modalInstance = $modal.open({
			templateUrl: "Views/RequestTypeEditTemplate.html",
			controller:RequestTypeEditController
		});
		$rootScope.RequestTypeEditData=data;
	}

	}

	 function RequestTypeEditController($scope, $http, baseURL, $modalInstance, $rootScope, processReqFactory){
 			$scope.RequestTypeEditDetails=function(RequestTypeEditData){
 		var JsonRequestTypeEditData=angular.copy(RequestTypeEditData);
		processReqFactory.processReq(baseURL.IP+"/admin/"+RequestTypeEditData.id+"/edit","POST",JsonRequestTypeEditData,function(response){
			swal({  title: "SUCCESS!",   text: "RequestTypes EDITED SUCCESSFULLY",   type: "success",   confirmButtonText: "Success" });
       location.path('RequestTypes/RequestTypeEditTemplate')
       },function(){
       	swal({ 	title: "ERROR!",   text: "RequestTypes NOT EDITED",   type: "warning",   confirmButtonText: "OK" });
        });
 		}
 		$scope.cancel=function(){
		$modalInstance.close();
	}
}

 function MerchantCreationController($scope,$state,$http,processReqFactory,baseURL){
	 $scope.ImageUpload=function(all){
			$scope.imgObj={
		   "imageName":all.files[0].name,
		   "imageType" : all.files[0].type,
		   "imageFileData":all.files[0]
		  }
		}

		$scope.merchantCreate=function(data,indexValue){
	   // var exdata = $("#image"+indexValue).attr('src');
	  // loadingView.startLoading("show");
	   // $scope.bytes = $scope.imgObj.imageFileData;
	    var imageType=$scope.imgObj.imageType;
	    var imgType=imageType.substring(0,5);
	   //  console.log($scope.bytes);
	   //  console.log(all.name);
	    var formData = new FormData();
	    // formData.append('file', $scope.bytes);
	    formData.append('merchantImage', $scope.imgObj.imageFileData);
	    formData.append('merchantName', data.merchantName);
	    formData.append('affiliateId', data.affiliateId);
	    formData.append('merchantToken', data.merchantToken);
	    formData.append('url', data.url);
	    formData.append('description', data.description);
	    // formData.append('merchantImage', data.imageAlt);
	    formData.append('status', data.status);
	    // console.log(formData);
	    if(imgType == "image"){
	       $.ajax({
	       type: "POST",
	       url: baseURL.IP+'/merchantForm/create',
	       // beforeSend: function(req) {
	       //   req.setRequestHeader("Content-Type", "multipart/form-data");
	       // },
	       data:formData,
	       processData: false,
	       contentType: false,
	       success: function(response) {
	           //alert(response);
	           // console.log(JSON.stringify(response));
	           
	           console.log(response);
	           	swal({   title: "SUCCESS!",   text: "MERCHANT CREATED SUCCESSFULLY",   type: "success",   confirmButtonText: "Success" });
							$state.go('Merchants.Merchant');
							},function(){
							swal({title: "ERROR!",   text: "MERCHANT NOT CREATED",   type: "warning",   confirmButtonText: "OK" });

	           // if(response.postName[0]=="E"){
	           //  var errorMsg = response.postName.split(":::")
	           //  $scope.errorAlert = errorMsg[1];
	           //  loadingView.startLoading("hide");
	           // }else{
	           //  $scope.errorAlert = '';
	           //  $scope.ImageData.push(response);
	           //  loadingView.startLoading("hide");
	           //  $scope.$apply();
	           // }
	       },
	       error:function(){
	           alert("failure");
	       }
	     });
	    }else{
	      alert('Please Upload Image');
	    }
	  }

	// 	$scope.merchantCreate=function(merchant){
	// 		$scope.merchantData=angular.copy(merchant);
	// 		$scope.merchantData['merchantImage']="image";
	// 		$scope.merchantData['merchantToken']="123456231231";
	// 	console.log(JSON.stringify($scope.merchantData));
	// 	processReqFactory.processReq(baseURL.IP+'/merchant/create','POST',$scope.merchantData,function(){
	// 	swal({   title: "SUCCESS!",   text: "MERCHANT CREATED SUCCESSFULLY",   type: "success",   confirmButtonText: "Success" });
	// 	$state.go('Merchants.Merchant');
	// 	},function(){
	// 	swal({title: "ERROR!",   text: "MERCHANT NOT CREATED",   type: "warning",   confirmButtonText: "OK" });
	// 	});
	// }
}

function MerchantController($scope,$rootScope,baseURL,processReqFactory){
	processReqFactory.processReq(baseURL.IP+'/merchant/all','GET','',function(Data){
		$scope.merchantDetails=Data;

		// console.log(JSON.stringify($scope.singleUserDetails));
	},function(){});
		
}

function SingleMerchantController($scope,$http,processReqFactory,baseURL,$stateParams){

		$scope.singleMerchantCategoryDetails=[];
		processReqFactory.processReq(baseURL.IP+'/merchant/'+$stateParams.merchantId,'GET','',function(Data){
		$scope.singleMerchantDetails=Data;
		// $scope.merchantCategory = {};
		$.each(Data._embedded.merchantCategory,function(k,v){
			$scope.singleMerchantCategoryDetails[k] = {};
			$scope.singleMerchantCategoryDetails[k]['categoryName']=v.categoryName;
			$scope.singleMerchantCategoryDetails[k]['merchantCategoryCode']=v.merchantCategoryCode;
			$scope.singleMerchantCategoryDetails[k]['marginDetails']=[];
			$.each(v._embedded.merchantCategoryMargin,function(k1,v1){
					$scope.singleMerchantCategoryDetails[k]['marginDetails'].push(v1);
			})
		});
		console.log(JSON.stringify($scope.singleMerchantCategoryDetails));
	},function(){});

}

function MerchantCategoryCreationController($scope,$state,$http,processReqFactory,baseURL){
	$http({
    method: "GET",
    url: baseURL.IP+'/merchant/all',
    data: '',
    headers: {
           'Content-Type': "application/json",
           'Accept': "application/json"
        }
      }).success(function(data) {
       $scope.merchantDetails = data;
     }).error(function(error){
        $scope.error = error;
     });
			$scope.MerchantCategoryCreate=function(jsonData){
		var JsonUserData=angular.copy(jsonData);
		// JsonUserData['categoryName']="clothes"

		console.log(JSON.stringify(JsonUserData));
		processReqFactory.processReq(baseURL.IP+'/merchantCategory/create','POST',JsonUserData,function(){
		swal({   title: "SUCCESS!",  text: "MERCHANTCATEGORY CREATED SUCCESSFULLY",   type: "success",   confirmButtonText: "Success" });
		$state.go('')
		},function(){
		swal({   title: "ERROR!",  text: "MERCHANTCATEGORY NOT CREATED",   type: "warning",   confirmButtonText: "OK" });
		});
	}
}

function MerchantCategoryController($scope,$http,processReqFactory,baseURL,dataTablesInitService){
	processReqFactory.processReq(baseURL.IP+'/merchantCategory/all', 'GET', '', function(Data){
		$scope.merchantCategoryData=Data;
		var columns = [
			{ "data": "merchantCategoryCode"},
			{ "data": "categoryName"},
			{ "data": "merchantCategoryCount"},
			{ "data": "merchantCategoryId",
			
			"render": function(data,type,row,meta) {
				var all = '<a href="#/Merchants/SingleMerchantCategory/'+data+'">View</a>'
				return all;
				}
			}
		];
		dataTablesInitService.initDataTables(Data,columns,'#merchantCategoryTable');

	},function(){});

	}

	function SingleMerchantCategoryController($scope,$http,processReqFactory,baseURL,$stateParams){
		processReqFactory.processReq(baseURL.IP+'/merchantCategory/'+$stateParams.merchantCategoryId,'GET','',function(Data){
		$scope.singleMerchantCategoryDetails=Data;

		console.log(JSON.stringify($scope.singleMerchantCategoryDetails));
	},function(){});
}

function MerchantCategoryMarginCreationController($scope,$http,$state,processReqFactory,baseURL){
	$http({
    method: "GET",
    url: baseURL.IP+'/merchantCategory/all',
    data: '',
    headers: {
           'Content-Type': "application/json",
           'Accept': "application/json"
        }
      }).success(function(data) {
       $scope.merchantCategoryDetails = data;
     }).error(function(error){
        $scope.error = error;
     });
		$scope.merchantCategoryMarginCreate=function(jsonData){
		var JsonUserData=angular.copy(jsonData);
		// JsonUserData['categoryName']="clothes"

		console.log(JSON.stringify(JsonUserData));
		processReqFactory.processReq(baseURL.IP+'/merchantCategoryMargin/create','POST',JsonUserData,function(){
		swal({   title: "SUCCESS!",  text: "MERCHANTCATEGORYMARGIN CREATED SUCCESSFULLY",   type: "success",   confirmButtonText: "Success" });
		$state.go('')
		},function(){
		swal({   title: "ERROR!",  text: "MERCHANTCATEGORYMARGIN NOT CREATED",   type: "warning",   confirmButtonText: "OK" });
		});
	}
}

function MerchantCategoryMarginController($scope,$http,processReqFactory,baseURL,dataTablesInitService){
	processReqFactory.processReq(baseURL.IP+'/merchantCategoryMargin/all', 'GET', '', function(Data){
		$scope.merchantCategoryMarginData=Data;
		var columns = [
			{ "data": "customerType"},
			{ "data": "channel"},
			{ "data": "commission"},
			{ "data": "commissionType"},
			{ "data": "dealmachaCommission"},
			{ "data": "dealmachaCommissionType"},
			{ "data": "merchantCategoryId",
			
			"render": function(data,type,row,meta) {
				var all = '<a href="#/Merchants/SingleMerchantCategoryMargin/'+data+'">View</a>'
				return all;
				}
			}
		];
		dataTablesInitService.initDataTables(Data,columns,'#merchantCategoryMarginTable');

	},function(){});

	}

	function SingleMerchantCategoryMarginController($scope,processReqFactory,baseURL,$stateParams){
		processReqFactory.processReq(baseURL.IP+'/merchantCategory/'+$stateParams.merchantCategoryId,'GET','',function(Data){
		 	$scope.singleMerchantCategoryMarginDetails=Data;

		console.log(JSON.stringify($scope.singleMerchantCategoryMarginDetails));
	},function(){});
		 // });
	}


function TransactionController($scope,processReqFactory,baseURL,dataTablesInitService){
	processReqFactory.processReq(baseURL.IP+'/transaction/all', 'GET', '', function(Data){
		$scope.transactionData=Data;
		var columns = [
			{ "data": "userName"},
			{ "data": "code"},
			{ "data": "createdDate"},
			{ "data": "source"},
			{ "data": "orderStatus"},
			{ "data": "transactionId",
			"render": function(data,type,row,meta) {
				var all = '<a href="#/Transactions/SingleTransaction/'+data+'">View</a>'
				return all;
				}
			}
		];
		dataTablesInitService.initDataTables(Data,columns,'#transactionTable');
			// console.log(JSON.stringify($scope.transactionData));

		// loadingView.startLoading("hide");
	},function(){});

	}
function SingleTransactionController($scope,$http,processReqFactory,baseURL,$stateParams){
	processReqFactory.processReq(baseURL.IP+'/transaction/'+$stateParams.transactionId,'GET','',function(Data){
		$scope.singleTransactionDetails=Data;

		console.log(JSON.stringify($scope.singleTransactionDetails));
	},function(){});
 }


function OffersCreationController($scope,$state,processReqFactory,baseURL){
	$scope.OffersCreate=function(jsonData){
		var JsonUserData=angular.copy(jsonData);
		JsonUserData['offerName']="Offer",
		JsonUserData['offerStatus']="ACTIVE",

		console.log(JSON.stringify(JsonUserData));
		processReqFactory.processReq(baseURL.IP+'/offers/create','POST',JsonUserData,function(){
		swal({   title: "SUCCESS!",  text: "OFFERS CREATED SUCCESSFULLY",   type: "success",   confirmButtonText: "Success" });
		$state.go('Offers.Offer')
		},function(){
		swal({   title: "ERROR!",  text: "OFFERS NOT CREATED",   type: "warning",   confirmButtonText: "OK" });
		});
	}
	
}
function OffersController($scope,$http,processReqFactory,baseURL,dataTablesInitService){
	processReqFactory.processReq(baseURL.IP+'/offers/all', 'GET', '', function(Data){
		$scope.offerData=Data;
		var columns = [
			{ "data": "offerName"},
			{ "data": "offerCommissionAmountType"},
			{ "data": "offerSource"},
			{ "data": "offerCommissionAmount"},
			{ "data": "offerStartDate"},
			{ "data": "offerEndDate"},
			{ "data": "offerStatus"},
			{ "data": "offersId",
			
			"render": function(data,type,row,meta) {
				var all = '<a href="#/Offers/SingleOffer/'+data+'">View</a>'
				return all;
				}
			}
		];
		dataTablesInitService.initDataTables(Data,columns,'#offersTable');

	},function(){});

	}

 function SingleOfferController($scope,$rootScope,$http,$modal,processReqFactory,baseURL,$stateParams){
	processReqFactory.processReq(baseURL.IP+'/offers/'+$stateParams.offerId,'GET','',function(Data){
		$scope.singleOfferDetails=Data;

		console.log(JSON.stringify($scope.singleOfferDetails));
	},function(){});
 }

 function CashbackController($scope,$http,processReqFactory,baseURL,dataTablesInitService){
		processReqFactory.processReq(baseURL.IP+'/cashbackTransaction/all','GET','',function(Data){
			$scope.cashbackTransactionData=Data;
		 var columns = [
			{ "data": "date"},
			{ "data": "userName"},
			{ "data": "transactionType",
				"orderable": true,
		    "searchable": true,
		    "render": function(data,type,row,meta) {
		    	if(data=="CREDIT"){
		    		var js = '<span class="text-info">'+data+'</span>'
		    		return js;
		    	}else{
		    		var js = '<span class="text-warning">'+data+'</span>'
		    		return js;
		    	}
				}
			},
			{ "data": "cashbackTransactionId",
				 "orderable": false,
		      "searchable": false,
			"render": function(data,type,row,meta) {
				var all = '<a href="#/Cashbacks/SingleCashbackTransaction/'+data+'">View</a>'
				return all;
				}
			}
		];
		dataTablesInitService.initDataTables(Data,columns,'#cashbackTable');
		// console.log(JSON.stringify($scope.cashbackTransactionData));
	},function(){});
 }

 function SingleCashbackController($scope,$rootScope,$http,$modal,processReqFactory,baseURL,$stateParams){
	processReqFactory.processReq(baseURL.IP+'/cashbackTransaction/'+$stateParams.cashbackTransactionId,'GET','',function(Data){
		$scope.singleCashbackTransactionDetails=Data;

		console.log(JSON.stringify($scope.singleCashbackTransactionDetails));
	},function(){});
 }


function CmsController($scope,$rootScope,dataTablesInitService,baseURL,processReqFactory,loadingView,$parse,notify){
		$scope.MAINBANNERBLOCK1 = true;
		$scope.MAINBANNERBLOCK2 = true;
		$scope.MAINBANNERBLOCK3 = true;
		$scope.MAINBANNERBLOCK4 = true;
		$scope.MAINBANNERBLOCK5 = true;
		$scope.MAINBANNERBLOCK6 = true;
		$scope.MAINBANNERBLOCK7 = true;
	$scope.ImageIndividualData={};
	$scope.ImageData=[];
	processReqFactory.processReq(baseURL.IP+'/cmsSection/sectionName/MAINBANNERSECTION','GET','',function(Data){
		var imageSampleData = Data._embedded.cmsBlock[0]._embedded.cmsPosts;
		
		$.each(imageSampleData,function(k,v){
			$scope.ImageIndividualData['imgData'+v.orderOfPlace] = {};
			$scope.ImageData.push(v);
			$scope[v.cmsBlockName+v.orderOfPlace] = false;
			$('#image'+v.orderOfPlace).attr('src', v.postsImageUrl);
			$scope.ImageIndividualData['imgData'+v.orderOfPlace]['imageAlt'] = v.postsImageAlt;
			$scope.ImageIndividualData['imgData'+v.orderOfPlace]['imageLocation'] = v.url;
			$scope.ImageIndividualData['imgData'+v.orderOfPlace]['postsImageId'] = v.postsImageId;
			$scope.ImageIndividualData['imgData'+v.orderOfPlace]['cmsPostsId'] = v.cmsPostsId;
			$('.image'+v.orderOfPlace).addClass('hide');
			// $scope.$apply();

		})
	},function(){});

	// Main slider Upload
	$scope.fileNameChanged=function(all,index){
		$scope.imgObj={
			"imageName":all.files[0].name,
			"imageType" : all.files[0].type,
			"imageFileData":all.files[0]
		}
		console.log(all.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#image'+index).attr('src', e.target.result);
      $('.image'+index).addClass('hide');
    }
    reader.readAsDataURL(all.files[0]);
	
  }
  
  $scope.ImageUpload=function(data,indexValue){
  	// var exdata = $("#image"+indexValue).attr('src');
		loadingView.startLoading("show");
  	$scope.bytes = $scope.imgObj.imageFileData;
    var imageType=$scope.bytes.type;
    var imgType=imageType.substring(0,5);
   //  console.log($scope.bytes);
   //  console.log(all.name);
    var formData = new FormData();
    formData.append('file', $scope.bytes);
    formData.append('blockName', "MAINBANNERBLOCK");
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('imageAlt', data.imageAlt);
    // console.log(formData);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          //alert(response);
	          // console.log(JSON.stringify(response));
	          
	          // console.log($scope.ImageData);
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// var errorMsg = response.postName.split(":::")
	          	// $scope.errorAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	// $scope.errorAlert = errorMsg[1];
	          	loadingView.startLoading("hide");
	          }else{
	          	var successMsg = response.postName.split(":::")
	          	// $scope.errorAlert = errorMsg[1];
	          	notify({ message: successMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	$scope.ImageData.push(response);
	          	$scope[response.cmsBlockName+indexValue] = false;
	          	$scope.ImageIndividualData['imgData'+indexValue] = {};
	          	$('#image'+indexValue).attr('src', response.postsImageUrl);
							$scope.ImageIndividualData['imgData'+indexValue]['imageAlt'] = response.postsImageAlt;
							$scope.ImageIndividualData['imgData'+indexValue]['imageLocation'] = response.url;
							$scope.ImageIndividualData['imgData'+indexValue]['postsImageId'] = response.postsImageId;
							$scope.ImageIndividualData['imgData'+indexValue]['cmsPostsId'] = response.id;
		          loadingView.startLoading("hide");
		          $scope.$apply();
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }
  $scope.ImageDelete=function(bannerData,deleteIndex,index){
  	processReqFactory.processReq(baseURL.IP+'/delete/'+deleteIndex,'GET','',function(deleteData){
  		if($scope.ImageIndividualData['imgData'+index].cmsPostsId == deleteData.id){
  			delete $scope.ImageIndividualData['imgData'+index];
  		}
  		$scope.ImageData = $scope.ImageData.filter(function (el) {
  			if(el.id){
  				return el.id !== deleteData.id;
  			}else{
  				return el.cmsPostsId !== deleteData.id;
  			}
          
     	});
  		$scope[deleteData.cmsBlockName+index] = true;
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	$('#image'+index).attr('src', deleteData.postsImageUrl);
      	$('.image'+index).removeClass('hide');
  	},function(){});
  }

// Promotion Images Upload

	$scope.FOURGRIDPROMOTIONBLOCK1=true;
	$scope.FOURGRIDPROMOTIONBLOCK2=true;
	$scope.FOURGRIDPROMOTIONBLOCK3=true;
	$scope.FOURGRIDPROMOTIONBLOCK4=true;
	$scope.PromotionData=[];
	$scope.PromotionIndividualData={};
  processReqFactory.processReq(baseURL.IP+'/cmsSection/sectionName/FOURGRIDPROMOTIONPAGESECTION','GET','',function(Data){
		var promoSampleData = Data._embedded.cmsBlock[0]._embedded.cmsPosts;
		$.each(promoSampleData,function(k,v){
			$scope.PromotionIndividualData['PromoData'+v.orderOfPlace] = {};
			$scope.PromotionData.push(v);
			$scope[v.cmsBlockName+v.orderOfPlace] = false;
			$('#promo'+v.orderOfPlace).attr('src', v.postsImageUrl);
			$scope.PromotionIndividualData['PromoData'+v.orderOfPlace]['imageAlt']=v.postsImageAlt;
			$scope.PromotionIndividualData['PromoData'+v.orderOfPlace]['imageLocation']=v.url;
			$scope.PromotionIndividualData['PromoData'+v.orderOfPlace]['cmsPostsId']=v.cmsPostsId;
			$('.promo'+v.orderOfPlace).attr('class','hide');
			// $scope.$apply();
		})
		
		// console.log($scope.PromotionData);
	},function(){});

  // Four Grid Promotion Upload
	$scope.filePromotionChanged=function(all,index){
		$scope.promoObj={
			"imageName":all.files[0].name,
			"imageType" : all.files[0].type,
			"imageFileData":all.files[0]
		}
		console.log(all.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#promo'+index).attr('src', e.target.result);
      $('.promo'+index).attr('class','hide');
    }
    reader.readAsDataURL(all.files[0]);
	
  }
  
  $scope.PromotionUpload=function(data,indexValue){
  	// var exdata = $("#image"+indexValue).attr('src');
	loadingView.startLoading("show");
  	$scope.promo = $scope.promoObj.imageFileData;
    var imageType=$scope.promo.type;
    var imgType=imageType.substring(0,5);
   //  console.log($scope.bytes);
   //  console.log(all.name);
    var formData = new FormData();
    formData.append('file', $scope.promo);
    formData.append('blockName', "FOURGRIDPROMOTIONBLOCK");
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('imageAlt', 'MainPromotion');
    // console.log(formData);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          //alert(response);
	          // console.log(JSON.stringify(response));
	          
	          // $scope.$apply();
	          // console.log($scope.ImageData);
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.errorAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	var sucessMsg = response.postName.split(":::")
	          	// $scope.errorAlert = sucessMsg[1];
	          	notify({ message: sucessMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	$scope.PromotionData.push(response);
	          	$scope[response.cmsBlockName+indexValue] = false;
	          	$scope.PromotionIndividualData['PromoData'+indexValue] = {};
	          	$('#promo'+indexValue).attr('src', response.postsImageUrl);
							$scope.PromotionIndividualData['PromoData'+indexValue]['imageAlt'] = response.postsImageAlt;
							$scope.PromotionIndividualData['PromoData'+indexValue]['imageLocation'] = response.url;
							$scope.PromotionIndividualData['PromoData'+indexValue]['postsImageId'] = response.postsImageId;
							$scope.PromotionIndividualData['PromoData'+indexValue]['cmsPostsId'] = response.id;
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }

  $scope.PromotionDelete=function(promotionData,index){
  	processReqFactory.processReq(baseURL.IP+'/delete/'+promotionData.cmsPostsId,'GET','',function(deleteData){
  		if($scope.PromotionIndividualData['PromoData'+index].cmsPostsId == deleteData.id){
  			delete $scope.PromotionIndividualData['PromoData'+index];
  		}
  		$scope.PromotionData = $scope.PromotionData.filter(function (el) {
  			if(el.id){
  				return el.id !== deleteData.id;
  			}else{
  				return el.cmsPostsId !== deleteData.id;
  			}
          
     	});
  		$scope[deleteData.cmsBlockName+index] = true;
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	$('#promo'+index).attr('src', deleteData.postsImageUrl);
      	$('.promo'+index).removeClass('hide');
  	},function(){});
  }

  // First Page Left Section Block
  $scope.leftSectionBlockData=[];
  $scope.leftTitleData = {};
  processReqFactory.processReq(baseURL.IP+'/cmsSection/sectionName/FIRSTPAGELEFTSIDESECTION','GET','',function(Data){
		$scope.leftTitleData['sectionTitle'] = Data.sectionTitle;
		$scope.leftTitleData['cmsSectionId'] = Data.cmsSectionId;
		if($scope.leftTitleData['sectionTitle']){
			$scope.leftSectionShow=false;
		}
		
		if(Data._embedded.cmsBlock[0]._embedded){
		var leftSectionData = Data._embedded.cmsBlock[0]._embedded.cmsPosts;
		$.each(leftSectionData,function(k,v){
			$scope.leftSectionBlockData.push(v);
		})
		// console.log($scope.leftSectionBlockData);
		}
		
	},function(){});

	 	$scope.leftSectionShow=true;
$scope.leftPageSectionTitle = function(titleData,sectionName){
 		loadingView.startLoading("show");
    var jsonfileData= {
		"sectionName":sectionName,
		"sectionTitle":titleData.sectionTitle
		}
		console.log(JSON.stringify(jsonfileData))
    // if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/cmsSection/sectionName/'+sectionName+'/edit',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	        req.setRequestHeader("Content-Type", "application/json");
	      },
	      data: JSON.stringify(jsonfileData),
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          	loadingView.startLoading("hide");
	          	notify({ message: 'Updated SUCCESSFULLY', classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.leftSectionShow=false;
	          	$scope.$apply();
	      },
	      error:function(){
	          alert("failure");
	          notify({ message: "Cannot Updated", classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	      }
	    });
 	}
 	$scope.leftPageSectionEdit=function(){

 	}

 	$scope.leftSectionImageRemove=function(leftSectionData){
 		var imageId;
 		if(leftSectionData.id){
 			imageId = leftSectionData.id;
 		}else{
 			imageId = leftSectionData.cmsPostsId;
 		}
 		processReqFactory.processReq(baseURL.IP+'/delete/'+imageId,'GET','',function(deleteData){
  		$scope.leftSectionBlockData = $scope.leftSectionBlockData.filter(function (el) {
  			if(el.id){
  				return el.id !== deleteData.id;
  			}else{
  				return el.cmsPostsId !== deleteData.id;
  			}
     	});
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	
  	},function(){});
 	}

$scope.leftSectionData={};
  $scope.fileLeftPageChanged=function(fileData,index){
  	$scope.leftObj={
			"leftSectionName":fileData.files[0].name,
			"leftSectionType" : fileData.files[0].type,
			"leftSectionFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#leftPage'+index).attr('src', e.target.result);
      $('.leftPage'+index).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
    // FIRSTPAGELEFTSECTIONLEFTBLOCK
  }


  $scope.sampleDate={};
  // $scope.changeDate=function(date){
  // 	console.log(date)
  // }
  $scope.LeftSectionUpload=function(data,indexValue){
  	var selectedDate = $scope.sampleDate.postExpiryTime;
  	var NewSelectedDate = new Date(selectedDate);
  	var mon = NewSelectedDate.getMonth()+1;
  	var getMonth = NewSelectedDate.getMonth() < 10 ? '0' + mon : mon;
  	var getDate = NewSelectedDate.getDate() < 10 ? '0' + NewSelectedDate.getDate() : NewSelectedDate.getDate();
  	var hours = NewSelectedDate.getHours() < 10 ? '0' + NewSelectedDate.getHours() : NewSelectedDate.getHours();
  	var min = NewSelectedDate.getMinutes() < 10 ? '0' + NewSelectedDate.getMinutes() : NewSelectedDate.getMinutes();
  	var sec = NewSelectedDate.getSeconds() < 10 ? '0' + NewSelectedDate.getSeconds() : NewSelectedDate.getSeconds();
  	var postExpiryTime = NewSelectedDate.getFullYear()+'-'+getMonth+'-'+getDate+' '+hours+':'+min+':'+sec
  	// console.log(postExpiryTime)
	loadingView.startLoading("show");
  	$scope.leftSection = $scope.leftObj.leftSectionFileData;
    var imageType=$scope.leftObj.leftSectionType;
    var imgType=imageType.substring(0,5);
    // console.log(JSON.stringify(data));
   //  console.log(all.name);
    var formData = new FormData();
    formData.append('file', $scope.leftSection);
    formData.append('blockName', "FIRSTPAGELEFTSECTIONLEFTBLOCK");
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('imageAlt', data.imageAlt);
    formData.append('postsProductTitle', data.postsProductTitle);
    formData.append('postsProductDiscountedPrice', data.postsProductDiscountedPrice);
    formData.append('postsProductPrice', data.postsProductPrice);
    formData.append('postExpiryTime', postExpiryTime);

    // console.log(formData);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	$scope.leftSectionAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	var successMsg = response.postName.split(":::")
	          	notify({ message: successMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.leftSectionBlockData.push(response);
	          	loadingView.startLoading("hide");
	          	$scope.leftSectionData= {
	          		postsProductTitle:'',
	          		postsProductDiscountedPrice:'',
	          		postsProductPrice:'',
	          		imageAlt:'',
	          		imageLocation:''
	          	};
	          	$scope.sampleDate.postExpiryTime = ''	;
	          	$('#leftPage1').attr('src', '');  
	          	$('#postItem').load();
	          	$scope.$apply();       
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }

  // FIRSTPAGERIGHTSIDESECTION

  // First Page Right Section Block
  $scope.rightSectionBlockData=[];
  $scope.rightTitleData = {};
  processReqFactory.processReq(baseURL.IP+'/cmsSection/sectionName/FIRSTPAGERIGHTSIDESECTION','GET','',function(Data){
		$scope.rightTitleData['sectionTitle'] = Data.sectionTitle;
		$scope.rightTitleData['cmsSectionId'] = Data.cmsSectionId;
		if ($scope.rightTitleData['sectionTitle']) {
				$scope.rightSectionShow=false;
		};
		if(Data._embedded.cmsBlock[0]._embedded){
		var rightSectionData = Data._embedded.cmsBlock[0]._embedded.cmsPosts;
		$.each(rightSectionData,function(k,v){
			$scope.rightSectionBlockData.push(v);
		})
		// console.log($scope.rightSectionBlockData);
		}
		
	},function(){});

	 	$scope.rightSectionShow=true;
$scope.rightPageSectionTitle = function(titleData,sectionName){
 		loadingView.startLoading("show");
    var jsonfileData= {
		"sectionName":sectionName,
		"sectionTitle":titleData.sectionTitle
		}
		console.log(JSON.stringify(jsonfileData))
    // if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/cmsSection/sectionName/'+sectionName+'/edit',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	        req.setRequestHeader("Content-Type", "application/json");
	      },
	      data: JSON.stringify(jsonfileData),
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          	loadingView.startLoading("hide");
	          	notify({ message: 'Updated SUCCESSFULLY', classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.rightSectionShow=false;
	          	$scope.$apply();
	      },
	      error:function(){
	          alert("failure");
	          notify({ message: "Cannot Updated", classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	      }
	    });
 	}
 	$scope.rightPageSectionEdit=function(){

 	}

 	$scope.rightSectionImageRemove=function(rightSectionData){
 		var imageId;
 		if(rightSectionData.id){
 			imageId = rightSectionData.id;
 		}else{
 			imageId = rightSectionData.cmsPostsId;
 		}
 		processReqFactory.processReq(baseURL.IP+'/delete/'+imageId,'GET','',function(deleteData){
  		$scope.rightSectionBlockData = $scope.rightSectionBlockData.filter(function (el) {
  			if(el.id){
  				return el.id !== deleteData.id;
  			}else{
  				return el.cmsPostsId !== deleteData.id;
  			}
     	});
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	
  	},function(){});
 	}
	$scope.rightSectionData={};
  $scope.fileRightPageChanged=function(fileData,index){
  	$scope.rightObj={
			"rightSectionName":fileData.files[0].name,
			"rightSectionType" : fileData.files[0].type,
			"rightSectionFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#rightPage'+index).attr('src', e.target.result);
      $('.rightPage'+index).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
  }
  $scope.RightSectionUpload=function(data,indexValue){
  	loadingView.startLoading("show");
  	$scope.rightSection = $scope.rightObj.rightSectionFileData;
    var imageType=$scope.rightObj.rightSectionType;
    var imgType=imageType.substring(0,5);
    // console.log(JSON.stringify(data));
   //  console.log(all.name);
    var formData = new FormData();
    formData.append('file', $scope.rightSection);
    formData.append('blockName', "FIRSTPAGERIGHTSECTIONRIGHTBLOCK");
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('imageAlt', data.imageAlt);
    formData.append('postsProductTitle', data.postsProductTitle);
    formData.append('postsProductDiscountedPrice', data.postsProductDiscountedPrice);
    formData.append('postsProductPrice', data.postsProductPrice);
    // formData.append('postExpiryTime', postExpiryTime);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	$scope.rightSectionAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	var successMsg = response.postName.split(":::")
	          	// $scope.rightSectionAlert = errorMsg[1];
	          	notify({ message: successMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.rightSectionBlockData.push(response);
	          	loadingView.startLoading("hide");
	          	$scope.rightSectionData= {
	          		postsProductTitle:'',
	          		postsProductDiscountedPrice:'',
	          		postsProductPrice:'',
	          		imageAlt:'',
	          		imageLocation:''
	          	};
	          	$('#rightPage1').attr('src', '');  
	          	// $('#postItem').load();    
	          	$scope.$apply();   
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }

// FIRST PROMOTION PAGE SECTION BANNERBLOCK

$scope.firstBannerData={};
	$scope.FirstBannerShow=true;
processReqFactory.processReq(baseURL.IP+'/cmsSection/sectionName/FIRSTPROMOTIONPAGESECTION','GET','',function(Data){
		if(Data._embedded.cmsBlock[0]._embedded){
		var firstBannerSectionData = Data._embedded.cmsBlock[0]._embedded.cmsPosts;
		$.each(firstBannerSectionData,function(k,v){
			$scope.firstBannerBlockData = v;
			$('#first_banner1').attr('src', v.postsImageUrl); 
			$scope.firstBannerData['imageAlt']=v.postsImageAlt;
			$scope.firstBannerData['imageLocation']=v.url;
			$scope.firstBannerData['cmsPostsId']=v.cmsPostsId;
			$('.first_banner1').attr('class','hide');
			$scope.FirstBannerShow=false;
		})
		// console.log($scope.firstBannerBlockData);
		}
		
	},function(){});
   $scope.filePromotionMainBannerChanged=function(fileData,index){
  	$scope.firstBannerObj={
			"firstBannerName":fileData.files[0].name,
			"firstBannerType" : fileData.files[0].type,
			"firstBannerFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#first_banner'+index).attr('src', e.target.result);
      $('.first_banner'+index).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
    // FIRSTPAGELEFTSECTIONLEFTBLOCK
  }
  $scope.FirstPromotionBannerUpload=function(data,indexValue){
  	loadingView.startLoading("show");
  	$scope.firstBanner = $scope.firstBannerObj.firstBannerFileData;
    var imageType=$scope.firstBannerObj.firstBannerType;
    var imgType=imageType.substring(0,5);

  	var formData = new FormData();
  	formData.append('file', $scope.firstBanner);
  	formData.append('blockName', "FIRSTPROMOTIONPAGESECTIONBANNERBLOCK");
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('imageAlt', data.imageAlt);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	$scope.firstBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	$scope.FirstBannerShow = false;
	          	// $scope.firstBannerData= {
	          	// 	imageAlt:'',
	          	// 	imageLocation:''
	          	// };
	          	// $('#first_banner1').attr('src', response.postsImageUrl);  
	          	$('#first_banner1').attr('src', response.postsImageUrl); 
							$scope.firstBannerData['imageAlt']=response.postsImageAlt;
							$scope.firstBannerData['imageLocation']=response.url;
							$scope.firstBannerData['cmsPostsId']=response.id;
							$('.first_banner1').attr('class','hide');
							$scope.$apply();
	          	// $('#postItem').load();       
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }
  $scope.FirstPromotionBannerDelete=function(bannerData,index){
  	processReqFactory.processReq(baseURL.IP+'/delete/'+bannerData.cmsPostsId,'GET','',function(deleteData){
  		if($scope.firstBannerData.cmsPostsId == deleteData.id){
  			delete $scope.firstBannerData;
  		}
  		$scope.FirstBannerShow = true;
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	$('#first_banner'+index).attr('src', deleteData.postsImageUrl);
      	$('.first_banner'+index).removeClass('hide');
  	},function(){});
  }

  // Second PROMOTION PAGE SECTION BANNERBLOCK

$scope.secondBannerData={};
	
	$scope.SecondBannerShow=true;
processReqFactory.processReq(baseURL.IP+'/cmsSection/sectionName/SECONDPROMOTIONPAGESECTION','GET','',function(Data){
		if(Data._embedded.cmsBlock[0]._embedded){
		var secondBannerSectionData = Data._embedded.cmsBlock[0]._embedded.cmsPosts;
		$.each(secondBannerSectionData,function(k,v){
			$scope.secondBannerBlockData = v;
			$('#second_banner1').attr('src', v.postsImageUrl); 
			$scope.secondBannerData['imageAlt']=v.postsImageAlt;
			$scope.secondBannerData['imageLocation']=v.url;
			$scope.secondBannerData['cmsPostsId']= v.cmsPostsId;
			$('.second_banner1').attr('class','hide');
			$scope.SecondBannerShow=false;
		})
		// console.log($scope.secondBannerBlockData);
		}
		
	},function(){});
   $scope.filePromotionSecondBannerChanged=function(fileData,index){
  	$scope.secondBannerObj={
			"secondBannerName":fileData.files[0].name,
			"secondBannerType" : fileData.files[0].type,
			"secondBannerFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#second_banner'+index).attr('src', e.target.result);
      $('.second_banner'+index).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
    // FIRSTPAGELEFTSECTIONLEFTBLOCK
  }
  $scope.SecondPromotionBannerUpload=function(data,indexValue){
  	loadingView.startLoading("show");
  	$scope.secondBanner = $scope.secondBannerObj.secondBannerFileData;
    var imageType=$scope.secondBannerObj.secondBannerType;
    var imgType=imageType.substring(0,5);

  	var formData = new FormData();
  	formData.append('file', $scope.secondBanner);
  	formData.append('blockName', "SECONDPROMOTIONPAGESECTIONBANNERBLOCK");
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('imageAlt', data.imageAlt);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	var sucessMsg = response.postName.split(":::");
    					notify({ message: sucessMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	
	          	$scope.secondBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	$scope.SecondBannerShow = false; 
	          	$('#second_banner1').attr('src', response.postsImageUrl); 
							$scope.secondBannerData['imageAlt']=response.postsImageAlt;
							$scope.secondBannerData['imageLocation']=response.url;
							$scope.secondBannerData['cmsPostsId']=response.id;
							$('.second_banner1').attr('class','hide');
							$scope.$apply(); 
	          	// $('#postItem').load();       
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }

  $scope.SecondPromotionBannerDelete=function(bannerData,index){
  	processReqFactory.processReq(baseURL.IP+'/delete/'+bannerData.cmsPostsId,'GET','',function(deleteData){
  		if($scope.secondBannerData.cmsPostsId == deleteData.id){
  			delete $scope.secondBannerData;
  		}
  		$scope.SecondBannerShow = true;
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	$('#second_banner1'+index).attr('src', deleteData.postsImageUrl);
      	$('.second_banner1'+index).removeClass('hide');
  	},function(){});
  }


//  SECOND PAGE SECTION 
  // $scope.secondPageBlock1 = true;
  // $scope.secondPageBlock2 = true;
  	$scope.shortImageData1 = {};
  	$scope.middleImageData1 = {};
  	$scope.shortImageBlock1=true;
  	$scope.middleImageBlock1 = true;
  	$scope.categoryMenuData = {};
  	$scope.categoryMenuData['categoryButShow1']=true;
  	$scope.categoryMenuData['categoryButShow2']=true;
  	$scope.categoryMenuData['categoryButShow3']=true;
  	$scope.categoryMenuData['categoryButShow4']=true;
  	$scope.categoryMenuData['categoryButShow5']=true;
  	$scope.tabsMenuData = {};
  	$scope.ChangeableTabs = {};
  	$scope.tabsMenuData['tab4']=true;
  	$scope.tabsMenuData['tab5']=true;
  	$scope.tabsMenuData['tab6']=true;
  	// $scope.ChangeableTabs['secondPageTab1']={};
  	// $scope.tabsMenuData['secondPageTab2']={};
  	$scope.IndividualTabs={};
  	$scope.IndividualTabs2={};
  	$scope.IndividualTabs3={};
  	$scope.categoryJsonData=[];
  	$scope.FirstTabBlockJsonData = [];
  	$scope.TabsBlockJsonData = [];
  	$scope.secondTitleData={};
  	for(var i=3;i<7;i++){
  		for(var j=0;j<6;j++){
  			$scope.ChangeableTabs['showTabs'+i+j]=true;
  		}
  	}
processReqFactory.processReq(baseURL.IP+'/cmsSection/sectionName/SECONDPAGESECTION','GET','',function(Data){
	$scope.secondTitleData['sectionTitle'] = Data.sectionTitle;
	$.each(Data._embedded.cmsBlock,function(k,v){
		$scope.secondSectionShow = false;
		if(v.blockName=="SECONDPAGESECTIONFIRSTTABBLOCK" || v.blockName=="SECONDPAGESECTIONSECONDTABBLOCK" || v.blockName=="SECONDPAGESECTIONTHIRDTABBLOCK"){
			$scope.TabsBlockJsonData.push(Data._embedded.cmsBlock[k]);
			$scope.tabsMenuData['secondPageTab'+v.orderOfPlace] = {};
			$scope.tabsMenuData['secondPageTab'+v.orderOfPlace]['blockTitle'] = v.blockTitle;
			$scope.tabsMenuData['tab'+v.orderOfPlace]=false;
			// console.log(JSON.stringify($scope.TabsBlockJsonData))
		}
		if(Data._embedded.cmsBlock[k]._embedded){
		var secondPageSectionData = Data._embedded.cmsBlock[k]._embedded.cmsPosts;
			$.each(secondPageSectionData,function(k1,v1){
				$scope.secondPageBlockData = v1;
				if(v1.cmsBlockName=="SECONDPAGESECTIONSHORTBANNERBLOCK"){
					$('#secondShortImage1').attr('src', v1.postsImageUrl); 
					$('#secondShortImage2').attr('src', v1.postsImageUrl); 
					$scope.shortImageData1['imageAlt']=v1.postsImageAlt;
					$scope.shortImageData1['imageLocation']=v1.url;
					$scope.shortImageData1['cmsPostsId']=v1.cmsPostsId;
					$('.secondShortImage1').attr('class','hide');
					$scope.shortImageBlock1=false;
				}
				if(v1.cmsBlockName=="SECONDPAGESECTIONMIDDLEBANNERBLOCK"){
					$('#middleBanner1').attr('src', v1.postsImageUrl); 
					$('#middleBanner2').attr('src', v1.postsImageUrl); 
					$scope.middleImageData1['imageAlt']=v1.postsImageAlt;
					$scope.middleImageData1['imageLocation']=v1.url;
					$scope.middleImageData1['cmsPostsId']=v1.cmsPostsId;
					$('.middleBanner1').attr('class','hide');
					// middleBanner1
					$scope.middleImageBlock1 = false;
				}			
				if(v1.cmsBlockName=="SECONDPAGESECTIONCATEGORYMENUBLOCK"){
					$scope.categoryJsonData.push(v1);
					$scope.categoryMenuData['catBlockData'+v1.orderOfPlace] = {};
					$scope.categoryMenuData['catBlockData'+v1.orderOfPlace]['postTitle'] = v1.postTitle;
					$scope.categoryMenuData['catBlockData'+v1.orderOfPlace]['url'] = v1.url;
					$scope.categoryMenuData['catBlockData'+v1.orderOfPlace]['cmsPostsId'] = v1.cmsPostsId;
					$scope.categoryMenuData['categoryButShow'+v1.orderOfPlace]=false;
				}
				if(v1.cmsBlockName=="SECONDPAGESECTIONFIRSTTABBLOCK" || v1.cmsBlockName=="SECONDPAGESECTIONSECONDTABBLOCK" || v1.cmsBlockName=="SECONDPAGESECTIONTHIRDTABBLOCK"){
					// $scope.FirstTabBlockJsonData.push(v1);
					// $scope.ChangeableTabs['secondPageTab'+v1.orderOfPlace+k1]=true;
					// if(k1==0){
					// 	$scope.IndividualTabs['tabsData'+v1.orderOfPlace+k1] = {};
					// 	$scope.ChangeableTabs['showTabs'+v1.orderOfPlace+k1]=false;
					// 	// $('#tabImage'+v1.orderOfPlace+k1).attr('src', v1.postsImageUrl);
					// 	$scope.IndividualTabs['tabsData'+v1.orderOfPlace+k1]['postsImageUrl'] = v1.postsImageUrl;
					// 	// $('#tabImage10').attr('src', v1.postsImageUrl);
					// 	$scope.IndividualTabs['tabsData'+v1.orderOfPlace+k1]['imageLocation'] = v1.url;
					// 	$scope.IndividualTabs['tabsData'+v1.orderOfPlace+k1]['imageAlt'] = v1.postsImageAlt;
					// 	$scope.IndividualTabs['tabsData'+v1.orderOfPlace+k1]['postsProductDiscountedPrice'] = v1.postsProductDiscountedPrice;
					// 	$scope.IndividualTabs['tabsData'+v1.orderOfPlace+k1]['postsProductPrice'] = v1.postsProductPrice;
					// 	$('.tabImage'+v1.orderOfPlace+k1).attr('class','hide');
					// }else{
						$scope.IndividualTabs['tabsData'+v1.orderOfPlace] = {};
						$scope.ChangeableTabs['showTabs'+v1.orderOfPlace]=false;
						// $('#tabImage'+v1.orderOfPlace).attr('src', v1.postsImageUrl);
						$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['postsImageUrl'] = v1.postsImageUrl;
						// $('#tabImage10').attr('src', v1.postsImageUrl);
						$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['imageLocation'] = v1.url;
						$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['postsProductTitle'] = v1.postsProductTitle;
						$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['postsProductDiscountedPrice'] = v1.postsProductAfterPrice;
						$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['postsProductPrice'] = v1.postsProductPrice;
						$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['cmsPostsId'] = v1.cmsPostsId;
						$('.tabImage'+v1.orderOfPlace).attr('class','hide');
					// }
					
					// $scope.tabsMenuData['catBlockData'+v1.orderOfPlace]['url'] = v1.url;
					$scope.tabsMenuData['tab'+v1.orderOfPlace]=false;
				}
				// if(v1.cmsBlockName=="SECONDPAGESECTIONSECONDTABBLOCK"){
				// 	// $scope.FirstTabBlockJsonData.push(v1);
				// 		$scope.IndividualTabs['tabsData'+v1.orderOfPlace] = {};
				// 		// $('#tabImage'+v1.orderOfPlace).attr('src', v1.postsImageUrl);
				// 		$scope.ChangeableTabs['showTabs'+v1.orderOfPlace]=false;
				// 		$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['postsImageUrl'] = v1.postsImageUrl;
				// 		// $('#tabImage10').attr('src', v1.postsImageUrl);
				// 		$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['imageLocation'] = v1.url;
				// 		$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['postsProductTitle'] = v1.postsProductTitle;
				// 		$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['postsProductDiscountedPrice'] = v1.postsProductAfterPrice;
				// 		$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['postsProductPrice'] = v1.postsProductPrice;
				// 	// $scope.tabsMenuData['catBlockData'+v1.orderOfPlace]['url'] = v1.url;
				// 	$scope.tabsMenuData['tab'+v1.orderOfPlace]=false;
				// }
				// if(v1.cmsBlockName=="SECONDPAGESECTIONTHIRDTABBLOCK"){
				// 	// $scope.FirstTabBlockJsonData.push(v1);
				// 		$scope.IndividualTabs['tabsData'+v1.orderOfPlace] = {};
				// 		// $('#tabImage'+v1.orderOfPlace).attr('src', v1.postsImageUrl);
				// 		$scope.ChangeableTabs['showTabs'+v1.orderOfPlace]=false;
				// 		$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['postsImageUrl'] = v1.postsImageUrl;
				// 		// $('#tabImage10').attr('src', v1.postsImageUrl);
				// 		$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['imageLocation'] = v1.url;
				// 		$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['postsProductTitle'] = v1.postsProductTitle;
				// 		$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['postsProductDiscountedPrice'] = v1.postsProductAfterPrice;
				// 		$scope.IndividualTabs['tabsData'+v1.orderOfPlace]['postsProductPrice'] = v1.postsProductPrice;
				// 	// $scope.tabsMenuData['catBlockData'+v1.orderOfPlace]['url'] = v1.url;
				// 	$scope.tabsMenuData['tab'+v1.orderOfPlace]=false;
				// 	// $scope.$apply();
				// }
			})
		// }
		// if(Data._embedded.cmsBlock[1]._embedded){
			// v1ar middlePageSectionData = Data._embedded.cmsBlock[1]._embedded.cmsPosts;
			// $.each(middlePageSectionData,function(k,v){
			
			// })
				// console.log(JSON.stringify($scope.FIRSTPROMOTIONPAGESECTIONBANNERBLOCKckJsonData))
		}
		})
	},function(){});

 	$scope.secondSectionShow=true;
$scope.secondPageSectionTitle = function(titleData,sectionName){
 		loadingView.startLoading("show");
    var jsonfileData= {
		"sectionName":sectionName,
		"sectionTitle":titleData.sectionTitle
		}
		// console.log(JSON.stringify(jsonfileData))
    // if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/cmsSection/sectionName/'+sectionName+'/edit',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	        req.setRequestHeader("Content-Type", "application/json");
	      },
	      data: JSON.stringify(jsonfileData),
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          	loadingView.startLoading("hide");
	          	notify({ message: 'Updated SUCCESSFULLY', classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.secondSectionShow=false;
	          	$scope.$apply();
	      },
	      error:function(){
	          // alert("failure");
	          loadingView.startLoading("hide");
	          notify({ message: "Cannot Updated", classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	      }
	    });
 	}

  $scope.fileShortImageChanged=function(fileData,index){
  	$scope.shortImageBannerObj={
			"shortImageBannerName":fileData.files[0].name,
			"shortImageBannerType" : fileData.files[0].type,
			"shortImageBannerFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#secondShortImage'+index).attr('src', e.target.result);
      $('.secondShortImage'+index).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
  }

  $scope.shortBannerBlockImageUpload=function(data, indexValue){
  	loadingView.startLoading("show");
  	$scope.shortImageBanner = $scope.shortImageBannerObj.shortImageBannerFileData;
    var imageType=$scope.shortImageBannerObj.shortImageBannerType;
    var imgType=imageType.substring(0,5);

  	var formData = new FormData();
  	formData.append('file', $scope.shortImageBanner);
  	formData.append('blockName', "SECONDPAGESECTIONSHORTBANNERBLOCK");
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('imageAlt', data.imageAlt);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	$scope.shortImageData1= {
	          		imageAlt: response.imageAlt,
	          		imageLocation: response.url,
	          		cmsPostsId:response.id
	          	};
	          	$('#secondShortImage1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();  
	          	$scope.shortImageBlock1=false;     
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }

  $scope.fileMiddleImageChanged=function(fileData,index){
  	$scope.middleImageBannerObj={
			"middleImageBannerName":fileData.files[0].name,
			"middleImageBannerType" : fileData.files[0].type,
			"middleImageBannerFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#middleBanner'+index).attr('src', e.target.result);
      $('.middleBanner'+index).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
  }


  $scope.middleBannerBlockImageUpload=function(data, indexValue){
  	loadingView.startLoading("show");
  	$scope.middleImageBanner = $scope.middleImageBannerObj.middleImageBannerFileData;
    var imageType=$scope.middleImageBannerObj.middleImageBannerType;
    var imgType=imageType.substring(0,5);

  	var formData = new FormData();
  	formData.append('file', $scope.middleImageBanner);
  	formData.append('blockName', "SECONDPAGESECTIONMIDDLEBANNERBLOCK");
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('imageAlt', data.imageAlt);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	$scope.middleImageData1= {
	          		imageAlt: response.imageAlt,
	          		imageLocation:response.url,
	          		cmsPostsId:response.id
	          	};
	          	$scope.middleImageBlock1 = false;
	          	$('#middleBanner1').attr('src', response.postsImageUrl);  
	          	$scope.$apply();
	          	// $('#postItem').load();       
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }

  $scope.categoryCreation=function(categoryData,indexValue){
  	loadingView.startLoading("show");
  	var formData = new FormData();
  	formData.append('blockName', "SECONDPAGESECTIONCATEGORYMENUBLOCK");
    formData.append('url', categoryData.url);
    formData.append('postTitle', categoryData.postTitle);
    formData.append('orderOfPlace', indexValue);
    // formData.append('imageAlt', data.imageAlt);
    // if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	notify({ message: response.postName, classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.categoryMenuData['categoryButShow'+response.orderOfPlace]=false;

	          	$scope.$apply();
	          	// console.log(response)
	          	// $scope.middleImageData1= {
	          	// 	imageAlt: response.imageAlt,
	          	// 	imageLocation:response.url
	          	// };
	          	// $scope.middleImageBlock1 = false;
	          	// $('#middleBanner1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();       
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    // }else{
    //   alert('Please Upload Image');
    // }
  }

  $scope.SecondPageSectionTabs = function(tabsData,blockName,indexValue){
  	loadingView.startLoading("show");
  	// var formData = new FormData();
  	// formData.append('blockName', blockName);
    // formData.append('url', tabsData.url);
    // formData.append('postTitle', tabsData.postTitle);
    // formData.append('orderOfPlace', indexValue);
    // formData.append('imageAlt', data.imageAlt);
    var jsonfileData= {
		"blockName":blockName,
		"blockTitle":tabsData.blockTitle,
		"orderOfPlace":indexValue
		}
		console.log(JSON.stringify(jsonfileData))
    // if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/cmsBlock/blockName/'+blockName+'/edit',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	        req.setRequestHeader("Content-Type", "application/json");
	      },
	      data: JSON.stringify(jsonfileData),
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          // if(response.postName[0]=="E"){
	          // 	var errorMsg = response.postName.split(":::")
	          // 	// $scope.firstBannerAlert = errorMsg[1];
	          // 	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          // 	loadingView.startLoading("hide");
	          // 	$scope.$apply();
	          // }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	notify({ message: 'Updated SUCCESSFULLY', classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.tabsMenuData['tab'+response.orderOfPlace]=false;

	          	// $scope.tabsMenuData['secondPageTab'+v.orderOfPlace]['postTitle']

	          	$scope.$apply();
	          	// console.log(response)
	          	// $scope.middleImageData1= {
	          	// 	imageAlt: response.imageAlt,
	          	// 	imageLocation:response.url
	          	// };
	          	// $scope.middleImageBlock1 = false;
	          	// $('#middleBanner1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();       
	          // }
	      },
	      error:function(){
	          alert("failure");
	          notify({ message: "Cannot Updated", classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	      }
	    });
    // }else{
    //   alert('Please Upload Image');
    // }
  }
  
  $scope.fileSecondSectionImageChanged=function(fileData,index){
  	$scope.tabImageObj={
			"tabImageName":fileData.files[0].name,
			"tabImageType" : fileData.files[0].type,
			"tabImageFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#tabImage'+index.srcElement.id).attr('src', e.target.result);
      $('.tabImage'+index.srcElement.id).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
  }

  $scope.TabImageUpload=function(data,indexValue,blockName){
  	// console.log(data,indexValue,blockName)
  	loadingView.startLoading("show");
  	$scope.tabMenuImageBanner = $scope.tabImageObj.tabImageFileData;
    var imageType=$scope.tabImageObj.tabImageType;
    var imgType=imageType.substring(0,5);

  	var formData = new FormData();
  	formData.append('file', $scope.tabMenuImageBanner);
  	formData.append('blockName', blockName);
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('postsProductDiscountedPrice', data.postsProductDiscountedPrice);
    formData.append('postsProductPrice', data.postsProductPrice);
    formData.append('postsProductTitle', data.postsProductTitle);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	notify({ message: response.postName, classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	// $scope.categoryMenuData['categoryButShow'+response.orderOfPlace]=false;
	          	$scope.ChangeableTabs['showTabs'+indexValue] = false;
	          	$scope.IndividualTabs['tabsData'+indexValue]['cmsPostsId'] = response.id;
	          	$scope.$apply();
	          	// console.log(response)
	          	// $scope.middleImageData1= {
	          	// 	imageAlt: response.imageAlt,
	          	// 	imageLocation:response.url
	          	// };
	          	// $scope.middleImageBlock1 = false;
	          	// $('#middleBanner1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();       
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }

  $scope.secondPagePostImageDelete=function(postData,index,name){
  	var tabsId;
  	if(postData.id){
  		tabsId = postData.id
  	}else{
  		tabsId = postData.cmsPostsId
  	}
  	processReqFactory.processReq(baseURL.IP+'/delete/'+tabsId,'GET','',function(deleteData){
  		// $scope.IndividualTabs['tabsData'+index]['postsImageUrl'] = deleteData.postsImageUrl;
  		if(postData.cmsPostsId == deleteData.id){
  			delete $scope.IndividualTabs['tabsData'+index]
  		}
  		$scope.ChangeableTabs['showTabs'+index] = true;
  		// $scope.SecondBannerShow = true;
  		$scope.$apply();
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	$('#tabImage'+index).attr('src', deleteData.postsImageUrl);
     //  	$('.second_banner1'+index).removeClass('hide');
  	},function(){});
  }

  $scope.middleBannerBlockImageDelete=function(middleImageData,index){
  	// console.log(middleImageData,index)
  	var middleImageId;
  	if(middleImageData.id){
  		middleImageId = middleImageData.id
  	}else{
  		middleImageId = middleImageData.cmsPostsId
  	}
  	processReqFactory.processReq(baseURL.IP+'/delete/'+middleImageId,'GET','',function(deleteData){
  		// $scope.IndividualTabs['tabsData'+index]['postsImageUrl'] = deleteData.postsImageUrl;
  		if(middleImageData.cmsPostsId == deleteData.id){
  			delete $scope.middleImageData1
  		}
  		$scope.middleImageBlock1 = true;
  		// $scope.SecondBannerShow = true;
  		$scope.$apply();
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	$('#middleBanner1').attr('src', deleteData.postsImageUrl);
     //  	$('.second_banner1'+index).removeClass('hide');
  	},function(){});
  }
  $scope.shortBannerBlockImageDelete=function(shortImageData,index){
  	
  	var shortImageId;
  	if(shortImageData.id){
  		shortImageId = shortImageData.id
  	}else{
  		shortImageId = shortImageData.cmsPostsId
  	}
  	processReqFactory.processReq(baseURL.IP+'/delete/'+shortImageId,'GET','',function(deleteData){
  		// $scope.IndividualTabs['tabsData'+index]['postsImageUrl'] = deleteData.postsImageUrl;
  		if(shortImageData.cmsPostsId == deleteData.id){
  			delete $scope.shortImageData1
  		}
  		$scope.shortImageBlock1 = true;
  		// $scope.SecondBannerShow = true;
  		$scope.$apply();
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	$('#secondShortImage').attr('src', deleteData.postsImageUrl);
     //  	$('.second_banner1'+index).removeClass('hide');
  	},function(){});
  }
  $scope.secondCategoryDelete=function(categoryData,index){
  	// console.log(categoryData,index)
  	var categorypostId;
  	if(categoryData.id){
  		categorypostId = categoryData.id
  	}else{
  		categorypostId = categoryData.cmsPostsId
  	}
  	processReqFactory.processReq(baseURL.IP+'/delete/'+categorypostId,'GET','',function(deleteData){
  		// $scope.IndividualTabs['tabsData'+index]['postsImageUrl'] = deleteData.postsImageUrl;
  		if(categoryData.cmsPostsId == deleteData.id){
  			delete $scope.categoryMenuData['catBlockData'+index]
  		}
  		$scope.categoryMenuData['categoryButShow'+index] = true;
  		// $scope.SecondBannerShow = true;
  		$scope.$apply();
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	
  	},function(){});
  }

//  THIRD PAGE SECTION 
  // $scope.secondPageBlock1 = true;
  // $scope.secondPageBlock2 = true;
  	$scope.thirdShortImageData1 = {};
  	$scope.thirdMiddleImageData1 = {};
  	$scope.thirdShortImageBlock1=true;
  	$scope.thirdMiddleImageBlock1 = true;
  	$scope.thirdSectionCategoryMenuData = {};
  	$scope.thirdSectionCategoryMenuData['categoryButShow1']=true;
  	$scope.thirdSectionCategoryMenuData['categoryButShow2']=true;
  	$scope.thirdSectionCategoryMenuData['categoryButShow3']=true;
  	$scope.thirdSectionCategoryMenuData['categoryButShow4']=true;
  	$scope.thirdSectionCategoryMenuData['categoryButShow5']=true;
  	$scope.thirdSectionTabsMenuData = {};
  	$scope.thirdChangeableTabs = {};
  	$scope.thirdSectionTabsMenuData['tab4']=true;
  	$scope.thirdSectionTabsMenuData['tab5']=true;
  	$scope.thirdSectionTabsMenuData['tab6']=true;
  	// $scope.ChangeableTabs['secondPageTab1']={};
  	// $scope.thirdSectionTabsMenuData['secondPageTab2']={};
  	$scope.thirdSectionIndividualTabs={};
  	$scope.thirdSectionIndividualTabs2={};
  	$scope.thirdSectionIndividualTabs3={};
  	$scope.thirdSectionCategoryJsonData=[];
  	$scope.titleData = {};
  	$scope.thirdSectionTabsBlockJsonData = [];
  	for(var i=3;i<7;i++){
  		for(var j=0;j<6;j++){
  			$scope.thirdChangeableTabs['showTabs'+i+j]=true;
  		}
  	}
processReqFactory.processReq(baseURL.IP+'/cmsSection/sectionName/THIRDPAGESECTION','GET','',function(Data){
	$scope.titleData['sectionTitle'] = Data.sectionTitle;
	$.each(Data._embedded.cmsBlock,function(k,v){
		
		if(v.blockName=="THIRDPAGESECTIONFIRSTTABBLOCK" || v.blockName=="THIRDPAGESECTIONSECONDTABBLOCK" || v.blockName=="THIRDPAGESECTIONTHIRDTABBLOCK"){
			$scope.thirdSectionTabsBlockJsonData.push(Data._embedded.cmsBlock[k]);
			$scope.thirdSectionTabsMenuData['thirdPageTab'+v.orderOfPlace] = {};
			$scope.thirdSectionTabsMenuData['thirdPageTab'+v.orderOfPlace]['blockTitle'] = v.blockTitle;
			$scope.thirdSectionTabsMenuData['tab'+v.orderOfPlace]=false;
			// console.log(JSON.stringify($scope.TabsBlockJsonData))
		}
		if(Data._embedded.cmsBlock[k]._embedded){
		var thirdPageSectionData = Data._embedded.cmsBlock[k]._embedded.cmsPosts;
			$.each(thirdPageSectionData,function(k1,v1){
				$scope.secondPageBlockData = v1;
				if(v1.cmsBlockName=="THIRDPAGESECTIONSHORTBANNERBLOCK"){
					$('#thirdShortImage1').attr('src', v1.postsImageUrl); 
					$('#thirdShortImage2').attr('src', v1.postsImageUrl); 
					$scope.thirdShortImageData1['imageAlt']=v1.postsImageAlt;
					$scope.thirdShortImageData1['imageLocation']=v1.url;
					$scope.thirdShortImageData1['cmsPostsId']=v1.cmsPostsId;
					$('.thirdShortImage1').attr('class','hide');
					$scope.thirdShortImageBlock1=false;
				}
				if(v1.cmsBlockName=="THIRDPAGESECTIONMIDDLEBANNERBLOCK"){
					$('#thirdMiddleBanner1').attr('src', v1.postsImageUrl); 
					$('#thirdMiddleBanner2').attr('src', v1.postsImageUrl); 
					$scope.thirdMiddleImageData1['imageAlt']=v1.postsImageAlt;
					$scope.thirdMiddleImageData1['imageLocation']=v1.url;
					$scope.thirdMiddleImageData1['cmsPostsId']=v1.cmsPostsId;
					$('.thirdMiddleBanner1').attr('class','hide');
					// middleBanner1
					$scope.thirdMiddleImageBlock1 = false;
				}			
				if(v1.cmsBlockName=="THIRDPAGESECTIONCATEGORYMENUBLOCK"){
					$scope.thirdSectionCategoryJsonData.push(v1);
					$scope.thirdSectionCategoryMenuData['catBlockData'+v1.orderOfPlace] = {};
					$scope.thirdSectionCategoryMenuData['catBlockData'+v1.orderOfPlace]['postTitle'] = v1.postTitle;
					$scope.thirdSectionCategoryMenuData['catBlockData'+v1.orderOfPlace]['url'] = v1.url;
					$scope.thirdSectionCategoryMenuData['catBlockData'+v1.orderOfPlace]['cmsPostsId'] = v1.cmsPostsId;
					$scope.thirdSectionCategoryMenuData['categoryButShow'+v1.orderOfPlace]=false;
				}
				if(v1.cmsBlockName=="THIRDPAGESECTIONFIRSTTABBLOCK" || v1.cmsBlockName=="THIRDPAGESECTIONSECONDTABBLOCK" || v1.cmsBlockName=="THIRDPAGESECTIONTHIRDTABBLOCK"){
					$scope.FirstTabBlockJsonData.push(v1);
					// $scope.thirdChangeableTabs['secondPageTab'+v1.orderOfPlace+k1]=true;
					// if(k1==0){
					// 	$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace+k1] = {};
					// 	$scope.thirdChangeableTabs['showTabs'+v1.orderOfPlace+k1]=false;
					// 	// $('#tabImage'+v1.orderOfPlace+k1).attr('src', v1.postsImageUrl);
					// 	$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace+k1]['postsImageUrl'] = v1.postsImageUrl;
					// 	// $('#tabImage10').attr('src', v1.postsImageUrl);
					// 	$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace+k1]['imageLocation'] = v1.url;
					// 	$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace+k1]['imageAlt'] = v1.postsImageAlt;
					// 	$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace+k1]['postsProductDiscountedPrice'] = v1.postsProductDiscountedPrice;
					// 	$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace+k1]['postsProductPrice'] = v1.postsProductPrice;
					// 	$('.tabImage'+v1.orderOfPlace+k1).attr('class','hide');
					// }else{
						$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace] = {};
						$scope.thirdChangeableTabs['showTabs'+v1.orderOfPlace]=false;
						// $('#tabImage'+v1.orderOfPlace).attr('src', v1.postsImageUrl);
						$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsImageUrl'] = v1.postsImageUrl;
						// $('#tabImage10').attr('src', v1.postsImageUrl);
						$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['imageLocation'] = v1.url;
						$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductTitle'] = v1.postsProductTitle;
						$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductDiscountedPrice'] = v1.postsProductAfterPrice;
						$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductPrice'] = v1.postsProductPrice;
						$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['cmsPostsId'] = v1.cmsPostsId;
						$('.tabImage'+v1.orderOfPlace).attr('class','hide');
					// }
					
					// $scope.thirdSectionTabsMenuData['catBlockData'+v1.orderOfPlace]['url'] = v1.url;
					$scope.thirdSectionTabsMenuData['tab'+v1.orderOfPlace]=false;
				}
				// if(v1.cmsBlockName=="THIRDPAGESECTIONSECONDTABBLOCK"){
				// 	// $scope.FirstTabBlockJsonData.push(v1);
				// 		$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace] = {};
				// 		// $('#tabImage'+v1.orderOfPlace).attr('src', v1.postsImageUrl);
				// 		$scope.thirdChangeableTabs['showTabs'+v1.orderOfPlace]=false;
				// 		$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsImageUrl'] = v1.postsImageUrl;
				// 		// $('#tabImage10').attr('src', v1.postsImageUrl);
				// 		$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['imageLocation'] = v1.url;
				// 		$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductTitle'] = v1.postsProductTitle;
				// 		$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductDiscountedPrice'] = v1.postsProductAfterPrice;
				// 		$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductPrice'] = v1.postsProductPrice;
				// 	// $scope.thirdSectionTabsMenuData['catBlockData'+v1.orderOfPlace]['url'] = v1.url;
				// 	$scope.thirdSectionTabsMenuData['tab'+v1.orderOfPlace]=false;
				// }
				// if(v1.cmsBlockName=="THIRDPAGESECTIONTHIRDTABBLOCK"){
				// 	// $scope.FirstTabBlockJsonData.push(v1);
				// 		$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace] = {};
				// 		// $('#tabImage'+v1.orderOfPlace).attr('src', v1.postsImageUrl);
				// 		$scope.thirdChangeableTabs['showTabs'+v1.orderOfPlace]=false;
				// 		$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsImageUrl'] = v1.postsImageUrl;
				// 		// $('#tabImage10').attr('src', v1.postsImageUrl);
				// 		$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['imageLocation'] = v1.url;
				// 		$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductTitle'] = v1.postsProductTitle;
				// 		$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductDiscountedPrice'] = v1.postsProductAfterPrice;
				// 		$scope.thirdSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductPrice'] = v1.postsProductPrice;
				// 	// $scope.thirdSectionTabsMenuData['catBlockData'+v1.orderOfPlace]['url'] = v1.url;
				// 	$scope.thirdSectionTabsMenuData['tab'+v1.orderOfPlace]=false;
				// 	// $scope.$apply();
				// }
			})
		// }
		// if(Data._embedded.cmsBlock[1]._embedded){
			// v1ar middlePageSectionData = Data._embedded.cmsBlock[1]._embedded.cmsPosts;
			// $.each(middlePageSectionData,function(k,v){
			
			// })
				// console.log(JSON.stringify($scope.FIRSTPROMOTIONPAGESECTIONBANNERBLOCKckJsonData))
		}
		})
	},function(){});

$scope.thirdSectionShow=true;
$scope.thirdPageSectionTitle = function(titleData,sectionName){
 		loadingView.startLoading("show");
    var jsonfileData= {
		"sectionName":sectionName,
		"sectionTitle":titleData.sectionTitle
		}
		console.log(JSON.stringify(jsonfileData))
    // if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/cmsSection/sectionName/'+sectionName+'/edit',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	        req.setRequestHeader("Content-Type", "application/json");
	      },
	      data: JSON.stringify(jsonfileData),
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          	loadingView.startLoading("hide");
	          	notify({ message: 'Updated SUCCESSFULLY', classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.thirdSectionShow=false;
	          	$scope.$apply();
	      },
	      error:function(){
	          alert("failure");
	          notify({ message: "Cannot Updated", classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	      }
	    });
 	}
 
  $scope.fileThirdShortImageChanged=function(fileData,index){
  	$scope.thirdShortImageBannerObj={
			"thirdShortImageBannerName":fileData.files[0].name,
			"thirdShortImageBannerType" : fileData.files[0].type,
			"thirdShortImageBannerFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#thirdShortImage'+index).attr('src', e.target.result);
      $('.thirdShortImage'+index).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
  }

  $scope.thirdSectionShortBlockImageUpload=function(data, indexValue){
  	loadingView.startLoading("show");
  	$scope.thirdShortImageBanner = $scope.thirdShortImageBannerObj.thirdShortImageBannerFileData;
    var imageType=$scope.thirdShortImageBannerObj.thirdShortImageBannerType;
    var imgType=imageType.substring(0,5);

  	var formData = new FormData();
  	formData.append('file', $scope.thirdShortImageBanner);
  	formData.append('blockName', "THIRDPAGESECTIONSHORTBANNERBLOCK");
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('imageAlt', data.imageAlt);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;

	          	var successMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: successMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	loadingView.startLoading("hide");
	          	$scope.thirdShortImageData1= {
	          		imageAlt: response.postsImageAlt,
	          		imageLocation: response.url,
	          		cmsPostsId:response.id
	          	};
	          	$('#thirdShortImage1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();  
	          	$scope.thirdShortImageBlock1=false; 
	          	$scope.$apply();    
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }

  $scope.fileThirdMiddleImageChanged=function(fileData,index){
  	$scope.thirdMiddleImageBannerObj={
			"thirdMiddleImageBannerName":fileData.files[0].name,
			"thirdMiddleImageBannerType" : fileData.files[0].type,
			"thirdMiddleImageBannerFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#thirdMiddleBanner'+index).attr('src', e.target.result);
      $('.thirdMiddleBanner'+index).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
  }


  $scope.ThirdSectionMiddleBlockImageUpload=function(data, indexValue){
  	loadingView.startLoading("show");
  	$scope.thirdMiddleImageBanner = $scope.thirdMiddleImageBannerObj.thirdMiddleImageBannerFileData;
    var imageType=$scope.thirdMiddleImageBannerObj.thirdMiddleImageBannerType;
    var imgType=imageType.substring(0,5);

  	var formData = new FormData();
  	formData.append('file', $scope.thirdMiddleImageBanner);
  	formData.append('blockName', "THIRDPAGESECTIONMIDDLEBANNERBLOCK");
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('imageAlt', data.imageAlt);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	var successMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: successMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	loadingView.startLoading("hide");
	          	$scope.thirdMiddleImageData1= {
	          		imageAlt: response.postsImageAlt,
	          		imageLocation:response.url,
	          		cmsPostsId:response.id
	          	};
	          	$scope.thirdMiddleImageBlock1 = false;
	          	$('#thirdMiddleBanner1').attr('src', response.postsImageUrl);  
	          	$scope.$apply();      
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }

  $scope.thirdSectionCategoryCreation=function(categoryData,indexValue){
  	loadingView.startLoading("show");
  	var formData = new FormData();
  	formData.append('blockName', "THIRDPAGESECTIONCATEGORYMENUBLOCK");
    formData.append('url', categoryData.url);
    formData.append('postTitle', categoryData.postTitle);
    formData.append('orderOfPlace', indexValue);
    // formData.append('imageAlt', data.imageAlt);
    // if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	notify({ message: response.postName, classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.thirdSectionCategoryMenuData['categoryButShow'+response.orderOfPlace]=false;
	          	$scope.thirdSectionCategoryMenuData['catBlockData'+response.orderOfPlace]['cmsPostsId']=response.id;

	          	$scope.$apply();
	          	// console.log(response)
	          	// $scope.middleImageData1= {
	          	// 	imageAlt: response.imageAlt,
	          	// 	imageLocation:response.url
	          	// };
	          	// $scope.middleImageBlock1 = false;
	          	// $('#middleBanner1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();       
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    // }else{
    //   alert('Please Upload Image');
    // }
  }

  $scope.ThirdPageSectionTabs = function(tabsData,blockName,indexValue){
  	loadingView.startLoading("show");
  	// var formData = new FormData();
  	// formData.append('blockName', blockName);
    // formData.append('url', tabsData.url);
    // formData.append('postTitle', tabsData.postTitle);
    // formData.append('orderOfPlace', indexValue);
    // formData.append('imageAlt', data.imageAlt);
    var jsonfileData= {
		"blockName":blockName,
		"blockTitle":tabsData.blockTitle,
		"orderOfPlace":indexValue
		}
		console.log(JSON.stringify(jsonfileData))
    // if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/cmsBlock/blockName/'+blockName+'/edit',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	        req.setRequestHeader("Content-Type", "application/json");
	      },
	      data: JSON.stringify(jsonfileData),
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          // if(response.postName[0]=="E"){
	          // 	var errorMsg = response.postName.split(":::")
	          // 	// $scope.firstBannerAlert = errorMsg[1];
	          // 	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          // 	loadingView.startLoading("hide");
	          // 	$scope.$apply();
	          // }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	notify({ message: 'Updated SUCCESSFULLY', classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.thirdSectionTabsMenuData['tab'+response.orderOfPlace]=false;
	          	// $scope.tabsMenuData['secondPageTab'+v.orderOfPlace]['postTitle']

	          	$scope.$apply();
	          	// console.log(response)
	          	// $scope.middleImageData1= {
	          	// 	imageAlt: response.imageAlt,
	          	// 	imageLocation:response.url
	          	// };
	          	// $scope.middleImageBlock1 = false;
	          	// $('#middleBanner1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();       
	          // }
	      },
	      error:function(){
	          alert("failure");
	          notify({ message: "Cannot Updated", classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	      }
	    });
    // }else{
    //   alert('Please Upload Image');
    // }
  }
  
  $scope.fileThirdSectionImageChanged=function(fileData,index){
  	$scope.thirdtabImageObj={
			"thirdtabImageName":fileData.files[0].name,
			"thirdtabImageType" : fileData.files[0].type,
			"thirdTabImageFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#thirdTabImage'+index.srcElement.id).attr('src', e.target.result);
      $('.thirdTabImage'+index.srcElement.id).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
  }

  $scope.thirdSectionTabImageUpload=function(data,indexValue,blockName){
  	console.log(data,indexValue,blockName)
  	loadingView.startLoading("show");
  	$scope.thirdtabMenuImageBanner = $scope.thirdtabImageObj.thirdTabImageFileData;
    var imageType=$scope.thirdtabImageObj.thirdtabImageType;
    var imgType=imageType.substring(0,5);

  	var formData = new FormData();
  	formData.append('file', $scope.thirdtabMenuImageBanner);
  	formData.append('blockName', blockName);
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('postsProductDiscountedPrice', data.postsProductDiscountedPrice);
    formData.append('postsProductPrice', data.postsProductPrice);
    formData.append('postsProductTitle', data.postsProductTitle);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	notify({ message: response.postName, classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	// $scope.categoryMenuData['categoryButShow'+response.orderOfPlace]=false;
	          	$scope.thirdChangeableTabs['showTabs'+indexValue] = false;
	          	$scope.thirdSectionIndividualTabs['tabsData'+indexValue]['cmsPostsId'] = response.id;

	          	$scope.$apply();
	          	// console.log(response)
	          	// $scope.middleImageData1= {
	          	// 	imageAlt: response.imageAlt,
	          	// 	imageLocation:response.url
	          	// };
	          	// $scope.middleImageBlock1 = false;
	          	// $('#middleBanner1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();       
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }


$scope.thirdSectionTabImageDelete=function(postData,index,name){
  	var tabsId;
  	if(postData.id){
  		tabsId = postData.id
  	}else{
  		tabsId = postData.cmsPostsId
  	}
  	processReqFactory.processReq(baseURL.IP+'/delete/'+tabsId,'GET','',function(deleteData){
  		// $scope.IndividualTabs['tabsData'+index]['postsImageUrl'] = deleteData.postsImageUrl;
  		if(postData.cmsPostsId == deleteData.id){
  			delete $scope.thirdSectionIndividualTabs['tabsData'+index]
  		}
  		$scope.thirdChangeableTabs['showTabs'+index] = true;
  		// $scope.SecondBannerShow = true;
  		$scope.$apply();
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	$('#thirdTabImage'+index).attr('src', deleteData.postsImageUrl);
     //  	$('.second_banner1'+index).removeClass('hide');
  	},function(){});
  }

  $scope.ThirdSectionMiddleBlockImageDelete=function(middleImageData,index){
  	// console.log(middleImageData,index)
  	var middleImageId;
  	if(middleImageData.id){
  		middleImageId = middleImageData.id
  	}else{
  		middleImageId = middleImageData.cmsPostsId
  	}
  	processReqFactory.processReq(baseURL.IP+'/delete/'+middleImageId,'GET','',function(deleteData){
  		// $scope.IndividualTabs['tabsData'+index]['postsImageUrl'] = deleteData.postsImageUrl;
  		if(middleImageData.cmsPostsId == deleteData.id){
  			delete $scope.thirdMiddleImageData1
  		}
  		$scope.thirdMiddleImageBlock1 = true;
  		// $scope.SecondBannerShow = true;
  		$scope.$apply();
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	$('#thirdMiddleBanner1').attr('src', deleteData.postsImageUrl);
     //  	$('.second_banner1'+index).removeClass('hide');
  	},function(){});
  }
  $scope.thirdSectionShortBlockImageDelete=function(shortImageData,index){
  	
  	var shortImageId;
  	if(shortImageData.id){
  		shortImageId = shortImageData.id
  	}else{
  		shortImageId = shortImageData.cmsPostsId
  	}
  	processReqFactory.processReq(baseURL.IP+'/delete/'+shortImageId,'GET','',function(deleteData){
  		// $scope.IndividualTabs['tabsData'+index]['postsImageUrl'] = deleteData.postsImageUrl;
  		if(shortImageData.cmsPostsId == deleteData.id){
  			delete $scope.thirdShortImageData1
  		}
  		$scope.thirdShortImageBlock1 = true;
  		// $scope.SecondBannerShow = true;
  		$scope.$apply();
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	$('#thirdShortImage1').attr('src', deleteData.postsImageUrl);
     //  	$('.second_banner1'+index).removeClass('hide');
  	},function(){});
  }
  $scope.thirdSectionCategoryDelete=function(categoryData,index){
  	console.log(categoryData,index)
  	var categorypostId;
  	if(categoryData.id){
  		categorypostId = categoryData.id
  	}else{
  		categorypostId = categoryData.cmsPostsId
  	}
  	processReqFactory.processReq(baseURL.IP+'/delete/'+categorypostId,'GET','',function(deleteData){
  		// $scope.IndividualTabs['tabsData'+index]['postsImageUrl'] = deleteData.postsImageUrl;
  		if(categoryData.cmsPostsId == deleteData.id){
  			delete $scope.thirdSectionCategoryMenuData['catBlockData'+index]
  		}
  		$scope.thirdSectionCategoryMenuData['categoryButShow'+index] = true;
  		// $scope.SecondBannerShow = true;
  		$scope.$apply();
      var deleteMsg = deleteData.postName.split(":::");
    	notify({ message: deleteMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
    	
  	},function(){});
  }


  //  FOURTH PAGE SECTION
  // $scope.secondPageBlock1 = true;
  // $scope.secondPageBlock2 = true;
  	$scope.fourthShortImageData1 = {};
  	$scope.fourthMiddleImageData1 = {};
  	$scope.fourthShortImageBlock1 = true;
  	$scope.fourthMiddleImageBlock1 = true;
  	$scope.fourthSectionCategoryMenuData = {};
  	$scope.fourthSectionCategoryMenuData['categoryButShow1']=true;
  	$scope.fourthSectionCategoryMenuData['categoryButShow2']=true;
  	$scope.fourthSectionCategoryMenuData['categoryButShow3']=true;
  	$scope.fourthSectionCategoryMenuData['categoryButShow4']=true;
  	$scope.fourthSectionCategoryMenuData['categoryButShow5']=true;
  	$scope.fourthSectionTabsMenuData = {};
  	$scope.fourthChangeableTabs = {};
  	$scope.fourthSectionTabsMenuData['tab4']=true;
  	$scope.fourthSectionTabsMenuData['tab5']=true;
  	$scope.fourthSectionTabsMenuData['tab6']=true;
  	// $scope.ChangeableTabs['secondPageTab1']={};
  	// $scope.fourthSectionTabsMenuData['secondPageTab2']={};
  	$scope.fourthSectionIndividualTabs={};
  	$scope.fourthSectionIndividualTabs2={};
  	$scope.fourthSectionIndividualTabs3={};
  	$scope.fourthSectionCategoryJsonData=[];
  	// $scope.FirstTabBlockJsonData = [];
  	$scope.fourthSectionTabsBlockJsonData = [];
  	for(var i=3;i<7;i++){
  		for(var j=0;j<6;j++){
  			$scope.fourthChangeableTabs['showTabs'+i+j]=true;
  		}
  	}
// processReqFactory.processReq(baseURL.IP+'/cmsSection/sectionName/FOURTHPAGESECTION','GET','',function(Data){
// 	$.each(Data._embedded.cmsBlock,function(k,v){
// 		if(v.blockName=="FOURTHPAGESECTIONFIRSTTABBLOCK" || v.blockName=="FOURTHPAGESECTIONSECONDTABBLOCK" || v.blockName=="FOURTHPAGESECTIONTHIRDTABBLOCK"){
// 			$scope.fourthSectionTabsBlockJsonData.push(Data._embedded.cmsBlock[k]);
// 			$scope.fourthSectionTabsMenuData['thirdPageTab'+v.orderOfPlace] = {};
// 			$scope.fourthSectionTabsMenuData['thirdPageTab'+v.orderOfPlace]['blockTitle'] = v.blockTitle;
// 			$scope.fourthSectionTabsMenuData['tab'+v.orderOfPlace]=false;
// 		}
// 		if(Data._embedded.cmsBlock[k]._embedded){
// 		var fourthPageSectionData = Data._embedded.cmsBlock[k]._embedded.cmsPosts;
// 			$.each(fourthPageSectionData,function(k1,v1){
// 				$scope.secondPageBlockData = v1;
// 				if(v1.cmsBlockName=="FOURTHPAGESECTIONSHORTBANNERBLOCK"){
// 					$('#fourthShortImage1').attr('src', v1.postsImageUrl); 
// 					$('#fourthShortImage2').attr('src', v1.postsImageUrl); 
// 					$scope.fourthShortImageData1['imageAlt']=v1.postsImageAlt;
// 					$scope.fourthShortImageData1['imageLocation']=v1.url;
// 					$('.thirdShortImage1').attr('class','hide');
// 					$scope.fourthShortImageBlock1=false;
// 				}
// 				if(v1.cmsBlockName=="FOURTHPAGESECTIONMIDDLEBANNERBLOCK"){
// 					$('#fourthMiddleBanner1').attr('src', v1.postsImageUrl); 
// 					$('#fourthMiddleBanner2').attr('src', v1.postsImageUrl); 
// 					$scope.fourthMiddleImageData1['imageAlt']=v1.postsImageAlt;
// 					$scope.fourthMiddleImageData1['imageLocation']=v1.url;
// 					$('.fourthMiddleBanner1').attr('class','hide');
// 					// middleBanner1
// 					$scope.fourthMiddleImageBlock1 = false;
// 				}			
// 				if(v1.cmsBlockName=="FOURTHPAGESECTIONCATEGORYMENUBLOCK"){
// 					$scope.fourthSectionCategoryJsonData.push(v1);
// 					$scope.fourthSectionCategoryMenuData['catBlockData'+v1.orderOfPlace] = {};
// 					$scope.fourthSectionCategoryMenuData['catBlockData'+v1.orderOfPlace]['postTitle'] = v1.postTitle;
// 					$scope.fourthSectionCategoryMenuData['catBlockData'+v1.orderOfPlace]['url'] = v1.url;
// 					$scope.fourthSectionCategoryMenuData['categoryButShow'+v1.orderOfPlace]=false;
// 				}
// 				if(v1.cmsBlockName=="FOURTHPAGESECTIONFIRSTTABBLOCK"){
// 					// $scope.FirstTabBlockJsonData.push(v1);
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace] = {};
// 						$scope.fourthChangeableTabs['showTabs'+v1.orderOfPlace]=false;
// 						// $('#tabImage'+v1.orderOfPlace).attr('src', v1.postsImageUrl);
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsImageUrl'] = v1.postsImageUrl;
// 						// $('#tabImage10').attr('src', v1.postsImageUrl);
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['imageLocation'] = v1.url;
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductTitle'] = v1.postsProductTitle;
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductDiscountedPrice'] = v1.postsProductAfterPrice;
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductPrice'] = v1.postsProductPrice;
// 						$('.fourthTabImage'+v1.orderOfPlace).attr('class','hide');
// 					$scope.fourthSectionTabsMenuData['tab'+v1.orderOfPlace]=false;
// 				}
// 				if(v1.cmsBlockName=="FOURTHPAGESECTIONSECONDTABBLOCK"){
// 					// $scope.FirstTabBlockJsonData.push(v1);
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace] = {};
// 						// $('#tabImage'+v1.orderOfPlace).attr('src', v1.postsImageUrl);
// 						$scope.fourthChangeableTabs['showTabs'+v1.orderOfPlace]=false;
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsImageUrl'] = v1.postsImageUrl;
// 						// $('#tabImage10').attr('src', v1.postsImageUrl);
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['imageLocation'] = v1.url;
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductTitle'] = v1.postsProductTitle;
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductDiscountedPrice'] = v1.postsProductAfterPrice;
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductPrice'] = v1.postsProductPrice;
// 					// $scope.fourthSectionTabsMenuData['catBlockData'+v1.orderOfPlace]['url'] = v1.url;
// 					$scope.fourthSectionTabsMenuData['tab'+v1.orderOfPlace]=false;
// 				}
// 				if(v1.cmsBlockName=="FOURTHPAGESECTIONTHIRDTABBLOCK"){
// 					// $scope.FirstTabBlockJsonData.push(v1);
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace] = {};
// 						// $('#tabImage'+v1.orderOfPlace).attr('src', v1.postsImageUrl);
// 						$scope.fourthChangeableTabs['showTabs'+v1.orderOfPlace]=false;
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsImageUrl'] = v1.postsImageUrl;
// 						// $('#tabImage10').attr('src', v1.postsImageUrl);
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['imageLocation'] = v1.url;
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductTitle'] = v1.postsProductTitle;
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductDiscountedPrice'] = v1.postsProductAfterPrice;
// 						$scope.fourthSectionIndividualTabs['tabsData'+v1.orderOfPlace]['postsProductPrice'] = v1.postsProductPrice;
// 					// $scope.fourthSectionTabsMenuData['catBlockData'+v1.orderOfPlace]['url'] = v1.url;
// 					$scope.fourthSectionTabsMenuData['tab'+v1.orderOfPlace]=false;
// 					// $scope.$apply();
// 				}
// 			})
// 		}
// 		})
// 	},function(){});

 	$scope.fourthPageSectionTitle = function(titleData,sectionName){
 		loadingView.startLoading("show");
    var jsonfileData= {
		"sectionName":sectionName,
		"sectionTitle":titleData.sectionTitle
		}
		console.log(JSON.stringify(jsonfileData))
    // if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/cmsSection/sectionName/'+sectionName+'/edit',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	        req.setRequestHeader("Content-Type", "application/json");
	      },
	      data: JSON.stringify(jsonfileData),
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          	loadingView.startLoading("hide");
	          	notify({ message: 'Updated SUCCESSFULLY', classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.fourthSectionShow=false;
	          	$scope.$apply();
	      },
	      error:function(){
	          alert("failure");
	          notify({ message: "Cannot Updated", classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	      }
	    });
 	}

  $scope.fileFouthShortImageChanged=function(fileData,index){
  	$scope.fourthShortImageBannerObj={
			"fourthShortImageBannerName":fileData.files[0].name,
			"fourthShortImageBannerType" : fileData.files[0].type,
			"fourthShortImageBannerFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#fourthShortImage'+index).attr('src', e.target.result);
      $('.fourthShortImage'+index).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
  }

  $scope.fourthSectionShortBlockImageUpload=function(data, indexValue){
  	loadingView.startLoading("show");
  	$scope.fourthShortImageBanner = $scope.fourthShortImageBannerObj.fourthShortImageBannerFileData;
    var imageType=$scope.fourthShortImageBannerObj.fourthShortImageBannerType;
    var imgType=imageType.substring(0,5);

  	var formData = new FormData();
  	formData.append('file', $scope.fourthShortImageBanner);
  	formData.append('blockName', "FOURTHPAGESECTIONSHORTBANNERBLOCK");
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('imageAlt', data.imageAlt);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;

	          	var successMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: successMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	loadingView.startLoading("hide");
	          	$scope.fourthShortImageData1= {
	          		imageAlt: response.postsImageAlt,
	          		imageLocation: response.url
	          	};
	          	$('#fourthShortImage1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();  
	          	$scope.fourthShortImageBlock1=false;     
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }

  $scope.fileFourthMiddleImageChanged=function(fileData,index){
  	$scope.fourthMiddleImageBannerObj={
			"fourthMiddleImageBannerName":fileData.files[0].name,
			"fourthMiddleImageBannerType" : fileData.files[0].type,
			"fourthMiddleImageBannerFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#fourthMiddleBanner'+index).attr('src', e.target.result);
      $('.fourthMiddleBanner'+index).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
  }


  $scope.FourthSectionMiddleBlockImageUpload=function(data, indexValue){
  	loadingView.startLoading("show");
  	$scope.fourthMiddleImageBanner = $scope.fourthMiddleImageBannerObj.fourthMiddleImageBannerFileData;
    var imageType=$scope.thirdMiddleImageBannerObj.fourthMiddleImageBannerType;
    var imgType=imageType.substring(0,5);

  	var formData = new FormData();
  	formData.append('file', $scope.fourthMiddleImageBanner);
  	formData.append('blockName', "FOURTHPAGESECTIONMIDDLEBANNERBLOCK");
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('imageAlt', data.imageAlt);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	var successMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: successMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	loadingView.startLoading("hide");
	          	$scope.fourthMiddleImageData1= {
	          		imageAlt: response.postsImageAlt,
	          		imageLocation:response.url
	          	};
	          	$scope.fourthMiddleImageBlock1 = false;
	          	$('#fourthMiddleBanner1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();       
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }

  $scope.fouthSectionCategoryCreation=function(categoryData,indexValue){
  	loadingView.startLoading("show");
  	var formData = new FormData();
  	formData.append('blockName', "FOURTHPAGESECTIONCATEGORYMENUBLOCK");
    formData.append('url', categoryData.url);
    formData.append('postTitle', categoryData.postTitle);
    formData.append('orderOfPlace', indexValue);
    // formData.append('imageAlt', data.imageAlt);
    // if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	notify({ message: response.postName, classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.fourthSectionCategoryMenuData['categoryButShow'+response.orderOfPlace]=false;

	          	$scope.$apply();
	          	// console.log(response)
	          	// $scope.middleImageData1= {
	          	// 	imageAlt: response.imageAlt,
	          	// 	imageLocation:response.url
	          	// };
	          	// $scope.middleImageBlock1 = false;
	          	// $('#middleBanner1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();       
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    // }else{
    //   alert('Please Upload Image');
    // }
  }

  $scope.FourthPageSectionTabs = function(tabsData,blockName,indexValue){
  	loadingView.startLoading("show");
  	// var formData = new FormData();
  	// formData.append('blockName', blockName);
    // formData.append('url', tabsData.url);
    // formData.append('postTitle', tabsData.postTitle);
    // formData.append('orderOfPlace', indexValue);
    // formData.append('imageAlt', data.imageAlt);
    var jsonfileData= {
		"blockName":blockName,
		"blockTitle":tabsData.blockTitle,
		"orderOfPlace":indexValue
		}
		console.log(JSON.stringify(jsonfileData))
    // if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/cmsBlock/blockName/'+blockName+'/edit',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	        req.setRequestHeader("Content-Type", "application/json");
	      },
	      data: JSON.stringify(jsonfileData),
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          // if(response.postName[0]=="E"){
	          // 	var errorMsg = response.postName.split(":::")
	          // 	// $scope.firstBannerAlert = errorMsg[1];
	          // 	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          // 	loadingView.startLoading("hide");
	          // 	$scope.$apply();
	          // }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	notify({ message: 'Updated SUCCESSFULLY', classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.fourthSectionTabsMenuData['tab'+response.orderOfPlace]=false;
	          	// $scope.tabsMenuData['secondPageTab'+v.orderOfPlace]['postTitle']

	          	$scope.$apply();
	          	// console.log(response)
	          	// $scope.middleImageData1= {
	          	// 	imageAlt: response.imageAlt,
	          	// 	imageLocation:response.url
	          	// };
	          	// $scope.middleImageBlock1 = false;
	          	// $('#middleBanner1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();       
	          // }
	      },
	      error:function(){
	          alert("failure");
	          notify({ message: "Cannot Updated", classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	      }
	    });
    // }else{
    //   alert('Please Upload Image');
    // }
  }
  
  $scope.fileFourthSectionImageChanged=function(fileData,index){
  	$scope.fourthtabImageObj={
			"fourthtabImageName":fileData.files[0].name,
			"fourthtabImageType" : fileData.files[0].type,
			"fourthTabImageFileData":fileData.files[0]
		}
		console.log(fileData.files[0].name);
		var reader = new FileReader();
		reader.onload = function (e) {
      $('#fourthTabImage'+index.srcElement.id).attr('src', e.target.result);
      $('.fourthTabImage'+index.srcElement.id).attr('class','hide');
    }
    reader.readAsDataURL(fileData.files[0]);
  }

  $scope.forthSectionTabImageUpload=function(data,indexValue,blockName){
  	console.log(data,indexValue,blockName)
  	loadingView.startLoading("show");
  	$scope.fourthtabMenuImageBanner = $scope.fourthtabImageObj.fourthTabImageFileData;
    var imageType=$scope.fourthtabImageObj.fourthtabImageType;
    var imgType=imageType.substring(0,5);

  	var formData = new FormData();
  	formData.append('file', $scope.fourthtabMenuImageBanner);
  	formData.append('blockName', blockName);
    formData.append('url', data.imageLocation);
    formData.append('orderOfPlace', indexValue);
    formData.append('postsProductDiscountedPrice', data.postsProductDiscountedPrice);
    formData.append('postsProductPrice', data.postsProductPrice);
    formData.append('postsProductTitle', data.postsProductTitle);
    if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/uploadPostsWithImage',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	      },
	      data:formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          
	          if(response.postName[0]=="E"){
	          	var errorMsg = response.postName.split(":::")
	          	// $scope.firstBannerAlert = errorMsg[1];
	          	notify({ message: errorMsg[1], classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	loadingView.startLoading("hide");
	          	$scope.$apply();
	          }else{
	          	// $scope.firstBannerAlert = '';
	          	// $scope.secondBannerBlockData = response;
	          	loadingView.startLoading("hide");
	          	notify({ message: response.postName, classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	// $scope.categoryMenuData['categoryButShow'+response.orderOfPlace]=false;

	          	$scope.$apply();
	          	// console.log(response)
	          	// $scope.middleImageData1= {
	          	// 	imageAlt: response.imageAlt,
	          	// 	imageLocation:response.url
	          	// };
	          	// $scope.middleImageBlock1 = false;
	          	// $('#middleBanner1').attr('src', response.postsImageUrl);  
	          	// $('#postItem').load();       
	          }
	      },
	      error:function(){
	          alert("failure");
	      }
	    });
    }else{
      alert('Please Upload Image');
    }
  }

  // Top Title
  $scope.TopTittle=function(titleName){
  	loadingView.startLoading("show");
  	// var formData = new FormData();
  	// formData.append('blockName', blockName);
    // formData.append('url', tabsData.url);
    // formData.append('postTitle', tabsData.postTitle);
    // formData.append('orderOfPlace', indexValue);
    // formData.append('imageAlt', data.imageAlt);
    var jsonfileData= {
		"blockName":blockName,
		"blockTitle":tabsData.blockTitle,
		"orderOfPlace":indexValue
		}
		console.log(JSON.stringify(jsonfileData))
    // if(imgType == "image"){
	      $.ajax({
	      type: "POST",
	      url: baseURL.IP+'/cmsBlock/blockName/'+blockName+'/edit',
	      beforeSend: function(req) {
	        req.setRequestHeader("Accept", "application/json");
	        req.setRequestHeader("Content-Type", "application/json");
	      },
	      data: JSON.stringify(jsonfileData),
	      processData: false,
	      contentType: false,
	      success: function(response) {
	          	loadingView.startLoading("hide");
	          	notify({ message: 'Updated SUCCESSFULLY', classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	          	
	          	$scope.fourthSectionTabsMenuData['tab'+response.orderOfPlace]=false;
	          	// $scope.tabsMenuData['secondPageTab'+v.orderOfPlace]['postTitle']

	          	$scope.$apply();
	      },
	      error:function(){
	          alert("failure");
	          notify({ message: "Cannot Updated", classes: 'alert-success', className: "success", templateUrl:"views/common/notify.html" });
	      }
	    });
    // }else{
  }

}

  function ProfileController($scope){

}

angular
	.module('DEALMACHA')
	.controller('MainCtrl',MainCtrl)
	.controller('HeadController',HeadController)
	.controller('DashboardPageController',DashboardPageController)
	.controller('UserCreationController',UserCreationController)
	.controller('UserController',UserController)
	.controller('SingleUserController',SingleUserController)
	.controller('SingleRequestController',SingleRequestController)
	.controller('RequestController',RequestController)
	.controller('RequestsTypeCreationController',RequestsTypeCreationController)
	.controller('RequestsTypeController',RequestsTypeController)
	.controller('SingleRequestsTypeController',SingleRequestsTypeController)
	.controller('RequestTypeEditController',RequestTypeEditController)
	.controller('MerchantCreationController',MerchantCreationController)
	.controller('MerchantController',MerchantController)
	.controller('SingleMerchantController',SingleMerchantController)
	.controller('TransactionController',TransactionController)
	.controller('SingleTransactionController',SingleTransactionController)
	.controller('ProfileController',ProfileController)
	.controller('MerchantCategoryCreationController',MerchantCategoryCreationController)
	.controller('MerchantCategoryController',MerchantCategoryController)
	.controller('SingleMerchantCategoryController',SingleMerchantCategoryController)
	.controller('MerchantCategoryMarginCreationController',MerchantCategoryMarginCreationController)
	.controller('MerchantCategoryMarginController',MerchantCategoryMarginController)
	.controller('SingleMerchantCategoryMarginController',SingleMerchantCategoryMarginController)
	.controller('OffersCreationController',OffersCreationController)
	.controller('OffersController',OffersController)
	.controller('SingleOfferController',SingleOfferController)
	.controller('CashbackController',CashbackController)
	.controller('SingleCashbackController',SingleCashbackController)
	// .controller('PayoomOfferController',PayoomOfferController)
	// .controller('SinglePayoomOfferController',SinglePayoomOfferController)
	// .controller('OmgOffersController',PayoomOfferController)
	// .controller('SingleOmgOfferController',SinglePayoomOfferController)
	.controller('CmsController',CmsController);

	