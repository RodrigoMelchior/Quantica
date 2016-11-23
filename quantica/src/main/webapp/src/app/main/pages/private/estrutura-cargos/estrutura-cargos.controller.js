(function() {
  'use strict';

  angular
    .module('app.pages.private.estruturaCargos')
    .controller('EstruturaCargosController', EstruturaCargosController);

  /** @ngInject */
  function EstruturaCargosController($scope, $location, $state, $mdDialog, $mdToast, $q, EstruturaCargosService, ImportarArquivoService, EstruturaOrganizacionalService) {
    var vm = this;
    vm.estrutura = {};
    vm.arquivo = {};
    vm.lista = [];
    vm.listaPatriocinadora = [];
    vm.listaCodigos = [];
    vm.validaArquivo = validaArquivo;
    vm.editar = editar;
    vm.adicionar = adicionar;
    vm.excluir = excluir;
    vm.validaArquivo = validaArquivo;
    vm.enviarArquivo = enviarArquivo;
    vm.salvar = salvar;
    vm.niveis = [];
    vm.carregarLista = carregarLista;
    vm.carregarListaPatriocinadora = carregarListaPatriocinadora;
    vm.carregarListaCodigos = carregarListaCodigos;
    vm.mesclarListas = mesclarListas;
      
      
    vm.idEmpresaUsuario = localStorage.getItem('idEmpresaUsuario');
    vm.tipoRelacionamento = localStorage.getItem('tipoRelacionamento');
    vm.empresaPatriocinadora = localStorage.getItem('empresaPatriocinadora');
    vm.idUsuarioLogado = localStorage.getItem('idUsuarioLogado');
    vm.pesquisaSelecionada = localStorage.getItem('pesquisaSelecionada');
    
    var filtroEo = {"empresa" : vm.idEmpresaUsuario, "limit":"100", "page":"1"};  
    EstruturaOrganizacionalService.pesquisar(filtroEo).then(function (response) {
        vm.niveis = response.data;
    });  
      
    function carregarListaCodigos(){
        angular.forEach(vm.listaPatriocinadora, function (obj) { 
            var cargo = {"id": obj.id, "codigo": obj.codigo }
            vm.listaCodigos.push(cargo);
        });        
    }
      
    function mesclarListas(){
        angular.forEach(vm.listaPatriocinadora, function (ltp, index) { 
            angular.forEach(vm.lista, function (lt) { 
                if(ltp.codigo == lt.codigo){
                    ltp.cargo = lt;
                    vm.listaPatriocinadora.splice(index, 1, ltp);
                }
            });
        });
console.log("vm.listaPatriocinadora",vm.listaPatriocinadora);
    }
      
    function carregarLista() {
        var filtroEc = {"empresa" : vm.idEmpresaUsuario, "limit":"100", "page":"1"};  
        EstruturaCargosService.pesquisar(filtroEc).then(function (response) {
            vm.lista = response.data;
            if (vm.tipoRelacionamento == 2){
                vm.carregarListaPatriocinadora();
            }
        });  
    }
      
    function carregarListaPatriocinadora() {
        var filtroEc = {"empresa" : vm.empresaPatriocinadora, "limit":"100", "page":"1"};  
        EstruturaCargosService.pesquisar(filtroEc).then(function (response) {
            vm.listaPatriocinadora = response.data;
            vm.carregarListaCodigos();
            vm.mesclarListas();
        });  
    }
    
    vm.carregarLista();
    
console.log("vm.listaPatriocinadora",vm.listaPatriocinadora);
console.log("vm.lista",vm.lista);
      
    function validaArquivo(arquivo) {
      return ImportarArquivoService.validaArquivo(arquivo);
    }

    function editar(item, indice) {
       vm.estrutura = angular.copy(item);
    }
    
    function adicionar(codigo, indice) {
       vm.estrutura.codigo = codigo;
       $location.hash('inicio');
    }

    function excluir(evt, item, indice) {
      var confirm = $mdDialog.confirm()
        .title('Deseja excluir o item?')
        .targetEvent(evt)
        .clickOutsideToClose(true)
        .parent(angular.element(document.body))
        .ok('Sim')
        .cancel('Não');
      $mdDialog.show(confirm).then(function() {
          item.empresa = null;
          item.estruturaOrganizacional = null;
          EstruturaCargosService.editar(item).then(function (response) {
                EstruturaCargosService.deletar(item.id).then(function (response) {
                    vm.carregarLista();
                });
          });
      });
    }
    
    function salvar() {    
console.log("Salvar");
        validarFormulario().then(function() {
            vm.estrutura.empresa = {"id":vm.idEmpresaUsuario};
            if (vm.estrutura.id != null){
                EstruturaCargosService.editar(vm.estrutura).then(function (response) {
                    vm.carregarLista();
                });
            }else{
                EstruturaCargosService.salvar(vm.estrutura).then(function (response) {
                    vm.carregarLista();
                });  
            }
            vm.estrutura = {};

            corrigeErrosFormulario();
        
              if (vm.arquivo.flow.files.length > 0) {
                console.log(vm.arquivo);
              } else {
                console.log(vm.lista);
              }
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
  
    function validaArquivo(arquivo) {
      return ImportarArquivoService.validaArquivo(arquivo);
    }

    function enviarArquivo() {
      ImportarArquivoService.uploadArquivo(vm.arquivo.flow.files[0].file, vm.idUsuarioLogado, vm.pesquisaSelecionada).then(function (response) {
        $mdToast.show(
          $mdToast.simple()
          .textContent('Arquivo importado com sucesso!')
          .position('right')
          .hideDelay(5000)
        );
      });
    }
      
      
    function validarFormulario() {
      var defer = $q.defer();

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
      vm.estruturaForm.ponto.$touched = false;
      vm.estruturaForm.nivel.$touched = false;
      vm.estruturaForm.descricao.$touched = false;
    }
    
  }
})();
