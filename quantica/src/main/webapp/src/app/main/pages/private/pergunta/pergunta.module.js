(function() {
  'use strict';

  angular
    .module('app.pages.private.pergunta', [])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.pergunta', {
        url : '/perguntas',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/pergunta/pesquisa.html',
            controller  : 'PerguntaController as prController'
          }
        },
        bodyClass : 'pergunta'
      })
      .state('app.pergunta.incluir', {
        url : '/incluir',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/pergunta/formulario.html',
            controller  : 'PerguntaController as prController'
          }
        }
      })
      .state('app.pergunta.editar', {
        url : '/:id/editar',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/pergunta/formulario.html',
            controller  : 'PerguntaController as prController'
          }
        }
      });
  }
})();
