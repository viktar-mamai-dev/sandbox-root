package com.mamay.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * mapped with table news
 */
@Entity
@Table(name = "news")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class NewsEntity implements Serializable {
    private static final long serialVersionUID = 8513291983185889134L;

    @Id
    @Column(name = "news_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "news_seq_generator")
    @SequenceGenerator(name = "news_seq_generator", sequenceName = "NEWS__SEQ")
    private Long id;
    @Column(name = "short_text")
    @EqualsAndHashCode.Include
    private String shortText;
    @Column(name = "full_text")
    private String fullText;
    @Column(name = "title")
    @EqualsAndHashCode.Include
    private String title;
    @Column(name = "creation_date", updatable = false)
    @Temporal(value = TemporalType.DATE)
    private LocalDateTime creationDate;
    @Column(name = "modification_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime modificationDate;
    @Version
    @Column(name = "version")
    private int version;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "news_tag", joinColumns = {@JoinColumn(name = "news_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<TagEntity> tags;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "news_author", joinColumns = {@JoinColumn(name = "news_id")}, inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private AuthorEntity author;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "news")
    @OrderBy(value = "creationDate desc")
    private List<CommentEntity> comments;

    @PrePersist
    public void onPersist() {
        this.modificationDate = this.creationDate;
    }
}
