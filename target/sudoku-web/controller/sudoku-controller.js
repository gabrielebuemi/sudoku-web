
app.controller("SudokuController", function($scope, $http, SudokuService) {

	$scope.slider = {
			value: 12,
			options: {
				showSelectionBar: true,
				getSelectionBarColor: function(value) {
					if (value <= 3)
						return 'red';
					if (value <= 6)
						return 'orange';
					if (value <= 9)
						return 'yellow';
					return '#2AE02A';
				}
			}
	};
	
	$scope.griglia = [];
	
	$scope.rngESUS = Math.round((Math.random() * 10) * 10);
	
	// on load
	SudokuService.generaGriglia().then(
		function successCallback(response) {
			$scope.griglia = response.data;
			console.log($scope.griglia);

		}, function errorCallback(response) {
			console.log(response.statusText);
		}
	);	

	$scope.submitGriglia = function() {
		var isCompleto = true;
		angular.forEach($scope.griglia, function(value, riga) {
			angular.forEach(value, function(valueCella, colonna) {
				if ( valueCella.valore == "" ) {
					valueCella.corretto = false;
					isCompleto = false;
				} 
			});
		});
		if (!isCompleto) {
			alert("Completa sudoku prima di verificarlo! ");
			return;
		} 
		
		SudokuService.submitGriglia($scope.griglia).then(
			function successCallback(response) {
				$scope.griglia = response.data;
				var isOk = true;
				angular.forEach($scope.griglia, function(value, riga) {
					angular.forEach(value, function(valueCella, colonna) {
						if ( !valueCella.corretto ) {
							isOk = false;
						} 
					});
				});

				if (isOk) {
					alert("Complimenti, sudoku risolto!");
				} else {
					alert("Sodoku non risolto!");
				}

			}, function errorCallback(response) {
				console.log(response.statusText);
			}
		);
	};	
	
	$scope.mostraSoluzione = function() {
		console.log($scope.griglia);
		angular.forEach($scope.griglia, function(value, riga) {
			angular.forEach(value, function(valueCella, colonna) {
				$scope.griglia[riga][colonna].valore = valueCella.valoreCorretto;
				$scope.griglia[riga][colonna].corretto = true;
			});
		});
	};
	
	$scope.ripristinaGriglia = function() {
		console.log($scope.griglia);
		angular.forEach($scope.griglia, function(value, riga) {
			angular.forEach(value, function(valueCella, colonna) {
				if ( valueCella.daSettare ) {
					$scope.griglia[riga][colonna].valore = 0;
					$scope.griglia[riga][colonna].corretto = true;
				}
			});
		});
	};
	
	$scope.rimuoviErrori = function(riga, colonna) {
		console.log(Math.floor((Math.random() * 81) + 1));
		$scope.griglia[riga][colonna].corretto = true;
	};
	
//	$scope.suggerisciSingoloNumero = function(riga, colonna) {
//		var esci = false;
//		var indiceRiga = Math.floor((Math.random() * 8) + 0);
//		var indiceColonna = Math.floor((Math.random() * 8) + 0);
//		$scope.griglia[riga][colonna].corretto = true;
//		
//	};
	
});
