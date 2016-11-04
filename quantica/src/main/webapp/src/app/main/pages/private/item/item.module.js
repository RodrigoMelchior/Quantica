(function() {
  'use strict';

  angular
    .module('app.pages.private.item', [])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.item', {
        url : '/itens',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/item/pesquisa.html',
            controller  : 'ItemController as itController'
          }
        },
        bodyClass : 'item'
      })
      .state('app.item.incluir', {
        url : '/incluir',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/item/formulario.html',
            controller  : 'ItemController as itController'
          }
        }
      })
      .state('app.item.editar', {
        url : '/:id/editar',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/item/formulario.html',
            controller  : 'ItemController as itController'
          }
        }
      });
  }
})();
