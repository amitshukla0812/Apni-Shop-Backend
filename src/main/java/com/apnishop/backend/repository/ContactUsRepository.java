package com.apnishop.backend.repository;

import com.apnishop.backend.entity.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsRepository extends JpaRepository<ContactUs, String> {
}
