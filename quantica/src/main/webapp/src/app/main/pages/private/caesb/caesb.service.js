(function() {
  'use strict';

  angular
    .module('app.pages.private.caesb')
    .service('CaesbService', CaesbService);

  /** @ngInject */
  function CaesbService($http, REST_URL) {
    var url = REST_URL + '/caesb';

    var getListaTipo = function () {
      return $http.get(url + '/tipo');
    };
      
    var getListaEmpresa = function () {
      return $http.get(url + '/empresa');
    };
    
    var getListaCod = function () {
      return $http.get(url + '/cod');
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
      getListaTipo      : getListaTipo,
      getListaEmpresa   : getListaEmpresa,
      getListaCod       : getListaCod,
      getMin            : getMin,
      getMax            : getMax,
      getSum            : getSum,
      getAll            : getAll,
      getSearch         : getSearch
    };
  }
})();
