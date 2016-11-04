(function() {
  'use strict';

  angular
    .module('app.pages.private.dados')
    .service('DadosService', DadosService);

  /** @ngInject */
  function DadosService($http, REST_URL) {
    var url = REST_URL + '/dados';

    var getListaSetor = function () {
      return $http.get(url + '/setor');
    };
      
    var getListaEmpresa = function (setor) {
      return $http.get(url + '/empresa/' + setor);
    };
    
    var getListaNivel = function () {
      return $http.get(url + '/nivel');
    };
    
    var getListaCargo = function (nivel) {
      return $http.get(url + '/cargo/' + nivel);
    };
    
    var getMin = function (filtro) {
      return $http.post(url + '/min', filtro);
    };
    
    var getMax = function (filtro) {
      return $http.post(url + '/max', filtro);
    };
    
    var getSum = function (filtro) {
      return $http.post(url + '/somatorio', filtro);
    };
      
    var getAll = function () {
      return $http.get(url + '/pesquisa');
    };
    
    var getSearch = function (filtro) {
      return $http.post(url + '/search', filtro);
    };
      
    return {
      getListaSetor     : getListaSetor,
      getListaEmpresa   : getListaEmpresa,
      getListaNivel     : getListaNivel,
      getListaCargo     : getListaCargo,
      getMin            : getMin,
      getMax            : getMax,
      getSum            : getSum,
      getAll            : getAll,
      getSearch         : getSearch
    };
  }
})();
