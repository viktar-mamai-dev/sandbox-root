package com.mamay.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * mapped with table tag
 */
@Entity
@Table(name = "tag")
@NamedQueries({
        @NamedQuery(name = "Tag.loadByIdList",
                query = "SELECT t FROM TagEntity t WHERE t.id in :tagIdList")
})
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class TagEntity implements Serializable {
    private static final long serialVersionUID = 9221657837871000144L;

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "tag_seq_generator")
    @SequenceGenerator(name = "tag_seq_generator", sequenceName = "TAG_SEQ")
    private Long id;

    @Column(name = "tag_name", length = 30, nullable = false)
    @EqualsAndHashCode.Include
    private String name;

    @Version
    @Column(name = "version")
    private int version;

}
