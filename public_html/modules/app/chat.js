var chat = angular
	.module('chat', ['ngWebSocket'])
    .factory('ChatWebSocketService', function($websocket) {

      var dataStream = $websocket('ws://localhost:8080/chat');

	  var data = [];
	  	  
	  
      dataStream.onMessage(function(e) {
	    data.push(JSON.parse(e.data));
      });
	   
      var methods = {
		getData : function() {return data;},
        send : function(userMessage) {
          dataStream.send(JSON.stringify({ 
			"name" : "login name",
			"message" : userMessage,
			"sessionId" : "session id",
			"isSystemMsg" : false,
			"alert" : true,
			"sendTime" :"00:00"
		  }));
        }
      };
	  


      return methods;
    })
    .controller('ChatController', function ($scope, ChatWebSocketService) {

	  $scope.data = ChatWebSocketService.getData();
	  $scope.isVisible = false;


	  $scope.sendMessage = function (){
		ChatWebSocketService.send($scope.chatText);
	  }
	  function refresh(){
			for (var i = 0; i < $scope.data.length; i++) {
			  if ($scope.data[i]!= undefined && $scope.data[i].alert){ 
				$scope.data[i].alert = false;
			  }
			}
		}
		

	  $scope.toggleVisibility = function (){
		$scope.isVisible = !$scope.isVisible;
		if ($scope.isVisible) {
			refresh();
			
		}
		
	  }
	  
	  $scope.getMessagesCount = function() {
		if (!$scope.isVisible){
			var count = 0;
			for (var i = 0; i < $scope.data.length; i++) {
			  if ($scope.data[i]!= undefined && $scope.data[i].alert)count++;
			}
			if (count) return count;
			
		}
		else refresh();
	}
	  
    });