(function() {
  'use strict';

  angular
    .module('app.pages.private.item')
    .service('ItemService', ItemService);

  /** @ngInject */
  function ItemService($http, REST_URL) {
    var url = REST_URL + '/itens';

    var salvarItem = function (item) {
      return $http.post(url, item);
    };

    var editarItem = function (item) {
      return $http.put(url + '/' + item.id, item);
    };

    var pesquisarItem = function (filtro) {
      return $http.post(url + '/search', filtro);
    };

    var deletarItem = function (id) {
      return $http.delete(url + '/' + id);
    };

    var getAllItens = function () {
      return $http.get(url);
    };

    var getItem = function (id) {
      return $http.get(url + '/' + id);
    };

    return {
      salvarItem    : salvarItem,
      editarItem    : editarItem,
      pesquisarItem : pesquisarItem,
      deletarItem   : deletarItem,
      getAllItens   : getAllItens,
      getItem       : getItem
    };
  }
})();
