(function ()
{

    'use strict';

    angular.module('app.components.security')
        .factory('Activate', function ($resource) {
            return $resource('api/activate', {}, {
                'get': { method: 'GET', params: {}, isArray: false}
            });
        });

})();
