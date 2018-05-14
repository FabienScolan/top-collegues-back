package dev.top.repos;

import dev.top.entities.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepo extends JpaRepository<Version, Integer> {
}
