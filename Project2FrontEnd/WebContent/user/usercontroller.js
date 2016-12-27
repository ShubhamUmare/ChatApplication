app.controller('UserController',function($scope,$rootScope,$location,UserService){
	$scope.users;
	$scope.user={id:'',username:'',password:'',email:'',role:'',isOnline:'',enabled:''};
	$scope.message;
	
	function fetchAllUsers(){
		console.log('entering fetchall users in controller')
		UserService.fetchAllUsers()
		.then(
				function(d){
					$scope.persons=d;
				},
				function(error){
					console.log(error);
				})
		}
	fetchAllUsers();
	
	$scope.submit=function(){
		console.log('Entering - submit function in usercontroller')
		UserService.authenticate($scope.user)
		.then(function(response){
			
				  $scope.user=response.data;
				  $rootScope.currentUser=$scope.user;
				  $location.path("/home");
				
		},
		function(response)
		{
			console.log('Invalid username and password')
			$scope.message="Invalid username and password";
			$location.path("/login");
		
						
		})
	}
	
	
	$scope.registerUser=function(){
		console.log('entering registerUser')
		UserService.registerUser($scope.user)
		.then(function(response){ //success 
			console.log("registration success" + response.status)
			$scope.message="Regsitration successful...Please Logon with Username and Password"
			$location.path("/login");
		},function(response){//failure
			console.log("registration failed" + response.status)
			console.log(response.data)
			$scope.errorMessage="Registration failed...." + response.data.message
			$location.path("/register")
		})
	}
	
	
	$rootScope.logout=function()
	{
		console.log('logout function')
		delete $rootScope.currentUser;
		
		UserService.logout()
		.then(function(response){
			console.log("Logged out successfully..");
			$scope.message="Logged out Sucessfully";
			$location.path("/login")
		},
		function(response){
			console.log(response.status)
		})
	}
})