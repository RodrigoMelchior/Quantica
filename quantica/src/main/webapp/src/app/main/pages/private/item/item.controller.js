(function() {
  'use strict';

  angular
    .module('app.pages.private.item')
    .controller('ItemController', ItemController);

  /** @ngInject */
  function ItemController($scope, $state, $stateParams, $mdToast, $mdDialog, $q, ItemService) {
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
      ItemService.getItem($stateParams.id).then(function (response) {
          vm.form = response.data;
      });
    }

    function pesquisar() {
        /*
      if (vm.pesquisa.nome === null || vm.pesquisa.nome === undefined || vm.pesquisa.nome === '') {
        vm.pesquisarForm.nome.$invalid = true;
        vm.pesquisarForm.nome.$touched = true;
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha pelo menos um campo para pesquisa!')
          .position('right')
          .hideDelay(5000)
        );
      } else {
      */
        vm.filtro.nome = vm.pesquisa.nome;
        vm.filtro.limit = 100;
        vm.filtro.page = 1;
        ItemService.pesquisarItem(vm.filtro).then(function (response) {
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

    function excluir(evt, item) {
      var confirm = $mdDialog.confirm()
        .title('Deseja excluir o item?')
        .targetEvent(evt)
        .clickOutsideToClose(true)
        .parent(angular.element(document.body))
        .ok('Sim')
        .cancel('Não');
      $mdDialog.show(confirm).then(function() {
        ItemService.deletarItem(item.id).then(function(response) {
          vm.pesquisa.lista.forEach(function(itemLista, index, lista) {
            if (itemLista.nome === item.nome) {
              lista.splice(index, 1);
            }
          });
        });
      });
    }

    function salvar() {
      validarFormulario().then(function() {
        if (isEditar()) {
          ItemService.editarItem(vm.form).then(function (response) {
            console.log(vm.form);
            $mdToast.show(
              $mdToast.simple()
              .textContent('Item atualizado com sucesso!')
              .position('right')
              .hideDelay(5000)
            );
          });
        } else {
          ItemService.salvarItem(vm.form).then(function (response) {
            console.log(vm.form);
            $mdToast.show(
              $mdToast.simple()
              .textContent('Item cadastrado com sucesso!')
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
      $state.go('app.item');
    }

    function isEditar() {
      if ($state.current.name === 'app.item.editar') {
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
