(function() {
  'use strict';

  angular
    .module('app.pages.private.empresa')
    .controller('EmpresaController', EmpresaController);

  /** @ngInject */
  function EmpresaController($scope, $state, $stateParams, $mdToast, $mdDialog, EmpresaService) {
    var vm = this;
    vm.pesquisa = {};
    vm.form = {};
    vm.pesquisa.lista = [];
    vm.tituloForm = 'Cadastrar';
    vm.pesquisar = pesquisar;
    vm.excluir = excluir;
    vm.salvar = salvar;
    vm.voltar = voltar;

    if (isEditar()) {
      vm.tituloForm = 'Editar';
      EmpresaService.getEmpresa($stateParams.id).then(function (response) {
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
        var filtro = {"nome":vm.pesquisa.nome, "page":"1","limit":"100"};
        EmpresaService.pesquisarEmpresa(filtro).then(function (response) {
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

    function excluir(evt, empresa) {
      var confirm = $mdDialog.confirm()
        .title('Deseja excluir a empresa?')
        .targetEvent(evt)
        .clickOutsideToClose(true)
        .parent(angular.element(document.body))
        .ok('Sim')
        .cancel('Não');
      $mdDialog.show(confirm).then(function() {
        EmpresaService.deletarEmpresa(empresa.id).then(function(response) {
          vm.pesquisa.lista.forEach(function(empresaLista, index, lista) {
            if (empresaLista.nome === empresa.nome) {
              lista.splice(index, 1);
            }
          });
        });
      });
    }

    function salvar() {
console.log(vm.form);
      if (vm.form.nome === null || vm.form.nome === undefined || vm.form.nome === '') {
        vm.ceForm.nome.$invalid = true;
        vm.ceForm.nome.$touched = true;
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha todos os dados obrigatórios!')
          .position('right')
          .hideDelay(5000)
        );
      } else {
        if (isEditar()) {
          EmpresaService.editarEmpresa(vm.form).then(function (response) {
            console.log(vm.form);
            $mdToast.show(
              $mdToast.simple()
              .textContent('Empresa atualizada com sucesso!')
              .position('right')
              .hideDelay(5000)
            );
          });
        } else {
          EmpresaService.salvarEmpresa(vm.form).then(function (response) {
            console.log(vm.form);
            $mdToast.show(
              $mdToast.simple()
              .textContent('Empresa cadastrada com sucesso!')
              .position('right')
              .hideDelay(5000)
            );
          });
        }
        voltar();
      }
    }

    function voltar() {
      $state.go('app.empresa');
    }

    function isEditar() {
      if ($state.current.name === 'app.empresa.editar') {
        return true;
      }

      return false;
    }
  }
})();
