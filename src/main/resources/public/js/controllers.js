'use strict';

var controllers = angular.module('controllers', []);

controllers.controller('UploadController',['$scope', '$http', function($scope, $http) {
    
    $scope.sizeLimit      = 10585760; // 10MB in Bytes
    $scope.uploadProgress = 0;
    $scope.creds          = {};

    $scope.upload = function() {
    	
    	if ($scope.file) {
    		$http.post('/api/presigned', {name: $scope.file.name, type: $scope.file.type })
	      		.success(function(resp) {
					console.log(resp);
	      			$http.put(resp.url, $scope.file, {headers: {'Content-Type': $scope.file.type}})
	        			.success(function(resp) {
	          				toastr.success('File Uploaded Successfully', 'Done');
	        			})
						.error(function(resp) {
							console.error(resp);
	          				toastr.error("An Error Occurred Attaching Your File");
						}
					);
	    		}).error(function(resp) {
					console.error(resp);
	      			toastr.error("An Error Occurred Attaching Your File");
	    		}
			);
    	}
    	else {
    		toastr.error('Please select a file to upload');
    	}
    };

    $scope.fileSizeLabel = function() {
		return Math.round($scope.sizeLimit / 1024 / 1024) + 'MB';
    };

    $scope.uniqueString = function() {
        var text     = "";
        var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for( var i=0; i < 8; i++ ) {
            text += possible.charAt(Math.floor(Math.random() * possible.length));
        }
        return text;
    }

}]);
