function config($stateProvider,$urlRouterProvider, $ocLazyLoadProvider){	

	$ocLazyLoadProvider.config({
		debug: false
	});
	$urlRouterProvider.otherwise("Dashboards/DashboardPage");
	$stateProvider
		.state('LoginPage', {
			url: "/LoginPage",
			templateUrl: "Views/LoginPageTemplate.html",
			data: { pageTitle: 'LoginPage'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					}
					]);
				}
			}
		})
		.state('Profile', {
			abstract: true,
			url: "/Profile",
			templateUrl: "Views/common/profile_side_navigation.html",
			})

		.state('Profile.MyProfile', {
			url: "/MyProfile",
			templateUrl: "Views/ProfileTemplate.html",
			data: { pageTitle: 'MyProfile'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					}
					]);
				}
			}
		})
		.state('Profile.ChangePassword', {
			url: "/ChangePassword",
			templateUrl: "Views/ProfilePasswordTemplate.html",
			data: { pageTitle: 'ChangePassword'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					}
					]);
				}
			}
		})
		.state('Dashboards', {
			abstract: true,
			url: "/Dashboards",
			templateUrl: "Views/common/content_top_navigation.html",
		})
		.state('Dashboards.DashboardPage', {
			url: "/DashboardPage",
			templateUrl: "Views/DashboardPageTemplate.html",
			data: { pageTitle: 'Dashboard'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					}
					]);
				}
			}
		})
		.state('Users', {
			abstract: true,
			url: "/Users",
			templateUrl: "Views/common/user_side_navigation.html",
		})
		.state('Users.User', {
			url: "/User",
			controller:UserController,
			templateUrl: "Views/UsersTemplate.html",
			data: { pageTitle: 'Users'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
                        serie: true,
                        files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
                    },
                    {
                        serie: true,
                        files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                    },
                    {
                        name: 'datatables',
                        files: ['../common/plugins/dataTables/angular-datatables.min.js']
                    }
					]);
				}
			}
		})

	.state('Users.UserCreation', {
			url: "/UserCreation",
			templateUrl: "Views/UserCreationTemplate.html",
			data: { pageTitle: 'User Creation'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
					{
                  serie: true,
                  files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
              },
                {
                    serie: true,
                    files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                },
                {
                    name: 'datatables',
                    files: ['../common/plugins/dataTables/angular-datatables.min.js']
                },
                {
                    name: 'datePicker',
                    files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                },
                {
                    files: ['../common/plugins/moment/moment.min.js']
                },
                {
                    files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
                },
                {
                    name: 'oitozero.ngSweetAlert',
                    files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
                }
					]);
				}
			}
		})
	.state('Users.SingleUserTemplate', {
			url: "/SingleUserTemplate/:userId",
			templateUrl: "Views/SingleUserTemplate.html",
			data: { pageTitle: 'SingleUser View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
         {
              name: 'datePicker',
              files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
          },
          {
              files: ['../common/plugins/moment/moment.min.js']
          },
          {
              files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
          },
          {
              name: 'oitozero.ngSweetAlert',
              files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
          }
					]);
				}
			}
		})
		.state('Requests', {
			abstract: true,
			url: "/Requests",
			templateUrl: "Views/common/request_side_navigation.html",
		})
		.state('Requests.Request', {
			url: "/Request",
			controller:RequestController,
			templateUrl: "Views/RequestTemplate.html",
			data: { pageTitle: 'Requests'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
                        serie: true,
                        files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
                    },
                    {
                        serie: true,
                        files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                    },
                    {
                        name: 'datatables',
                        files: ['../common/plugins/dataTables/angular-datatables.min.js']
                    }
					]);
				}
			}
		})
		.state('Requests.RequestsCreation', {
			url: "/RequestsCreation",
			templateUrl: "Views/RequestsCreationTemplate.html",
			data: { pageTitle: 'Requests Creation'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
					{
                  serie: true,
                  files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
              },
                {
                    serie: true,
                    files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                },
                {
                    name: 'datatables',
                    files: ['../common/plugins/dataTables/angular-datatables.min.js']
                },
                {
                    name: 'datePicker',
                    files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                },
                {
                    files: ['../common/plugins/moment/moment.min.js']
                },
                {
                    files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
                },
                {
                    name: 'oitozero.ngSweetAlert',
                    files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
                }
					]);
				}
			}
		})
		.state('Requests.SingleRequestsTemplate', {
			url: "/SingleRequestsTemplate/:requestsId",
			templateUrl: "Views/SingleRequestTemplate.html",
			data: { pageTitle: 'SingleRequest View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
         {
              name: 'datePicker',
              files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
          },
          {
              files: ['../common/plugins/moment/moment.min.js']
          },
          {
              files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
          },
          {
              name: 'oitozero.ngSweetAlert',
              files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
          }
					]);
				}
			}
		})
		.state('Requests.RequestsType', {
			url: "/RequestsType",
			templateUrl: "Views/RequestsTypeTemplate.html",
			data: { pageTitle: 'RequestsType'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
                        serie: true,
                        files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
                    },
                    {
                        serie: true,
                        files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                    },
                    {
                        name: 'datatables',
                        files: ['../common/plugins/dataTables/angular-datatables.min.js']
                    }
					]);
				}
			}
		})
		.state('Requests.RequestsTypeCreation', {
			url: "/RequestsTypeCreation",
			templateUrl: "Views/RequestsTypeCreationTemplate.html",
			data: { pageTitle: 'RequestsType Creation'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
					{
                  serie: true,
                  files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
              },
                {
                    serie: true,
                    files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                },
                {
                    name: 'datatables',
                    files: ['../common/plugins/dataTables/angular-datatables.min.js']
                },
                {
                    name: 'datePicker',
                    files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                },
                {
                    files: ['../common/plugins/moment/moment.min.js']
                },
                {
                    files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
                },
                {
                    name: 'oitozero.ngSweetAlert',
                    files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
                }
					]);
				}
			}
		})
		.state('Requests.SingleRequestsTypeTemplate', {
			url: "/SingleRequestsTypeTemplate/:requestTypesId",
			templateUrl: "Views/SingleRequestsTypeTemplate.html",
			data: { pageTitle: 'SingleRequestType View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
         {
              name: 'datePicker',
              files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
          },
          {
              files: ['../common/plugins/moment/moment.min.js']
          },
          {
              files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
          },
          {
              name: 'oitozero.ngSweetAlert',
              files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
          }
					]);
				}
			}
		})


	
		.state('Merchants', {
			abstract: true,
			url: "/Merchants",
			templateUrl: "Views/common/merchants_side_navigation.html",
		})
			.state('Merchants.Merchant',{
			url: "/Merchant",
			templateUrl: "Views/MerchantTemplate.html",
			data: { pageTitle: 'Merchant View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/bootstrap/bootstrap-datetimepicker.css','../common/bootstrap/bootstrap-datepicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
					{
                        serie: true,
                        files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
                    },
                    {
                        serie: true,
                        files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                    },
                    {
                        name: 'datatables',
                        files: ['../common/plugins/dataTables/angular-datatables.min.js']
                    }
					]);
				}
			}
		})
			.state('Merchants.MerchantCreation', {
			url: "/MerchantCreation",
			templateUrl: "Views/MerchantCreationTemplate.html",
			data: { pageTitle: 'Merchant Creation'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
                    {
                        files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
                    },
                    {
                        name: 'oitozero.ngSweetAlert',
                        files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
                    }
					]);
				}
			}
		})
			.state('Merchants.SingleMerchant', {
			url: "/SingleMerchant/:merchantId",
			templateUrl: "Views/SingleMerchantTemplate.html",
			data: { pageTitle: 'SingleMerchant View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
					{
          serie: true,
            files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
              },
            {
                serie: true,
                files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
            },
            {
                name: 'datatables',
                files: ['../common/plugins/dataTables/angular-datatables.min.js']
            },
            {
					  files: ['../common/plugins/footable/footable.all.min.js', '../common/css/plugins/footable/footable.core.css']
					},
					{
					name: 'ui.footable',
					 files: ['../common/plugins/footable/angular-footable.js']
					},
          {
          name: 'datePicker',
           files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
          },
          {
              files: ['../common/plugins/moment/moment.min.js']
          },
          {
              files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
          },
          {
              name: 'oitozero.ngSweetAlert',
              files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
          }
					]);
				}
			}
		})
		.state('Merchants.MerchantCategoryCreation', {
			url: "/MerchantCategoryCreation",
			templateUrl: "Views/MerchantCategoryCreationTemplate.html",
			data: { pageTitle: 'Merchant Category Creation'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
                    {
                        files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
                    },
                    {
                        name: 'oitozero.ngSweetAlert',
                        files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
                    }
					]);
				}
			}
		})
		.state('Merchants.MerchantCategory',{
			url: "/MerchantCategory",
			templateUrl: "Views/MerchantCategoryTemplate.html",
			data: { pageTitle: 'Merchant Category View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/bootstrap/bootstrap-datetimepicker.css','../common/bootstrap/bootstrap-datepicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
					{
                        serie: true,
                        files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
                    },
                    {
                        serie: true,
                        files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                    },
                    {
                        name: 'datatables',
                        files: ['../common/plugins/dataTables/angular-datatables.min.js']
                    }
					]);
				}
			}
		})
			.state('Merchants.SingleMerchantCategory', {
			url: "/SingleMerchantCategory/:merchantCategoryId",
			templateUrl: "Views/SingleMerchantCategoryTemplate.html",
			data: { pageTitle: 'SingleMerchant Category View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
					{
                        serie: true,
                        files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
                    },
                    {
                        serie: true,
                        files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                    },
                    {
                        name: 'datatables',
                        files: ['../common/plugins/dataTables/angular-datatables.min.js']
                    },
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
                    {
                        files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
                    },
                    {
                        name: 'oitozero.ngSweetAlert',
                        files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
                    }
					]);
				}
			}
		})
		.state('Merchants.MerchantCategoryMarginCreation', {
			url: "/MerchantCategoryMarginCreation",
			templateUrl: "Views/MerchantCategoryMarginCreationTemplate.html",
			data: { pageTitle: 'Merchant CategoryMargin Creation'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
                    {
                        files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
                    },
                    {
                        name: 'oitozero.ngSweetAlert',
                        files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
                    }
					]);
				}
			}
		})
		.state('Merchants.MerchantCategoryMargin',{
			url: "/MerchantCategoryMargin",
			templateUrl: "Views/MerchantCategoryMarginTemplate.html",
			data: { pageTitle: 'Merchant CategoryMargin View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/bootstrap/bootstrap-datetimepicker.css','../common/bootstrap/bootstrap-datepicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
					{
                        serie: true,
                        files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
                    },
                    {
                        serie: true,
                        files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                    },
                    {
                        name: 'datatables',
                        files: ['../common/plugins/dataTables/angular-datatables.min.js']
                    }
					]);
				}
			}
		})
		.state('Merchants.SingleMerchantCategoryMargin', {
			url: "/SingleMerchantCategoryMargin/:merchantCategoryId",
			templateUrl: "Views/SingleMerchantCategoryMarginTemplate.html",
			data: { pageTitle: 'Single Merchant CategoryMargin View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
          {
	            name: 'datePicker',
	            files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
	        },
	        {
	            files: ['../common/plugins/moment/moment.min.js']
	        },
	        {
	            files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
	        },
	        {
	            name: 'oitozero.ngSweetAlert',
	            files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
	        }
					]);
				}
			}
		})

			.state('Cashbacks', {
			abstract: true,
			url: "/Cashbacks",
			templateUrl: "Views/common/Cashback_side_navigation.html",
		})
			.state('Cashbacks.Cashback',{
			url: "/Cashback",
			templateUrl: "Views/CashbackTemplate.html",
			data: { pageTitle: 'Cashback View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/bootstrap/bootstrap-datetimepicker.css','../common/bootstrap/bootstrap-datepicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
										{
                        serie: true,
                        files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
                    },
                    {
                        serie: true,
                        files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                    },
                    {
                        name: 'datatables',
                        files: ['../common/plugins/dataTables/angular-datatables.min.js']
                    }
					]);
				}
			}
		})
			.state('Cashbacks.SingleCashbackTransaction', {
			url: "/SingleCashbackTransaction/:cashbackTransactionId",
			templateUrl: "Views/SingleCashbackTemplate.html",
			data: { pageTitle: 'Single Cashback View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
          {
              name: 'datePicker',
              files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
          },
          {
              files: ['../common/plugins/moment/moment.min.js']
          },
          {
              files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
          },
          {
              name: 'oitozero.ngSweetAlert',
              files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
          }
					]);
				}
			}
		})
		
		.state('Offers', {
			abstract: true,
			url: "/Offers",
			templateUrl: "Views/common/Offers_side_navigation.html",
		})

			.state('Offers.OfferCreateView',{
			url: "/OfferCreateView",
			templateUrl: "Views/OfferCreationTemplate.html",
			data: { pageTitle: 'Offer Create View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/bootstrap/bootstrap-datetimepicker.css','../common/bootstrap/bootstrap-datepicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
					
					{
						files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
					},
					{
							name: 'oitozero.ngSweetAlert',
							files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
					}
					]);
				}
			}
		})
			.state('Offers.Offer',{
			url: "/OfferTemplate",
			templateUrl: "Views/OfferTemplate.html",
			data: { pageTitle: 'Offer View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/bootstrap/bootstrap-datetimepicker.css','../common/bootstrap/bootstrap-datepicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
										{
                        serie: true,
                        files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
                    },
                    {
                        serie: true,
                        files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                    },
                    {
                        name: 'datatables',
                        files: ['../common/plugins/dataTables/angular-datatables.min.js']
                    }
					]);
				}
			}
		})
		.state('Offers.SingleOffer', {
			url: "/SingleOffer/:offerId",
			templateUrl: "Views/SingleOfferTemplate.html",
			data: { pageTitle: 'SingleOffer View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
                    {
                        files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
                    },
                    {
                        name: 'oitozero.ngSweetAlert',
                        files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
                    }
					]);
				}
			}
		})
		
			.state('Transactions', {
			abstract: true,
			url: "/Transactions",
			templateUrl: "Views/common/Transaction_side_navigation.html",
		})
			.state('Transactions.TransactionTemplate',{
			url: "/TransactionTemplate",
			templateUrl: "Views/TransactionTemplate.html",
			data: { pageTitle: 'Transaction View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/bootstrap/bootstrap-datetimepicker.css','../common/bootstrap/bootstrap-datepicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
										{
                        serie: true,
                        files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
                    },
                    {
                        serie: true,
                        files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
                    },
                    {
                        name: 'datatables',
                        files: ['../common/plugins/dataTables/angular-datatables.min.js']
                    }
					]);
				}
			}
		})
		.state('Transactions.SingleTransaction', {
			url: "/SingleTransaction/:transactionId",
			templateUrl: "Views/SingleTransactionTemplate.html",
			data: { pageTitle: 'SingleTransaction View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
					{
						files: ['../common/css/plugins/steps/jquery.steps.css','../common/plugins/bootstrap-wizard/jquery.bootstrap.wizard.js','../common/plugins/bootstrap-wizard/form-wizard.js',"../common/jquery/jquery.validate.js","../common/plugins/select2/select2.min.css","../common/plugins/select2/select2.full.min.js",'../common/css/plugins/iCheck/custom.css','../common/plugins/iCheck/icheck.min.js']
					},
         {
              name: 'datePicker',
              files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
          },
          {
              files: ['../common/plugins/moment/moment.min.js']
          },
          {
              files: ['../common/plugins/sweetalert/sweetalert.min.js', '../common/css/plugins/sweetalert/sweetalert.css']
          },
          {
              name: 'oitozero.ngSweetAlert',
              files: ['../common/plugins/sweetalert/angular-sweetalert.min.js']
          }
					]);
				}
			}
		})
		.state('Cms', {
			abstract: true,
			url: "/Cms",
			templateUrl: "Views/common/Cms_side_navigation.html",
		})
		.state('Cms.CmsTemplate',{
			url: "/CmsTemplate",
			templateUrl: "Views/CmsTemplate.html",
			data: { pageTitle: 'Cms View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
                    {
                        files: ['../common/bootstrap/bootstrap-datetimepicker.css','../common/bootstrap/bootstrap-datepicker.js']
                    },
                    {
                        name: 'datePicker',
                        files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
                    },
                    {
                        files: ['../common/css/plugins/clockpicker/clockpicker.css', '../common/plugins/clockpicker/clockpicker.js']
                    },
                    {
                        files: ['../common/plugins/moment/moment.min.js']
                    },
                    {
                        name: 'cgNotify',
                        files: ['../common/css/plugins/angular-notify/angular-notify.min.css','../common/plugins/angular-notify/angular-notify.min.js']
	                }
                    // ,
										// {
          //               serie: true,
          //               files: ['../common/plugins/dataTables/jquery.dataTables.js','../common/css/plugins/dataTables/dataTables.bootstrap.css']
          //           },
          //           {
          //               serie: true,
          //               files: ['../common/plugins/dataTables/dataTables.bootstrap.js']
          //           },
          //           {
          //               name: 'datatables',
          //               files: ['../common/plugins/dataTables/angular-datatables.min.js']
          //           }
					]);
				}
			}
		})
		.state('CmsMobile', {
			abstract: true,
			url: "/CmsMobile",
			templateUrl: "Views/common/Cms_MobileSide_navigation.html",
		})
		.state('CmsMobile.CmsMobileTemplate',{
			url: "/CmsMobileTemplate",
			templateUrl: "Views/CmsMobileTemplate.html",
			data: { pageTitle: 'Cms Mobile View'},
			resolve:{
				loadPlugin:function($ocLazyLoad){
					return $ocLazyLoad.load([
					{
						files:['../common/css/plugins/icheck/custom.css','../common/plugins/icheck/icheck.min.js','../common/plugins/masonry/masonry.pkgd.min.js']
					},
					{
						files:['../common/plugins/masonry/angular-masonry.min.js']
					},
          {
              files: ['../common/bootstrap/bootstrap-datetimepicker.css','../common/bootstrap/bootstrap-datepicker.js']
          },
          {
              name: 'datePicker',
              files: ['../common/css/plugins/datapicker/angular-datapicker.css','../common/plugins/datapicker/angular-datepicker.js']
          },
          {
              files: ['../common/css/plugins/clockpicker/clockpicker.css', '../common/plugins/clockpicker/clockpicker.js']
          },
          {
              files: ['../common/plugins/moment/moment.min.js']
          },
          {
              name: 'cgNotify',
              files: ['../common/css/plugins/angular-notify/angular-notify.min.css','../common/plugins/angular-notify/angular-notify.min.js']
        	}
					]);
				}
			}
		})
			
}
angular
	.module('DEALMACHA')
	.config(config)
	.run(function($rootScope, $state) {
		$rootScope.$state = $state;
	});