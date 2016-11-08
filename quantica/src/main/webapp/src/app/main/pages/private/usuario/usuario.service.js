(function() {
  'use strict';

  angular
    .module('app.pages.private.usuario')
    .service('UsuarioService', UsuarioService);

  /** @ngInject */
  function UsuarioService($http, REST_URL) {
    var url = REST_URL + '/usuarios';

    var salvarUsuario = function (usuario) {
      return $http.post(url, usuario);
    };

    var editarUsuario = function (usuario) {
      return $http.put(url + '/' + usuario.id, usuario);
    };

    var pesquisarUsuario = function (filtro) {
      return $http.post(url + '/search', filtro);
    };

    var deletarUsuario = function (id) {
      return $http.delete(url + '/' + id);
    };

    var getAllUsuarios = function () {
      return $http.get(url);
    };

    var getUsuario = function (id) {
      return $http.get(url + '/' + id);
    };

    return {
      salvarUsuario    : salvarUsuario,
      editarUsuario    : editarUsuario,
      pesquisarUsuario : pesquisarUsuario,
      deletarUsuario   : deletarUsuario,
      getAllUsuarios   : getAllUsuarios,
      getUsuario       : getUsuario
    };
  }
})();
