package com.apnishop.backend.repository;

import com.apnishop.backend.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, String> {
}
