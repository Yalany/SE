package nlp;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public final class SynonymsSet {
  private final HashSet<String> synonyms = new HashSet<>();
  /**
   * @param synonymsCollection коллекция String с синонимами, добавляемыми к этому списку
   */
  public SynonymsSet addSynonyms(final Collection<String> synonymsCollection) {
    synonyms.addAll(synonymsCollection);
    return this;
  }

  /**
   * @param synonymsArray массив String с синонимами, добавляемыми к этому списку
   */
  public SynonymsSet addSynonyms(final String[] synonymsArray) {
    synonyms.addAll(Arrays.asList(synonymsArray));
    return this;
  }

  /**
   * @param synonym синоним, добавляемый к этому списку
   */
  public SynonymsSet addSynonym(final String synonym) {
    synonyms.add(synonym);
    return this;
  }

  /**
   * @param words массив слов, каждое из которых будет проверено на принадлежность к этому списку
   * @return true если хотя бы один записанный синоним похож хотя бы на одно слово из words,
   *         false если ни один записанный синоним не похож ни на одно слово в words
   */
  public boolean containsAny(final String[] words) {
    return Arrays.stream(words).anyMatch(this::contains);
  }

  /**
   * @param words коллекция слов, каждое из которых будет проверено на принадлежность к этому списку
   * @return true если хотя бы один записанный синоним похож хотя бы на одно слово из word,
   *         false если ни один записанный синоним не похож ни на одно слово в words
   */
  public boolean containsAny(final Collection<String> words) {
    return words.stream().anyMatch(this::contains);
  }

  /**
   * @param word слово, которое будет проверено на принадлежность к этому списку
   * @return true если хотя бы один записанный синоним похож на word,
   *         false если ни один записанный синоним не похож на word
   */
  public boolean contains(final String word) {
    return synonyms.contains(word) || synonyms.stream().anyMatch(synonym -> StringSimilarity.isSimilar(synonym, word));
  }

  /**
   * @param words массив слов, каждое из которых будет проверено на принадлежность к этому списку
   * @return true если хотя бы один записанный синоним совпадает хотя бы с одним словом из words,
   *         false если ни один записанный синоним не совпадет ни с одним словом в words
   */
  public boolean containsPreciseAny(final String[] words) {
    return Arrays.stream(words).anyMatch(this::containsPrecise);
  }

  /**
   * @param words коллекция слов, каждое из которых будет проверено на принадлежность к этому списку
   * @return true если хотя бы один записанный синоним совпадает хотя бы с одним словом из words,
   *         false если ни один записанный синоним не совпадет ни с одним словом в words
   */
  public boolean containsPreciseAny(final Collection<String> words) {
    return words.stream().anyMatch(this::containsPrecise);
  }

  /**
   * @param word слово, котороя будет проверено на принадлежность к этому списку
   * @return true если хотя бы один записанный синоним похож на word,
   *         false если ни один записанный синоним не похож на word
   */
  public boolean containsPrecise(final String word) {
    return synonyms.contains(word) || synonyms.stream().anyMatch(synonym -> synonym.equals(word));
  }
}
