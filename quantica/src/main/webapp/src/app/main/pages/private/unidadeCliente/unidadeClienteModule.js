(function ()
{
    'use strict';

    angular
            .module('app.pages.private.unidadeCliente', ['app.pages.private.unidadeCliente.service'])
            .config(config)
            .constant('UNIT_CLIENT_PERMISSIONS', {
                view: 'GR_UNIT_CLIENT_PERMISSIONS_VIEW',
                edit: 'GR_UNIT_CLIENT_PERMISSIONS_EDIT',
                insert: 'GR_UNIT_CLIENT_PERMISSIONS_INSERT',
                delete: 'GR_UNIT_CLIENT_PERMISSIONS_DELETE'
            });

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider, UNIT_CLIENT_PERMISSIONS)
    {
        // State
        $stateProvider
                .state('app.unidadeCliente', {
                    url: '/unidadeCliente',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/unidadeCliente/unidadeCliente.html',
                            controller: 'unidadeClienteController as vm',
                        }
                    },
                    resolve: {
                        SampleData: function (apiResolver)
                        {
                            return apiResolver.resolve('sample@get');
                        }
                    },
                    data: {
                        publicRoute: false,
                        authorities: [UNIT_CLIENT_PERMISSIONS.view, UNIT_CLIENT_PERMISSIONS.edit, UNIT_CLIENT_PERMISSIONS.insert, UNIT_CLIENT_PERMISSIONS.delete]
                    }
                })
                .state('app.unidadeCliente.incluirUnidadeCliente', {
                    url: '/incluir',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/unidadeCliente/incluirUnidadeCliente.html',
                            controller: 'unidadeClienteController as CtrlIncluir'
                        }
                    },
                    resolve: {
                        SampleData: function (apiResolver)
                        {
                            return apiResolver.resolve('sample@get');
                        }
                    },
                    data: {
                        publicRoute: false,
                        authorities: [UNIT_CLIENT_PERMISSIONS.view, UNIT_CLIENT_PERMISSIONS.edit, UNIT_CLIENT_PERMISSIONS.insert, UNIT_CLIENT_PERMISSIONS.delete]
                    }
                }).state('app.unidadeCliente.editarUnidadeCliente', {
                    url: '/edit/:id',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/unidadeCliente/editarUnidadeCliente.html',
                            controller: 'unidadeClienteController'
                        }
                    },
                    resolve: {
                        SampleData: function (apiResolver)
                        {
                            return apiResolver.resolve('sample@get');
                        }
                    },
                    data: {
                        publicRoute: false,
                        authorities: [UNIT_CLIENT_PERMISSIONS.view, UNIT_CLIENT_PERMISSIONS.edit, UNIT_CLIENT_PERMISSIONS.insert, UNIT_CLIENT_PERMISSIONS.delete]
                    }
                }).state('app.unidadeCliente.vizualizarUnidadeCliente', {
                    url: '/:id',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/unidadeCliente/vizualizarUnidadeCliente.html',
                            controller: 'unidadeClienteController'
                        }
                    },
                    resolve: {
                        SampleData: function (apiResolver)
                        {
                            return apiResolver.resolve('sample@get');
                        }
                    },
                    data: {
                        publicRoute: false,
                        authorities: [UNIT_CLIENT_PERMISSIONS.view, UNIT_CLIENT_PERMISSIONS.edit, UNIT_CLIENT_PERMISSIONS.insert, UNIT_CLIENT_PERMISSIONS.delete]
                    }
                });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/pages/private/unidadeCliente');


    }
})();