# Tennis

## Descrição
Esse projeto tem como objetivo ser parte do meu portfólio e como exemplo de um app para Android. Por enquanto, o app é um placar de tênis para Android bonito e simples de usar. No futuro, histórico de partidas e sincronização pela com outros jogadores farão parte da lista de funcionalidades do aplicativo. Mais sobre os itens a serem implementados abaixo. Com o aumento do uso de Kotlin no Android, cada vez mais se fazem necessários exemplos que se aproveitam das vantagens do Kotlin, e não somente códigos "Java" que usam *keywords* do Kotlin. XMLs de *drawable* foram utilizados por permitir formatos e preenchimentos personalizados, além de ajudar com a organização do código.

## Quem sou eu
Meu nome é Vitor, tenho 21 anos, sou formado e pós-graduado em desenvolvimento de games. Apesar da minha formação trabalho com aplicativos e adoro o que faço. Estou sempre buscando formas de crescer profissionalmente e inovar e esse projeto faz parte disso. Este é o primeiro aplicativo que faço com o intuito de constituir um portfólio aberto e será o primeiro de muitos. Veja meu perfil no [LinkedIn](https://www.linkedin.com/in/vitor-sramos).

## Roadmap
### Melhorias
Independentemente de qual funcionalidade está sendo implementa, a todo momento é de se esperar que melhorias, tanto no código, quanto no produto final sejam aplicadas. Essas melhorias podem ser refatorações de código, atualizações, utilização de novas ferramentas ou mudanças na usabilidade, além de correção de bugs.

### Testes
O próximo passo é a implementação dos testes, que vão ajudar a manter a estabilidade do projeto conforme novas funcionalidades são adicionadas ao aplicativos.

### Room
Um histórico de placares ficará salvo no celular para consulta do(a) jogador(a), com as informações introduzidas por ele(a) próprio(a), além de data hora, tempo de jogo e, futuramente, a localização, coletados automaticamente.

### Firebase
Será possível convidar outra pessoa para a partida, sincronizando o placar do jogo em tempo real. Para isso, o login do Firebase vai ser usado para autenticar os jogadores e o armazenamento para a atualização em tempo real do placar e *backup* do histórico na nuvem.

### Localização
Caso seja do interesse do(a) jogador(a), o aplicativo poderá guardar a informação de localização no momento da partida, e mostrá-la no histórico, juntamente com outras informacões relevantes.

### Bluetooth
Como uma alternativa para a sincronização do placar utilizando a nuvem, será possível parear dois dispositivos com o aplicativo e sincronizar o placar localmente, sem conexão com a internet.

### Wear OS
Considerando a dificuldade de jogar com o celular no bolso, uma versão do app para Wear OS será implementada para que os(as) próprios(as) jogadores(as) marquem a pontuação enquanto jogam, utilizando dispositivos vestíveis.
