/**
 * 
 * @author Marcus Vinicius <marcus.fms@gmail.com>
 * @returns {undefined}
 */
(function ()
{
    'use strict';

    angular
            .module('app.pages.private.cliente.clienteService', [])
            .service('ClienteService', ClienteService);

    var _pesquisa = {}

    /** @ngInject */
    function ClienteService($http, REST_URL) {
        this.$http = $http;
        this.REST_URL = REST_URL;
    }

    ClienteService.prototype.getPesquisaParams = function () {
        return _pesquisa;
    };

    ClienteService.prototype.getAll = function () {
        return this.$http.get(this.REST_URL + "/clientes?paging=true&page=1&limit=10");
    };

    ClienteService.prototype.getById = function (id) {
        return this.$http.get(this.REST_URL + "/clientes/" + id);
    };

    ClienteService.prototype.setPesquisaParams = function (param) {
        _pesquisa = param;
    };

    ClienteService.prototype.delete = function (id) {
        return this.$http.delete(this.REST_URL + "/clientes/" + id);
    };

    ClienteService.prototype.save = function (json) {
        return this.$http.post(this.REST_URL + "/clientes", json);
    };

    ClienteService.prototype.update = function (id, json) {
        return this.$http.put(this.REST_URL + "/clientes/" + id, json);
    };

    ClienteService.prototype.search = function (obj, page, limit) {
        obj.limit = limit || 10;
        obj.page = page || 1;
        return this.$http({
            method: 'GET',
            url: this.REST_URL + "/clientes/search",
            params: obj
        });
    };

    ClienteService.prototype.paginate = function (page, limit) {
        page = page || 1;
        limit = limit || 10;

        return this.$http({
            method: 'GET',
            url: this.REST_URL + "/clientes",
            params: {
                page: page,
                limit: limit
            }
        });
    };

    ClienteService.prototype.cnpj = function (cnpj) {
        return this.$http.get(REST_URL + "/clientes/validacoes/cnpjcadastrado/" + cnpj);
    };
})();