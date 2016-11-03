/* global $mdToast */
/**
 * 
 * @author Marcus Vinicius <marcus.fms@gmail.com> e Matheus Herculano <>
 * @returns {undefined}
 */
!(function () {
    'use strict';
    angular
            .module('app.pages.private.cliente')
            .controller('clienteController', ClienteController);
    /**
     * @ngInject 
     * @author Marcus Vinicius <marcus.fms@gmail.com>
     * @param {type} maskInJs
     * @param {type} ROWS_PAGE
     * @param {type} $stateParams
     * @param {type} $state
     * @param {type} $mdToast
     * @param {type} $scope
     * @param {type} $mdDialog
     * @param {type} ClienteService
     * @param {type} Uf
     * @param {type} municipioService
     * @param {type} $log
     * @param {type} $q
     * @returns {clienteController_L3.ClienteController}
     */
    function ClienteController(maskInJs,
            ROWS_PAGE,
            $stateParams,
            $state,
            $mdToast,
            $scope,
            $mdDialog,
            ClienteService,
            Uf,
            municipioService,
            $log,
            $q) {
        this.maskInJs = maskInJs;
        this.ROWS_PAGE = ROWS_PAGE;
        this.$stateParams = $stateParams;
        this.$state = $state;
        this.$mdToast = $mdToast;
        this.$scope = $scope;
        this.$mdDialog = $mdDialog;
        this.ClienteService = ClienteService;
        this.Uf = Uf;
        this.municipioService = municipioService;
        this.$log = $log;
        this.$q = $q;
        var $this = this;

        //Objeto onde guarda todos os fiscais
        $this.unidadesFiscais = [];
        
        $this.totalCount = 0;

        /**
         * @desc Propriedades utilizadas como objetos na view
         * @type {{}}
         */
        this.pesquisa = this.ClienteService.getPesquisaParams();
        /*
         *@descr carregamento da grid          
         * 
         */
        this.showGrid = Object.keys(this.pesquisa).length;
        if (this.showGrid && this.$state.current.name == 'app.cadastroCliente') {
            this.carregarGrid();
        }

        /**
         * @desc Se editando ou cadastrando, carrega-se as UFs
         */
        if (this.$state.current.name == 'app.cadastroCliente.incluirCliente') {
            this.carregarDadosApoio();
        }

        if (this.$state.current.name == 'app.cadastroCliente.editarCliente') {
            this.carregarDadosApoio();
            $this.visualizar($this.$stateParams.id);
            $this.carregarEdicao($this.$stateParams.id);
        }

        if (this.$state.current.name == "app.cadastroCliente.visualizar") {
            this.visualizar($this.$stateParams.id);
        }

        /**
         * @description Função para paginação do sistema
         * @param {type} page
         * @param {type} limit
         * @returns {undefined}
         */
        this.onPaginate = function (page, limit) {

            var sucesso = function (response) {
                $this.totalCount = response.headers('x-total-count');
                $this.gridContent = response.data;
                $this.showGrid = true;
            };
            var erro = function (response) {
                $this.$log.error(response);
            };
            $this.ClienteService.paginate(page, limit).then(sucesso, erro);
        };
    }

    /**
     * @description carrega a grid de clientes
     * @returns {undefined}
     */
    ClienteController.prototype.carregarGrid = function () {

        var $this = this;
        var sucesso = function (response) {

            angular.forEach(response.data, function (obj) { //mascara de cnpj
                obj.cnpj = $this.maskInJs.cnpj(obj.cnpj);
            });
            $this.totalCount = response.headers('x-total-count');
            $this.gridContent = response.data;
            $this.showGrid = true;
        };
        var erro = function (response) {
            $this.$log.error(response);
        };
        this.ClienteService.search(this.pesquisa).then(sucesso, erro);
    };

    /**
     * @description Carrregar dados de UF e municipio
     * @returns {undefined}
     */
    ClienteController.prototype.carregarDadosApoio = function () {
        var $this = this;
        this.uf = $this.Uf.data; //Pega lista de Ufs cadastradas.
    };

    /**
     * @description Função para listar municipios de uma determinada UF
     * @returns {undefined}
     */
    ClienteController.prototype.buscarMunicipio = function (idUf) {
        var $this = this;
        var municipios;
        $this.municipioService.get(idUf).then(function (response) {
            $this.municipio = response.data;
        }, function () {
            $this.$log.info("Falha ao recuperar os municipios");
        });
        this.municipio = municipios;
        $this.$log.info(this.municipio);
    };

    /**
     * @description Função para realizar validação de pesquisaa dos clientes;
     * Caso tenha algum parametro o sistema efetuará uma busca no sistema com os parametros informados.
     * @returns {undefined}
     */
    ClienteController.prototype.pesquisar = function () {
        var $this = this;
        if (!this.pesquisa.hasOwnProperty('nome') &&
                !this.pesquisa.hasOwnProperty('cnpj') &&
                !this.pesquisa.hasOwnProperty('codigoSonda') &&
                !this.pesquisa.hasOwnProperty('ativo')
                ) {
            $this.$mdToast.show(
                    $this.$mdToast.simple()
                    .textContent('Por favor, informe ao menos algum dos par\u00e2metros!')
                    .position('right')
                    .hideDelay(5000)
                    );
        } else {
            this.ClienteService.setPesquisaParams(this.pesquisa);
            this.carregarGrid();
        }
    };

    /**
     * @description Função para visualizar cliente
     * @returns {undefined}
     */
    ClienteController.prototype.visualizar = function (id) {
        var $this = this;
        var sucesso = function (response) {
            $this.visualizarForm = response.data;
            //Mascara de CNPJ
            response.data.cnpj = $this.maskInJs.cnpj(response.data.cnpj);
            //Mascara de telefone
            $this.visualizarForm.telefones[0] = $this.maskInJs.telefone(response.data.telefones[0].ddd + response.data.telefones[0].numero);
            //Mascara de CEP
            $this.visualizarForm.enderecos[0].cep = $this.maskInJs.cep(response.data.enderecos[0].cep);
            //Trocar o valor de ativo se TRUE = Sim e FALSE = Não
            $this.visualizarForm.ativo === true ? $this.visualizarForm.ativo = "Sim" : $this.visualizarForm.ativo = "Não"

            //$this.$log.info($this.visualizarForm);
        };
        var erro = function (response) {
            $this.$log.error(response);
        };
        $this.ClienteService.getById(id).then(sucesso, erro);
    };

    /**
     * @description Função para carregar determinado cliente na tela de edição
     * @param {type} id
     * @returns {undefined}
     */
    ClienteController.prototype.carregarEdicao = function (id) {
        var $this = this;
        var sucesso = function (response) {

            $this.telefone = response.data.telefones[0].ddd + response.data.telefones[0].numero;

            $this.editCliente = response.data;

            //Transforma o valor do response data em String
            $this.editCliente.enderecos[0].uf = JSON.stringify(response.data.enderecos[0].municipio.uf.id);

            $this.buscarMunicipio($this.editCliente.enderecos[0].uf);

        };
        var erro = function (response) {
            $this.$log.error(response);
        };
        $this.ClienteService.getById(id).then(sucesso, erro);
    };

    /**
     * @description Função para realização de exclusão de cliente
     * @returns {undefined}
     */
    ClienteController.prototype.remover = function (cliente) {
        var $this = this;
        var confirm = this.$mdDialog.confirm()
                .title('Deseja excluir o registro selecionado?')
                .textContent()
                .ariaLabel('Lucky day')
                .targetEvent(cliente)
                .clickOutsideToClose(true)
                .parent(angular.element(document.body))
                .ok('Sim')
                .cancel('Não');
        this.$mdDialog.show(confirm).then(function () {
            var sucesso = function () {
                $this.$mdToast.show(
                        $this.$mdToast.simple()
                        .textContent("O(a) " + cliente.nome + " foi excluído(a) com sucesso!")
                        .position('right')
                        .hideDelay(5000)
                        );
                $this.carregarGrid();
            };
            var erro = function () {
                $this.$mdToast.show(
                        $this.$mdToast.simple()
                        .textContent("Falha ao excluir " + cliente.nome)
                        .position('right')
                        .hideDelay(5000)
                        );
            };
            $this.ClienteService.delete(cliente.id).then(sucesso, erro);
        }, function () {
            $this.$log.error('Deu erro');
        });
    };

    /** 
     * @description Validação de campos do formulario
     * @returns {undefined}
     * 
     */
    ClienteController.prototype.validarForm = function () {
        /**
         * Criação da instância do objeto deferido
         */
        var defer = this.$q.defer();
        var $this = this;
        if (this.clienteForm.$invalid) {
            angular.forEach($this.clienteForm.$error, function (val, index) {
                angular.forEach(val, function (obj) {
                    /**
                     * Atribuição de $touched = true para os objetos inválidos
                     */
                    $this.clienteForm[obj.$name].$setTouched();
                });
            });
            /**
             * Rejeição do objeto deferido
             */
            defer.reject();
        } else {
            /**
             * Aceitação do objeto deferido
             */
            defer.resolve();
        }
        /**
         * Retorno do promise do objeto deferido
         */
        return defer.promise;
    };

    /**
     * @description Função para validação de campos do formulario de edição
     * @returns {clienteController_L7.ClienteController.$q@call;defer.promise}
     */
    ClienteController.prototype.validarFormEdicao = function () {
        /**
         * Criação da instância do objeto deferido
         */
        var defer = this.$q.defer();
        var $this = this;
        if (this.editClienteForm.$invalid) {
            angular.forEach($this.editClienteForm.$error, function (val, index) {
                angular.forEach(val, function (obj) {
                    /**
                     * Atribuição de $touched = true para os objetos inválidos
                     */
                    $this.editClienteForm[obj.$name].$setTouched();
                });
            });
            /**
             * Rejeição do objeto deferido
             */
            defer.reject();
        } else {
            /**
             * Aceitação do objeto deferido
             */
            defer.resolve();
        }
        /**
         * Retorno do promise do objeto deferido
         */
        return defer.promise;
    };

    /**
     * @description Função para persistir os dados do cliente no banco
     * @returns {undefined}
     */
    ClienteController.prototype.salvar = function (obj) {
        var $this = this;
        /**
         * @desc função de erro da validação
         */
        var erro = function () {
            $this.$mdToast.show(
                    $this.$mdToast.simple()
                    .textContent('Por favor, verifique as obrigatoriedades')
                    .position('right')
                    .hideDelay(5000)
                    );
        };
        var sucesso = function () {
            var objBackEnd = {
                "id": obj.cliente.id,
                "nome": obj.cliente.nome,
                "telefones": [
                    {
                        "ddd": obj.cliente.telefone.substr(0, 2),
                        "numero": obj.cliente.telefone.substr(2),
                        "tipoTelefone": 1
                    }
                ],
                "enderecos": [
                    {
                        "municipio": {
                            "id": obj.cliente.endereco.municipio.id,
                            "uf": {
                                "id": obj.cliente.endereco.uf
                            }
                        },
                        "cep": obj.cliente.endereco.cep,
                        "numero": obj.cliente.endereco.numero,
                        "logradouro": obj.cliente.endereco.logradouro,
                        "complemento": obj.cliente.endereco.complemento,
                        "tipoEndereco": 1
                    }
                ],
                "cnpj": obj.cliente.cnpj,
                "codigoSonda": obj.cliente.codigoSonda,
                "ativo": obj.cliente.ativo
            };
            //Função sucesso para salvar cliente
            var sucesso = function () {
                $this.$mdToast.show(
                        $this.$mdToast.simple()
                        .textContent("O(a) " + obj.cliente.nome + " foi incluido(a) com sucesso!")
                        .position('right')
                        .hideDelay(5000)
                        );
                $this.$state.go('app.cadastroCliente');
            };
            //Função erro para salvar cliente
            var erro = function () {
                $this.$mdToast.show(
                        $this.$mdToast.simple()
                        .textContent("Falha ao salvar o cliente")
                        .position('right')
                        .hideDelay(5000)
                        );
            };
            //Chamado do service para salvar
            $this.ClienteService.save(objBackEnd).then(sucesso, erro);
        };
        $this.validarForm().then(sucesso, erro);
    };

    /**
     * @description Função para edição de clientes
     * @param {type} obj
     * @returns {undefined}
     */
    ClienteController.prototype.editar = function (obj) {
        var $this = this;
        /**
         * @desc função de erro da validação
         */
        var erro = function () {
            $this.$mdToast.show(
                    $this.$mdToast.simple()
                    .textContent('Por favor, verifique as obrigatoriedades')
                    .position('right')
                    .hideDelay(5000)
                    );
        };
        var sucesso = function () {

            var objBackEnd = {
                "id": obj.editCliente.id,
                "nome": obj.editCliente.nome,
                "telefones": [
                    {
                        "id": $this.editCliente.telefones[0].id,
                        "ddd": obj.telefone.substr(0, 2),
                        "numero": obj.telefone.substr(2),
                        "tipoTelefone": 1
                    }
                ],
                "enderecos": [
                    {
                        "id": obj.editCliente.enderecos[0].id,
                        "municipio": {
                            "id": obj.editCliente.enderecos[0].municipio.id,
                            "uf": {
                                "id": obj.editCliente.enderecos[0].uf
                            }
                        },
                        "cep": obj.editCliente.enderecos[0].cep,
                        "numero": obj.editCliente.enderecos[0].numero,
                        "logradouro": obj.editCliente.enderecos[0].logradouro,
                        "complemento": obj.editCliente.enderecos[0].complemento,
                        "tipoEndereco": 1
                    }
                ],
                "cnpj": obj.editCliente.cnpj,
                "codigoSonda": obj.editCliente.codigoSonda,
                "ativo": obj.editCliente.ativo
            };
            //Função sucesso para salvar cliente
            var sucesso = function () {
                $this.$mdToast.show(
                        $this.$mdToast.simple()
                        .textContent("O(a) " + obj.editCliente.nome + " foi alterado(a) com sucesso!")
                        .position('right')
                        .hideDelay(5000)
                        );
                $this.$state.go('app.cadastroCliente');
            };
            //Função erro para salvar cliente
            var erro = function () {
                $this.$mdToast.show(
                        $this.$mdToast.simple()
                        .textContent("Falha ao salvar o cliente")
                        .position('right')
                        .hideDelay(5000)
                        );
            };
            //Chamado do service para salvar               
            console.log(angular.toJson(objBackEnd));
            $this.ClienteService.update($this.$stateParams.id, angular.fromJson(objBackEnd)).then(sucesso, erro);
        };
        $this.validarFormEdicao().then(sucesso, erro);
    };

    /**
     * @description Função que traz mensagem na tela quando o usuário não informa os campos obrigatorios
     * do endereço quando informado - Formulário de Cadastro
     * @param {type} cnpj
     * @returns {undefined}
     */
    ClienteController.prototype.verificarEndereco = function (obj) {
        var $this = this;

        //Esconde as mensafens de erro.
        $this.enderecoRequired = true;
        $this.camposObrigatorioMsg = true;

        //Caso algum campo de endereço seja informado os demais campos se tornam obrigatórios.
        if (obj.cliente.endereco.uf === "" ||
                obj.cliente.endereco.municipio.id === "" ||
                obj.cliente.endereco.cep === "" ||
                obj.cliente.endereco.logradouro === "") {
            $this.enderecoRequired = true;
            $this.camposObrigatorioMsg = true;
        } else {
            $this.enderecoRequired = false;
            $this.camposObrigatorioMsg = false;
        }
    };

    /**
     * @description Função que traz mensagem na tela quando o usuário não informa os campos obrigatorios
     * do endereço quando informado - Formulario de Edição
     * @returns {undefined}
     */
    ClienteController.prototype.verificarEnderecoEdicao = function (obj) {
        var $this = this;

        //Esconde as mensafens de erro.
        $this.enderecoRequired = true;
        $this.camposObrigatorioMsg = true;

        //Caso algum campo de endereço seja informado os demais campos se tornam obrigatórios.
        if (obj.editCliente.enderecos[0].uf === "" ||
                obj.editCliente.enderecos[0].municipio.id === "" ||
                obj.editCliente.enderecos[0].cep === "" ||
                obj.editCliente.enderecos[0].logradouro === "") {
            $this.enderecoRequired = true;
            $this.camposObrigatorioMsg = true;
        } else {
            $this.enderecoRequired = false;
            $this.camposObrigatorioMsg = false;
        }

    };

    /**
     * 
     * @description Função para verificar se o CNPJ informado tem registro no sistema.
     * @returns {undefined}
     */
    ClienteController.prototype.verificarCnpjCadastrado = function (cnpj) {
        var $this = this;
        if (cnpj.length > 13) {
            this.ClienteService.cnpj(cnpj).then(function () {
                $this.clienteForm.cnpj.$setTouched();
                $this.cnpjExistente = true;
            }, function () {
                $this.cnpjExistente = false;
            });
        }
    };

    /**
     * @description Função para realização de limpeza de formulario de pesquisa
     * @returns {undefined}
     */
    ClienteController.prototype.limparPesquisa = function () {
        this.pesquisa = this.gridContent = {};
        this.showGrid = false;
        this.ClienteService.setPesquisaParams({});
    };

    /**
     * Limpeza de campos dos formularios de editar e incluir
     * @returns {undefined}
     */
    ClienteController.prototype.limparCampos = function () {
        var $this = this;
        $this.editCliente = {};
        $this.cliente = {};
        $this.unidade = {};
        $this.telefone = null;
        $this.enderecoRequired = false;
        $this.camposObrigatorioMsg = false;
    };

    /**
     * @description Função do botão cancelar
     * @param {type} evt
     * @returns {undefined}
     */
    ClienteController.prototype.cancelar = function (evt) {
        var $this = this;
        var confirm = this.$mdDialog.confirm()
                .title('Cancelar?')
                .textContent()
                .ariaLabel('Lucky day')
                .targetEvent(evt)
                .clickOutsideToClose(true)
                .parent(angular.element(document.body))
                .ok('Sim')
                .cancel('Não');
        this.$mdDialog.show(confirm).then(function () {
            $this.$state.go('app.cadastroCliente');
        });
    };

    //Funcao para adicionar um novo fiscal tecnico
    ClienteController.prototype.adicionarUnidade = function (obj) {
        var $this = this;

        $this.totalCount++;
        //Atribuição de valores do form
        $this.unidadesFiscais.push(angular.copy(obj));
        //Limpeza do formulario unidade Fiscal
        $this.unidadeFiscal = {};

    };

    ClienteController.prototype.removerUnidade = function (obj) {
        var $this = this;

        var index = $this.unidadesFiscais.indexOf(obj)
        $this.unidadesFiscais.splice(index, 1);
        
        $this.totalCount--;        
    };

})();
