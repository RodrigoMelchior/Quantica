(function() {
  'use strict';

  angular
    .module('app.pages.private.importarArquivo')
    .controller('ImportarArquivoController', ImportarArquivoController);

  /** @ngInject */
  function ImportarArquivoController($scope, $state, $mdToast, $http, ImportarArquivoService, CARGA_ESTRUTURA_DE_CARGOS) {
      
    var idUsuarioLogado = localStorage.getItem('idUsuarioLogado');
    var pesquisaSelecionada = localStorage.getItem('pesquisaSelecionada');
        
    var vm = this;
    vm.form = {};
    vm.validaArquivo = validaArquivo;
    vm.enviarArquivo = enviarArquivo;

    function carregaJaImportado() {
    
        ImportarArquivoService.getArquivo(idUsuarioLogado, pesquisaSelecionada).then(function (response) {
            vm.arquivoJaImportado = response.data;
            if (vm.arquivoJaImportado.id != null){
                ImportarArquivoService.getRegistroArquivo(vm.arquivoJaImportado.id).then(function (response) {
                    vm.registroArquivoJaImportado = response.data;
                });
            }
        });
        ImportarArquivoService.getItemPesquisa(pesquisaSelecionada).then(function (response) {
            vm.itensPesquisa = response.data;
        });
    }
      
    function validaArquivo(arquivo) {
      return ImportarArquivoService.validaArquivo(arquivo);
    }

    function enviarArquivo() {  
console.log('1');
      ImportarArquivoService.uploadArquivo(vm.form.flow.files[0].file, idUsuarioLogado, pesquisaSelecionada, CARGA_ESTRUTURA_DE_CARGOS).then(function (response) {
console.log('2');
 //       carregaJaImportado();
console.log('3');
        $mdToast.show(
          $mdToast.simple()
          .textContent('Arquivo importado com sucesso!')
          .position('right')
          .hideDelay(5000)
        );
console.log('5');
      });
console.log('6');
      $mdToast.show(
          $mdToast.simple()
          .textContent('Arquivo importado com sucesso!')
          .position('right')
          .hideDelay(5000)
      );
console.log('7');
    }
  }
})();
