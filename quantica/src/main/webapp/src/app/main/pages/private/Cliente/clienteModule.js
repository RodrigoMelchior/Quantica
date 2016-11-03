/**
 * 
 * @author Marcus Vinicius <marcus.fms@gmail.com> e Matheus Herculano <>
 * @returns {undefined}
 */
(function ()
{
    'use strict';

    angular
            .module('app.pages.private.cliente', [
                'app.pages.private.cliente.clienteService'
            ])
            .config(config)
            .constant('CLIENTE_PERMISSIONS', {
                view: 'GR_CLIENTE_VIEW',
                edit: 'GR_CLIENTE_EDIT',
                insert: 'GR_CLIENTE_INSERT',
                delete: 'GR_CLIENTE_DELETE'
            });

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider, RestangularProvider, CLIENTE_PERMISSIONS)
    {
        // State
        $stateProvider
                .state('app.cadastroCliente', {
                    url: '/cliente',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/cliente/cliente.html',
                            controller: 'clienteController as vm',
                        }
                    },
                    resolve: {
                        SampleData: function (apiResolver)
                        {
                            return apiResolver.resolve('sample@get');
                        },
                        Uf: function (UfService) {
                            return UfService.get().then(function (response) {
                                return response;
                            }, function () {
                                console.log("Falha ao recuperar UF");
                            });
                        }
                    },
                    data: {
                        publicRoute: false,
                        authorities: [CLIENTE_PERMISSIONS.view, CLIENTE_PERMISSIONS.edit, CLIENTE_PERMISSIONS.insert, CLIENTE_PERMISSIONS.delete]
                    }
                }).state('app.cadastroCliente.incluirCliente', {
            url: '/incluir',
            views: {
                'content@app': {
                    templateUrl: 'app/main/pages/private/cliente/incluirCliente.html',
                    controller: 'clienteController as vm'
                }
            },
            resolve: {
                SampleData: function (apiResolver)
                {
                    return apiResolver.resolve('sample@get');
                },
                Uf: function (UfService) {
                    return UfService.get().then(function (response) {
                        return response;
                    }, function () {
                        console.log("Falha ao recuperar UF");
                    });
                }
            },
            data: {
                publicRoute: false,
                authorities: [CLIENTE_PERMISSIONS.view, CLIENTE_PERMISSIONS.edit, CLIENTE_PERMISSIONS.insert, CLIENTE_PERMISSIONS.delete]
            }
        }).state('app.cadastroCliente.editarCliente', {
            url: '/edit/:id',
            views: {
                'content@app': {
                    templateUrl: 'app/main/pages/private/cliente/editarCliente.html',
                    controller: 'clienteController as editClient'
                }
            },
            resolve: {
                SampleData: function (apiResolver)
                {
                    return apiResolver.resolve('sample@get');
                },
                Uf: function (UfService) {
                    return UfService.get().then(function (response) {
                        return response;
                    }, function () {
                        console.log("Falha ao recuperar UF");
                    });
                }
            },
            data: {
                publicRoute: false,
                authorities: [CLIENTE_PERMISSIONS.view, CLIENTE_PERMISSIONS.edit, CLIENTE_PERMISSIONS.insert, CLIENTE_PERMISSIONS.delete]
            }
        }).state('app.cadastroCliente.visualizar', {
            url: '/:id',
            views: {
                'content@app': {
                    templateUrl: 'app/main/pages/private/cliente/visualizarCliente.html',
                    controller: 'clienteController as viewCliente'
                }
            },
            resolve: {
                SampleData: function (apiResolver)
                {
                    return apiResolver.resolve('sample@get');
                },
                Uf: function (UfService) {
                    return UfService.get().then(function (response) {
                        return response;
                    }, function () {
                        console.log("Falha ao recuperar UF");
                    });
                }
            },
            data: {
                publicRoute: false,
                authorities: [CLIENTE_PERMISSIONS.view, CLIENTE_PERMISSIONS.edit, CLIENTE_PERMISSIONS.insert, CLIENTE_PERMISSIONS.delete]
            }
        });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/pages/private/cliente');


    }
})();