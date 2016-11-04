(function() {
  'use strict';

  angular
    .module('app.pages.private.atualizarDados')
    .service('AtualizarDadosService', AtualizarDadosService);

  /** @ngInject */
  function AtualizarDadosService($http, REST_URL) {
    var listaContatos = [],
    contatoEditar = null,
    atualizarLista =  null;
      
    var url = REST_URL + '/empresas';
      
    var salvar = function (empresa) {
      return $http.post(url, empresa);
    };

    var editar = function (empresa) {
      return $http.put(url + '/' + empresa.id, empresa);
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
      listaContatos : listaContatos,
      contatoEditar : contatoEditar,
      atualizarLista : atualizarLista,
      salvar    : salvar,
      editar    : editar,
      pesquisar : pesquisar,
      deletar   : deletar,
      getAll    : getAll,
      getId     : getId
        
    };
  }
})();
