(function() {
  'use strict';

  angular
    .module('app.pages')
    .service('UtilService', UtilService);

  /** @ngInject */
  function UtilService ($http, REST_URL) {
    var urlUf = REST_URL + '/ufs';

    var getAllUfs = function () {
      return $http.get(urlUf + '?paging=false');
    };

      
    var dateConverte = function(data){
        if (data != null && data != undefined ){
            var ano = data.substring(0, 4);
            var mes = data.substring(5, 7);
            var dia = data.substring(8, 10);       
            return new Date(ano, mes - 1, dia); 
        }else{
            return null;
        }
    }
      
    return {
      getAllUfs   : getAllUfs,
      dateConverte:dateConverte
    };
  }
})();
