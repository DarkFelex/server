angular
	.module('app', ['ui.router', 'chat'])
	.config(function($stateProvider) {
	  
		$stateProvider.state('main',{
			abstract : true,
			views:{
				'mainView':{
					templateUrl: '/views/main.html'
				},
				'chat@main':{
					templateUrl: '/views/chat.html'
				}
			}
			
		});
		
		$stateProvider.state('main.oldIndex',{
			url: '/oldIndex',
			views:{
				'content@main':{
					templateUrl: '/views/old.html'
				},
			}
			
		});

		
		$stateProvider.state('main.map',{
			url: '/map',
			views:{
				'content@main':{
					templateUrl: '/views/map.html'
				}
			}
			
		});
	  
	  	$stateProvider.state('main.city',{
			url: '/city',
			views:{
				'content@main':{
					templateUrl: '/views/city.html'
				}
			}
			
		});
		
		

		
		

	})
	//.config(["$locationProvider", function($locationProvider) {
	//  $locationProvider.html5Mode(true);
	//}]) раскоментировать после переноса на сервер и добавить base в head
		.run(function($state) { $state.go('main.oldIndex');});
	