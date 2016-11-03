(function () {
  'use strict';

  angular
    .module('app.pages.private.funcionarioCliente', ['app.pages.private.funcionarioCliente.funcionarioClienteService'])
    .config(Config)
    .run(Run)
    .constant('EMPLOYEEcLIENT_PERMISSIONS', {
      view: 'GR_EMPLOYEEcLIENT_VIEW',
      edit: 'GR_EMPLOYEEcLIENT_EDIT',
      insert: 'GR_EMPLOYEEcLIENT_INSERT',
      delete: 'GR_EMPLOYEEcLIENT_DELETE'
    });

  /** @ngInject */
  function Config($stateProvider,
                  EMPLOYEEcLIENT_PERMISSIONS) {
    // State
    $stateProvider
      .state('app.funcionarioCliente', {
        url: '/funcionarioCliente',
        views: {
          'content@app': {
            templateUrl: 'app/main/pages/private/funcionarioCliente/funcionarioCliente.html',
            controller: 'funcionarioClienteController as vm',
          }
        },
          data: {
            publicRoute: false,
            authorities: [EMPLOYEEcLIENT_PERMISSIONS.view, EMPLOYEEcLIENT_PERMISSIONS.edit, EMPLOYEEcLIENT_PERMISSIONS.insert, EMPLOYEEcLIENT_PERMISSIONS.delete]
          }
        })
      .state('app.funcionarioCliente.incluir', {
        url: '/incluir',
        views: {
          'content@app': {
            templateUrl: 'app/main/pages/private/funcionarioCliente/incluirFuncionarioCliente.html',
            controller: 'funcionarioClienteController as incluirCtrl'
          }
        },
          data: {
            publicRoute: false,
            authorities: [EMPLOYEEcLIENT_PERMISSIONS.view, EMPLOYEEcLIENT_PERMISSIONS.edit, EMPLOYEEcLIENT_PERMISSIONS.insert, EMPLOYEEcLIENT_PERMISSIONS.delete]
          }
        })
      .state('app.funcionarioCliente.visualizar', {
        url: '/:id/visualizar',
        views: {
          'content@app': {
            templateUrl: 'app/main/pages/private/funcionarioCliente/vizualizarFuncionarioCliente.html',
            controller: 'funcionarioClienteController as vm'
          }
        },
          data: {
            publicRoute: false,
            authorities: [EMPLOYEEcLIENT_PERMISSIONS.view, EMPLOYEEcLIENT_PERMISSIONS.edit, EMPLOYEEcLIENT_PERMISSIONS.insert, EMPLOYEEcLIENT_PERMISSIONS.delete]
          }
        })
      .state('app.funcionarioCliente.editar', {
        url: '/:id/editar',
        views: {
          'content@app': {
            templateUrl: 'app/main/pages/private/funcionarioCliente/incluirFuncionarioCliente.html',
            controller: 'funcionarioClienteController as vm'
          }
        },
          data: {
            publicRoute: false,
            authorities: [EMPLOYEEcLIENT_PERMISSIONS.view, EMPLOYEEcLIENT_PERMISSIONS.edit, EMPLOYEEcLIENT_PERMISSIONS.insert, EMPLOYEEcLIENT_PERMISSIONS.delete]
          }
        });
  }
  
  /**
   * @ngInject
   * @param {type} $translatePartialLoader
   * @returns {undefined}
   */
  function Run($translatePartialLoader) {
      $translatePartialLoader.addPart('app/pages/private/funcionarioCliente');
  }
  
})();
