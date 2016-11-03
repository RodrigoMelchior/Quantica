(function ()
{
    'use strict';

    angular
            .module('app.pages.private.feriado.feriadoController', [])
            .controller('FeriadoController', FeriadoController);

    /** @ngInject 
     * @author Matheus Herculano <matheus95df@gmail.com>
     * @param {type} ROWS_PAGE
     * @param {type} centroDeCustoService
     * @param {type} $stateParams
     * @param {type} $scope
     * @param {type} $mdDialog
     * @param {type} $mdMedia
     * @param {type} $state
     * @param {type} $mdToast
     * @param {type} $log
     * @param {type} $q
     * @returns {feriado_controller_L1.FeriadoController}
     */

    function FeriadoController(
            ROWS_PAGE,
            FeriadoService,
            UfService,
            municipioService,
            $stateParams,
            $scope,
            $mdDialog,
            $mdMedia,
            $state,
            $mdToast,
            $log,
            $q) {

        this.ROWS_PAGE = ROWS_PAGE;
        this.FeriadoService = FeriadoService;
        this.$stateParams = $stateParams;
        this.$scope = $scope;
        this.$mdDialog = $mdDialog;
        this.$mdMedia = $mdMedia;
        this.$state = $state;
        this.$mdToast = $mdToast;
        this.$log = $log;
        this.$q = $q;
        this.UfService = UfService;
        this.MunicipioService = municipioService;

        var $this = this;
        
        $this.listaUf = [] // iniciação de variavel
        $this.listaMunicipios = []; // iniciação de variavel
        
        $this.gridContent;
        $this.FeriadoService.getAll().then(function (response) {
            $this.gridContent = response.data;
        });


        if ($this.$state.current.name === 'app.feriado.visualizar') {
            $this.FeriadoService.getAll().then(function (response) {
                $this.gridContent = response.data;
                angular.forEach($this.gridContent, function (obj) {
                    if ($this.$stateParams.id == obj.id) {
                        $this.visualizar = obj;
                    }
                });
            });
        }

        if ($this.$state.current.name === 'app.feriado.incluir') {

            $this.carregarUf();
        }
    }


    FeriadoController.prototype.limparPesquisa = function () {
        var $this = this;

        $this.pesquisa = {};

    };

    FeriadoController.prototype.todosUf = function () {
        var $this = this;

//        $this.carregarUf();
        angular.forEach($this.uf, function (obj) {
            $this.listaUf.push(obj.id);
        });
    };

    FeriadoController.prototype.nenhumUf = function () {
        var $this = this;
        $this.listaUf = [];
    };
    
    FeriadoController.prototype.todosMunicipios = function (){
      var $this = this; 
      angular.forEach($this.municipio, function (obj) {
            $this.listaMunicipios.push(obj.id);
        });
    };
    
    FeriadoController.prototype.nenhumMunicipio = function (){
      var $this = this;
      
      $this.listaMunicipios = [];
    };

    FeriadoController.prototype.carregarUf = function () {
        var $this = this;

        var sucesso = function (response) {
            $this.uf = response.data;
        };

        var erro = function () {
            $this.$log.error("Falha ao carregar a lista de UF");
        };

        $this.UfService.get().then(sucesso, erro);
    };
    
    FeriadoController.prototype.carregarMunicipios = function (id){
        var $this = this;
        
        var sucesso = function (response) {
            $this.municipio = response.data;
        };

        var erro = function () {
            $this.$log.error("Falha ao carregar a lista de municipios");
        };
        
        $this.MunicipioService.get(id).then(sucesso, erro);
    };
    
    FeriadoController.prototype.delete = function (evt) {
        var $this = this;
        var confirm = $this.$mdDialog.confirm()
                .title('Deseja excluir o registro selecionado?')
                .textContent()
                .ariaLabel('Lucky day')
                .targetEvent(evt)
                .clickOutsideToClose(true)
                .parent(angular.element(document.body))
                .ok('Sim')
                .cancel('NÃ£o');
        $this.$mdDialog.show(confirm).then(function () {

        });
    };
    
    FeriadoController.prototype.cancelar = function (evt){
        var $this = this;
        var confirm = $this.$mdDialog.confirm()
                .title('Deseja cancelar?')
                .textContent()
                .ariaLabel('Lucky day')
                .targetEvent(evt)
                .clickOutsideToClose(true)
                .parent(angular.element(document.body))
                .ok('Sim')
                .cancel('NÃ£o');
        $this.$mdDialog.show(confirm).then(function () {
            $this.$state.go('app.feriado');
        });
    };


})();
