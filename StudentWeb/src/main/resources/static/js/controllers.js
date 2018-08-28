var studentWebApp = angular.module('studentWebApp', []);

studentWebApp.controller('StudentWebCtrl', function ($scope, $http) {
	var urlBase="http://localhost:8080/";
	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";

	// get all the students
	$http.get(urlBase + '/api/student').
	     success(function(data) {
	         $scope.students = data;
         }
    );

	// add a new student
	$scope.add = function add() {
		if($scope.firstName == "" || $scope.lastName == "" || $scope.graduationYear == "" || $scope.gpa == "") {
		   alert("Insufficient Data! Please provide values for First Name, Last Name, Graduation Year, and Grade Point Average.");
		}
		else {
		    $http.post(urlBase + '/api/student/' + $scope.firstName + '/' + $scope.lastName + '/' + $scope.graduationYear + '/' + $scope.gpa).
		       success(function(data) {
		    	   	$scope.students.push(data);
			    	$scope.firstName = data.firstName;
			    	$scope.lastName = data.lastName;
			    	$scope.studentId = data.studentId;
			    	$scope.graduationYear = data.graduationYear;
			    	$scope.gpa = data.gpa;
			    	$('#new').hide();
			    	$('#view').show();
		       });
		  	}
	};

	// load a student
	$scope.load = function load(studentId) {
		$http.get(urlBase + '/api/student/' + studentId).
	     success(function(data) {
	    	 $scope.firstName = data.firstName;
	    	 $scope.lastName = data.lastName;
	    	 $scope.studentId = data.studentId;
	    	 $scope.graduationYear = data.graduationYear;
	    	 $scope.gpa = data.gpa;
	    	 $('#new').hide();
	    	 $('#view').show();
        });
	};

	// show add function
	$scope.showAdd = function showAdd() {
		$scope.firstName = "";
		$scope.lastName = "";
		$scope.studentId = "";
		$scope.graduationYear = "";
		$scope.gpa = "";
		$('#view').hide();
		$('#new').show();
	};
	
	// edit student from view
	$scope.showEdit = function showEdit(studentId) {
		$http.get(urlBase + '/api/student/' + studentId).
	     success(function(data) {
	    	 $scope.firstName = data.firstName;
	    	 $scope.lastName = data.lastName;
	    	 $scope.studentId = data.studentId;
	    	 $scope.graduationYear = data.graduationYear;
	    	 $scope.gpa = data.gpa;
	    	 $('#view').hide();
	    	 $('#edit').show();
	     });
	};

	// update a student
	$scope.update = function update(studentId) {
		if($scope.firstName == "" || $scope.lastName == "" || $scope.graduationYear == "" || $scope.gpa == "") {
			   alert("Insufficient Data! Please provide values for First Name, Last Name, Graduation Year, and Grade Point Average.");
		}
		else {
		    $http.put(urlBase + '/api/student/' + $scope.studentId + '/' + $scope.firstName + '/' + $scope.lastName + '/' + $scope.graduationYear + '/' + $scope.gpa).
		       success(function(data) {
		    	   $scope.firstName = data.firstName;
		    	   $scope.lastName = data.lastName;
		    	   $scope.studentId = data.studentId;
		    	   $scope.graduationYear = data.graduationYear;
		    	   $scope.gpa = data.gpa;
		    	   $('#edit').hide();
		    	   $('#view').show();
		       });
	  	}
	};
	
	// delete a student
	$scope.deleteStudent = function deleteStudent(studentId) {
		var response = confirm("Do you want to delete " + $scope.firstName + $scope.lastName + "?");
		if (response == true) {
			$http.delete(urlBase + '/api/student/' + studentId).
				success(function(data) {
					$scope.students = data;
					$scope.firstName = "";
					$scope.lastName = "";
					$scope.studentId = "";
					$scope.graduationYear = "";
					$scope.gpa = "";
					$('#view').hide();
					$('#new').show();
				});
		}
	}
	
	$scope.orderProp = 'firstName';
});