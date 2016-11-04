(function() {
  'use strict';

  angular
    .module('app.pages.private.inpc')
    .service('InpcService', InpcService);

  /** @ngInject */
  function InpcService($http, REST_URL) {
    var url = REST_URL + '/inpc';

    var salvarInpc = function (inpc) {
      return $http.post(url, inpc);
    };

    var editarInpc = function (inpc) {
      return $http.put(url + '/' + inpc.id, inpc);
    };

    var pesquisarInpc = function (filtro) {
      return $http.post(url + '/search', filtro);
    };

    var deletarInpc = function (id) {
      return $http.delete(url + '/' + id);
    };

    var getAllInpc = function () {
      return $http.get(url);
    };

    var getInpc = function (id) {
      return $http.get(url + '/' + id);
    };

    return {
      salvarInpc    : salvarInpc,
      editarInpc    : editarInpc,
      pesquisarInpc : pesquisarInpc,
      deletarInpc   : deletarInpc,
      getAllInpc    : getAllInpc,
      getInpc       : getInpc
    };
  }
})();
