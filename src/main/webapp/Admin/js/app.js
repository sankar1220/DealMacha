(function(){
	angular.module('DEALMACHA',[
		'ui.router',    						//routing         
		'oc.lazyLoad',   						//ocLazyLoad
		'ui.bootstrap' , 						//ui Bootstrap
		'pascalprecht.translate',   //Angular Translate
		'ngIdle',										//Idle Timer
		'ngSanitize'          			//ngSanitize
		     
	])
	
})();

//Other Libraries are loaded dynamically in the config.js file using the library ocLazyLoad