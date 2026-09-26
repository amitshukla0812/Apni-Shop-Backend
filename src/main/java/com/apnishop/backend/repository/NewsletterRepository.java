package com.apnishop.backend.repository;

import com.apnishop.backend.entity.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsletterRepository extends JpaRepository<Newsletter, String> {
}
