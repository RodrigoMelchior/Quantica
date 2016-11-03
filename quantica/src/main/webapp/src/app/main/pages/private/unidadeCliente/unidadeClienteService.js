(function ()
{
    'use strict';

    angular
            .module('app.pages.private.unidadeCliente.service', [])
            .service("UnidadeClienteService", UnidadeClienteService)

    var _pesquisa = {};

    /** @ngInject */
    function UnidadeClienteService($http, REST_URL) {
        this.$http = $http;
        this.REST_URL = REST_URL;
    }

    UnidadeClienteService.prototype.getAll = function () {
        return this.$http.get(this.REST_URL + "/?paging=true&page=1&limit=10");
    };

    UnidadeClienteService.prototype.getById = function (id) {
        return this.$http.get(this.REST_URL + "//" + id);
    };

    UnidadeClienteService.prototype.insert = function (json) {
        return this.$http.post(this.REST_URL + "/", json);
    };

    UnidadeClienteService.prototype.update = function (id, json) {
        return this.$http.put(this.REST_URL + "//" + id, json);
    };

    UnidadeClienteService.prototype.remove = function (id) {
        return this.$http.delete(this.REST_URL + "//" + id);
    };

})();