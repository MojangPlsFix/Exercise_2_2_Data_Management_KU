package com.fabianrossmann.ingestData.io;

import java.io.IOException;
import java.util.List;

public interface CSVFileReader<T> {
    public List<T> readCSV(String path) throws IOException;
}
