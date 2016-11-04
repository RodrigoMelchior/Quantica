(function() {
  'use strict';

  angular
    .module('app.pages.private.responderPerguntas', [])
    .config(config);

  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
      .state('app.responderPerguntas', {
        url : '/responder-perguntas',
        views : {
          'content@app' : {
            templateUrl : 'app/main/pages/private/responder-perguntas/formulario.html',
            controller  : 'ResponderPerguntasController as rpController'
          }
        },
        bodyClass : 'responder-perguntas'
      });
  }
})();
