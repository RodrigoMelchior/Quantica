(function ()
{
    'use strict';

    angular
            .module('app.pages.public.login')
            .controller('RedefinirSenhaController', RedefinirSenhaController);

    function RedefinirSenhaController($scope, $mdDialog, $mdMedia, $mdToast, $state, Authentication, LOGIN_STATE) {
        $scope.newPassForm = {};

        $scope.resetPasswordFinish = function (keyAndPassword) {
            Authentication.resetPasswordFinish(keyAndPassword).then(function () {
                $mdToast.show(
                            $mdToast.simple()
                            .textContent('Senha alterada com sucesso.')
                            .position('right')
                            .hideDelay(5000)
                            );
                $state.go(LOGIN_STATE);
            }).catch(function () {
                $mdToast.show(
                            $mdToast.simple()
                            .textContent('Falha ao alterar a senha.')
                            .position('right')
                            .hideDelay(5000)
                            );
            }); 
        };

        $scope.compararSenha = function (senha1, senha2) { // Fun��o para verificar se as senhas s�o iguais
            senha1 === senha2 ? $scope.senhasIguais = false : $scope.senhasIguais = true;

        };


    }
})();
