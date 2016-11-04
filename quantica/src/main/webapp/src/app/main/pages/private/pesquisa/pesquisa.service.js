(function() {
  'use strict';

  angular
    .module('app.pages.private.pesquisa')
    .service('PesquisaService', PesquisaService);

  /** @ngInject */
  function PesquisaService($http, REST_URL) {
    
    var url = REST_URL + '/pesquisas';

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

    var pesquisaPorEmpresa = function (id) {
      return $http.get(url +'/pesquisa-por-empresa/' + id);
    };
    
    var empresaPesquisaPorEmpresaAndPesquisa = function (idEmpresa, idPesquisa) {
      return $http.get(REST_URL + '/empresaPesquisas/por-empresa-pesquisa/' + idEmpresa + '/' + idPesquisa);
    };
      
    return {
      salvar    : salvar,
      editar    : editar,
      pesquisar : pesquisar,
      deletar   : deletar,
      getAll   : getAll,
      getId       : getId,
      pesquisaPorEmpresa : pesquisaPorEmpresa,
      empresaPesquisaPorEmpresaAndPesquisa : empresaPesquisaPorEmpresaAndPesquisa
    };
  }
})();
