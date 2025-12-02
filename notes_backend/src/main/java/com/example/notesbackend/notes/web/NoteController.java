package com.example.notesbackend.notes.web;

import com.example.notesbackend.notes.service.NoteService;
import com.example.notesbackend.notes.web.dto.NoteRequest;
import com.example.notesbackend.notes.web.dto.NoteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller providing Notes CRUD operations.
 */
@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes", description = "CRUD operations for personal notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a note", description = "Creates a new note and returns it")
    public NoteResponse create(@Valid @RequestBody NoteRequest request) {
        /** Create a note. */
        return service.create(request);
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(
            summary = "List notes",
            description = "Returns a pageable list of notes. Supports filtering by archived and searching by title with 'q'."
    )
    public Page<NoteResponse> list(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "archived", required = false) Boolean archived,
            @RequestParam(value = "q", required = false) String q
    ) {
        /** List notes with pagination and optional filters. */
        return service.list(page, size, sort, archived, q);
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(summary = "Get a note", description = "Returns a single note by id")
    public NoteResponse get(@PathVariable("id") Long id) {
        /** Get single note by id. */
        return service.get(id);
    }

    // PUBLIC_INTERFACE
    @PutMapping("/{id}")
    @Operation(summary = "Update a note", description = "Updates the note with the specified id")
    public NoteResponse update(@PathVariable("id") Long id, @Valid @RequestBody NoteRequest request) {
        /** Update note by id. */
        return service.update(id, request);
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a note", description = "Deletes the note with the specified id")
    public void delete(@PathVariable("id") Long id) {
        /** Delete note by id. */
        service.delete(id);
    }
}
