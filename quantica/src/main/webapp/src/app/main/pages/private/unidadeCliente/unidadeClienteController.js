!(function () {
    'use strict';
    angular
            .module('app.pages.private.unidadeCliente')
            .controller('unidadeClienteController', unidadeClienteController);
    /**
     * @ngInject 
     * @param {type} $scope
     * @param {type} $state
     * @param {type} $location
     * @param {type} $mdMedia
     * @param {type} $mdToast
     * @param {type} $stateParams
     * @param {type} $mdDialog
     * @param {type} UnidadeClienteService
     * @param {type} ROWS_PAGE
     * @returns {undefined}
     */
    function unidadeClienteController($scope,
            $state,
            $location,
            $mdMedia,
            $mdToast,
            $stateParams,
            $mdDialog,
            UnidadeClienteService,
            ROWS_PAGE
            ) {
        this.$scope = $scope;
        this.$state = $state;
        this.$location = $location;
        this.$mdMedia = $mdMedia;
        this.$mdToast = $mdToast;
        this.$stateParams = $stateParams;
        this.$mdDialog = $mdDialog;
        this.UnidadeClienteService = UnidadeClienteService;
        this.ROWS_PAGE = ROWS_PAGE;
    }

    unidadeClienteController.prototype.cancelar = function (evt) {
        var $this = this;
        var confirm = this.$mdDialog.confirm()
                .title('Cancelar?')
                .textContent()
                .ariaLabel('Lucky day')
                .targetEvent(evt)
                .clickOutsideToClose(true)
                .parent(angular.element(document.body))
                .ok('Sim')
                .cancel('Não');
        this.$mdDialog.show(confirm).then(function () {
            $this.$state.go('app.unidadeCliente');
        });
    };

    unidadeClienteController.prototype.incluirFiscalModal = function (evt) {
        
        var $this = this;
        
        var useFullScreen = ($this.$mdMedia('sm') || $this.$mdMedia('xs')) && $this.$scope.customFullscreen;
        $this.$mdDialog.show({
            scope: $this.$scope.$new(),
            templateUrl: 'app/main/pages/private/unidadeCliente/incluirFiscal.modal.html',
            parent: angular.element(document.body),
            targetEvent: evt,
            clickOutsideToClose: true,
            fullscreen: useFullScreen
        })
        $this.$scope.$watch(function () {
            return $this.$mdMedia('xs') || $this.$mdMedia('sm');
        }, function (wantsFullScreen) {
            $this.$scope.customFullscreen = (wantsFullScreen === true);
        });
    };

})();