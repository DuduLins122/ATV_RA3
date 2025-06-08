# Trabalho de Estrutura de Dados — Tabela Hash

## 👨‍💻 Autor
Nome: Marcelo Eduardo Claudiano Nascimento Lins, Carlos Eduardo Jerônimo, Miguel Wihby, Gulherme Santos  
Curso: Ciência da Computação  
Semestre:   

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
- 1 milhão de registros (executado com sucesso)
- 5 milhões e 20 milhões (execução futura com otimização por rodada)

### Funções hash implementadas
1. **Resto da divisão**
2. **Multiplicação de Knuth**
3. **Dobramento (soma de blocos)**

---

## 🔢 Geração dos Dados

- Cada registro é um número de 9 dígitos (tipo `String`).
- Dados gerados com `Random(seed)` para garantir reprodutibilidade entre rodadas.
- Todos os registros são inseridos e buscados com os mesmos conjuntos para garantir justiça nos testes.

---

## 📊 Resultados para 1 Milhão de Registros

### 📈 Número de Colisões
`Veja os dados reais no arquivo graficos_dados_corrigido_final.csv`

### ⏱️ Tempo de Inserção (ms)
`Veja os dados reais no arquivo graficos_dados_corrigido_final.csv`

### 🔍 Comparações nas Buscas
`Veja os dados reais no arquivo graficos_dados_corrigido_final.csv`

---

## 🧠 Análise dos Resultados

- **Colisões** foram muito altas nas tabelas pequenas, especialmente com 1000 posições (99,9% de colisão).
- **Tempos de inserção** aumentaram com o tamanho da tabela, principalmente por causa da sondagem linear.
- **Função de dobramento** teve desempenho levemente inferior em tempo, mas similar em colisões.
- Com **100.000 posições**, o tempo de inserção aumentou bastante devido ao custo de sondagem em uma área de espalhamento muito grande.

---

## ✅ Conclusão

- A função **Multiplicação de Knuth** apresentou o melhor custo-benefício geral entre tempo e colisão.
- Para grandes volumes de dados, o tamanho da tabela **deve ser bem planejado**, pois o impacto no tempo de inserção cresce muito.
- **Todas as funções hash são sensíveis ao fator de carga**, mas os testes com 1 milhão de dados mostraram limites seguros e comparáveis entre si.

---

## ▶️ Instruções para Execução

1. Abra o projeto no IntelliJ.
2. Compile e execute `HashMain.java`.
3. O programa irá gerar os resultados no console e salvar no arquivo `resultados_hash.csv`.
4. Os gráficos são gerados automaticamente se rodar o script de análise Python (ou você pode montar manualmente com Excel).

---

