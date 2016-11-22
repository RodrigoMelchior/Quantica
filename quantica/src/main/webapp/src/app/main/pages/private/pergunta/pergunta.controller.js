(function() {
  'use strict';

  angular
    .module('app.pages.private.pergunta')
    .controller('PerguntaController', PerguntaController);

  /** @ngInject */
  function PerguntaController($scope, $state, $stateParams, $mdToast, $mdDialog, $q, PerguntaService) {
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
      PerguntaService.getPergunta($stateParams.id).then(function (response) {
        vm.form = response.data;
      });
    }

    function pesquisar() {
/*        
      if (vm.pesquisa.enunciado === null || vm.pesquisa.enunciado === undefined || vm.pesquisa.enunciado === '') {
        vm.pesquisarForm.enunciado.$invalid = true;
        vm.pesquisarForm.enunciado.$touched = true;
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha pelo menos um campo para pesquisa!')
          .position('right')
          .hideDelay(5000)
        );
      } else {
  */      
        vm.filtro.enunciado = vm.pesquisa.enunciado;
        vm.filtro.limit = 100;
        vm.filtro.page = 1;
          
        PerguntaService.pesquisarPergunta(vm.filtro).then(function (response) {
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

    function excluir(evt, pergunta) {
      var confirm = $mdDialog.confirm()
        .title('Deseja excluir a pergunta?')
        .targetEvent(evt)
        .clickOutsideToClose(true)
        .parent(angular.element(document.body))
        .ok('Sim')
        .cancel('Não');
      $mdDialog.show(confirm).then(function() {
        PerguntaService.deletarPergunta(pergunta.id).then(function (response) {
          vm.pesquisa.lista.forEach(function(perguntaLista, index, lista) {
            if (perguntaLista.enunciado === pergunta.enunciado) {
              lista.splice(index, 1);
            }
          });
        });
      });
    }

    function salvar() {
      validarFormulario().then(function() {
        if (isEditar()) {
          PerguntaService.editarPergunta(vm.form).then(function (response) {
            $mdToast.show(
              $mdToast.simple()
              .textContent('Pergunta atualizada com sucesso!')
              .position('right')
              .hideDelay(5000)
            );
          });
        } else {
          PerguntaService.salvarPergunta(vm.form).then(function (response) {
            $mdToast.show(
              $mdToast.simple()
              .textContent('Pergunta cadastrada com sucesso!')
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
      $state.go('app.pergunta');
    }

    function isEditar() {
      if ($state.current.name === 'app.pergunta.editar') {
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
