package com.example.notesbackend.notes.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO for creating/updating notes.
 */
@Schema(description = "Payload to create or update a note")
public class NoteRequest {

    @NotBlank(message = "title must not be blank")
    @Schema(description = "Title of the note", example = "Grocery list", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Content/body of the note", example = "Eggs, Milk, Bread")
    private String content;

    @Schema(description = "Comma-separated tags", example = "personal,shopping")
    private String tags;

    @Schema(description = "Whether the note is archived", example = "false", defaultValue = "false")
    private Boolean archived = false;

    public NoteRequest() {
    }

    public String getTitle() {
        return title;
    }

    public NoteRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NoteRequest setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public NoteRequest setTags(String tags) {
        this.tags = tags;
        return this;
    }

    public Boolean getArchived() {
        return archived;
    }

    public NoteRequest setArchived(Boolean archived) {
        this.archived = archived;
        return this;
    }
}
