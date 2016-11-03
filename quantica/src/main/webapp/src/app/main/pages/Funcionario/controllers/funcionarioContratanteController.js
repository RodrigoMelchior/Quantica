(function ()
{
    'use strict';

    angular
            .module('app.pages.funcionario')
            .controller('funcionarioContratanteController', FuncionarioContratanteController);

    function FuncionarioContratanteController($scope, $mdDialog, $mdMedia, $stateParams, $location, LOCALIDADE) {
        var vm = this;
        
        $scope.showConfirm = function (ev) {
            // Appending dialog to document.body to cover sidenav in docs app
            var confirm = $mdDialog.confirm()
                    .title('Would you like to delete your debt?')
                    .textContent('All of the banks have agreed to forgive you your debts.')
                    .ariaLabel('Lucky day')
                    .targetEvent(ev)
                    .clickOutsideToClose(true)
                    .parent(angular.element(document.body))
                    .ok('Please do it!')
                    .cancel('Sounds like a scam');
            $mdDialog.show(confirm).then(function () {
                $scope.status = 'You decided to get rid of your debt.';
            }, function () {
                $scope.status = 'You decided to keep your debt.';
            });
        };
    

        vm.gridContent = [
            {id: "1",
                nome: "Luis Carlos",
                cpf: "00000000000",
                login: "121s3d5",
                telefone: "6134343334",
                endereco: "Meu Endereco as754",
                uf: "DF",
                cidade: "Plano Plito"
            },
            {
                id: "2",
                nome: "Robson",
                cpf: "11111111111",
                login: "5s4d45s",
                telefone: "6133333333",
                endereco: "Meu Endereco as754",
                uf: "DF",
                cidade: "Plano Plito"
            },
            {
                id: "3",
                nome: "Matheus",
                cpf: "33333333333",
                login: "19sdrr",
                telefone: "6144444444",
                endereco: "Meu Endereco as754",
                uf: "DF",
                cidade: "Plano Plito"
            }
        ];

    }
})();