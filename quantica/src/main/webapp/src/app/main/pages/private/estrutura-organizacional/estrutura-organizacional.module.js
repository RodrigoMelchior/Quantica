(function() {
  'use strict';

  angular
    .module('app.pages.private.estruturaOrganizacional', ['flow', 'vAccordion', 'ngAnimate'])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.estruturaOrganizacional', {
        url : '/estrutura-organizacional',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/estrutura-organizacional/estrutura-organizacional.html',
            controller  : 'EstruturaOrganizacionalController as eoController'
          }
        },
        bodyClass : 'estrutura-organizacional'
      });
  }
})();
