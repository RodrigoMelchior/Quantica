(function() {
  'use strict';

  angular
    .module('app.pages.private.atualizarDados')
    .service('ContatoService', ContatoService);

  /** @ngInject */
  function ContatoService($http, REST_URL) {
    var url = REST_URL + '/contatos';

    var salvar = function (contato) {
      return $http.post(url, contato);
    };

    var editar = function (contato) {
      return $http.put(url + '/' + contato.id, contato);
    };

    var pesquisar = function (filtro) {
      return $http.post(url + '/search', filtro);
    };

    var deletar = function (id) {
      return $http.delete(url + '/' + id);
    };

    var getAll = function () {
      return $http.get(url);
    };

    var getId = function (id) {
      return $http.get(url + '/' + id);
    };

    return {
      salvar    : salvar,
      editar    : editar,
      pesquisar : pesquisar,
      deletar   : deletar,
      getAll    : getAll,
      getId     : getId        
    };
  }
})();
