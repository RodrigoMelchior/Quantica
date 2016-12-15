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
                'app.pages.private.dados',
                'app.pages.private.dados-c'
            ])
            .config(config);

    function config(msNavigationServiceProvider) {
        
        msNavigationServiceProvider.saveItem('fuse', {
            title: 'Bem Vindo',
            group: true,
            privilege: ['1','2','3'],
            weight: 1
        });
        
            msNavigationServiceProvider.saveItem('fuse.atualizarDados', {
                title: 'Atualizar Cadastro',
                icon: 'icon-briefcase-checked',
                state: 'app.atualizarDados',
                privilege: ['2'],
                weight: 1
            });

            msNavigationServiceProvider.saveItem('fuse.estruturaOrganizacional', {
                title: 'Estrutura Organizacional',
                icon: 'icon-account-network',
                state: 'app.estruturaOrganizacional',
                privilege: ['2'],
                weight: 1
            });

            msNavigationServiceProvider.saveItem('fuse.estruturaCargos', {
                title: 'Estrutura de Cargos',
                icon: 'icon-account-network',
                state: 'app.estruturaCargos',
                privilege: ['2'],
                weight: 1
            });

            msNavigationServiceProvider.saveItem('fuse.responderPerguntas', {
                title: 'Responder Perguntas',
                icon: 'icon-comment-check',
                state: 'app.responderPerguntas',
                privilege: ['2'],
                weight: 1
            });

            msNavigationServiceProvider.saveItem('fuse.importarArquivo', {
                title: 'Importar Arquivo CSV',
                icon: 'icon-upload',
                state: 'app.importarArquivo',
                privilege: ['2'],
                weight: 1
            });

            msNavigationServiceProvider.saveItem('fuse.emitirRelatorio', {
                title: 'Emitir Relatório',
                icon: 'icon-file',
                state: 'app.emitirRelatorioAdmin',
                privilege: ['1'],
                weight: 1
            });
        
            msNavigationServiceProvider.saveItem('fuse.emitirRelatorio', {
                title: 'Emitir Relatório',
                icon: 'icon-file',
                state: 'app.emitirRelatorio',
                privilege: ['2'],
                weight: 1
            });
        
            msNavigationServiceProvider.saveItem('fuse.empresa', {
                title: 'Empresas',
                icon: 'icon-city',
                state: 'app.empresa',
                privilege: ['1'],
                weight: 1
            });

            msNavigationServiceProvider.saveItem('fuse.usuario', {
                title: 'Usuários',
                icon: 'icon-account',
                state: 'app.usuario',
                privilege: ['1'],
                weight: 1
            });

            msNavigationServiceProvider.saveItem('fuse.item', {
                title: 'Itens',
                icon: 'icon-clipboard-text',
                state: 'app.item',
                privilege: ['1'],
                weight: 1
            });

            msNavigationServiceProvider.saveItem('fuse.inpc', {
                title: 'INPC',
                icon: 'icon-clipboard-text',
                state: 'app.inpc',
                privilege: ['1'],
                weight: 1
            });

            msNavigationServiceProvider.saveItem('fuse.pergunta', {
                title: 'Perguntas',
                icon: 'icon-help',
                state: 'app.pergunta',
                privilege: ['1'],
                weight: 1
            });

            msNavigationServiceProvider.saveItem('fuse.pesquisa', {
                title: 'Pesquisas',
                icon: 'icon-magnify',
                state: 'app.pesquisa',
                privilege: ['1'],
                weight: 1
            });
        
        msNavigationServiceProvider.saveItem('fuse.dados', {
            title: 'Relatórios',
            icon: 'icon-clipboard-text',
            state: 'app.dados',
            privilege: ['3'],
            weight: 1
        });
        
    };
})();
