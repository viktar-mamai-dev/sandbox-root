package com.mamay.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * mapped with table comments
 */
@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CommentEntity implements Serializable {
    private static final long serialVersionUID = -1985331543318205729L;

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "comment_seq_generator")
    @SequenceGenerator(name = "comment_seq_generator", sequenceName = "COMMENT_SEQ")
    private Long id;

    @Column(name = "comment_text", length = 100, nullable = false)
    @EqualsAndHashCode.Include
    private String text;

    @Column(name = "creation_date", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private NewsEntity news;

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }
}
