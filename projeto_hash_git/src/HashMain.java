import java.util.Random;
import java.util.Scanner;

public class HashMain {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000};
        int[] datasetSizes = {1_000_000, 5_000_000, 20_000_000};
        long seed = 42;

        for (int datasetSize : datasetSizes) {
            Registro[] dataset = RegistroGenerator.generate(datasetSize, seed);
            for (int size : sizes) {
                HashTable[] tables = {
                        new HashRest(size),
                        new HashMultiplicacao(size),
                        new HashDobramento(size)
                };
                for (HashTable table : tables) {
                    long start = System.nanoTime();
                    int colisoes = 0;
                    for (Registro r : dataset) {
                        if (!table.insert(r)) colisoes++;
                    }
                    long end = System.nanoTime();
                    System.out.printf("Insercao em %s (tamanho %d, %d registros): %dms, Colisoes: %d\n",
                            table.getClass().getSimpleName(), size, datasetSize, (end - start) / 1_000_000, colisoes);

                    int comparacoes = 0;
                    start = System.nanoTime();
                    for (int i = 0; i < 5; i++) {
                        int idx = new Random(seed + i).nextInt(datasetSize);
                        comparacoes += table.search(dataset[idx].codigo);
                    }
                    end = System.nanoTime();
                    System.out.printf("Busca em %s: %dms, Comparacoes: %d\n\n", table.getClass().getSimpleName(), (end - start) / 1_000_000, comparacoes);
                }
            }
        }
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
        Random r = new Random(seed);
        for (int i = 0; i < count; i++) {
            int codigo = 100_000_000 + r.nextInt(900_000_000);
            registros[i] = new Registro(codigo);
        }
        return registros;
    }
}

abstract class HashTable {
    int size;
    Registro[] table;
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
}

class HashRest extends HashTable {
    public HashRest(int size) { super(size); }
    public int hash(int key) { return key % size; }
}

class HashMultiplicacao extends HashTable {
    final double A = 0.6180339887;
    public HashMultiplicacao(int size) { super(size); }
    public int hash(int key) {
        double frac = (key * A) % 1;
        return (int)(frac * size);
    }
}

class HashDobramento extends HashTable {
    public HashDobramento(int size) { super(size); }
    public int hash(int key) {
        String str = String.valueOf(key);
        int sum = 0;
        for (int i = 0; i < str.length(); i += 2) {
            String parte = (i + 2 <= str.length()) ? str.substring(i, i + 2) : str.substring(i);
            sum += Integer.parseInt(parte);
        }
        return sum % size;
    }
}
