app.factory('UserService',function($http){
	var userService=this;
	var BASE_URL="http://localhost:8085/Project2BackEnd"
		
	userService.fetchAllUsers=function(){
		console.log('entering fetchallusers in service')
		return $http.get(BASE_URL+"/users")
		.then(function(response){
			//response - object returned from the back end
			//data, status, headers,statusText
			//data- list of persons
			console.log(response.data)
			console.log(response.status)
			return response.data
			
		},
		function(response){
			console.log(response.data)
			return response.data
		})
	};	
	
	
	userService.authenticate=function(user){
		return $http.post(BASE_URL + "/login",user);
	}
	userService.registerUser=function(user){
		return $http.post(BASE_URL +"/register",user);
	};
	
	userService.logout=function(){
		console.log("Entering in Logout function");
		return $http.put(BASE_URL +"/logout");
	}
	return userService;
})
