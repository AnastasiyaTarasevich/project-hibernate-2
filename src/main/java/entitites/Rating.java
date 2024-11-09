package entitites;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Rating {
    G ("G"),
    PG ("PG"),
    PG13("PG-13"),
    R ("R"),
    NC17("NC-17");

    private final String value;

}
