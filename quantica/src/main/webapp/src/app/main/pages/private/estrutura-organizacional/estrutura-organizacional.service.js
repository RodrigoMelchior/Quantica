(function() {
  'use strict';

  angular
    .module('app.pages.private.estruturaOrganizacional')
    .service('EstruturaOrganizacionalService', EstruturaOrganizacionalService);

  /** @ngInject */
  function EstruturaOrganizacionalService($http, REST_URL) {
    var url = REST_URL + '/estruturas';

    var salvar = function (item) {
      return $http.post(url, item);
    };

    var editar = function (item) {
      return $http.put(url + '/' + item.id, item);
    };

    var pesquisar = function (filtro) {
      return $http.post(url + '/search', filtro);
    };

    var deletar = function (id) {
console.log(id);
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
