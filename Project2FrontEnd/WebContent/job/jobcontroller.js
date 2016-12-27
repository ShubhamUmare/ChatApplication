app.controller('JobController',function($scope,$location,JobService)
		{
		$scope.jobs={}
		$scope.job={jobTitle:'',jobDescription:'',skillsRequired:'',salary:'',location:''}
		
		function getAllJobs(){
			
			console.log("Entering in getALLJobs.....")
			JobService.getAllJobs()
			.then(function(response){
				console.log(response.status)
				$scope.jobs=response.data
			},function(response){
				console.log(response.status);	
			}
		)
		}	
		getAllJobs();
		
		
		$scope.saveJob=function(){
				console.log('entering save job in job controller')
				JobService.saveJob($scope.job)
				.then(function(response)
					{
						console.log("successfully inserted job details")
						alert("Posted job successfully");
					},function(response){
						console.log("failure" +response.status);
						console.log(response.data.message)
					})

		}
		
		
		$scope.deleteJob=function(jobId)
		{
			console.log('entering delete Job in controller with Job id:'+jobId)
			JobService.deleteJob(jobId)
			.then(
					function(d){
				console.log('deleted successfully')
				console.log(d)
				getAllJobs();
				$location.path('/displayJobTitle.html')
			},function(){
				console.log("unable to delete the record")
			})

		}
		
		
	})