package ru.itmo.gostev.testing.lab1.task2;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TestCase {
  private final String name;
  private final String description;

  private final List<Entry<Integer, String>> entries;

  private final Query query;

  private final Entry<Integer, String> queryEntry;

  private final List<Action> actions;

  @JsonCreator
  public TestCase(
      @JsonProperty("name") final String name,
      @JsonProperty("description") final String description,
      @JsonProperty("entries") final List<Entry<Integer, String>> entries,
      @JsonProperty("query") final Query query,
      @JsonProperty("queryEntry") final Entry<Integer, String> queryEntry,
      @JsonProperty("actions") final List<Action> actions
  ) {
    this.name = name;
    this.description = description;
    this.entries = entries;
    this.query = query;
    this.queryEntry = queryEntry;
    this.actions = actions;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public List<Entry<Integer, String>> getEntries() {
    return entries;
  }

  public Query getQuery() {
    return query;
  }

  public Entry<Integer, String> getQueryEntry() {
    return queryEntry;
  }

  public List<Action> getActions() {
    return actions;
  }

  public static enum Action { GO_LEFT, GO_RIGHT, PUT_LEFT, PUT_RIGHT }

  public static enum Query { PUT, FIND }

  public static class Entry<K, V> {
    private final K key;
    private final V value;

    @JsonCreator
    public Entry(@JsonProperty("key") K key, @JsonProperty("value") V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }
  }
}
