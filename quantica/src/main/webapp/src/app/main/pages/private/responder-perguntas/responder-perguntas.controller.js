(function() {
  'use strict';

  angular
    .module('app.pages.private.responderPerguntas')
    .controller('ResponderPerguntasController', ResponderPerguntasController);

  /** @ngInject */
  function ResponderPerguntasController($scope, $state, $stateParams, $mdToast, $mdDialog, $q, ResponderPerguntasService) {
    var vm = this;
    vm.form = {};
    vm.salvar = salvar;
    vm.listaPerguntas = {};
    vm.idEmpresaUsuario = localStorage.getItem('idEmpresaUsuario');
            
    ResponderPerguntasService.pesquisarPerguntaPorPesquisa(1).then(function (response) {
console.log(response.data);
        vm.listaPerguntas = response.data;
    });
      

console.log("depois");

    function salvar() {
console.log(vm.listaPerguntas); 
      validarFormulario().then(function() {        
          var objt = {"perguntaPesquisas": vm.listaPerguntas,
           "idEmpresa":vm.idEmpresaUsuario};
          console.log(objt);
          ResponderPerguntasService.salvar(objt).then(function (response) {
            $mdToast.show(
              $mdToast.simple()
              .textContent('Perguntas respondidas com sucesso!')
              .position('right')
              .hideDelay(5000)
            );
          });
      }, function() {
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha todos os dados obrigatórios!')
          .position('right')
          .hideDelay(5000)
        );
      });
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
