(function ()
{
    'use strict';

    angular
            .module('app.pages.private.funcionarioCtisSonda', [])
            .config(config)
            .constant('EMPLOYEE_CTIS_SONDA_PERMISSIONS', {
                view: 'GR_EMPLOYEE_CTIS_SONDA_VIEW',
                edit: 'GR_EMPLOYEE_CTIS_SONDA_EDIT',
                insert: 'GR_EMPLOYEE_CTIS_SONDA_INSERT',
                delete: 'GR_EMPLOYEE_CTIS_SONDA_DELETE'
            });

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider, EMPLOYEE_CTIS_SONDA_PERMISSIONS)
    {
        // State
        $stateProvider
                .state('app.funcionarioCtisSonda', {
                    url: '/funcionarioCtisSonda',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/funcionarioCtisSonda/funcionarioCtisSonda.html',
                            controller: 'funcionarioCtisSondaController',
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
                        authorities: [EMPLOYEE_CTIS_SONDA_PERMISSIONS.view, EMPLOYEE_CTIS_SONDA_PERMISSIONS.edit, EMPLOYEE_CTIS_SONDA_PERMISSIONS.insert, EMPLOYEE_CTIS_SONDA_PERMISSIONS.delete]
                    }
                }).state('app.incluirFuncionarioCtisSonda', {
                    url: '/funcionarioCtisSonda/incluir',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/funcionarioCtisSonda/incluirFuncionarioCtisSonda.html',
                            controller: 'funcionarioCtisSondaController',
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
                        authorities: [EMPLOYEE_CTIS_SONDA_PERMISSIONS.view, EMPLOYEE_CTIS_SONDA_PERMISSIONS.edit, EMPLOYEE_CTIS_SONDA_PERMISSIONS.insert, EMPLOYEE_CTIS_SONDA_PERMISSIONS.delete]
                    }
                }).state('app.editarFuncionarioCtisSonda', {
                    url: '/funcionarioCtisSonda/edit/:id',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/funcionarioCtisSonda/incluirFuncionarioCtisSonda.html',
                            controller: 'funcionarioCtisSondaController',
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
                        authorities: [EMPLOYEE_CTIS_SONDA_PERMISSIONS.view, EMPLOYEE_CTIS_SONDA_PERMISSIONS.edit, EMPLOYEE_CTIS_SONDA_PERMISSIONS.insert, EMPLOYEE_CTIS_SONDA_PERMISSIONS.delete]
                    }
                }).state('app.vizualizarFuncionarioCtisSonda', {
                    url: '/funcionarioCtisSonda/:id',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/funcionarioCtisSonda/vizualizarFuncionarioCtisSonda.html',
                            controller: 'funcionarioCtisSondaController',
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
                        authorities: [EMPLOYEE_CTIS_SONDA_PERMISSIONS.view, EMPLOYEE_CTIS_SONDA_PERMISSIONS.edit, EMPLOYEE_CTIS_SONDA_PERMISSIONS.insert, EMPLOYEE_CTIS_SONDA_PERMISSIONS.delete]
                    }
                });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/pages/private/funcionarioCtisSonda');


    }
})();