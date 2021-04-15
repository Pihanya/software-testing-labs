package ru.itmo.anokhin.testing.lab2.utils;

import org.apache.commons.csv.CSVFormat;

public class IOConstants {

  public static final String[] UNARY_FUNCTION_CSV_HEADERS = new String[]{"X", "Результаты модуля (X)"};

  public static final String UNARY_FUNCTION_X_COLUMN = UNARY_FUNCTION_CSV_HEADERS[0];

  public static final String UNARY_FUNCTION_Y_COLUMN = UNARY_FUNCTION_CSV_HEADERS[1];

  public static final Character CSV_COMMENT_MARKER = ';';

  public static final CSVFormat UNARY_FUNCTION_RAW_CSV_FORMAT = CSVFormat.DEFAULT.withCommentMarker(CSV_COMMENT_MARKER);

  public static final CSVFormat UNARY_FUNCTION_WRITER_CSV_FORMAT = UNARY_FUNCTION_RAW_CSV_FORMAT.withHeader(UNARY_FUNCTION_CSV_HEADERS);

  public static final CSVFormat UNARY_FUNCTION_READER_CSV_FORMAT = UNARY_FUNCTION_RAW_CSV_FORMAT.withHeader(UNARY_FUNCTION_CSV_HEADERS).withSkipHeaderRecord();
}
