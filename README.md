
# Trabalho de Estrutura de Dados — Tabela Hash

## 👥 Integrantes
- Marcelo Eduardo Claudiano Nascimento Lins
- Carlos Eduardo Jerônimo
- Miguel Wihby
- Gulherme Santos

Curso: Ciência da Computação

---

## 📌 Objetivo

Implementar e analisar o desempenho de diferentes funções de hashing em tabelas hash com variações de tamanho e volume de dados. Foram utilizadas três funções de hash e diferentes tamanhos de tabela para avaliar eficiência em termos de colisões, tempo de inserção e busca.

---

## ⚙️ Estratégias Utilizadas

### Tamanhos da tabela hash
- 1.000 posições
- 10.000 posições
- 100.000 posições

### Tamanhos dos conjuntos de dados
- 1 milhão de registros
- 5 milhões de registros
- 20 milhões de registros

### Funções hash implementadas
1. **Resto da divisão**
2. **Multiplicação de Knuth**
3. **Dobramento (soma de blocos)**

---

## 🔢 Geração dos Dados

- Cada registro é um número de 9 dígitos (tipo `int`).
- Dados gerados com `Random(seed)` para garantir reprodutibilidade entre rodadas.
- Todos os registros são inseridos e buscados com os mesmos conjuntos para garantir justiça nos testes.

---

## 📊 Resultados

Os resultados foram exportados automaticamente para o arquivo `resultados_hash.csv`, contendo:

- Nome da função hash
- Tamanho da tabela
- Quantidade de dados
- Número de colisões
- Tempo de inserção (ms)
- Número de comparações nas buscas
- Tempo de busca (ms)

Esse arquivo pode ser utilizado para gerar gráficos comparativos em qualquer ferramenta de visualização (Excel, Google Sheets, Python etc.).

---

## ✅ Conclusão

- A função **Multiplicação de Knuth** apresentou o melhor custo-benefício entre tempo e dispersão.
- Tabelas com 1.000 posições apresentaram taxa altíssima de colisão (até 99%).
- O desempenho da **tabela de 100.000 posições** em tempo de inserção e busca caiu devido à dispersão ampla e sondagem.
- Os dados e métricas são reprodutíveis, e a implementação seguiu as regras da atividade com uso exclusivo de estruturas autorizadas.

---

## 📂 Arquivos Entregues

- `HashMain.java`: código-fonte completo com geração de dados, testes e exportação em CSV.
- `resultados_hash.csv`: resultados de todas as rodadas com métricas coletadas.
- `README.md`: este documento explicativo.
