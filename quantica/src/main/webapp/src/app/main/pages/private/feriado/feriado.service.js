(function () {
  'use strict';

  angular
    .module('app.pages.private.feriado.feriadoService', [])
    .service('FeriadoService', FeriadoService);


  var _pesquisa = {};

  /** @ngInject */
  function FeriadoService($http, REST_URL) {
    this.$http = $http;
    this.REST_URL = REST_URL;
    this.REST_URL = 'app/main/pages/private/feriado/data/grid.json';
  }
  
    FeriadoService.prototype.getAll = function (){
      return this.$http.get(this.REST_URL);  
    };


})();
