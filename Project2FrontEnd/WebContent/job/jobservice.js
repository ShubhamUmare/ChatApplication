app.factory('JobService',function($http){
	var jobService={};
	var BASE_URL="http://localhost:8085/Project2BackEnd"
		jobService.saveJob=function(job){
		return $http.post(BASE_URL + "/postJob",job);
		
	}
	
	jobService.getAllJobs=function(){
		return $http.get(BASE_URL + "/getAllJobs");
	}
	
	jobService.deleteJob=function()
	{
		return $http.delete(BASE_URL + "/job/{jobId}");
	}
	return jobService; 
})