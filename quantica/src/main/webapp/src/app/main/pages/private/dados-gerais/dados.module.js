(function() {
  'use strict';

  angular
    .module('app.pages.private.dados', [])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.dados', {
        url : '/dados-gerais',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/dados-gerais/filtro.html',
            controller  : 'DadosController as dgController'
          }
        },
        bodyClass : 'dados'
      })
      .state('app.dados.resultado', {
        url : '/resultado',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/dados-gerais/resultado.html',
            controller  : 'DadosController as dgController'
          }
        }
      });
  }
})();
