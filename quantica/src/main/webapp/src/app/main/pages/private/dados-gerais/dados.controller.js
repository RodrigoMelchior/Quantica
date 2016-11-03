(function() {
  'use strict';

  angular
    .module('app.pages.private.dados')
    .controller('DadosController', DadosController);

  /** @ngInject */
  function DadosController($scope, $state, $stateParams, $mdToast, $mdDialog, $q, DadosService) {
    var vm = this;
    vm.listaSetor = {};
    vm.listaEmpresa = {};
    vm.listaNivel = {};
    vm.listaCargo = {};
    vm.retorno = {};
    vm.pesquisa = {};
    vm.filtro = {};
    vm.resultado = resultado;  
    vm.atualizaEmpresa = atualizaEmpresa;
    vm.atualizaCargo = atualizaCargo;

    DadosService.getListaSetor().then(function (response) {
        vm.listaSetor = response.data;
    });
    DadosService.getListaEmpresa('TODOS').then(function (response) {
        vm.listaEmpresa = response.data;
    });
    DadosService.getListaNivel().then(function (response) {
        vm.listaNivel = response.data;
    });
    DadosService.getListaCargo('TODOS').then(function (response) {
        vm.listaCargo = response.data;
    });
      
    function resultado() {
      if (vm.filtro.cargo === null || vm.filtro.cargo === undefined || vm.filtro.cargo === ''){
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha o cargo!')
          .position('right')
          .hideDelay(5000)
        );
      } else {
        DadosService.getAll().then(function (response) {
            vm.retorno.dados = response.data;
            vm.retorno.dados = orderBy(vm.retorno.dados, 'sal1', false)
console.log("getAll", vm.retorno);
        });
        DadosService.getMin().then(function (response) {
            vm.retorno.min = response.data;
console.log("getMin", vm.retorno);
        });
        DadosService.getMax().then(function (response) {
            vm.retorno.max = response.data;
console.log("getMax", vm.retorno);
        });
        DadosService.getSum().then(function (response) {
            vm.retorno.sum = response.data;
console.log("getSum", vm.retorno);
        });
      }
    }

    function atualizaEmpresa(){
        DadosService.getListaEmpresa(vm.filtro.setor).then(function (response) {
            vm.listaEmpresa = response.data;
        });
    }
    
    function atualizaCargo(){
        DadosService.getListaCargo(vm.filtro.nivel).then(function (response) {
            vm.listaCargo = response.data;
        });
    }
    
    function voltar() {
      $state.go('app.dados');
    }

    function validarFormulario() {
      var defer = $q.defer();
      if (vm.ceForm.$invalid) {
        angular.forEach(vm.ceForm.$error, function (val, index) {
          angular.forEach(val, function (obj) {
              vm.ceForm[obj.$name].$setTouched();
          });
        });
        defer.reject();
      } else {
        defer.resolve();
      }

      return defer.promise;
    }
  }
})();
