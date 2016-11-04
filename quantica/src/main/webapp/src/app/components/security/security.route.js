(function ()
{
    'use strict';



    angular.module('app.components.security').run(function ($rootScope, $state, Authentication) {
      $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){
          authorize();
      });

      function authorize(){
          Authentication.authorize(false);
      }
    });

})();
