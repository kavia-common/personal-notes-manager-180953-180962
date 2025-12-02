package com.example.notesbackend.notes.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

/**
 * JPA Entity representing a Note.
 */
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title must not be blank")
    @Column(nullable = false)
    private String title;

    @Lob
    @Column
    private String content;

    /**
     * Optional comma-separated tags
     */
    @Column
    private String tags;

    @Column(nullable = false)
    private boolean archived = false;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    public Note() {
    }

    public Note(String title, String content, String tags, boolean archived) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.archived = archived;
    }

    @PrePersist
    public void prePersist() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Note setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Note setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Note setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public Note setTags(String tags) {
        this.tags = tags;
        return this;
    }

    public boolean isArchived() {
        return archived;
    }

    public Note setArchived(boolean archived) {
        this.archived = archived;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Note setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Note setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
