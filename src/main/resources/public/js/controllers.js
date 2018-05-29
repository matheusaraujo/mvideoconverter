'use strict';

var STATE_INITIAL = "glyphicon glyphicon-play-circle text-muted";
var STATE_LOADING = "glyphicon glyphicon-refresh gly-spin";
var STATE_DONE = "glyphicon glyphicon-ok-circle text-success";
var STATE_ERROR = "glyphicon glyphicon-remove-circle text-danger";

var controllers = angular.module('controllers', []);

controllers.controller('UploadController',['$scope', '$http', '$timeout', '$sce', function($scope, $http, $timeout, $sce) {
    
	$scope.fileName = "";
	$scope.fileType = "";
	$scope.fileSize = "";
	$scope.fileDate = "";
	
	$scope.step1class = STATE_INITIAL;
	$scope.step2class = STATE_INITIAL;
	$scope.step3class = STATE_INITIAL;
	$scope.step4class = STATE_INITIAL;

	$scope.started = false;
	$scope.finished = false;
	$scope.videoUrl = null;

	$scope.$watch('file', function(newf, oldf) {
		if (newf) {
			$scope.fileName = newf.name;
			$scope.fileType = newf.type;
			$scope.fileSize = Math.round(newf.size / 1024 ) + 'kb';
			$scope.fileDate = newf.lastModifiedDate;
		}
	});

	$scope.newConversion = function () {
		window.location.reload();
	};

	$scope.startUpload = function () {

		$scope.started = true;

		$scope.step1class = STATE_INITIAL;
		$scope.step2class = STATE_INITIAL;
		$scope.step3class = STATE_INITIAL;
		$scope.step4class = STATE_INITIAL;
		
	};

    $scope.upload = function() {

    	if (!$scope.file) {
			toastr.error('Please select a file to upload');
    	}
    	else if ($scope.file.type.indexOf('video') === -1) {
    		toastr.error('Please select a video file to upload');	
    	}
    	else {
    		$scope.startUpload();
    		$scope.step1class = STATE_LOADING;
    		$http.post('/api/presigned', {name: $scope.file.name, type: $scope.file.type })
	      		.success($scope.step1success)
				.error($scope.step1error);
    	}
    };

    $scope.step1success = function(resp) {
		$scope.step1class = STATE_DONE;
		$scope.step2class = STATE_LOADING;
		console.info(resp);

		$http.put(resp.url, $scope.file, {headers: {'Content-Type': $scope.file.type}})
			.success($scope.step2success)
			.error($scope.step1error);
	};

	$scope.step1error = function(resp, code) {
		$scope.step1class = STATE_ERROR;
		$scope.errorCallback(resp, code);
	};

	$scope.step2success = function(resp) {
		$scope.step2class = STATE_DONE;
		$scope.step3class = STATE_LOADING;
		console.info(resp);

		$http.post('/api/conversion', {name: $scope.file.name})
			.success($scope.step3success)
			.error($scope.step3error);
	};

	$scope.step2error = function(resp, code) {
		$scope.step2class = STATE_ERROR;
		$scope.errorCallback(resp, code);
	};

	$scope.step3success = function (resp) {
		$scope.step3class = STATE_DONE;
		$scope.step4class = STATE_LOADING;
		console.info(resp);

		$scope.videoUrl = resp.path;

		$http.get('/api/conversion/' + resp.id)
			.success($scope.step4success)
			.error($scope.step4error);
	};

	$scope.step3error = function (resp, code) {
		$scope.step3class = STATE_ERROR;
		$scope.errorCallback(resp, code);
	};

	$scope.step4success = function (resp) {
		if (resp.state == 'waiting' || resp.state == 'processing') {
			$timeout(function () {
				$http.get('/api/conversion/' + resp.id)
					.success($scope.step4success)
					.error($scope.step4error);
			}, 1000);
		}
		else if (resp.state == 'finished') {
			$scope.step4class = STATE_DONE;
			$scope.finished = true;
			toastr.success('File converted!');
		}
		else {
			$scope.step4class = STATE_ERROR;
			console.error(resp);
			toastr.error("An Error Occurred Attaching Your File");
		}
	};

	$scope.step4error = function (resp, code) {
		$scope.step4class = STATE_ERROR;
		$scope.errorCallback(resp, code);
	};

	$scope.trustSrc = function(src) {
    	return $sce.trustAsResourceUrl(src);
  	};

  	$scope.errorCallback = function(resp, code) {
  		console.log('error - ' + code);
  		console.log(resp);
  		if (code === 400)
  			toastr.error(resp)
  		else
  			toastr.error("An Error Occurred Attaching Your File");
  	}

}]);
