(function ()
{
    'use strict';
    angular
            .module('app.pages.private.funcionarioCtisSonda')
            .controller('funcionarioCtisSondaController', funcionarioCtisSondaController);
    /** @ngInject */
    function funcionarioCtisSondaController($scope, $location, $mdToast, $stateParams, $state, $mdDialog)
    {
        $scope.cancel = function (){
            $location.path('/funcionarioCtisSonda');
        };

        $scope.gridContent = [
            {
                id: "1",
                nome: "Matheus Ribeiro",
                cpf: "123.321.123.09",
                login: "matheus.herculano",
                situacao: "andamento",
                matricula: "12344432",
                pis:"12333",
                dt_nasc: new Date(),
                emailPessoal:"matheus.herculano@ctis.com.br",
                telResidencial:"34343434",
                telCelular:"89876566",
                uf:"DF",
                cidade:"Plano Piloto",
                cep:"72600000",
                endereco:"meu endeco casa 058",
                grauInstrucao: "Superior",
                sexo:"m",
                cargoClt:"",
                funcaoContrato:"",
                senioridade:"",
                centroDeCusto:"",
                telCorporativo:"",
                emailCorporativo:"",
                salario:"165467",
                anuenio:"12",
                dt_adimissao:new Date(),
                dt_demissao:"",
                motivoDemissao:"",
                OBS:"",
                horarioTrabalho:"",
                jornadaDeTrabalho:"40",
                RPE:"true",
                banco:"Santander",
                agencia:"1234",
                conta:"1233-33223"
            },
            {
                id: "2",
                nome: "Fulano da Silva",
                cpf: "123.321.123.09",
                login: "Fulano.Souza",
                situacao: "andamento",
                matricula: "12344432",
                pis:"12333",
                dt_nasc: new Date(),
                emailPessoal:"matheus.herculano@gmail.com.br",
                telResidencial:"6134343434",
                telCelular:"6189876566",
                uf:"DF",
                cidade:"Plano Piloto",
                cep:"72600000",
                endereco:"meu endeco casa 058",
                grauInstrucao: "Superior",
                sexo:"m",
                cargoClt:"",
                funcaoContrato:"",
                senioridade:"",
                centroDeCusto:"",
                telCorporativo:"6134343334",
                emailCorporativo:"matheus.herculano@ctis.com.br",
                salario:"165467",
                anuenio:"12",
                dt_adimissao:new Date(),
                dt_demissao:"",
                motivoDemissao:"",
                OBS:"",
                horarioTrabalho:"08:00",
                jornadaDeTrabalho:"40",
                RPE:"true",
                banco:"Santander",
                agencia:"1234",
                conta:"1233-33223"
            }
        ];
        console.info($scope.gridContent);

        //tela de confirmação de exclusão
        $scope.showConfirm = function (ctis) {
            var confirm = $mdDialog.confirm()
                    .title('Deseja excluir o registro selecionado?')
                    .textContent()
                    .ariaLabel('Lucky day')
                    .targetEvent(ctis)
                    .clickOutsideToClose(true)
                    .parent(angular.element(document.body))
                    .ok('Sim')
                    .cancel('Não');
            $mdDialog.show(confirm).then(function () {

                $mdToast.show(
                        $mdToast.simple()
                        .textContent("O(a) " + ctis.nome + " foi excluído(a) com sucesso!")
                        .position('right')
                        .hideDelay(5000)
                        );

                console.log("SIM");
            }, function () {
                console.log("Não");
            });
        };
        
        // carregar dados para editar
        if ($state.current.name === 'app.editarFuncionarioCtisSonda') {
            angular.forEach($scope.gridContent, function (value, key) {
                if (value.id === $stateParams.id) {
                    $scope.wizard = value;
                }
            });
        }
        
        // carregar dados para vizualizar
        if ($state.current.name === 'app.vizualizarFuncionarioCtisSonda') {
            angular.forEach($scope.gridContent, function (value, key) {
                if (value.id === $stateParams.id) {
                    $scope.vizualizarForm = value;
                }
            });
        }
        
    }
})();
