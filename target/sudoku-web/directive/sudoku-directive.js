//var app = angular.module('SudokuFilter', []);

app.directive('isValidNumberSudoku', function () {
	return {
		require: 'ngModel',
		link: function (scope) {	
			scope.$watch('colonne.valore', function(newValue,oldValue) {
                var arr = String(newValue).split("");
                if (arr.length === 0) return;
                if (arr.length === 1 && (arr[0] == '-' || arr[0] === '.' )) return;
                if (arr.length === 2 && newValue === '-.') return;
                if (isNaN(newValue)) {
                	 scope.colonne.valore = "";
                } else if (newValue < 1 || newValue > 9) {
                	scope.colonne.valore = "";
                }
            });
		}
	};
});