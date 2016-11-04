(function() {
  'use strict';

  angular
    .module('app.pages.private.estruturaOrganizacional')
    .controller('EstruturaOrganizacionalController', EstruturaOrganizacionalController);

  /** @ngInject */
  function EstruturaOrganizacionalController($scope, $state, $mdDialog, $mdToast, $q, EstruturaOrganizacionalService) {
    var vm = this;
    vm.estrutura = {};
    vm.lista = [
      [], [], [], [], [], [], [], [], [], [], [], [], [], [], []
    ];
    vm.editar = editar;
    vm.excluir = excluir;
    vm.salvar = salvar;
    vm.isListaVazia = isListaVazia;
    vm.carregaLista = carregaLista;

    vm.niveis = [
      { id : "1", nome : '1º Nível' },
      { id : "2", nome : '2º Nível' },
      { id : "3", nome : '3º Nível' },
      { id : "4", nome : '4º Nível' },
      { id : "5", nome : '5º Nível' },
      { id : "6", nome : '6º Nível' },
      { id : "7", nome : '7º Nível' },
      { id : "8", nome : '8º Nível' },
      { id : "9", nome : '9º Nível' },
      { id : "10", nome : '10º Nível' },
      { id : "11", nome : '11º Nível' },
      { id : "12", nome : '12º Nível' },
      { id : "13", nome : '13º Nível' },
      { id : "14", nome : '14º Nível' },
      { id : "15", nome : '15º Nível' }
    ];

    vm.idEmpresaUsuario = localStorage.getItem('idEmpresaUsuario');
      
    function carregaLista() {
console.log("Carregando lista...");
        vm.lista = [
          [], [], [], [], [], [], [], [], [], [], [], [], [], [], []
        ];
        var filtro = {"empresa" : vm.idEmpresaUsuario, "limit":"1000", "page":"1"};  
        EstruturaOrganizacionalService.pesquisar(filtro).then(function (response) {
console.log(response.data);
            response.data.forEach(function(estrutura, index, lista) {
console.log(estrutura.nivel);
                vm.lista[estrutura.nivel].push(estrutura);
            });
            console.log(vm.lista);
        });   
    }
    
    vm.carregaLista();

    function editar(item) {
console.log('Editar...');
      vm.estrutura = angular.copy(item);
      vm.lista[indice].forEach(function(itemLista, index, lista) {
        if (itemLista.nome === item.nome) {
          lista.splice(index, 1);
        }
      });
    }

    function excluir(evt, item) {
console.log('Excluir...');
        var confirm = $mdDialog.confirm()
        .title('Deseja excluir o item?')
        .targetEvent(evt)
        .clickOutsideToClose(true)
        .parent(angular.element(document.body))
        .ok('Sim')
        .cancel('Não');
      $mdDialog.show(confirm).then(function() {
          item.empresa = null;
          EstruturaOrganizacionalService.editar(item).then(function (response) {
                EstruturaOrganizacionalService.deletar(item.id).then(function (response) {
                    console.log(response);
                    vm.carregaLista();
                });
          })
          .error(function(response){
            console.log(response);
          });
      });
    }

    function salvar() {
console.log('Salvar...');
        validarFormulario().then(function() {
            vm.estrutura.empresa = { "id":vm.idEmpresaUsuario};  
            if (vm.estrutura.id == null){
console.log(vm.estrutura);
                EstruturaOrganizacionalService.salvar(vm.estrutura).then(function (response) {
                    vm.carregaLista();
                    vm.estrutura = {};
                });
            }else{
console.log(vm.estrutura);
                EstruturaOrganizacionalService.editar(vm.estrutura).then(function (response) {
                    vm.carregaLista();
                    vm.estrutura = {};
                });
            }
            corrigeErrosFormulario();
            $mdToast.show(
                $mdToast.simple()
                .textContent('Estrutura cadastrada com sucesso!')
                .position('right')
                .hideDelay(5000)
              );
        }, function() {
            $mdToast.show(
                $mdToast.simple()
                .textContent('Preencha todos os dados obrigatórios!')
                .position('right')
                .hideDelay(5000)
            );
        });
    }

    function isListaVazia() {
      if (
        (vm.lista[0].length <= 0) &&
        (vm.lista[1].length <= 0) &&
        (vm.lista[2].length <= 0) &&
        (vm.lista[3].length <= 0) &&
        (vm.lista[4].length <= 0) &&
        (vm.lista[5].length <= 0) &&
        (vm.lista[6].length <= 0) &&
        (vm.lista[7].length <= 0) &&
        (vm.lista[8].length <= 0) &&
        (vm.lista[9].length <= 0) &&
        (vm.lista[10].length <= 0) &&
        (vm.lista[11].length <= 0) &&
        (vm.lista[12].length <= 0) &&
        (vm.lista[13].length <= 0) &&
        (vm.lista[14].length <= 0)
      ) {
        return true;
      }
      return false;
    }

    function validarFormulario() {
      var defer = $q.defer();
console.log(vm.estruturaForm);
console.log(vm.estruturaForm.$invalid);
      if (vm.estruturaForm.$invalid) {
        angular.forEach(vm.estruturaForm.$error, function (val, index) {
          angular.forEach(val, function (obj) {
            vm.estruturaForm[obj.$name].$setTouched();
          });
        });
        defer.reject();
      } else {
        defer.resolve();
      }

      return defer.promise;
    }

    function corrigeErrosFormulario() {
      vm.estruturaForm.nome.$touched = false;
      vm.estruturaForm.nivel.$touched = false;
    }
  }
})();
