(function() {
  'use strict';

  angular
    .module('app.pages.private.atualizarDados')
    .controller('AtualizarDadosController', AtualizarDadosController);

  /** @ngInject */
  function AtualizarDadosController($scope, $state, $mdToast, $mdMedia, $mdDialog, $http, $q, MESES_LIST, AtualizarDadosService, UtilService, ContatoService) {
    var vm = this;
    vm.empresa = {};
    vm.empresa.contatos = [];
    vm.openContatoModal = openContatoModal;
    vm.excluirContato = excluirContato;
    vm.salvarDados = salvarDados;
    vm.empresa.ufsBanco = []; 
    vm.idEmpresaUsuario = localStorage.getItem('idEmpresaUsuario');

      
    var carregaDados = function(){  
        AtualizarDadosService.getId(vm.idEmpresaUsuario).then(function (response) {
            vm.empresa = response.data;
            vm.empresa.ufsBanco = vm.empresa.ufs;
              var  ufEmpresa = [];           
              angular.forEach(vm.empresa.ufs, function (obj) { 
                    ufEmpresa.push(obj.uf.id.toString());
               });
              vm.empresa.ufs = ufEmpresa;
              vm.empresa.dataUltimoReajuste = UtilService.dateConverte(vm.empresa.dataUltimoReajuste);

        });
    }
    carregaDados();
    
    function getContatos() {
        var filtro = {"empresa":vm.idEmpresaUsuario, "page":1, "limit":1000};
        ContatoService.pesquisar(filtro).then(function (response) {
            vm.empresa.contatos = response.data;
        });
    }
  
getContatos();
      
    UtilService.getAllUfs().then(function (response) {
        vm.listaUfs = response.data;
    });
  
    vm.formasContratacao = [
      { id : '1', nome : 'CLT' },
      { id : '2', nome : 'Estatutário' },
      { id : '3', nome : 'Pessoa Jurídica' }
    ];

    vm.tiposFaturamento = [
      { id : '1', nome : 'Pequeno' },
      { id : '2', nome : 'Médio' },
      { id : '3', nome : 'Grande' }
    ];

    vm.meses = MESES_LIST;
/*
    vm.tiposOrganizacao = [
      { id : '1', nome : 'Corporativo' },
      { id : '2', nome : 'Subsidiária' },
      { id : '3', nome : 'Grupo' },
      { id : '4', nome : 'Divisão' }
    ];
*/
    vm.tiposPropriedade = [
      { id : '1', nome : 'Empresa de Capital Aberto' },
      { id : '2', nome : 'Empresa de Capital Fechado' },
      { id : '3', nome : 'Empresa Sem Fins Lucrativos' },
      { id : '4', nome : 'Joint Venture' },
      { id : '5', nome : 'Organização Não Governamental - ONG' }
    ];

    function openContatoModal(evt, contatoEditar) {
        
        if (contatoEditar !== null) {
            AtualizarDadosService.contatoEditar = contatoEditar;
        }
        
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;
      $mdDialog.show({
        targetEvent: evt,
        controller: contatoDialogController,
        templateUrl: 'app/main/pages/private/atualizar-dados/contato-modal.html',
        parent: angular.element(document.body),
        clickOutsideToClose: true,
        fullscreen: useFullScreen
      });
      $scope.$watch(function () {
          if(AtualizarDadosService.atualizarLista){
              getContatos();
              AtualizarDadosService.atualizarLista = false;
          }
          return $mdMedia('xs') || $mdMedia('sm');
      }, function (wantsFullScreen) {
          $scope.customFullscreen = (wantsFullScreen === true);
      });
    }

    function excluirContato(evt, contato) {
      var confirm = $mdDialog.confirm()
        .title('Deseja excluir o contato?')
        .targetEvent(evt)
        .clickOutsideToClose(true)
        .parent(angular.element(document.body))
        .ok('Sim')
        .cancel('Não');
      $mdDialog.show(confirm).then(function() {
          ContatoService.deletar(contato.id).then(function (response) {
                getContatos();
            });
          
      });
    }

    function salvarDados() {
console.log(vm.empresa);
      validarFormulario().then(function() {
          var  ufEmpresa = [];
          var ufsDelete = [];
          var empresa = {"id":vm.idEmpresaUsuario};
          angular.forEach(vm.empresa.ufs, function (obj) {
              var existeBanco = false;
              var uf = {};  
              var id = null;  
              angular.forEach(vm.empresa.ufsBanco, function (objBanco) {
                  if (obj == objBanco.uf.id){
                    uf = objBanco.uf;
                    id = objBanco.id;
                    existeBanco = true;
                  }
              });
             if (!existeBanco){
                uf = {"id":obj};
             }
            var objeEmpresa = {"id":id, "uf":uf, "empresa":empresa};
            ufEmpresa.push(objeEmpresa);
           });
          
          vm.empresa.ufs = ufEmpresa;  
          vm.empresa.ufsDelete = ufsDelete;
console.log(vm.empresa);
          AtualizarDadosService.editar(vm.empresa).then(function (response) {
            $mdToast.show(
              $mdToast.simple()
              .textContent('Dados atualizados com sucesso!')
              .position('right')
              .hideDelay(5000)
            );
            carregaDados();
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

      if (vm.atualizarForm.$invalid) {
        angular.forEach(vm.atualizarForm.$error, function (val, index) {
          angular.forEach(val, function (obj) {
            vm.atualizarForm[obj.$name].$setTouched();
          });
        });
        defer.reject();
      } else {
        defer.resolve();
      }

      return defer.promise;
    }
  }

  function contatoDialogController($scope, $mdDialog, $mdToast, AtualizarDadosService, ContatoService) {
    $scope.form = {};

    if (AtualizarDadosService.contatoEditar !== null) {
      $scope.form = angular.copy(AtualizarDadosService.contatoEditar);
    }

    $scope.cancel = function () {
      $mdDialog.cancel();
      AtualizarDadosService.atualizarLista = false;
    };

    $scope.formularioInvalido = function() {        
      if (
        ($scope.form.nome === null || $scope.form.nome === undefined || $scope.form.nome === '') ||
        ($scope.form.descricaoCargo === null || $scope.form.descricaoCargo === undefined || $scope.form.descricaoCargo === '') ||
        ($scope.form.telefone === null || $scope.form.telefone === undefined || $scope.form.telefone.lenght < 13) ||
        ($scope.form.email === null || $scope.form.email === undefined || $scope.form.email === '')
      ) {
        return true;
      }
      return false;
    };

    $scope.adicionaContato = function() {
        $scope.form.empresa = {"id":vm.idEmpresaUsuario};
        if ($scope.form.id != null){
            ContatoService.editar($scope.form).then(function (response) {
                AtualizarDadosService.atualizarLista = true;
            });
        }else{
            ContatoService.salvar($scope.form).then(function (response) {
                AtualizarDadosService.atualizarLista = true;
            });
        }
        $mdToast.show(
          $mdToast.simple()
          .textContent('Dados atualizados com sucesso!')
          .position('right')
          .hideDelay(5000)
        );
        $mdDialog.cancel();
    };
  }
})();
