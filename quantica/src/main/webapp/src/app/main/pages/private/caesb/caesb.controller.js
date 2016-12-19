(function() {
  'use strict';

  angular
    .module('app.pages.private.caesb')
    .controller('CaesbController', CaesbController);

  /** @ngInject */
  function CaesbController($scope, $state, $stateParams, $mdToast, $mdDialog, $q, CaesbService) {
    var vm = this;
    vm.listaTipo = {};
    vm.listaEmpresa = {};
    vm.listaCod = {};
    vm.retorno = {};
    vm.retornoEmp = {};
    vm.pesquisa = {};
    vm.filtro = {};
    vm.filtroEmp = {};
    vm.resultado = resultado;  
    vm.marcaTodos = marcaTodos;  

    CaesbService.getListaTipo().then(function (response) {
        vm.listaTipo = response.data;
        vm.listaTipoEmp = response.data;
    });
/*      
    CaesbService.getListaEmpresa().then(function (response) {
        vm.listaEmpresa = response.data;
        vm.listaEmpresaEmp = response.data;
    });
*/
    CaesbService.getListaCod().then(function (response) {
        vm.listaCod = response.data;
        vm.listaCodEmp = response.data;
    });
    
    function compareObjBy(property) { 
        return function (a,b) { 
            if (a[property] < b[property]) return -1; 
            if (a[property] > b[property]) return 1; 
            return 0; 
        } 
    }

    function quartil(Q, L){
        var k = (Q*(L.length-1))/4;
        var ki = parseInt(k);
        var retorno = L[ki] + ((k-ki)*(L[(ki+1)]-L[(ki)]));
        return retorno;
    }
    function quartilSal1(Q, L){
        var k = (Q*(L.length-1))/4;
        var ki = parseInt(k);
        var retorno = L[ki].sal1 + ((k-ki)*(L[(ki+1)].sal1-L[(ki)].sal1));
        return retorno;
    }
    function quartilSal2(Q, L){
        var k = (Q*(L.length-1))/4;
        var ki = parseInt(k);
        var retorno = L[ki].sal2 + ((k-ki)*(L[(ki+1)].sal2-L[(ki)].sal2));
        return retorno;
    }
    function quartilSal3(Q, L){
        var k = (Q*(L.length-1))/4;
        var ki = parseInt(k);
        var retorno = L[ki].sal3 + ((k-ki)*(L[(ki+1)].sal3-L[(ki)].sal3));
        return retorno;
    }
    function quartilSal4(Q, L){
        var k = (Q*(L.length-1))/4;
        var ki = parseInt(k);
        var retorno = L[ki].sal4 + ((k-ki)*(L[(ki+1)].sal4-L[(ki)].sal4));
        return retorno;
    }
    function quartilSal5(Q, L){
        var k = (Q*(L.length-1))/4;
        var ki = parseInt(k);
        var retorno = L[ki].sal5 + ((k-ki)*(L[(ki+1)].sal5-L[(ki)].sal5));
        return retorno;
    }
    
    function preparaVetorSal1(v){
        var vAtual = 0;
        var contador = 0;
        var vRetorno = 0;
        var cRetorno = 0;
        angular.forEach(v, function (val, index) {
            if (vAtual == val.sal1) {
                contador++;
            }else{
                if (cRetorno < contador){
                    cRetorno = contador;
                    vRetorno = vAtual;
                }else if(cRetorno == contador){
                    vRetorno += ' - ' + vAtual;
                }
                contador = 1;
                vAtual = val.sal1;
            }
        });
        if (cRetorno < contador){
            cRetorno = contador;
            vRetorno = vAtual;
        }else if(cRetorno == contador){
            vRetorno += ' - ' + vAtual;
        }
        return vRetorno;
    }
      
    function preparaVetorSal2(v){
        var vAtual = 0;
        var contador = 0;
        var vRetorno = 0;
        var cRetorno = 0;
        angular.forEach(v, function (val, index) {
            if (vAtual == val.sal2) {
                contador++;
            }else{
                if (cRetorno < contador){
                    cRetorno = contador;
                    vRetorno = vAtual;
                }else if(cRetorno == contador){
                    vRetorno += ' - ' + vAtual;
                }
                contador = 1;
                vAtual = val.sal2;
            }
        });
        if (cRetorno < contador){
            cRetorno = contador;
            vRetorno = vAtual;
        }else if(cRetorno == contador){
            vRetorno += ' - ' + vAtual;
        }
        return vRetorno;
    }
    function preparaVetorSal3(v){
        var vAtual = 0;
        var contador = 0;
        var vRetorno = 0;
        var cRetorno = 0;
        angular.forEach(v, function (val, index) {
            if (vAtual == val.sal3) {
                contador++;
            }else{
                if (cRetorno < contador){
                    cRetorno = contador;
                    vRetorno = vAtual;
                }else if(cRetorno == contador){
                    vRetorno += ' - ' + vAtual;
                }
                contador = 1;
                vAtual = val.sal3;
            }
        });
        if (cRetorno < contador){
            cRetorno = contador;
            vRetorno = vAtual;
        }else if(cRetorno == contador){
            vRetorno += ' - ' + vAtual;
        }
        return vRetorno;
    }
    function preparaVetorSal4(v){
        var vAtual = 0;
        var contador = 0;
        var vRetorno = 0;
        var cRetorno = 0;
        angular.forEach(v, function (val, index) {
            if (vAtual == val.sal4) {
                contador++;
            }else{
                if (cRetorno < contador){
                    cRetorno = contador;
                    vRetorno = vAtual;
                }else if(cRetorno == contador){
                    vRetorno += ' - ' + vAtual;
                }
                contador = 1;
                vAtual = val.sal4;
            }
        });
        if (cRetorno < contador){
            cRetorno = contador;
            vRetorno = vAtual;
        }else if(cRetorno == contador){
            vRetorno += ' - ' + vAtual;
        }
        return vRetorno;
    }
    function preparaVetorSal5(v){
        var vAtual = 0;
        var contador = 0;
        var vRetorno = 0;
        var cRetorno = 0;
        angular.forEach(v, function (val, index) {
            if (vAtual == val.sal5) {
                contador++;
            }else{
                if (cRetorno < contador){
                    cRetorno = contador;
                    vRetorno = vAtual;
                }else if(cRetorno == contador){
                    vRetorno += ' - ' + vAtual;
                }
                contador = 1;
                vAtual = val.sal5;
            }
        });
        if (cRetorno < contador){
            cRetorno = contador;
            vRetorno = vAtual;
        }else if(cRetorno == contador){
            vRetorno += ' - ' + vAtual;
        }
        return vRetorno;
    }
    
    function desvioPadraoSal1(v, m, s){
        var somatorio = 0;
        var media = s.sal1 / m;
        angular.forEach(v, function (val, index) {
            var dm = 0;
            if (val.sal1 > media){
                dm = val.sal1 - media;
            }else{
                dm = media - val.sal1;
            }
            var aoQuadrado = dm * dm;
            somatorio += aoQuadrado;
        });
        var mdp = somatorio / m;
        var raiz = Math.sqrt(mdp);
        return raiz;
    }
    
    function desvioPadraoSal2(v, m, s){
        var somatorio = 0;
        var media = s.sal2 / m;
        angular.forEach(v, function (val, index) {
            var dm = 0;
            if (val.sal2 > media){
                dm = val.sal2 - media;
            }else{
                dm = media - val.sal2;
            }
            var aoQuadrado = dm * dm;
            somatorio += aoQuadrado;
        });
        var mdp = somatorio / m;
        var raiz = Math.sqrt(mdp);
        return raiz;
    }
      
    function desvioPadraoSal3(v, m, s){
        var somatorio = 0;
        var media = s.sal3 / m;
        angular.forEach(v, function (val, index) {
            var dm = 0;
            if (val.sal3 > media){
                dm = val.sal3 - media;
            }else{
                dm = media - val.sal3;
            }
            var aoQuadrado = dm * dm;
            somatorio += aoQuadrado;
        });
        var mdp = somatorio / m;
        var raiz = Math.sqrt(mdp);
        return raiz;
    }
      
    function desvioPadraoSal4(v, m, s){
        var somatorio = 0;
        var media = s.sal4 / m;
        angular.forEach(v, function (val, index) {
            var dm = 0;
            if (val.sal4 > media){
                dm = val.sal4 - media;
            }else{
                dm = media - val.sal1;
            }
            var aoQuadrado = dm * dm;
            somatorio += aoQuadrado;
        });
        var mdp = somatorio / m;
        var raiz = Math.sqrt(mdp);
        return raiz;
    }
      
    function desvioPadraoSal5(v, m, s){
        var somatorio = 0;
        var media = s.sal5 / m;
        angular.forEach(v, function (val, index) {
            var dm = 0;
            if (val.sal5 > media){
                dm = val.sal5 - media;
            }else{
                dm = media - val.sal5;
            }
            var aoQuadrado = dm * dm;
            somatorio += aoQuadrado;
        });
        var mdp = somatorio / m;
        var raiz = Math.sqrt(mdp);
        return raiz;
    }
      
    function resultado() {
      if (vm.filtro.tipo === null || vm.filtro.tipo === undefined || vm.filtro.tipo === ''){
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha o tipo!')
          .position('right')
          .hideDelay(5000)
        );
      } else if (vm.filtro.codigo === null || vm.filtro.codigo === undefined || vm.filtro.codigo === ''){
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha o código!')
          .position('right')
          .hideDelay(5000)
        );
        /*
        }else if (vm.filtro.empresa[0] != 'TODOS' && vm.filtro.empresa.length < 8){
        $mdToast.show(
          $mdToast.simple()
          .textContent('Selecione no mínimo 8 empresas!')
          .position('right')
          .hideDelay(5000)
        );
        */
      } else{
        CaesbService.getSearch(vm.filtro).then(function (response) {
            if (response.data.length > 0){ 
                vm.retorno.dados = response.data;
                vm.retorno.mod = vm.retorno.dados.length % 2;
                if (vm.retorno.mod == 0){
                    vm.retorno.meio = vm.retorno.dados.length / 2;
                } else {
                    vm.retorno.meio = (vm.retorno.dados.length + 1) / 2;
                }
                
                CaesbService.getMin(vm.filtro).then(function (response) {
                    vm.retorno.min = response.data;
                });
                CaesbService.getMax(vm.filtro).then(function (response) {
                    vm.retorno.max = response.data;
                });
                
                vm.retorno.ordenado = {};
                
                CaesbService.getSum(vm.filtro).then(function (response) {
                    vm.retorno.sum = response.data;
                
                    //SAL1
                    vm.retorno.ordenado.sal1 = {};
                    if (vm.retorno.dados.length > 1){
                        vm.retorno.ordenado.sal1.dados = vm.retorno.dados.sort(compareObjBy('sal1'));
                        vm.retorno.ordenado.sal1.mediana = 0;   
                        if (vm.retorno.mod == 0){
                            vm.retorno.ordenado.sal1.mediana = (vm.retorno.ordenado.sal1.dados[vm.retorno.meio - 1].sal1 + vm.retorno.ordenado.sal1.dados[vm.retorno.meio].sal1) / 2;    
                        }else{
                            vm.retorno.ordenado.sal1.mediana = (vm.retorno.ordenado.sal1.dados[(vm.retorno.dados.length - 1) / 2].sal1);
                        } 
                        vm.retorno.ordenado.sal1.desvio = desvioPadraoSal1(vm.retorno.ordenado.sal1.dados, vm.retorno.dados.length, vm.retorno.sum);
                        vm.retorno.ordenado.sal1.moda = preparaVetorSal1(vm.retorno.ordenado.sal1.dados);
                        vm.retorno.ordenado.sal1.Q1 = quartilSal1(1, vm.retorno.ordenado.sal1.dados);
                        vm.retorno.ordenado.sal1.Q3 = quartilSal1(3, vm.retorno.ordenado.sal1.dados);
                    } else {
                        vm.retorno.ordenado.sal1.mediana = vm.retorno.dados[0].sal1;   
                        vm.retorno.ordenado.sal1.Q1 = vm.retorno.dados[0].sal1;
                        vm.retorno.ordenado.sal1.Q3 = vm.retorno.dados[0].sal1;
                    }
                
                    //SAL2
                    vm.retorno.ordenado.sal2 = {};
                    if (vm.retorno.dados.length > 1){
                        vm.retorno.ordenado.sal2.dados = vm.retorno.dados.sort(compareObjBy('sal2'));
                        vm.retorno.ordenado.sal2.mediana = 0;   
                        if (vm.retorno.mod == 0){
                            vm.retorno.ordenado.sal2.mediana = (vm.retorno.ordenado.sal2.dados[vm.retorno.meio - 1].sal2 + vm.retorno.ordenado.sal2.dados[vm.retorno.meio].sal2) / 2;    
                        }else{
                            vm.retorno.ordenado.sal2.mediana = (vm.retorno.ordenado.sal2.dados[(vm.retorno.dados.length - 1) / 2].sal2);                
                        } 
                        vm.retorno.ordenado.sal2.desvio =  desvioPadraoSal2(vm.retorno.ordenado.sal2.dados, vm.retorno.dados.length, vm.retorno.sum);
                        vm.retorno.ordenado.sal2.moda = preparaVetorSal2(vm.retorno.ordenado.sal2.dados);
                        vm.retorno.ordenado.sal2.Q1 = quartilSal2(1, vm.retorno.ordenado.sal2.dados);
                        vm.retorno.ordenado.sal2.Q3 = quartilSal2(3, vm.retorno.ordenado.sal2.dados);
                    } else {
                        vm.retorno.ordenado.sal2.mediana = vm.retorno.dados[0].sal2;   
                        vm.retorno.ordenado.sal2.Q1 = vm.retorno.dados[0].sal2;
                        vm.retorno.ordenado.sal2.Q3 = vm.retorno.dados[0].sal2;
                    }
                    //SAL3
                    vm.retorno.ordenado.sal3 = {};
                    if (vm.retorno.dados.length > 1){
                        vm.retorno.ordenado.sal3.dados = vm.retorno.dados.sort(compareObjBy('sal3'));
                        vm.retorno.ordenado.sal3.mediana = 0;   
                        if (vm.retorno.mod == 0){
                            vm.retorno.ordenado.sal3.mediana = (vm.retorno.ordenado.sal3.dados[vm.retorno.meio - 1].sal3 + vm.retorno.ordenado.sal3.dados[vm.retorno.meio].sal3) / 2;    
                        }else{
                            vm.retorno.ordenado.sal3.mediana = (vm.retorno.ordenado.sal3.dados[(vm.retorno.dados.length - 1) / 2].sal3);                
                        } 
                        vm.retorno.ordenado.sal3.desvio =  desvioPadraoSal3(vm.retorno.ordenado.sal3.dados, vm.retorno.dados.length, vm.retorno.sum);
                        vm.retorno.ordenado.sal3.moda = preparaVetorSal3(vm.retorno.ordenado.sal3.dados);
                        vm.retorno.ordenado.sal3.Q1 = quartilSal3(1, vm.retorno.ordenado.sal3.dados);
                        vm.retorno.ordenado.sal3.Q3 = quartilSal3(3, vm.retorno.ordenado.sal3.dados);
                    } else {
                        vm.retorno.ordenado.sal3.mediana = vm.retorno.dados[0].sal3;   
                        vm.retorno.ordenado.sal3.Q1 = vm.retorno.dados[0].sal3;
                        vm.retorno.ordenado.sal3.Q3 = vm.retorno.dados[0].sal3;
                    }
                    //SAL4
                    vm.retorno.ordenado.sal4 = {};
                    if (vm.retorno.dados.length > 1){
                        vm.retorno.ordenado.sal4.dados = vm.retorno.dados.sort(compareObjBy('sal4'));
                        vm.retorno.ordenado.sal4.mediana = 0;   
                        if (vm.retorno.mod == 0){
                            vm.retorno.ordenado.sal4.mediana = (vm.retorno.ordenado.sal4.dados[vm.retorno.meio - 1].sal4 + vm.retorno.ordenado.sal4.dados[vm.retorno.meio].sal4) / 2;    
                        }else{
                            vm.retorno.ordenado.sal4.mediana = (vm.retorno.ordenado.sal4.dados[(vm.retorno.dados.length - 1) / 2].sal4);                
                        } 
                        vm.retorno.ordenado.sal4.desvio =  desvioPadraoSal4(vm.retorno.ordenado.sal4.dados, vm.retorno.dados.length, vm.retorno.sum);
                        vm.retorno.ordenado.sal4.moda = preparaVetorSal4(vm.retorno.ordenado.sal4.dados);
                        vm.retorno.ordenado.sal4.Q1 = quartilSal4(1, vm.retorno.ordenado.sal4.dados);
                        vm.retorno.ordenado.sal4.Q3 = quartilSal4(3, vm.retorno.ordenado.sal4.dados);
                    } else {
                        vm.retorno.ordenado.sal4.mediana = vm.retorno.dados[0].sal4;   
                        vm.retorno.ordenado.sal4.Q1 = vm.retorno.dados[0].sal4;
                        vm.retorno.ordenado.sal4.Q3 = vm.retorno.dados[0].sal4;
                    }
                    //SAL5
                    vm.retorno.ordenado.sal5 = {};
                    if (vm.retorno.dados.length > 1){
                        vm.retorno.ordenado.sal5.dados = vm.retorno.dados.sort(compareObjBy('sal5'));
                        vm.retorno.ordenado.sal5.mediana = 0;   
                        if (vm.retorno.mod == 0){
                            vm.retorno.ordenado.sal5.mediana = (vm.retorno.ordenado.sal5.dados[vm.retorno.meio - 1].sal5 + vm.retorno.ordenado.sal5.dados[vm.retorno.meio].sal5) / 2;    
                        }else{
                            vm.retorno.ordenado.sal5.mediana = (vm.retorno.ordenado.sal5.dados[(vm.retorno.dados.length - 1) / 2].sal5);                
                        } 
                        vm.retorno.ordenado.sal5.desvio =  desvioPadraoSal5(vm.retorno.ordenado.sal5.dados, vm.retorno.dados.length, vm.retorno.sum);
                        vm.retorno.ordenado.sal5.moda = preparaVetorSal5(vm.retorno.ordenado.sal5.dados);
                        vm.retorno.ordenado.sal5.Q1 = quartilSal5(1, vm.retorno.ordenado.sal5.dados);
                        vm.retorno.ordenado.sal5.Q3 = quartilSal5(3, vm.retorno.ordenado.sal5.dados);
                    } else {
                        vm.retorno.ordenado.sal5.mediana = vm.retorno.dados[0].sal5;   
                        vm.retorno.ordenado.sal5.Q1 = vm.retorno.dados[0].sal5;
                        vm.retorno.ordenado.sal5.Q3 = vm.retorno.dados[0].sal5;
                    }
                });
            }
        });
      }
    }

      
    function marcaTodos(){
        angular.forEach(vm.filtro.empresa, function (val, index) {
            if (val === 'TODOS'){
                vm.filtro.empresa = ['TODOS'];
            }
        });
    }
    
    function voltar() {
      $state.go('app.caesb');
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
