var app=angular.module("myApp",['ngRoute'])
app.config(function($routeProvider){
	console.log('entering configuration')
	$routeProvider
	.when('/ListAllUsers',{
		controller:'UserController',
		templateUrl:'user/ListAllUsers.html'
	})
	.when('/login',{
		controller:'UserController',
		templateUrl:'user/login.html'
	})
	.when('/register',{
		controller:'UserController',
		templateUrl:'user/register.html'
	})
	.when('/home',{
		templateUrl:'home/home.html'
	})
	.when('/postJob',{
		controller:'JobController',
		templateUrl:'job/createJob.html'
	})
	.when('/getAllJobs',{
		controller:'JobController', //to get all jobs from backend
		templateUrl:'job/displayJobTitle.html'//to siplay the job title in html page
	})
	.when('/job/{jobId}',{
		controller:'JobController',
		templateUrl:'job/displayJobTitle.html'
	})
})
