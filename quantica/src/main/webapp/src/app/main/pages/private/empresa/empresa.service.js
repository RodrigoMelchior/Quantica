(function() {
  'use strict';

  angular
    .module('app.pages.private.empresa')
    .service('EmpresaService', EmpresaService);

  /** @ngInject */
  function EmpresaService ($http, REST_URL) {
    var url = REST_URL + '/empresas';

    var salvarEmpresa = function (empresa) {
      return $http.post(url, empresa);
    };

    var editarEmpresa = function (empresa) {
      return $http.put(url + '/' + empresa.id, empresa);
    };

    var pesquisarEmpresa = function (filtro) {
      return $http.post(url + '/search', filtro);
    };

    var deletarEmpresa = function (id) {
      return $http.delete(url + '/' + id);
    };

    var getAllEmpresas = function () {
      return $http.get(url + '/todas');
    };

    var getEmpresa = function (id) {
      return $http.get(url + '/' + id);
    };

    var empresaPatriocinadora = function (idPesquisa) {
      return $http.get(REST_URL + '/empresaPesquisas/empresa-patriocinadora/' + idPesquisa);
    };
      
      
    return {
      salvarEmpresa    : salvarEmpresa,
      editarEmpresa    : editarEmpresa,
      pesquisarEmpresa : pesquisarEmpresa,
      deletarEmpresa   : deletarEmpresa,
      getAllEmpresas   : getAllEmpresas,
      getEmpresa       : getEmpresa,
      empresaPatriocinadora : empresaPatriocinadora
    };
  }
})();
