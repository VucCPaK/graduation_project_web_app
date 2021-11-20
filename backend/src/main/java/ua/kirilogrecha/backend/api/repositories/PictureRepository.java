package ua.kirilogrecha.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.kirilogrecha.backend.api.entities.EPicture;

public interface PictureRepository extends JpaRepository<EPicture, String> {

}
