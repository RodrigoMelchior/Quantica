(function() {
  'use strict';

  angular
    .module('app.pages.private.caesb', [])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.caesb', {
        url : '/caesb',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/caesb/filtro.html',
            controller  : 'CaesbController as dgController'
          }
        },
        bodyClass : 'caesb'
      })
      .state('app.caesb.resultado', {
        url : '/resultado',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/caesb/resultado.html',
            controller  : 'CaesbController as dgController'
          }
        }
      });
  }
})();
