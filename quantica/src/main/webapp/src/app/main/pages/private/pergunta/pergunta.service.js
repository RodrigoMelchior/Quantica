(function() {
  'use strict';

  angular
    .module('app.pages.private.pergunta')
    .service('PerguntaService', PerguntaService);

  /** @ngInject */
  function PerguntaService($http, REST_URL) {
    var url = REST_URL + '/perguntas';

    var salvarPergunta = function (pergunta) {
      return $http.post(url, pergunta);
    };

    var editarPergunta = function (pergunta) {
      return $http.put(url + '/' + pergunta.id, pergunta);
    };

    var pesquisarPergunta = function (filtro) {
      return $http.post(url + '/search', filtro);
    };

    var deletarPergunta = function (id) {
      return $http.delete(url + '/' + id);
    };

    var getAllPerguntas = function () {
      return $http.get(url);
    };

    var getPergunta = function (id) {
      return $http.get(url + '/' + id);
    };

    return {
      salvarPergunta    : salvarPergunta,
      editarPergunta    : editarPergunta,
      pesquisarPergunta : pesquisarPergunta,
      deletarPergunta   : deletarPergunta,
      getAllPerguntas   : getAllPerguntas,
      getPergunta       : getPergunta
    };
  }
})();
