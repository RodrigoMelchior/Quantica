(function() {
  'use strict';

  angular
    .module('app.pages.public.login')
    .service('LoginService', LoginService);

  /** @ngInject */
  function LoginService($http, REST_URL) {
    var url = REST_URL + '/usuarios';

    var autenticado = function (obj) {
      return $http.get(url, item);
    };

    var salvar = function (obj) {
      return $http.post(url, item);
    };

    var editar = function (obj) {
      return $http.put(url + '/' + obj.id, obj);
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
    
    var logar = function (usuario) {
      return $http.post(url + '/logar', usuario);
    };

    return {
      autenticado : autenticado,
      salvar    : salvar,
      editar    : editar,
      pesquisar : pesquisar,
      deletar   : deletar,
      getAll    : getAll,
      getId     : getId,
      logar     : logar
    };
  }
})();
