(function ()
{
    'use strict';

    angular
            .module('app.pages.funcionario', [])
            .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
                .state('app.funcionarioContratante', {
                    url: '/funcionario/contratante',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/Funcionario/funcionarioContratante.html',
                            controller: 'funcionarioContratanteController as vm',
                            publicRoute: true,
                            permissions: ['']
                        },
                        resolve: {
                            SampleData: function (apiResolver)
                            {
                                return apiResolver.resolve('sample@get');
                            }
                        }
                    },
                    bodyClass: 'funcionario'
                })
                .state('app.funcionarioContratada', {
                    url: '/funcionario/contratada',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/Funcionario/funcionarioContratada.html',
                            controller: 'funcionarioContratadaController as vm'
                        },
                        resolve: {
                            SampleData: function (apiResolver)
                            {
                                return apiResolver.resolve('sample@get');
                            }
                        }
                    },
                    bodyClass: 'funcionario'
                });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/pages/Funcionario');



    }

})();