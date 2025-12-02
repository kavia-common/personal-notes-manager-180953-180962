package com.example.notesbackend.notes.service;

import com.example.notesbackend.notes.domain.Note;
import com.example.notesbackend.notes.repo.NoteRepository;
import com.example.notesbackend.notes.web.dto.NoteRequest;
import com.example.notesbackend.notes.web.dto.NoteResponse;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer that encapsulates business logic for notes.
 */
@Service
@Transactional
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    // PUBLIC_INTERFACE
    public NoteResponse create(NoteRequest request) {
        /** Create a new note from a request DTO. */
        Note note = new Note()
                .setTitle(request.getTitle())
                .setContent(request.getContent())
                .setTags(request.getTags())
                .setArchived(Boolean.TRUE.equals(request.getArchived()));
        Note saved = repository.save(note);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    public NoteResponse update(Long id, NoteRequest request) {
        /** Update an existing note by id. */
        Note existing = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Note with id " + id + " not found"));
        existing
                .setTitle(request.getTitle())
                .setContent(request.getContent())
                .setTags(request.getTags())
                .setArchived(Boolean.TRUE.equals(request.getArchived()));
        Note saved = repository.save(existing);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public NoteResponse get(Long id) {
        /** Fetch single note by id. */
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public Page<NoteResponse> list(Integer page, Integer size, String sort, Boolean archived, String q) {
        /** List notes with pagination, sorting, and optional archived filter and query (title contains). */
        int p = page == null || page < 0 ? 0 : page;
        int s = size == null || size <= 0 ? 20 : size;
        Sort sortObj = parseSort(sort);
        Pageable pageable = PageRequest.of(p, s, sortObj);

        Page<Note> result;
        boolean archivedVal = archived != null && archived;

        if (q != null && !q.isBlank()) {
            if (archived != null) {
                result = repository.findByArchivedAndTitleContainingIgnoreCase(archivedVal, q.trim(), pageable);
            } else {
                result = repository.findByTitleContainingIgnoreCase(q.trim(), pageable);
            }
        } else {
            if (archived != null) {
                result = repository.findByArchived(archivedVal, pageable);
            } else {
                result = repository.findAll(pageable);
            }
        }

        return result.map(this::toResponse);
    }

    // PUBLIC_INTERFACE
    public void delete(Long id) {
        /** Hard delete a note by id. */
        if (!repository.existsById(id)) {
            throw new NotFoundException("Note with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    private Sort parseSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return Sort.by(Sort.Direction.DESC, "updatedAt");
        }
        // Example: "title,asc" or "createdAt,desc"
        String[] parts = sort.split(",", 2);
        String property = parts[0].trim();
        Sort.Direction dir = Sort.Direction.DESC;
        if (parts.length > 1) {
            try {
                dir = Sort.Direction.fromString(parts[1].trim());
            } catch (IllegalArgumentException ignored) {
            }
        }
        return Sort.by(dir, property);
    }

    private NoteResponse toResponse(Note note) {
        return new NoteResponse()
                .setId(note.getId())
                .setTitle(note.getTitle())
                .setContent(note.getContent())
                .setTags(note.getTags())
                .setArchived(note.isArchived())
                .setCreatedAt(note.getCreatedAt())
                .setUpdatedAt(note.getUpdatedAt());
    }
}
