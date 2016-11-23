(function() {
  'use strict';

  angular
    .module('app.pages.private.inpc')
    .controller('InpcController', InpcController);

  /** @ngInject */
  function InpcController($scope, $state, $stateParams, $mdToast, $mdDialog, $q, InpcService, UtilService) {
    var vm = this;
    vm.pesquisa = {};
    vm.filtro = {};
    vm.form = {};
    vm.pesquisa.lista = [];
    vm.tituloForm = 'Cadastrar';
    vm.pesquisar = pesquisar;
    vm.excluir = excluir;
    vm.salvar = salvar;
    vm.voltar = voltar;    

    if (isEditar()) {
      vm.tituloForm = 'Editar';
      InpcService.getInpc($stateParams.id).then(function (response) {
        vm.form = response.data;
        vm.form.mesAno = UtilService.dateConverte(vm.form.mesAno);
      });
    }

    function pesquisar() {
/*        
      if ( 
        (vm.pesquisa.mesAnoInicio === null || vm.pesquisa.mesAnoInicio === undefined || vm.pesquisa.mesAnoInicio === '') &&
        (vm.pesquisa.mesAnoFim === null || vm.pesquisa.mesAnoFim === undefined || vm.pesquisa.mesAnoFim === '')
      ){
        vm.pesquisarForm.mesAnoInicio.$invalid = true;
        vm.pesquisarForm.mesAnoInicio.$touched = true;
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha pelo menos um campo para pesquisa!')
          .position('right')
          .hideDelay(5000)
        );
      } else {
*/      
        vm.filtro.mesAnoInicio = vm.pesquisa.mesAnoInicio;
        vm.filtro.mesAnoFim = vm.pesquisa.mesAnoFim;
        vm.filtro.limit = 100;
        vm.filtro.page = 1;
        InpcService.pesquisarInpc(vm.filtro).then(function (response) {
          vm.pesquisa.lista = response.data;
            if (vm.pesquisa.lista.length == 0){
              $mdToast.show(
                  $mdToast.simple()
                  .textContent('Nenum registro encontrado!')
                  .position('right')
                  .hideDelay(5000)
                );
            }
        });
//      }
    }

    function excluir(evt, inpc) {
      var confirm = $mdDialog.confirm()
        .title('Deseja excluir o INPC?')
        .targetEvent(evt)
        .clickOutsideToClose(true)
        .parent(angular.element(document.body))
        .ok('Sim')
        .cancel('Não');
      $mdDialog.show(confirm).then(function() {
        InpcService.deletarInpc(inpc.id).then(function(response) {
          vm.pesquisa.lista.forEach(function(inpcLista, index, lista) {
            if (inpcLista.nome === inpc.nome) {
              lista.splice(index, 1);
            }
          });
        });
      });
    }

    function salvar() {
      validarFormulario().then(function() {
        if (isEditar()) {
          InpcService.editarInpc(vm.form).then(function (response) {
            $mdToast.show(
              $mdToast.simple()
              .textContent('INPC atualizado com sucesso!')
              .position('right')
              .hideDelay(5000)
            );
          });
        } else {
          InpcService.salvarInpc(vm.form).then(function (response) {
            $mdToast.show(
              $mdToast.simple()
              .textContent('INPC cadastrado com sucesso!')
              .position('right')
              .hideDelay(5000)
            );
          });
        }
        voltar();
      }, function() {
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha todos os dados obrigatórios!')
          .position('right')
          .hideDelay(5000)
        );
      });
    }

    function voltar() {
      $state.go('app.inpc');
    }

    function isEditar() {
      if ($state.current.name === 'app.inpc.editar') {
        return true;
      }

      return false;
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
