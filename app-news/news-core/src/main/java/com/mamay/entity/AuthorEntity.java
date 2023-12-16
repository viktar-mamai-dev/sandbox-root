package com.mamay.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * mapped with table author
 */
@Entity
@Table(name = "author")
@NamedQueries({
        @NamedQuery(name = "Author.loadActiveAuthors",
                query = "SELECT a FROM AuthorEntity a WHERE a.expiredDate is NULL ORDER BY a.name")
})
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AuthorEntity implements Serializable {
    private static final long serialVersionUID = -7256138554405623825L;

    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "author_seq_generator")
    @SequenceGenerator(name = "author_seq_generator", sequenceName = "AUTHOR_SEQ")
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "expired")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime expiredDate;

    @Version
    @Column(name = "version")
    private int version;
}
