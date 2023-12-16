package edu.coursera.concurrent.week1;

class TestResults {
  public final String lbl;

  public final double addRate;
  public final double containsRate;
  public final double removeRate;

  public final int listLengthAfterAdds;
  public final int totalContainsSuccesses;
  public final int totalContainsFailures;
  public final int listLengthAfterRemoves;
  public final int totalRemovesSuccesses;
  public final int totalRemovesFailures;

  public TestResults(
      final String lbl,
      final double addRate,
      final double containsRate,
      final double removeRate,
      final int listLengthAfterAdds,
      final int totalContainsSuccesses,
      final int totalContainsFailures,
      final int listLengthAfterRemoves,
      final int totalRemovesSuccesses,
      final int totalRemovesFailures) {
    this.lbl = lbl;

    this.addRate = addRate;
    this.containsRate = containsRate;
    this.removeRate = removeRate;

    this.listLengthAfterAdds = listLengthAfterAdds;
    this.totalContainsSuccesses = totalContainsSuccesses;
    this.totalContainsFailures = totalContainsFailures;
    this.listLengthAfterRemoves = listLengthAfterRemoves;
    this.totalRemovesSuccesses = totalRemovesSuccesses;
    this.totalRemovesFailures = totalRemovesFailures;
  }
}

class TestResultsPair {
  public final TestResults A;
  public final TestResults B;

  public TestResultsPair(final TestResults setA, final TestResults setB) {
    A = setA;
    B = setB;
  }
}
