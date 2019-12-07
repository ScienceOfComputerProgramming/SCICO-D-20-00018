package com.gamaza.rest4cep.mysql.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Event Types Database Model
 */
@Entity @Table(name = "esper_event_types")
@Getter @Setter @EqualsAndHashCode
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "channel_id", nullable = false, unique = true)
    private Integer channel;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "esper_event_type_epl_patterns", joinColumns = {@JoinColumn(name = "event_type_id")}, inverseJoinColumns = {@JoinColumn(name = "epl_pattern_id")})
    private List<EplEventPattern> eplEventPatterns;

}
