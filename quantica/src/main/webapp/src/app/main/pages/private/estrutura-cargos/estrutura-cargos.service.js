(function() {
  'use strict';

  angular
    .module('app.pages.private.estruturaCargos')
    .service('EstruturaCargosService', EstruturaCargosService);

  /** @ngInject */
  function EstruturaCargosService($http, REST_URL) {
    var url = REST_URL + '/cargos';

    var salvar = function (item) {
      return $http.post(url, item);
    };

    var editar = function (item) {
      return $http.put(url + '/' + item.id, item);
    };

    var pesquisar = function (filtro) {
console.log("pesquisar",filtro);
      return $http.post(url + '/search', filtro);
    };

    var deletar = function (id) {
      return $http.delete(url + '/' + id);
    };

    var getAll = function () {
      return $http.get(url);
    };

    var get = function (id) {
      return $http.get(url + '/' + id);
    };

    return {
      salvar    : salvar,
      editar    : editar,
      pesquisar : pesquisar,
      deletar   : deletar,
      getAll   : getAll,
      get       : get
    };
  }
})();
