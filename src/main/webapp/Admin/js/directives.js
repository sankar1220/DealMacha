/**
 * pageTitle - Directive for set Page title - mata title
 */
function pageTitle($rootScope, $timeout) {
	return {
		link: function(scope, element) {
			var listener = function(event, toState, toParams, fromState, fromParams) {
				// Default title - load on Dashboard 1
				var title = 'DEALMACHA | Responsive Admin Theme';
				// Create your own title pattern
				if (toState.data && toState.data.pageTitle) title = 'DEALMACHA | ' + toState.data.pageTitle;
				$timeout(function() {
					element.text(title);
				});
			};
			$rootScope.$on('$stateChangeStart', listener);
		}
	}
};

function processReqFactory($http){
	return {
			processReq:function(url,type,data,success,error){
				$http({
				url:url, 
				method:type,
				data:data,
				headers:{
				  'Content-Type': "application/json",
				   'Accept': "application/json"
				}
				}).success(success)
				.error(error);
			},
			search:function(){

			}
		}
}
function sideNavigation($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element) {
            // Call the metsiMenu plugin and plug it to sidebar navigation
            $timeout(function(){
                element.metisMenu();

            });
        }
    };
};
function baseURL () {
	return {
			IP:"http://192.168.0.73:8081"
		}
}

function mainMenu(){
	return {
		role:[
			{
				path:"HOME",
				url:"Dashboards.DashboardPage",
				include:"Dashboards",
			},
			{
				path:"USERS",
				url:"Users.User",
				include:"Users"
			},
			{
				path:"REQUESTS",
				url:"Requests.Request",
				include:"Requests"
			},
			{
				path:"MERCHANTS",
				url:"Merchants.Merchant",
				include:"Merchants"
			},
			{
				path:"CASHBACKS",
				url:"Cashbacks.Cashback",
				include:"Cashbacks"
			},
			{
				path:"OFFERS",
				url:"Offers.Offer",
				include:"Offers"
			},
			{
				path:"TRANSACTIONS",
				url:"Transactions.TransactionTemplate",
				include:"Transactions"
			},
			{
				path:"CMS",
				url:"Cms.CmsTemplate",
				include:"Cms"
			},
			{
				path:"CMS Mobile",
				url:"CmsMobile.CmsMobileTemplate",
				include:"CmsMobile"
			}
			
		]
	}
}
function fullScroll($timeout){
	return {
		restrict: 'A',
		link: function(scope, element) {
			$timeout(function(){
				element.slimscroll({
					height: '100%',
					overflow: 'scroll',
					railOpacity: 0.9
				});

			});
		}
	};
}
function ngEnter() {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.ngEnter);
                });
 
                event.preventDefault();
            }
        });
    }
}
function capitalize(){
	return {
		require: 'ngModel',
		link: function(scope, element, attrs, modelCtrl){
			var capitalize = function(inputValue){
				if(inputValue === undefined){
					inputValue = '';
				}
				var capitalized = inputValue.toUpperCase();
				if(capitalized !== inputValue){
					modelCtrl.$setViewValue(capitalized);
					modelCtrl.$render();
				}
				return capitalized;
			}
			modelCtrl.$parsers.push(capitalize);
			capitalize(scope[attrs.ngModel]);
		}
	}
}
function icheck($timeout) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function($scope, element, $attrs, ngModel) {
            return $timeout(function() {
                var value;
                value = $attrs['value'];

                $scope.$watch($attrs['ngModel'], function(newValue){
                    $(element).iCheck('update');
                })

                return $(element).iCheck({
                    checkboxClass: 'icheckbox_square-green',
                    radioClass: 'iradio_square-green'

                }).on('ifChanged', function(event) {
                        if ($(element).attr('type') === 'checkbox' && $attrs['ngModel']) {
                            $scope.$apply(function() {
                                return ngModel.$setViewValue(event.target.checked);
                            });
                        }
                        if ($(element).attr('type') === 'radio' && $attrs['ngModel']) {
                            return $scope.$apply(function() {
                                return ngModel.$setViewValue(value);
                            });
                        }
                    });
            });
        }
    };
}
function stringToNumber() {
  return {
    require: 'ngModel',
    link: function(scope, element, attrs, ngModel) {
      ngModel.$parsers.push(function(value) {
        return '' + value;
      });
      ngModel.$formatters.push(function(value) {
        return parseFloat(value, 10);
      });
    }
  };
}


