package com.apnishop.backend.repository;

import com.apnishop.backend.entity.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepository extends JpaRepository<Testimonial, String> {
}
