(function ()
{

/*jshint bitwise: false*/
'use strict';

angular.module('app.components.utils')
    .service('UfService', function ($http, REST_URL) {
                    this.get = function () {
                        return $http.get(REST_URL + "/ufs?paging=false");
                    };
            })
            .service('municipioService', function ($http, REST_URL) {
                    this.get = function (idUf) {
                        return $http.get(REST_URL + "/ufs/" + idUf + "/municipios?paging=false");
                    };
            });

})();
