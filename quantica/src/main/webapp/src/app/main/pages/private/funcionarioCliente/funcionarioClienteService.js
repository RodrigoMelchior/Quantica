(function () {
  'use strict';

  angular
    .module('app.pages.private.funcionarioCliente.funcionarioClienteService', [])
    .service('FuncionarioClienteService', FuncionarioClienteService);


  var _pesquisa = {};

  /** @ngInject */
  function FuncionarioClienteService($http, REST_URL) {
    this.$http = $http;
    this.REST_URL = REST_URL;
    this.REST_URL = 'app/main/pages/private/funcionarioCliente/data';
  }

  FuncionarioClienteService.prototype.getPesquisaParams = function () {
    return _pesquisa;
  }

  FuncionarioClienteService.prototype.setPesquisaParams = function (params) {
    _pesquisa = params;
  }

  FuncionarioClienteService.prototype.getAll = function (id) {
    return this.$http.get(this.REST_URL + "/clientes?paging=true&page=1&limit=10");
  }

  FuncionarioClienteService.prototype.remove = function (id) {
    return this.$http.delete(this.REST_URL + "/clientes/" + id);
  }

  FuncionarioClienteService.prototype.insert = function (json) {
    return this.$http.post(this.REST_URL + "/clientes", json);
  }

  FuncionarioClienteService.prototype.update = function (id, json) {
    return this.$http.put(this.REST_URL + "/clientes/" + id, json);
  }

  FuncionarioClienteService.prototype.search = function (obj, page, limit) {
    obj.page = page || 1;
    obj.limit = limit || 10;
    return this.$http.get(this.REST_URL + "/grid.json", {params: obj}); //remover ao implementar backend
    return this.$http.get(this.REST_URL + "/clientes/search", obj);
  }

  FuncionarioClienteService.prototype.validarCnpj = function (cnpj) {
    return this.$http.get(this.REST_URL + "/clientes/validacoes/cnpjcadastrado/" + cnpj);
  }

})();
