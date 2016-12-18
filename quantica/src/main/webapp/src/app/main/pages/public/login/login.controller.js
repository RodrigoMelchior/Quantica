(function () {
    'use strict';

    angular
        .module('app.pages.public.login')
        .controller('LoginController', LoginController);

    function LoginController($scope, $mdDialog, $mdMedia, $state, $mdToast, $http, LoginService) {
        $scope.form = {};

        $scope.modalPrimeiroAcesso = function () {
            var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;
            $mdDialog.show({
                controller: DialogController,
                templateUrl: 'app/main/pages/public/login/senha-modal.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true,
                fullscreen: useFullScreen
            })
            $scope.$watch(function () {
                return $mdMedia('xs') || $mdMedia('sm');
            }, function (wantsFullScreen) {
                $scope.customFullscreen = (wantsFullScreen === true);
            });
        };

        function DialogController($scope, $mdDialog, $mdToast, $http, $state) {
            $scope.form = {};

            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            $scope.alteraSenha = function() {
              if ($scope.form.newPassword === $scope.form.repeatPassword) {
                var alterar = {
                  novaSenha : $scope.form.newPassword
                };
                  
                var usuario = $rootScope.usuarioLogado;
                usuario.senha = $scope.form.newPassword;
                usuario.trocaSenha = 'N';
                
                LoginService.editar(usuario).then(function (response) {
                        $scope.data = response.data;
                        $rootScope.usuarioLogado = $scope.data;
                        $state.go('app.dashboard');
                        $state.go('app.selecionaPesquisa');
                        $mdToast.showSimple('Senha alterada com sucesso!');
                });
                $mdDialog.cancel();
              } else {
                $mdToast.showSimple('As senhas digitadas devem ser iguais!');
              }
            };
        }

        $scope.login = function () {
            var login = {
              login : $scope.form.username,
              senha   : $scope.form.password
            };
            LoginService.logar(login).then(function (response) {

                if (response.data == ""){
                    $mdToast.showSimple('Usuário e/ou senha inexistente!');                    
                }else{
                    $scope.data = response.data;
                    localStorage.setItem('idUsuarioLogado', $scope.data.id);
                    localStorage.setItem('idEmpresaUsuario', $scope.data.empresa.id);
                    if ($scope.data.perfis.length < 1){
                        $mdToast.showSimple('Usuário sem perfil!');  
                    }else{
                        localStorage.setItem('idPerfil', $scope.data.perfis[0].id);
                        if ($scope.data.trocaSenha === 'S'){
                            $scope.modalPrimeiroAcesso();
                        }else{
                            if ($scope.data.perfis[0].id == 2){
                                $state.go('app.selecionaPesquisa');
                            }else{
                                $state.go('app.dashboard');
                            }
                        }
                    }
                }
            });
        }
    }
})();
