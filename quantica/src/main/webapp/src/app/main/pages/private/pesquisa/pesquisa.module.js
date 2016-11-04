(function() {
  'use strict';

  angular
    .module('app.pages.private.pesquisa', [])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.pesquisa', {
        url : '/pesquisas',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/pesquisa/pesquisa.html',
            controller  : 'PesquisaController as psController'
          }
        },
        bodyClass : 'pesquisa'
      })
      .state('app.pesquisa.incluir', {
        url : '/incluir',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/pesquisa/formulario.html',
            controller  : 'PesquisaController as psController'
          }
        }
      })
      .state('app.pesquisa.editar', {
        url : '/:id/editar',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/pesquisa/formulario.html',
            controller  : 'PesquisaController as psController'
          }
        }
      });
  }
})();