/**
 * clockPicker - Directive for clock picker plugin
 */
function clockPicker() {
    return {
        restrict: 'A',
        link: function(scope, element) {
                element.clockpicker({
                	twelvehour : true,
                	default : 'now'

                });
        }
    };
};

/**
 * landingScrollspy - Directive for scrollspy in landing page
 */
function landingScrollspy(){
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            element.scrollspy({
                target: '.navbar-fixed-top',
                offset: 80
            });
        }
    }
}
function loadingView(){
	this.startLoading = function(text){
		if(text=="show"){
	    	return $("body").append('<div class="loadingModal"><div class="spiner-example '+text+'"><div class="sk-spinner sk-spinner-wave"><div class="sk-rect1"></div><div class="sk-rect2"></div><div class="sk-rect3"></div><div class="sk-rect4"></div></div></div></div>');
		}else{
			// return $("body").prepend('<div class="spiner-example '+text+'"><div class="sk-spinner sk-spinner-wave"><div class="sk-rect1"></div><div class="sk-rect2"></div><div class="sk-rect3"></div><div class="sk-rect4"></div></div></div>');
			return $('.loadingModal').remove()
		}
	}
}

function dataTablesInitService(){

				this.initDataTables  = function(dataset,columns,selector){
					var table = $(selector);

		/* Fixed header extension: http://datatables.net/extensions/keytable/ */

		var oTable = table.dataTable({

			// Internationalisation. For more info refer to http://datatables.net/manual/i18n
			"language": {
				"aria": {
					"sortAscending": ": activate to sort column ascending",
					"sortDescending": ": activate to sort column descending"
				},
				"emptyTable": "No data available in table",
				"info": "Showing _START_ to _END_ of _TOTAL_ entries",
				"infoEmpty": "No entries found",
				"infoFiltered": "(filtered1 from _MAX_ total entries)",
				"lengthMenu": "Show _MENU_ entries",
				"search": "Search:",
				"sPaginationType": "full_numbers",
				"zeroRecords": "No matching records found"
			},
			"data": dataset,
			"columns": columns,
			"bProcessing": true,
			"bDestroy": true,
			/*"aoColumnDefs": customizedColumn,*/
			"lengthMenu": [
				[5, 15, 20, -1],
				[5, 15, 20, "All"] // change per page values here
			],
			"pageLength": 5, // set the initial value,
			"searching": true,
			"columnDefs": [{  // set default column settings
				'orderable': false,
				'targets': [0]
			}, {
				"searchable": true,
				"targets": [0]
			}],
			"order": [
				[0, "asc"]
			]         
		});

		// var oTableColReorder = new $.fn.dataTable.ColReorder( oTable );

		var tableWrapper = $(selector+'_wrapper'); 
		var tableHeader = $(''+selector+' tfoot th');

		 // Setup - add a text input to each footer cell
	tableHeader.each( function () {
		var title = $(''+selector+' tfoot th').eq( $(this).index() ).text();
		var name = title.trim();
		$(this).html( '<input type="text" name="'+name+'" placeholder="SEARCH  '+name+'" class="input-table" />' );
	} );
 
	// DataTable
	// var tableData = $(''+selector+'').DataTable();
	
	// //Apply the search
	// tableData.columns().eq(0).each( function (colIdx) {
	//     var that = this;
	//     var column = tableData.column();
 
	//     $( 'input', tableData.column( colIdx ).footer() ).keyup( function () {

	//         $(''+selector+'').dataTable().fnFilter(this.value, colIdx);
	//         // if ( that.search() !== this.value ) {
	//         //     that
	//         //         .search( this.value )
	//         //         .draw();
	//         // }
	//     } );
	// } );
				
}
 }

/**
 *
 * Pass all functions into module
 */
angular
	.module('DEALMACHA')
	.directive('pageTitle', pageTitle)
    .directive('sideNavigation', sideNavigation)
	.directive('ngEnter',ngEnter)
	.directive('capitalize',capitalize)
	.directive('icheck', icheck)
	.factory('processReqFactory',processReqFactory)
	.service('baseURL',baseURL)
	.service('loadingView',loadingView)
	.service('mainMenu',mainMenu)
	.directive('fullScroll',fullScroll)
	.directive('stringToNumber',stringToNumber)
    .directive('clockPicker', clockPicker)
	.directive('landingScrollspy',landingScrollspy)
	.service('dataTablesInitService',dataTablesInitService)
   
