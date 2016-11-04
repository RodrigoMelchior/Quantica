(function() {
  'use strict';

  angular
    .module('app.pages.private.empresa', [])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.empresa', {
        url : '/empresas',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/empresa/pesquisa.html',
            controller  : 'EmpresaController as emController'
          }
        },
        bodyClass : 'empresa'
      })
      .state('app.empresa.incluir', {
        url : '/incluir',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/empresa/formulario.html',
            controller  : 'EmpresaController as emController'
          }
        }
      })
      .state('app.empresa.editar', {
        url : '/:id/editar',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/empresa/formulario.html',
            controller  : 'EmpresaController as emController'
          }
        }
      });
  }
})();
