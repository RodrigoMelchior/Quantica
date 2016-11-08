(function() {
  'use strict';

  angular
    .module('app.pages.private.atualizarDados', ['ui.mask'])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.atualizarDados', {
        url : '/atualizar-dados',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/atualizar-dados/atualizar-dados.html',
            controller  : 'AtualizarDadosController as adController'
          }
        },
        bodyClass : 'atualizar-dados'
      });
  }
})();
