(function() {
  'use strict';

  angular
    .module('app.pages.private.usuario', [])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.usuario', {
        url : '/usuarios',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/usuario/pesquisa.html',
            controller  : 'UsuarioController as usController'
          }
        },
        bodyClass : 'usuario'
      })
      .state('app.usuario.incluir', {
        url : '/incluir',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/usuario/formulario.html',
            controller  : 'UsuarioController as usController'
          }
        }
      })
      .state('app.usuario.editar', {
        url : '/:id/editar',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/usuario/formulario.html',
            controller  : 'UsuarioController as usController'
          }
        }
      });
  }
})();
