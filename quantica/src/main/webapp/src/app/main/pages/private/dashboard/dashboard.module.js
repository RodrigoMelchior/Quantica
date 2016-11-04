(function () {
  'use strict';

  angular
  .module('app.pages.private.dashboard', [])
  .config(config);



  /** @ngInject */
  function config($stateProvider) {
    $stateProvider
    .state('app.dashboard', {
      url: '/dashboard',
      views: {
        'content@app': {
          templateUrl: 'app/main/pages/private/dashboard/dashboard.html',
          controller: 'DashboardController as vm',
        }
      }
    });
  }
})();
