(function() {
  'use strict';

  angular
    .module('app.pages.private.estruturaCargos', ['flow', 'vAccordion', 'ngAnimate'])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.estruturaCargos', {
        url : '/estrutura-cargos',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/estrutura-cargos/estrutura-cargos.html',
            controller  : 'EstruturaCargosController as ecController'
          }
        },
        bodyClass : 'estrutura-cargos'
      });
  }
})();
