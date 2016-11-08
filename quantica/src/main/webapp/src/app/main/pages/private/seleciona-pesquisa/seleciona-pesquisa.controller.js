(function() {
  'use strict';

  angular
    .module('app.pages.private.selecionaPesquisa')
    .controller('SelecionaPesquisaController', SelecionaPesquisaController);

  /** @ngInject */
  function SelecionaPesquisaController($scope, $rootScope, $state, $mdToast, $http, PesquisaService, EmpresaService) {
    var vm = this;
    vm.selecionaPesquisa = selecionaPesquisa;

    vm.todasPesquisas = [];      
    var idEmpresaUsuario =  localStorage.getItem('idEmpresaUsuario');
      
console.log("idEmpresaUsuario",idEmpresaUsuario);      
    PesquisaService.pesquisaPorEmpresa(idEmpresaUsuario).then(function (response) {
console.log("response",response);
        vm.todasPesquisas = response.data;
    });

    function selecionaPesquisa() {
console.log("vm.pesquisaSelecionada",vm.pesquisaSelecionada);
      localStorage.setItem('pesquisaSelecionada', vm.pesquisaSelecionada);
      PesquisaService.empresaPesquisaPorEmpresaAndPesquisa(idEmpresaUsuario, vm.pesquisaSelecionada).then(function (response) {
console.log("response",response);
          localStorage.setItem('tipoRelacionamento', response.data.relacionamento);
          if (response.data.relacionamento == 2){
              EmpresaService.empresaPatriocinadora(vm.pesquisaSelecionada).then(function (response) {
console.log("empresaPatriocinadora",response);
                    localStorage.setItem('empresaPatriocinadora', response.data.empresa.id);
              });
              
          }
      });
      $state.go('app.dashboard');
    }
  }
})();
