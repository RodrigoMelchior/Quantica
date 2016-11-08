(function() {
  'use strict';

  angular
    .module('app.pages.private.responderPerguntas')
    .service('ResponderPerguntasService', ResponderPerguntasService);

  /** @ngInject */
  function ResponderPerguntasService($http, REST_URL) {
    var url = REST_URL + '/perguntas/pesquisa';
      
    var salvar = function (pergunta, idEmpresa) {
      return $http.post(url+"/salvar-resposta", pergunta);
    };

    var editar = function (pergunta) {
      return $http.put(url + '/' + pergunta.id, pergunta);
    };

    var pesquisar = function (pergunta) {
      // TODO
    };
      
    var pesquisarPerguntaPorPesquisa = function (id) {
        var filtro = {"pesquisa": id, "page": 1, "limit":100};
        return $http.post(url + '/search', filtro);
    };


    var deletar = function (id) {
      return $http.delete(url + '/' + id);
    };

    var getAll = function () {
      return $http.get(url);
    };

    var getById = function (id) {
      return $http.get(url + '/' + id);
    };  

    return {
      salvar    : salvar,
      editar    : editar,
      pesquisar : pesquisar,
      pesquisarPerguntaPorPesquisa : pesquisarPerguntaPorPesquisa,
      deletar   : deletar,
      getAll    : getAll,
      getById   : getById
    };
  }
})();
