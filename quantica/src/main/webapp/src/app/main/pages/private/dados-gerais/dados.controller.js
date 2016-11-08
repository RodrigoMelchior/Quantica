(function() {
  'use strict';

  angular
    .module('app.pages.private.dados')
    .controller('DadosController', DadosController);

  /** @ngInject */
  function DadosController($scope, $state, $stateParams, $mdToast, $mdDialog, $q, DadosService) {
    var vm = this;
    vm.listaSetor = {};
    vm.listaEmpresa = {};
    vm.listaNivel = {};
    vm.listaCargo = {};
    vm.retorno = {};
    vm.retornoEmp = {};
    vm.pesquisa = {};
    vm.filtro = {};
    vm.filtroEmp = {};
    vm.resultado = resultado;  
    vm.atualizaEmpresa = atualizaEmpresa;
    vm.atualizaCargo = atualizaCargo;
    vm.atualizaEmpresaEmp = atualizaEmpresaEmp;
    vm.atualizaCargoEmp = atualizaCargoEmp;

    DadosService.getListaSetor().then(function (response) {
        vm.listaSetor = response.data;
        vm.listaSetorEmp = response.data;
    });
    DadosService.getListaEmpresa('TODOS').then(function (response) {
        vm.listaEmpresa = response.data;
        vm.listaEmpresaEmp = response.data;
    });
    DadosService.getListaNivel().then(function (response) {
        vm.listaNivel = response.data;
        vm.listaNivelEmp = response.data;
    });
    DadosService.getListaCargo('TODOS').then(function (response) {
        vm.listaCargo = response.data;
        vm.listaCargoEmp = response.data;
    });
    
    function compareObjBy(property) { 
        return function (a,b) { 
            if (a[property] < b[property]) return -1; 
            if (a[property] > b[property]) return 1; 
            return 0; 
        } 
    }

    function quartil(Q, L){
        k = (Q*(L.length+1))/4;
        ki = parseInt(k);
        retorno = L[(ki-1)] + ((((Q*(L.length+1))/4)-ki)*(L[(ki)]-L[(ki-1)]))
        return retorno;
    }
    function quartilSal1(Q, L){
        var k = (Q*(L.length+1))/4;
        var ki = parseInt(k);
        var retorno = L[(ki-1)].sal1 + ((((Q*(L.length+1))/4)-ki)*(L[(ki)].sal1-L[(ki-1)].sal1))
        return retorno;
    }
    function quartilSal2(Q, L){
        var k = (Q*(L.length+1))/4;
        var ki = parseInt(k);
        var retorno = L[(ki-1)].sal2 + ((((Q*(L.length+1))/4)-ki)*(L[(ki)].sal2-L[(ki-1)].sal2))
        return retorno;
    }
    function quartilSal3(Q, L){
        var k = (Q*(L.length+1))/4;
        var ki = parseInt(k);
        var retorno = L[(ki-1)].sal3 + ((((Q*(L.length+1))/4)-ki)*(L[(ki)].sal3-L[(ki-1)].sal3))
        return retorno;
    }
    function quartilSal4(Q, L){
        var k = (Q*(L.length+1))/4;
        var ki = parseInt(k);
        var retorno = L[(ki-1)].sal4 + ((((Q*(L.length+1))/4)-ki)*(L[(ki)].sal4-L[(ki-1)].sal4))
        return retorno;
    }
    function quartilSal5(Q, L){
        var k = (Q*(L.length+1))/4;
        var ki = parseInt(k);
        var retorno = L[(ki-1)].sal5 + ((((Q*(L.length+1))/4)-ki)*(L[(ki)].sal5-L[(ki-1)].sal5))
        return retorno;
    }
      
    function resultado() {
      if (vm.filtro.cargo === null || vm.filtro.cargo === undefined || vm.filtro.cargo === ''){
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha o cargo!')
          .position('right')
          .hideDelay(5000)
        );
      } else {
        DadosService.getSearch(vm.filtro).then(function (response) {
            if (response.data.length > 0){ 
                vm.retorno.dados = response.data;
                vm.retorno.mod = vm.retorno.dados.length % 2;
                vm.retorno.meio = vm.retorno.dados.length / 2;
                vm.retorno.ordenado = {};
                //SAL1
                vm.retorno.ordenado.sal1 = {};
                vm.retorno.ordenado.sal1.dados = vm.retorno.dados.sort(compareObjBy);
                vm.retorno.ordenado.sal1.mediana = 0;   
                if (vm.retorno.mod == 0){
                    vm.retorno.ordenado.sal1.mediana = (vm.retorno.ordenado.sal1.dados[vm.retorno.meio - 1].sal1 + vm.retorno.ordenado.sal1.dados[vm.retorno.meio].sal1) / 2;    
                }else{
                    vm.retorno.ordenado.sal1.mediana = (vm.retorno.ordenado.sal1.dados[(vm.retorno.dados.length + 1) / 2].sal1);           
                } 
                vm.retorno.ordenado.sal1.Q1 = quartilSal1(1, vm.retorno.ordenado.sal1.dados);
                vm.retorno.ordenado.sal1.Q3 = quartilSal1(3, vm.retorno.ordenado.sal1.dados);
                //SAL2
                vm.retorno.ordenado.sal2 = {};
                vm.retorno.ordenado.sal2.dados = vm.retorno.dados.sort(compareObjBy);
                vm.retorno.ordenado.sal2.mediana = 0;   
                if (vm.retorno.mod == 0){
                    vm.retorno.ordenado.sal2.mediana = (vm.retorno.ordenado.sal2.dados[vm.retorno.meio - 1].sal2 + vm.retorno.ordenado.sal2.dados[vm.retorno.meio].sal2) / 2;    
                }else{
                    vm.retorno.ordenado.sal2.mediana = (vm.retorno.ordenado.sal2.dados[(vm.retorno.dados.length + 1) / 2].sal2);                
                } 
                vm.retorno.ordenado.sal2.Q1 = quartilSal2(1, vm.retorno.ordenado.sal2.dados);
                vm.retorno.ordenado.sal2.Q3 = quartilSal2(3, vm.retorno.ordenado.sal2.dados);
                //SAL3
                vm.retorno.ordenado.sal3 = {};
                vm.retorno.ordenado.sal3.dados = vm.retorno.dados.sort(compareObjBy);
                vm.retorno.ordenado.sal3.mediana = 0;   
                if (vm.retorno.mod == 0){
                    vm.retorno.ordenado.sal3.mediana = (vm.retorno.ordenado.sal3.dados[vm.retorno.meio - 1].sal3 + vm.retorno.ordenado.sal3.dados[vm.retorno.meio].sal3) / 2;    
                }else{
                    vm.retorno.ordenado.sal3.mediana = (vm.retorno.ordenado.sal3.dados[(vm.retorno.dados.length + 1) / 2].sal3);                
                } 
                vm.retorno.ordenado.sal3.Q1 = quartilSal3(1, vm.retorno.ordenado.sal3.dados);
                vm.retorno.ordenado.sal3.Q3 = quartilSal3(3, vm.retorno.ordenado.sal3.dados);
                //SAL4
                vm.retorno.ordenado.sal4 = {};
                vm.retorno.ordenado.sal4.dados = vm.retorno.dados.sort(compareObjBy);
                vm.retorno.ordenado.sal4.mediana = 0;   
                if (vm.retorno.mod == 0){
                    vm.retorno.ordenado.sal4.mediana = (vm.retorno.ordenado.sal4.dados[vm.retorno.meio - 1].sal4 + vm.retorno.ordenado.sal4.dados[vm.retorno.meio].sal4) / 2;    
                }else{
                    vm.retorno.ordenado.sal4.mediana = (vm.retorno.ordenado.sal4.dados[(vm.retorno.dados.length + 1) / 2].sal4);                
                } 
                vm.retorno.ordenado.sal4.Q1 = quartilSal4(1, vm.retorno.ordenado.sal4.dados);
                vm.retorno.ordenado.sal4.Q3 = quartilSal4(3, vm.retorno.ordenado.sal4.dados);
                //SAL5
                vm.retorno.ordenado.sal5 = {};
                vm.retorno.ordenado.sal5.dados = vm.retorno.dados.sort(compareObjBy);
                vm.retorno.ordenado.sal5.mediana = 0;   
                if (vm.retorno.mod == 0){
                    vm.retorno.ordenado.sal5.mediana = (vm.retorno.ordenado.sal5.dados[vm.retorno.meio - 1].sal5 + vm.retorno.ordenado.sal5.dados[vm.retorno.meio].sal5) / 2;    
                }else{
                    vm.retorno.ordenado.sal5.mediana = (vm.retorno.ordenado.sal5.dados[(vm.retorno.dados.length + 1) / 2].sal5);                
                } 
                vm.retorno.ordenado.sal5.Q1 = quartilSal5(1, vm.retorno.ordenado.sal5.dados);
                vm.retorno.ordenado.sal5.Q3 = quartilSal5(3, vm.retorno.ordenado.sal5.dados);

                
                
                DadosService.getMin(vm.filtro).then(function (response) {
                    vm.retorno.min = response.data;
                });
                DadosService.getMax(vm.filtro).then(function (response) {
                    vm.retorno.max = response.data;
                });
                DadosService.getSum(vm.filtro).then(function (response) {
                    vm.retorno.sum = response.data;
                });

            }
        });
      }
        
      
      if (vm.filtroEmp.cargo === null || vm.filtroEmp.cargo === undefined || vm.filtroEmp.cargo === ''){
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha o cargo!')
          .position('right')
          .hideDelay(5000)
        );
      } else {
        DadosService.getSearch(vm.filtroEmp).then(function (response) {
            if (response.data.length > 0){ 
                vm.retornoEmp.dados = response.data;
                vm.retornoEmp.mod = vm.retornoEmp.dados.length % 2;
                vm.retornoEmp.meio = vm.retornoEmp.dados.length / 2;
                vm.retornoEmp.ordenado = {};
                //SAL1
                vm.retornoEmp.ordenado.sal1 = {};
                vm.retornoEmp.ordenado.sal1.dados = vm.retornoEmp.dados.sort(compareObjBy);
                vm.retornoEmp.ordenado.sal1.mediana = 0;   
                if (vm.retornoEmp.mod == 0){
                    vm.retornoEmp.ordenado.sal1.mediana = (vm.retornoEmp.ordenado.sal1.dados[vm.retornoEmp.meio - 1].sal1 + vm.retornoEmp.ordenado.sal1.dados[vm.retornoEmp.meio].sal1) / 2;    
                }else{
                    vm.retornoEmp.ordenado.sal1.mediana = (vm.retornoEmp.ordenado.sal1.dados[(vm.retornoEmp.dados.length + 1) / 2].sal1);           
                } 
                vm.retornoEmp.ordenado.sal1.Q1 = quartilSal1(1, vm.retornoEmp.ordenado.sal1.dados);
                vm.retornoEmp.ordenado.sal1.Q3 = quartilSal1(3, vm.retornoEmp.ordenado.sal1.dados);
                //SAL2
                vm.retornoEmp.ordenado.sal2 = {};
                vm.retornoEmp.ordenado.sal2.dados = vm.retornoEmp.dados.sort(compareObjBy);
                vm.retornoEmp.ordenado.sal2.mediana = 0;   
                if (vm.retornoEmp.mod == 0){
                    vm.retornoEmp.ordenado.sal2.mediana = (vm.retornoEmp.ordenado.sal2.dados[vm.retornoEmp.meio - 1].sal2 + vm.retornoEmp.ordenado.sal2.dados[vm.retornoEmp.meio].sal2) / 2;    
                }else{
                    vm.retornoEmp.ordenado.sal2.mediana = (vm.retornoEmp.ordenado.sal2.dados[(vm.retornoEmp.dados.length + 1) / 2].sal2);                
                } 
                vm.retornoEmp.ordenado.sal2.Q1 = quartilSal2(1, vm.retornoEmp.ordenado.sal2.dados);
                vm.retornoEmp.ordenado.sal2.Q3 = quartilSal2(3, vm.retornoEmp.ordenado.sal2.dados);
                //SAL3
                vm.retornoEmp.ordenado.sal3 = {};
                vm.retornoEmp.ordenado.sal3.dados = vm.retornoEmp.dados.sort(compareObjBy);
                vm.retornoEmp.ordenado.sal3.mediana = 0;   
                if (vm.retornoEmp.mod == 0){
                    vm.retornoEmpEmp.ordenado.sal3.mediana = (vm.retornoEmp.ordenado.sal3.dados[vm.retornoEmp.meio - 1].sal3 + vm.retornoEmp.ordenado.sal3.dados[vm.retornoEmp.meio].sal3) / 2;    
                }else{
                    vm.retornoEmp.ordenado.sal3.mediana = (vm.retornoEmp.ordenado.sal3.dados[(vm.retornoEmp.dados.length + 1) / 2].sal3);                
                } 
                vm.retornoEmp.ordenado.sal3.Q1 = quartilSal3(1, vm.retornoEmp.ordenado.sal3.dados);
                vm.retornoEmp.ordenado.sal3.Q3 = quartilSal3(3, vm.retornoEmp.ordenado.sal3.dados);
                //SAL4
                vm.retornoEmp.ordenado.sal4 = {};
                vm.retornoEmp.ordenado.sal4.dados = vm.retornoEmp.dados.sort(compareObjBy);
                vm.retornoEmp.ordenado.sal4.mediana = 0;   
                if (vm.retornoEmp.mod == 0){
                    vm.retornoEmp.ordenado.sal4.mediana = (vm.retornoEmp.ordenado.sal4.dados[vm.retornoEmp.meio - 1].sal4 + vm.retornoEmp.ordenado.sal4.dados[vm.retornoEmp.meio].sal4) / 2;    
                }else{
                    vm.retornoEmp.ordenado.sal4.mediana = (vm.retornoEmp.ordenado.sal4.dados[(vm.retornoEmp.dados.length + 1) / 2].sal4);                
                } 
                vm.retornoEmp.ordenado.sal4.Q1 = quartilSal4(1, vm.retornoEmp.ordenado.sal4.dados);
                vm.retornoEmp.ordenado.sal4.Q3 = quartilSal4(3, vm.retornoEmp.ordenado.sal4.dados);
                //SAL5
                vm.retornoEmp.ordenado.sal5 = {};
                vm.retornoEmp.ordenado.sal5.dados = vm.retornoEmp.dados.sort(compareObjBy);
                vm.retornoEmp.ordenado.sal5.mediana = 0;   
                if (vm.retornoEmp.mod == 0){
                    vm.retornoEmp.ordenado.sal5.mediana = (vm.retornoEmp.ordenado.sal5.dados[vm.retornoEmp.meio - 1].sal5 + vm.retornoEmp.ordenado.sal5.dados[vm.retornoEmp.meio].sal5) / 2;    
                }else{
                    vm.retornoEmp.ordenado.sal5.mediana = (vm.retornoEmp.ordenado.sal5.dados[(vm.retornoEmp.dados.length + 1) / 2].sal5);                
                } 
                vm.retornoEmp.ordenado.sal5.Q1 = quartilSal5(1, vm.retornoEmp.ordenado.sal5.dados);
                vm.retornoEmp.ordenado.sal5.Q3 = quartilSal5(3, vm.retornoEmp.ordenado.sal5.dados);

                
                DadosService.getMin(vm.filtroEmp).then(function (response) {
                    vm.retornoEmp.min = response.data;
                });
                DadosService.getMax(vm.filtroEmp).then(function (response) {
                    vm.retornoEmp.max = response.data;
                });
                DadosService.getSum(vm.filtroEmp).then(function (response) {
                    vm.retornoEmp.sum = response.data;
                });
                
            }
        });

      }
    }

    function atualizaEmpresa(){
        DadosService.getListaEmpresa(vm.filtro.setor).then(function (response) {
            vm.listaEmpresa = response.data;
        });
    }
    function atualizaEmpresaEmp(){
        DadosService.getListaEmpresa(vm.filtroEmp.setor).then(function (response) {
            vm.listaEmpresaEmp = response.data;
        });
    }
    
    function atualizaCargoEmp(){
        DadosService.getListaCargo(vm.filtroEmp.nivel).then(function (response) {
            vm.listaCargoEmp = response.data;
        });
    }
    function atualizaCargo(){
        DadosService.getListaCargo(vm.filtro.nivel).then(function (response) {
            vm.listaCargo = response.data;
        });
    }
    
    function voltar() {
      $state.go('app.dados');
    }

    function validarFormulario() {
      var defer = $q.defer();
      if (vm.ceForm.$invalid) {
        angular.forEach(vm.ceForm.$error, function (val, index) {
          angular.forEach(val, function (obj) {
              vm.ceForm[obj.$name].$setTouched();
          });
        });
        defer.reject();
      } else {
        defer.resolve();
      }

      return defer.promise;
    }
  }
})();
