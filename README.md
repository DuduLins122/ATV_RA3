
# Trabalho de Estrutura de Dados — Tabela Hash

## Integrantes
- Marcelo Eduardo Claudiano Nascimento Lins
- Carlos Eduardo Jerônimo
- Miguel Wihby
- Gulherme Santos

Curso: Ciência da Computação

---

## Objetivo

Implementar e analisar o desempenho de diferentes funções de hashing em tabelas hash com variações de tamanho e volume de dados. Foram utilizadas três funções de hash e diferentes tamanhos de tabela para avaliar eficiência em termos de colisões, tempo de inserção e busca.

---

## Escolha das Funções Hash

Foram escolhidas três funções para a dispersão de valores na tabela:

1. **Resto da divisão**: simples e direta, usa `key % size`.
2. **Multiplicação de Knuth**: baseada em uma constante multiplicativa que ajuda na uniformização.
3. **Dobramento**: soma blocos de dígitos do número, promovendo uma distribuição mais balanceada.

A escolha se baseia em observar variações reais de desempenho e colisões em cenários com diferentes padrões de chave.

---

## Tamanhos dos Conjuntos de Dados

Para avaliar escalabilidade, foram usados:

- 1.000.000 de registros
- 5.000.000 de registros
- 20.000.000 de registros

Cada registro é um número de 9 dígitos, gerado com `Random(seed)` para garantir testes consistentes entre as funções.

---

## Tamanhos das Tabelas Hash

Três tamanhos distintos de tabela hash foram definidos para observar o impacto da densidade:

- 1.000 posições
- 10.000 posições
- 100.000 posições

Cada um com proporção 10x em relação ao anterior, como exigido.

---

## Gráficos dos Resultados e Comparativos

Todos os resultados (tempo de inserção, colisões, tempo e comparações de busca) foram salvos automaticamente no arquivo `resultados_hash.csv`.

Esse arquivo pode ser utilizado em qualquer ferramenta como Excel ou Python para gerar gráficos que comparam:

- Função hash vs. número de colisões
- Tamanho da tabela vs. tempo de inserção
- Comparações de busca por função hash e tamanho da tabela

---

## Explicação de Qual Função Foi Melhor e Por Quê

Durante os testes:

- A função **Multiplicação de Knuth** se destacou por apresentar **menos colisões** em tabelas maiores e **tempo de inserção razoável**.
- A função **Resto** foi eficiente em tempo, mas gerou mais colisões em tabelas pequenas.
- A função **Dobramento** teve desempenho intermediário, com boas colisões, mas tempo um pouco superior à multiplicação.

Conclusão: **Multiplicação de Knuth foi a mais equilibrada**, com boa dispersão e tempo de execução adequado.

---

## Conclusão

A análise mostra que o tipo de função hash e o tamanho da tabela influenciam diretamente o desempenho da estrutura.

- Em tabelas pequenas, há maior número de colisões, especialmente com funções simples.
- Tabelas grandes consomem mais tempo, mas distribuem melhor os dados.
- A Multiplicação de Knuth oferece uma boa relação entre tempo e qualidade de dispersão.

