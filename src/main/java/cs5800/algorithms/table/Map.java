package cs5800.algorithms.table;

import java.io.IOException;
import java.util.List;

public interface Map<U,V> {
    void insert(U u, V v) throws IOException;
    void delete(U u) throws IOException;
    V increase(U u) throws IOException;
    V find(U u) throws IOException;
    List<U> listAllKeys();
}
