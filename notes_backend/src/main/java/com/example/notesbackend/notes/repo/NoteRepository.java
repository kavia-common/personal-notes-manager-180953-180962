package com.example.notesbackend.notes.repo;

import com.example.notesbackend.notes.domain.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Page<Note> findByArchived(boolean archived, Pageable pageable);

    Page<Note> findByArchivedAndTitleContainingIgnoreCase(boolean archived, String title, Pageable pageable);

    Page<Note> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
