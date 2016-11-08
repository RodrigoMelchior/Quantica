(function() {
  'use strict';

  angular
    .module('app.pages.private.inpc', [])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.inpc', {
        url : '/inpc',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/inpc/pesquisa.html',
            controller  : 'InpcController as inController'
          }
        },
        bodyClass : 'inpc'
      })
      .state('app.inpc.incluir', {
        url : '/incluir',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/inpc/formulario.html',
            controller  : 'InpcController as inController'
          }
        }
      })
      .state('app.inpc.editar', {
        url : '/:id/editar',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/inpc/formulario.html',
            controller  : 'InpcController as inController'
          }
        }
      });
  }
})();
