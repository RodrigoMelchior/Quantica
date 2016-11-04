(function() {
  'use strict';

  angular
    .module('app.pages.private.importarArquivo')
    .controller('ImportarArquivoController', ImportarArquivoController);

  /** @ngInject */
  function ImportarArquivoController($scope, $state, $mdToast, $http, ImportarArquivoService, CARGA_ESTRUTURA_DE_CARGOS) {
    var vm = this;
    vm.form = {};
    vm.validaArquivo = validaArquivo;
    vm.enviarArquivo = enviarArquivo;

    function validaArquivo(arquivo) {
      return ImportarArquivoService.validaArquivo(arquivo);
    }

    function enviarArquivo() {
        
        var idUsuarioLogado = localStorage.getItem('idUsuarioLogado');
        var pesquisaSelecionada = localStorage.getItem('pesquisaSelecionada');
        
      ImportarArquivoService.uploadArquivo(vm.form.flow.files[0].file, idUsuarioLogado, pesquisaSelecionada, CARGA_ESTRUTURA_DE_CARGOS).then(function (response) {
        console.log(vm.form);
        $mdToast.show(
          $mdToast.simple()
          .textContent('Arquivo importado com sucesso!')
          .position('right')
          .hideDelay(5000)
        );
      });
      $mdToast.show(
          $mdToast.simple()
          .textContent('Arquivo importado com sucesso!')
          .position('right')
          .hideDelay(5000)
      );
    }
  }
})();
