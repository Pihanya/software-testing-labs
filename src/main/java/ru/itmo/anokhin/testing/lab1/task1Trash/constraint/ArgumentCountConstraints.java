package ru.itmo.anokhin.testing.lab1.task1S.constraint;

import java.util.ArrayList;
import java.util.List;

public class ArgumentCountConstraints implements TimesConstraint {

  private final List<TimesConstraint> constraints;

  public ArgumentCountConstraints(final List<TimesConstraint> constraints) {
    this.constraints = constraints;
  }

  @Override
  public boolean matches(int times) {
    return constraints.stream().allMatch(constraint -> constraint.matches(times));
  }

  public static Builder builder() {
    return new Builder();
  }

  abstract static class AbstractTimesConstraint implements TimesConstraint {
    private final Integer times;

    protected AbstractTimesConstraint(Integer times) {
      this.times = times;
    }

    protected Integer getTimes() {
      return times;
    }
  }

  public static class ExactlyTimes extends AbstractTimesConstraint {

    public ExactlyTimes(Integer times) {
      super(times);
    }

    @Override
    public boolean matches(int times) {
      return getTimes() == times;
    }
  }

  public static class MoreTimes extends AbstractTimesConstraint {

    public MoreTimes(Integer times) {
      super(times);
    }

    @Override
    public boolean matches(int times) {
      return getTimes() > times;
    }
  }

  public static class LessTimes extends AbstractTimesConstraint {

    public LessTimes(Integer times) {
      super(times);
    }

    @Override
    public boolean matches(int times) {
      return getTimes() < times;
    }
  }

  public static class Builder {
    private final List<TimesConstraint> constraints = new ArrayList<>(5);

    private Builder() {
    }

    public Builder moreThan(final Integer times) {
      constraints.add(new MoreTimes(times));
      return this;
    }

    public Builder atLeast(final Integer times) {
      constraints.add(new MoreTimes(times - 1));
      return this;
    }

    public Builder lessThan(final Integer times) {
      constraints.add(new LessTimes(times));
      return this;
    }

    public Builder notMoreThan(final Integer times) {
      constraints.add(new LessTimes(times + 1));
      return this;
    }

    public ArgumentCountConstraints exactly(final Integer times) {
      return new ArgumentCountConstraints(List.of(new ExactlyTimes(times)));
    }

    public ArgumentCountConstraints build() {
      return new ArgumentCountConstraints(constraints);
    }
  }
}
