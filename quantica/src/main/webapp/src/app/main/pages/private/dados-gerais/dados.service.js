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
    
    var getMin = function () {
      return $http.get(url + '/min');
    };
    
    var getMax = function () {
      return $http.get(url + '/max');
    };
    
    var getSum = function () {
      return $http.get(url + '/somatorio');
    };
      
    var getAll = function () {
      return $http.get(url + '/pesquisa');
    };
    
    return {
      getListaSetor     : getListaSetor,
      getListaEmpresa   : getListaEmpresa,
      getListaNivel     : getListaNivel,
      getListaCargo     : getListaCargo,
      getMin            : getMin,
      getMax            : getMax,
      getSum            : getSum,
      getAll            : getAll
    };
  }
})();
