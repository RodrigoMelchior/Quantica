(function ()
{
    'use strict';

    angular
            .module('app.pages.private', [
                'app.pages.private.dashboard',
                'app.pages.private.selecionaPesquisa',
                'app.pages.private.atualizarDados',
                'app.pages.private.importarArquivo',
                'app.pages.private.estruturaCargos',
                'app.pages.private.estruturaOrganizacional',
                'app.pages.private.empresa',
                'app.pages.private.usuario',
                'app.pages.private.item',
                'app.pages.private.inpc',
                'app.pages.private.pergunta',
                'app.pages.private.responderPerguntas',
                'app.pages.private.pesquisa',
                'app.pages.private.dados'
            ])
            .config(config);

    function config(msNavigationServiceProvider) {
        
        var idPerfil = localStorage.getItem('idPerfil');
        
console.log("idPerfil",idPerfil);
console.log("idPerfil == 1",idPerfil == 1);
console.log("idPerfil == 2",idPerfil == 2);
console.log("idPerfil == 1 || idPerfil == 2",idPerfil == 1 || idPerfil == 2);
console.log("atual");
        if (idPerfil == 1 || idPerfil == 2){
            msNavigationServiceProvider.saveItem('fuse', {
                title: 'Bem Vindo',
                group: true,
                weight: 1
            });
        }

        if (idPerfil == 2){
            msNavigationServiceProvider.saveItem('fuse.atualizarDados', {
                title: 'Atualizar Cadastro',
                icon: 'icon-briefcase-checked',
                state: 'app.atualizarDados',
                weight: 1
            });
        }

        if (idPerfil == 2){
            msNavigationServiceProvider.saveItem('fuse.estruturaOrganizacional', {
                title: 'Estrutura Organizacional',
                icon: 'icon-account-network',
                state: 'app.estruturaOrganizacional',
                weight: 1
            });
        }

        if (idPerfil == 2){
            msNavigationServiceProvider.saveItem('fuse.estruturaCargos', {
                title: 'Estrutura de Cargos',
                icon: 'icon-account-network',
                state: 'app.estruturaCargos',
                weight: 1
            });
        }

        if (idPerfil == 2){
            msNavigationServiceProvider.saveItem('fuse.responderPerguntas', {
                title: 'Responder Perguntas',
                icon: 'icon-comment-check',
                state: 'app.responderPerguntas',
                weight: 1
            });
        }

        if (idPerfil == 2){
            msNavigationServiceProvider.saveItem('fuse.importarArquivo', {
                title: 'Importar Arquivo CSV',
                icon: 'icon-upload',
                state: 'app.importarArquivo',
                weight: 1
            });
        }

        if (idPerfil == 1){
            msNavigationServiceProvider.saveItem('fuse.emitirRelatorio', {
                title: 'Emitir Relatório',
                icon: 'icon-file',
                state: 'app.emitirRelatorioAdmin',
                weight: 1
            });
        }
        
        if (idPerfil == 2){
            msNavigationServiceProvider.saveItem('fuse.emitirRelatorio', {
                title: 'Emitir Relatório',
                icon: 'icon-file',
                state: 'app.emitirRelatorio',
                weight: 1
            });
        }

        if (idPerfil == 1){
            msNavigationServiceProvider.saveItem('fuse.empresa', {
                title: 'Empresas',
                icon: 'icon-city',
                state: 'app.empresa',
                weight: 1
            });
        }

        if (idPerfil == 1){
            msNavigationServiceProvider.saveItem('fuse.usuario', {
                title: 'Usuários',
                icon: 'icon-account',
                state: 'app.usuario',
                weight: 1
            });
        }

        if (idPerfil == 1){
            msNavigationServiceProvider.saveItem('fuse.item', {
                title: 'Itens',
                icon: 'icon-clipboard-text',
                state: 'app.item',
                weight: 1
            });
        }

        if (idPerfil == 1){
            msNavigationServiceProvider.saveItem('fuse.inpc', {
                title: 'INPC',
                icon: 'icon-clipboard-text',
                state: 'app.inpc',
                weight: 1
            });
        }

        if (idPerfil == 1){
            msNavigationServiceProvider.saveItem('fuse.pergunta', {
                title: 'Perguntas',
                icon: 'icon-help',
                state: 'app.pergunta',
                weight: 1
            });
        }

        if (idPerfil == 1){
            msNavigationServiceProvider.saveItem('fuse.pesquisa', {
                title: 'Pesquisas',
                icon: 'icon-magnify',
                state: 'app.pesquisa',
                weight: 1
            });
        }
        
        if (idPerfil == 1){
            msNavigationServiceProvider.saveItem('fuse.dados', {
                title: 'Dados Gerais',
                icon: 'icon-magnify',
                state: 'app.dados',
                weight: 1
            });
        }
    };
})();
