(function ()
{
    'use strict';
    angular
            .module('app.pages.private.loginUsuario')
            .controller('loginUsuarioController', LoginUsuarioController);
    /** @ngInject 
     * 
     * @param {type} $stateParams
     * @param {type} $mdToast
     * @param {type} $scope
     * @param {type} $state
     * @param {type} $mdDialog
     * @param {type} $mdMedia
     * @param {type} LoginUsuarioService
     * @returns {undefined}
     */
    function LoginUsuarioController(
            $stateParams,
            $mdToast,
            $scope,
            $state,
            $mdDialog,
            $mdMedia,
            LoginUsuarioService) {

        this.$stateParams = $stateParams;
        this.$mdToast = $mdToast;
        this.$scope = $scope;
        this.$state = $state;
        this.$mdDialog = $mdDialog;
        this.$mdMedia = $mdMedia;
        this.LoginUsuarioService = LoginUsuarioService;

        var $this = this;


        $this.pesquisa = $this.LoginUsuarioService.getPesquisaParams();

        $this.gridContent = [
            {
                id: "1",
                nome: "Matheus Herculano",
                login: "matheus.herculano",
                ativo: "Sim"
            },
            {
                id: "2",
                nome: "Fulano Silva",
                login: "fulano.silva",
                ativo: "Não"
            }
        ];

//carregar dados para visualizar
        if ($this.$state.current.name === 'app.loginUsuario.visualizar') {
            angular.forEach($this.gridContent, function (obj) {
                if ($this.$stateParams.id === obj.id) {
                    $this.vizualizarForm = obj;
                }
            });
        }

        if ($this.$state.current.name === 'app.loginUsuario.incluir') {
            $this.usuario = {ativo: true}; //valor para iniciar ativo marcado
            angular.forEach($this.gridContent, function (obj) {
                if ($this.$stateParams.id === obj.id) {
                    $this.usuario = obj;
                }
            });
        }

        //carregar dados para editar
        if ($this.$state.current.name === 'app.loginUsuario.editar') {
            angular.forEach($this.gridContent, function (obj) {
                if ($this.$stateParams.id === obj.id) {
                    $this.usuario = obj;
                }
            });
        }

    }

    LoginUsuarioController.prototype.limparPesquisa = function () {
        var $this = this;
        $this.pesquisa = {};
        $this.LoginUsuarioService.setPesquisaParams($this.pesquisa);
    };

    LoginUsuarioController.prototype.pesquisar = function () {
        var $this = this;

        if (!$this.pesquisa.hasOwnProperty('login') &&
                !$this.pesquisa.hasOwnProperty('nome') &&
                !$this.pesquisa.hasOwnProperty('ativo')) {

            $this.$mdToast.show(
                    $this.$mdToast.simple()
                    .textContent('Por favor, informe ao menos algum dos par\u00e2metros!')
                    .position('right')
                    .hideDelay(5000)
                    );
        } else {
            console.log("de");
//            this.carregarGrid();
        }
    };

    LoginUsuarioController.prototype.cancelar = function (evt) {
        var $this = this;
        var confirm = $this.$mdDialog.confirm()
                .title('Deseja cancelar?')
                .textContent()
                .ariaLabel('Lucky day')
                .targetEvent(evt)
                .clickOutsideToClose(true)
                .parent(angular.element(document.body))
                .ok('Sim')
                .cancel('Não');
        $this.$mdDialog.show(confirm).then(function () {
            $this.$state.go('app.loginUsuario');
        });
    };

    LoginUsuarioController.prototype.delete = function (evt) {
        var $this = this;
        var confirm = $this.$mdDialog.confirm()
                .title('Deseja excluir o registro selecionado?')
                .textContent()
                .ariaLabel('Lucky day')
                .targetEvent(evt)
                .clickOutsideToClose(true)
                .parent(angular.element(document.body))
                .ok('Sim')
                .cancel('Não');
        $this.$mdDialog.show(confirm).then(function () {

        });
    };

})();
