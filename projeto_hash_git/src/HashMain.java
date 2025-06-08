import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class HashMain {
    public static void main(String[] args) {
        HashBenchmark benchmark = new HashBenchmark();
        benchmark.runTests();
    }
}

class HashBenchmark {
    private static final int[] TABLE_SIZES = {1000, 10000, 100000};
    private static final int[] DATASET_SIZES = {1_000_000, 5_000_000, 20_000_000};
    private static final long SEED = 42;
    private static final int NUM_BUSCAS = 5;
    private static final String CSV_PATH = "resultados_hash.csv";

    public void runTests() {
        try (FileWriter writer = new FileWriter(CSV_PATH)) {
            writer.write("Funcao Hash,Tamanho Tabela,Quantidade de Dados,Colisoes,Tempo Insercao (ms),Comparacoes Busca,Tempo Busca (ms)\n");

            for (int datasetSize : DATASET_SIZES) {
                Registro[] dataset = RegistroGenerator.generate(datasetSize, SEED);

                for (int tableSize : TABLE_SIZES) {
                    HashTable[] hashTables = {
                        new HashRest(tableSize),
                        new HashMultiplicacao(tableSize),
                        new HashDobramento(tableSize)
                    };

                    for (HashTable table : hashTables) {
                        runInsertTest(writer, table, dataset, datasetSize);
                        runSearchTest(writer, table, dataset, datasetSize);
                    }
                }
            }

            System.out.println("✅ Resultados exportados para " + CSV_PATH);
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo CSV: " + e.getMessage());
        }
    }

    private void runInsertTest(FileWriter writer, HashTable table, Registro[] dataset, int datasetSize) throws IOException {
        long startTime = System.nanoTime();
        int colisoes = 0;

        for (Registro r : dataset) {
            if (!table.insert(r)) {
                colisoes++;
            }
        }

        long tempoInsercao = (System.nanoTime() - startTime) / 1_000_000;

        System.out.printf("Insercao em %s (tabela %d, %d registros): %dms, Colisoes: %d\n",
                table.getClass().getSimpleName(), table.size, datasetSize, tempoInsercao, colisoes);

        table.setInsertMetrics(colisoes, tempoInsercao);
    }

    private void runSearchTest(FileWriter writer, HashTable table, Registro[] dataset, int datasetSize) throws IOException {
        Random random = new Random(SEED);
        int comparacoes = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_BUSCAS; i++) {
            int index = random.nextInt(datasetSize);
            comparacoes += table.search(dataset[index].codigo);
        }
        long tempoBusca = (System.nanoTime() - startTime) / 1_000_000;

        System.out.printf("Busca em %s: %dms, Comparacoes: %d\n\n",
                table.getClass().getSimpleName(), tempoBusca, comparacoes);

        writer.write(String.format("%s,%d,%d,%d,%d,%d,%d\n",
                table.getClass().getSimpleName(),
                table.size,
                datasetSize,
                table.getColisoes(),
                table.getTempoInsercao(),
                comparacoes,
                tempoBusca));
    }
}

class Registro {
    int codigo;

    public Registro(int codigo) {
        this.codigo = codigo;
    }
}

class RegistroGenerator {
    public static Registro[] generate(int count, long seed) {
        Registro[] registros = new Registro[count];
        Random random = new Random(seed);
        for (int i = 0; i < count; i++) {
            int codigo = 100_000_000 + random.nextInt(900_000_000);
            registros[i] = new Registro(codigo);
        }
        return registros;
    }
}

abstract class HashTable {
    int size;
    Registro[] table;

    private int colisoes;
    private long tempoInsercao;

    public HashTable(int size) {
        this.size = size;
        this.table = new Registro[size];
    }

    abstract int hash(int key);

    public boolean insert(Registro r) {
        int index = hash(r.codigo);
        int tries = 0;
        while (table[index] != null && tries < size) {
            index = (index + 1) % size;
            tries++;
        }
        if (tries == size) return false;
        table[index] = r;
        return true;
    }

    public int search(int key) {
        int index = hash(key);
        int tries = 0, comparisons = 0;
        while (table[index] != null && tries < size) {
            comparisons++;
            if (table[index].codigo == key) return comparisons;
            index = (index + 1) % size;
            tries++;
        }
        return comparisons;
    }

    // Getters e setters para resultados
    public void setInsertMetrics(int colisoes, long tempoInsercao) {
        this.colisoes = colisoes;
        this.tempoInsercao = tempoInsercao;
    }

    public int getColisoes() {
        return colisoes;
    }

    public long getTempoInsercao() {
        return tempoInsercao;
    }
}

class HashRest extends HashTable {
    public HashRest(int size) { super(size); }

    @Override
    public int hash(int key) {
        return key % size;
    }
}

class HashMultiplicacao extends HashTable {
    private static final double A = 0.6180339887;

    public HashMultiplicacao(int size) { super(size); }

    @Override
    public int hash(int key) {
        double frac = (key * A) % 1;
        return (int) (frac * size);
    }
}

class HashDobramento extends HashTable {
    public HashDobramento(int size) { super(size); }

    @Override
    public int hash(int key) {
        String str = String.valueOf(key);
        int sum = 0;
        for (int i = 0; i < str.length(); i += 2) {
            String part = (i + 2 <= str.length()) ? str.substring(i, i + 2) : str.substring(i);
            sum += Integer.parseInt(part);
        }
        return sum % size;
    }
}
