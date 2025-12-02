package com.example.notesbackend.notes.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

/**
 * Response DTO for notes.
 */
@Schema(description = "Note response model")
public class NoteResponse {

    @Schema(description = "Unique identifier", example = "1")
    private Long id;

    @Schema(description = "Title of the note", example = "Grocery list")
    private String title;

    @Schema(description = "Content/body of the note", example = "Eggs, Milk, Bread")
    private String content;

    @Schema(description = "Comma-separated tags", example = "personal,shopping")
    private String tags;

    @Schema(description = "Whether the note is archived", example = "false")
    private boolean archived;

    @Schema(description = "Creation timestamp (UTC)", example = "2024-11-13T12:34:56Z")
    private Instant createdAt;

    @Schema(description = "Last update timestamp (UTC)", example = "2024-11-14T08:42:10Z")
    private Instant updatedAt;

    public NoteResponse() {
    }

    public Long getId() {
        return id;
    }

    public NoteResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NoteResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NoteResponse setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public NoteResponse setTags(String tags) {
        this.tags = tags;
        return this;
    }

    public boolean isArchived() {
        return archived;
    }

    public NoteResponse setArchived(boolean archived) {
        this.archived = archived;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public NoteResponse setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public NoteResponse setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
