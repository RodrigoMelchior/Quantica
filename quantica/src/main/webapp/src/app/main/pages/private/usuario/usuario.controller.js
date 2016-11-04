(function() {
  'use strict';

  angular
    .module('app.pages.private.usuario')
    .controller('UsuarioController', UsuarioController);

  /** @ngInject */
  function UsuarioController($scope, $state, $stateParams, $mdToast, $mdDialog, $q, UsuarioService, EmpresaService) {
    var vm = this;
    vm.pesquisa = {};
    vm.form = {};
    vm.pesquisa.lista = [];
    vm.tituloForm = 'Cadastrar';
    vm.listaPerfis = [
      { id : 1, nome : 'Administrador' },
      { id : 2, nome : 'Participante' }
    ];
    vm.listaEmpresas = [];
    vm.pesquisar = pesquisar;
    vm.excluir = excluir;
    vm.salvar = salvar;
    vm.voltar = voltar;

    EmpresaService.getAllEmpresas().then(function (response) {
        vm.listaEmpresas = response.data;
      });
    
      
    if (isEditar()) {
      vm.tituloForm = 'Editar';
      UsuarioService.getUsuario($stateParams.id).then(function (response) {
        vm.form = response.data;          
          vm.form.perfil =  vm.form.perfis[0].id;
          console.log(vm.form);
      });
    }

    function pesquisar() {
/*
      if (
        (vm.pesquisa.usuario === null || vm.pesquisa.usuario === undefined || vm.pesquisa.usuario === '') &&
        (vm.pesquisa.perfil === null || vm.pesquisa.perfil === undefined || vm.pesquisa.perfil === '') &&
        (vm.pesquisa.empresa === null || vm.pesquisa.empresa === undefined || vm.pesquisa.empresa === '') &&
        (vm.pesquisa.ativo === null || vm.pesquisa.ativo === undefined || vm.pesquisa.ativo === '')
      ) {
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha pelo menos um campo para pesquisa!')
          .position('right')
          .hideDelay(5000)
        );
      } else {
*/
vm.pesquisa.page = 1;
vm.pesquisa.limit = 1000;
console.log(vm.pesquisa);
          UsuarioService.pesquisarUsuario(vm.pesquisa).then(function (response) {
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

    function excluir(evt, usuario) {
      var confirm = $mdDialog.confirm()
        .title('Deseja excluir o usuário?')
        .targetEvent(evt)
        .clickOutsideToClose(true)
        .parent(angular.element(document.body))
        .ok('Sim')
        .cancel('Não');
      $mdDialog.show(confirm).then(function() {
        UsuarioService.deletarUsuario(usuario.id).then(function(response) {
          vm.pesquisa.lista.forEach(function(usuarioLista, index, lista) {
            if (usuarioLista.usuario === usuario.usuario) {
              lista.splice(index, 1);
            }
          });
        });
      });
    }

    function salvar() {
      validarFormulario().then(function() {
          
        console.log("original");
        console.log(vm.form);
          
        var rlPerfil = [];
          var perfil = {"id":vm.form.perfil};
          rlPerfil.push(perfil);
          vm.form.perfis = rlPerfil;
          
          console.log("manipulado");
          console.log(vm.form);
          
        if (isEditar()) {
          UsuarioService.editarUsuario(vm.form).then(function (response) {
            console.log(vm.form);
            $mdToast.show(
              $mdToast.simple()
              .textContent('Usuário atualizado com sucesso!')
              .position('right')
              .hideDelay(5000)
            );
          });
        } else {
          UsuarioService.salvarUsuario(vm.form).then(function (response) {
            console.log(vm.form);
            $mdToast.show(
              $mdToast.simple()
              .textContent('Usuário cadastrado com sucesso!')
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
      $state.go('app.usuario');
    }

    function isEditar() {
      if ($state.current.name === 'app.usuario.editar') {
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
