package services.question.types;

import com.google.common.collect.ImmutableList;
import java.time.Instant;
import java.util.Optional;
import java.util.OptionalLong;
import services.LocalizedStrings;
import services.question.QuestionOption;

/**
 * Defines a dropdown question, which has a list of options, of which at most one and at least one
 * must be selected.
 */
public final class DropdownQuestionDefinition extends MultiOptionQuestionDefinition {

  public DropdownQuestionDefinition(
      OptionalLong id,
      String name,
      Optional<Long> enumeratorId,
      String description,
      LocalizedStrings questionText,
      LocalizedStrings questionHelpText,
      ImmutableList<QuestionOption> options,
      Optional<Instant> lastModifiedTime) {
    super(
        id,
        name,
        enumeratorId,
        description,
        questionText,
        questionHelpText,
        options,
        SINGLE_SELECT_PREDICATE,
        lastModifiedTime);
  }

  public DropdownQuestionDefinition(
      String name,
      Optional<Long> enumeratorId,
      String description,
      LocalizedStrings questionText,
      LocalizedStrings questionHelpText,
      ImmutableList<QuestionOption> options) {
    super(
        name,
        enumeratorId,
        description,
        questionText,
        questionHelpText,
        options,
        SINGLE_SELECT_PREDICATE);
  }

  @Override
  public QuestionType getQuestionType() {
    return QuestionType.DROPDOWN;
  }
}
