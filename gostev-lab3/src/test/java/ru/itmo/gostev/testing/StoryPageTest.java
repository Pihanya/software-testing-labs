package ru.itmo.gostev.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.itmo.gostev.testing.page.StoryPage;
import ru.itmo.gostev.testing.utils.PropertiesHelper;

public class StoryPageTest {

  @Test
  public void performTestCases() {
    final List<StoryPageTestCase> testCases = loadTestCases();
    for (final StoryPageTestCase testCase : testCases) {
      final StoryPage page = new StoryPage(PropertiesHelper.getTestProperties().getDriverType(), testCase.getUrl());

      assertEquals(testCase.getPublicationTime(), page.getPublicationTime());
      assertEquals(testCase.getTitle(), page.getTitle());
      assertTrue(greatestCommonPrefix(testCase.getShortText(), page.getShortText()).length() >= page.getShortText().length() / 2);

      page.close();
    }
  }

  private static List<StoryPageTestCase> loadTestCases() {
    final ObjectMapper mapper = new ObjectMapper();
    try {

      final List<File> caseFiles = Arrays.asList(
          Path.of(StoryPageTest.class.getClassLoader()
              .getResource("stories")
              .toURI()).toFile()
              .listFiles((dir, name) -> name.startsWith("case") && name.endsWith(".json"))
      );
      final List<StoryPageTestCase> cases = new ArrayList<>(caseFiles.size());
      for (final File caseFile : caseFiles) {
        try {
          cases.add(mapper.readValue(caseFile, StoryPageTestCase.class));
        } catch (final Exception e) {
          throw new RuntimeException(e);
        }
      }
      return cases;
    } catch (final URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  private static String greatestCommonPrefix(String a, String b) {
    int minLength = Math.min(a.length(), b.length());
    for (int i = 0; i < minLength; i++) {
      if (a.charAt(i) != b.charAt(i)) {
        return a.substring(0, i);
      }
    }
    return a.substring(0, minLength);
  }

  public static class StoryPageTestCase {
    final String url;

    final String publicationTime;

    final String title;

    final String shortText;

    public StoryPageTestCase(
        @JsonProperty("url") final String url,
        @JsonProperty("publicationTime") final String publicationTime,
        @JsonProperty("title") final String title,
        @JsonProperty("shortText") final String shortText) {
      this.url = url;
      this.publicationTime = publicationTime;
      this.title = title;
      this.shortText = shortText;
    }

    public String getUrl() {
      return url;
    }

    public String getPublicationTime() {
      return publicationTime;
    }

    public String getTitle() {
      return title;
    }

    public String getShortText() {
      return shortText;
    }
  }
}
