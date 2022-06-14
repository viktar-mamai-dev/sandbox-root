package edu.coursera.concurrent.week1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImprovementStats {
    private final double addImprovement, containsImprovement, removeImprovement;
}
