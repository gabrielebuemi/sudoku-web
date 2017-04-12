angular.module('services.sudoku', [])
.factory('SudokuService', ['$http', function($http) {
	return {
		
		submitGriglia: function(griglia) {
			var promise = $http({
				method : "POST",
				url : 'rest/',
				data : angular.toJson(griglia),
				headers : { 'Content-Type' : 'application/json' }

			});
			return promise;
		},
		
		generaGriglia:function generaGriglia() {
			var promise = $http({
				method : 'GET',
				url : 'rest/'
				// url : 'http://localhost:8080/sudoku-web/rest/' -> test json da browser
			});
			return promise;
		}
	}
}]);