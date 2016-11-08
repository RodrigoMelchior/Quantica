(function() {
  'use strict';

  angular
    .module('app.pages.private.importarArquivo', ['flow'])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.importarArquivo', {
        url : '/importar-arquivo',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/importar-arquivo/importar-arquivo.html',
            controller  : 'ImportarArquivoController as iaController'
          }
        },
        bodyClass : 'importar-arquivo'
      });
  }
})();
