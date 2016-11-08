(function() {
  'use strict';

  angular
    .module('app.pages.private.selecionaPesquisa', [])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.selecionaPesquisa', {
        url : '/seleciona-pesquisa',
        views : {
          'main@' : {
            templateUrl : 'app/core/layouts/content-only.html',
            controller  : 'MainController as vm'
          },
          'content@app.selecionaPesquisa' : {
            templateUrl : 'app/main/pages/private/seleciona-pesquisa/seleciona-pesquisa.html',
            controller  : 'SelecionaPesquisaController as spController'
          }
        },
        bodyClass : 'seleciona-pesquisa'
      });
  }
})();
