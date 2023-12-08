# Jogo da Velha N por N - Teste Técnico

Este é um aplicativo de Jogo da Velha interativo desenvolvido como parte de um teste técnico para a área de programação mobile. A aplicação oferece a flexibilidade de escolher o tamanho do tabuleiro, variando de 3x3 a 10x10, e pode ser jogada entre dois jogadores ou contra um robô inteligente.

## Funcionalidades Principais

- **Tela Inicial:**
  - Botão para selecionar o tipo de jogo: "Jogar contra outra pessoa" ou "Jogar contra o robô".
  - Campos para inserir o nome dos jogadores.
  - Seletor para escolher o tamanho do tabuleiro.
  - Botão "Começar o jogo".
  - Botão "Ver histórico de jogadas".

- **Tela do Jogo:**
  - Tabuleiro do jogo que se ajusta ao tamanho selecionado.
  - Informações sobre de quem é a vez de jogar.
  - Ao finalizar o jogo, exibe o vencedor e oferece opções para repetir o jogo ou voltar.

- **Tela de Histórico:**
  - Lista em ordem decrescente de jogadas, mostrando o resultado das partidas com os nomes dos jogadores e o vencedor.
  - Persistência do histórico entre as execuções do aplicativo.

## Extras

- **Ranking:**
  - Exibe um ranking de vitórias por nome de jogador, ignorando letras maiúsculas/minúsculas e acentos.

- **Jogada Contra a Máquina:**
  - Garante que o robô não permita que o jogador ganhe caso o jogador possa vencer na próxima jogada.
  - Certifica-se de que o robô ganhe quando possível em sua jogada.
  - Implementa jogadas "inteligentes" para o robô, escolhendo locais próximos aos locais que já escolheu.

## Layout

O layout foi inspirado no design fornecido, mantendo as cores azul para o Player 1 e vermelho para o Player 2, tanto durante o jogo quanto no histórico. A estrutura é simples e intuitiva para proporcionar uma experiência agradável ao usuário.

## Tecnologias Utilizadas

- [Liste aqui as tecnologias utilizadas, como ReactJS, Swift, Flutter, etc.]

## Instruções para Execução

[Forneça instruções claras sobre como executar o projeto localmente, incluindo qualquer configuração necessária.]

---

**Nota:** Este projeto foi desenvolvido exclusivamente como parte de um teste técnico para avaliação de habilidades na área de programação mobile. Não foram utilizadas bibliotecas de terceiros que impactem diretamente a lógica de negócio.
