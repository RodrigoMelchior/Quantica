(function ()
{
    'use strict';
    angular
            .module('app.pages.private.centroDeCusto.centroDeCustoService', [])
            .service('centroDeCustoService', function ($http, REST_URL) {
                var pesquisa = {}; //manter os parametros da pesquisa
                    this.getPesquisaParams = function () {
                        return pesquisa;
                    };
                    this.setPesquisaParams = function (valor) {
                        pesquisa = valor;
                    };
                    this.save = function (json) {
                        return $http.post(REST_URL + "/centroscustos", json);
                    };
                    this.paginate = function (page, limit) {
                        page = page || 1;
                        limit = limit || 10;
                        
                        return $http({
                            method: 'GET',
                            url: REST_URL + "/centroscustos",
                            params: {
                                page: page,
                                limit: limit
                            }
                        });
                    };
                    this.getById = function (id) {
                        return $http.get(REST_URL + "/centroscustos/porid/"+id);
                    };
                    this.remove = function (id){
                        return $http.delete(REST_URL + "/centroscustos/"+id);
                    }
                    this.update = function (id, json){
                      return $http.put(REST_URL + "/centroscustos/"+id, json);  
                    };
                    this.search = function (obj, page, limit) {
                        obj.page = page || 1;
                        obj.limit = limit || 10;
                        
                        return $http({
                            method: 'GET',
                            url: REST_URL + "/centroscustos/search",
                            params: obj
                        });
                    };
                    
                    this.autoComplete = function (num) {
                        return $http({
                            method: 'GET',
                            url: REST_URL + "/centroscustos/search",
                            params: {
                                numerocentrocusto:num,
                                ativo: true,
                                page: 1,
                                limit: 50
                            }
                        });
                    };
                    this.getArvore = function (id){
                      return $http.get(REST_URL + "/centroscustos/arvore/"+id);  
                    };
                    
                    this.verificaCentroCusto = function (numero){
                        return $http.get(REST_URL + "/centroscustos/validacoes/existecentrocusto/"+numero);
                    };
            });
})();