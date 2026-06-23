# Projeto Xadrez - Programação Orientada a Objetos

Este projeto consiste em um simulador de jogo de xadrez desenvolvido em Java, focado na aplicação de conceitos fundamentais de Programação Orientada a Objetos (POO). O jogo é executado via console e permite a partida entre dois jogadores locais.

## 🚀 Como Jogar

O jogo funciona em turnos, alternando entre as peças **Brancas** (letras maiúsculas) e **Pretas** (letras minúsculas).

### Comandos de Movimentação
Para realizar uma jogada, o sistema solicitará três informações:
1. **Letra da peça:** O caractere da peça que você deseja mover (Ex: `P`, `T`, `C`, `B`, `D`, `R`).
2. **Posição da peça (Origem):** A coordenada atual da peça no model.tabuleiro.
3. **Posição de chegada (Destino):** A coordenada para onde a peça deve ir.

**Formato das Coordenadas:** Use o padrão `coluna` (a-h) seguido da `linha` (1-8).
* **Exemplo:**
    * Peça na posição: `e2`
    * Vai para: `e4`

### Legenda do Tabuleiro
* `P` / `p`: Peão
* `T` / `t`: Torre
* `C` / `c`: Cavalo
* `B` / `b`: Bispo
* `D` / `d`: Dama
* `R` / `r`: Rei
* `.`: Casa vazia

---

## 🧠 Habilidades Exercitadas

O desenvolvimento deste projeto permitiu a aplicação prática de diversos pilares da Engenharia de Software e POO:

1.  **Abstração e Encapsulamento:** Modelagem das entidades do jogo (Peças, Tabuleiro, Posição) com atributos privados e métodos de acesso controlados para garantir a integridade dos dados.
2.  **Herança:** Utilização de uma classe base `Peca` que define comportamentos comuns, estendida por classes específicas para cada tipo de peça (Bispo, Cavalo, etc.), promovendo a reutilização de código.
3.  **Polimorfismo:** Implementação do método `movimentosValidos` em cada subclasse de peça. Embora a assinatura seja a mesma, cada peça executa sua própria lógica de cálculo de movimento.
4.  **Lógica de Algoritmos Complexos:** Desenvolvimento de algoritmos para detecção de colisão (caminho livre), verificação de **Cheque** e **Cheque-Mate**, exigindo simulações de jogadas futuras para validar a segurança do Rei.
5.  **Tratamento de Matrizes:** Manipulação de matrizes bidimensionais para representar o model.tabuleiro e as possibilidades de movimento no espaço 8x8.

---

## 🛠️ Estrutura do Projeto

* **`Main.java`**: Ponto de entrada do programa, gerencia o fluxo de turnos e as entradas do usuário.
* **`model.modelo`**: Contém definições básicas como as cores das peças.
* **`model.tabuleiro`**: Gerencia a lógica do model.tabuleiro, posições, casas e validação de movimentos.
* **`model.pecas`**: Contém a lógica específica de movimento para cada peça do xadrez.

---
*Este trabalho foi desenvolvido como parte dos requisitos da disciplina de Programação Orientada a Objetos.*
