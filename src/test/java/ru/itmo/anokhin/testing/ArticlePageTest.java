package ru.itmo.anokhin.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.itmo.anokhin.testing.page.ArticlePage;
import ru.itmo.anokhin.testing.page.ArticlePage.BreadCrumbElement;
import ru.itmo.anokhin.testing.utils.PropertiesHelper;

public class ArticlePageTest {

  @Test
  public void articleContentsCorrespondTestCases() {
    final var testCases = loadTestCases();
    for (final var testCase : testCases) {
      final ArticlePage page = new ArticlePage(PropertiesHelper.getTestProperties().getDriverType(), testCase.getUrl());

      assertEquals(testCase.getTopic(), page.getTopic());

      final var breadCrumb = page.getBreadCrumb();
      assertEquals(testCase.getBreadCrumb().size(), breadCrumb.size());

      for (int i = 0; i < testCase.getBreadCrumb().size(); ++i) {
        final var caseBreadCrumbElement = testCase.getBreadCrumb().get(0);
        final var pageBreadCrumbElement = breadCrumb.get(0);

        assertEquals(caseBreadCrumbElement.getTitle(), pageBreadCrumbElement.getTitle());
        assertEquals(caseBreadCrumbElement.getHref(), pageBreadCrumbElement.getHref());
      }

      assertEquals(testCase.getName(), page.getName());

      assertEquals(testCase.getPreviousArticleUrl(), page.getPreviousArticleUrl());
      assertEquals(testCase.getNextArticleUrl(), page.getNextArticleUrl());

      page.close();
    }
  }

  private static List<ArticlePageTestCase> loadTestCases() {
    final ObjectMapper mapper = new ObjectMapper();
    try {

      final List<File> caseFiles = Arrays.asList(
          Path.of(ArticlePageTest.class.getClassLoader()
              .getResource("articles")
              .toURI()).toFile()
              .listFiles((dir, name) -> name.startsWith("case") && name.endsWith(".json"))
      );
      final List<ArticlePageTestCase> cases = new ArrayList<>(caseFiles.size());
      for (final File caseFile : caseFiles) {
        try {
          cases.add(mapper.readValue(caseFile, ArticlePageTestCase.class));
        } catch (final Exception e) {
          throw new RuntimeException(e);
        }
      }
      return cases;
    } catch (final URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public static class ArticlePageTestCase {

    private final String url;

    private final List<BreadCrumbElement> breadCrumb;

    private final String topic;

    private final String previousArticleUrl;

    private final String nextArticleUrl;

    private final String name;

    @JsonCreator
    public ArticlePageTestCase(
        @JsonProperty("url") final String url,
        @JsonProperty("breadCrumb") final List<BreadCrumbElement> breadCrumb,
        @JsonProperty("topic") final String topic,
        @JsonProperty("previousArticleUrl") final String previousArticleUrl,
        @JsonProperty("nextArticleUrl") final String nextArticleUrl,
        @JsonProperty("name") final String name
    ) {
      this.url = url;
      this.breadCrumb = breadCrumb;
      this.topic = topic;
      this.previousArticleUrl = previousArticleUrl;
      this.nextArticleUrl = nextArticleUrl;
      this.name = name;
    }

    public String getUrl() {
      return url;
    }

    public List<BreadCrumbElement> getBreadCrumb() {
      return breadCrumb;
    }

    public String getTopic() {
      return topic;
    }

    public String getPreviousArticleUrl() {
      return previousArticleUrl;
    }

    public String getNextArticleUrl() {
      return nextArticleUrl;
    }

    public String getName() {
      return name;
    }
  }
}
